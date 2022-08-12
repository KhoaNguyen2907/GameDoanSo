<%@ page import="cybersoft.java18.backend.gamedoanso.utils.UrlUtils" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/08/2022
  Time: 5:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <title>Danh sách game đã chơi</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Fontawsome Icon CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand font-weight-bold" href="<%=request.getContextPath() + UrlUtils.ROOT%>">Trò Chơi Đoán Số</a>
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link font-weight-bold" href="<%=request.getContextPath() + UrlUtils.ROOT%>">Home<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link font-weight-bold" href="<%=request.getContextPath() + UrlUtils.GAME%>">Game</a>
            </li>
            <li class="nav-item">
                <a class="nav-link font-weight-bold"
                   href="<%=request.getContextPath() + UrlUtils.XEP_HANG%>">Ranking</a>
            </li>
        </ul>
    </div>
    <div class="nav-item dropdown">
        <a class="nav-link dropdown-toggle font-weight-bold" href="#" role="button" data-toggle="dropdown"
           aria-expanded="false">
            ${currentUser.name}
        </a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath() + UrlUtils.DANG_XUAT%>">Đăng xuất</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12 ">
            <h1 class=" text-center text-primary mt-5">Các màn chơi của bạn</h1>
            <table class="table table-bordered mt-5 text-center ">
                <thead>
                <tr>
                    <th>Game</th>
                    <th>Kết quả</th>
                    <th>Số lượt đoán</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết thúc</th>
                    <th>Trạng thái</th>
                    <th>Tùy chọn</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="game" items="${gameList}" varStatus="id">
                    <c:if test="${game.completed eq true}">
                        <tr class="table-success">
                    </c:if>
                    <c:if test="${game.completed eq false}">
                        <tr class="table-danger">
                    </c:if>
                    <td scope="row">${id.index+1}</td>
                    <td class ="align-middle">
                        <c:if test="${game.completed eq true}">
                            ${game.targetNumber}
                        </c:if>
                        <c:if test="${game.completed eq false}">
                            <i class="bi bi-question-lg"></i>
                            <i class="bi bi-question-lg"></i>
                            <i class="bi bi-question-lg"></i>
                        </c:if>
                    </td>
                    <td class ="align-middle">${game.guessList.size()} lượt</td>
                    <td class ="align-middle">${game.startTime.format(game.formatter)}</td>
                    <td class ="align-middle">
                        <c:if test="${game.endTime ne null}">
                            ${game.endTime.format(game.formatter)}
                        </c:if>
                        <c:if test="${game.endTime eq null}">
                            Chưa hoàn thành
                        </c:if>

                    </td>
                    <td class ="align-middle">
                        <c:if test="${game.completed eq true}">
                            <i class="bi bi-check-square-fill"></i>
                        </c:if>
                        <c:if test="${game.completed eq false}">
                            <i class="bi bi-x-square"></i>
                        </c:if>
                    </td>
                    <td class ="align-middle">
                        <c:if test="${game.completed eq true}">
                            <form action="<%=request.getContextPath() + UrlUtils.GAME%>" method="get">
                                <input type="hidden" name="id" value="${game.id}">
                                <input type="submit" class="btn btn-secondary disabled" disabled value="Đã hoàn thành">
                            </form>
                        </c:if>
                        <c:if test="${game.completed eq false}">
                            <form action=" <%= request.getContextPath() + UrlUtils.GAME %>" method="get">
                                <input type="hidden" name="id" value="${game.id}">
                                <button type="submit" class="btn btn-primary"><i class="bi bi-play-fill"></i> Chơi tiếp</button>

                            </form>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <div class=" row justify-content-center mt-5">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
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