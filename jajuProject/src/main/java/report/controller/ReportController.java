package report.controller;

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

import inquire.bean.InquireDTO;
import member.bean.MemberDTO;
import report.bean.CommentDTO;
import report.bean.ReportDTO;
import report.bean.ReportPaging;
import report.service.ReportService;

@Controller
@RequestMapping(value="serviceCenter")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="reportWriteForm", method=RequestMethod.GET)
	public String reportWriteForm(Model model) {
		model.addAttribute("display", "/serviceCenter/reportWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getMemberName", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMemberName(@RequestParam String report_id) {
		
		MemberDTO memberDTO = reportService.getMemberName(report_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	
	@RequestMapping(value="reportWrite", method=RequestMethod.POST)
	@ResponseBody
	public void reportsWrite(@ModelAttribute ReportDTO reportDTO,
							 @RequestParam ("img[]") List<MultipartFile> list,
							 HttpSession session) { //img[] 배열이라고 알려준다, 파일이 여러개가 가능) {


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
				reportDTO.setReport_image1(fileName);
			}else if(i==1){
				reportDTO.setReport_image2(fileName);
			}else if(i==2){
				reportDTO.setReport_image3(fileName);
			}
			
			i++;
		
		}//for
		
		if(i==0) {
			reportDTO.setReport_image1("");
			reportDTO.setReport_image2("");
			reportDTO.setReport_image3("");
		}else if(i==1){
			reportDTO.setReport_image2("");
			reportDTO.setReport_image3("");
		}else if(i==2){
			reportDTO.setReport_image3("");
		}
		
		//DB
		reportService.reportWrite(reportDTO);
	}
	
	@RequestMapping(value="reportList", method=RequestMethod.GET)
	public String reportList(@RequestParam(required=false, defaultValue="1") String pg,
							 HttpSession session, Model model) {
		
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/reportList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getReportList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getReportList(@RequestParam(required=false, defaultValue="1") String pg,
									  @RequestParam String report_id,
									  HttpSession session) {
		
		//세션
		String memId = (String) session.getAttribute("memId");
		
		//System.out.println(memId+ "       1");
		//1 페이지당 10개씩
		List<ReportDTO> list = reportService.getReportList(pg, report_id);
		
		//페이징 처리
		ReportPaging reportPaging = reportService.reportPaging(pg, report_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("report_id", report_id);
		mav.addObject("reportPaging", reportPaging);
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="reportView", method=RequestMethod.GET)
	public String reportView(@RequestParam String report_seq,
							 @RequestParam String pg,
							 HttpSession session, Model model) {
		
		model.addAttribute("report_seq", report_seq);
		//model.addAttribute("comment_seq", comment_seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/reportView.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getReport", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getReport(@RequestParam String report_seq,
								  HttpSession session) {

		ReportDTO reportDTO = reportService.getReport(report_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("reportDTO", reportDTO);
		//System.out.println(reportDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="reportModifyForm", method=RequestMethod.POST)
	public String reportModifyForm(@RequestParam String report_seq,
			  					   @RequestParam String pg,
			  					   Model model) {
		
		model.addAttribute("report_seq", report_seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/serviceCenter/reportModifyForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="reportModify", method=RequestMethod.POST)
	@ResponseBody
	public void reportModify(@ModelAttribute ReportDTO reportDTO,
							 @RequestParam Map<String, MultipartFile> imgMap,
							 @RequestParam Map<String, String> checkMap,
							 Model model) {
		
		String filePath;
		String fileName;
		File file;	
		
		if(checkMap.get("checkbox1") != null) {
			reportDTO.setReport_image1("");
			
		}else {
			
		if(imgMap.get("img1").getOriginalFilename() != "") {
		
			filePath = "D:\\spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";
			fileName = imgMap.get("img1").getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				FileCopyUtils.copy(imgMap.get("img1").getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				//e.printStackTrace();
			}
			reportDTO.setReport_image1(fileName);
			}
		}
		
		if(checkMap.get("checkbox2") != null) {
			reportDTO.setReport_image2("");
		}else {
			
		if(imgMap.get("img2").getOriginalFilename() != "") {
		
			filePath = "D:\\spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";
			fileName = imgMap.get("img2").getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				FileCopyUtils.copy(imgMap.get("img2").getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				//e.printStackTrace();
			}
			reportDTO.setReport_image2(fileName);
			}
		}
		
		if(checkMap.get("checkbox3") != null) {
			reportDTO.setReport_image3("");
			
		}else {
			
		if(imgMap.get("img3").getOriginalFilename() != "") {
		
			filePath = "D:\\spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";
			fileName = imgMap.get("img3").getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				FileCopyUtils.copy(imgMap.get("img3").getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				//e.printStackTrace();
			}
			reportDTO.setReport_image3(fileName);
			}
		}
		
		//DB
		reportService.reportModify(reportDTO);
	}	
	
	@RequestMapping(value="reportDelete", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView reportDelete(@RequestParam String report_seq) {
		
		reportService.reportDelete(report_seq);
		
		return new ModelAndView("redirect:/serviceCenter/reportList");
	}
	
	@RequestMapping(value="commentWrite", method=RequestMethod.POST)
	@ResponseBody
	public void commentWrite(@RequestParam Map<String, String> map) {
		
		//System.out.println(map);
		reportService.commentWrite(map);// seq값이랑 DTO 값
	}
	
	@RequestMapping(value="getCommentList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getCommentList(@RequestParam String report_seq) {
		
		List<CommentDTO> list = reportService.getCommentList(report_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="getComment", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getComment(@RequestParam String comment_seq) {

		CommentDTO commentDTO = reportService.getComment(comment_seq);
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("report_seq", report_seq);
		//mav.addObject("comment_seq", comment_seq);
		mav.addObject("commentDTO", commentDTO);
		//System.out.println(commentDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="commentModify", method=RequestMethod.POST)
	@ResponseBody
	public void commentModify(@ModelAttribute CommentDTO commentDTO) {
		//System.out.println(commentDTO);
		reportService.commentModify(commentDTO);
	}
	
	@RequestMapping(value="commentDelete", method=RequestMethod.POST)
	@ResponseBody
	public void commentDelete(@RequestParam Map<String, String> map) {
		
		reportService.commentDelete(map);
		
	}
}
