package member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.address.GpsToAddress;
import member.bean.MemberDTO;
import member.mail.MailSendService;
import member.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MailSendService mailSendService;
	@Autowired
	private GpsToAddress gps;
	
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("display", "/member/loginForm.jsp");
		
		return "/index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		String result = memberService.login(map);
		
		if(result.equals("success")) {
			model.addAttribute("memId", session.getAttribute("memId"));
		}
		
		return result;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@RequestMapping(value="/writeForm", method=RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		
		return "/index";
	}

	@RequestMapping(value="/checkMail", method=RequestMethod.POST)
	@ResponseBody
	public void checkMail(@RequestParam String email, HttpSession session) {
		String authKey = mailSendService.sendAuthMail(email);
		session.setAttribute("authKey", authKey);
		session.setMaxInactiveInterval(3 * 60);
	}

	@RequestMapping(value="/checkCode", method=RequestMethod.POST)
	@ResponseBody
	public String checkCode(@RequestParam String authKey, HttpSession session) {
		String code = (String) session.getAttribute("authKey");
		
		if(authKey.equals(code)) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String member_id) {
		return memberService.checkId(member_id);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute MemberDTO memberDTO) {
		memberService.write(memberDTO);
	}
	
	@RequestMapping(value="/kakao_login", method=RequestMethod.POST)
	@ResponseBody
	public void kakao_write(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		MemberDTO memberDTO = memberService.getMember(map.get("member_id"));
		
		if(memberDTO == null) {
			try {
				gps.setGps(Double.parseDouble(map.get("lat")), Double.parseDouble(map.get("lng")));

				map.put("member_sido", gps.getMember_sido());
				map.put("member_sigungu", gps.getMember_sigungu());
				map.put("member_detailAddr", gps.getMember_detailAddr());
				map.put("member_address", gps.getMember_address());
				
				memberService.kakao_write(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("memId", map.get("member_id"));
		model.addAttribute("memId", session.getAttribute("memId"));
	}
	
	@RequestMapping(value="/find_id", method=RequestMethod.GET)
	public String find_id(Model model) {
		model.addAttribute("display", "/member/find_idForm.jsp");
		
		return "/index";
	}
	
	@RequestMapping(value="/getId", method=RequestMethod.POST)
	@ResponseBody
	public String getId(@RequestParam Map<String, String> map) {
		return memberService.getId(map);
	}

	@RequestMapping(value="/find_pwd", method=RequestMethod.GET)
	public String find_pwd(Model model) {
		model.addAttribute("display", "/member/find_pwdForm.jsp");
		
		return "/index";
	}

	@RequestMapping(value="/getPwd", method=RequestMethod.POST)
	@ResponseBody
	public String getPwd(@RequestParam Map<String, String> map) {
		return memberService.getPwd(map);
	}

	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(Model model) {
		model.addAttribute("display", "/member/modifyForm.jsp");
		
		return "/index";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	@ResponseBody
	public void modify(@ModelAttribute MemberDTO memberDTO) {
		memberService.modify(memberDTO);
	}
	
	@RequestMapping(value="/getMember", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getMember(HttpSession session) {
		MemberDTO memberDTO = memberService.getMember((String) session.getAttribute("memId"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDTO", memberDTO);
		mav.setViewName("jsonView");
		
		return mav;
	}
}
