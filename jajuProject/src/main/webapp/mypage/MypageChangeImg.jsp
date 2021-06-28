<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 이미지 변경</title>
<link rel="stylesheet" href="/jaju/css/myPageChangeImg.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous"><style type="text/css">
#member_image{
    width: 0.2px;
   height: 0.2px;
   opacity: 0;
   overflow: hidden;
   position: absolute;
   z-index: -1;
}

#member_image + label {
    border: 1px solid #d9e1e8;
    background-color: #fff;
    color: #617aad;
    border-radius: 2rem;
    padding: 8px 15px 8px 17px;
    font-weight: 500;
    font-size: 15px;
    box-shadow: 1px 2px 3px 0px #f2f2f2;
    outline: none;
    margin-left: -10px;
}

#member_image:focus + label,
#member_image + label:hover {
    cursor: pointer;
}
.fa-file-image{
   font-size: 18px;
   margin-right: 6px;
}
</style>
</head>
<body>
<!-- 프로필 이미지 변경 (간단한 정보 변경?) -->

   <form name="myPageChangeImgForm"  id="myPageChangeImgForm">
   
      <input type="hidden" name="pg" id="pg" value="${pg}" />
      <input type="hidden" name="id" id="id" value="${memId}" />

      <h1 id="profile_name" align="center" >프로필 변경</h1>

      <table border="0" id="table" cellspacing="0" cellpadding="10" bordercolor="#fff" align="center" 
         width="450px" height="550px" frame="hsides" rules="rows">

         <tr>
            <td rowspan="4" class="image" align="center" style="margin-top:5px; margin-bottom:5px;">
            
            <img id="image1"  width="180px" height="180px"></td>
            <td>이름</td>
            <td width="90px"><span id="member_name_span"></span></td>
         </tr>

         <tr>
            <td>매너온도</td>
            <td><span id="member_manner_span"></span></td>
         </tr>

         <tr>
            <td>현재지역</td>
            <td><span id="member_location_span"></span></td>
         </tr>

         <tr>
            <!-- <td>이미지 변경 :<span id="totalSpan"></span></td> -->
            <td colspan="3" width="100%" >
                 <input type="file" name="member_image" id="member_image" />
                  <label for="member_image"><i class="far fa-file-image"></i>&nbsp;파일 선택</label>
               </td>
         </tr>

         <tr>
            <td colspan="3" align="center" height="250px">
            <span id="saleContentSpan">내 판매 내역 추가 할 예정</span>
            </td>
         </tr>

         <tr>
            <td colspan="3" align="center">
            <button id="update_btn" >수정하기</button></td>
         </tr>
      </table>
   </form>
   
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/myPageChangeImg.js"></script>

</body>
</html>