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
            이력서 등록하기
        </span>
    </div>

    <div class="table-wrap">
        <!-- 실제 action을 담당하는 form -->
        <div id="action-from-wrap">
            <form id="action-form" name="action-form" method="post" action="/resume/registerResume">
                <input type='hidden' id='personal_no' name='personal_no' value='${personal.personal_no}'>
                <input type="hidden" name='education_group_no' value='${education.education_group_no}'>
                <input type="hidden" name='training_group_no' value='${training.training_group_no}'>

            </form>
        </div>

        <!-- 개인신상 form-wrap -->
        <div class="form-wrap">
            <form name="personal-form">
                <div class="form-row">
                    <div>
                        <img class="personal-img" src="${pageContext.request.contextPath}/resources/img/attach.png" height="200px" width="300px" style="margin:10px">
                    </div>
                </div>
                <div class="form-row" id="uploadDiv">
                    <div>
                        사진추가
                    </div>
                    <input type="file" name='uploadPic'>
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
                        졸업여부
                    </div>
                    <div class="radio-wrap">
                        <label><input type="radio" name='grated' value="1" checked="checked"><span>졸업</span></label>
                        <label><input type="radio" name='grated' value="2" ><span>수료</span></label>
                        <label><input type="radio" name='grated' value="3" ><span>재학</span></label>
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
                    <input type="text" name='training_no' value='${training.training_no}'>
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
                        수료여부
                    </div>
                    <div class="radio-wrap">
                        <label><input type="radio" name='training_completion' value="1" checked="checked"><span>수료</span></label>
                        <label><input type="radio" name='training_completion' value="2" ><span>미수료</span></label>
                    </div>
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
                    <!-- forEach를 사용해서 자소서 목록들을 options에 추가한다. -> 성능상의 이유로 잘 사용하지 않음-->
                    <select id="personalStatement-list" name="personalStatement-list" onchange="changeSelect()">
                        <option value="none">=== 선택 ===</option>
                        <c:forEach items="${personalStatementList}" var="personalStatement" varStatus="s">
                            <option value="${personalStatement.personal_statement_no}">${personalStatement.personal_statement_no}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-row">
                    <div>
                        자기소개서_번호
                    </div>
                    <input type="text" name='personal_statement_no'
                           value="" readonly="readonly">
                </div>
                <div class="form-row">
                    <div>
                        가정환경
                    </div>
                    <textarea rows="5" cols="50" name='home_environment' readonly="readonly"></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인장단점
                    </div>
                    <textarea rows="5" cols="50" name='pros_and_cons' readonly="readonly"></textarea>
                </div>
                <div class="form-row">
                    <div>
                        본인경험
                    </div>
                    <textarea rows="5" cols="50" name='experiance' readonly="readonly"></textarea>
                </div>
                <div class="form-row">
                    <div>
                        입사포부
                    </div>
                    <textarea rows="5" cols="50" name='job_espirations' readonly="readonly"></textarea>
                </div>

            </form>
            <!-- end form-wrap 자소서-->
        </div>


        <!-- button-wrap -->
        <div class="button-wrap">
            <button type="submit" data-oper='register' class="btn btn-default"> Register</button>

            <button type="submit" data-oper='list' class="btn btn-info" >List</button>

            <!-- end button-wrap -->
        </div>

        <!-- end table-wrap -->
    </div>

</div>

