package question.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import question.bean.QuestionDTO;

@Transactional
@Repository
public class QuestionDAOMybatis implements QuestionDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void questionWrite(QuestionDTO questionDTO) {
		sqlSession.insert("questionSQL.questionWrite", questionDTO);
		
	}
	
	@Override
	public List<QuestionDTO> getQuestionList(Map<String, String> map) {
		List<QuestionDTO> list = sqlSession.selectList("questionSQL.getQuestionList", map);
		return list;
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("questionSQL.getTotalA");
	}

	@Override
	public QuestionDTO getQuestion(String question_seq) {
		return sqlSession.selectOne("questionSQL.getQuestion", question_seq);
	}

	@Override
	public List<QuestionDTO> getQuestionSearchList(Map<String, String> map) {
		List<QuestionDTO> list =  sqlSession.selectList("questionSQL.getQuestionSearchList", map);
		return list;
	}

	@Override
	public int getTotalSearchA(Map<String, String> map) {
		return sqlSession.selectOne("questionSQL.getTotalSearchA", map);
	}

}
