<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/18
  Time: 11:50 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<style type="text/css">
    .table{
        border: 2px solid #444444;
    }

    .table td, th{
        border: 2px solid #444444;
        padding: 5px;
    }

    .table td a{
        color : black;
        text-decoration:none;
        cursor : text;
    }

</style>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            면접처 후기 리스트
        </span>

        <!--TODO 없애고 register 만들어야함 -->
        <div class="reg-btn-wrap">
            <div class="reg-btn-div">
                <button id="reg-btn" onclick="location.href='/interviewReview/registerInterviewReview'">register</button>
            </div>
        </div>

    </div>
    <div class="table-wrap">
        <table class="table">
            <thead>
            <tr>
                <th>면접처 번호</th>
                <th>면접처 이름</th>
                <th>버튼 부분 </th>
            </tr>
            </thead>
            <c:forEach items="${interviewJoinList}" var="interviewJoin">
                <tr>
                    <td><a class='move' href='${interviewJoin.interview_no}'>${interviewJoin.interview_no}</a></td>
                    <td>${interviewJoin.interview_name}</td>
                    <!-- interviewJoin.interview_review_no 체크해서 0이면 작성 버튼을 아니면 수정버튼을 만들기 -->
                    <td>
                        <c:choose>
                            <c:when test="${interviewJoin.interview_review_no == 0}">
                                <button data-oper='register' class="btn btn-info" value="${interviewJoin.interview_no}">작성</button>
                            </c:when>
                            <c:otherwise>
                                <button data-oper='modify' class="btn btn-default" value="${interviewJoin.interview_no}">수정</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- 페이징 처리 -->
        <div class='pagination-wrap'>
            <ul class="pagination">

                <!-- pageMaker.prev가 있으면 Prev를 만들고 없으면 만들지 않는다. -->
                <c:if test="${pageMaker.prev}">
                    <li class="paginate_button previous">
                        <a href="${pageMaker.startPage -1}">Prev</a>
                    </li>
                </c:if>

                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="paginate_button ${pageMaker.cri.pageNum == num ? "active" : ""}"><a
                            href="${num}">${num}</a></li>
                </c:forEach>

                <c:if test="${pageMaker.next}">
                    <li class="paginate_button next">
                        <a href="${pageMaker.endPage +1 }">Next</a>
                    </li>
                </c:if>
            </ul>
            <!-- end Pagination -->
        </div>


    </div>
</div>

<!-- actionForm 페이징처리 관련 -->
<form id='actionForm' action="/interviewReview/interviewReviewList" method='get'>
    <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
    <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
</form>


<%@include file="../includes/footer.jsp" %>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        // result 를 체크해서 modify, remove 결과를 확인
        var result = '${result}';

        if(result === 'success'){
            alert("작업이 성공했습니다. ");

        }else if(result === 'fail'){
            alert("작업이 실패했습니다. ");
        }

        // 페이지 이동을 사용할 form -> modify 할때 정보를 가지고 들어감, register는 해당사항 x
        var actionForm = $("#actionForm");

        // 버튼클릭 이벤트 작성버튼이면 작성페이지로 이동, 수정버튼이면 수정페이지로 이동
        $("button").on("click", function(e){

            e.preventDefault();

            var operation = $(this).data("oper");

            console.log("버튼클릭 oper -> " + operation);

            // 페이지 정보를 가지고 들어가기 위해서 form 을 submit 해서 이동한다.
            if(operation === 'modify'){
                // interview_no 정보 추가
                actionForm.attr("action", "/interviewReview/modifyInterviewReview");
                actionForm.append("<input type='hidden' name='interview_no' value='" + $(this).attr("value") + "'>");

            }else if(operation === 'register'){
                // register는 page 관련 정보가 필요없으니 location.href 로 이동한다.
                // 테스트 하기 위해서 home/home으로 이동

                actionForm.empty();
                actionForm.attr("action", "/interviewReview/registerInterviewReview");
                actionForm.append("<input type='hidden' name='interview_no' value='" + $(this).attr("value") + "'>");
            }
            actionForm.submit();

        });

        // 페이징 처리 이동 관련 이벤트 추가
        // 페이지 번호를 클릭하면 처리하는 부분 추가
        $(".paginate_button a").on("click", function (e) {

            // 기존 a태그 동작 중지
            e.preventDefault();

            console.log('paginate_button click');

            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });


        // end $.function()
    });



</script>


</body>
</html>
