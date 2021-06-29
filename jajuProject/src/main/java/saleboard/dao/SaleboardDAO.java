package saleboard.dao;

import java.util.List;
import java.util.Map;

import mypage.bean.MessageDTO;
import saleboard.bean.SaleboardCommentDTO;
import saleboard.bean.SaleboardDTO;

public interface SaleboardDAO {

	public void saleboardWrite(SaleboardDTO saleboardDTO);

	public List<SaleboardDTO> getSaleboardList(Map<String, String> map);

	public SaleboardDTO getSaleboardView(int sale_seq);

	public int getTotal();

	public List<SaleboardDTO> getSearchSaleboardList(Map<String, String> map);

	public int getSearchTotal(Map<String, String> map);
	
	public void saleboardDelete(int sale_seq);

	public void saleboardModify(SaleboardDTO saleboardDTO);

	public void writeSaleboardComment(Map<String, String> map);

	public List<SaleboardCommentDTO> getSaleboardComment(Map<String, String> map);

	public int getCommentTotal(int sale_seq);

	public void writeReplySaleboardComment(Map<String, String> map);

	public SaleboardCommentDTO getSaleboardCommentOne(String string);

	public void commentModify(Map<String, String> map);

	public void commentDelete(SaleboardCommentDTO saleboardCommentDTO);

	public void upHit(int sale_seq);

	public List<SaleboardDTO> getSaleboardListLogin(Map<String, String> map);

	public int getTotalLogin(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListLogin(Map<String, String> map);

	public int getSearchTotalLogin(Map<String, String> map);

	public List<SaleboardDTO> getSaleboardListCategory(Map<String, String> map);

	public int getTotalCategory(String sale_category);

	public List<SaleboardDTO> getSaleboardListLoginCategory(Map<String, String> map);

	public int getTotalLoginCategory(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListCategory(Map<String, String> map);

	public int getSearchTotalCategory(Map<String, String> map);

	public List<SaleboardDTO> getSearchSaleboardListLoginCategory(Map<String, String> map);

	public int getSearchTotalLoginCategory(Map<String, String> map);
	
	public void saleStateModify(Map<String, String> map);

	public List<MessageDTO> salebuyerFindMessage(int sale_seq);

	public List<SaleboardCommentDTO> salebuyerFindComment(int sale_seq);

	public void salebuyerConfirmation(Map<String, String> map);
	
}
