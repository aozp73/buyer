<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <h1>구매 페이지</h1>
        <hr>
        <c:choose>
            <c:when test="${check < 0}">
                <p style="color: red;">신청하신만큼 재고가 없습니다. 재고를 확인해 주세요.</p>
            </c:when>

            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <form action="/product/${product.id}/purchase" method="Post">
            <input type="hidden" value="${product.id}">
            <input type="hidden" name="name" value="${product.name}">
            <input type="hidden" name="price" value="${product.price}"><br>
            가격 : <input type="text" value="${product.price}원" size="20" readonly><br>
            재고 : <input type="text" value="${product.qty}개" size="20" required><br>
            등록 : <input type="text" value="${product.createdAt}" readonly><br><br>
            <input type="number" name="qty" min="1" max="1000" size="12" required>
            <button type="submit">구매하기</button>
        </form>

        <%@ include file="../layout/footer.jsp" %>