<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/manager_css/managerMember.css" />
</head>
<body>

<div style="width: 1080px; margin: auto;">

<jsp:include page="/manager/managerMenu.jsp"/>

<div id="framecontent">

<input type="hidden" name="pg" id="pg" value="">

	<div id="content" class="cont_post member_admin">
	
		<div class="wrap_tit">
			
			<h3 class="cont_tit">
				<span class="tit_menu">전체회원/운영진</span>
			</h3>
			
			<form name="searchform" onsubmit="">
			
				<div class="box_search">
					<div class="select_admin">
						<select id="searchMode" fixedsize="110" class="selectbox_styled">
							<option value="3" selected="selected">닉네임+이메일</option>
							<option value="2">닉네임</option>
							<option value="1">이메일</option>
						</select>
						
						<a id="searchMode_img" class="img_selectbox" tabindex="0" style="width: 90px;">닉네임+이메일</a>
					</div>
					
					<div class="search_input">
						<input id="search-text" maxlength="20" size="25" class="tf_search textbox_default" title="검색어">
						<button type="button" class="btn_admin btn_search button-search">
							<span class="ico_admin">검색</span>
						</button>
					</div>
				</div><!-- box_search -->
			</form>
		</div>


		<div class="box_noti" id="menuInfoArea" style="display:none;"><!--  -->
		
				<div class="bundle_radio">
				
					<div class="radio_admin">
						<img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif" width="14" height="14" class="img_radiobtn rb_checked">
						<input	type="radio" name="detail_search" id="radioArticle"	value="article" checked="checked" class="checkradio_styled"> 
						<label for="radioArticle" class="lab_radio hand">게시글, 댓글 수</label>
					</div>
					
					<div class="radio_admin">
						<img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif"	width="14" height="14" class="img_radiobtn">
						<input	type="radio" name="detail_search" id="radioVisit" value="visit"	class="checkradio_styled"> 
						<label for="radioVisit"	class="lab_radio hand">방문일</label>
					</div>
					
					<div class="radio_admin">
						<img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif"	width="14" height="14" class="img_radiobtn">
						<input type="radio" name="detail_search" id="radioTerm" value="term" class="checkradio_styled"> 
						<label for="radioTerm" class="lab_radio hand">기간</label>
					</div>
				</div>
			</div>
		</div>

			<!-- tabbox_admin 탭, 전체회원 / 활동중지 회원 나눠 진 곳.  -->
			<div class="tabbox_admin">
				<ul class="tab_nav">
					
					<li class="on"><a href="/_c21_/founder_member_admin_v2?grpid=1Z5aM" class="link_tab">
						<span class="screen_out">선택됨 </span>전체 회원</a>
					</li> <!-- 선택된 탭에 on 클래스 부여 및 선택됨 대체텍스트 제공 -->

					<li>
						<a href="#" class="link_tab">활동중지 회원</a>
					</li>
					
				</ul>
			</div> <!--// admin_tab -->

			<div class="set_list" id="listControlBar">
			</div>
		
			<!-- 전체회원관리 (append 되는 곳) -->
			<div id="admin_listview">
			
			<!-- 상단에 회원 정보 수정하는 거로 바꿈 -->
			<div class="set_list" id="listControlBar">
				<div class="bundle_set">
					<div class="txt_choice">
						<!--<div class="check_admin">
							 <img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif" width="18" height="18" class="img_checkbox"><input type="checkbox" id="inpCheckAll" onclick="boardManager.toggleAllCheck(this);" class="checkradio_styled">
						</div> -->
						<span class="txt_inner">선택한 회원 아이디 : </span>
					</div>
					
					<span class="txt_g" id="input_checked_id_span">여기 값 들어오는 곳</span>
					<button type="button" class="btn_admin btn_type1" id="btnGradeChange">
						<span class="ico_admin ico_change"></span><span class="txt_btn">변경</span>
					</button>
				</div>
				
				<div class="bundle_set">
					<button type="button" class="btn_admin btn_type2" id="btnWithdraw">
						<span class="ico_admin ico_caution"></span>
						<span class="txt_btn" id="again_member_id">탈퇴 철회</span>
					</button>

				</div>
			</div>
			
			<table class="tbl_admin tbl_type3"  id="adminTable" summary="전체회원 목록입니다. 닉네임, 회원등급, 가입일, 최종방문일, 방문수, 게시글수를 제공합니다.">
				<caption class="ir_caption">전체회원 관리 목록</caption>
				<thead id="memberListHead">
					<tr>
				
						<th class="lineL chk" style="width: 38px;">
							<input type="checkbox" id="all" name="all"/>
						</th>
				
						<th class="member_writer" style="width:90px; text-align:center;">아이디</th>
						
						<th class="member_email" style="width: 120px; text-align:center;">이메일</th>
						
						<th class="txt_al" style="width: 120px; text-align:center;">회원등급</th>
						
						<th id="regdt" style="width: 90px;">
							<label class="btn_admin btn_date">
				                   <span class="txt_btn">가입일
				                       <span class="ico_admin"></span>
				                   </span>
								<input type="button" class="btn_g" value="가입일">
							</label>
						</th>
						
						<th id="lastcntdt" class="hidecell" style="width: 90px;">
							<label class="btn_admin btn_date">
				                   <span class="txt_btn">최종방문일
				                       <span class="ico_admin ico_disabled"></span>
				                   </span>
								<input type="button" class="btn_g" value="최종방문일">
							</label>
						</th>
						
						<th id="visit" style="width: 90px;">
							<label class="btn_admin btn_date">
				                   <span class="txt_btn">매너온도
				                       <span class="ico_admin ico_disabled"></span>
				                   </span>
								<input type="button" class="btn_g" value="방문수">
							</label>
						</th>
						
						<th id="article" style="width: 90px;" >
							<label class="btn_admin btn_date">
				                   <span class="txt_btn">판매글수
				                       <span class="ico_admin ico_disabled"></span>
				                   </span>
								<input type="button" class="btn_g" value="게시글수">
							</label>
						</th>
						
						<th id="comment" style="width: 90px;">
							<label class="btn_admin btn_date">
				                   <span class="txt_btn">구매글수
				                       <span class="ico_admin ico_disabled"></span>
				                   </span>
								<input type="button" class="btn_g" value="댓글수">
							</label>
						</th>
						
						<!--
						<th class="bar hidecell" style="width: 60px;">성별</th>
						<th class="txt_al hidecell" style="width: 140px;">가입시 답변</th>
						<th class="bar hidecell" style="width: 60px;">메일수신</th>
						<th class="bar hidecell" style="width: 60px;">쪽지수신</th>
						<th class="bar hidecell" style="width: 80px;">정보공개</th>
						<th class="bar hidecell" style="width: 90px;">등업대기 등급</th>
						<th class="bar hidecell" style="width: 90px;">등급변경일</th>
						<th class="txt_al hidecell" style="width: 120px;">처리자</th>
						<th class="writer hidecell" style="width: 120px;">초대자(계정)</th>
						<th scope="col" style="width: 39px;">-->
					</tr>
				</thead>
						
						<!-- ========= 여기가 append 되는 곳  ========= -->
						
						<!-- tbody, tr, td/td/td/td/td/td/td -->
						<tbody id="memberListBody">
						<!-- 	<tr>
								<td class="chk">
									<input type="checkbox" class="checkradio_styled">
								</td>
								
						        <td>
						         <div class="table_data">
						          <a class="user_info" href="#" onclick="">여기에 아이디 append</a>
						          <span class="email_tooltip">이메일 append</span>
						         </div>
						        </td>
						        
								<td><div class="table_data">회원</div></td>
								<td class="signin_date_span">가입일 append</td>
								<td class="member_manner_span">매너온도 append</td>
								<td class="member_sell_span">판매게시글 수  append</td>
								<td class="num_buy_span">구매게시글 수 append </td>
							</tr> -->
						</tbody>
			</table>
		</div>


			<div class="set_list" id="listControlBar2" style="background: #FAFAFA;height: 40px;">
				<!-- 
				<div class="bundle_set">
					<div class="txt_choice">
						<div class="check_admin">
							<input type="checkbox" id="inpCheckAll2" class="checkradio_styled">
					</div>
					<span class="txt_inner">선택한 회원</span>
				</div>
				
				<div class="select_admin select_post">
				
					<button type="button" class="btn_admin btn_type1" id="btnGradeChange2">
						<span class="ico_admin ico_change"></span><span class="txt_btn">여기다가 선택한 회원 아디 텍스트로 append</span>
					</button>
						
				</div>
			
			</div>
				
				
				<div class="bundle_set">
					
					<button type="button" class="btn_admin btn_type2" id="btnDeactivate2">
						<span class="ico_admin ico_caution"></span><span class="txt_btn">활동중지</span>
					</button>
					<button type="button" class="btn_admin btn_type2" id="btnWithdraw2">
						<span class="ico_admin ico_caution"></span><span class="txt_btn">강제탈퇴</span>
					</button>
					
				</div> -->
				
			</div>

			<div id="pagingArea" class="page_admin">
			
				<a class="btn_page btn_prev page_disabled">
					<span class="ico_admin"></span>&nbsp;이전
				</a>
				
				<ul class="bundle_page"><li class="on"><a>페이징처리하는곳</a></li></ul>
				
				<a class="btn_page btn_next page_disabled">다음&nbsp;
					<span class="ico_admin"></span>
				</a>
			</div>

           <div id="accountNotiLayer">
               <ul class="account_noti">
                   <li>마우스 포인터를 닉네임(계정) 위에 올리면 전체 메일 주소가 나타납니다.</li>
                   <li>회원을 검색할 때, 전체 메일 주소를 입력해야만 검색 결과가 노출됩니다.</li>
                   <li>회원이 대표 메일을 변경하면 카페 내 메일 주소도 함께 변경됩니다.</li>
               </ul>
           </div>
           <div id="articleSummaryLayer" class="layer_post" style="top: -10000px; left: -10000px; visibility: hidden;"></div>
	</div><!-- end content -->
</div>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
	<script src="/jaju/manager_js/managerMember.js"></script>
</body>
</html>