
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="uploadFileCss.css">
<div class="album py-5 bg-light">
    <div class="container d-flex justify-content-center">
        <div class="card shadow-sm m-1"  style="width: 35rem;">
            <div class="card-body">
                <h2 class="card-header">Загрузка файлов на сервер</h2>
                <form action="uploadFile" method="POST"  enctype="multipart/form-data" >
                  <div class="form-group">
                    <label for="description" class="form-label mt-4">Название файла</label>
                    <input type="text" class="form-control" id="description" name="description" aria-describedby="" placeholder="" >
                    <small id="description" hidden class="form-text text-muted">Error</small>
                  </div>
                  <div class="form-group">
                    <label for="file" class="form-label mt-4">Выберите файл</label>
                    <input type="file" class="form-control" id="file" name="file" aria-describedby="" placeholder="" >
                    <small id="file" hidden class="form-text text-muted">Error</small>
                  </div>
                   <input class="btn btn-primary mt-5" type="submit" value="Загрузить файл">
                </form>
            </div>
        </div>
    </div>
</div>

