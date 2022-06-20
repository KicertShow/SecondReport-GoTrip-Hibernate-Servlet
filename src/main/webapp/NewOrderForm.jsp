<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js" lang="">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>後台管理</title>
<meta name="description" content="dashboard">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- style sheet (以下三個style sheet請記得link進來) --%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<style>
h2 {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<%-- side Bar (side bar請記得include進來) --%>
	<%@include file="jspf/sidebar.jspf"%>

	<%-- main--%>
	<main id="right-panel" class="right-panel">



		<%-- 網站內容請放main裡面--%>

		<div class="container col-md-10 mt-5">
			<div class="card">
				<div class="card-body">
					<c:if test="${order != null}">
						<form action="OrderServletOS?name=update" method="post">
					</c:if>
					<c:if test="${order == null}">
						<form action="OrderServletOS?name=insert" method="post">
					</c:if>

					<caption>
						<h2>
							<c:if test="${order != null}">
                                    編輯訂單
                                </c:if>
							<c:if test="${order == null}">
                                    新增訂單
                                </c:if>
						</h2>
					</caption>

					<c:if test="${order != null}">
						<input type="hidden" name="orderId"
							value="<c:out value='${order.orderId}' />" />
						<input type="hidden" name="orderDatePlus"
							value="<c:out value='${order.orderDatePlus}' />" />	
					</c:if>

					<div class="mb-4 row">
						<fieldset class="form-group col-md-4">
							<label>訂單類別:</label><select class="form-select"
								name="orderCategory">
								<option value="訂房"
									${order.orderCategory == "訂房" ? 'selected="selected"' : ""}>訂房</option>
								<option value="訂票"
									${order.orderCategory == "訂票" ? 'selected="selected"' : ""}>訂票</option>
							</select>
						</fieldset>
						<fieldset class="form-group col-md-4">
							<label>會員編號:</label> <input type="text"
								value="<c:out value='${order.userNo}' />" class="form-control"
								name="userNo" id="userno">
						</fieldset>
					</div>
					<div class="mb-4 row">
						<fieldset class="form-group col-md-4">
							<label>總價:</label> <input type="text"
								value="<c:out value='${order.orderTotal}'/>"
								class="form-control" name="orderTotal" id="total">
						</fieldset>
						<fieldset class="form-group col-md-4">
							<label>折扣金額:</label> <input type="text"
								value="<c:out value='${order.orderDiscount}'/>"
								class="form-control" name="orderDiscount" readOnly="true"
								id="discount">
						</fieldset>
						<fieldset class="form-group col-md-4">
							<label>折扣類別:</label><select class="form-select" name="couponId"
								id="couponId">
								<option value="1"
									${order.couponId == 1 ? 'selected="selected"' : ""}>滿千送百</option>
								<option value="2"
									${order.couponId == 2 ? 'selected="selected"' : ""}>打八折</option>
							</select>
						</fieldset>
					</div>
					<div class="mb-2 row">

						<fieldset class="form-group col-md-4">
							<label>訂單狀態:</label><select class="form-select"
								name="orderStatus">
								<option value="1"
									${order.orderStatus == 1 ? 'selected="selected"' : ""}>成功</option>
								<option value="2"
									${order.orderStatus == 2 ? 'selected="selected"' : ""}>不成立</option>
							</select>
						</fieldset>

						<fieldset class="form-group col-md-4">
							<label>付款狀態:</label><select class="form-select"
								name="orderPayStatus">
								<option value="1"
									${order.orderPayStatus == 1 ? 'selected="selected"' : ""}>尚未付款</option>
								<option value="2"
									${order.orderPayStatus == 2 ? 'selected="selected"' : ""}>已付款</option>
							</select>
						</fieldset>

					</div>
					<div class="row" style="text-align: center">
						<fieldset class="form-group mt-4">
							<a href="OrderServletOS" onclick="history.back()"
								class="btn btn-secondary">上一頁 </a>
							<button type="submit" class="btn btn-primary">送出</button>
						</fieldset>
					</div>
					<%-- 	<input type="button" value="回上一頁"
								class="btn btn-secondary" /> --%>

					</form>
				</div>
			</div>
		</div>
	</main>

	<%-- scripts --%>
	<script src="js/vendor/jquery-2.1.4.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
	<script>
	jQuery(document).ready(function($){
			
		var  coupon = $('#couponId').val() ;
		console.log(coupon);
		
		$('#total').on("blur",function(){
			var division;
			var	twenty
			if($('#couponId').val() == 1){
				 division  = parseInt($(this).val()/1000);
				$('#discount').val(division*100);
				
			}else if($('#couponId').val() == 2){
				twenty	= Math.round($(this).val()*0.2);
				$('#discount').val(twenty);
			}
			
		})

		$('#couponId').on("change",function(){
			
				
				if($('#couponId').val() == 1){
					division  = parseInt($("input[name=orderTotal]").val()/1000);
					$('#discount').val(division*100);
					
				}else if($('#couponId').val() == 2){
				 twenty = Math.round($("input[name=orderTotal]").val()*0.2);
					$('#discount').val(twenty);
				}
				
			
			})
			var total_input = document.getElementById("total");

			total_input.addEventListener("keydown", function(e){			
			
				 if( (e.which >= 48 && e.which <= 57) || e.which == 8 ){ // 8 是刪除鍵
					  
				  }else{
				     e.preventDefault(); // 停止預設行為(在欄位上出現所打的文字)
				  }
				  
				 
				});
				
			var userno_input = document.getElementById("userno");

			userno_input.addEventListener("keydown", function(e){			
			
				 if( (e.which >= 48 && e.which <= 57) || e.which == 8 ){ // 8 是刪除鍵
					  
				  }else{
				     e.preventDefault(); // 停止預設行為(在欄位上出現所打的文字)
				  }
				  
				 
				});
	
	});
	</script>

	<%--
	if($('#couponId').val()== "1"){
			if($('#'))
			$('#Total')
		}
	 --%>

</body>

</html>