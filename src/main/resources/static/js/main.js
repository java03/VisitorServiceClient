/**
表格排序图标 -------------------
*/

function setOrderIcon(tr, firstNoOrderFlag, prjUrl,async,callBackFun){
		//设定所有列头的点击事件
		tr.children().each(function(index){
		//除了第一个序号列
		if(index == 0 && firstNoOrderFlag){
			return;
		}
				
				var tdEle = $(this);
				//设定点击指针
				tdEle.prop("style","cursor:pointer;");
				
				//设定每个td的点击事件
				tdEle.click(function(event){
					
					viewOrderIcon(index, tr,prjUrl,async,callBackFun);
				});
			});
		}
		
/**
获取当前排序列位置和排序方式
*/
function getOrderColIndex(tr){
	//判断当前排序列位置
	var currIndex = -1;
	var tds = tr.children();
	//当前排序列的class内容
	var classText = "";
	tds.each(function(i){
		var span = $(this).children();
		var classCont = span.prop("class");
		if(classCont != undefined){
			currIndex = i;
			classText = classCont;
		}
	});
	
	return new Array(currIndex, classText);
}
		
function viewOrderIcon(index, tr, prjUrl,async,callBackFun){
	var orderInfoArray = getOrderColIndex(tr);
	var currIndex = orderInfoArray[0];
	var classText = orderInfoArray[1];
	
	//移除span
	var tds = tr.children();
	tds.eq(currIndex).children().remove();
	
	//设置点击列的span
	//判断当前点击列是否原有的排序列
	if(index == currIndex){
		if(classText == "glyphicon glyphicon-chevron-down"){
			classText="glyphicon glyphicon-chevron-up";
		}else{
			classText="glyphicon glyphicon-chevron-down";
		}
	}else{
			classText="glyphicon glyphicon-chevron-up";
	}
	tds.eq(index).append("<span class='"+classText+"'></span>");
	
	//保存排序列的位置和排序方式
	var orderFlag = "asc";
	if(classText == "glyphicon glyphicon-chevron-down"){
		orderFlag = "desc";
	}
	
	var url = prjUrl+"?orderIndex="+index+"&flag="+orderFlag;
	//判断是否有查询条件
	if($("#condition").val() != ''){
		url += "&condition="+$("#condition").val()
	}
	//发送请求
	if(async){
		eval(callBackFun+"('"+url+"')");
	}else{
		location.href=url;	
	}
	
}

/**
设置排序图标的回显
*/
function viewOrderCallBackView(orderIndex, orderFlag, tr){
	var classText = "glyphicon glyphicon-chevron-down";
	if(orderFlag == "asc"){
		classText = "glyphicon glyphicon-chevron-up";
	}
	//移除原有span标签，添加span到点击的列上
	//判断当前排序列位置
	var orderInfoArray = getOrderColIndex(tr);
	var currIndex = orderInfoArray[0];
	//移除span
	var tds = tr.children();
	tds.eq(currIndex).children().remove();
	
	tds.eq(orderIndex).append("<span class='"+classText+"'></span>");
}

/**
分页跳转
*/
function jumpIndexPage(prjUrl, pageNum, tr, callBackFun){
	tr = $("#"+tr);
	var pageCount = $("#pageCount").val();
	var orderInfoArray = getOrderColIndex(tr);
	var currIndex = orderInfoArray[0];
	var classText = orderInfoArray[1];
	var orderFlag = "asc";
	if(classText == "glyphicon glyphicon-chevron-down"){
		orderFlag = "desc";
	}
	var url = prjUrl+"?orderIndex="+currIndex+"&flag="+orderFlag+"&pageNum="+pageNum+"&pageCount="+pageCount;
	//判断是否有查询条件
	if($("#condition").val() != ''){
		url += "&condition="+$("#condition").val()
	}
	//判断是否有Ajax的回调函数
	if(callBackFun){
		//执行回调函数
		eval(callBackFun+"("+url+")");
	}else{
		//发送同步请求
		location.href=url;
	}
}
/**
获取年月日
*/
function getNowYMD(){
	var nowDate = new Date();
 	var year = nowDate.getFullYear();
 	var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1):nowDate.getMonth() + 1;
 	var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
 	var dateStr = year + "-" + month + "-" + day;
	return dateStr;
}