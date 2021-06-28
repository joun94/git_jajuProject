package mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import mypage.dao.MypageDAO;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageDAO mypageDAO;

	@Autowired
	private MypagePaging mypagePaging;

	@Autowired
	private MyScrap_Paging myScrap_Paging;

	@Autowired
	private MyRecodePaging myRecodePaging;

	@Override
	public MypageDTO getKeywordList(String id) {
		return mypageDAO.getKeywordList(id);
	}

	// 관심키워드 등록
	@Override
	public void myKeyword(Map<String, Object> map) {
		System.out.println(map);
		// map널이라면?
		if (map.get("keyword1") == null) {
			map.put("keyword1", "");
		}
		if (map.get("keyword2") == null) {
			map.put("keyword2", "");
		}
		if (map.get("keyword3") == null) {
			map.put("keyword3", "");
		}
		if (map.get("keyword4") == null) {
			map.put("keyword4", "");
		}
		if (map.get("keyword5") == null) {
			map.put("keyword5", "");
		}
		//System.out.println("keyword1" + map.get("keyword1"));
		//System.out.println("keyword2" + map.get("keyword2"));
		//System.out.println("keyword3" + map.get("keyword3"));
		//System.out.println("keyword4" + map.get("keyword4"));
		//System.out.println("keyword5" + map.get("keyword5"));
		mypageDAO.myKeyword(map);
	}

	// db에서 관심 키워드 개수 가져오기
	@Override
	public String getKeywordCountNum(String id) {
		String returnCountNum = mypageDAO.getKeywordCountNum(id) + "";
		//System.out.println("마이서비스returnCountNum= :" + returnCountNum);
		return returnCountNum;
	}

	// 관심 키워드 업데이트 하기
	@Override
	public void updateMyKeyword(Map<String, String> map) {
		// map널이라면?
				
		if (map.get("keyword1") == null) {
			map.put("keyword1", "");
		}
		if (map.get("keyword2") == null) {
			map.put("keyword2", "");
		}
		if (map.get("keyword3") == null) {
			map.put("keyword3", "");
		}
		if (map.get("keyword4") == null) {
			map.put("keyword4", "");
		}
		if (map.get("keyword5") == null) {
			map.put("keyword5", "");
		}
		//System.out.println("keyword1" + map.get("keyword1"));
		//System.out.println("keyword2" + map.get("keyword2"));
		//System.out.println("keyword3" + map.get("keyword3"));
		//System.out.println("keyword4" + map.get("keyword4"));
		//System.out.println("keyword5" + map.get("keyword5"));
		mypageDAO.updateMyKeyword(map);
	}

	// 팔로우 리스트 가져오기
	@Override
	public List<MemberDTO> getFollowList(Map<String, String> map) {

		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;

		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		//System.out.println("startNum" + startNum + "");
		//System.out.println("endNum" + endNum + "");

		return mypageDAO.getFollowList(map);
	}

	// 체크한 팔로우 삭제하기
	@Override
	public void deleteFollowList(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);
		mypageDAO.deleteFollowList(map);
	}

	@Override
	public List<SaleboardDTO> getMyFollowSaleList(Map<String, String> map, HttpSession session,String followId) {
		//1페이지당 10개씩
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		//System.out.println("FollowSaleList pg값"+pg);
		System.out.println(followId);
		map.put("startNum",startNum+"");
		map.put("endNum",endNum+"");
		System.out.println("FI START"+startNum);
		System.out.println("FI END"+endNum);
		
		return mypageDAO.getMyFollowSaleList(map,followId);
	}

	@Override
	public MyRecodePaging getMyFollowSaleListPage(String pg,String followId) {
		
		int TotalA= mypageDAO.getMyFollowSaleListPageTotalA(followId);
		
		myRecodePaging.setCurrentPage(Integer.parseInt(pg));
		myRecodePaging.setPageBlock(3);
		myRecodePaging.setPageSize(5);
		myRecodePaging.setTotalA(TotalA);
		myRecodePaging.makePagingHTML();
		return myRecodePaging;
	}
	
	@Override
	public List<MessageDTO> getMyMessageList(Map<String, String> map) {
		
		int endNum = Integer.parseInt(map.get("pg"))*5;
		int startNum = endNum-4;
		
		 map.put("startNum", startNum+"");
		 map.put("endNum", endNum+"");
		 
		//System.out.println("getMyMessageList startNum = "+ startNum+"");
		//System.out.println("endNum"+ endNum+"");
		//System.out.println("service의 message controller id넘어오는 지 확인 중" + map.get("id"));
		return mypageDAO.getMyMessageList(map);
	}

	// 스크랩 리스트
	@Override
	public List<ScrapDTO> getMyScrapList(Map<String, String> map) {

		int endNum = Integer.parseInt(map.get("pg")) * 6;
		int startNum = endNum - 5;

		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		//System.out.println("startNum" + startNum + "");
		//System.out.println("endNum" + endNum + "");

		//System.out.println("service의 getMyScrapList controller id넘어오는 지 확인 중" + map.get("id"));
		return mypageDAO.getMyScrapList(map);
	}

	// location
	@Override
	public void mylocationWrite(Map<String, String> map) {
		// map.put("id", (String)session.getAttribute("memId"));
		mypageDAO.mylocationWrite(map);
	}

	@Override
	public void mylocationWrite2(Map<String, String> map) {
		// map.put("id", (String)session.getAttribute("memId"));
		mypageDAO.mylocationWrite2(map);
	}

	@Override
	public List<MylocationDTO> mylocationList(Map<String, String> map) {
		// System.out.println("컨트롤러확인용 id = " + map.get("id"));
		return mypageDAO.mylocationList(map);
	}

	@Override
	public List<MylocationDTO> mylocationCount(Map<String, String> map) {
		return mypageDAO.mylocationCount(map);
	}

	@Override
	public void mylocationDelete(Map<String, String> map) {
		// map.put("id", (String)session.getAttribute("memId"));
		mypageDAO.mylocationDelete(map);
	}

	@Override
	public void mylocationDelete2(Map<String, String> map) {
		// map.put("id", (String)session.getAttribute("memId"));
		mypageDAO.mylocationDelete2(map);
	}

	// 메세지 삭제
	@Override
	public void deleteMyMessage(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);
		mypageDAO.deleteMyMessage(map);
	}

	// 스크랩 삭제
	@Override
	public void deleteMyScrap(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);
		mypageDAO.deleteMyScrap(map);
	}

	// 팔로우-페이징처리
	@Override
	public MypagePaging mypagePaging(Map<String, String> map) {
		int followtotalA = mypageDAO.getTotalA(map.get("id"));
		mypagePaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		mypagePaging.setPageBlock(3);
		mypagePaging.setPageSize(5);
		mypagePaging.setTotalA(followtotalA);
		mypagePaging.makePagingHTML();
		return mypagePaging;
	}

	// 스크랩 페이징 처리
	@Override
	public MyScrap_Paging myScrap_Paging(Map<String, String> map) {
		int scrapTotalA = mypageDAO.getScrapTotalA(map.get("id"));
		myScrap_Paging.setCurrentPage(Integer.parseInt(map.get("pg")));
		myScrap_Paging.setPageBlock(3);
		myScrap_Paging.setPageSize(6);
		myScrap_Paging.setTotalA(scrapTotalA);
		myScrap_Paging.makePagingHTML();
		return myScrap_Paging;
	}

	@Override
	public MessageDTO myMessageView(Map<String, String> map) {
		return mypageDAO.myMessageView(map);
	}

