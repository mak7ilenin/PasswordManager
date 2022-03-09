<%-- 
    Document   : editUser
    Created on : Mar 7, 2022, 7:46:17 PM
    Author     : makso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center">
    <form action="showEditUser" method="POST" >
        <div class="card border-0 mb-3" style="width: 40em;">
            <h2 style="padding-bottom: 50px" class="my-4 w-100 d-flex justify-content-center">Изменение обуви</h2>
            <label for="theUsers" class="form-label mt-4">Доступные модели</label>
            <select class="form-select" id="theUsers" name="theUsers">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}" <c:if test="${user.id eq theUsers}">selected</c:if>>User name: ${user.firstName} // Surname: ${user.lastName} // Phone ${user.phone} // Money: ${user.money}</option>
                </c:forEach>
            </select>
            <div class="form-group">
                <label for="editModelName" class="form-label mt-4">Изменить название</label>
                <input type="text" class="form-control" id="editModelName" name="editModelName" aria-describedby="" placeholder="" value="${modelName}">
                <small id="editModelName" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editModelSize" class="form-label mt-4">Изменить размер</label>
                <input type="text" class="form-control" id="editModelSize" name="editModelSize" aria-describedby="" placeholder="" value="${modelSize}">
                <small id="editModelSize" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editModelFirm" class="form-label mt-4">Изменить бренд</label>
                <input type="text" class="form-control" id="editModelFirm" name="editModelFirm" aria-describedby="" placeholder="" value="${modelFirm}">
                <small id="editModelFirm" hidden class="form-text text-muted">Error</small>
            </div>
            <div class="form-group">
                <label for="editPrice" class="form-label mt-4">Изменить цену</label>
                <input type="text" class="form-control" id="editPrice" name="editPrice" aria-describedby="" placeholder="" value="${price}">
                <small id="editPrice" hidden class="form-text text-muted">Error</small>
            </div>
            <input class="btn btn-primary mt-5" type="submit" value="Изменить">
        </div>
    </form>
</div>
