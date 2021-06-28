package question.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import question.bean.QuestionDTO;
import question.bean.QuestionPaging;
import question.dao.QuestionDAO;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private QuestionPaging questionPaging;
	
	@Override
	public void questionWrite(QuestionDTO questionDTO) {
		questionDAO.questionWrite(questionDTO);
		
	}
	
	@Override
	public List<QuestionDTO> getQuestionList(String pg) {
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, String> map = new HashedMap<String, String>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return questionDAO.getQuestionList(map);
	}

	@Override
	public QuestionPaging questionPaging(String pg) {
		int totalA = questionDAO.getTotalA(); //총글수
		
		questionPaging.setCurrentPage(Integer.parseInt(pg));//현재 페이지
		questionPaging.setPageBlock(5);
		questionPaging.setPageSize(10);
		questionPaging.setTotalA(totalA);
		questionPaging.makePagingHTML();
		
		return questionPaging;
	}

	@Override
	public QuestionDTO getQuestion(String question_seq) {
		return questionDAO.getQuestion(question_seq);
	}

	@Override
	public List<QuestionDTO> getQuestionSearchList(Map<String, String> map) {
		System.out.println(map);
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(map.get("pg"))*10; //map에 언제 pg 값을 넣었나?
		int startNum = endNum-9;	
		
		//pg, searchOption, keyword, startNum, endNum
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return questionDAO.getQuestionSearchList(map);
	}

	@Override
	public QuestionPaging questionPaging(Map<String, String> map) {
		int totalA = questionDAO.getTotalSearchA(map); //검색한 총글수
		
		questionPaging.setCurrentPage(Integer.parseInt(map.get("pg")));//현재 페이지
		questionPaging.setPageBlock(5);
		questionPaging.setPageSize(10);
		questionPaging.setTotalA(totalA);
		questionPaging.makePagingHTML();
		
		return questionPaging;
	}

}
