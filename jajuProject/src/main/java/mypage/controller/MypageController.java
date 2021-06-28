package mypage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import mypage.bean.FollowDTO;
import mypage.bean.MessageDTO;
import mypage.bean.MyCountDTO;
import mypage.bean.MyRecodePaging;
import mypage.bean.MyScrap_Paging;
import mypage.bean.MylocationDTO;
import mypage.bean.MypageDTO;
import mypage.bean.MypagePaging;
import saleboard.bean.SaleboardDTO;
import mypage.bean.ScrapDTO;
import mypage.service.MypageService;

@Controller
@RequestMapping(value = "mypage")
public class MypageController {

	@Autowired
	private MypageService mypageService;

	// 마이페이지 메인
	@RequestMapping(value = "mypageMain", method = RequestMethod.GET)
	public String mypageMain(Model model, HttpSession session) {
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");

		// model에 아이디 같이 보내기.
		model.addAttribute("memId", memId);
		model.addAttribute("display", "/mypage/mypageMain.jsp");
		return "/index";
	}

	// 마이페이지 프로필 이미지 변경 (window.open)
	@RequestMapping(value = "mypageChangeImg", method = RequestMethod.GET)
	public String mypageChangeImg(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");
		// model에 아이디 같이 보내기.
		model.addAttribute("memId", memId);

		return "/mypage/MypageChangeImg";
	}

