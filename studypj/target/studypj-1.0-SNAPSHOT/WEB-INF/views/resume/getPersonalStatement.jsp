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
            자기소개서
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
                <div>
                    등록일자
                </div>
                <input type="text" name='regdate'
                       value="<fmt:formatDate pattern="yyyy-MM-dd" value="${personalStatement.regdate}" />"
                       readonly="readonly">
            </div>


            <div class="button-wrap">
                <button data-oper='modify' class="btn btn-default" >Modify</button>

                <button data-oper='list' class="btn btn-info" >List</button>

                <form id='operForm' action="/resume/modifyPersonalStatement" method="get">
                    <input type='hidden' id='personal_statement_no' name='personal_statement_no' value='<c:out value="${personalStatement.personal_statement_no}"/>'>
                    <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
                    <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
                </form>
                <!-- end button-wrap -->
            </div>

        <!-- end form-wrap -->
        </div>

    </div>
</div>



<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){

        var operForm = $("#operForm");

        // mddify 버튼 클릭시 operForm을 submit()
        $("button[data-oper='modify']").on("click", function(){

            operForm.submit();
        });

        // list 버튼 클릭시 이동 operForm을 submit(), action 속성 수정
        $("button[data-oper='list']").on("click", function(){

            // list로 이동할때는 personal_statement_no 삭제
            operForm.find("#personal_statement_no").remove();
            operForm.attr("action", "/resume/personalStatementList");
            operForm.submit();
        });

    });

</script>

</body>
</html>
