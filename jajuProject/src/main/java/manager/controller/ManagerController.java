package manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import manager.service.ManagerService;
import member.bean.MemberDTO;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
	
	 @Autowired 
	 private ManagerService managerService;
	
	@RequestMapping(value="/managerMember", method=RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("display", "/manager/managerMember.jsp");
		return "/index";
	}
	
	@RequestMapping(value="/getManagerMember",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getManagerMember(@RequestParam(required = false, defaultValue = "1") String pg) {
		
		List<MemberDTO> list = managerService.getManagerMember(pg);//pg넘겨서 페이징 처리해서 회원 리스트 가져오기 
		//System.out.println("겟메니저맴버= "+list);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		//mav.addObject("managerPaging", managerPaging);
		return mav;
		
	}
	
	
}
