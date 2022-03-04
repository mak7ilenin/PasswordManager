<%-- 
    Document   : showAccount
    Created on : Feb 17, 2022, 8:34:58 AM
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="album py-5 bg-light">
    <div class="container d-flex justify-content-center">
        <div class="card shadow-sm m-1"  style="width: 25rem;">
            <a class="w-100 d-flex justify-content-end" href="removeAccount?id=${accountBox.id}">удалить</a>
            <img src="insertFile/${accountBox.picture.pathToFile}" style="width:25rem;">
            <div class="card-body">
                <h2 class="card-header">${accountBox.name}</h2>
                <p class="card-text"><a href="${accountBox.url}" target="blank">${accountBox.url}</a></p>
                <p class="card-text">Логин: ${accountBox.urlLogin}</p>
                <p class="card-text">Парль: ${accountBox.urlPassword}</p>
          </div>
        </div>
    </div>
</div>
