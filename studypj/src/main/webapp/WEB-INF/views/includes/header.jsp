<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/11
  Time: 9:44 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Interview Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- Reset CSS -->
    <link href="/resources/css/reset.css" rel="stylesheet" type="text/css">

    <!-- template CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/template.css" rel="stylesheet" type="text/css">

</head>

<body>

<div id="header">
    <div id="h1-wrap">
        <h1>스터디 프로젝트 - 면접 가는 곳 길 찾기 및 후기 사이트</h1>
    </div>
</div>

<div id="container">
    <!-- 사이드 바 -->
    <div class="nav">
        <ul>
            <li><a class="menu menu-title" href="#">자기소개</a></li>
            <li><a href="/resume/resumeList">이력서</a></li>
            <li><a href="/resume/personalStatementList">자기소개서</a></li>
        </ul>
        <ul>
            <li><a class="menu menu-title" href="#">면접처 리스트</a></li>
            <li><a href="/interviewPlace/interviewList">면접처 등록 / 수정 / 보기</a></li>
            <li><a href="/interviewPlace/interviewMapList">면접처 위치(지도)</a></li>
            <li><a href="/interviewPlace/interviewDirectionList">면접처 길찾기(지도)</a></li>
        </ul>
        <ul>
            <li><a class="menu menu-title" href="#">면접처 후기</a></li>
            <li><a href="/interviewReview/interviewReviewList">면접처별 후기 등록</a></li>
        </ul>
    </div>
