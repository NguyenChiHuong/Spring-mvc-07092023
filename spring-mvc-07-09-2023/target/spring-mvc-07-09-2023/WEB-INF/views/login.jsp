<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/taglib/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-body">
				<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">Username or password incorrect</div>
				</c:if>
				<c:if test="${param.accessDenied != null}">
					<div class="alert alert-danger">Not authorize incorrect</div>
				</c:if>
				<form action="j_spring_security_check" id="formLogin" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="userName" name="j_username" placeholder="Tên đăng nhập">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">
					</div>
					<div class="form-group">
						<button type="submit" class="btn float-right login_btn">Đăng nhập</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>