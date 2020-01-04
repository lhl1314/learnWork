<%--
  Created by IntelliJ IDEA.
  User: lhl
  Date: 2019/12/27
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="${pageContext.request.contextPath}/comment.jsp"/>
    <style>
        #search_list2 {
            display: none;
        }

        #myId {
            color: #1c94c4;
        }
    </style>
</head>
<body>
<div id="importExcel">
    <form action="/excelToSql" method="post" enctype="multipart/form-data" >
        <input type="file" id="excelFile" name="excelFile">
        <input type="submit">
    </form>
</div>
<form id="search_form">
    id:<input type="text" name="id">
    name:<input type="text" name="name">
    countryCode:<input type="text" name="countryCode">
    district:<input type="text" name="district">
    population:<input type="text" name="population">
</form>mytest1@139.196.125.230
<br>
<div>
    <button id="getRowData">获取选中行数据</button>
    <button id="delRowData">删除选中行</button>
    <button id="getNowPage">获取当前页</button>
    <button id="daoru">导入</button>
    <button id="daochu">导出</button>
    <button id="reload">重载表格</button>
    <button id="search">搜索</button>
    <button id="clear">清空</button>
    <br>
    <button id="getOneRow">获取选中的行的ID</button>
    <button id="hideNameColumn">隐藏name列</button>
    <button id="showNameColumn">显示name列</button>
</div>

<table id="list2"></table>
<div id="pager2"></div>
<script>
    /**导出
     * */
    $("#daochu").click(function () {
        location.href="/getExportExcel";
    });
    /**导入
     * */
    $("#daoru").click(function () {
        $("#importExcel form").submit();
    });

    $(function () {
        pageInit();
    });


    function pageInit() {
        jQuery("#list2").jqGrid(
            {
                url: '/getCity',
                datatype: "json",
                colNames: ['id', 'name', 'countryCode', 'district', 'population', 'status', 'sex', 'birthday', 'createTime','operation'],
                colModel: [
                    {name: 'id', align: 'center', index: 'id', classes: 'myId', sortable: true},
                    {name: 'name', align: 'center',sortable: false, index: 'name'},
                    {name: 'countryCode', align: 'center',sortable: false, index: 'countryCode'},
                    {name: 'district', align: 'center', sortable: false,index: 'district'},
                    {name: 'population', align: 'center',sortable: false, index: 'population'},
                    {name: 'status', index: 'status',sortable: false, align: 'center'},
                    {name: 'sex', index: 'sex', align: 'center', sortable: false},
                    {
                        name: 'birthday', index: 'birthday', sortable: false, align: 'center',
                        formatter: function (cellvalue, options, rowObject) {
                            // console.log("cellvalue=" + cellvalue);//单元格的数值
                            // console.log("options=" + JSON.stringify(options));//jqgrid这行配置的参数信息
                            // console.log("rowObject=" + JSON.stringify(rowObject));//一行的json数据
                            return (moment(rowObject.birthday).format("YYYY-MM-DD"));
                        }
                    },
                    {
                        name: 'createTime', index: 'createTime', sortable: false, width: 200, align: 'center',
                        formatter: function (cellvalue, options, rowObject) {
                            return (moment(rowObject.createTime).format("YYYY-MM-DD HH:mm:ss"));
                        }
                    },
                    {name: 'operate',align:'center',sortable: false,
                        formatter: function (cellvalue, options, rowObject) {
                            return '<a href="javascript:void(0);" style="color:#f60" οnclick="modify(\''+ rowObject.id+ '\');">查看</a>'+'&nbsp;&nbsp;<a href="javascript:void(0);" style="color:#f60" οnclick="modify(\''+ rowObject.id+ '\');">删除</a>';
                        }
                    }
                ],
                rowNum: 20,
                rowList: [10, 20, 30],
                pager: '#pager2',
                sortname: 'id',//初始化的时候排序字段
                mtype: "post",//提交的方式
                viewrecords: true,
                sortorder: "desc",//排序类型
                multiselect : true,//开启多选
                autowidth: true,
                shrinkToFit: true,
                height: 400,
                rownumbers: true,//自动计算编号
                caption: "JSON 实例",
                onSelectRow : function(ids) {
                    console.log(ids);//获取到的是这行的ID,然后再根据id获取其他的东西吧。
                            jQuery("#list2_2").jqGrid(
                                'setGridParam',
                                {
                                    url :"/getErJi",
                                    page : 2
                                }).trigger("reloadGrid");//必须要重载表格呀。
                    // $("#list2_2").trigger("reloadGrid");
                }
            });
        jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: false, add: false, del: false});
        /**
         * 搜索时候重载表格数据
         */
        jQuery("#search").click(function() {
            $("#list2").jqGrid('setGridParam','postData', {
                q : 1,
                param1 : "p1"
            });
            $("#list2").trigger("reloadGrid");
        });
    }

    /**
     * 获取选中行的数据  ----一行
     */
    $("#getRowData").click(function () {
        var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');
        if (id) {
            var ret = jQuery("#list2").jqGrid('getRowData', id);
            alert(JSON.stringify(ret))
        } else {
            alert("Please select row");
        }
    });

    /**
     * 多选框获取选中的ID   可以是一个或者多个
     */
    $("#getOneRow").click(function () {
        var s;
        s = jQuery("#list2").jqGrid('getGridParam', 'selarrrow');
        alert(s);
    })
    /**
     * 删除选中行
     * @type {jQuery|*}
     */
    $("#delRowData").click(function () {
        var id = jQuery("#list2").jqGrid('getGridParam', 'selrow');//获取选中的id
        if (id){
            var su=jQuery("#list2").jqGrid('delRowData', id);//true或者false
            if (su){
                alert("delete success")
            }else {
                alert("delete fail")
            }
        }else {
            alert("请选择一行数据！")
        }
    });


    /**
     * 获取当前页码
     */
    $("#getNowPage").click(function () {
        alert(jQuery('#list2').jqGrid('getGridParam','page'));
    })


    /**
     * 重载表格  自动回把当前页的页码传过去，
     */
    $("#reload").click(function () {
        $("#list2").jqGrid('setGridParam',{  // 重新加载数据
        }).trigger("reloadGrid");
    })


    /**
     * 搜索
     */
    $("#search").click(function () {
        var serialize = $("#search_form").serialize();
        $("#list2").jqGrid("setGridParam", {
            page:"1",
            postData: {
                "id":$("input[name='id']").val().trim(),
                "name":$("input[name='name']").val().trim(),
                "countryCode":$("input[name='countryCode']").val().trim(),
                "district":$("input[name='district']").val().trim(),
                "population":$("input[name='population']").val().trim()
            }//发送查询条件
        }).trigger("reloadGrid");//重新载入
    })

    /**
     * 清空form表单
     */
    $("#clear").click(function () {
        document.getElementById("search_form").reset();
    });


    /**
     * 隐藏name列
     */
    $("#hideNameColumn").click(function() {
        jQuery("#list2").jqGrid( 'hideCol', "name");
    });
    /**
     * 显示name列
     */
    $("#showNameColumn").click(function() {
        jQuery("#list2").jqGrid( 'showCol', "name");
    });

</script>
</body>
</html>
