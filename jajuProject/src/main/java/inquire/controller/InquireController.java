package inquire.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import inquire.bean.InquireDTO;
import inquire.bean.InquirePaging;
import inquire.service.InquireService;

@Controller
@RequestMapping(value="serviceCenter")
public class InquireController {
	@Autowired
	private InquireService inquireService;
	
	//1:1문의 작성
	@RequestMapping(value="inquireList", method=RequestMethod.GET)
	public String InquireList(Model model) {
		model.addAttribute("display", "/serviceCenter/inquireList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="inquireWriteForm", method=RequestMethod.GET)
	public String inquireWriteForm(Model model) {
		model.addAttribute("display", "/serviceCenter/inquireWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value = "inquireWrite", method = RequestMethod.POST)
	@ResponseBody
	public void inquireWrite(@ModelAttribute InquireDTO inquireDTO,
							 @RequestParam ("img[]") List<MultipartFile> list,
							 Model model) {
		String filePath;
		String fileName;
		File file;	
		
		int i = 0;
		for(MultipartFile img: list) {
			filePath = "D:\\git_home\\git_jajuProject\\jajuProject\\src\\main\\webapp\\storage";
			fileName = img.getOriginalFilename();
			file = new File(filePath,fileName);
			
//			파일 복사
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				//e.printStackTrace();
			}
			if(i == 0) {
				inquireDTO.setInquiry_image1(fileName);				
			}else if(i == 1) {
				inquireDTO.setInquiry_image2(fileName);				
			}else if(i == 2) {
				inquireDTO.setInquiry_image3(fileName);	
			}
			i++;
		}
	
		if(i == 0) {
			inquireDTO.setInquiry_image1("");
			inquireDTO.setInquiry_image2("");
			inquireDTO.setInquiry_image3("");
		}else if(i == 1) {
			inquireDTO.setInquiry_image2("");
			inquireDTO.setInquiry_image3("");
		}else if(i == 2) {
			inquireDTO.setInquiry_image3("");
		}
		i++;
		
		//DB
		inquireService.inquireWrite(inquireDTO);
		
	}
	
	//1:1문의 목록
	@RequestMapping(value = "getInquireList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getInquireList(@RequestParam(required=false, defaultValue="1") String pg) {
		
		//1페이지당 3개
		List<InquireDTO>list = inquireService.getInquireList(pg);
		
		//페이징 처리
		InquirePaging inquirePaging = inquireService.inquirePaging(pg);
		list.size();
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg",pg);
		mav.addObject("list", list);
		mav.addObject("inquirePaging", inquirePaging);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	//1:1문의확인 창
	@RequestMapping(value = "inquireView", method=RequestMethod.GET)
	public String inquireView(@RequestParam String seq, 
								 @RequestParam String pg,
								 Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display","/serviceCenter/inquireView.jsp");
		return "/index";
		
	}
	
	@RequestMapping(value = "getInquireView", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getInquireView(@RequestParam String seq) {
		InquireDTO inquireDTO = inquireService.getInquireView(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("inquireDTO",inquireDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	//1:1문의 수정
	@RequestMapping(value="inquireModifyForm", method=RequestMethod.POST)
	public String inquireModifyForm(@RequestParam String seq, 
									 @RequestParam String pg,
									 Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/inquireModifyForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getInquireModify", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getInquireModify(@RequestParam String seq,
										HttpServletResponse response){
		
		InquireDTO inquireDTO = inquireService.getInquireModify(seq);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("inquireDTO",inquireDTO);
		mav.setViewName("jsonView");
		return mav;	
	}
	
	//1:1문의 수정
	@RequestMapping(value = "inquireModify", method = RequestMethod.POST)
	@ResponseBody
	public void inquireModify(@ModelAttribute InquireDTO inquireDTO,
							  @RequestParam Map<String, MultipartFile> imgMap,
							  @RequestParam Map<String, String> checkMap,
							 Model model) {
		String filePath;
		String fileName;
		File file;	
		
		if(checkMap.get("checkbox1") != null) {
			inquireDTO.setInquiry_image1("");
		}
		else {
			if(imgMap.get("img1").getOriginalFilename() != "") {
				
					filePath = "D:\\git_home\\git_jajuProject\\jajuProject\\src\\main\\webapp\\storage";
					fileName = imgMap.get("img1").getOriginalFilename();
					file = new File(filePath,fileName);
					
					try {
						FileCopyUtils.copy(imgMap.get("img1").getInputStream(), new FileOutputStream(file));
					}catch (IOException e) {
						//e.printStackTrace();
					}
					inquireDTO.setInquiry_image1(fileName);
			}
		}
		
		if(checkMap.get("checkbox2") != null) {
			inquireDTO.setInquiry_image2("");
		}
		else {
			if(imgMap.get("img2").getOriginalFilename() != "") {
				
					filePath = "D:\\git_home\\git_jajuProject\\jajuProject\\src\\main\\webapp\\storage";
					fileName = imgMap.get("img2").getOriginalFilename();
					file = new File(filePath,fileName);
					
					try {
						FileCopyUtils.copy(imgMap.get("img2").getInputStream(), new FileOutputStream(file));
					}catch (IOException e) {
						//e.printStackTrace();
					}
					inquireDTO.setInquiry_image2(fileName);
			}
		}
		
		if(checkMap.get("checkbox3") != null) {
			inquireDTO.setInquiry_image3("");
		}
		else {
			if(imgMap.get("img3").getOriginalFilename() != "") {
			
					filePath = "D:\\git_home\\git_jajuProject\\jajuProject\\src\\main\\webapp\\storage";
					fileName = imgMap.get("img3").getOriginalFilename();
					file = new File(filePath,fileName);
					
					try {
						FileCopyUtils.copy(imgMap.get("img3").getInputStream(), new FileOutputStream(file));
					}catch (IOException e) {
						//e.printStackTrace();
					}
					inquireDTO.setInquiry_image3(fileName);
			}
		}
//		
		
		
//		int i = 0;
//		for(MultipartFile img: list) {
//			filePath = "D:\git_home\git_jajuProject\jajuProject\src\main\webapp\storage";
//			fileName = img.getOriginalFilename();
//			file = new File(filePath,fileName);
//			
//			if(!fileName.equals("")) {
//	//			파일 복사
//				try {
//					FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//				}catch (IOException e) {
//					//e.printStackTrace();
//				}
//				
//				if(i == 0) {
//						inquireDTO.setInquiry_image1(fileName);		
//				}else if(i == 1) {
//						inquireDTO.setInquiry_image2(fileName);		
//				}else if(i == 2) {
//						inquireDTO.setInquiry_image3(fileName);		
//					
//				}
//				i++;
//			}
//		}
//		
//		
////		System.out.println("inquireModify"+inquireDTO);
//		//DB
		inquireService.inquireModify(inquireDTO);
	}
	
	@RequestMapping(value = "inquireDelete", method=RequestMethod.POST)
	public ModelAndView inquireDelete(@RequestParam String seq) {
		inquireService.inquireDelete(seq);
		
		return new ModelAndView("redirect:/serviceCenter/inquireList");
		
	}
	
	//관리자 답글
	@RequestMapping(value="inquireReplyForm", method=RequestMethod.GET)
	public String inquireReplyForm(@RequestParam String seq, 
								   @RequestParam String pg,
								   Model model) {
		//답글 - 원글페이지, 원글 밑
		model.addAttribute("pseq",seq);//원글번호
		model.addAttribute("pg",pg);//원글 페이지번호
		model.addAttribute("display", "/serviceCenter/inquireReplyForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="inquireReply", method=RequestMethod.POST)
	@ResponseBody
	public void inquireReply(@RequestParam Map<String, String>map) {//pseq, pg, subject, content
		
		inquireService.inquireReply(map);
	}
}
