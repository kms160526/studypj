<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/20
  Time: 5:55 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<style type="text/css">
    .table td, th{
        padding: 5px;
    }

</style>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            면접처 길찾기(지도)
        </span>
    </div>
    <div class="table-wrap">
        <table class="table">
            <thead>
            <tr>
                <th>면접처번호</th>
                <th>면접처명칭</th>
                <th>면접진행여부</th>
                <th>등록일자</th>
            </tr>
            </thead>
            <c:forEach items="${interviewList}" var="interview">
                <tr>
                    <td><a class='move' href='${interview.interview_no}'>${interview.interview_no}</a></td>
                    <td>${interview.interview_name}</td>
                    <!-- 면접 상태를 체크해서 한글로 작성 1이면 면접진행전, 2이면 면접완료 -->
                    <c:choose>
                        <c:when test="${interview.interview_progress == 1}">
                            <td>면접진행전</td>
                            <input type="hidden" name="interview_progress" value="${interview.interview_progress}">
                        </c:when>
                        <c:otherwise>
                            <td>면접완료</td>
                            <input type="hidden" name="interview_progress" value="${interview.interview_progress}">
                        </c:otherwise>
                    </c:choose>

                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${interview.interview_regdate}" /></td>
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
<form id='actionForm' action="/interviewPlace/interviewDirectionList" method='get'>
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

        var actionForm = $("#actionForm");

        // .move click 이벤트
        $(".move").on("click", function (e) {

            e.preventDefault();
            actionForm.append("<input type='hidden' name='interview_no' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/interviewPlace/getInterviewDirection");
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

