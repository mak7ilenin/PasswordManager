
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="listAccounts">Менеджер паролей</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="addAccountBox">Добавить аккаунт</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="listAccounts">Список аккаунтов</a>
        </li>
        
        
        
            
      </ul>
      <ul class="navbar-nav  mb-2 mb-lg-0">
        <c:if test="${authUser eq null}">
            <li class="nav-item">
              <a class="nav-link" href="showLogin">Войти</a>
            </li>
        </c:if>
        <c:if test="${authUser ne null}">
            <li class="nav-item">
              <a class="nav-link" href="logout">Выйти</a>
            </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
