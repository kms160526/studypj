<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/18
  Time: 1:28 오후
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
            면접처 후기 수정하기
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <form action="/interviewReview/modifyInterviewReview" method="post">
                <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                <input type='hidden' name='amount' value='${cri.amount}'>

                <div class="form-row">
                    <div>
                        면접 후기 번호
                    </div>
                    <input type="text" name='interview_review_no'
                           value="${interviewReview.interview_review_no}" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        면접처 번호
                    </div>
                    <input type="text" name='interview_no' value='${interviewReview.interview_no}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        내용
                    </div>
                    <textarea rows="5" cols="50" name='interview_review_content'>${interviewReview.interview_review_content}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        등록일자
                    </div>
                    <input type="text" name='regdate'
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${interviewReview.regdate}" />"
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
                // 삭제하기전에 체크
                if(confirm("정말 삭제하시겠습니까?")){
                    formObj.attr("action", "/interviewReview/removeInterviewReview");
                }else{
                    return;
                }

            }else if(operation === 'list'){
                // list로 이동 이동할때 method는 get으로 personalStatement의 정보는 지우고 페이징 관련 정보만 가지고 가야함
                formObj.attr("action", "/interviewReview/interviewReviewList").attr("method","get");

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
