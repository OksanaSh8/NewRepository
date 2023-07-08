<%@ page import="by.itclass.constants.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">

      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/">Главная</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="onas.php">О нас</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Контакты</a>
        </li>
      </ul>

      <c:choose>
        <c:when test="${empty user}">
          <ul class="navbar-nav me-2 mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="auth.jsp">Авторизация</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="reg.jsp">Регистрация</a>
            </li>
          </ul>
        </c:when>
        <c:otherwise>
          <ul class="navbar-nav me-2 mb-2 mb-lg-0">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  ${user.login}
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="<c:url value="<%= AppConstant.CABINET_JSP %>"/>">Кабинет</a></li>
                <li><a class="dropdown-item" href="<c:url value="<%= AppConstant.ADD_NEWS_JSP %>"/>">Добавить новость</a></li>
                <li><a class="dropdown-item" href="<c:url value="<%= AppConstant.NEWS_HOME_LIST_CONT %>"/>">Мои новости</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value="<%= AppConstant.USER_LOGOUT_CONT %>"/>">Выход</a>
            </li>
          </ul>
        </c:otherwise>
      </c:choose>

    </div>
  </div>
</nav>