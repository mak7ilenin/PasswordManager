<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="album py-5 bg-light">
    <div class="container d-flex justify-content-center">
        <c:forEach var="modelBox" items="${listModels}">
            <div class="card shadow-sm m-1"  style="width: 10rem;">
                <img src="insertFile/${modelBox.model.pathToFile}" style="height: 12rem;">
                <div class="card-body">
                    <p class="card-text"><a href="showAccount?accountId=${modelBox.id}">${modelBox.modelName}</a></p>
              </div>
            </div>
        </c:forEach>
    </div>
</div>