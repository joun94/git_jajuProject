package reviewboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reviewboard.bean.ReviewboardDTO;
import reviewboard.bean.ReviewboardPaging;
import reviewboard.dao.ReviewboardDAO;
import saleboard.bean.SaleboardDTO;

@Service
public class ReviewboardServiceImpl implements ReviewboardService {
	
	@Autowired
	private ReviewboardPaging reviewboardPaging;
	
	@Autowired
	private ReviewboardDAO reviewboardDAO;

	@Override
	public void reviewboardWrite(ReviewboardDTO reviewboardDTO) {

		if(reviewboardDTO.getSale_image1() == null) {
			reviewboardDTO.setSale_image1("");
		}
		if(reviewboardDTO.getMember_id() == null) {
			reviewboardDTO.setMember_id("");
		}
		if(reviewboardDTO.getReview_id() == null) {
			reviewboardDTO.setReview_id("");
		}
		if(reviewboardDTO.getSale_subject() == null) {
			reviewboardDTO.setSale_subject("");
		}
		if(reviewboardDTO.getReview_subject() == null) {
			reviewboardDTO.setReview_subject("");
		}
		if(reviewboardDTO.getReview_content() == null) {
			reviewboardDTO.setReview_content("");
		}
		if(reviewboardDTO.getReview_image1() == null) {
			reviewboardDTO.setReview_image1("");
		}
		if(reviewboardDTO.getReview_image2() == null) {
			reviewboardDTO.setReview_image2("");
		}
		if(reviewboardDTO.getReview_image3() == null) {
			reviewboardDTO.setReview_image3("");
		}
		reviewboardDAO.reviewboardWrite(reviewboardDTO);
	}

	@Override
	public void reviewboardModify(ReviewboardDTO reviewboardDTO) {
		if(reviewboardDTO.getSale_image1() == null) {
			reviewboardDTO.setSale_image1("");
		}
		if(reviewboardDTO.getMember_id() == null) {
			reviewboardDTO.setMember_id("");
		}
		if(reviewboardDTO.getReview_id() == null) {
			reviewboardDTO.setReview_id("");
		}
		if(reviewboardDTO.getSale_subject() == null) {
			reviewboardDTO.setSale_subject("");
		}
		if(reviewboardDTO.getReview_subject() == null) {
			reviewboardDTO.setReview_subject("");
		}
		if(reviewboardDTO.getReview_content() == null) {
			reviewboardDTO.setReview_content("");
		}
		if(reviewboardDTO.getReview_image1() == null) {
			reviewboardDTO.setReview_image1("");
		}
		if(reviewboardDTO.getReview_image2() == null) {
			reviewboardDTO.setReview_image2("");
		}
		if(reviewboardDTO.getReview_image3() == null) {
			reviewboardDTO.setReview_image3("");
		}
		reviewboardDAO.reviewboardModify(reviewboardDTO);
	}

	@Override
	public void reviewboardDelete(int review_seq) {
		reviewboardDAO.reviewboardDelete(review_seq);
		
	}

	@Override
	public List<SaleboardDTO> review_saleReportList(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 10;
		int startNum = endNum - 9;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		
		return reviewboardDAO.review_saleReportList(map);
	}

	@Override
	public ReviewboardDTO getReviewboardView(int reivew_seq) {
		return reviewboardDAO.getReviewboardView(reivew_seq);
	}

	@Override
	public List<ReviewboardDTO> getReviewboardList(String pg) {
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return reviewboardDAO.getReviewboardList(map);
	}

	@Override
	public ReviewboardPaging reviewboardPaging(String pg) {
		int totalA = reviewboardDAO.getTotalA();
		
		reviewboardPaging.setCurrentPage(Integer.parseInt(pg));
		reviewboardPaging.setPageBlock(5);
		reviewboardPaging.setPageSize(10);
		reviewboardPaging.setTotalA(totalA);
		reviewboardPaging.makePagingHTML();
		
		
		return reviewboardPaging;
	}

	 
	 	@Override
	public List<ReviewboardDTO> getReviewboardSearchList(Map<String, String> map) {
			//1페이지당 5개씩
		
			int endNum = Integer.parseInt(map.get("searchPg"))*10; //map에서 pg를 꺼냄
			int startNum = endNum - 9;
				
			map.put("startNum", startNum+"");
			map.put("endNum", endNum+"");
		
			return reviewboardDAO.getReviewboardSearchList(map);
	}

	@Override
	public ReviewboardPaging reviewboardPaging_search(Map<String, String> map) {
		int totalA = reviewboardDAO.getSearchTotal(map); //검색한 총 글수
		
		reviewboardPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); //현재 페이지
		reviewboardPaging.setPageBlock(5);
		reviewboardPaging.setPageSize(10);
		reviewboardPaging.setTotalA(totalA);
		reviewboardPaging.makePagingHTML();
		
		return reviewboardPaging;
	}


	@Override
	public ReviewboardPaging reviewboardPaging(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		reviewboardPaging.setCurrentPage(pg);
		reviewboardPaging.setPageBlock(3);
		reviewboardPaging.setPageSize(10);
		int totalA = reviewboardDAO.getTotalABuy(map.get("id"));
		reviewboardPaging.setTotalA(totalA);		
		
		reviewboardPaging.makePagingHTML();
		
		return reviewboardPaging;
	}

	@Override
	public void upHit_review(int review_seq) {
		reviewboardDAO.upHit_review(review_seq);
	}

	@Override
	public List<ReviewboardDTO> getAllReviewboardList(String member_id) {
		return reviewboardDAO.getAllReviewboardList(member_id);
	}

	@Override
	public void review_mannerModify(Map<String, String> map) {
		reviewboardDAO.review_mannerModify(map);
		
	}

	@Override
	public ReviewboardDTO getPage(int review_seq) {
		return reviewboardDAO.getPage(review_seq);
	}


}
