<%@ page import="by.itclass.constants.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Cabinet</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-grid.css">
	<link rel="stylesheet" href="css/bootstrap-reboot.css">
	<link rel="stylesheet" href="css/bootstrap-utilities.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<c:import url="add/menu.jsp"/>

	<div class="container" style="height: 1000px;">
		<div class="row">
			<div class="col-md-12 m_corr">
				<h2>Добро пожаловать ${user.login}</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="main_news">
					<div class="row">
						<div class="col-3">
							<img src="image/${user.image.name}" class="img-fluid">
							<form method="post" action="<c:url value="<%= AppConstant.USER_IMAGE_CONT %>"/>" enctype="multipart/form-data">
								<input type="file" name="<%= AppConstant.IMAGE_FILE_PARAMETER %>" class="form-control">
								<input type="submit" class="btn btn-success" value="Загрузить">
							</form>
						</div>
						<div class="col-9">
							<h3>Личные данные: ${user.login}</h3><hr>
							<p>Текущая почта: ${user.email}</p>
							<form>
								<input type="text" class="form-control" placeholder="Новое имя"><br>
								<input type="email" class="form-control" placeholder="Новая почта"><br>
								<input type="password" class="form-control" placeholder="Новый пароль"><br>
								<input type="password" class="form-control" placeholder="Подтвердить новый пароль"><br>
								<input type="submit" class="btn btn-success" value="Поменять">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<c:import url="add/footer.jsp"/>

	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.js"></script>
</body>
</html>