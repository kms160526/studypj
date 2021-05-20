<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/20
  Time: 4:39 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
<style type="text/css">
    .table-wrap {
        width: 80%;
    }

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
            면접처
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <div class="form-row">
                <div>
                    면접처 번호
                </div>
                <input type="text" name='interview_no'
                       value="${interview.interview_no}" readonly="readonly">
            </div>
            <div class="form-row">
                <div>
                    면접처 명칭
                </div>
                <input type="text" name='interview_name' readonly="readonly" value="${interview.interview_name}">
            </div>
            <div class="form-row">
                <div>
                    면접일시
                </div>
                <input type="text" name='interview_date' readonly="readonly" value="${interview.interview_date}">
            </div>
            <div class="form-row">
                <div>
                    면접처 주소
                </div>
                <input type="text" name='interview_address' readonly="readonly" value="${interview.interview_address}">
            </div>
            <div class="form-row">
                <div>
                    면접처 위치(지도)
                </div>
                <span>지도지도지도지도지도</span>
            </div>
            <div class="form-row">
                <div>
                    면접처 진행여부
                </div>
                <!-- 면접 상태를 체크해서 한글로 작성 1이면 면접진행전, 2이면 면접완료 -->
                <div id="radio-wrap">
                    <c:choose>
                        <c:when test="${interview.interview_progress == 1}">
                            <label><input type="radio" name='interview_progress' value="1" checked="checked" onclick="return(false);"><span>면접진행전</span></label>
                            <label><input type="radio" name='interview_progress' value="2" onclick="return(false);"><span>면접완료</span></label>
                        </c:when>
                        <c:otherwise>
                            <label><input type="radio" name='interview_progress' value="1" onclick="return(false);"><span>면접진행전</span></label>
                            <label><input type="radio" name='interview_progress' value="2" checked="checked" onclick="return(false);"><span>면접완료</span></label>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-row">
                <div>
                    등록일자
                </div>
                <input type="text" name='interview_regdate'
                       value="<fmt:formatDate pattern="yyyy-MM-dd" value="${interview.interview_regdate}" />"
                       readonly="readonly">
            </div>


            <div class="button-wrap">

                <button data-oper='list' class="btn btn-info" >List</button>

                <form id='operForm' action="/interviewPlace/interviewMapList" method="get">
                    <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                    <input type='hidden' name='amount' value='${cri.amount}'>
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

        // list 버튼 클릭시 이동 operForm을 submit(), action 속성 수정
        $("button[data-oper='list']").on("click", function(){

            // list로 이동할때는 personal_statement_no 삭제
            operForm.find("#interview_no").remove();
            // operForm.attr("action", "/interviewPlace/interviewMapList");
            operForm.submit();
        });

    });

</script>

<!-- 네이버 주소 API -- Geocoding 주소를 가져와서 위도, 경도로 반환 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=eyjh3k2c64&submodules=geocoder"></script>
<script>
    // interview.interview_address 에 있는 체크를 진행해야함.

    naver.maps.Service.geocode({
        query: '${interview.interview_address}'
    }, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return alert('Something wrong!');
        }

        var result = response.v2, // 검색 결과의 컨테이너
            items = result.addresses; // 검색 결과의 배열

        // do Something
        console.log(result.addresses[0].x);
        console.log(result.addresses[0].y);
        // x좌표와 y좌표를 등록

    });

</script>
</body>
</html>
