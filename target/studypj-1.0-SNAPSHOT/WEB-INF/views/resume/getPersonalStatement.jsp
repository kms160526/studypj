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
            getPS
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <div class="form-row">
                <div>
                    자기소개서 번호
                </div>
                <input type="text" name='personal_statement_no'
                       value="<c:out value="${personalStatement.personal_statement_no}" />" readonly="readonly">
            </div>
            <div class="form-row">
                <div>
                    가정환경
                </div>
                <textarea rows="5" cols="50" name='home_environment' readonly="readonly"><c:out
                        value="${personalStatement.home_environment}"/></textarea>
            </div>
            <div class="form-row">
                <div>
                    본인장단점
                </div>
                <textarea rows="5" cols="50" name='pros_and_cons' readonly="readonly"><c:out
                        value="${personalStatement.pros_and_cons}"/></textarea>
            </div>
            <div class="form-row">
                <div>
                    본인경험
                </div>
                <textarea rows="5" cols="50" name='experiance' readonly="readonly"><c:out
                        value="${personalStatement.experiance}"/></textarea>
            </div>
            <div class="form-row">
                <div>
                    입사포부
                </div>
                <textarea rows="5" cols="50" name='job_espirations' readonly="readonly"><c:out
                        value="${personalStatement.job_espirations}"/></textarea>
            </div>
            <div class="form-row">
                등록일자
                <input type="text" name='regdate'
                       value="<fmt:formatDate pattern="yyyy-MM-dd" value="${personalStatement.regdate}" />"
                       readonly="readonly">
            </div>
        <!-- end form-wrap -->
        </div>

    </div>
</div>



<%@include file="../includes/footer.jsp" %>

</body>
</html>
