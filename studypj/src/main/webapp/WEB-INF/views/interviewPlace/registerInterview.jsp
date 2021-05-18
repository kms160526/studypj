<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/18
  Time: 3:59 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

<style type="text/css">
    #radio-wrap{
        display: flex;
        padding: 10px;
    }

    #radio-wrap label{
        display: flex;
    }

    #radio-wrap label span{
        width: 150px;
    }

</style>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            면접처 등록하기
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <form action="/interviewPlace/registerInterview" method="post">
                <div class="form-row">
                    <div>
                        면접처 명칭
                    </div>
                    <input type="text" name='interview_name'>
                </div>
                <div class="form-row">
                    <div>
                        면접일시
                    </div>
                    <input type="text" name='interview_date'>
                </div>
                <div class="form-row">
                    <div>
                        면접처 주소
                    </div>
                    <input type="text" name='interview_address'>
                </div>
                <div class="form-row">
                    <div>
                        면접처 진행여부
                    </div>
                    <div id="radio-wrap">
                        <label><input type="radio" name='interview_progress' value="1" checked="checked"><span>면접진행전</span></label>
                        <label><input type="radio" name='interview_progress' value="2" ><span>면접완료</span></label>
                    </div>
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

            if(operation === 'register'){
                // TODO register 할때 주소정보 관련한 처리가 필요함
                console.log("register 클릭");

            }else if(operation === 'list'){
                // formObj 의 내용을 지운다.
                formObj.empty();

                formObj.attr("action", "/interviewPlace/interviewList").attr("method","get");

            }

            // TODO submit() 하기전에 유효성 체크를 진행해야함
            // TODO API사용해서 주소정보를 얻어와야함 관련 처리 추가 필요.

            formObj.submit();
            // end $("button").on("click", function(e){
        });


        // end $(document).ready(function(){
    });

</script>
</body>
</html>

