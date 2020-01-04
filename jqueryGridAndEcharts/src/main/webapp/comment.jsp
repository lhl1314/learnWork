<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- jqGrid组件基础样式包-必要 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jqGrid/jqgrid/css/ui.jqgrid.css" />

<!-- jqGrid主题包-非必要 -->
<!--在jqgrid/css/css这个目录下还有其他的主题包，可以尝试更换看效果 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jqGrid/jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css" />

<!-- jquery插件包-必要 -->
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jqGrid/js/jquery-3.3.1.min.js"></script>

<!-- jqGrid插件包-必要 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jqGrid/jqgrid/js/jquery.jqGrid.src.js"></script>

<!-- jqGrid插件的多语言包-非必要 -->
<!-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jqGrid/jqgrid/js/i18n/grid.locale-cn.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/jqGrid/js/moment.min.js"></script>


<!--datepicker日期控件的引入-->
<link rel="stylesheet" href="https://blog.mn886.net/jqGrid/javascript/datepicker/jquery.ui.datepicker.css" />
<script type="text/javascript" src="https://blog.mn886.net/jqGrid/javascript/datepicker/jquery.ui.core.js"></script>
<script type="text/javascript" src="https://blog.mn886.net/jqGrid/javascript/datepicker/jquery.ui.widget.js"></script>
<script type="text/javascript" src="https://blog.mn886.net/jqGrid/javascript/datepicker/jquery.ui.datepicker.js"></script>