// =================판매내역, 구매내역, 판매중(+예약중)내역 , 각 페이징처리=================
	@Override
	public List<SaleboardDTO> mySaleRecodeList(Map<String, String> map) {

		// 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;

	
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		//System.out.println("START" + startNum);
		//System.out.println("END" + endNum);

		return mypageDAO.mySaleRecodeList(map);
	}

	@Override
	public MyRecodePaging myRecodePaging(Map<String, String> map) {
		int totalA = mypageDAO.getTotalASale(map.get("id")); // 총글수

		myRecodePaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		myRecodePaging.setPageBlock(3);
		myRecodePaging.setPageSize(5);
		myRecodePaging.setTotalA(totalA);
		myRecodePaging.makePagingHTML();

		return myRecodePaging;
	}


	// 쪽지 리스트 페이징처리
	/*@Override
	public MyRecodePaging getMyMessageListPage(String pg) {
		
		int TotalA= mypageDAO.getMyMessageListPageTotalA();
		myRecodePaging.setCurrentPage(Integer.parseInt(pg));
		myRecodePaging.setPageBlock(5);
		myRecodePaging.setPageSize(10);
		myRecodePaging.setTotalA(TotalA);
		myRecodePaging.makePagingHTML();
		return myRecodePaging;
	}*/
	
	// 쪽지 리스트 페이징처리
	@Override
	public MyRecodePaging getMyMessageListPage(Map<String, String> map) {
		int TotalA= mypageDAO.getMyMessageListPageTotalA(map.get("id"));
		myRecodePaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		myRecodePaging.setPageBlock(5);
		myRecodePaging.setPageSize(5);
		myRecodePaging.setTotalA(TotalA);
		myRecodePaging.makePagingHTML();
		return myRecodePaging;
	}
	
	@Override
	public List<SaleboardDTO> myBuyRecodeList(Map<String, String> map) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;
		// System.out.println("pg값"+pg);
	
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		//System.out.println("2START" + startNum);
		//System.out.println("2END" + endNum);

		return mypageDAO.myBuyRecodeList(map);
	}

	@Override
	public MyRecodePaging myRecodePaging2(Map<String, String> map) {
		int totalA = mypageDAO.getTotalABuy(map.get("id")); // 총글수

		myRecodePaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		myRecodePaging.setPageBlock(3);
		myRecodePaging.setPageSize(5);
		myRecodePaging.setTotalA(totalA);
		myRecodePaging.makePagingHTML();

		return myRecodePaging;
	}

	@Override
	public List<SaleboardDTO> myDealRecodeList(Map<String, String> map) {
		// 1페이지당 5개씩
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;
		// System.out.println("pg값"+pg);

		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		//System.out.println("3START" + startNum);
		//System.out.println("3END" + endNum);

		return mypageDAO.myDealRecodeList(map);
	}

	@Override
	public MyRecodePaging myRecodePaging3(Map<String, String> map) {
		
		int totalA = mypageDAO.getTotalADeal(map.get("id")); // 총글수

		myRecodePaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		myRecodePaging.setPageBlock(3);
		myRecodePaging.setPageSize(5);
		myRecodePaging.setTotalA(totalA);
		myRecodePaging.makePagingHTML();

		return myRecodePaging;
	}

	@Override
	public List<MyCountDTO> mysaleStateCount(Map<String, String> map, HttpSession session) {
		// map.put("id", (String)session.getAttribute("memId"));
		return mypageDAO.mysaleStateCount(map);
	}

	@Override
	public String getTotalScrapCount(String id) {
		String getTotalScrapCount = mypageDAO.getTotalScrapCount(id);
		return getTotalScrapCount;
	}

	@Override
	public String getTotalFollowCount(String id) {
		String getTotalFollowCount = mypageDAO.getTotalFollowCount(id);
		return getTotalFollowCount;
	}

	@Override
	public String getTotalMessageCount(String id) {
		String getTotalMessageCount = mypageDAO.getTotalMessageCount(id);
		return getTotalMessageCount;
	}

	/* 판매 게시판 쪽 스크랩 */
	@Override
	public void writeMyScrap(Map<String, String> map) {
		mypageDAO.writeMyScrap(map);
	}

	@Override
	public ScrapDTO getMyScrap(Map<String, String> map) {
		return mypageDAO.getMyScrap(map);
	}

	@Override
	public void deleteMyScrapView(Map<String, String> map) {
		mypageDAO.deleteMyScrapView(map);
	}

	@Override
	public String getMemberName(String id) {
		return mypageDAO.getMemberName(id);
	}

	@Override
	public String getMemberMannerTemp(String id) {
		return mypageDAO.getMemberMannerTemp(id);
	}

	@Override
	public String getNotReadMessageCount(String id) {
		return mypageDAO.getNotReadMessageCount(id);
	}

	@Override
	public MylocationDTO getMemberLocation(Map<String, String> map) {
		return mypageDAO.getMemberLocation(map);
	}

	@Override
	public void writeMyMessage(Map<String, String> map) {
		mypageDAO.writeMyMessage(map);		
	}
	
	//마이페이지 이미지 변경
	@Override
	public void myPageChangeImg(Map<String, String> map) {
		mypageDAO.myPageChangeImg(map) ;
	}

	@Override
	public MemberDTO getMyProfileInfo(Map<String, String> map) {
		return mypageDAO.getMyProfileInfo(map);
	}

	@Override
	public void updateProfileImage(Map<String, String> map) {
		mypageDAO.updateProfileImage(map);
		
	}

	@Override
	public void changeMessageState(String seq) {
		mypageDAO.changeMessageState(seq);
	}
	
	//06.24
	@Override
	public void replyMyMessage(Map<String, String> map) {
		mypageDAO.replyMyMessage(map);		
	}
	
	@Override
	public MessageDTO myMessageFormLoad(String seq) {
		return mypageDAO.myMessageFormLoad(seq);
	}

	@Override
	public List<MemberDTO> getMyFollowProfile(String followId) {
		return mypageDAO.getMyFollowProfile(followId);
	}

	@Override
	public MemberDTO getFollowerIdManner(Map<String, String> map) {
		return mypageDAO.getFollowerIdManner(map);
	}

	@Override
	public void writeMyFollow(Map<String, String> map) {
		mypageDAO.writeMyFollow(map);
	}

	@Override
	public FollowDTO getMyFollow(Map<String, String> map) {
		return mypageDAO.getMyFollow(map);
	}

	@Override
	public void deleteMyFollow(Map<String, String> map) {
		mypageDAO.deleteMyFollow(map);	
	}
	

}
