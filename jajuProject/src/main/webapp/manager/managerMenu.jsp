<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/manager_css/managerMenu.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="side">
<ul class="list_lnb">
	<li class="lnb_admin1">
		<strong><span class="ico_admin"></span> <span class="txt_lnb">운영/관리</span></strong>
		<ul>
			<li id="founder_standard_v2"><a href="/_c21_/founder_standard_v2?grpid=1Z5aM" class="link_lnb">통계</a></li>
		</ul>
	</li>
	<li class="lnb_admin2">
		<strong><span class="ico_admin"></span> <span class="txt_lnb">회원 관리</span></strong>
		<ul>
			<li id="founder_member_admin_v2"><a href="/_c21_/founder_member_admin_v2?grpid=1Z5aM" class="link_lnb">회원 관리</a></li>
			<li id="founder_bbsAdminMgtForm_v2"><a href="/_c21_/founder_bbsAdminMgtForm_v2?grpid=1Z5aM" class="link_lnb">블랙리스트 관리</a></li>
		</ul>
	</li>
	<li class="lnb_admin3">
		<strong><span class="ico_admin"></span> <span class="txt_lnb">게시판 관리</span></strong>
		<ul>
			<li id="founder_articlemanagement" class="on"><a href="/_c21_/founder_articlemanagement?grpid=1Z5aM" class="link_lnb">판매게시판 관리</a></li>
			<li id="founder_commentmanagement"><a href="/_c21_/founder_commentmanagement?grpid=1Z5aM" class="link_lnb">리뷰게시판 관리</a></li>
			<li id="founder_noticemanagement"><a href="/_c21_/founder_noticemanagement?grpid=1Z5aM" class="link_lnb">자유게시판 관리</a></li>
		</ul>
	</li>
		<li class="lnb_admin4">
		<strong><span class="ico_admin"></span> <span class="txt_lnb">고객센터 관리</span></strong>
		<ul>
			<li id="founder_articlemanagement" class="on"><a href="/_c21_/founder_articlemanagement?grpid=1Z5aM" class="link_lnb">공지사항 관리</a></li>
			<li id="spamArticles"><a href="/_c21_/manage/spamArticles?grpid=1Z5aM" class="link_lnb">자주묻는질문 관리</a></li>
			<li id="founder_articlemanagement" class="on"><a href="/_c21_/founder_articlemanagement?grpid=1Z5aM" class="link_lnb">1:1 문의 관리</a></li>
			<li id="spamArticles"><a href="/_c21_/manage/spamArticles?grpid=1Z5aM" class="link_lnb">신고게시판 관리</a></li>
		</ul>
	</li>
</ul>
<script>
(function($) {
	var re = new RegExp( "/_c21_/([_0-9a-zA-Z]+)/([_0-9a-zA-Z]+)" );
	var re_old = new RegExp( "/_c21_/([_0-9a-zA-Z]+)" );
	var match = re.exec( document.location.href );
	var match_old = re_old.exec( document.location.href );
	var menuUrl, menuUrl2;

	if ( null != match && 2 < match.length) {
		menuUrl = match[1];
		menuUrl2 = match[2];
	} else if ( null != match_old) {
		menuUrl = match_old[1];
	}

	if (menuUrl === "founder_group_mail") {
		$("#founder_group_mail_group_msgbox").addClass('on');
		return;
	}
	
	if (menuUrl == "invite_v2" ||  menuUrl == "founder_daummsg_write" || menuUrl == "founder_daummsg_all_v2") {
		menuUrl = "founder_group_mail_group_msgbox";
	} else if (menuUrl == "founder_selfdesign_v2") {
		menuUrl = "founder_skinlist_v2";
	} else if (menuUrl == "founder_entrance_list_v2" || menuUrl == "founder_entrance_rewrite_v2") {
		menuUrl = "founder_entrance_write_v2";
	} else if (menuUrl == "founder_stat_search" || menuUrl == "founder_stat_member") {
		menuUrl = "founder_stat_cafe";
	} else if (menuUrl == "founder_member_roleup_v2" || menuUrl == "founder_member_autolevelup" || menuUrl == "founder_member_inactive") {
		menuUrl = "founder_member_admin_v2";
	} else if (menuUrl == "founder_dustbin_manager") {
		menuUrl = "founder_board_management_v2";
	} else if (menuUrl == "founder_history_v2" || menuUrl == "founder_cafeclose_v2" || menuUrl == "founder_premium_service_v2" || menuUrl == "founder_premium_check_v2" || menuUrl == "founder_apply_premium_v2") {
		menuUrl = "founder_standard_v2";
	} else if (menuUrl == "manage") {
		menuUrl = menuUrl2;
	} else if (menuUrl == "founder_invite_link" || menuUrl == "founder_invite_v2") {
		menuUrl = "founder_invite";
	}
	$("#" + menuUrl).addClass('on');
})(jQuery);
</script>
		</div>
</body>
</html>