<%-- 
    Document   : showAccount
    Created on : Feb 17, 2022, 8:34:58 AM
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="album py-5 bg-light">
    <div class="container d-flex justify-content-center">
        <div class="card shadow-sm m-1"  style="width: 15rem;">
            <img src="pic/${accountBox.picture}" style="width:15rem; height: 12rem;">
            <div class="card-body">
                <p class="card-header">${accountBox.name}</p>
                <p class="card-text">Логин: ${accountBox.urlLogin}</p>
                <p class="card-text">Парль: ${accountBox.urlPassword}</p>
          </div>
        </div>
    </div>
</div>
