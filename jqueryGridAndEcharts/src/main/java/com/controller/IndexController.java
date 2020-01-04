package com.controller;

import com.ExcelUtils;
import com.dao.CityDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.JqGridData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author lhl
 * @version 1.0
 * @date 2019/12/19 9:32
 * @description TODO
 */
@Controller
public class IndexController {
    @Autowired
    CityDao cityDao;

    /**
     * 获取city的数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCity")
    @ResponseBody
    public JqGridData getCity(HttpServletRequest request){
        System.out.println("-------------------------------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String,Object>paramterNewMap=new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println(entry.getKey() + "-------------" + entry.getValue()[0]);
            paramterNewMap.put(entry.getKey(),entry.getValue()[0]);
        }
        Integer pageNum=Integer.parseInt(request.getParameter("page"));
        Integer pageSize=Integer.parseInt(request.getParameter("rows"));
        PageHelper.startPage(pageNum,pageSize);
//        List<Map<String, Object>> all = cityDao.getAll();
        List<Map<String, Object>> dynamicCitys = cityDao.getDynamicCitys(paramterNewMap);
        PageInfo info=new PageInfo(dynamicCitys);
        JqGridData data=new JqGridData();
        data.setRows(info.getList());
        data.setPage(info.getPageNum());
        data.setRecords(info.getTotal());
        data.setTotal(info.getPages());
        return data;
    }


    @RequestMapping(value = "/getErJi")
    @ResponseBody
    public JqGridData getErJi(HttpServletRequest request){
        Integer pageNum=Integer.parseInt(request.getParameter("page"));
        Integer pageSize=Integer.parseInt(request.getParameter("rows"));
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> all = cityDao.getErJi();
        PageInfo info=new PageInfo(all);
        JqGridData data=new JqGridData();
        data.setRows(info.getList());
        data.setPage(info.getPageNum());
        data.setRecords(info.getTotal());
        data.setTotal(info.getPages());
        return data;
    }


    /**
     * 导入数据到数据库
     */
    @RequestMapping(value = "/getImportExcel")
    public void getImportExcel(){

    }


    /**这里是全部数据，可以根据具体需求导出
     * 数据为world数据库中city表
     * 个别自己新增的字段
     * 导出数据到excel
     */
    @RequestMapping(value = "/getExportExcel")
    public void getExportExcel(HttpServletResponse response){
        try {
            List<String> titles = Arrays.asList(
                    "编号",
                    "名称",
                    "城市编码",
                    "区域",
                    "人口",
                    "状态",
                    "性别",
                    "生日",
                    "创建时间"
            );
            List<Map<String, Object>> listData = cityDao.getExportSqlList();
            response.setContentType("text/html; charset=utf-8");
            response.setContentType("application/octet-stream");
            //xxxx列表为文件的列表
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("xxxx列表.xls".getBytes("GB2312"), "ISO-8859-1"));
            ExcelUtils.exportExcelDataInfo(listData,titles,response.getOutputStream(),"xxxx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel存储到数据库中
     */
    @RequestMapping(value = "/excelToSql")
    @ResponseBody
    public void excelToSql(MultipartFile excelFile) throws IOException {
        System.out.println("-----------------------------------------------");
        try {
            Map<String, Object> map = ExcelUtils.importExcelToSql(excelFile.getInputStream());
            List<String> titles= (List<String>) map.get("excelTitles");
            List<List<String>>mapList= (List<List<String>>) map.get("excelList");
            System.out.println("---------------读出excel数据------------------");
            mapList.forEach(t-> System.out.println(t));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
