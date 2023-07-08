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

<div class="container">
    <div class="row">
        <div class="col-lg-12 m_corr">
            <div class="main_news">
                <h2>Author: ${user.login}</h2>
                <table class="table table-bordered">
                    <tr>
                        <img src="image/${news.image.name}" class="img-fluid" style="float:left; margin-right: 10px;">
                        <h2>${news.title} </h2>
                    </tr>
                </table>
                <p>
                    ${news.text}
                </p>
                <br>
                <hr>
                <h2>
                    <button class="like_button" name="my" onclick="updateRating(${news.id}, 'upp')">&#128077;</button>
                    ${news.rating}
                    <button class="like_button" onclick="updateRating(${news.id}, 'down')">&#128078;</button>
                    <c:import url="add/message.jsp"/>
                </h2>
                <br><br>
                <a href="<c:url value="<%= AppConstant.NEWS_INDEX_CONT %>"/>?id=${news.id}" class="btn btn-warning">Назад</a><br><br>
                <p> &nbsp;
                    <span class="right_date">${news.date}</span>
                </p>
            </div>
        </div>
    </div>
    <form id="sendRatingForm" method="post" action="<c:url value="<%= AppConstant.NEWS_RATING_CONT %>"/>">

        <input type="hidden" name="<%= AppConstant.ID_PARAMETER %>">
        <input type="hidden" name="<%= AppConstant.ACTION_PARAMETER %>">
    </form>
</div>

<c:import url="add/footer.jsp"/>

<script src="js/main.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery-3.6.0.js"></script>
</body>
</html>