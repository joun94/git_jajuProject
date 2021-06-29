package saleboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import saleboard.bean.SaleboardCommentDTO;
import saleboard.bean.SaleboardCommentPaging;
import saleboard.bean.SaleboardDTO;
import saleboard.bean.SaleboardPaging;
import saleboard.service.SaleboardService;

@Controller
@RequestMapping(value="saleboard")
public class SaleboardController {
	@Autowired
	private SaleboardService saleboardService;
	
	
	@RequestMapping(value="saleboardWriteForm", method=RequestMethod.GET)
	public String saleboardWriteForm(Model model) {
		
		model.addAttribute("display","/saleboard/saleboardWriteForm.jsp");
		return "/index";
	}
	
	@RequestMapping(value="saleboardList", method=RequestMethod.GET)
	public String saleboardList(Model model) {
		
		
		model.addAttribute("display","/saleboard/saleboardList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="saleboardView", method=RequestMethod.GET)
	public String saleboardView(Model model) {
		
		
		
		model.addAttribute("display","/saleboard/saleboardView.jsp");
		return "/index";
	}
	
	//write ���
		@RequestMapping(value="saleboardWrite", method=RequestMethod.POST)
		@ResponseBody
		public void saleboardWrite(@ModelAttribute SaleboardDTO saleboardDTO,
				@RequestParam("file") List<MultipartFile> list) {
			
			String filePath = "C:\\sts_work\\jajuProject\\src\\main\\webapp\\storage";

			String fileName;
			File file;
		
			for(int i=0; i<list.size(); i++) {
				fileName = list.get(i).getOriginalFilename();
				file = new File(filePath, fileName);
				
				
				//���� ����
				try {
					FileCopyUtils.copy(list.get(i).getInputStream(), new FileOutputStream(file));
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				if(fileName!=null) {
					if(i==0) saleboardDTO.setSale_image1(fileName);
					else if (i==1) saleboardDTO.setSale_image2(fileName);
					else if (i==2) saleboardDTO.setSale_image3(fileName);
					else if (i==3) saleboardDTO.setSale_image4(fileName);
					else if (i==4) saleboardDTO.setSale_image5(fileName);
					else if (i==5) saleboardDTO.setSale_image6(fileName);
					else if (i==6) saleboardDTO.setSale_image7(fileName);
					else if (i==7) saleboardDTO.setSale_image8(fileName);
					else if (i==8) saleboardDTO.setSale_image9(fileName);
					else if (i==9) saleboardDTO.setSale_image10(fileName);
				} 

				
			} //for
			saleboardService.saleboardWrite(saleboardDTO);
		
		}
		
		@RequestMapping(value="getSaleboardList", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardList(@RequestParam Map<String, String> map) {
			
			ModelAndView mav = new ModelAndView();
			
			
			List<SaleboardDTO> list = saleboardService.getSaleboardList(map);
			
			int pg = Integer.parseInt(map.get("pg"));
			
			SaleboardPaging saleboardPaging = saleboardService.saleboardPaging(pg);
			
			mav.addObject("saleboardPaging", saleboardPaging);
			
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		@RequestMapping(value="getSaleboardView", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardView(@RequestParam int sale_seq, HttpSession session,
											 HttpServletResponse response, HttpServletRequest request) {

			String memId = (String) session.getAttribute("memId");
			int sw = 0;
			Cookie[] cookie = request.getCookies();
			for (Cookie coo : cookie) {
				if(coo.getName().equals(memId) && coo.getValue().equals(sale_seq+"")) {
					sw = 1;
				}
			}
			//조회수 새로고침 방지
			if(sw != 1) {
				Cookie cookies = new Cookie(memId, sale_seq+"");
				cookies.setMaxAge(24*60*60);//시간설정 초단위
				response.addCookie(cookies);//클라이언트 보내기
				saleboardService.upHit(sale_seq);
			}

			ModelAndView mav = new ModelAndView();

			SaleboardDTO saleboardDTO = saleboardService.getSaleboardView(sale_seq);

			
			mav.addObject("saleboardDTO", saleboardDTO);
			mav.setViewName("jsonView");

			return mav;
		}

		@RequestMapping(value = "index", method = RequestMethod.GET)
		public String index(Model model) {

			model.addAttribute("display", "/main/body.jsp");
			return "/index";

		}

		@RequestMapping(value = "getSearchSaleboardList", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSearchSaleboardList(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();

			List<SaleboardDTO> list = saleboardService.getSearchSaleboardList(map);

			SaleboardPaging saleboardPaging = saleboardService.searchSaleboardPaging(map);

			mav.addObject("saleboardPaging", saleboardPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");

			return mav;
		}
	   
	 //����
		@RequestMapping(value="saleboardModifyForm", method=RequestMethod.GET)
		public String saleboardModifyForm(@RequestParam int sale_seq,
										Model model) {
			
			model.addAttribute("sale_seq", sale_seq);
			
			model.addAttribute("display", "/saleboard/saleboardModifyForm.jsp");
			return "/index";
		}
		
		//���� - ÷������ ����
		@RequestMapping(value="saleboardModify", method=RequestMethod.POST)
		@ResponseBody
		public void saleboardModify(@ModelAttribute SaleboardDTO saleboardDTO,
								@RequestParam("file[]") List<MultipartFile> list) {
			
			String filePath = "C:\\sts_work\\jajuProject\\src\\main\\webapp\\storage";

			String fileName;
			File file;

			for(int i=0; i<list.size(); i++) {
				fileName = list.get(i).getOriginalFilename();
				file = new File(filePath, fileName);
				
				System.out.println(fileName);
				
				//���� ����
				try {
					FileCopyUtils.copy(list.get(i).getInputStream(), new FileOutputStream(file));
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				if(fileName!=null) {
					if(i==0) saleboardDTO.setSale_image1(fileName);
					else if (i==1) saleboardDTO.setSale_image2(fileName);
					else if (i==2) saleboardDTO.setSale_image3(fileName);
					else if (i==3) saleboardDTO.setSale_image4(fileName);
					else if (i==4) saleboardDTO.setSale_image5(fileName);
					else if (i==5) saleboardDTO.setSale_image6(fileName);
					else if (i==6) saleboardDTO.setSale_image7(fileName);
					else if (i==7) saleboardDTO.setSale_image8(fileName);
					else if (i==8) saleboardDTO.setSale_image9(fileName);
					else if (i==9) saleboardDTO.setSale_image10(fileName);
				} 

			} //for

			//���� ������ �̹��� ����x ������ �����ϰ� �ϱ�
			saleboardService.saleboardModify(saleboardDTO);
		}
		
		//����
		@RequestMapping(value="saleboardDelete", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView saleboardDelete(@RequestParam int sale_seq) {

			saleboardService.saleboardDelete(sale_seq);
			
			return new ModelAndView("redirect:/saleboard/saleboardList");
		}
		
		//��� �ޱ�
		@RequestMapping(value="writeSaleboardComment", method=RequestMethod.POST)
		@ResponseBody
		public void writeSaleboardComment(@RequestParam Map<String, String> map) {

			saleboardService.writeSaleboardComment(map);
			
		}
		//��� ��������
		
		@RequestMapping(value="getSaleboardComment", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardComment(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();
			
			List<SaleboardCommentDTO> list = saleboardService.getSaleboardComment(map);
			
			SaleboardCommentPaging saleboardCommentPaging = saleboardService.saleboardCommentPaging(map);
			
			mav.addObject("saleboardCommentPaging", saleboardCommentPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		//���� ����
		@RequestMapping(value="/writeReplySaleboardComment", method=RequestMethod.POST)
		@ResponseBody
		public void writeReplySaleboardComment(@RequestParam Map<String, String> map) {			
			
			saleboardService.writeReplySaleboardComment(map);
			
			
		}
		//��� 1�� �ҷ�����
		@RequestMapping(value="getSaleboardCommentOne", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardCommentOne(@RequestParam String comment_seq) {

			ModelAndView mav = new ModelAndView();
			
			SaleboardCommentDTO saleboardCommentDTO = saleboardService.getSaleboardCommentOne(comment_seq);
			
			mav.addObject("saleboardCommentDTO", saleboardCommentDTO);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		@RequestMapping(value="commentModify", method=RequestMethod.POST)
		@ResponseBody
		public void commentModify(@RequestParam Map<String, String> map) {
			
			saleboardService.commentModify(map);
			
		}
		
		@RequestMapping(value="commentDelete", method=RequestMethod.POST)
		@ResponseBody
		public void commentDelete(@RequestParam String comment_seq) {
			
			
			SaleboardCommentDTO saleboardCommentDTO = saleboardService.getSaleboardCommentOne(comment_seq);
			
			saleboardService.commentDelete(saleboardCommentDTO);
			
		}
		
		@RequestMapping(value="getSaleboardListLogin", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardListLogin(@RequestParam Map<String, String> map) {
			
			ModelAndView mav = new ModelAndView();
			
			
			List<SaleboardDTO> list = saleboardService.getSaleboardListLogin(map);
			
			
			
			SaleboardPaging saleboardPaging = saleboardService.saleboardPagingLogin(map);
			
			mav.addObject("saleboardPaging", saleboardPaging);
			
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		@RequestMapping(value = "getSearchSaleboardListLogin", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSearchSaleboardListLogin(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();

			List<SaleboardDTO> list = saleboardService.getSearchSaleboardListLogin(map);

			SaleboardPaging saleboardPaging = saleboardService.searchSaleboardPagingLogin(map);

			mav.addObject("saleboardPaging", saleboardPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");

			return mav;
		}
		
		@RequestMapping(value="getSaleboardViewReview", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardViewReview(@RequestParam int sale_seq) {

			ModelAndView mav = new ModelAndView();

			SaleboardDTO saleboardDTO = saleboardService.getSaleboardView(sale_seq);

			
			mav.addObject("saleboardDTO", saleboardDTO);
			mav.setViewName("jsonView");

			return mav;
		}
		
		@RequestMapping(value="saleboardListCategory", method=RequestMethod.GET)
		public String saleboardListCategory(Model model) {
			
			
			model.addAttribute("display","/saleboard/saleboardListCategory.jsp");
			return "/index";
		}		
		
		@RequestMapping(value="getSaleboardListCategory", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardListCategory(@RequestParam Map<String, String> map) {
			
			ModelAndView mav = new ModelAndView();
			
			
			List<SaleboardDTO> list = saleboardService.getSaleboardListCategory(map);
			
			
			
			SaleboardPaging saleboardPaging = saleboardService.saleboardPagingCategory(map);
			
			mav.addObject("saleboardPaging", saleboardPaging);
			
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		@RequestMapping(value="getSaleboardListLoginCategory", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSaleboardListLoginCategory(@RequestParam Map<String, String> map) {
			
			ModelAndView mav = new ModelAndView();
						
			List<SaleboardDTO> list = saleboardService.getSaleboardListLoginCategory(map);
									
			SaleboardPaging saleboardPaging = saleboardService.saleboardPagingLoginCategory(map);
			
			mav.addObject("saleboardPaging", saleboardPaging);
			
			mav.addObject("list", list);
			mav.setViewName("jsonView");
			
			return mav;
		}
		
		@RequestMapping(value = "getSearchSaleboardListCategory", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSearchSaleboardListCategory(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();

			List<SaleboardDTO> list = saleboardService.getSearchSaleboardListCategory(map);

			SaleboardPaging saleboardPaging = saleboardService.searchSaleboardPagingCategory(map);

			mav.addObject("saleboardPaging", saleboardPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");

			return mav;
		}
		
		@RequestMapping(value = "getSearchSaleboardListLoginCategory", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView getSearchSaleboardListLoginCategory(@RequestParam Map<String, String> map) {

			ModelAndView mav = new ModelAndView();

			List<SaleboardDTO> list = saleboardService.getSearchSaleboardListLoginCategory(map);

			SaleboardPaging saleboardPaging = saleboardService.searchSaleboardPagingLoginCategory(map);

			mav.addObject("saleboardPaging", saleboardPaging);
			mav.addObject("list", list);
			mav.setViewName("jsonView");

			return mav;
		}
		
		
		
		
}
