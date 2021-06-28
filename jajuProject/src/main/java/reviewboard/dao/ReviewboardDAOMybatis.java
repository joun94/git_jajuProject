package reviewboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reviewboard.bean.ReviewboardDTO;
import saleboard.bean.SaleboardDTO;

@Transactional
@Repository
public class ReviewboardDAOMybatis implements ReviewboardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void reviewboardWrite(ReviewboardDTO reviewboardDTO) {
		sqlSession.insert("reviewboardSQL.reviewboardWrite", reviewboardDTO);
	}
	@Override
	public void reviewboardModify(ReviewboardDTO reviewboardDTO) {
		sqlSession.update("reviewboardSQL.reviewboardModify", reviewboardDTO);
	}

	@Override
	public void reviewboardDelete(int review_seq) {
		sqlSession.delete("reviewboardSQL.reviewboardDelete", review_seq);
	}

	@Override
	public List<SaleboardDTO> review_saleReportList(Map<String, String> map) {
		return sqlSession.selectList("reviewboardSQL.review_saleReportList", map);
	}

	@Override
	public ReviewboardDTO getReviewboardView(int reivew_seq) {
		return sqlSession.selectOne("reviewboardSQL.getReviewboardView", reivew_seq);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("reviewboardSQL.getTotalA");
	}

	@Override
	public List<ReviewboardDTO> getReviewboardList(Map<String, String> map) {
		return sqlSession.selectList("reviewboardSQL.getReviewboardList", map);
	}

	@Override
	public List<ReviewboardDTO> getReviewboardSearchList(Map<String, String> map) {
		return sqlSession.selectList("reviewboardSQL.getReviewboardSearchList", map);
	}

	
	@Override
	public int getSearchTotal(Map<String, String> map) {
		return sqlSession.selectOne("reviewboardSQL.getSearchTotal", map);
	}
	@Override
	public int getTotalABuy(String string) {
		return sqlSession.selectOne("reviewboardSQL.getTotalABuy", string);
	}
	@Override
	public void upHit_review(int review_seq) {
		sqlSession.update("reviewboardSQL.upHit_review", review_seq);
	}
	@Override
	public List<ReviewboardDTO> getAllReviewboardList(String member_id) {
		return sqlSession.selectList("reviewboardSQL.getAllReviewboardList", member_id);
	}

	@Override
	public void review_mannerModify(Map<String, String> map) {
		sqlSession.update("reviewboardSQL.review_mannerModify", map);
		
	}
	@Override
	public ReviewboardDTO getPage(int review_seq) {
		return sqlSession.selectOne("reviewboardSQL.getPage", review_seq);
	}
	
}
