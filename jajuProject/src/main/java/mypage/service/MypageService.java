package mypage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;
import mypage.bean.FollowDTO;
import mypage.bean.MessageDTO;
import mypage.bean.MyCountDTO;
import mypage.bean.MyRecodePaging;
import mypage.bean.MyScrap_Paging;
import mypage.bean.MylocationDTO;
import mypage.bean.MypageDTO;
import mypage.bean.MypagePaging;
import mypage.bean.ScrapDTO;
import saleboard.bean.SaleboardDTO;

public interface MypageService {

	public MypageDTO getKeywordList(String id);
	//관심 키워드 등록
	public void myKeyword(Map<String, Object> map);
	//관심키워드 업데이트
	public void updateMyKeyword(Map<String, String> map);
	//관심키워드 총 개수
	public String getKeywordCountNum(String id);
	
	//팔로우리스트
	public List<MemberDTO> getFollowList(Map<String, String> map);
	//메세지 리스트
	public List<MessageDTO> getMyMessageList(Map<String, String> map);
	//찜목록 리스트
	public List<ScrapDTO> getMyScrapList(Map<String, String> map);
	
	//메세지 상세 페이지 
	public MessageDTO myMessageView(Map<String, String> map);
	
	//관심지역
	public void mylocationWrite(Map<String, String> map);

	public void mylocationWrite2(Map<String, String> map);

	public void mylocationDelete(Map<String, String> map);
	
	public void mylocationDelete2(Map<String, String> map);
	
	public List<MylocationDTO> mylocationList(Map<String, String> map);

	public List<MylocationDTO> mylocationCount(Map<String, String> map);

	public MylocationDTO getMemberLocation(Map<String, String> map);

	//팔로우 삭제
	public void deleteFollowList(String[] check);
	//메세지 삭제
	public void deleteMyMessage(String[] check);
	//찜목록 삭제
	public void deleteMyScrap(String[] check);

	
	public List<SaleboardDTO> mySaleRecodeList(Map<String, String> map);

	public MyRecodePaging myRecodePaging(Map<String, String> map);

	public List<SaleboardDTO> myBuyRecodeList(Map<String, String> map);

	public MyRecodePaging myRecodePaging2(Map<String, String> map);

	public List<SaleboardDTO> myDealRecodeList(Map<String, String> map);

	public MyRecodePaging myRecodePaging3(Map<String, String> map);

	/*public MypagePaging myScrapPaging(String pg);*/
	
	//팔로우 페이징 처리
	public MypagePaging mypagePaging(Map<String,String> map);
	//찜목록 페이징 처리
	public MyScrap_Paging myScrap_Paging(Map<String, String> map);
	
	
	//마이페이지 이름, 매너온도, 총 개수 가져오기
	public String getMemberName(String id);
	
	public List<MyCountDTO> mysaleStateCount(Map<String, String> map, HttpSession session);

	public String getTotalScrapCount(String id);
	
	public String getTotalFollowCount(String id);
	
	public String getTotalMessageCount(String id);
	
	public String getMemberMannerTemp(String id);
	
	public String getNotReadMessageCount(String id);

	//판매게시판 스크랩 1개 생성
	public void writeMyScrap(Map<String, String> map);
	//판매게시판 스크랩 1개 불러오기
	public ScrapDTO getMyScrap(Map<String, String> map);
	//판매게시판 스크랩 1개 삭제
	public void deleteMyScrapView(Map<String, String> map);
	
	public void writeMyMessage(Map<String, String> map);
	//마이페이지 , 프로필 이미지 변경.
	public void myPageChangeImg(Map<String, String> map);
	//마이페이지 , 기본 정보 불러오기
	public MemberDTO getMyProfileInfo(Map<String, String> map);
	
	//쪽지 페이징처리
	//public MyRecodePaging getMyMessageListPage(String pg);

	//myFollowSaleList
	public List<SaleboardDTO> getMyFollowSaleList(Map<String, String> map, HttpSession session,String followId);
	
	//myFollowSaleList paging
	public MyRecodePaging getMyFollowSaleListPage(String pg,String followId);
	
	public void updateProfileImage(Map<String, String> map);
	
	public MyRecodePaging getMyMessageListPage(Map<String, String> map);
	
	//쪽지 메세지 안읽음 -> 읽음으로 변경 
	public void changeMessageState(String seq);
	public MessageDTO myMessageFormLoad(String seq);
	public void replyMyMessage(Map<String, String> map);
	public List<MemberDTO> getMyFollowProfile(String followId);
	public MemberDTO getFollowerIdManner(Map<String, String> map);
	public void writeMyFollow(Map<String, String> map);
	public FollowDTO getMyFollow(Map<String, String> map);
	public void deleteMyFollow(Map<String, String> map);

	

}
