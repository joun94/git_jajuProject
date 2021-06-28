package inquire.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inquire.bean.InquireDTO;

@Repository
@Transactional
public class InquireDAOMybatis implements InquireDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void inquireWrite(InquireDTO inquireDTO) {
		sqlSession.insert("inquireSQL.inquireWrite", inquireDTO);
		
	}

	@Override
	public List<InquireDTO> getInquireList(Map<String, Integer> map) {
		return sqlSession.selectList("inquireSQL.getInquireList", map);
	}

	@Override
	public InquireDTO getInquireView(String seq) {
		return sqlSession.selectOne("inquireSQL.getInquireView", Integer.parseInt(seq));
	}

	@Override
	public InquireDTO getInquireModify(String seq) {
		return sqlSession.selectOne("inquireSQL.getInquireModify", Integer.parseInt(seq));
	}

	@Override
	public void inquireModify(InquireDTO inquireDTO) {
		sqlSession.update("inquireSQL.inquireModify", inquireDTO);	
		
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("inquireSQL.getTotalA");
	}

	@Override
	public void inquireDelete(String seq) {
		sqlSession.delete("inquireSQL.inquireDelete", Integer.parseInt(seq));
		
	}

	@Override
	public void inquireReply(Map<String, String> map) {
		
		sqlSession.insert("inquireSQL.inquireReply", map);
	}
	
}
