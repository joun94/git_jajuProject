package notice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import notice.bean.NoticeDTO;
import notice.bean.NoticePaging;
import notice.service.NoticeService;

@Controller
@RequestMapping(value="serviceCenter")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeWriteForm", method=RequestMethod.GET)
	public String reportWriteForm(Model model) {
		model.addAttribute("display", "/serviceCenter/noticeWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	@ResponseBody
	public void reportsWrite(@ModelAttribute NoticeDTO noticeDTO,
							 @RequestParam ("img[]") List<MultipartFile> list) { //img[] 배열이라고 알려준다, 파일이 여러개가 가능) {


		String filePath = "D:\\git_home\\git_jajuProject\\jajuProject\\src\\main\\webapp\\storage";
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
				noticeDTO.setImage0(fileName);
			}else if(i==1){
				noticeDTO.setImage1(fileName);
			}else if(i==2){
				noticeDTO.setImage2(fileName);
			}else if(i==3){
				noticeDTO.setImage3(fileName);
			}else if(i==4){
				noticeDTO.setImage4(fileName);
			}else if(i==5){
				noticeDTO.setImage5(fileName);
			}else if(i==6){
				noticeDTO.setImage6(fileName);
			}else if(i==7){
				noticeDTO.setImage7(fileName);
			}else if(i==8){
				noticeDTO.setImage8(fileName);
			}else if(i==9){
				noticeDTO.setImage9(fileName);
			}
			
			i++;
		
		}//for
		
		if(i==0) {
			noticeDTO.setImage0("");
			noticeDTO.setImage1("");
			noticeDTO.setImage2("");
			noticeDTO.setImage3("");
			noticeDTO.setImage4("");
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==1){
			noticeDTO.setImage1("");
			noticeDTO.setImage2("");
			noticeDTO.setImage3("");
			noticeDTO.setImage4("");
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==2){
			noticeDTO.setImage2("");
			noticeDTO.setImage3("");
			noticeDTO.setImage4("");
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==3){
			noticeDTO.setImage3("");
			noticeDTO.setImage4("");
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==4){
			noticeDTO.setImage4("");
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==5){
			noticeDTO.setImage5("");
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==6){
			noticeDTO.setImage6("");
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==7){
			noticeDTO.setImage7("");
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==8){
			noticeDTO.setImage8("");
			noticeDTO.setImage9("");
		}else if(i==9){
			noticeDTO.setImage9("");
		}
		
		
		//DB
		noticeService.noticeWrite(noticeDTO);
	}
	
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public String noticeList(@RequestParam(required=false, defaultValue="1") String pg,
							 Model model) {
		
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/noticeList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getNoticeList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getNoticeList(@RequestParam(required=false, defaultValue="1") String pg) {
		
		//1 페이지당 10개씩
		List<NoticeDTO> list = noticeService.getNoticeList(pg);
		
		//페이징 처리
		NoticePaging noticePaging = noticeService.noticePaging(pg); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		//System.out.println(list);
		mav.addObject("list", list);
		mav.addObject("noticePaging", noticePaging);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public String noticeView(@ModelAttribute NoticeDTO noticeDTO,
							 @RequestParam String notice_seq,
							 @RequestParam String pg,
							 Model model) {
		
		//System.out.println(notice_seq);
		//System.out.println(pg);
		model.addAttribute("noticeDTO", noticeDTO);
		model.addAttribute("notice_seq", notice_seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/noticeView.jsp");
		return "/index";
	}	
	
	@RequestMapping(value="getNotice", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getNotice(@RequestParam String notice_seq) {

		NoticeDTO noticeDTO = noticeService.getNotice(notice_seq);
		
		//noticeDTO = noticeService.getPage(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeDTO", noticeDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getNoticeSearchList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getReportSearchList(@RequestParam Map<String, String> map) {
		
		List<NoticeDTO> list = noticeService.getNoticeSearchList(map);
		
		//페이징 처리
		NoticePaging noticePaging = noticeService.noticePaging(map);
		
		ModelAndView mav = new ModelAndView();
		//System.out.println(map.get("pg"));
		mav.addObject("pg", map.get("pg"));
		mav.addObject("list", list);
		mav.addObject("noticePaging", noticePaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getPage", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getPage(@RequestParam String notice_seq) {
		
		NoticeDTO noticeDTO = noticeService.getPage(notice_seq);
		
		//System.out.println(noticeDTO.getPrev_subject());
		//System.out.println(noticeDTO.getNext_subject());
		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeDTO", noticeDTO);
		mav.setViewName("jsonView");
		return mav;
	}

}
