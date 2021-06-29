package saleboard.service;

import java.util.List;
import java.util.Map;

import saleboard.bean.SaleboardCommentDTO;
import saleboard.bean.SaleboardCommentPaging;
import saleboard.bean.SaleboardDTO;
import saleboard.bean.SaleboardPaging;

public interface SaleboardService {

	public void saleboardWrite(SaleboardDTO saleboardDTO);

	public List<SaleboardDTO> getSaleboardList(Map<String, String> map);

	public SaleboardDTO getSaleboardView(int sale_seq);

	public SaleboardPaging saleboardPaging(int pg);

	public List<SaleboardDTO> getSearchSaleboardList(Map<String, String> map);

	public SaleboardPaging searchSaleboardPaging(Map<String, String> map);
	
	public void saleboardDelete(int sale_seq);

	public void saleboardModify(SaleboardDTO saleboardDTO);

	public void writeSaleboardComment(Map<String, String> map);

	public List<SaleboardCommentDTO> getSaleboardComment(Map<String, String> map);

	public SaleboardCommentPaging saleboardCommentPaging(Map<String, String> map);

	public void writeReplySaleboardComment(Map<String, String> map);

	public SaleboardCommentDTO getSaleboardCommentOne(String comment_seq);

	public void commentModify(Map<String, String> map);

	public void commentDelete(SaleboardCommentDTO saleboardCommentDTO);

	public void upHit(int sale_seq);

	public List<SaleboardDTO> getSaleboardListLogin(Map<String, String> map);
	
	public SaleboardPaging saleboardPagingLogin(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListLogin(Map<String, String> map);

	public SaleboardPaging searchSaleboardPagingLogin(Map<String, String> map);

	public List<SaleboardDTO> getSaleboardListCategory(Map<String, String> map);

	public SaleboardPaging saleboardPagingCategory(Map<String, String> map);

	public List<SaleboardDTO> getSaleboardListLoginCategory(Map<String, String> map);

	public SaleboardPaging saleboardPagingLoginCategory(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListCategory(Map<String, String> map);

	public SaleboardPaging searchSaleboardPagingCategory(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListLoginCategory(Map<String, String> map);

	public SaleboardPaging searchSaleboardPagingLoginCategory(Map<String, String> map);
	
}
