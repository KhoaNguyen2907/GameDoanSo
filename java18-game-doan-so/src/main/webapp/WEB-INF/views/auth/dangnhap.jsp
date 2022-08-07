<%@ page import="cybersoft.java18.backend.gamedoanso.utils.UrlUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <title>Đăng nhập</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="text-center text-primary mt-3">
        <h1> Đăng nhập</h1>
    </div>
    <div class="row justify-content-center">
        <div class="form-group col-md-4 ">
            <form action="<%=request.getContextPath()+UrlUtils.DANG_NHAP%>" method="post">
                <div class="mt-2">
                    <label for="username">Tài khoản</label>
                    <input type="text"
                           class="form-control" name="username" id="username" aria-describedby="helpId"
                           placeholder="Nhập tài khoản">
                </div>
                <div class="mt-2">
                    <label for="password">Mật khẩu</label>
                    <input type="password"
                           class="form-control" name="password" id="password" aria-describedby="helpId"
                           placeholder="Nhập mật khẩu">
                </div>

                <div class="row justify-content-center mt-4">
                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%=request.getAttribute("error")%>
                    </div>
                    <%}%>
                </div>
                <div class="row justify-content-center mt-2">

                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row justify-content-center mt-2">
        <p class="form-text text-muted">
            Chưa có tài khoản?  <a href="<%=request.getContextPath() + UrlUtils.DANG_KY%>"> Đăng ký</a>
        </p>
    </div>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>