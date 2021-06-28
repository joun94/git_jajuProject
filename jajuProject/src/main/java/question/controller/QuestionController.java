package question.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import question.bean.QuestionDTO;
import question.bean.QuestionPaging;
import question.service.QuestionService;

@Controller
@RequestMapping(value="serviceCenter")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value="questionWriteForm", method=RequestMethod.GET)
	public String questionWriteForm(Model model) {
		model.addAttribute("display", "/serviceCenter/questionWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="questionWrite", method=RequestMethod.POST)
	@ResponseBody
	public void reportsWrite(@ModelAttribute QuestionDTO questionDTO,
							 @RequestParam ("img[]") List<MultipartFile> list,
							 @RequestParam Map<String, String> map) { //img[] 배열이라고 알려준다, 파일이 여러개가 가능) {
		
		String filePath = "D:\\Spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";
		String fileName;
		File file;

		//파일 복사
		int i=0;
		for(MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			file = new File(filePath, fileName);
			
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
			if(i==0) {
				questionDTO.setQuestion_image1(fileName);
			}else if(i==1){
				questionDTO.setQuestion_image2(fileName);
			}else if(i==2){
				questionDTO.setQuestion_image3(fileName);
			}
			
			i++;
		
		}//for
		
		if(i==0) {
			questionDTO.setQuestion_image1("");
			questionDTO.setQuestion_image2("");
			questionDTO.setQuestion_image3("");
		}else if(i==1){
			questionDTO.setQuestion_image2("");
			questionDTO.setQuestion_image3("");
		}else if(i==2){
			questionDTO.setQuestion_image3("");
		}
		i++;
		
		//DB
		questionService.questionWrite(questionDTO);
	}
	
	@RequestMapping(value="questionList", method=RequestMethod.GET)
	public String questionList(@RequestParam(required=false, defaultValue="1") String pg,
							   Model model) {
		
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/questionList.jsp");
		return "/index";
	}

	@RequestMapping(value="getQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getNoticeList(@RequestParam(required=false, defaultValue="1") String pg) {
		//1 페이지당 10개씩
		List<QuestionDTO> list = questionService.getQuestionList(pg);
		
		//페이징 처리
		QuestionPaging questionPaging = questionService.questionPaging(pg); 
		
		//System.out.println(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("questionPaging", questionPaging);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getQuestion", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getQuestion(@RequestParam String question_seq) {

		QuestionDTO questionDTO = questionService.getQuestion(question_seq);

		ModelAndView mav = new ModelAndView();
		mav.addObject("questionDTO", questionDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getQuestionSearchList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getQuestionSearchList(@RequestParam Map<String, String> map ) {
		
		List<QuestionDTO> list = questionService.getQuestionSearchList(map);
		
		//페이징 처리
		QuestionPaging questionPaging = questionService.questionPaging(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		//System.out.println(list);
		mav.addObject("questionPaging", questionPaging);
		mav.setViewName("jsonView");
		return mav;
	}
}