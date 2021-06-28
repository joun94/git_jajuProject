package mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import mypage.bean.FollowDTO;
import mypage.bean.MessageDTO;
import mypage.bean.MyCountDTO;
import mypage.bean.MylocationDTO;
import mypage.bean.MypageDTO;
import saleboard.bean.SaleboardDTO;
import mypage.bean.ScrapDTO;

@Transactional
@Repository
public class MypageDAOMybatis implements MypageDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MypageDTO getKeywordList(String id) {
		return sqlSession.selectOne("mypageSQL.getKeywordList", id);
	}

	@Override
	public void myKeyword(Map<String, Object> map) {
		sqlSession.update("mypageSQL.myKeyword", map);
	}

	@Override
	public int getKeywordCountNum(String id) {
		//System.out.println("==마이바티스 겟카운트넘버아이디==" + id);
		String keyword1 = sqlSession.selectOne("mypageSQL.getKeywordCountNum1", id);
		String keyword2 = sqlSession.selectOne("mypageSQL.getKeywordCountNum2", id);
		String keyword3 = sqlSession.selectOne("mypageSQL.getKeywordCountNum3", id);
		String keyword4 = sqlSession.selectOne("mypageSQL.getKeywordCountNum4", id);
		String keyword5 = sqlSession.selectOne("mypageSQL.getKeywordCountNum5", id);
		int count1 = 0,count2=0,count3=0,count4=0,count5=0;
		if(keyword1!=null) {
			count1=1;
		}
		if(keyword2!=null) {
			count2=1;
		}
		if(keyword3!=null) {
			count3=1;
		}
		if(keyword4!=null) {
			count4=1;
		}
		if(keyword5!=null) {
			count5=1;
		}
		int sum = count1 + count2 + count3 + count4 + count5;
		System.out.println("sum = " + sum);
		return sum;
	}

	@Override
	public void updateMyKeyword(Map<String, String> map) {
		//System.out.println("updateMyKeyword map = " + map);
		sqlSession.update("mypageSQL.updateMyKeyword", map);
	}

	@Override
	public List<MemberDTO> getFollowList(Map<String, String> map) {
		List<MemberDTO> list = sqlSession.selectList("mypageSQL.getFollowList", map);
		//System.out.println("마이바티스 return list 값 확인중 = " + list);
		return list;
	}

	@Override
	public void deleteFollowList(Map<String, String[]> map) {
		sqlSession.delete("mypageSQL.deleteFollowList", map);
	}

	@Override
	public List<MessageDTO> getMyMessageList(Map<String, String> map) {
		return sqlSession.selectList("mypageSQL.getMyMessageList", map);
	}

	@Override
	public List<ScrapDTO> getMyScrapList(Map<String, String> map) {
		return sqlSession.selectList("mypageSQL.getMyScrapList", map);
	}

	// location
	@Override
	public void mylocationWrite(Map<String, String> map) {
		sqlSession.update("mypageSQL.mylocationWrite", map);
	}

	@Override
	public void mylocationWrite2(Map<String, String> map) {
		sqlSession.update("mypageSQL.mylocationWrite2", map);
	}

	@Override
	public List<MylocationDTO> mylocationList(Map<String, String> map) {
		//System.out.println("마이바티스컨트롤러확인용 id = " + map.get("id"));

		List<MylocationDTO> list = sqlSession.selectList("mypageSQL.mylocationList", map);
		//System.out.println(map);
		return list;
	}

	@Override
	public List<MylocationDTO> mylocationCount(Map<String, String> map) {

		List<MylocationDTO> list = sqlSession.selectList("mypageSQL.mylocationCount", map);

		return list;
	}

	@Override
	public void mylocationDelete(Map<String, String> map) {
		sqlSession.update("mypageSQL.mylocationDelete", map);

	}

	@Override
	public void mylocationDelete2(Map<String, String> map) {
		sqlSession.update("mypageSQL.mylocationDelete2", map);

	}

	// 메세지 삭제. 삭제하지 말고 상태를 3으로 바꾸기.--3은 삭제한 메시지
	@Override
	public void deleteMyMessage(Map<String, String[]> map) {
		sqlSession.update("mypageSQL.deleteMyMessage", map);
	}

	// 스크랩삭제
	@Override
	public void deleteMyScrap(Map<String, String[]> map) {
		sqlSession.delete("mypageSQL.deleteMyScrap", map);
	}

	// 페이징 처리 - 팔로우 토탈 게시글 수
	@Override
	public int getTotalA(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalA", id);
	}

	// 페이징 처리 - 팔로우 토탈 게시글 수
	@Override
	public int getScrapTotalA(String id) {
		return sqlSession.selectOne("mypageSQL.getScrapTotalA",id);
	}

	@Override
	public MessageDTO myMessageView(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.myMessageView", map);
	}

	// recode 판매 목록 구매목록 판매중(+예약중) , 각 페이징 처리
	@Override
	public List<SaleboardDTO> mySaleRecodeList(Map<String, String> map) {

		return sqlSession.selectList("mypageSQL.mySaleRecodeList", map);
	}

	@Override
	public int getTotalASale(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalASale",id);
	}

	@Override
	public List<SaleboardDTO> myBuyRecodeList(Map<String, String> map) {
		return sqlSession.selectList("mypageSQL.myBuyRecodeList", map);
	}

	@Override
	public int getTotalABuy(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalABuy",id);
	}

	@Override
	public List<SaleboardDTO> myDealRecodeList(Map<String, String> map) {
		return sqlSession.selectList("mypageSQL.myDealRecodeList", map);
	}

	@Override
	public int getTotalADeal(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalADeal",id);
	}

	
	// 판매게시판 쪽 스크랩
	@Override
	public void writeMyScrap(Map<String, String> map) {
		sqlSession.insert("mypageSQL.writeMyScrap", map);
	}

	@Override
	public ScrapDTO getMyScrap(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.getMyScrap", map);
	}

	@Override
	public void deleteMyScrapView(Map<String, String> map) {
		sqlSession.delete("mypageSQL.deleteMyScrapView", map);

	}

	
	/* mypage 이름, 매너온도, 총 개수 가져오기*/
	@Override
	public String getMemberName(String id) {
		return sqlSession.selectOne("mypageSQL.getMemberName",id);
	}
	
	@Override
	public String getMemberMannerTemp(String id) {
		return sqlSession.selectOne("mypageSQL.getMemberMannerTemp",id);
	}

	
	@Override
	public List<MyCountDTO> mysaleStateCount(Map<String, String> map) {
		List<MyCountDTO> list = sqlSession.selectList("mypageSQL.mysaleStateCount", map);

		return list;
	}

	@Override
	public String getTotalScrapCount(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalScrapCount", id) + "";
	}

	@Override
	public String getTotalFollowCount(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalFollowCount", id) + "";
	}

	@Override
	public String getTotalMessageCount(String id) {
		return sqlSession.selectOne("mypageSQL.getTotalMessageCount", id) + "";
	}

	@Override
	public String getNotReadMessageCount(String id) {
		return sqlSession.selectOne("mypageSQL.getNotReadMessageCount", id) + "";
	}

	@Override
	public MylocationDTO getMemberLocation(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.getMemberLocation", map);
	}

	@Override
	public void writeMyMessage(Map<String, String> map) {
	   sqlSession.insert("mypageSQL.writeMyMessage", map);
	}

	@Override
	public void myPageChangeImg(Map<String, String> map) {
		sqlSession.update("mypageSQL.myPageChangeImg",map);
	}

	@Override
	public MemberDTO getMyProfileInfo(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.getMyProfileInfo",map) ;
	}
	//팔로우 상세 페이지
	@Override
	public List<SaleboardDTO> getMyFollowSaleList(Map<String, String> map, String followId) {
		List<SaleboardDTO> list=sqlSession.selectList("mypageSQL.getMyFollowSaleList",map);
		
		return list;
	}

	@Override
	public int getMyFollowSaleListPageTotalA(String followId) {
		//System.out.println("팔로아이디 뜨나3"+followId);
		return sqlSession.selectOne("mypageSQL.getMyFollowSaleListPageTotalA",followId);
	}

	@Override
	public void updateProfileImage(Map<String, String> map) {
		sqlSession.update("mypageSQL.updateProfileImage",map);
	}
	
	@Override
	public int getMyMessageListPageTotalA(String id) {
		return sqlSession.selectOne("mypageSQL.getMyMessageListPageTotalA",id);
	}

	@Override
	public void changeMessageState(String seq) {
		sqlSession.update("mypageSQL.changeMessageState",Integer.parseInt(seq));
	}
	//06.24

	@Override
	public void replyMyMessage(Map<String, String> map) {
	   sqlSession.insert("mypageSQL.writeMyMessage", map);
	}

	@Override
	public MessageDTO myMessageFormLoad(String seq) {
		return sqlSession.selectOne("mypageSQL.myMessageFormLoad", seq);
	}

	@Override
	public List<MemberDTO> getMyFollowProfile(String followId) {
		System.out.println("MFP bybatis"+followId);
		return sqlSession.selectList("mypageSQL.getMyFollowProfile" , followId);
	}

	@Override
	public MemberDTO getFollowerIdManner(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.getFollowerIdManner", map);
	}

	@Override
	public void writeMyFollow(Map<String, String> map) {
		sqlSession.insert("mypageSQL.writeMyFollow", map);
	}

	@Override
	public FollowDTO getMyFollow(Map<String, String> map) {
		return sqlSession.selectOne("mypageSQL.getMyFollow", map);
	}

	@Override
	public void deleteMyFollow(Map<String, String> map) {
		sqlSession.delete("mypageSQL.deleteMyFollow", map);		
	}
}
