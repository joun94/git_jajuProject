package reviewboard.dao;

import java.util.List;
import java.util.Map;

import reviewboard.bean.ReviewboardDTO;
import saleboard.bean.SaleboardDTO;

public interface ReviewboardDAO {

	public void reviewboardWrite(ReviewboardDTO reviewboardDTO);

	public void reviewboardModify(ReviewboardDTO reviewboardDTO);

	public void reviewboardDelete(int review_seq);

	public List<SaleboardDTO> review_saleReportList(Map<String, String> map);

	public ReviewboardDTO getReviewboardView(int reivew_seq);

	public int getTotalA();

	public List<ReviewboardDTO> getReviewboardList(Map<String, String> map);

	public List<ReviewboardDTO> getReviewboardSearchList(Map<String, String> map);

	public int getSearchTotal(Map<String, String> map);

	public int getTotalABuy(String string);

	public void upHit_review(int review_seq);

	public List<ReviewboardDTO> getAllReviewboardList(String member_id);

	public void review_mannerModify(Map<String, String> map);

	public ReviewboardDTO getPage(int review_seq);


}
