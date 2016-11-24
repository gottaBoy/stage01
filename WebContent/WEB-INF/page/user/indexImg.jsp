<%@page import="com.smt.webapp.util.HttpSessionHelper"%>
<%@page import="com.smt.common.StageVariale"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title><%=StageVariale.title%></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="<%=StageVariale.description%>">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/css/global_layout.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/theme/css/member_center.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/01stageDialog/skins/idialog.css" />
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/jquery.validate.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/address.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/stageDate/WdatePicker.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/01stageDialog/jquery.artDialog.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/01stageDialog/plugins/iframeTools.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/theme/js/swfobject.js"></script>
	<style type="text/css" media="screen">
		html, body { height:100%; background-color: #ffffff;}
		#flashContent { width:100%; height:100%; }
	</style>

		<script type="text/javascript">
			function handleImgLoadError(oEvent)
		   	{
		       oEvent.srcElement.src='<%=request.getContextPath()%>/theme/images/no_img_tj.jpg';
			}
	
			var flashvars = {
			  "uploadServerUrl":"./userinfo!upLoadIndexHeadImage.htm"
			};
			var params = {
				wmode:"opaque",
				width:800,
				height:600,
				type:"application/x-shockwave-flash"	
			};
			var attributes = {
			};

			swfobject.embedSWF("../theme/flex/indexHeadImage.swf", "altContent", "600", "500", "9.0.0", "expressInstall.swf", flashvars, params, attributes);

			function avatar_success()
			{
			
				//这里是头像保存后的成功提示，你可以刷新当前页面，也可以做些其他操作
				alert("图片保存成功"); 
				window.location.reload();
			}
</script>
  </head>
  <body>
  <jsp:include page="../public/head2.jsp">
  <jsp:param value="no" name="titleType"/>
  </jsp:include>
  <!-- 主内容开始 -->
<table width="980" border="0" align="center" cellpadding="0" cellspacing="0" class="layout_table">
    <tr>
        <td width="146" valign="top" class="left_menubj">
        <!-- 会员 左导航 开始 
        -->
        <s:action name="menuHyzx" executeResult="true">
        	<s:param name="menuFlag" value="1"></s:param>
        </s:action>
       <!-- 会员 左导航 结束 -->
</td>
        <td height="720">&nbsp;</td>
        <td width="810" align="left" valign="top">
        <div class="your_position
">您的位置：<a href="#">首页</a> > <a href="#">个人信息</a></div>

<div class="right_tab">
<ul>
<li><a href="userinfo.htm?sign=1">基础信息</a></li>
<li><a href="userinfo.htm?sign=2"><%if(1==HttpSessionHelper.getIsOrg(request)){%>公司介绍<%}else{ %>个人介绍<%} %></a></li>
<li><a href="userinfo.htm?sign=3">职业技能</a></li>
<li><a href="userinfo.htm?sign=7">成长经历</a></li>
<li><a href="userinfo.htm?sign=4">实名认证</a></li>
<li><a href="userinfo.htm?sign=5">头像修改</a></li>
<li><a class="change" href="userinfo.htm?sign=6">首页照片<img src="<%=request.getContextPath() %>/theme/images2/tu_xb.png" /></a></li>
</ul>
</div>
<span class="clear"></span>
<div id="TabTab03Con5">
<div style=" float:left; margin-top:15px;">
<div id="altContent"></div>
</div>
		 <div id="avatar_priview" style=" margin-top:15px;float: right; margin-right:10px;"><img id="aa" width="182" height="167" src="/stageUpload/indexHeadImage/<s:property value="#request.userId"/>.png?id=<%=new Date() %>" 
				onerror="handleImgLoadError(event);" /></div>        

</div>
</td>
</tr>
</table>

<!-- 主内容结束 -->
<jsp:include page="../public/bottom2.jsp"></jsp:include>

  </body>
</html>