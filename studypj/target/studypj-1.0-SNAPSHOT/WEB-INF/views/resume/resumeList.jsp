<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/11
  Time: 9:43 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<style type="text/css">


</style>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            이력서 리스트
        </span>
        <div class="reg-btn-wrap">
            <div class="reg-btn-div">
                <button id="reg-btn" onclick="location.href='/resume/registerPersonalStatement'">register</button>
            </div>
        </div>
    </div>
    <div class="table-wrap">
        <table class="table">
            <thead>
            <tr>
                <th>이력서 번호</th>
                <th>등록일자</th>
            </tr>
            </thead>
            <c:forEach items="${list}" var="resume">
                <tr>
                    <td><a class='move' href='<c:out value="${resume.resume_no}"/>'><c:out value="${resume.resume_no}"/></a></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${resume.regdate}" /></td>
                </tr>
            </c:forEach>
        </table>

        <!-- 페이징 처리 -->
        <div class='pagination-wrap'>
            <ul class="pagination">

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
<form id='actionForm' action="/resume/resumeList" method='get'>
    <input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
    <input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
</form>


<%@include file="../includes/footer.jsp" %>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        // result 를 체크해서 modify, remove 결과를 확인
        var result = '<c:out value="${result}"/>';

        if(result === 'success'){
            alert("작업이 성공했습니다. ");

        }else if(result === 'fail'){
            alert("작업이 실패했습니다. ");
        }

        var actionForm = $("#actionForm");

        // .move click 이벤트
        $(".move").on("click", function (e) {

            e.preventDefault();
            actionForm.append("<input type='hidden' name='resume_no' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/resume/getResume");
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

