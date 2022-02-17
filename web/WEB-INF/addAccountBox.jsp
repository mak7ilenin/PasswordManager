<%-- 
    Document   : addAccountBox
    Created on : Feb 10, 2022, 1:25:08 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="createAccountBox" method="POST" >
      <div class="card border-0 mb-3" style="width: 40em;">
        <h2 class="my-5 w-100 d-flex justify-content-center">Сохранить данные для адреса</h2>
        <a href="showUploadFile" class="my-1 w-100 d-flex justify-content-center">Загрузить изображение</a>
        <div class="form-group">
          <label for="name" class="form-label mt-4">Название</label>
          <input type="text" class="form-control" id="name" name="name" aria-describedby="" placeholder="" value="${name}">
          <small id="name" hidden class="form-text text-muted">Error</small>
        </div>
          <div class="form-group">
            <label for="picture" class="form-label mt-4">Url изображения</label>
            <select class="form-select"  id="picture" name="picture">
                <c:forEach var="pic" items="${pictures}">
                    <option value="${pic.id}" <c:if test="${pic.id eq picture}">selected</c:if>>${pic.description}</option>
                </c:forEach>
            </select>
          </div>
        <div class="form-group">
          <label for="url" class="form-label mt-4">URL страницы</label>
          <input type="text" class="form-control" id="url" name="url" aria-describedby="" placeholder="" value="${url}">
          <small id="url" hidden class="form-text text-muted">Error</small>
        </div>
        <div class="form-group">
          <label for="urlLogin" class="form-label mt-4">Логин</label>
          <input type="text" class="form-control" id="urlLogin" name="urlLogin" aria-describedby="" placeholder="" value="${urlLogin}">
          <small id="urlLogin" hidden class="form-text text-muted">Error</small>
        </div>
        <div class="form-group">
          <label for="urlPassword" class="form-label mt-4">Пароль</label>
          <input type="text" class="form-control" id="urlPassword" name="urlPassword" aria-describedby="" placeholder="" value="${urlPassword}">
          <small id="urlPassword" hidden class="form-text text-muted">Error</small>
        </div>
        <input class="btn btn-primary mt-5" type="submit" value="Запомнить">
      </div>
    </form>
</div>
       
