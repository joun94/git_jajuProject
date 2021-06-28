package freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import freeboard.bean.FreeboardCommentDTO;
import freeboard.bean.FreeboardDTO;

@Transactional
@Repository
public class FreeboardDAOMybatis implements FreeboardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void freeboardWrite(FreeboardDTO freeboardDTO) {
		sqlSession.insert("freeboardSQL.freeboardWrite", freeboardDTO);
	}

	@Override
	public FreeboardDTO getFreeboardView(int board_seq) {
		return sqlSession.selectOne("freeboardSQL.getFreeboardView", board_seq);
	}

	@Override
	public void freeboardModify(FreeboardDTO freeboardDTO) {
		 sqlSession.update("freeboardSQL.freeboardModify", freeboardDTO);
	}

	@Override
	public void freeboardDelete(int board_seq) {
		sqlSession.delete("freeboardSQL.freeboardDelete", board_seq);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("freeboardSQL.getTotalA");
	}


	@Override
	public List<FreeboardDTO> getFreeboardList(Map<String, String> map) {
		return sqlSession.selectList("freeboardSQL.getFreeboardList", map);
	}

	@Override
	public List<FreeboardDTO> getFreeboardSearchList(Map<String, String> map) {
		return sqlSession.selectList("freeboardSQL.getFreeboardSearchList", map);
		
	}

	@Override
	public int getSearchTotal(Map<String, String> map) {
		return sqlSession.selectOne("freeboardSQL.getSearchTotal", map);
	}

	@Override
	public void writeFreeboardComment(Map<String, String> map) {
		sqlSession.insert("freeboardSQL.writeFreeboardComment", map);
	}

	@Override
	public List<FreeboardCommentDTO> getFreeboardComment(Map<String, String> map) {
		return sqlSession.selectList("freeboardSQL.getFreeboardComment", map);
	}

	@Override
	public void writeReplyFreeboardComment(Map<String, String> map) {
		sqlSession.insert("freeboardSQL.writeReplyFreeboardComment", map);
		
	}

	@Override
	public FreeboardCommentDTO getFreeboardCommentOne(String string) {
		int comment_pseq = Integer.parseInt(string);
		return sqlSession.selectOne("freeboardSQL.getFreeboardCommentOne", comment_pseq);
	}


	@Override
	public void commentModify(Map<String, String> map) {
		sqlSession.update("freeboardSQL.commentModify", map);
	}

	@Override
	public void commentDelete(FreeboardCommentDTO freeboardCommentDTO) {
		sqlSession.delete("freeboardSQL.commentDelete", freeboardCommentDTO);
		
	}

	@Override
	public int getCommentTotal2(int board_seq) {
		return sqlSession.selectOne("freeboardSQL.getCommentTotal2", board_seq);
	}

	@Override
	public void upHit_board(int board_seq) {
		
		sqlSession.update("freeboardSQL.upHit_board", board_seq);
	}

	@Override
	public FreeboardDTO getPage(int board_seq) {
		return sqlSession.selectOne("freeboardSQL.getPage", board_seq);
	}


}
