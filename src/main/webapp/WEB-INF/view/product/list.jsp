<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <h1>상품 목록페이지</h1>
        <hr>

        <table border="1">

            <tr>
                <th>번호</th>
                <th>상품명</th>
                <th>가격</th>
                <th>재고</th>
                <th>등록일</th>
            </tr>

            <c:forEach items="${productList}" var="productList">
                <tr>
                    <td>${productList.id}</td>
                    <td><a href="/product/${productList.id}">${productList.name}</a> </td>
                    <td>${productList.price}</td>
                    <td>${productList.qty}</td>
                    <td>${productList.createdAt}</td>
                </tr>
            </c:forEach>

        </table>

        <%@ include file="../layout/footer.jsp" %>