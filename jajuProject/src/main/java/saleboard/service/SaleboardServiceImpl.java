package saleboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypage.bean.MessageDTO;
import saleboard.bean.SaleboardCommentDTO;
import saleboard.bean.SaleboardCommentPaging;
import saleboard.bean.SaleboardDTO;
import saleboard.bean.SaleboardPaging;
import saleboard.dao.SaleboardDAO;

@Service
public class SaleboardServiceImpl implements SaleboardService {
	@Autowired
	private SaleboardCommentPaging saleboardCommentPaging;
	@Autowired
	private SaleboardPaging saleboardPaging;

	@Autowired
	private SaleboardDAO saleboardDAO;
	
	@Override
	public void saleboardWrite(SaleboardDTO saleboardDTO) {

		
		if(saleboardDTO.getSale_state() == null) {
			saleboardDTO.setSale_state("");
		}
		if(saleboardDTO.getSale_subject() == null) {
			saleboardDTO.setSale_subject("");
		}
		if(saleboardDTO.getSale_nego() == null) {
			saleboardDTO.setSale_nego("");
		}
		if(saleboardDTO.getSale_category() == null) {
			saleboardDTO.setSale_category("");
		}
		if(saleboardDTO.getSale_condition() == null) {
			saleboardDTO.setSale_condition("");
		}
		if(saleboardDTO.getSale_direct() == null) {
			saleboardDTO.setSale_direct("");
		}
		if(saleboardDTO.getSale_delivery() == null) {
			saleboardDTO.setSale_delivery("");
		}
		if(saleboardDTO.getSale_onlineTran() == null) {
			saleboardDTO.setSale_onlineTran("");
		}
		
		if(saleboardDTO.getSale_content() == null) {
			saleboardDTO.setSale_content("");
		}

		if(saleboardDTO.getSale_hide() == null) {
			saleboardDTO.setSale_hide("");
		}
		
		if(saleboardDTO.getSale_buyer() == null) {
			saleboardDTO.setSale_buyer("");
		}
		
		if(saleboardDTO.getSale_image1() == null) {
			saleboardDTO.setSale_image1("");
		}
		
		if(saleboardDTO.getSale_image2() == null) {
			saleboardDTO.setSale_image2("");
		}
		if(saleboardDTO.getSale_image3() == null) {
			saleboardDTO.setSale_image3("");
		}
		if(saleboardDTO.getSale_image4() == null) {
			saleboardDTO.setSale_image4("");
		}
		if(saleboardDTO.getSale_image5() == null) {
			saleboardDTO.setSale_image5("");
		}
		if(saleboardDTO.getSale_image6() == null) {
			saleboardDTO.setSale_image6("");
		}
		if(saleboardDTO.getSale_image7() == null) {
			saleboardDTO.setSale_image7("");
		}
		if(saleboardDTO.getSale_image8() == null) {
			saleboardDTO.setSale_image8("");
		}
		if(saleboardDTO.getSale_image9() == null) {
			saleboardDTO.setSale_image9("");
		}
		
		if(saleboardDTO.getSale_image10() == null) {
			saleboardDTO.setSale_image10("");
		}
		
		saleboardDAO.saleboardWrite(saleboardDTO);
		
		
	}

	@Override
	public List<SaleboardDTO> getSaleboardList(Map<String, String> map) {
		
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 15;
		int startNum = endNum - 14;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSaleboardList(map);
	}

	@Override
	public SaleboardDTO getSaleboardView(int sale_seq) {
		return saleboardDAO.getSaleboardView(sale_seq);
	}

