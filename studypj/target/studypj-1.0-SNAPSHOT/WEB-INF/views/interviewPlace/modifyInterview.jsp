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
            면접처 수정하기
        </span>
    </div>

    <div class="table-wrap">
        <div class="form-wrap">
            <form action="/interviewPlace/modifyInterview" method="post">
                <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                <input type='hidden' name='amount' value='${cri.amount}'>

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
                    <input type="text" name='interview_name'  value="${interview.interview_name}">
                </div>
                <div class="form-row">
                    <div>
                        면접일시
                    </div>
                    <input type="text" name='interview_date'  value="${interview.interview_date}">
                </div>
                <div class="form-row">
                    <div>
                        면접처 주소
                    </div>
                    <input type="text" name='interview_address'  value="${interview.interview_address}">
                </div>
                <div class="form-row">
                    <div>
                        면접처 진행여부
                    </div>
                    <!-- 면접 상태를 체크해서 한글로 작성 1이면 면접진행전, 2이면 면접완료 -->
                    <div id="radio-wrap">
                        <c:choose>
                            <c:when test="${interview.interview_progress == 1}">
                                <label><input type="radio" name='interview_progress' value="1" checked="checked"><span>면접진행전</span></label>
                                <label><input type="radio" name='interview_progress' value="2" ><span>면접완료</span></label>
                            </c:when>
                            <c:otherwise>
                                <label><input type="radio" name='interview_progress' value="1" ><span>면접진행전</span></label>
                                <label><input type="radio" name='interview_progress' value="2" checked="checked"><span>면접완료</span></label>
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
                    formObj.attr("action", "/interviewPlace/removeInterview");
                }else{
                    return;
                }

            }else if(operation === 'list'){
                // list로 이동 이동할때 method는 get으로 personalStatement의 정보는 지우고 페이징 관련 정보만 가지고 가야함
                formObj.attr("action", "/interviewPlace/interviewList").attr("method","get");

                var pageNumTag = $("input[name='pageNum']").clone();
                var amountTag = $("input[name='amount']").clone();

                // formObj 의 내용을 지운다. 태그는 남아있음, remove()와 다르다.
                formObj.empty();

                formObj.append(pageNumTag);
                formObj.append(amountTag);

            }else if(operation === 'modify'){
                if(fnCheckProgress()){
                    if(!confirm("면접 상태를 변경하면 더 이상 수정할 수 없습니다. 계속하시겠습니까?")){
                        return;
                    }
                }

            }

            // TODO submit() 하기전에 유효성 체크를 진행해야함
            // TODO API사용해서 주소정보를 얻어와야함 관련 처리 추가 필요.

            formObj.submit();
            // end $("button").on("click", function(e){
        });


        // end $(document).ready(function(){
    });

    // 면접진행여부를 체크하는 함수
    function fnCheckProgress(){
        // value를 체크해서 1이면 false, 2이면 경고창을 띄움
        var check = $(":input:radio[name='interview_progress']:checked").val();
        if(check == 1){
            return false;
        }else{
            return true;
        }


    }

</script>
</body>
</html>
