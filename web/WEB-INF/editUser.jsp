<%-- 
    Document   : editUser
    Created on : Feb 10, 2022, 1:25:08 PM
    Author     : makso
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="editUser" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Изменение личных данных пользователя</h2>
            <label for="theUsers" style="text-align: center;" class="form-label mt-4">Зарегистрированные пользователи</label>
            <select class="form-select"  id="theUsers" name="theUsers">
                <c:forEach var="user" items="${users}">
                    <option style="text-align: center" value="${user.id}" <c:if test="${user.id eq theUsers}">${user.firstName}</c:if>>${user.firstName}</option>
                </c:forEach>
            </select>

            <div class="form-group">
                <label for="editUserFirstName" class="form-label mt-4">Изменить имя</label>
                <input type="text" class="form-control" id="editUserFirstName" name="editUserFirstName" aria-describedby="" placeholder="" value="${firstName}">
                <small id="editUserFirstName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserLastName" class="form-label mt-4">Изменить фамилию</label>
                <input type="text" class="form-control" id="editUserLastName" name="editUserLastName" aria-describedby="" placeholder="" value="${lastName}">
                <small id="editUserLastName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserPhone" class="form-label mt-4">Изменить номер телефона</label>
                <input type="text" class="form-control" id="editUserPhone" name="editUserPhone" aria-describedby="" placeholder="" value="${phone}">
                <small id="editUserPhone" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editUserMoney" class="form-label mt-4">Изменить количество денег</label>
                <input type="text" class="form-control" id="editUserMoney" name="editUserMoney" aria-describedby="" placeholder="" value="${money}">
                <small id="editUserMoney" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Изменить">
            <!--<a class="btn btn-primary mt-2" href="edit">Изменить данные входа</a>-->
            
        </div>
    </form>
</div>
       