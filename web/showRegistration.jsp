<%-- 
    Document   : showAccount
    Created on : Feb 17, 2022, 8:34:58 AM
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="my-5 w-100 d-flex justify-content-center">Регистрация нового пользователя</h2>
<div class="w-100 d-flex justify-content-center">
    <form action="registration" method="POST" >
      <div class="card border-0 mb-3" style="width: 70em;">
        <div class="row gx-5">
            <div class="form-group col">
              <label for="firstName" class="form-label mt-4">Имя</label>
              <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="" placeholder="" value="${firstName}">
              <small id="firstName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group col">
              <label for="lastName" class="form-label mt-4">Фаиилия</label>
              <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="" placeholder="" value="${lastName}">
              <small id="lastName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group col">
              <label for="phone" class="form-label mt-4">Телефон</label>
              <input type="text" class="form-control" id="phone" name="phone" aria-describedby="" placeholder="" value="${phone}">
              <small id="phone" hidden class="form-text text-muted">Error</small>
            </div>
        </div>
        <div class="row gx-5">
            <div class="form-group col">
              <label for="login" class="form-label mt-4">Логин</label>
              <input type="text" class="form-control" id="login" name="login" aria-describedby="" placeholder="" value="${login}">
              <small id="login" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group col">
              <label for="password1" class="form-label mt-4">Пароль</label>
              <input type="text" class="form-control" id="password1" name="password1" aria-describedby="" placeholder="" value="">
              <small id="password1" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group col">
              <label for="password2" class="form-label mt-4">Повторите пароль</label>
              <input type="text" class="form-control" id="password2" name="password2" aria-describedby="" placeholder="" value="">
              <small id="password2" hidden class="form-text text-muted">Error</small>
            </div>
        </div>
              <div class="w-100 d-flex justify-content-center">
            <input class="btn btn-primary mt-5 w-50" type="submit" value="Зарегистрировать">
          </div>
            
      </div>
    </form>
</div>
