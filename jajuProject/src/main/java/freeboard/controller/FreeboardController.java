package freeboard.controller;

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

import freeboard.bean.FreeboardCommentDTO;
import freeboard.bean.FreeboardCommentPaging;
import freeboard.bean.FreeboardDTO;
import freeboard.bean.FreeboardPaging;
import freeboard.service.FreeboardService;

@Controller
@RequestMapping(value="freeboard")
public class FreeboardController {
	@Autowired
	private FreeboardService freeboardService;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("display", "/main/body.jsp");
		return "/index";
		
	}
	
	//write
	@RequestMapping(value="freeboardWriteForm", method=RequestMethod.GET)
	public String freeboardWriteForm(Model model, HttpSession session) {
		
		//ID 세션 가져오기
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memId", memId);
		model.addAttribute("display", "/freeboard/freeboardWriteForm.jsp");
		return "/index";
		
	}
	
	//write 기능
	@RequestMapping(value="freeboardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void freeboardWrite(HttpSession session, Model model,
							@ModelAttribute FreeboardDTO freeboardDTO,
							@RequestParam("file[]") List<MultipartFile> list) {
		
		//ID 세션
		String memId = (String) session.getAttribute("memId");
		model.addAttribute("memId", memId);
		
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
				if(i==0) freeboardDTO.setImage1(fileName);
				else if (i==1) freeboardDTO.setImage2(fileName);
				else if (i==2) freeboardDTO.setImage3(fileName);

			}

		} //for
		freeboardService.freeboardWrite(freeboardDTO);
	
	}
	
		//뷰
		@RequestMapping(value="freeboardView", method=RequestMethod.GET)
		public String freeboardView(@RequestParam int board_seq, //나에게 들어오는 데이터: seq, pg
									HttpSession session,
									Model model) {
			
			//ID 세션 가져오기
			String memId = (String) session.getAttribute("memId");
			model.addAttribute("memId", memId);
			model.addAttribute("board_seq", board_seq); //seq를 모델에 넣어줌
			
			model.addAttribute("display","/freeboard/freeboardView.jsp");
			return "/index";
		}
		
		@RequestMapping(value="getFreeboardView", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getFreeboardView(@RequestParam int board_seq,
											HttpSession session,
											HttpServletResponse response,
											HttpServletRequest request) {
			
			String memId = (String) session.getAttribute("memId");
			ModelAndView mav = new ModelAndView();
			
			int sw = 0;
			Cookie[] cookie = request.getCookies();
			for (Cookie coo : cookie) {
				if(coo.getName().equals(memId) && coo.getValue().equals(board_seq+"")) {
					sw = 1;
				}
			}
			//조회수 새로고침 방지
			if(sw != 1) {
				Cookie cookies = new Cookie(memId, board_seq+"");
				cookies.setMaxAge(24*60*60);//시간설정 초단위
				response.addCookie(cookies);//클라이언트 보내기
				freeboardService.upHit_board(board_seq);
				//System.out.println("abc");
			}
			
			FreeboardDTO freeboardDTO = freeboardService.getFreeboardView(board_seq);
			
			mav.addObject("freeboardDTO", freeboardDTO);
			mav.addObject("memId", memId);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		
		//수정
		@RequestMapping(value="freeboardModifyForm", method=RequestMethod.GET)
		public String freeboardModifyForm (@RequestParam int board_seq, 
										HttpSession session,
										Model model) {
			
			String memId = (String) session.getAttribute("memId");
			model.addAttribute("memId", memId);
			model.addAttribute("board_seq", board_seq);
			
			model.addAttribute("display", "/freeboard/freeboardModifyForm.jsp");
			return "/index";
		}
		
		@RequestMapping(value="freeboardModify", method=RequestMethod.POST)
		@ResponseBody
		public void freeboardModify(@ModelAttribute FreeboardDTO freeboardDTO,
								HttpSession session, 
								Model model,
								@RequestParam("file[]") List<MultipartFile> list) {
			
//			System.out.println(freeboardDTO.getBoard_subject());
//			System.out.println(freeboardDTO.getBoard_content());

			String memId = (String) session.getAttribute("memId");
			model.addAttribute("memId", memId);
			
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
					if(i==0) freeboardDTO.setImage1(fileName);
					else if (i==1) freeboardDTO.setImage2(fileName);
					else if (i==2) freeboardDTO.setImage3(fileName);
				} 
	
			} //for
	
			freeboardService.freeboardModify(freeboardDTO);
		}
		
		//삭제
		@RequestMapping(value="freeboardDelete", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView freeboardDelete(@RequestParam int board_seq) {
	
			freeboardService.freeboardDelete(board_seq);
			
			return new ModelAndView("redirect:/freeboard/freeboardList");
		}
		
		//리스트
		@RequestMapping(value="freeboardList", method=RequestMethod.GET)
		public String freeboardList(Model model, HttpSession session) {
			
			String memId = (String) session.getAttribute("memId");
			model.addAttribute("memId", memId);
			
			model.addAttribute("display","/freeboard/freeboardList.jsp");
			return "/index";
		}
		
		@RequestMapping(value="getFreeboardList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getFreeboardList(@RequestParam(required=false, defaultValue="1") String pg,
												HttpSession session,
												HttpServletResponse response
				) {
			
			//1페이지당 5개씩
			List<FreeboardDTO> list = freeboardService.getFreeboardList(pg); //보드DTO에 담는다
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			//페이징 처리
			FreeboardPaging freeboardPaging = freeboardService.freeboardPaging(pg);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", pg);
			mav.addObject("list", list);
			mav.addObject("memId", memId);
			mav.addObject("freeboardPaging", freeboardPaging);
			
			mav.setViewName("jsonView");
			return mav;
		}
		
		//검색
		@RequestMapping(value="getFreeboardSearchList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getFreeboardSearchList(@RequestParam Map<String, String> map,
												HttpSession session) { //넘어오는거: form에 다 묶여있던 거-pg, searchOption, keyword
			
			//System.out.println(map.get("searchPg"));
			//1페이지당 5개씩
			List<FreeboardDTO> list = freeboardService.getFreeboardSearchList(map);
			
			//세션
			String memId = (String) session.getAttribute("memId");
			
			//페이징 처리
			FreeboardPaging freeboardPaging = freeboardService.freeboardPaging(map);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("pg", map.get("pg"));
			mav.addObject("list", list);
			mav.addObject("freeboardPaging", freeboardPaging);
			mav.setViewName("jsonView");
			return mav;
			
		}
		
//-------------------------댓글		
		//댓글 달기
		@RequestMapping(value="writeFreeboardComment", method=RequestMethod.POST)
		@ResponseBody
		public void writeFreeboardComment(@RequestParam Map<String, String> map) {

			freeboardService.writeFreeboardComment(map);
			
		}
		//댓글 가져오기
				
		@RequestMapping(value="getFreeboardComment", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getFreeboardComment(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();
			
			List<FreeboardCommentDTO> list = freeboardService.getFreeboardComment(map);
			
			FreeboardCommentPaging freeboardCommentPaging = freeboardService.freeboardCommentPaging(map);
			
			mav.addObject("freeboardCommentPaging", freeboardCommentPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		//대댓글 쓰기
		@RequestMapping(value="/writeReplyFreeboardComment", method=RequestMethod.POST)
		@ResponseBody
		public void writeReplyFreeboardComment(@RequestParam Map<String, String> map) {			
			
			freeboardService.writeReplyFreeboardComment(map);
			
			
		}
		//댓글 1개 불러오기
		@RequestMapping(value="getFreeboardCommentOne", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getFreeboardCommentOne(@RequestParam String comment_seq) {

			ModelAndView mav = new ModelAndView();
			
			FreeboardCommentDTO freeboardCommentDTO = freeboardService.getFreeboardCommentOne(comment_seq);
			
			mav.addObject("freeboardCommentDTO", freeboardCommentDTO);
			mav.setViewName("jsonView");
			
			return mav;
		}
				
		@RequestMapping(value="commentModify", method=RequestMethod.POST)
		@ResponseBody
		public void commentModify(@RequestParam Map<String, String> map) {
			
			freeboardService.commentModify(map);
			
		}
		
		@RequestMapping(value="commentDelete", method=RequestMethod.POST)
		@ResponseBody
		public void commentDelete(@RequestParam String comment_seq) {
			
			
			FreeboardCommentDTO freeboardCommentDTO = freeboardService.getFreeboardCommentOne(comment_seq);
			
			freeboardService.commentDelete(freeboardCommentDTO);
			
		}
		
		
		@RequestMapping(value="getPage", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getPage(@RequestParam int board_seq) {
			
			FreeboardDTO freeboardDTO = freeboardService.getPage(board_seq);
			
			//System.out.println(noticeDTO.getPrev_subject());
			//System.out.println(noticeDTO.getNext_subject());
			ModelAndView mav = new ModelAndView();
			mav.addObject("freeboardDTO", freeboardDTO);
			mav.setViewName("jsonView");
			return mav;
		}


}
