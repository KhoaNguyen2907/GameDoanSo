<%@ page import="cybersoft.java18.backend.gamedoanso.utils.UrlUtils" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/08/2022
  Time: 12:34 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <title>Trang chủ</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 mt-5">
            <div class="row justify-content-center">
                <h1 class=" text-primary">TRÒ CHƠI ĐOÁN SỐ</h1>
            </div>
            <div class="row justify-content-center mt-5">
                <c:if test="${currentUser ne null}">
                    <a name="" id="" class="btn btn-primary mx-3 px-3" href="<%=request.getContextPath() + UrlUtils.DANG_XUAT%>" role="button">Đăng xuất</a>
                </c:if>
                <c:if test="${currentUser eq null}">
                    <a name="" id="" class="btn btn-primary mx-3 px-3" href="<%=request.getContextPath() + UrlUtils.DANG_NHAP%>" role="button">Đăng nhập</a>
                    <a name="" id="" class="btn btn-primary mx-3 px-4" href="<%=request.getContextPath() + UrlUtils.DANG_KY%>" role="button">Đăng ký</a>
                </c:if>
                <a name="" id="" class="btn btn-primary mx-3 px-4" href="<%=request.getContextPath() + UrlUtils.GAME%>" role="button">Game</a>
            </div>

        </div>
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