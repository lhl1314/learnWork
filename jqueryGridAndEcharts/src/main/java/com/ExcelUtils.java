package com;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/4 14:42
 * @description TODO
 */
public class ExcelUtils {

    /**
     *导出excel工具类
     * @param list 数据集合
     * @param titles 每个row的标题
     * @param os     输出流
     * @param str    导出文档的sheet标题
     */
    public static void exportExcelDataInfo(List<Map<String, Object>> list,List<String> titles, OutputStream os, String str) {
        if(list != null) {
            // HSSFWorkbook 97-2003 版本的 ,
            // XSSFWorkbook 2007以上版本的 无关紧要导出哪个版本的。
            Workbook workbook = new HSSFWorkbook();
//			Sheet sheet = workbook.createSheet(str);
            for(int k = 0;k<list.size()/60000+1;k++) {
                int num = list.size() < ((k + 1) * 60000 - 1) ? list.size()-1 :((k + 1) * 60000 - 1);
                List<Map<String, Object>> excelDatas = list.subList(k * 60000, num+1);
                Sheet sheet = workbook.createSheet(str+(k+1));
                if(titles != null && titles.size() > 0) {
                    int r = 0;
                    int columnNum = titles.size();
                    Row titleRow = sheet.createRow(r++);
                    CellStyle cellStyle = getTitleStyle(workbook);
                    for(int i = 0; i < columnNum; i++) {
                        Cell titleCell = titleRow.createCell(i);
                        titleCell.setCellValue(titles.get(i));
                        titleCell.setCellStyle(cellStyle);
                    }
                    if(excelDatas != null && excelDatas.size() > 0) {
                        for(Map<String, Object> map : excelDatas) {
                            Row row = sheet.createRow(r++);// 数据行
                            for(int j = 0; j < columnNum; j++) {
                                Cell cell = row.createCell(j);
                                cell.setCellValue(Objects.toString(map.get(titles.get(j)), ""));
                            }
                        }
                    }
                    sheet.setDefaultColumnStyle(k, getErrorColumnStyle(workbook));
                }
            }
            try {
                workbook.write(os);
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析excel数据
     *
     * @Title: excel2DataInfo
     * @param inputStream
     * @return
     * @return Map<String,Object>
     *     List<String> titles      excelTitles
     *       List<List<String>>  excelList
     * @throws
     */
    public static Map<String,Object> importExcelToSql(InputStream inputStream) {
        Map<String,Object>mapExcel=new HashMap<>();
        List<String> titles = new ArrayList<String>();
        List<List<String>> dataInfos = new ArrayList<List<String>>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);// 处理输入流
            Sheet sheet = workbook.getSheetAt(0);// 获取第一个sheet
            int rowNum = sheet.getLastRowNum();// 总行数
            Row firstRow = sheet.getRow(0); // 获取总行数
            int columnNum = firstRow.getLastCellNum();// 总列数

            for(int i = 0; i < columnNum; i++) {// 获取excel每列的表头
                Cell cell = firstRow.getCell((short) i);
                String title = cell.getStringCellValue();
                if(StringUtils.isEmpty(title)) {
                    break;
                }
                titles.add(title);
            }
            mapExcel.put("excelTitles",titles);

            for (int i = 1; i <= rowNum; i++) {
                List<String> cellValues = new ArrayList<String>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnNum; j++) {//对一行的每个列进行解析
                    Cell cell = row.getCell((short) j);
                    Object value = null;
                    if(null!=cell){
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_BLANK: // 貌似永远不会出现,
                                value = "";
                                break;
                            case Cell.CELL_TYPE_NUMERIC: // 数值或者日期类型
                                if (DateUtil.isCellDateFormatted(cell)) { // 日期
                                    value = cell.getDateCellValue();
                                } else {// 数值
                                    NumberFormat nf = NumberFormat.getInstance();
                                    String s = nf.format(cell.getNumericCellValue());
                                    if (s.indexOf(",") >= 0) {
                                        s = s.replace(",", "");
                                    }
                                    value = s;
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN: // 布尔值
                                value = cell.getBooleanCellValue();
                                break;
                            case Cell.CELL_TYPE_STRING: // 字符串
                                value = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA://excel公式
                                try {
                                    value = cell.getStringCellValue();
                                } catch (IllegalStateException e) {
                                    value = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    cellValues.add(Objects.toString(value, ""));
                }
                dataInfos.add(cellValues);
            }
            mapExcel.put("excelList",dataInfos);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapExcel;
    }


    /**
     * 标题单元格样式
     *
     * @Title: getTitleStyle
     * @param workbook
     * @return
     * @return CellStyle
     * @throws
     */
    public static CellStyle getTitleStyle(Workbook workbook) {
        CellStyle titleStyel = workbook.createCellStyle();
		/*XSSFFont font = (XSSFFont) workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置粗体	*/
        HSSFFont font = (HSSFFont) workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleStyel.setFont(font);
        return titleStyel;
    }
    /**
     * 设置错误单元格样式
     *
     * @Title: getErrorColumnStyle
     * @param workbook
     * @return
     * @return CellStyle
     * @throws
     */
    public static CellStyle getErrorColumnStyle(Workbook workbook) {
        CellStyle errorColumnStyel = workbook.createCellStyle();
        /*XSSFFont font = (XSSFFont) workbook.createFont();*/
//		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
        HSSFFont font = (HSSFFont) workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setColor(Font.COLOR_RED);
        errorColumnStyel.setFont(font);
        errorColumnStyel.setFillBackgroundColor(HSSFColor.RED.index);
        return errorColumnStyel;
    }

}
