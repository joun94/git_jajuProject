package saleboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mypage.bean.MessageDTO;
import saleboard.bean.SaleboardCommentDTO;
import saleboard.bean.SaleboardDTO;

@Transactional
@Repository
public class SaleboardDAOMybatis implements SaleboardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void saleboardWrite(SaleboardDTO saleboardDTO) {
		sqlSession.insert("saleboardSQL.saleboardWrite", saleboardDTO);
		
	}

	@Override
	public List<SaleboardDTO> getSaleboardList(Map<String, String> map) {
		return sqlSession.selectList("saleboardSQL.getSaleboardList", map);
	}

	@Override
	public SaleboardDTO getSaleboardView(int sale_seq) {
		return sqlSession.selectOne("saleboardSQL.getSaleboardView", sale_seq);
	}

	@Override
	public int getTotal() {
		return sqlSession.selectOne("saleboardSQL.getTotal");
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardList(Map<String, String> map) {
		return sqlSession.selectList("saleboardSQL.getSearchSaleboardList", map);
	}

	@Override
	public int getSearchTotal(Map<String, String> map) {
		return sqlSession.selectOne("saleboardSQL.getSearchTotal", map);
	}
	
	@Override
	public void saleboardDelete(int sale_seq) {
		sqlSession.delete("saleboardSQL.saleboardDelete", sale_seq);
	}

	@Override
	public void saleboardModify(SaleboardDTO saleboardDTO) {
		sqlSession.update("saleboardSQL.saleboardModify", saleboardDTO);
	}

	@Override
	public void writeSaleboardComment(Map<String, String> map) {
		sqlSession.insert("saleboardSQL.writeSaleboardComment",map);
	}

	@Override
	public List<SaleboardCommentDTO> getSaleboardComment(Map<String, String> map) {
		return sqlSession.selectList("saleboardSQL.getSaleboardComment", map);
	}

	@Override
	public int getCommentTotal(int sale_seq) {
		return sqlSession.selectOne("saleboardSQL.getCommentTotal", sale_seq);
	}

	@Override
	public void writeReplySaleboardComment(Map<String, String> map) {
		sqlSession.insert("saleboardSQL.writeReplySaleboardComment", map);
	}

	@Override
	public SaleboardCommentDTO getSaleboardCommentOne(String string) {
		int comment_pseq = Integer.parseInt(string);
		return sqlSession.selectOne("saleboardSQL.getSaleboardCommentOne", comment_pseq);
	}

	@Override
	public void commentModify(Map<String, String> map) {
		sqlSession.update("saleboardSQL.commentModify", map);
	}

	@Override
	public void commentDelete(SaleboardCommentDTO saleboardCommentDTO) {
		sqlSession.delete("saleboardSQL.commentDelete", saleboardCommentDTO);
		
	}

	@Override
	public void upHit(int sale_seq) {
		sqlSession.update("saleboardSQL.upHit", sale_seq);
		
	}

	@Override
	public List<SaleboardDTO> getSaleboardListLogin(Map<String, String> map) {
		return sqlSession.selectList("saleboardSQL.getSaleboardListLogin", map);
	}

	@Override
	public int getTotalLogin(Map<String, String> map) {
		int a =0;
		int b =0;
		int c =0;
		if(!map.get("location1").equals("")) {
			a = sqlSession.selectOne("saleboardSQL.getTotalLogin1", map);
		}
		if(!map.get("location2").equals("")) {
			b = sqlSession.selectOne("saleboardSQL.getTotalLogin2", map);
		}
		if(!map.get("location3").equals("")) {
			c = sqlSession.selectOne("saleboardSQL.getTotalLogin3", map);
		}
		
		return a+b+c;
	}

	@Override
	public List<SaleboardDTO> getSearchSaleboardListLogin(Map<String, String> map) {
//		System.out.println(map.get("location1"));
//		System.out.println(map.get("searchText"));
//		System.out.println(map.get("startNum"));
//		System.out.println(map.get("endNum"));
//		System.out.println(map.get("sale_category"));
		return sqlSession.selectList("saleboardSQL.getSearchSaleboardListLogin", map);
	}

	@Override
	public int getSearchTotalLogin(Map<String, String> map) {
		int a =0;
		int b =0;
		int c =0;
		if(!map.get("location1").equals("")) {
			a = sqlSession.selectOne("saleboardSQL.getSearchTotalLogin1", map);
		}
		if(!map.get("location2").equals("")) {
			b = sqlSession.selectOne("saleboardSQL.getSearchTotalLogin2", map);
		}
		if(!map.get("location3").equals("")) {
			c = sqlSession.selectOne("saleboardSQL.getSearchTotalLogin3", map);
		}
		
		return a+b+c;
	}

	@Override
	public void saleStateModify(Map<String,String> map) {
		sqlSession.update("saleboardSQL.saleStateModify", map);
	}
	/*-----*/

	@Override
	public List<MessageDTO> salebuyerFindMessage(int sale_seq) {
		return sqlSession.selectList("saleboardSQL.salebuyerFindMessage", sale_seq);	
	}

	@Override
	public List<SaleboardCommentDTO> salebuyerFindComment(int sale_seq) {
		return sqlSession.selectList("saleboardSQL.salebuyerFindComment", sale_seq);
	}

	@Override
	public void salebuyerConfirmation(Map<String, String> map) {
		sqlSession.update("saleboardSQL.salebuyerConfirmation", map);	
	}
}
