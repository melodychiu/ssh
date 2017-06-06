<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	getData(1);
	//首页
	$("#firstPage").click(function(){
		clearData();		
		getData(1);
		return false;		
	});
	//上一页
	$("#prePage").click(function(){
		clearData();		
		//获得当前页数
		var pageIndex = $("#pageIndex").text();
		pageIndex=parseInt(pageIndex)-1;
		if(pageIndex<=0)
			pageIndex=1;
		getData(pageIndex);
		return false;
	});	
	//下一页
	$("#nextPage").click(function(){
		clearData();
		var pageIndex = $("#pageIndex").text();	
		var totalPage = $("#totalPage").text();
		pageIndex=parseInt(pageIndex)+1;
		if(pageIndex>=totalPage)
			pageIndex=totalPage;
		getData(pageIndex);
		return false;
	});	
	//最后一页
	$("#lastPage").click(function(){
		clearData();		
		var totalPage = $("#totalPage").text();
		getData(totalPage);
		return false;
	});
	$("#search").click(function(){
		clearData();
		$.post("searchStandard",
			{"condition":$("#content").val()},
			function(data){
				fillData(data);
			},
			"json");
	});
	//根据页面获取数据
	function getData(pageIndex){
		$.ajax({
			url:"searchStandard",
			type:"post",
			data:{"pageSupport.pageIndex":pageIndex},
			dataType:"json",
			beforeSend:function(){				 
				// 正在加载数据
				$("#load").show();   
			},
			success:function(data){
				$("#load").hide();
				fillData(data);
			},
			error:function(){
				alert("数据读取失败");
			}
		});
	};
	//填充数据
	function fillData(data){
		 //转换数据
		var data = eval("("+data+")"); 
		// 分页的数据
	    var page = data.pageSupport;					  
		//设置分页的数据
		$("#totalCount").text(data.totalCount);
		$("#pageIndex").text(page.pageIndex);
		$("#totalPage").text(page.totalPage);				
		$("#table tr:gt(1):eq(0)").remove();						           
		$.each(data.list,function(index,value){
			// 取出返回的json数据
	       	var id = value.id;
     	   	var stdnum = value.stdnum;
          	var zhname=value.zhname;
     	   	var version = value.version;
          	var releasedate = value.releasedate==null?"":formatDate(value.releasedate);	  
     	   	var impldate = value.impldate==null?"":formatDate(value.impldate);	 
     	   	var packagepath=value.packagepath;
	     	// 将返回的数据追加到表格中
	     	if(index%2>0){
	     		var col_1="<tr align='center' style='background:SkyBlue'>";
	     	}
	     	else{
	     		var col_1="<tr align='center' style='background:PowderBlue'>";
	     	}
	       	var col_2="<td><input type='checkbox' name='ids' value="+id+"/></td>";
	       	var col_3="<td>"+stdnum+"</td>";
	       	var col_4="<td>"+zhname+"</td>";
	       	var col_5="<td>"+version+"</td>";
	       	var col_7="<td>"+releasedate+"</td>";
	       	var col_8="<td>"+impldate+"</td>";
	       	var update="<a class='update' id="+id+" href='javascript:void(0)'>修改</a>";
	       	var download="<a href='download?filename="+packagepath+"'>下载</a>";
	       	var col_9="<td>"+download+"&nbsp;&nbsp;"+update+"</td>";
	       	var col_10="</tr>";
	       	var newRow = col_1+col_2+col_3+col_4+col_5+col_7+col_8+col_9+col_10;			       
	       	$("#table tr:last").after(newRow);  	      			   	  
		});	
 		$(".update").click(function(){
 			window.location.href="modifyStandard?id="+$(this).attr("id");
		});
	};
	//清除原有数据
	function clearData(){
		// 清除原来增加的TR,把大于第一行的数据都给清除
		$("#table tr:gt(1)").remove();		
	};

	$("#add").click(function(){
		window.location.href="addStandard";
	});
	var ids=[];
	var isChecked=false;
	$("#selectAll").click(function(){
		isChecked=!isChecked;
	     $("input[name='ids']").attr("checked",isChecked);
	});
	$("#del").click(function(){
		ids=[];
		$("[name='ids']:checked").each(function(){
			ids.push($(this).val());
		});
		if(ids.length==0){
			alert("请选择删除项！");
			return;
		};
		if(confirm("确定要删除"+ids.length+"条标准？")){
			var i=0;
			$.ajaxSettings.async = false;
			for(id in ids){
		        $.post("deleteStandard",{"standard.id":parseInt(ids[id])},function (data){  
		        	var data = eval("("+data+")"); 
		        	if(data.result == "true"){
						i++;
		        	}
		        });
			};
			alert("共删除"+i+"条标准");
			location.reload();
		};
	});
});
</script>
</head>
<body>
	<h3 align="center">标准信息列表</h3>
	<table id="table" border="1" cellpadding="0" cellspacing="1" width="100%">
		<tr height="30px">
			<td colspan="7" align="right">
				<input type="text" id="content" name="content">
				<input type="button" id="search" value="提交检索">
				<input type="button" id="add" value="新增标准">
				<input type="button" id="del" value="删除标准">					
			</td>
		</tr>
		<tr height="30px" style="background:RoyalBlue">
			<td width="5%" align="center" id="selectAll">
				<input type="checkbox">
			</td>
			<td width="20%" align="center">标准号</td>
			<td width="30%" align="center">中文名称</td>
			<td width="15%" align="center">版本</td>
			<td width="10%" align="center">发布日期</td>
			<td width="10%" align="center">实施日期</td>
			<td width="10%" align="center">操作</td>
		</tr>	
  		<tr id="load">
  			<td colspan="7">
  				<div id="" align="center">
  					<img src="image/load.gif" width="50" height="50"/>
  					正在加载数据,请稍后...
  				</div>
  			</td>
  		</tr>		
	</table>
	<div align="center"> 
   		<div> 
	                     共<label id="totalCount"></label>条数据,第<label id="pageIndex"></label>页/共<label id="totalPage">0</label>页 
	        &nbsp;&nbsp;
	        <a id="firstPage" href="#">首页</a>
	        <a id="prePage" href="#">上一页</a>
	        <a id="nextPage" href="#">下一页</a>
	        <a id="lastPage" href="#">末页</a> 
   		</div> 
	</div>	
	<span id="msg"></span>
</body>
</html>