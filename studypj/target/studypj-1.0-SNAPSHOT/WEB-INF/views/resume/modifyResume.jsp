<%--
  Created by IntelliJ IDEA.
  User: kimminsu
  Date: 2021/05/13
  Time: 10:13 오전
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
            이력서
        </span>
    </div>

    <div class="table-wrap">
        <!-- 실제 action을 담당하는 form -->
        <div id="action-from-wrap">
            <form id="action-form" name="action-form" method="post" action="/resume/modifyResume">
                <input type='hidden' id='resume_no' name='resume_no' value='${resume.resume_no}'>
                <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                <input type='hidden' name='amount' value='${cri.amount}'>
            </form>
        </div>

        <!-- 개인신상 form-wrap -->
        <div class="form-wrap">
            <form name="personal-form">
                <div class="form-row">
                    <div>
                        개인신상_번호
                    </div>
                    <input type="text" name='personal_no'
                           value="${personal.personal_no}" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        이름_한글
                    </div>
                    <input type="text" name='name_ko' value='${personal.name_ko}'>
                </div>
                <div class="form-row">
                    <div>
                        이름_영문
                    </div>
                    <input type="text" name='name_en' value='${personal.name_en}'>
                </div>
                <div class="form-row">
                    <div>
                        이름_한자
                    </div>
                    <input type="text" name='name_cn' value='${personal.name_cn}'>
                </div>
                <div class="form-row">
                    <div>
                        생년월일
                    </div>
                    <input type="text" name='birth' value='${personal.birth}'>
                </div>
                <div class="form-row">
                    <div>
                        주소
                    </div>
                    <input type="text" cols="50" name='address' value='${personal.address}'>
                </div>

                <div class="form-row">
                    <div>
                        연락처
                    </div>
                    <input type="text" name='contact' value='${personal.contact}'>
                </div>
                <div class="form-row">
                    <div>
                        핸드폰
                    </div>
                    <input type="text" name='phone' value='${personal.phone}'>
                </div>
                <div class="form-row">
                    <div>
                        이메일
                    </div>
                    <input type="text" name='email' value='${personal.email}'>
                </div>
                <div class="form-row">
                    <div>
                        등록일자
                    </div>
                    <input type="text" name='regdate'
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${personal.regdate}" />"
                           readonly="readonly">
                </div>

            </form>
            <!-- end form-wrap 개인신상-->
        </div>

        <!-- 학력 form-wrap -->
        <div class="form-wrap">
            <form name="education-form">
                <div class="form-row">
                    <div>
                        학력사항_그룹_번호
                    </div>
                    <input type="text" name='education_group_no'
                           value="${education.education_group_no}" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        학력사항_번호
                    </div>
                    <input type="text" name='education_no' value='${education.education_no}'>
                </div>
                <div class="form-row">
                    <div>
                        학력기간
                    </div>
                    <input type="text" name='education_term' value='${education.education_term}'>
                </div>
                <div class="form-row">
                    <div>
                        학교명
                    </div>
                    <input type="text" name='school_name' value='${education.school_name}'>
                </div>
                <div class="form-row">
                    <div>
                        졸업여부 -> 1 졸업, 2 수료, 3 재학
                    </div>
                    <input type="text" name='grated' value='${education.grated}'>
                </div>
                <div class="form-row">
                    <div>
                        등록일자
                    </div>
                    <input type="text" name='regdate'
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${education.regdate}" />"
                           readonly="readonly">
                </div>

            </form>
            <!-- end form-wrap 학력-->
        </div>

        <!-- 교육사항 form-wrap -->
        <div class="form-wrap">
            <form name="training-form">
                <div class="form-row">
                    <div>
                        교육연수_그룹_번호
                    </div>
                    <input type="text" name='training_group_no'
                           value="${training.training_group_no}" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        교육연수_번호
                    </div>
                    <input type="text" name='home_environment' value='${training.training_no}'>
                </div>
                <div class="form-row">
                    <div>
                        교육연수_기간
                    </div>
                    <input type="text" name='training_term' value='${training.training_term}'>
                </div>
                <div class="form-row">
                    <div>
                        교육연수명
                    </div>
                    <input type="text" name='training_name' value='${training.training_name}'>
                </div>
                <div class="form-row">
                    <div>
                        수료여부 -> radio 형태로
                    </div>
                    <input type="text" name='training_completion' value='${training.training_completion}'>
                </div>
                <div class="form-row">
                    <div>
                        등록일자
                    </div>
                    <input type="text" name='regdate'
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${training.regdate}" />"
                           readonly="readonly">
                </div>

            </form>
            <!-- end form-wrap 교육-->
        </div>

        <!-- 자기소개서 form-wrap -->
        <div class="form-wrap">
            <form name="persnalStatement-form">
                <div class="form-row">
                    <div>
                        <!-- 클릭하면 팝업창으로 수정하는 방법도 좋은것 같다. -->
                        자기소개서 선택
                    </div>
                    <!-- forEach를 사용해서 자소서 목록들을 options에 추가한다. -> 성능상의 이유로 잘 사용하지 않음-->
                    <select id="personalStatement-list" name="personalStatement-list">
                        <c:forEach items="${personalStatementList}" var="personalStatement" varStatus="s">
                            <option value="${personalStatement.personal_statement_no}">${personalStatement.personal_statement_no}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <div>
                        자기소개서_번호 -> 자기소개서 수정은 자기소개서 탭에서..
                    </div>
                    <input type="text" name='personal_statement_no'
                           value="${personalStatement.personal_statement_no}" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        가정환경
                    </div>
                    <textarea rows="5" cols="50" name='home_environment' readonly="readonly">${personalStatement.home_environment}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인장단점
                    </div>
                    <textarea rows="5" cols="50" name='pros_and_cons' readonly="readonly">${personalStatement.pros_and_cons}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인경험
                    </div>
                    <textarea rows="5" cols="50" name='experiance' readonly="readonly">${personalStatement.experiance}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        입사포부
                    </div>
                    <textarea rows="5" cols="50" name='job_espirations' readonly="readonly">${personalStatement.job_espirations}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        등록일자
                    </div>
                    <input type="text" name='regdate'
                           value='<fmt:formatDate pattern="yyyy-MM-dd" value="${personalStatement.regdate}" />'
                           readonly="readonly">
                </div>

            </form>
            <!-- end form-wrap 자소서-->
        </div>


        <!-- button-wrap -->
        <div class="button-wrap">
            <button type="submit" data-oper='modify' class="btn btn-default" >Modify</button>

            <button type="submit" data-oper='remove' class="btn btn-danger" >Remove</button>

            <button type="submit" data-oper='list' class="btn btn-info" >List</button>

            <!-- end button-wrap -->
        </div>

        <!-- end table-wrap -->
    </div>

