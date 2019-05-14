package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cafe.jjdev.mall.email.EmailServiceImpl;
import cafe.jjdev.mall.service.MemberService;
import cafe.jjdev.mall.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	// 17. 아이디 중복 검사 
	@PostMapping("/member/idCheck")
	@ResponseBody
    public Map<Object, Object> idcheck(@RequestBody String userid) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        int count = 0;
        count = memberService.idCheck(userid);
        map.put("count", count);
        return map;
    }
	// 임시 비밀번호 생성 및 변경 후 이메일 발송
	@PostMapping("/sendPwToEmail")
	public String sendPwToEmail(Member member){
		System.out.println("MemberController.sendPwToEmail POST member : "+member);
		String tempPw = memberService.tempPw();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", member.getMemberId());
		map.put("memberEmail", member.getMemberEmail());
		map.put("tempPw", tempPw);
		memberService.modifyTempPw(map);
		emailServiceImpl.sendSimpleMessage(member.getMemberEmail(), "임시 비밀번호", "임시 비밀번호는 : "+tempPw+" 입니다.");
		return "/member/findResult";
	}
	// 16. 비밀번호 찾기 액션
	@PostMapping("/member/findPw")
	public String findPw(Model model, Member member) {
		System.out.println("MemberController.findPw POST member : "+member);
		Member resultMember = memberService.getFindPw(member);
		System.out.println("MemberController.findPw POST resultMember : "+resultMember);
		model.addAttribute("resultMember", resultMember);
		return "/member/findPw";
	}
	// 15. 비밀번호 찾기 폼
	@GetMapping("/member/findPw")
	public String findPw(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:"+"/";
		} else {
			return "/member/findPw";
		}
	}
	// 찾은 아이디 이메일 발송
	@PostMapping("/sendIdToEmail")
	public String sendIdToEmail(String memberEmail){
		System.out.println("MemberController.sendEmail POST memberEmail : "+memberEmail);
		emailServiceImpl.sendSimpleMessage(memberEmail, "제목", "내용");
		return "/member/findResult";
	}
	// 14. 아이디 찾기 액션
	@PostMapping("/member/findId")
	public String findId(Model model, Member member){
		System.out.println("MemberController.findId POST member :"+member);
		Member resultMember = memberService.getFindId(member);
		System.out.println("MemberController.findId POST resultMember :"+resultMember);
		model.addAttribute("resultMember", resultMember);
		return "/member/findId";
	}
	// 13. 아이디 찾기 폼
	@GetMapping("/member/findId")
	public String foundId(HttpSession session){
		if(session.getAttribute("loginMember") != null) {
			return "redirect:"+"/";
		} else {
			return "/member/findId";
		}
	}
	// 12. 회원 탈퇴 액션 Membercontroller.removeMember -> MemberService.removeMember -> MemberMapper.deleteMember -> 역순으로 리턴 -> index.jsp
	@PostMapping("/member/removeMember")
	public String removeMember(HttpSession session, Member member){
		Member loginMember = (Member) session.getAttribute("loginMember");
		member.setMemberId(loginMember.getMemberId());
		memberService.removeMember(member);
		session.invalidate();
		return "redirect:"+"/";
	}
	// 11. 회원 탈퇴 폼 MemberController.removeMember -> removeMember.jsp
	@GetMapping("/member/removeMember")
	public String removeMember(HttpSession session){
		if(session.getAttribute("loginMember") == null) {
			return "redirect:"+"/member/login";
		} else {
			return "/member/removeMember";
		}
	}
	// 10. 회원 정보 수정 액션 MemberController.modifyMember -> MemberService.modifyMember -> MemberMapper.updateMember -> 역순으로 리턴 -> memberInfo.jsp
	@PostMapping("/member/modifyMember")
	public String modifyMember(Member member){
		System.out.println("MemberController.modifyMember POST member :"+member);
		memberService.modifyMember(member);
		return "redirect:"+"/member/memberInfo";
	}
	// 9. 회원 정보 수정 폼 MemberController.modifyMember -> MemberService.getMemberForInfo -> MemberMapper.selectMemberForInfo -> 역순으로 리턴 -> modifyMember.jsp
	@GetMapping("/member/modifyMember")
	public String modifyMember(Model model, HttpSession session){
		if(session.getAttribute("loginMember") == null) {
			return "redirect:"+"/member/login";
		} else {
			Member loginMember = (Member) session.getAttribute("loginMember");
			Member member = memberService.getMemberForInfo(loginMember.getMemberId());
			System.out.println("MemberController.modifyMember GET member :"+member);
			model.addAttribute("member", member);
			return "/member/modifyMember";
		}
	}
	// 8. 비밀번호 수정 액션 MemberController.modifyMemberPw -> MemberService.modifyMemberPw -> MemberMapper.updateMemberPw -> 역순으로 리턴 -> memberInfo.jsp
	@PostMapping("/member/modifyMemberPw")
	public String modifyMemberPw(Member member, @RequestParam(value = "memberNewPw", required = true) String memberNewPw, HttpSession session){
		System.out.println("MemberController.modifyMemberPw POST member :"+member);
		System.out.println("MemberController.modifyMemberPw POST memberNewPw :"+memberNewPw);
		Member loginMember = (Member) session.getAttribute("loginMember");
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", loginMember.getMemberId());
		map.put("memberPw", member.getMemberPw());
		map.put("memberNewPw", memberNewPw);
		memberService.modifyMemberPw(map);
		return "redirect:"+"/member/memberInfo";
	}
	// 7. 비밀번호 수정 폼 MemberController.modifyMemberPw -> modifyMemberPw.jsp
	@GetMapping("/member/modifyMemberPw")
	public String modifyMemberPw(HttpSession session){
		if(session.getAttribute("loginMember") == null) {
			return "redirect:"+"/member/login";
		} else {
			return "/member/modifyMemberPw";
		}
	}
	// 6. 개인 정보 확인  MemberController.memberInfo -> MemberService.getMemberForInfo -> MemberMapper.selectMemberForInfo -> 역순으로 리턴 -> memberInfo.jsp
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model, HttpSession session){
		if(session.getAttribute("loginMember") == null) {
			return "redirect:"+"/member/login";
		} else {
			Member loginMember = (Member) session.getAttribute("loginMember");
			System.out.println("MemberController.memberInfo GET loginMember :"+loginMember);
			Member member = memberService.getMemberForInfo(loginMember.getMemberId());
			model.addAttribute("member", member);
			return "/member/memberInfo";
		}
	}
	// 5. 회원 가입 액션  MemberController.addMember -> MemberService.addMember -> MemberMapper.insertMember -> 역순으로 리턴 -> index.jsp 
	@PostMapping("/member/addMember")
	public String addMember(Member member, @RequestParam(value = "memberConsumerDateOfBirth", required = false) String memberConsumerDateOfBirth){
		System.out.println("MemberController.addMember POST member :"+member);
		System.out.println("MemberController.addMember POST memberConsumerDateOfBirth :"+memberConsumerDateOfBirth);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member", member);
		map.put("memberConsumerDateOfBirth", memberConsumerDateOfBirth);
		memberService.addMember(map);
		return "redirect:/";
	}
	// 4. 회원 가입 폼  MemberController.addMember -> addMember.jsp
	@GetMapping("/member/addMember")
	public String addMember(HttpSession session){
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		} else {
			return "/member/addMember";
		}
	}
	// 3. 로그아웃 MemberController.logout -> index.jsp
	@GetMapping("/member/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	// 2. 로그인 액션  MemberController.getMember -> MemberService.getMemberForLogin -> MemberMapper.selectMemberForLogin -> 역순으로 리턴 -> index.jsp
	@PostMapping("/member/login")
	public String getMember(HttpSession session, Member member){
		System.out.println("MemberController.getMember POST member :"+member);
		Member loginMember = memberService.getMemberForLogin(member);
		System.out.println("MemberController.getMember POST loginMember :"+loginMember);
		if(loginMember == null){
			return "redirect:"+"/member/login";
		} else {
			session.setAttribute("loginMember", loginMember);
			System.out.println("MemberController.getMember POST session :"+session);
			return "redirect:/";
		}
	}
	// 1. 로그인 폼 MemberController.getMember -> login.jsp
	@GetMapping("/member/login")
	public String getMember(HttpSession session){
		System.out.println("MemberController.getMember GET session.loginMember :"+session.getAttribute("loginMember"));
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		} else {
			return "/member/login";
		}
	}
}
