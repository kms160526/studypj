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
            자기소개서 수정하기
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <form action="/resume/modifyPersonalStatement" method="post">
                <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
                <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>

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
                    <textarea rows="5" cols="50" name='home_environment'><c:out
                            value="${personalStatement.home_environment}"/></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인장단점
                    </div>
                    <textarea rows="5" cols="50" name='pros_and_cons'><c:out
                            value="${personalStatement.pros_and_cons}"/></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인경험
                    </div>
                    <textarea rows="5" cols="50" name='experiance' ><c:out
                            value="${personalStatement.experiance}"/></textarea>
                </div>
                <div class="form-row">
                    <div>
                        입사포부
                    </div>
                    <textarea rows="5" cols="50" name='job_espirations' ><c:out
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
                    <button type="submit" data-oper='modify' class="btn btn-default" >Modify</button>

                    <button type="submit" data-oper='remove' class="btn btn-danger" >Remove</button>

                    <button type="submit" data-oper='list' class="btn btn-info" >List</button>


                    <!-- end button-wrap -->
                </div>

            </form>
            <!-- end form-wrap -->
        </div>

    </div>
</div>



<%@include file="../includes/footer.jsp" %>
<script>
    $(document).ready(function(){

        var formObj = $("form");

        $("button").on("click", function(e){

            // 기존 submit 이벤트를 멈춘다.
            e.preventDefault();

            // button 에있는 data-oper를 읽어서 operation에 저장
            var operation = $(this).data("oper");

            console.log("operation: " + operation);

            if(operation === 'remove'){
                // POST - /resume/removePersonalStatement 으로 변경
                formObj.attr("action", "/resume/removePersonalStatement");

            }else if(operation === 'list'){
                // list로 이동 이동할때 method는 get으로 personalStatement의 정보는 지우고 페이징 관련 정보만 가지고 가야함
                formObj.attr("action", "/resume/personalStatementList").attr("method","get");

                var pageNumTag = $("input[name='pageNum']").clone();
                var amountTag = $("input[name='amount']").clone();

                // formObj 의 내용을 지운다. 태그는 남아있음, remove()와 다르다.
                formObj.empty();

                formObj.append(pageNumTag);
                formObj.append(amountTag);

            }else if(operation === 'modify'){
                console.log("딱히내용없음 modify.....");
            }

            formObj.submit();
        // end $("button").on("click", function(e){
        });





    // end $(document).ready(function(){
    });



</script>
</body>
</html>
