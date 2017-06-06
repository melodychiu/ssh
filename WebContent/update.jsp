<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	getData();
	function getData(){
 		$.ajax({
			url:"getStandard",
			type:"post",
			data:{"standard.id":$("#id").val()},
			dataType:"json",
			success:function(data){
				fillData(data);
			},
			error:function(){
				alert("数据读取失败");
			}
		});	
	};
	function fillData(data){
		var data = eval("("+data+")");
 	   	$("#id").val(data.id);
 	   	$("#stdnum").val(data.stdnum);	
 	   	$("#zhname").val(data.zhname);
 	   	$("#version").val(data.version);
 	   	$("#keys").val(data.keys);
 	   	var releasedate = data.releasedate==null?"":formatDate(data.releasedate);	
 	   	$("#releasedate").val(releasedate);
	   	var impldate = data.impldate==null?"":formatDate(data.impldate);
	   	$("#impldate").val(impldate);
	   	$("#packagepath").val(data.packagepath);
	};
	$("#save").click(function(){	
		if($.trim($("#zhname").val())==""){
			$("#zhname").focus();
			alert("中文名称不能为空！");
			return false;	
		}
		if($.trim($("#version").val())==""){
			$("#version").focus();
			alert("版本不能为空！");
			return false;
		}	
		if($.trim($("#keys").val())==""){
			$("#keys").focus();
			alert("关键字不能为空！");
			return false;
		}
		if($.trim($("#version").val())==""){
			$("#version").focus();
			alert("版本不能为空！");
			return false;
		}
		if($.trim($("#releasedate").val())!=""){
			var dateval=$("#releasedate").val();
			if(!isDate(dateval)){
				$("#releasedate").focus();
				alert("发布日期格式错误！");
				return false;
			}
		}
		if($.trim($("#impldate").val())!=""){
			var dateval=$("#impldate").val();
			if(!isDate(dateval)){
				$("#impldate").focus();
				alert("实施日期格式错误！");
				return false;
			}
		}		
		$.ajaxFileUpload({
			url:"updateStandard",
			type:"post",
			data:{
				"standard.id":$("#id").val(),
				"standard.stdnum":$("#stdnum").val(),
				"standard.zhname":$("#zhname").val(),
				"standard.version":$("#version").val(),
				"standard.keys":$("#keys").val(),
				"standard.releasedate":$("#releasedate").val(),
				"standard.impldate":$("#impldate").val(),
				"standard.packagepath":$("#packagepath").val(),
			},
			secureuri:false,
			fileElementId:"pic",
            dataType:"json",
            success: function(data) 
            {
				var data = eval("("+data+")"); 
				if(data.result == "true"){
					alert("修改成功");
					window.location.href="listStandard";
				}else{
				 	alert("修改失败");
				}
            },
            error: function()
            {
                alert("数据连接失败！");
            }			
		});
	});
});
</script>
<title>修改</title>
</head>
<body>
	<div style="margin:0 auto; width:600px">
		<h3 align="center">修改标准信息</h3>
		<input type="hidden" name="id" id="id" value="${param.id}">
		<table align="center" border="1" cellpadding="0" cellspacing="1" width="100%">			
			<tr height="30px">
				<td width="200px" align="right">*标准号：</td>
				<td style="padding-left:5px">
				<input type="text" name="stdnum" id="stdnum" style="width:90%" readonly="readonly">
			</tr>
			<tr height="30px">
				<td align="right">*中文名称：</td>
				<td style="padding-left:5px">
					<input type="text" name="zhname" id="zhname" style="width:90%">
				</td>
			</tr>	
			<tr height="30px">
				<td align="right">*版本：</td>
				<td style="padding-left:5px">
					<input type="text" name="version" id="version" style="width:90%">
				</td>
			</tr>
			<tr height="30px">
				<td align="right">*关键字：</td>
				<td style="padding-left:5px">
					<input type="text" name="keys" id="keys" style="width:90%">
				</td>
			</tr>
			<tr height="30px">
				<td align="center">发布日期(yyyy-MM-dd)：</td>
				<td style="padding-left:5px">
					<input type="text" name="releasedate" id="releasedate" style="width:90%" onClick="WdatePicker()">
				</td>
			</tr>
			<tr height="30px">
				<td align="center">实施日期(yyyy-MM-dd)：</td>
				<td style="padding-left:5px">
					<input type="text" name="impldate" id="impldate" style="width:90%" onClick="WdatePicker()">
				</td>
			</tr>
			<tr height="30px">
				<td align="right">*附件：</td>
				<td style="padding-left:5px">
					<input type="file" name="pic" id="pic">
					<input type="hidden" name="packagepath" id="packagepath">
				</td>
			</tr>
			<tr height="30px">
				<td colspan="2" align="center">
					<input type="submit" id="save" value="保存">
					<input type="button" value="取消" onClick="window.history.back();">				
				</td>
			</tr>																				
		</table>
	</div>
</body>
</html>