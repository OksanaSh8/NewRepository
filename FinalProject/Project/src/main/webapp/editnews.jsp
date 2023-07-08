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
				<h2>Редактировать новость</h2>
				<c:import url="add/message.jsp"/>
			</div>
		</div>


		<div class="row">
			<div class="col-md-12">
				<div class="main_news">
					<%--<input type="text" value=${news.getTitle()} name="<%= AppConstant.TITLE_PARAMETER %>"  class="form-control" placeholder="Введите заголовок"><br>
					<input type="text" value=${news.getTitle()}  class="form-control" placeholder="Введите заголовок"><br>
					<input name="<%= AppConstant.TITLE_PARAMETER %>" value="${news.getTitle()}" /><br><br>--%>

					<form method="post" action="<c:url value="<%= AppConstant.FILE_UPLOAD_CONT %>"/>" enctype="multipart/form-data">
						<input type="text" value="${news.getTitle()}" name="<%= AppConstant.TITLE_PARAMETER %>"  class="form-control" placeholder="Введите заголовок"><br>
						<input type="text" value="${news.text}" name="<%= AppConstant.TEXT_PARAMETER %>" class="form-control" placeholder="Введите текст новости"><br>
						<input type="file" class="form-control"><br>
						<%--<input type="hidden" value="${image}" name="<%= AppConstant.IMAGE_PARAMETER %>" class="form-control" >
						<input type="hidden" value="${news.image.context}" name="<%= AppConstant.IMAGE_CONTEXT_PARAMETER %>" class="form-control" >--%>
						<input type="hidden" name="<%= AppConstant.ID_PARAMETER %>" value=${news.id}>
						<input type="hidden" name="<%= AppConstant.ACTION_PARAMETER %>" value="<%= AppConstant.EDIT_NEWS_ACTION %>">
						<input type="submit" class="btn btn-warning" value="Редактировать новость">
						<a href="mynews.jsp" class="btn btn-link">Назад</a>
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