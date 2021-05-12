<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/11
  Time: 4:14 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            modify PS
        </span>
    </div>
    <div class="table-wrap">
        <table class="table">
            <thead>
            <tr>
                <th>자기소개서 번호</th>
                <th>등록일자</th>
            </tr>
            </thead>
            <c:forEach items="${list}" var="personalStatement">
                <tr>
                    <td><c:out value="${personalStatement.personal_statement_no}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${personalStatement.regdate}" /></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>



<%@include file="../includes/footer.jsp" %>

</body>
</html>
