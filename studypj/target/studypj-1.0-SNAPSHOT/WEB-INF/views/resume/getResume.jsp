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
<style type="text/css">
    .radio-wrap{
        display: flex;
        padding: 10px;
    }

    .radio-wrap label{
        display: flex;
    }

    .radio-wrap label span{
        width: 150px;
    }
</style>

<!-- 본문 -->
<div id="content">
    <div class="home">
        <span>
            이력서
        </span>
    </div>

    <div class="table-wrap">
        <!-- TEST -->
        <!-- 개인신상 form-wrap -->
        <div class="form-wrap">
            <form name="personal-form">
                <div class="form-row">
                    <div>
                        <!-- resumeAttach 가 있으면 ... -->
                        <c:choose>
                            <c:when test="${!empty resumeAttach}">
                                <img id="personal-img-exist" class="personal-img" src="#" height="200px" width="300px" style="margin:10px" onload="fnImgLoad();">
                            </c:when>
                            <c:otherwise>
                                <img class="personal-img" src="${pageContext.request.contextPath}/resources/img/attach.png" height="200px" width="300px" style="margin:10px">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="form-row" id="uploadDiv">
                    <div>
                        사진추가
                    </div>


                </div>
                <div class="form-row">
                    <div>
                        이름_한글
                    </div>
                    <input type="text" name='name_ko' value='${personal.name_ko}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        이름_영문
                    </div>
                    <input type="text" name='name_en' value='${personal.name_en}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        이름_한자
                    </div>
                    <input type="text" name='name_cn' value='${personal.name_cn}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        생년월일
                    </div>
                    <input type="text" name='birth' value='${personal.birth}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        주소
                    </div>
                    <input type="text" cols="50" name='address' value='${personal.address}' readonly="readonly">
                </div>

                <div class="form-row">
                    <div>
                        연락처
                    </div>
                    <input type="text" name='contact' value='${personal.contact}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        핸드폰
                    </div>
                    <input type="text" name='phone' value='${personal.phone}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        이메일
                    </div>
                    <input type="text" name='email' value='${personal.email}' readonly="readonly">
                </div>


            </form>
            <!-- end form-wrap 개인신상-->
        </div>

        <!-- 학력 form-wrap -->
        <div class="form-wrap">
            <form name="education-form">

                <div class="form-row">
                    <div>
                        학력사항_번호
                    </div>
                    <input type="text" name='education_no' value='${education.education_no}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        학력기간
                    </div>
                    <input type="text" name='education_term' value='${education.education_term}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        학교명
                    </div>
                    <input type="text" name='school_name' value='${education.school_name}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        졸업여부
                    </div>
                    <div class="radio-wrap">
                        <label><input type="radio" name='grated' value="1" checked="checked" onclick="return(false);"><span>졸업</span></label>
                        <label><input type="radio" name='grated' value="2" onclick="return(false);"><span>수료</span></label>
                        <label><input type="radio" name='grated' value="3" onclick="return(false);"><span>재학</span></label>
                    </div>
                </div>


            </form>
            <!-- end form-wrap 학력-->
        </div>

        <!-- 교육사항 form-wrap -->
        <div class="form-wrap">
            <form name="training-form">

                <div class="form-row">
                    <div>
                        교육연수_번호
                    </div>
                    <input type="text" name='home_environment' value='${training.training_no}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        교육연수_기간
                    </div>
                    <input type="text" name='training_term' value='${training.training_term}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        교육연수명
                    </div>
                    <input type="text" name='training_name' value='${training.training_name}' readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        수료여부
                    </div>
                    <div class="radio-wrap">
                        <label><input type="radio" name='training_completion' value="1" checked="checked" onclick="return(false);"><span>수료</span></label>
                        <label><input type="radio" name='training_completion' value="2" onclick="return(false);"><span>미수료</span></label>
                    </div>
                </div>

            </form>
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

    <c:if test="${!empty resumeAttach}">
        fnImgLoad();
    </c:if>

    function fnImgLoad(){
        var uploadPath = "${resumeAttach.uploadPath}";
        var uuid = "${resumeAttach.uuid}";
        var fileName = "${resumeAttach.fileName}";

        var fileCallPath = encodeURIComponent(uploadPath+ "/s_" + uuid + "_" + fileName);
        console.log("fileCallPath -> " + fileCallPath);

        $("#personal-img-exist").attr("src", "/display?fileName=" + fileCallPath);

    }
</script>

</body>
</html>
