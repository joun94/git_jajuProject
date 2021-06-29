<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/manager_css/managerBoard.css" />
</head>
<body>
<div style="width: 1080px; margin: auto;">
<jsp:include page="/manager/managerMenu.jsp"/>
<div id="content" class="cont_post post_spam">
		<div class="wrap_tit">
			<h3 class="cont_tit">
				<span class="tit_menu">게시판 관리</span>
			</h3>
			<div class="box_search">
				<div class="select_admin" style="font-size: 14px;">
				<select id="itemcd" name="itemcd">
					<option value="managerService">&nbsp;&nbsp;&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;&nbsp;</option>
					<option value="managerService">&nbsp;&nbsp;&nbsp;&nbsp;작성자&nbsp;&nbsp;&nbsp;&nbsp;</option>
				</select>
				</div>
				<div class="search_input">
					<input id="search-text" maxlength="20" size="25" class="tf_search textbox_default" title="검색어">
					<button type="button" class="btn_admin btn_search button-search">
						<span class="ico_admin">검색</span>
					</button>
				</div>
			</div>
		</div>
		<div class="option_list article_option_list">
			
		</div>

		<div class="set_list" id="listControlBar">
			<div class="bundle_set">
				<div class="txt_choice">
					<div class="check_admin check-all">
						<img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif" width="18" height="18" class="img_checkbox">
						<input type="checkbox" id="inpCheckAll" class="checkradio_styled">
						<label for="inpCheckAll" class="ico_ncheck hand">선택 됨/선택 안됨</label>
					</div>
					선택한 글
				</div>
				<div class="select_admin select_post">
					<select id="boardMenu" name="boardMenu" style="width: 250px; height: 32px;">
						<option value="">&nbsp;&nbsp;게시판 전체&nbsp;&nbsp;&nbsp;&nbsp;</option>
						<option value="boardMenu">&nbsp;&nbsp;작성자&nbsp;&nbsp;&nbsp;&nbsp;</option>
					</select>
				</div>
				<span class="txt_g">(으)로</span>
				<button type="button" class="btn_admin btn_type1 button-move-article">
					<span class="ico_admin ico_move"></span>
					<span class="txt_btn">이동</span>
				</button>
			</div>
			<div class="bundle_set">
				<button type="button" class="btn_admin btn_type2 button-delete-article">
					<span class="ico_admin ico_del"></span>
					<span class="txt_btn">삭제</span>
				</button>
				<button type="button" class="btn_admin btn_type2 button-spam-article">
					<span class="ico_admin ico_caution"></span>
					<span class="txt_btn">스팸처리</span>
				</button>
			</div>
		</div>

		<div id="admin_listview">
			<table class="tbl_admin" summary="게시글 목록입니다. 제목, 글쓴이, 작성일, 조회수를 제공합니다.">
				<caption class="ir_caption">게시글 관리 목록</caption>
				<colgroup>
					<col width="48px">
					<col width="150px" class="fldname">
					<col width="100%" class="title">
					<col width="140px" class="writer">
					<col width="100px">
					<col width="90px">
				</colgroup>
				<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col" class="fldname">게시판명</th>
					<th scope="col" class="title">제목</th>
					<th scope="col" class="writer">글쓴이</th>
					<th scope="col" class="num_g">작성일</th>
					<th scope="col" class="num_g">조회수</th>
				</tr>
				</thead>
				<tbody id="article-list"><tr data-fldid="XE2F" data-dataid="1" data-userid="tQnQeh9UolU0" data-issueregyn="N" data-espam="U.yriMXOi5QP6BrTtqLKGfTebxBv_dbcctf3Kla.TRdEiu9ebEPaHey.p1F7_WsE" data-nickname="윤마녀" data-read-url="/_c21_/bbs_read?grpid=1Z5aM&amp;fldid=XE2F&amp;page=1&amp;contentval=00001zzzzzzzzzzzzzzzzzzzzzzzzz&amp;datanum=1">
	<td class="chk">
		<img src="//t1.daumcdn.net/cafe_image/cf_img2/img_blank2.gif" width="18" height="18" class="img_checkbox">
		<input type="checkbox" name="article" title="해당 리스트 선택" class="checkradio_styled">
	</td>
	<td class="folderName">
		<div class="table_data">
			<span>우리들의 이야기</span>
		</div>
	</td>
	<td>
		<div class="table_data">
			<a href="/_c21_/bbs_read?grpid=1Z5aM&amp;fldid=XE2F&amp;page=1&amp;contentval=00001zzzzzzzzzzzzzzzzzzzzzzzzz&amp;datanum=1" class="datanamelink" target="_blank" rel="noopener">가입 했습니다. 혹시 자격증 자료들 필요..</a>
		</div>
	</td>
	<td>
		<div class="table_data">
			<a href="#" class="username">윤마녀</a>
		</div>
	</td>
	<td class="num_g">2021.05.30</td>
	<td class="num_g">6</td>
</tr></tbody>
			</table>
		</div>

<div id="pagingAreaPg" class="page_admin"></div>

		<div id="pagingArea" class="page_admin">
			<ul class="bundle_page">
				<li class="btn_page btn_prev page_disabled">
					<a href="#" class="page-link"><span class="ico_admin"></span> 이전</a>
				</li>
				<li class="page-item on">
					<a href="#" class="page-link" style="font-size: 13px; text-align: center;">1</a>
				</li>
				<li class="btn_page btn_next page_disabled">
					<a href="#" class="page-link">다음 <span class="ico_admin"></span></a>
				</li>
			</ul>
		</div>
		<div id="article-summary-layer" class="layer_post summary-layer"></div>
	</div>
</div>
</body>
</html>