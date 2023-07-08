<%@ page import="by.itclass.constants.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Add News</title>
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
				<h2>Добавить новость</h2>
				<c:import url="add/message.jsp"/>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="main_news">
					<form method="post" action="<c:url value="<%= AppConstant.FILE_UPLOAD_CONT %>"/>" enctype="multipart/form-data">
						<input type="text" name="<%= AppConstant.TITLE_PARAMETER %>" class="form-control" placeholder="Введите заголовок"><br>
						<textarea rows="10" name="<%= AppConstant.TEXT_PARAMETER %>" class="form-control" placeholder="Введите текст новости"></textarea><br>
						<input type="file" name="<%= AppConstant.IMAGE_FILE_PARAMETER %>" class="form-control"><br>
						<input type="hidden" name="<%= AppConstant.ACTION_PARAMETER %>" value="<%= AppConstant.ADD_NEWS_ACTION %>">
						<input type="submit" class="btn btn-success" value="Добавить новость">
					</form>
				</div>
			</div>
		</div>

	</div>

	<c:import url="add/footer.jsp"/>

	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.js"></script>
</body>
</html>