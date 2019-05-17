package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.MemberMapper;
import cafe.jjdev.mall.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	// 임시 비밀번호로 변경
	public int modifyTempPw(Map<String, String> map) {
		return memberMapper.updateTempPw(map);
	}
	// 임시 비밀번호 생성
	public String tempPw() {
		String tempPw = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		System.out.println("MemberService.tempPw tempPw :"+tempPw);
		return tempPw;
	}
	// 아이디 중복 검사 
	public int idCheck(String memberId) {
		// member 테이블 아이디 중복 검사 
		String resultId = memberMapper.selectMemberId(memberId);
		System.out.println("MemberService.addMember resultId :"+resultId);
		// member_out 테이블 아이디 중복 검사
		String resultOutId = memberMapper.selectMemberOutId(memberId);
		System.out.println("MemberService.addMember resultOutId :"+resultOutId);
		int count = 0;
		if(resultId != null || resultOutId != null) {
			count = 1;
		}
		return count;
	}
	// 비밀번호 찾기
	public Member getFindPw(Member member) {
		return memberMapper.selectFindPw(member);
	}
	// 아이디 찾기
	public Member getFindId(Member member){
		return memberMapper.selectFindId(member);
		/*
		 * if(resultMember != null) {
		 * resultMember.setMemberId(resultMember.getMemberId().replaceAll("(?<=.{2})." ,
		 * "*")); System.out.println("MemberService.getFindId resultMember.memberId :"
		 * +resultMember.getMemberId()); }
		 */
	}
	// 회원 삭제
	public void removeMember(Member member){
		memberMapper.insertMemberOut(member.getMemberId());
		memberMapper.deleteMember(member);
	}
	// 회원 정보 수정
	public int modifyMember(Member member){
		return memberMapper.updateMember(member);
	}
	// 비밀번호 수정
	public int modifyMemberPw(Map<String, String> map){
		return memberMapper.updateMemberPw(map);
	}	
	// 개인 정보 조회
	public Member getMemberForInfo(String memberId){
		return memberMapper.selectMemberForInfo(memberId);
	}
	// 회원 가입
	public void addMember(Map<String, Object> map){
		Member member = (Member) map.get("member");
		memberMapper.insertMember(member);
		Member resultMember = memberMapper.selectMemberForInfo(member.getMemberId());
		Map<String, Object> mapForCunsumer = new HashMap<String, Object>();
		mapForCunsumer.put("memberNo", resultMember.getMemberNo());
		mapForCunsumer.put("memberConsumerDateOfBirth", map.get("memberConsumerDateOfBirth"));
		memberMapper.insertMemberConsumer(mapForCunsumer);
	}
	// 로그인 처리를 위한 회원 정보 조회
	public Member getMemberForLogin(Member member){
		return memberMapper.selectMemberForLogin(member);
	}
}
