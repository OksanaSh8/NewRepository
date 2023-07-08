<%@ page import="by.itclass.constants.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Document</title>
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
				<h2>Мои новости</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="main_news" Новости за последнюю неделю>
					
					<table class="table table-bordered">
						<tr>
							<th>ID</th>
							<th>Заголовок</th>
							<th>Текст</th>
							<th>Дата</th>
							<th>Картинка</th>
							<th>Действие</th>
						</tr>

						<c:forEach var="news" items="${newsList}">
							<tr>
								<td>${news.id}</td>
								<td>${news.title}</td>
								<td>${news.text}</td>				 &nbsp;
								<td><span class="right_date">${news.getDate()}</span></td>>
								<td><img src="image/${news.image.name}" width="100px"></td>
								<td>
									<a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${news.id}" class="btn btn-success">Просмотреть</a><br><br>
									<a href="<c:url value="<%= AppConstant.NEWS_EDIT_CONT %>"/>?id=${news.id}" class="btn btn-warning">Редактировать</a><br><br>
									<a href="<c:url value="<%= AppConstant.NEWS_DELETE_CONT %>"/>?id=${news.id}" class="btn btn-danger">Удалить</a><br>

								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

	<c:import url="add/footer.jsp"/>

	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.js"></script>
</body>
</html>