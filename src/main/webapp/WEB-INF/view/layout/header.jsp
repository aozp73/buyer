<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>구매서버</title>
            <link rel="stylesheet" href="../css/style.css">
        </head>

        <body>
            <nav>
                <ul>
                    <c:choose>
                        <c:when test="${principal == null}">
                            <div class="nav_title">
                                <li>
                                    <a href="/">홈</a>
                                </li>
                                <li>
                                    <a href="/loginForm">로그인</a>
                                </li>
                                <li>
                                    <a href="/joinForm">회원가입</a>
                                </li>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="nav_title"></div>
                            <li>
                                <a href="/">홈</a>
                            </li>
                            <li>
                                <a href="purchase">구매목록</a>
                            </li>
                            <li>
                                <a href="/logout">로그아웃</a>
                            </li>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </nav>