	// TODO 내판매내역
	@RequestMapping(value = "mySaleRecode", method = RequestMethod.GET)
	public String mySaleRecode(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		String memId = (String) session.getAttribute("memId");

		// required=false 는 안써도됨
		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg); // pg값 받기
		model.addAttribute("display", "/mypage/mySaleRecode.jsp");
		// System.out.println("pgpg"+pg);
		return "/index";
	}

	// 내구매내역
	@RequestMapping(value = "myBuyRecode", method = RequestMethod.GET)
	public String myBuyRecode(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		String memId = (String) session.getAttribute("memId");

		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg); // pg값 받기
		model.addAttribute("display", "/mypage/myBuyRecode.jsp");
		return "/index";
	}

	// 내 판매중(판매중+예약중) 내역
	@RequestMapping(value = "myDealRecode", method = RequestMethod.GET)
	public String myDealRecode(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		
		String memId = (String) session.getAttribute("memId");

		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg); // pg값 받기
		model.addAttribute("display", "/mypage/myDealRecode.jsp");
		return "/index";
	}

	// 내거래내역
	@RequestMapping(value = "myDealRecord", method = RequestMethod.GET)
	public String myDealRecord(Model model, HttpSession session) {
		String memId = (String) session.getAttribute("memId");

		model.addAttribute("memId", memId);
		model.addAttribute("display", "/mypage/myDealRecord.jsp");
		return "/index";
	}

	// ============================== 팔로우 ==============================ㄴ
	// 팔로우목록
	@RequestMapping(value = "myFollow", method = RequestMethod.GET)
	public String myFollow(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");

		// model에 아이디 같이 보내기.
		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/mypage/myFollow.jsp");
		return "/index";
	}

	// 관심키워드등록
	@RequestMapping(value = "myKeywordForm", method = RequestMethod.GET)
	public String myKeywordForm(Model model, HttpSession session) {
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");

		// model에 아이디 같이 보내기.
		model.addAttribute("memId", memId);
		model.addAttribute("display", "/mypage/myKeywordForm.jsp");
		return "/index";
	}

	// 관심지역등록
	// 관심지역 등록된 목록보여주기
	@RequestMapping(value = "myLocationForm", method = RequestMethod.GET)
	public String myLocationForm(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("memId");

		model.addAttribute("memId", memId);
		model.addAttribute("display", "/mypage/myLocationForm.jsp");
		return "/index";
	}

	// 쪽지 리스트
	@RequestMapping(value = "myMessage", method = RequestMethod.GET)
	public String myMessage(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");
		// model에 아이디 같이 보내기.
		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg);
		return "/mypage/myMessage";
	}

	// 쪽지 상세 페이지
	@RequestMapping(value = "myMessageView", method = RequestMethod.GET)
	public String myMessageView(@RequestParam String seq, @RequestParam(required = false, defaultValue = "1") String pg,
			Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		return "/mypage/myMessageView";
	}
	
	//판매게시판에서 메세지 보내는 기능!!!!
	// myMessageForm?pg=1
	@RequestMapping(value = "myMessageForm", method = RequestMethod.GET)
	public String myMessageForm(@RequestParam(required = false, defaultValue = "1") String pg, HttpSession session, Model model) {
		model.addAttribute("pg", pg);
		return "/mypage/myMessageForm";
	}

	// 메시지 답장화면 띄우기 --답장만, 
	@RequestMapping(value = "myMessageReplyForm", method = RequestMethod.GET)
		public String myMessageReplyForm(@RequestParam(required = false, defaultValue = "1") String pg, @RequestParam String seq, HttpSession session, Model model) {
			model.addAttribute("seq", seq);
			model.addAttribute("pg", pg);
			return "/mypage/myMessageReplyForm";
		}
	
	// 찜목록
	@RequestMapping(value = "myScrap", method = RequestMethod.GET)
	public String myScrap(@RequestParam(required = false, defaultValue = "1") String pg, Model model,
			HttpSession session) {

		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");

		model.addAttribute("memId", memId);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/mypage/myScrap.jsp");
		return "/index";
	}

	/* ==================== 여기서부터 DB연결 METHOD ==================== */

	// ajax호출, GET KEYWORD LIST
	@RequestMapping(value = "getKeywordList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getKeywordList(@RequestParam String id, HttpSession session) {
		// list불러오기.

		// 세션
		String memId = (String) session.getAttribute("memId");

		// System.out.println("getKeywordList memId 아이디" + memId);

		MypageDTO mypageDTO = mypageService.getKeywordList(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memId", memId);
		mav.addObject("mypageDTO", mypageDTO);
		mav.setViewName("jsonView");
		return mav;
	}

	// ajax호출, INSERT 관심키워드
	@RequestMapping(value = "myKeyword", method = RequestMethod.POST)
	@ResponseBody
	public Object myKeyword(@RequestParam Map<String, Object> map, @RequestParam String id, HttpSession session) {
		// System.out.println("map의 값 확인" + map);
		map.put("id", id);// 컨트롤러에서 넘긴 임의 아이디 값. 바꿔야함.

		// db에 저장하기
		mypageService.myKeyword(map);

		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");

		Map<String, Object> data = new HashMap<String, Object>();
		// 성공했다고 처리
		data.put("code", "OK");
		data.put("message", "등록에 성공 하였습니다.");
		return data;
	}

	// ajax호출, UPDATE 관심키워드
	@RequestMapping(value = "updateMyKeyword", method = RequestMethod.POST)
	@ResponseBody
	public Object updateMyKeyword(@RequestParam Map<String, String> map, @RequestParam String id) {
		// System.out.println("map의 값 확인" + map);
		
		map.put("id", id);// 컨트롤러에서 넘긴 임의 아이디 값. 바꿔야함.

		// db에 저장하기
		mypageService.updateMyKeyword(map);

		Map<String, Object> data = new HashMap<String, Object>();
		// 성공했다고 처리
		data.put("code", "OK");
		data.put("message", "등록에 성공 하였습니다.");
		return data;
	}

	// ajax호출, GET 관심키워드 COUNT NUMBER
	@RequestMapping(value = "getKeywordCountNum", method = RequestMethod.POST)
	@ResponseBody
	public String getKeywordCountNum(@RequestParam String id) {
		// db에서 불러오기, int값 가져오기 (count(*)from), sum 의 값 .
		// System.out.println("getKeywordCountNum= " + id);

		// MypageDTO mypageDTO = mypageService.getKeywordList(id);

		return mypageService.getKeywordCountNum(id);

	}

	/* ======================= FOLLOW ======================= */
	/* ======================= FOLLOW ======================= */
	// ajax호출, GET 팔로우 LIST
	@RequestMapping(value = "getFollowList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getFollowList(@RequestParam Map<String, String> map,
			@RequestParam(required = false, defaultValue = "1") String pg) {
		// System.out.println("getFollowList 아이디" + map.get("id"));

		// GET LIST
		List<MemberDTO> list = mypageService.getFollowList(map);

		// PAGING -> 1PAGE--5POSTS
		map.put("pg", pg);
		MypagePaging mypagePaging = mypageService.mypagePaging(map);

		ModelAndView mav = new ModelAndView();

		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("mypagePaging", mypagePaging);

		mav.setViewName("jsonView");
		return mav;
	}

	// DELETE 팔로우 LIST (without ajax function)
	@RequestMapping(value = "deleteMyFollowList", method = RequestMethod.GET)
	public ModelAndView deleteMyFollowList(String[] check) {
		// System.out.println("check = " + check[0]);
		mypageService.deleteFollowList(check);
		return new ModelAndView("redirect:/mypage/myFollow");
	}

	// 팔로우를 눌렀을 때 , 그 사람의 정보 불러오기 (팝업-> 디스플레이로 변경)
		// 팔로우 판매목록list 화면
		@RequestMapping(value = "myFollowSaleList", method = RequestMethod.GET)
		public String myFollowSaleList(@RequestParam(required = false, defaultValue = "1") String pg, String followId,
				Model model) {
			//System.out.println("test중인 followId" + followId);
			model.addAttribute("pg", pg);
			model.addAttribute("followId", followId);

			model.addAttribute("display", "/mypage/myFollowSaleList.jsp");
			return "/index";
	}

	/* ======================= MESSAGE ======================= */
	// ajax호출, GET 메세지 LIST
	@RequestMapping(value = "getMyMessageList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMyMessageList(@RequestParam Map<String, String> map,
			@RequestParam(required = false, defaultValue = "1") String pg) {
		// System.out.println("getMyMessageList 아이디" + map.get("id"));
		map.put("pg", pg);
		// GET MESSAGE LIST
		List<MessageDTO> list = mypageService.getMyMessageList(map);

		// PAGING -> 1PAGE--10POSTS
		//MypagePaging mypagePaging = mypageService.mypagePaging(map);
		MyRecodePaging getMyMessageListPage = mypageService.getMyMessageListPage(map);	
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("getMyMessageListPage",getMyMessageListPage);
		//mav.addObject("mypagePaging", mypagePaging);

		mav.setViewName("jsonView");
		return mav;
	}

	// 메세지 상세페이지
	@RequestMapping(value = "myMessageView", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myMessageView(@RequestParam Map<String, String> map,
			@RequestParam(required = false, defaultValue = "1") String pg) {
		
		// map의 값 확인해보기
		//System.out.println("myMessageView의 map / id, seq 확인하기 = " + map);
		
		mypageService.changeMessageState(map.get("seq"));
		

		// 맵에 pg값 담아주기? 안담아도 될듯 이따가 다시 확인
		map.put("pg", pg);

		// GET MESSAGE LIST
		MessageDTO messageDTO = mypageService.myMessageView(map);

		// System.out.println("messageDTO?== " + messageDTO.getMessage_subject());

		ModelAndView mav = new ModelAndView();

		mav.addObject("pg", pg);
		mav.addObject("messageDTO", messageDTO);
		mav.setViewName("jsonView");
		return mav;
	}

	// DELETE 메세지 (without ajax function)
	@RequestMapping(value = "deleteMyMessage", method = RequestMethod.GET)
	public ModelAndView deleteMyMessage(String[] check) {
		// System.out.println("check = " + check[0]);
		mypageService.deleteMyMessage(check);
		return new ModelAndView("redirect:/mypage/myMessage");
	}
	
	//메시지 답장버튼을 눌렀을때 메시지에 수신자,발신자 정보 띄우기
	@RequestMapping(value = "myMessageFormLoad", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myMessageFormLoad(@RequestParam String seq) {
		
		// map의 값 확인해보기
		//System.out.println("myMessageView의 map / id, seq 확인하기 = " + map);
		
	//	mypageService.changeMessageState(map.get("seq"));
		

		// 맵에 pg값 담아주기? 안담아도 될듯 이따가 다시 확인
		//map.put("pg", pg);

		// GET MESSAGE LIST
		MessageDTO messageDTO = mypageService.myMessageFormLoad(seq);

		// System.out.println("messageDTO?== " + messageDTO.getMessage_subject());

		ModelAndView mav = new ModelAndView();
		//System.out.println("메시지보내기전 메시지글번호"+seq);
		//mav.addObject("pg", pg);
		mav.addObject("messageDTO", messageDTO);
		mav.setViewName("jsonView");
		return mav;
	}
		
	// 답장메세지 보내기
	@RequestMapping(value = "replyMyMessage", method = RequestMethod.POST)
	@ResponseBody
	public void replyMyMessage(@RequestParam Map<String, String> map) {
		/*
		 * System.out.println("답장 맵내용보기"+map);
		 * System.out.println(map.get("message_reader"));
		 * System.out.println(map.get("message_writer"));
		 * System.out.println(map.get("message_subject"));
		 * System.out.println(map.get("message_content"));
		 * System.out.println(map.get("sale_seq"));
		 */

		//map.put("message_content", map.get("message_content_Span"));

		mypageService.replyMyMessage(map);
	}


	// myFollowSaleList 띄울때 Follow 프로필 띄우기
	@RequestMapping(value = "getMyFollowProfile", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMyFollowProfile(@RequestParam String followId ) {
		//System.out.println("M F P 컨트롤러 가고있나"+followId);
		List<MemberDTO> list = mypageService.getMyFollowProfile(followId); // memberDTO에 담아 불러오기
		//System.out.println("M F P List돌아왔나"+list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}	
		
	
	// 메세지 보내기
	@RequestMapping(value = "writeMyMessage", method = RequestMethod.POST)
	@ResponseBody
	public void writeMyMessage(@RequestParam Map<String, String> map) {

		System.out.println(map.get("message_reader"));
		System.out.println(map.get("message_writer"));
		System.out.println(map.get("message_subject"));
		System.out.println(map.get("message_content"));
		System.out.println(map.get("sale_seq"));

		map.put("message_content", map.get("message_content_Span"));

		mypageService.writeMyMessage(map);
	}
	
	
	//민지 팔로잉 writeMyFollow
	// 판매게시판 쪽 팔로잉 1개 생성
	@RequestMapping(value = "writeMyFollow", method = RequestMethod.POST)
	@ResponseBody
	public void writeMyFollow(@RequestParam Map<String, String> map) {
		//writeMyFollow
		mypageService.writeMyFollow(map);
	}

	// 판매게시판 쪽 팔로잉 1개 불러오기(판매 게시글 view 에서 팔로우->팔로잉 으로 변경)
	@RequestMapping(value = "getMyFollow", method = RequestMethod.POST)
	@ResponseBody
	public String getMyFollow(@RequestParam Map<String, String> map, HttpSession session) {
		//System.out.println("getMyFollow===" +map);
		FollowDTO followDTO = mypageService.getMyFollow(map);
		//System.out.println(followDTO);
		if (followDTO == null){
			return "non_exist";	
		} 
		else {
			return "exist";
		}
	}

	// 판매게시판 쪽 찜 1개 삭제하기(누르면 팔로잉 해제 기능)
	@RequestMapping(value = "deleteMyFollow", method = RequestMethod.POST)
	@ResponseBody
	public void deleteMyFollow(@RequestParam Map<String, String> map) {
		//deleteMyFollow
		mypageService.deleteMyFollow(map);
	}
	
	
	
	/* ======================= SCRAP ======================= */
	// ajax호출, GET 찜목록 LIST
	@RequestMapping(value = "getMyScrapList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMyScrapList(@RequestParam Map<String, String> map,
			@RequestParam(required = false, defaultValue = "1") String pg) {

		// System.out.println("getMyScrapList string pg" + map);

		map.put("pg", pg);

		// GET MESSAGE LIST
		List<ScrapDTO> list = mypageService.getMyScrapList(map);

		// PAGING -> 1PAGE--5POSTS
		MyScrap_Paging myScrap_Paging = mypageService.myScrap_Paging(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("myScrap_Paging", myScrap_Paging);
		mav.setViewName("jsonView");
		return mav;
	}

	// 찜목록 삭제
	@RequestMapping(value = "deleteMyScrap", method = RequestMethod.GET)
	public ModelAndView deleteMyScrap(String[] check) {
		// System.out.println("check = " + check[0]);
		mypageService.deleteMyScrap(check);
		return new ModelAndView("redirect:/mypage/myScrap");
	}

	// 판매게시판 쪽 찜 1개 생성
	@RequestMapping(value = "writeMyScrap", method = RequestMethod.POST)
	@ResponseBody
	public void writeMyScrap(@RequestParam Map<String, String> map) {
		// Write MyScrap
		mypageService.writeMyScrap(map);
	}

	// 판매게시판 쪽 찜 1개 불러오기(판매 게시글 view 에서 찜 한 글은 빨간하트로 보여지게)
	@RequestMapping(value = "getMyScrap", method = RequestMethod.POST)
	@ResponseBody
	public String getMyScrap(@RequestParam Map<String, String> map) {

		ScrapDTO scrapDTO = mypageService.getMyScrap(map);

		String swich = null;
		if (scrapDTO == null) {
			swich = "blackhart.png";
		} else {
			swich = "hart.png";
		}
		return swich;
	}

	// 판매게시판 쪽 찜 1개 삭제하기(누르면 찜 해제 기능)
	@RequestMapping(value = "deleteMyScrapView", method = RequestMethod.POST)
	@ResponseBody
	public void deleteMyScrapView(@RequestParam Map<String, String> map) {
		// delete MyScrap
		mypageService.deleteMyScrapView(map);
	}

	/* ======================= LOCATION ======================= */

	// 지역2등록
	@RequestMapping(value = "mylocationWrite", method = RequestMethod.POST)
	@ResponseBody
	public void mylocationWrite(@RequestParam Map<String, String> map) {
		// System.out.println(map);
		mypageService.mylocationWrite(map);
	}

	// 지역3등록
	@RequestMapping(value = "mylocationWrite2", method = RequestMethod.POST)
	@ResponseBody
	public void mylocationWrite2(@RequestParam Map<String, String> map) {
		// System.out.println(map);
		mypageService.mylocationWrite2(map);
	}

	// 지역2삭제
	@RequestMapping(value = "mylocationDelete", method = RequestMethod.POST)
	@ResponseBody
	public void mylocationDelete(@RequestParam Map<String, String> map) {
		// System.out.println(map);
		mypageService.mylocationDelete(map);
	}

	// 지역3삭제
	@RequestMapping(value = "mylocationDelete2", method = RequestMethod.POST)
	@ResponseBody
	public void mylocationDelete2(@RequestParam Map<String, String> map) {
		// System.out.println(map);
		mypageService.mylocationDelete2(map);
	}

	// 지역 list
	@RequestMapping(value = "mylocationList", method = RequestMethod.POST)
	@ResponseBody // 나중에session으로 대체
	public ModelAndView mylocationList(@RequestParam Map<String, String> map) {

		// System.out.println("컨트롤러확인용 id = " + map.get("id"));

		List<MylocationDTO> list = mypageService.mylocationList(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}

	// 지역 count(*)
	@RequestMapping(value = "mylocationCount", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mylocationCount(@RequestParam Map<String, String> map) {
		// System.out.println(map );
		List<MylocationDTO> list = mypageService.mylocationCount(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}

	// 회원의 주소 받아오기 ( 시도, 군구 만 받아올 것 )
	@RequestMapping(value = "getMemberLocation", method = RequestMethod.POST)
	@ResponseBody // 나중에session으로 대체
	public ModelAndView getMemberLocation(@RequestParam Map<String, String> map) {

		// System.out.println("컨트롤러확인용 id = " + map.get("id"));

		MylocationDTO mylocationDTO = mypageService.getMemberLocation(map);

		//System.out.println("현재확인중인주소= "+mylocationDTO.getLocation1_addr1());
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("mylocationDTO", mylocationDTO);
		mav.setViewName("jsonView");
		return mav;
	}

	// ================= 판매완료,구매완료, 판매중(+예약중) 목록 =================//

	@RequestMapping(value = "mySaleRecodeList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mySaleRecodeList(@RequestParam String pg, @RequestParam Map<String, String> map) {
		// System.out.println("pg가는지"+pg);
		// 1페이지당 5개씩
		List<SaleboardDTO> list = mypageService.mySaleRecodeList(map);

		// 페이징처리
		MyRecodePaging myRecodePaging = mypageService.myRecodePaging(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("myRecodePaging", myRecodePaging);
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "myBuyRecodeList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myBuyRecodeList(@RequestParam String pg, @RequestParam Map<String, String> map) {
		// System.out.println("pg가는지"+pg);
		// 1페이지당 5개씩
		List<SaleboardDTO> list = mypageService.myBuyRecodeList(map);
		// System.out.println("pg가는지2"+pg);
		// 페이징처리
		MyRecodePaging myRecodePaging2 = mypageService.myRecodePaging2(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("myRecodePaging2", myRecodePaging2);
		mav.setViewName("jsonView");

		return mav;

	}

	@RequestMapping(value = "myDealRecodeList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myDealRecodeList(@RequestParam String pg, @RequestParam Map<String, String> map) {
		// map에 pg 같이 담아 보내기. id+pg

		//System.out.println("myDealRecodeList==" + map);

		// 1페이지당 5개씩
		List<SaleboardDTO> list = mypageService.myDealRecodeList(map);

		// 페이징처리
		MyRecodePaging myRecodePaging3 = mypageService.myRecodePaging3(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("myRecodePaging3", myRecodePaging3);
		mav.setViewName("jsonView");

		return mav;

	}

	// =============== MYPAGE >> count(*) 이용해서 이름, 매너온도 ,찜목록수, 팔로우수, 쪽지 수
	// 가져오기===============//
	// 로그인 한 유저 이름
	@RequestMapping(value = "getMemberName", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String getMemberName(@RequestParam String id) {
		return mypageService.getMemberName(id);
	}

	@RequestMapping(value = "getMemberMannerTemp", method = RequestMethod.POST)
	@ResponseBody
	public String getMemberMannerTemp(@RequestParam String id) {
		return mypageService.getMemberMannerTemp(id);
	}

	// 찜목록 total
	@RequestMapping(value = "getTotalScrapCount", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalScrapCount(@RequestParam String id) {
		return mypageService.getTotalScrapCount(id);
	}

	// 팔로우 total
	@RequestMapping(value = "getTotalFollowCount", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalFollowCount(@RequestParam String id) {
		return mypageService.getTotalFollowCount(id);
	}

	// 메세지 total
	@RequestMapping(value = "getTotalMessageCount", method = RequestMethod.POST)
	@ResponseBody
	public String getTotalMessageCount(@RequestParam String id) {
		return mypageService.getTotalMessageCount(id);
	}

	// 안읽은 메세지 total, 상태 : 0 (읽지않음)
	@RequestMapping(value = "getNotReadMessageCount", method = RequestMethod.POST)
	@ResponseBody
	public String getNotReadMessageCount(@RequestParam String id) {
		return mypageService.getNotReadMessageCount(id);
	}

	// 마이페이지 판매완료,구매완료,판매중 건수 Count확인
	@RequestMapping(value = "mysaleStateCount", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mysaleStateCount(@RequestParam Map<String, String> map, HttpSession session) {
		List<MyCountDTO> list = mypageService.mysaleStateCount(map, session);
		// String id = (String) session.getAttribute("memId");

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}

	// 마이페이지 프로필 기본 정보 불러오기 ( 이미지, 아이디, 매너온도, 현재위치 시도 군구 까지만)
	// 마이페이지 프로필 이미지 변경 myPageChangeImg
	@RequestMapping(value = "getMyProfileInfo", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMyProfileInfo(@RequestParam Map<String, String> map, HttpSession session) {
		//System.out.println("getMyProfileInfo 의 map (id있어야함) == " + map);

		MemberDTO memberDTO = mypageService.getMyProfileInfo(map); // memberDTO에 담아 불러오기

		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("jsonView");
		return mav;
	}

	// 마이페이지 프로필 이미지 변경 myPageChangeImg
	@RequestMapping(value = "myPageChangeImg", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myPageChangeImg(@RequestParam Map<String, String> map, HttpSession session) {
		//System.out.println("myPageChangeImg 의 map == " + map);

		mypageService.myPageChangeImg(map);// update image 하기.

		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}

	/* <input type="file" name="img" > name이 1개인 경우 */
	@RequestMapping(value = "updateProfileImage", method = RequestMethod.POST)
	@ResponseBody
	public void updateProfileImage(@RequestParam MultipartFile member_image, HttpSession session) { // img tmp라는 임시파일로 올라옴
		//System.out.println("(jsp-> controller) updateProfileImage의 memberDTO의 이미지 이름은?==" +member_image);
		
		// ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");
		
		String filePath = "E:\\Spring\\workspace\\JajuProject\\src\\main\\webapp\\storage";
		String fileName = member_image.getOriginalFilename();
		File file = new File(filePath, fileName);// tmp라는 임시파일로 올라온 파일을 진짜 생성해준다 //파일 복사 input, output생성 후 예외 처리
		
		try {
			FileCopyUtils.copy(member_image.getInputStream(), new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map <String, String> map = new HashMap<String, String>();
		map.put("member_image",fileName);
		map.put("id", memId);
		//System.out.println(" updateProfileImage의 fileName 이름은?==" +fileName);

		mypageService.updateProfileImage(map);
		
	}

	
	// 팔로우 상세페이지
	@RequestMapping(value = "getMyFollowSaleList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMyFollowSaleList(@RequestParam Map<String, String> map, String pg, HttpSession session,
			String followId) {
		// System.out.println("팔로아이디 뜨나1"+followId);
		// System.out.println("pg뜨나1"+pg);
		map.put("pg", pg);

		// 1페이지당 10개씩
		List<SaleboardDTO> list = mypageService.getMyFollowSaleList(map, session, followId);
		// String id = (String) session.getAttribute("memId");

		// 페이징처리
		// System.out.println("팔로아이디 뜨나2"+followId);
		MyRecodePaging getMyFollowSaleListPage = mypageService.getMyFollowSaleListPage(pg, followId);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("getMyFollowSaleListPage", getMyFollowSaleListPage);
		mav.setViewName("jsonView");
		return mav;
	}
	@RequestMapping(value = "getFollowerIdManner", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getFollowerIdManner(@RequestParam Map<String, String> map, HttpSession session) {
		System.out.println("getFollowerIdManner 의 map (id있어야함) == " + map);

		MemberDTO memberDTO = mypageService.getFollowerIdManner(map); // memberDTO에 담아 불러오기

		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("jsonView");
		return mav;
	}
}
