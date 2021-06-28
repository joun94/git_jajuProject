package question.dao;

import java.util.List;
import java.util.Map;

import question.bean.QuestionDTO;

public interface QuestionDAO {

	public void questionWrite(QuestionDTO questionDTO);
	
	public List<QuestionDTO> getQuestionList(Map<String, String> map);

	public int getTotalA();

	public QuestionDTO getQuestion(String question_seq);

	public List<QuestionDTO> getQuestionSearchList(Map<String, String> map);

	public int getTotalSearchA(Map<String, String> map);


}
