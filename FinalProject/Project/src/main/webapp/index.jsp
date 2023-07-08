<%@ page import="by.itclass.constants.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My News</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-grid.css">
	<link rel="stylesheet" href="css/bootstrap-reboot.css">
	<link rel="stylesheet" href="css/bootstrap-utilities.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="<c:url value="<%= AppConstant.NEWS_INDEX_CONT %>"/>">Get list form</a>
<%--<c:if test="${not empty newsList}">--%>
	<c:import url="add/menu.jsp"/>


		<div class="row">
			<div class="col-lg-8 m_corr">
				<div class="main_news">
					<img src="image/${newsListRating.get(0).getImage().getName()}" class="img-fluid" style="float:left; margin-right: 10px;">
					<h2>${newsListRating.get(0).getTitle()}</h2>
					<p>${newsListRating.get(0).getText()}

						<a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${newsListRating.get(0).getId()}">читать далее...</a>
					</p>
					<p> &nbsp;
						<span class="right_date">${newsListRating.get(0).getDate()}</span>
					</p>
				</div>
			</div>

			<div class="col-lg-4 m_corr">
				<iframe frameborder="0" height="131" marginheight="0" marginwidth="0" scrolling="no" src="https://admin.myfin.by/outer/informer/brest/full" width="100%"></iframe>

				<hr>

				<h3>Топ новости за неделю:</h3>
				<!-- сделать вывод 3 лучших новостей за последнюю неделю - JAVA -->

				<div class="top_news">
					<h4> &#128077; ${newsListRating.get(1).getRating()} - ${newsListRating.get(1).getTitle()}</h4>
					<p>${newsListRating.get(1).getText()} <a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${newsListRating.get(1).getId()}">читать далее...</a>
				</p>  
				<!-- В этой новости в превью выводится до 150 символов - JAVA -->
				</div>

				<div class="top_news">
					<h4>  &#128077;${newsListRating.get(2).getRating()} - ${newsListRating.get(2).getTitle()}</h4>
					<p>${newsListRating.get(2).getText()} <a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${newsListRating.get(2).getId()}">читать далее...</a>
					</p>
				</div>

				<div class="top_news">
					<h4> &#128077; 	${newsListRating.get(3).getRating()} - ${newsListRating.get(3).getTitle()}</h4>
					<p>${newsListRating.get(3).getText()} <a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${newsListRating.get(3).getId()}">читать далее...</a>
					</p>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-md-12 m_corr">
				<div class="regular_news">
					<div class="row">
						<div class="col-5">
							<%--<img src="image/img1.jpg" class="img-fluid">--%>
						</div>
						<div class="col-12">
							<table class="table table-bordered">
								<c:forEach var="news" items="${newsList}">
									<tr>
										<td><img src="image/${news.image.name}" class="img-fluid" style="max-height: 100px;"></td>
										<td> <h5>${news.title} </h5>
										${news.text}
										<a href="<c:url value="<%= AppConstant.NEWS_VIEW_CONT %>"/>?id=${news.id}" >читать далее...</a>			 &nbsp;
										<span class="right_date">${news.getDate()}</span></td>>

									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


	<c:import url="add/footer.jsp"/>

	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.js"></script>
<%--</c:if>--%>
</body>
</html>