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
        <!-- 개인신상 form-wrap -->
        <div class="form-wrap">
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

            <!-- end form-wrap 개인신상-->
        </div>

        <!-- 학력 form-wrap -->
        <div class="form-wrap">
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

            <!-- end form-wrap 학력-->
        </div>

        <!-- 교육사항 form-wrap -->
        <div class="form-wrap">
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


            <!-- end form-wrap 교육-->
        </div>

        <!-- 자기소개서 form-wrap -->
        <div class="form-wrap">
            <div class="form-row">
                <div>
                    자기소개서_번호
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


            <!-- end form-wrap 자소서-->
        </div>


        <!-- button-wrap -->
        <div class="button-wrap">
            <button data-oper='modify' class="btn btn-default">Modify</button>

            <button data-oper='list' class="btn btn-info">List</button>

            <form id='operForm' action="/resume/modifyResume" method="get">
                <input type='hidden' id='resume_no' name='resume_no'
                       value="${resume.resume_no}">
                <input type='hidden' name='pageNum' value='${cri.pageNum}'>
                <input type='hidden' name='amount' value='${cri.amount}'>
            </form>
            <!-- end button-wrap -->
        </div>

    <!-- end table-wrap -->
    </div>

</div>



<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){

        var operForm = $("#operForm");

        // mddify 버튼 클릭시 operForm을 submit()
        $("button[data-oper='modify']").on("click", function(){

            operForm.submit();
        });

        // list 버튼 클릭시 이동 operForm을 submit(), action 속성 수정
        $("button[data-oper='list']").on("click", function(){

            // list로 이동할때는 resume_no 삭제
            operForm.find("#resume_no").remove();
            operForm.attr("action", "/resume/resumeList");
            operForm.submit();
        });

    });

</script>

</body>
</html>