<%@include file="../includes/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
    $(document).ready(function(){

        var formObj = $("form[name='action-form']");

        $(".btn").on("click", function(e){
            // 버튼 클릭 당시의 값을 가져와야 한다.
            // jstl로 하지말고 다르게

            // 기존 submit 이벤트를 멈춘다.
            e.preventDefault();

            // button 에있는 data-oper를 읽어서 operation에 저장
            var operation = $(this).data("oper");

            console.log("operation: " + operation);

            if(operation === 'list'){
                // POST - /resume/removePersonalStatement 으로 변경
                formObj.attr("action", "/resume/resumeList").attr("method","get");

            }else if(operation === 'register'){

                // action-form 에 register를 하기위한 정보를 모두 담는다.
                fnRegisterResume(formObj);

                // 사진 관련된 정보들 form에 담아서 전달하기

            }

            formObj.submit();
            // end $("button").on("click", function(e){
        });


        // end $(document).ready(function(){
    });



        // select 가 클릭되면 해당하는 resume의 값을 가져온다.
    function changeSelect(){

        var selectNo = document.getElementById("personalStatement-list");
        // select element에서 선택된 option의 value가 저장된다.
        var selectValue = selectNo.options[selectNo.selectedIndex].value;

        // // select element에서 선택된 option의 text가 저장된다.
        // var selectText = selectNo.options[langSelect.selectedIndex].text;

        console.log("value -> " + selectValue);

        // Ajax로 값을 Json 형식으로 요청하고 그 값을 자소서 란에 작성한다.
        $.ajax({
            url: '${pageContext.request.contextPath}/resume/getPersonalStatementAjax',
            data: {
                "personal_statement_no": selectValue
            },
            dataType: 'json',
            type: 'GET',
            success: function(data){
                console.log("getPersonalStatementAjax Ajax Success");
                // console.log(data);
                // console.log(data.personal_statement_no);
                // console.log(data.home_environment);
                // console.log(data.pros_and_cons);
                // console.log(data.experiance);
                // console.log(data.job_espirations);

                $("input[name='personal_statement_no']").val(data.personal_statement_no);
                $("textarea[name='home_environment']").val(data.home_environment);
                $("textarea[name='pros_and_cons']").val(data.pros_and_cons);
                $("textarea[name='experiance']").val(data.experiance);
                $("textarea[name='job_espirations']").val(data.job_espirations);

            },
            error: function(request, status, error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

            // end Ajax
        });

        // end function changeSelect
    }



    function fnRegisterResume(obj){
        // action-form을 파라미터로 전달받아 업데이트에 필요한 내용들을 전부 담아 전달
        // 값을 전달하는 부분이 있으므로 테스트를 진행해서 값의 유효성을 체크해야한다.

        // input name에 있는것들 전부 긁어온다.
        // var pageNumTag = $("input[name='pageNum']").clone(); 와같은 식으로 clone 하고 obj form 에 append 한다.
        // 신상정보 -> 신상정보_no는 바뀌지 않는다.
        // + 추가 -> 안보이게 하기위해서 type을 hidden으로 둔다.
        // + 문제가 생겼을 경우에는 obj.empty() 를 이용해서 초기화시킨다. -> 일반적인 경우는 그냥 post 되므로 상관이 없다.

        // 문제가 있을 때 초기화를 위한 기본 Tag 들

        var personal_noTag = $("input[name='personal_no']").clone();
        var education_group_noTag = $("input[name='education_group_no']").clone();
        var training_group_noTag = $("input[name='training_group_no']").clone();

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
        var grated = $("input[name='grated']:checked").val();
        obj.append("<input type='hidden' name='grated' value='" + grated + "'>");
        // obj.append($("input[name='grated']").clone().attr("type", "hidden"));


        // 교육정보 -> 교육정보_no는 바뀌지 않는다.
        obj.append($("input[name='training_group_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_no']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_term']").clone().attr("type", "hidden"));
        obj.append($("input[name='training_name']").clone().attr("type", "hidden"));
        // obj.append($("input[name='training_completion']").clone().attr("type", "hidden"));
        var training_completion = $("input[name='training_completion']:checked").val();
        obj.append("<input type='hidden' name='training_completion' value='" + training_completion + "'>");

        // 자기소개서 번호 -> 자기소개서는 따로 update가 되지않고 resume에 자기소개서 번호만 추가되는식으로 update 된다.
        // var personal_statement_noTag = $("input[name='personal_statement_no']").clone();
        obj.append($("input[name='personal_statement_no']").clone().attr("type", "hidden"));
        obj.append($("textarea[name='home_environment']").clone().attr("type", "hidden"));
        obj.append($("textarea[name='pros_and_cons']").clone().attr("type", "hidden"));
        obj.append($("textarea[name='experiance']").clone().attr("type", "hidden"));
        obj.append($("textarea[name='job_espirations']").clone().attr("type", "hidden"));

        console.log("----------- formObj 에 있는 html 텍스트 체크 -----------");
        console.log($(obj).html());
        console.log("---------------------------------------------------");

        //    -> 유효성 체크하고 싹다 한번에 컨트롤러로 보내서 컨트롤러에서 전부 처리한다.
    }

    // select box 클릭시 해당하는 번호의 자소서를 불러와서 뿌려주는 기능

    // TODO: 각종 유효성 체크
    // 한글체크함수
    // 날짜체크함수
    // 전화번호체크함수
    // 이메일체크함수



    // 이력서 사진관련처리
    // 이미지 확장자만 처리
    var regex = new RegExp("(.*?)\.(jpg|gif|png|bmp|svg|jpeg|jfif)$");
    var maxSize = 5242880; // 5MB

    function checkExtension(fileName, fileSize){
        if(fileSize >= maxSize){
            alert("파일 사이즈 초과");
            return false;
        }

        if(!regex.test(fileName)){
            alert("해당 종류의 파일은 업로드 할 수 없습니다");
            return false;
        }

        return true;
    }

    // 파일의 변화가 있으면 change, -> 기존파일의 삭제도 같이 진행
    $("input[type='file']").change(function(e){

        var formData = new FormData();
        var inputFile = $("input[name='uploadPic']");
        var files = inputFile[0].files;

        console.log(files);

        for(var i=0; i < files.length; i++){
            if(!checkExtension(files[i].name, files[i].size)){
                return false;
            }

            formData.append("uploadFile", files[i]);
        }

        console.log(formData);

        //기존 파일이 있다면 삭제를 먼저 진행
        if($("input[name='uploadPath']").length>0){
            deleteFile();
        }

        // processData와 contentType은 false로 지정해줘야 파일 전송이 가능

        $.ajax({
            url: '/uploadAjaxAction',
            processData: false,
            contentType: false,
            data: formData,
            type: 'post',
            success: function(result){
                console.log("Uploaded");
                showUploadResult(result);

            },
            error: function(request, status, error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

            // end Ajax
        });

        // end file change
    });


    // 사진 등록
    function fnRegisterPic() {


    }

    // 업로드된 파일의 정보를 읽어서 form에 정보를 담고, 섬네일을 출력하는 함수
    function showUploadResult(uploadResultArr){
        var formObj = $("form[name='action-form']");

        if(!uploadResultArr || uploadResultArr.length == 0) { return; }

        var str = "";

        $(uploadResultArr).each(function(i, obj){

            // image type
            var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

            // 이미지 src를 변경
            $(".personal-img").attr("src", "/display?fileName=" + fileCallPath);

            str += "<input type='hidden' name='fileName' value='" + obj.fileName + "'>";
            str += "<input type='hidden' name='uuid' value='" + obj.uuid + "'>";
            str += "<input type='hidden' name='uploadPath' value='" + obj.uploadPath + "'>";
            str += "<input type='hidden' name='fileType' value='true'>";

        });

        formObj.append(str);

    }   // end showUploadResult function


    // 이미지를 변경했을 때 기존의 파일을 삭제하는 함수
    function deleteFile(){

        console.log("delete file");

        var fileCallPath = encodeURIComponent( $("input[name='uploadPath']").val()
            + "/s_" + $("input[name='uuid']").val() + "_" + $("input[name='fileName']").val());

        var targetFile = fileCallPath;

        // image 타입만 처리
        var type = "image";

        $.ajax({
            url: '/deleteFile',
            data: {fileName: targetFile, type: type},
            dataType: 'text',
            type: 'POST',
            success: function(result){
                console.log(result);
                $("input[name='fileName']").remove();
                $("input[name='uuid']").remove();
                $("input[name='uploadPath']").remove();
                $("input[name='fileType']").remove();
            } // success
        }); // end ajax

    } // end function deleteFile()

</script>
</body>
</html>
