<%@page import="com.smt.webapp.util.HttpSessionHelper"%>
<%@page import="com.smt.common.StageVariale"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title><%=StageVariale.title%></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="<%=StageVariale.description%>">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/css/member_center.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/01stageDialog/skins/idialog.css" />
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/jquery.validate.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/stageDate/WdatePicker.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/01stageDialog/jquery.artDialog.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/01stageDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript">
	
	
		function CloseWin() 
		{ 
			art.dialog.close(); 
		 
		} 
  </script>
  </head>
  <body>
	<form id="taskForm" name="taskForm" action="member/task!suTask.htm" method="post">
	<input type="hidden" name="task.id" value="${task.id }"/> 
	<table width="520" border="0" align="center" cellpadding="0" cellspacing="0" >
    <tr>
        <td height="360" bgcolor="#FFFFFF">
        
        <!-- 弹出框内容 开始 -->
        <div class="add_table_box">
        <table width="450" border="0" align="center" cellpadding="0" cellspacing="0" class="add__Popup_title">
            <tr>
                <td height="18">委托需求</td>
            </tr>
        </table>
        <table width="460" border="0" align="center" cellpadding="0" cellspacing="0" class="add__Popup_con">
		    <tr>
		        <td width="61" align="right"><b>标题</b></td>
		        <td>${task.title }</td>
		    </tr>

			<tr>
		        <td width="61" align="right"><b>开始时间</b></td>
		        <td>${task.startDate }</td>
		    </tr>
		    
		    <tr>
		        <td width="61" align="right"><b>结束时间</b></td>
		        <td>${task.endDate }</td>
		    </tr>
		    
		    <tr>
		        <td width="61" align="right"><b>活动城市</b></td>
		        <td>${task.provice } ${task.city } ${task.place }</td>
		    </tr>
		    
		    <tr>
		        <td width="61" align="right"><b>活动预算</b></td>
		        <td>${task.price }</td>
		    </tr>
		    
		    <tr>
		        <td width="61" align="right"><b>活动人数</b></td>
		        <td>${task.personCount }</td>
		    </tr>
		    
		    <tr>
		        <td width="61" align="right"><b>联系方式</b></td>
		        <td>${task.mobile }</td>
		    </tr>

		    <tr>
		        <td align="right"><b>内容</b></td>
		        <td >${task.content }</td>
		        </tr>
		     <tr>
			<tr>
		        <td align="right"><b>创建时间</b></td>
		        <td >${task.creatTime }</td>
		        </tr>
		     <tr>  
		    <tr>
		        <td colspan="2" align="center">

		        <a href="javascript:CloseWin();"><img src="<%=request.getContextPath() %>/theme/images/nav_qx.jpg" width="60" height="28" /></a>
		        
		        </td>
		        </tr>
		</table>
        

        </div>
         <!-- 弹出框内容 结束 -->
        
        </td>
    </tr>
</table>
	
 
        </form>
  </body>
</html>