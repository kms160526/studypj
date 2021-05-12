<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/11
  Time: 9:44 오전
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
            자기소개서 페이지
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
                    <td><a class='move' href='<c:out value="${personalStatement.personal_statement_no}"/>'><c:out value="${personalStatement.personal_statement_no}"/></a></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${personalStatement.regdate}" /></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>

<!-- actionForm 페이징처리 관련 -->
<form id='actionForm' action="/resume/personalStatementlist" method='get'>
    <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
    <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
</form>


<%@include file="../includes/footer.jsp" %>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        var actionForm = $("#actionForm");


        // .move click 이벤트
        $(".move").on("click", function (e) {

            e.preventDefault();
            actionForm.append("<input type='hidden' name='personal_statement_no' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/resume/getPersonalStatement");
            actionForm.submit();
        });

        // end $.function()
    });



</script>


</body>
</html>