	@Override
	public SaleboardPaging saleboardPaging(int pg) {
				
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getTotal();
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardList(Map<String, String> map) {
		
		int endNum = Integer.parseInt(map.get("pg")) * 15;
		int startNum = endNum - 14;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");

		
		return saleboardDAO.getSearchSaleboardList(map);
	}

	@Override
	public SaleboardPaging searchSaleboardPaging(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getSearchTotal(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}
	
	@Override
	public void saleboardDelete(int sale_seq) {
		saleboardDAO.saleboardDelete(sale_seq);
	}

	@Override
	public void saleboardModify(SaleboardDTO saleboardDTO) {
		
		if(saleboardDTO.getSale_state() == null) {
			saleboardDTO.setSale_state("");
		}
		
		if(saleboardDTO.getSale_subject() == null) {
			saleboardDTO.setSale_subject("");
		}
		
		if(saleboardDTO.getSale_nego() == null) {
			saleboardDTO.setSale_nego("");
		}
		
		if(saleboardDTO.getSale_category() == null) {
			saleboardDTO.setSale_category("");
		}
		
		if(saleboardDTO.getSale_condition() == null) {
			saleboardDTO.setSale_condition("");
		}
		
		if(saleboardDTO.getSale_direct() == null) {
			saleboardDTO.setSale_direct("");
		}
		
		if(saleboardDTO.getSale_delivery() == null) {
			saleboardDTO.setSale_delivery("");
		}
		
		if(saleboardDTO.getSale_onlineTran() == null) {
			saleboardDTO.setSale_onlineTran("");
		}
		
		if(saleboardDTO.getSale_content() == null) {
			saleboardDTO.setSale_content("");
		}
		
		if(saleboardDTO.getMember_id() == null) {
			saleboardDTO.setMember_id("");
		}
		
		if(saleboardDTO.getSale_hide() == null) {
			saleboardDTO.setSale_hide("");
		}
		
		if(saleboardDTO.getSale_buyer() == null) {
			saleboardDTO.setSale_buyer("");
		}
		
		if(saleboardDTO.getSale_image1() == null) {
			saleboardDTO.setSale_image1("");
		}
		
		if(saleboardDTO.getSale_image2() == null) {
			saleboardDTO.setSale_image2("");
		}
		if(saleboardDTO.getSale_image3() == null) {
			saleboardDTO.setSale_image3("");
		}
		if(saleboardDTO.getSale_image4() == null) {
			saleboardDTO.setSale_image4("");
		}
		if(saleboardDTO.getSale_image5() == null) {
			saleboardDTO.setSale_image5("");
		}
		if(saleboardDTO.getSale_image6() == null) {
			saleboardDTO.setSale_image6("");
		}
		if(saleboardDTO.getSale_image7() == null) {
			saleboardDTO.setSale_image7("");
		}
		if(saleboardDTO.getSale_image8() == null) {
			saleboardDTO.setSale_image8("");
		}
		if(saleboardDTO.getSale_image9() == null) {
			saleboardDTO.setSale_image9("");
		}
		
		if(saleboardDTO.getSale_image10() == null) {
			saleboardDTO.setSale_image10("");
		}
		
		saleboardDAO.saleboardModify(saleboardDTO);
		
	}

	@Override
	public void writeSaleboardComment(Map<String, String> map) {
		saleboardDAO.writeSaleboardComment(map);
	}

	@Override
	public List<SaleboardCommentDTO> getSaleboardComment(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSaleboardComment(map);
	}

	@Override
	public SaleboardCommentPaging saleboardCommentPaging(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardCommentPaging.setCurrentPage(pg);
		saleboardCommentPaging.setPageBlock(3);
		saleboardCommentPaging.setPageSize(5);
		int sale_seq = Integer.parseInt(map.get("sale_seq"));
		int totalA = saleboardDAO.getCommentTotal(sale_seq);
		saleboardCommentPaging.setTotalA(totalA);		
		
		saleboardCommentPaging.makePagingHTML();
		
		return saleboardCommentPaging;
	}

	@Override
	public void writeReplySaleboardComment(Map<String, String> map) {
		
		SaleboardCommentDTO pDTO = saleboardDAO.getSaleboardCommentOne(map.get("comment_pseq"));
		map.put("comment_ref", pDTO.getComment_ref()+"");
		map.put("comment_lev", pDTO.getComment_lev()+"");
		map.put("comment_step", pDTO.getComment_step()+"");
		
		saleboardDAO.writeReplySaleboardComment(map);		
	}

	@Override
	public SaleboardCommentDTO getSaleboardCommentOne(String comment_seq) {
		return saleboardDAO.getSaleboardCommentOne(comment_seq);
	}

	@Override
	public void commentModify(Map<String, String> map) {
		saleboardDAO.commentModify(map);	
	}

	@Override
	public void commentDelete(SaleboardCommentDTO saleboardCommentDTO) {
		saleboardDAO.commentDelete(saleboardCommentDTO);
		
	}

	@Override
	public void upHit(int sale_seq) {
		saleboardDAO.upHit(sale_seq);
	}

	@Override
	public List<SaleboardDTO> getSaleboardListLogin(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 15;
		int startNum = endNum - 14;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSaleboardListLogin(map);
	}
	
	@Override
	public SaleboardPaging saleboardPagingLogin(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getTotalLogin(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardListLogin(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg")) * 15;
		int startNum = endNum - 14;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");

		
		return saleboardDAO.getSearchSaleboardListLogin(map);
	}

	@Override
	public SaleboardPaging searchSaleboardPagingLogin(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getSearchTotalLogin(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSaleboardListCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 15;
		int startNum = endNum - 14;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSaleboardListCategory(map);
	}

	@Override
	public SaleboardPaging saleboardPagingCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		String sale_category = map.get("sale_category"); 
		int totalA = saleboardDAO.getTotalCategory(sale_category);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSaleboardListLoginCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 15;
		int startNum = endNum - 14;
				
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSaleboardListLoginCategory(map);
	}

	@Override
	public SaleboardPaging saleboardPagingLoginCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getTotalLoginCategory(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardListCategory(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg")) * 15;
		int startNum = endNum - 14;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSearchSaleboardListCategory(map);
	}

	@Override
	public SaleboardPaging searchSaleboardPagingCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getSearchTotalCategory(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardListLoginCategory(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg")) * 15;
		int startNum = endNum - 14;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return saleboardDAO.getSearchSaleboardListLoginCategory(map);
	}

	@Override
	public SaleboardPaging searchSaleboardPagingLoginCategory(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		saleboardPaging.setCurrentPage(pg);
		saleboardPaging.setPageBlock(3);
		saleboardPaging.setPageSize(15);
		int totalA = saleboardDAO.getSearchTotalLoginCategory(map);
		saleboardPaging.setTotalA(totalA);		
		
		saleboardPaging.makePagingHTML();
		
		return saleboardPaging;
	}
	@Override
	public void saleStateModify(Map<String,String> map)  {
		saleboardDAO.saleStateModify(map);
	}
	/*-----*/
	@Override
	public List<MessageDTO> salebuyerFindMessage(int sale_seq) {
		return saleboardDAO.salebuyerFindMessage(sale_seq);
	}

	@Override
	public List<SaleboardCommentDTO> salebuyerFindComment(int sale_seq) {
		return saleboardDAO.salebuyerFindComment(sale_seq);
	}

	@Override
	public void salebuyerConfirmation(Map<String, String> map) {
		saleboardDAO.salebuyerConfirmation(map);
		
	}
}
