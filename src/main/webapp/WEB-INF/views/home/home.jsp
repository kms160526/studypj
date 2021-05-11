<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/10
  Time: 4:59 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Interview Home</title>

    <!-- Reset CSS -->
    <link href="/resources/css/reset.css" rel="stylesheet">

<style type="text/css">
    #header{
        width: 100%;
        height: 80px;
        display: flex;
        justify-content: center;
        min-width: 680px;
    }

    #header #h1-wrap{

    }

    #container{
        width: 100%;
        height: 600px;
        display: flex;
        min-width: 680px;
    }

    #container .nav{
        width: 170px;
        background-color: #ccc;
    }

    /* 사이드바 관련 태그 */
    .nav ul {
        list-style-type: none;
        background-color: #ccc;
        padding: 0;
        margin:  0;
    }

    .nav li a.menu {
        background-color: tomato;
        color: #fff;
    }

    .nav li a {
        text-decoration: none;
        display: block;
        color: #000;
        padding: 8px 15px 8px 15px;
        font-weight: bold;
    }

    /* hover 되어도 커서 변경 없이 */
    .nav li a:hover:not(.menu-title) {
        background-color: #333;
        color: #fff;
    }

    .nav li a.menu-title {
        cursor: default;
    }

    #container #content{
        /* 나머지 영역을 전부 채움 */
        flex-grow: 1;
    }

    #footer{
        width: 100%;
        height: 150px;
        min-width: 680px;
    }


</style>

</head>
<body>

<div id="header">
    <div id="h1-wrap">
        <h1>스터디 프로젝트 - 면접 가는 곳 길 찾기 및 후기 사이트</h1>
    </div>
</div>

<div id="container">
    <div class="nav">
        <ul>
            <li><a class="menu menu-title" href="#">자기소개</a></li>
            <li><a href="#">이력서</a></li>
            <li><a href="#">자기소개서</a></li>
        </ul>
        <ul>
            <li><a class="menu menu-title" href="#">면접처 리스트</a></li>
            <li><a href="#">면접처 등록 / 수정 / 보기</a></li>
            <li><a href="#">면접처 위치(지도)</a></li>
            <li><a href="#">면접처 길찾기(지도)</a></li>
        </ul>
        <ul>
            <li><a class="menu menu-title" href="#">면접처 후기</a></li>
            <li><a href="#">면접처별 후기 등록</a></li>
        </ul>
    </div>
    <div id="content">
        <div>
            본문
            본문
            본문
            본문
            본문
        </div>
    </div>

</div>

<div id="footer">
    footer
</div>

</body>
</html>
