package reviewboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import reviewboard.bean.ReviewboardDTO;
import reviewboard.bean.ReviewboardPaging;
import reviewboard.service.ReviewboardService;
import saleboard.bean.SaleboardDTO;

@Controller
@RequestMapping(value="reviewboard")
public class ReviewboardController {
	@Autowired
	private ReviewboardService reviewboardService;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("display", "/main/body.jsp");
		return "/index";
		
	}
	
	//write
		@RequestMapping(value="reviewboardWriteForm", method=RequestMethod.GET)
		public String reviewboardWriteForm(Model model, HttpSession session) {
			
			//ID 세션 가져오기
			String memId = (String) session.getAttribute("memId");
			
			model.addAttribute("memId", memId);
			model.addAttribute("display", "/reviewboard/reviewboardWriteForm.jsp");
			return "/index";
		}
		
		@RequestMapping(value="reviewboardWrite", method=RequestMethod.POST)
		@ResponseBody
		public void reviewboardWrite(HttpSession session, Model model,
				@ModelAttribute ReviewboardDTO reviewboardDTO,
				@RequestParam("file[]") List<MultipartFile> list) {
			
			//System.out.println(reviewboardDTO);
			//ID 세션
			String memId = (String) session.getAttribute("memId");
			model.addAttribute("memId", memId);
			
			String filePath =  "D:\\Spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";

			String fileName;
			File file;
			
			for(int i=0; i<list.size(); i++) {
				fileName = list.get(i).getOriginalFilename();
				file = new File(filePath, fileName);
				
				System.out.println(fileName);
				
				//파일 복사
				try {
					FileCopyUtils.copy(list.get(i).getInputStream(), new FileOutputStream(file));
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				if(fileName!=null) {
					if(i==0) reviewboardDTO.setReview_image1(fileName);
					else if (i==1) reviewboardDTO.setReview_image2(fileName);
					else if (i==2) reviewboardDTO.setReview_image3(fileName);

				} 

			} //for
			reviewboardService.reviewboardWrite(reviewboardDTO);
		
		}
	
		//View
		@RequestMapping(value="reviewboardView", method=RequestMethod.GET)
		public String reviewboardView(@RequestParam int review_seq, 
										Model model, 
										HttpSession session) {
			
			//ID 세션 가져오기
			String memId = (String) session.getAttribute("memId");
			
			model.addAttribute("memId", memId);
			model.addAttribute("review_seq", review_seq);
			
			model.addAttribute("display","/reviewboard/reviewboardView.jsp");
			return "/index";
			
		}
	
	@RequestMapping(value="getReviewboardView", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getReviewboardView(@RequestParam int review_seq, 
											HttpSession session,
											HttpServletResponse response,
											HttpServletRequest request) {

		String memId = (String) session.getAttribute("memId");
		
		
		//조회수 쿠키
		int sw = 0;
		Cookie[] cookie = request.getCookies();
		for (Cookie coo : cookie) {
			if(coo.getName().equals(memId) && coo.getValue().equals(review_seq+"")) {
				sw = 1;
			}
		}
		//조회수 새로고침 방지
		if(sw != 1) {
			Cookie cookies = new Cookie(memId, review_seq+"");
			cookies.setMaxAge(24*60*60);//시간설정 초단위
			response.addCookie(cookies);//클라이언트 보내기
			reviewboardService.upHit_review(review_seq);
		}
		
		ModelAndView mav = new ModelAndView();
		ReviewboardDTO reviewboardDTO = reviewboardService.getReviewboardView(review_seq);
		
		mav.addObject("reviewboardDTO", reviewboardDTO);
		mav.addObject("memId", memId);
		mav.setViewName("jsonView");
		
		return mav;
		
	}
	
	//수정
	@RequestMapping(value="reviewboardModifyForm", method=RequestMethod.GET)
	public String reviewboardModifyForm(@RequestParam int review_seq, 
										HttpSession session, 
										Model model) {

		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memId", memId);
		model.addAttribute("review_seq", review_seq);
		model.addAttribute("display", "/reviewboard/reviewboardModifyForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="reviewboardModify", method=RequestMethod.POST)
	@ResponseBody
	public void reviewboardModify(@ModelAttribute ReviewboardDTO reviewboardDTO,
							@RequestParam int sale_seq,
			 				HttpSession session,
			 				Model model,
							@RequestParam("file[]") List<MultipartFile> list) {
		
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memId", memId);
		
		System.out.println(reviewboardDTO.getReview_manner());
		
		//model.addAttribute("pg", pg); // pg값 받기
		
		String filePath = "D:\\Spring\\workspace\\jajuProject\\src\\main\\webapp\\storage";

		String fileName;
		File file;

		for(int i=0; i<list.size(); i++) {
			fileName = list.get(i).getOriginalFilename();
			file = new File(filePath, fileName);
			
			System.out.println(fileName);
			
			//파일 복사
			try {
				FileCopyUtils.copy(list.get(i).getInputStream(), new FileOutputStream(file));
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			if(fileName!=null) {
				if(i==0) reviewboardDTO.setReview_image1(fileName);
				else if (i==1) reviewboardDTO.setReview_image2(fileName);
				else if (i==2) reviewboardDTO.setReview_image3(fileName);

			} 

		} //for
		
		reviewboardService.reviewboardModify(reviewboardDTO);
		
	}
	
	//삭제
	@RequestMapping(value="reviewboardDelete", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView reviewboardDelete(@RequestParam int review_seq) {
	
		reviewboardService.reviewboardDelete(review_seq);
		
		return new ModelAndView("redirect:/reviewboard/reviewboardList");
	}
	
	
	//리스트 
	@RequestMapping(value="reviewboardList", method=RequestMethod.GET)
	public String reviewboardList(Model model, HttpSession session) {
		
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memId", memId);
		
		model.addAttribute("display", "/reviewboard/reviewboardList.jsp");
		return "/index";
	}

	//리스트
	@RequestMapping(value="getReviewboardList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getReviewboardList(@RequestParam(required=false, defaultValue="1") String pg,
												HttpSession session,
												HttpServletResponse response) {
		//1페이지당 5개씩
		List<ReviewboardDTO> list = reviewboardService.getReviewboardList(pg);
		
		//세션
		String memId = (String) session.getAttribute("memId");
		
		//페이징 처리
		ReviewboardPaging reviewboardPaging = reviewboardService.reviewboardPaging(pg);
		
		//조회수 - 새로고침 방지
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);		
		mav.addObject("memId", memId);
		mav.addObject("reviewboardPaging", reviewboardPaging);
		
		
		mav.setViewName("jsonView");
		return mav;
	}
	
	//검색


	 	@RequestMapping(value="getReviewboardSearchList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getReviewboardSearchList(@RequestParam Map<String, String> map,
												HttpSession session) { //넘어오는거: form에 다 묶여있던 거-pg, searchOption, keyword
			
			//System.out.println(map.get("searchPg"));
			//1페이지당 5개씩
			List<ReviewboardDTO> list = reviewboardService.getReviewboardSearchList(map);
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			//페이징 처리
			ReviewboardPaging reviewboardPaging = reviewboardService.reviewboardPaging_search(map);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", map.get("pg"));
			mav.addObject("list", list);
			mav.addObject("reviewboardPaging", reviewboardPaging);
			mav.setViewName("jsonView");
			return mav;
			
		}



	//구매내역 띄우기
		@RequestMapping(value="review_saleReport", method=RequestMethod.GET)
		public String review_saleReport(//@RequestParam(required = false, defaultValue = "1") String pg,
										Model model,
										HttpSession session) {
			String memId = (String) session.getAttribute("memId");

			model.addAttribute("memId", memId);
			//model.addAttribute("pg", pg); // pg값 받기
			model.addAttribute("display", "/reviewboard/review_saleReport.jsp");
			return "/index";
		}
		
		//구매내역 기능
		@RequestMapping(value="review_saleReportList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView review_saleReportList(//@RequestParam String pg, 
											@RequestParam Map<String, String> map) {
			List<SaleboardDTO> list = reviewboardService.review_saleReportList(map);
			
			//페이징
			ReviewboardPaging reviewboardPaging = reviewboardService.reviewboardPaging(map);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.addObject("reviewboardPaging", reviewboardPaging);
			mav.setViewName("jsonView");

			return mav;
			
		}
		
		@RequestMapping(value="getAllReviewboardList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getAllReviewboardList(@RequestParam String member_id) {
			
			List<ReviewboardDTO> list = reviewboardService.getAllReviewboardList(member_id);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("list", list);
			mav.setViewName("jsonView");

			return mav;
			
		}
		
		@RequestMapping(value="review_mannerModify", method=RequestMethod.POST)
		@ResponseBody
		public void review_mannerModify(@RequestParam Map<String, String> map) {
			
			reviewboardService.review_mannerModify(map);
		}
		
		@RequestMapping(value="getPage", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getPage(@RequestParam int review_seq) {
			
			ReviewboardDTO reviewboardDTO = reviewboardService.getPage(review_seq);
			
			//System.out.println(noticeDTO.getPrev_subject());
			//System.out.println(noticeDTO.getNext_subject());
			ModelAndView mav = new ModelAndView();
			mav.addObject("reviewboardDTO", reviewboardDTO);
			mav.setViewName("jsonView");
			return mav;
		}
	
}
