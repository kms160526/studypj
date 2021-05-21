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
            면접처 길찾기(지도)
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
                <div id="map" style="width:100%;height:400px;"></div>
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

<!-- 네이버 주소 API -- 위도,경도를 통해 지도에 위치 표시 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=eyjh3k2c64"></script>
<!-- 네이버 주소 API -- Geocoding 주소를 가져와서 위도, 경도로 반환 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=eyjh3k2c64&submodules=geocoder"></script>
<script>
    // interview.interview_address 에 있는 체크를 진행해야함.
    var interviewAddress = "${interview.interview_address}";
    // TODO: Address의 유효성 검사...

    naver.maps.Service.geocode({
        query: interviewAddress
    }, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return alert('Something wrong!');
        }

        var result = response.v2, // 검색 결과의 컨테이너
            items = result.addresses; // 검색 결과의 배열

        // do Something
        // 지도 정보를 제대로 가지고 오지 못하면 response.v2.addresses[0] 이 "undefined" 이다.
        if(typeof result.addresses[0] == "undefined"){
            alert("지도를 불러오는데 실패했습니다. ");
            return;
        }

        // log 체크
        console.log(result.addresses[0].x);
        console.log(result.addresses[0].y);
        // x좌표와 y좌표를 등록

        var entX = result.addresses[0].x;
        var entY = result.addresses[0].y;

        var mapOptions = {
            center: new naver.maps.LatLng(entY,entX),
            zoom: 16
        };

        var map = new naver.maps.Map('map', mapOptions);

        // 지도에 마커 옵션 추가
        var marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(entY, entX),
            map: map
        });

        // 마커 위에 추가 정보 출력 -- infowindow 관련 이벤트 설정, 마커를 클릭하면 popup
        var contentString = [
            '<div class="iw_inner">',
            '   <br/><h3 align="center">${interview.interview_name}</h3><br/>',
            '   <p align="center">${interview.interview_address}<br/></p><br/>',
            '   <p align="center">${interview.interview_date}<br/></p><br/>',
            '</div>'
        ].join('');

        var infowindow = new naver.maps.InfoWindow({
            content: contentString
        });

        naver.maps.Event.addListener(marker, "click", function(e) {
            if (infowindow.getMap()) {
                infowindow.close();
            } else {
                infowindow.open(map, marker);
            }
        });

        infowindow.open(map, marker);

        // end naver.maps.Service.geocode
    });


</script>
<script>
    // 네이버 API Ajax 테스트
    $(document).ready(function(){
        // $.ajax(function(){
        //
        // });

            // start={출발지}&goal={목적지}&option={탐색옵션}

        // 주소는 {경도,위도} , 출발지는 우리집주소에서 출발, 목적지는 목적지의 위도경도..

        $.ajax({
            url: "${pageContext.request.contextPath}/interviewPlace/naverAPI",
            data:
                {
                },
            dataType: "text",
            type: "GET",
            async: false,
            success: function (data) {
                console.log("API 통신 성공 ");
                console.log(data);

                // $.each(data, function (key, val) {
                //
                //     // 받아온 데이터 처리
                //
                // });
            },
            error: function(request, status, error){
                // console.log(error);
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

            }
        });




    });


</script>
</body>
</html>