</div>

<button id="test-btn" name="test-btn">test</button>



<%@include file="../includes/footer.jsp" %>
<script>
    $(document).ready(function(){

        var formObj = $("form[name='action-form']");

        // 테스트
        // $("#test-btn").on("click" , function(e){
        //
        //     fnModifyResume(formObj);
        // });

        $("button").on("click", function(e){
            // 버튼 클릭 당시의 값을 가져와야 한다.
            // jstl로 하지말고 다르게

            // 기존 submit 이벤트를 멈춘다.
            e.preventDefault();

            // button 에있는 data-oper를 읽어서 operation에 저장
            var operation = $(this).data("oper");

            console.log("operation: " + operation);

            if(operation === 'remove'){
                // POST - /resume/removePersonalStatement 으로 변경
                formObj.attr("action", "/resume/removeResume");

            }else if(operation === 'list'){
                // list로 이동 이동할때 페이징 관련 정보만 가지고 가야함
                formObj.attr("action", "/resume/resumeList").attr("method","get");

                var pageNumTag = $("input[name='pageNum']").clone();
                var amountTag = $("input[name='amount']").clone();

                // formObj 의 내용을 지운다. 태그는 남아있음, remove()와 다르다.
                formObj.empty();

                formObj.append(pageNumTag);
                formObj.append(amountTag);

            }else if(operation === 'modify'){
                // 폼내용들을 전부 가지고 Controller에 넘겨 줘야함

                // form에 실어 보낼 내용들을 formObj에 담는다.
                fnModifyResume(formObj);

            }

            formObj.submit();
            // end $("button").on("click", function(e){
        });

        // end $(document).ready(function(){
    });

    function fnModifyResume(obj){
        // action-form을 파라미터로 전달받아 업데이트에 필요한 내용들을 전부 담아 전달
        // 값을 전달하는 부분이 있으므로 테스트를 진행해서 값의 유효성을 체크해야한다.

        // input name에 있는것들 전부 긁어온다.
        // var pageNumTag = $("input[name='pageNum']").clone(); 와같은 식으로 clone 하고 obj form 에 append 한다.
        // 신상정보 -> 신상정보_no는 바뀌지 않는다.
        // + 추가 -> 안보이게 하기위해서 type을 hidden으로 둔다.
        // + 문제가 생겼을 경우에는 obj.empty() 를 이용해서 초기화시킨다. -> 일반적인 경우는 그냥 post 되므로 상관이 없다.

        // 문제가 있을 때 초기화를 위한 기본 Tag 들
        var pageNumTag = $("input[name='pageNum']").clone();
        var amountTag = $("input[name='amount']").clone();
        var resume_noTag = $("input[name='resume_no']").clone();

        obj.append($("input[name='personal_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='name_ko']").clone().attr("type", "hidden"));
        obj.append($("input[name='name_en']").clone().attr("type", "hidden"));
        obj.append($("input[name='name_cn']").clone().attr("type", "hidden"));
        obj.append($("input[name='birth']").clone().attr("type", "hidden"));
        obj.append($("input[name='address']").clone().attr("type", "hidden"));
        obj.append($("input[name='contact']").clone().attr("type", "hidden"));
        obj.append($("input[name='phone']").clone().attr("type", "hidden"));
        obj.append($("input[name='email']").clone().attr("type", "hidden"));

        // 순수 value 값만 가지고 오고 싶으면 이런식으로 하면 된다.
        // console.log($(testObj).val());
        obj.append($("input[name='education_group_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='education_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='education_term']").clone().attr("type", "hidden"));
        obj.append($("input[name='school_name']").clone().attr("type", "hidden"));
        obj.append($("input[name='grated']").clone().attr("type", "hidden"));


        // 교육정보 -> 교육정보_no는 바뀌지 않는다.
        obj.append($("input[name='training_group_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_term']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_name']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_completion']").clone().attr("type", "hidden"));

        // 자기소개서 번호 -> 자기소개서는 따로 update가 되지않고 resume에 자기소개서 번호만 추가되는식으로 update 된다.
        // var personal_statement_noTag = $("input[name='personal_statement_no']").clone();
        obj.append($("input[name='personal_statement_no']").clone().attr("type", "hidden"));

        console.log("----------- formObj 에 있는 html 텍스트 체크 -----------");
        console.log($(obj).html());
        console.log("---------------------------------------------------");

    //    -> 유효성 체크하고 싹다 한번에 컨트롤러로 보내서 컨트롤러에서 전부 처리한다.
    }

    // select box 클릭시 해당하는 번호의 자소서를 불러와서 뿌려주는 기능

    // 한글체크함수
    // 날짜체크함수
    // 전화번호체크함수
    // 이메일체크함수



</script>
</body>
</html>