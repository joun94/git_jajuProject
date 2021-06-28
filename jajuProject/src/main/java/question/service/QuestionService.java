package question.service;

import java.util.List;
import java.util.Map;

import question.bean.QuestionDTO;
import question.bean.QuestionPaging;

public interface QuestionService {

	public void questionWrite(QuestionDTO questionDTO);
	
	public List<QuestionDTO> getQuestionList(String pg);

	public QuestionPaging questionPaging(String pg);

	public QuestionDTO getQuestion(String question_seq);

	public List<QuestionDTO> getQuestionSearchList(Map<String, String> map);

	public QuestionPaging questionPaging(Map<String, String> map);

}
