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
        <form id="action-form" name="action-form" method="post">
            <input type='hidden' id='resume_no' name='resume_no' value='${resume.resume_no}'>
            <input type='hidden' name='pageNum' value='${cri.pageNum}'>
            <input type='hidden' name='amount' value='${cri.amount}'>
        </form>

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
                    <textarea rows="5" cols="50" name='home_environment' readonly="readonly">${personal.name_ko}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        이름_영문
                    </div>
                    <textarea rows="5" cols="50" name='name_en' readonly="readonly">${personal.name_en}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        이름_한자
                    </div>
                    <textarea rows="5" cols="50" name='name_cn' readonly="readonly">${personal.name_cn}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        생년월일
                    </div>
                    <textarea rows="5" cols="50" name='birth' readonly="readonly">${personal.birth}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        주소
                    </div>
                    <textarea rows="5" cols="50" name='address' readonly="readonly">${personal.address}</textarea>
                </div>

                <div class="form-row">
                    <div>
                        연락처
                    </div>
                    <textarea rows="5" cols="50" name='contact' readonly="readonly">${personal.contact}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        핸드폰
                    </div>
                    <textarea rows="5" cols="50" name='phone' readonly="readonly">${personal.phone}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        이메일
                    </div>
                    <textarea rows="5" cols="50" name='email' readonly="readonly">${personal.email}</textarea>
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
                    <textarea rows="5" cols="50" name='education_no' readonly="readonly">${education.education_no}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        학력기간
                    </div>
                    <textarea rows="5" cols="50" name='education_term' readonly="readonly">${education.education_term}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        학교명
                    </div>
                    <textarea rows="5" cols="50" name='school_name' readonly="readonly">${education.school_name}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        졸업여부
                    </div>
                    <textarea rows="5" cols="50" name='grated' readonly="readonly">${education.grated}</textarea>
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
                    <textarea rows="5" cols="50" name='home_environment' readonly="readonly">${training.training_no}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        교육연수_기간
                    </div>
                    <textarea rows="5" cols="50" name='training_term' readonly="readonly">${training.training_term}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        교육연수명
                    </div>
                    <textarea rows="5" cols="50" name='training_name' readonly="readonly">${training.training_name}</textarea>
                </div>
                <div class="form-row">
                    <div>
                        수료여부
                    </div>
                    <textarea rows="5" cols="50" name='training_completion' readonly="readonly">${training.training_completion}</textarea>
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
                        자기소개서 선택
                    </div>
                    <select id="personalStatement-list" name="personalStatement-list">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
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



<%@include file="../includes/footer.jsp" %>
<script>
    $(document).ready(function(){

        var formObj = $("form[name='action-form']");

        $("button").on("click", function(e){

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