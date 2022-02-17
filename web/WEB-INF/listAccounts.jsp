<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="album py-5 bg-light">
    <div class="container d-flex justify-content-center">
        <c:forEach var="accountBox" items="${listAccounts}">
            <div class="card shadow-sm m-1"  style="width: 10rem;">
                <img src="pic/${accountBox.picture}" style="width:10rem; height: 12rem;">
                <div class="card-body">
                    <p class="card-text"><a href="showAccount?accountId=${accountBox.id}">${accountBox.name}</a></p>
              </div>
            </div>
        </c:forEach>
    </div>
</div>