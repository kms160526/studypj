<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/12
  Time: 5:03 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            자기소개서 등록하기
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <form action="/resume/registerPersonalStatement" method="post">

                <div class="form-row">
                    <div>
                        가정환경
                    </div>
                    <textarea rows="5" cols="50" name='home_environment'></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인장단점
                    </div>
                    <textarea rows="5" cols="50" name='pros_and_cons'></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인경험
                    </div>
                    <textarea rows="5" cols="50" name='experiance' ></textarea>
                </div>
                <div class="form-row">
                    <div>
                        입사포부
                    </div>
                    <textarea rows="5" cols="50" name='job_espirations' ></textarea>
                </div>

                <div class="button-wrap">
                    <button type="submit" data-oper='register' class="btn btn-default" >Register</button>

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

            if(operation === 'list'){
                // POST - /resume/removePersonalStatement 으로 변경
                formObj.attr("action", "/resume/personalStatementList").attr("method","get");

            }

            formObj.submit();
            // end $("button").on("click", function(e){
        });

        // end $(document).ready(function(){
    });

</script>
</body>
</html>
