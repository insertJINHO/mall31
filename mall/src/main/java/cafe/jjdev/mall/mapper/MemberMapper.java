package cafe.jjdev.mall.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Member;

@Mapper
public interface MemberMapper {
	// 임시 비밀번호로 변경
	public int updateTempPw(Map<String, String> map);
	// 비밀번호 찾기
	public Member selectFindPw(Member member);
	// 아이디 찾기
	public Member selectFindId(Member member);
	// 회원 가입 시 아이디 중복 검사
	public String selectMemberId(String memberId);
	// 회원 가입 시 탈퇴 한 아이디 중복 검사
	public String selectMemberOutId(String memberId);
	// 회원 탈퇴 후 아이디 보관
	public int insertMemberOut(String memberId);
	// 회원 탈퇴
	public int deleteMember(Member member);
	// 회원 정보 수정
	public int updateMember(Member member);
	// 비밀번호 수정
	public int updateMemberPw(Map<String, String> map);
	// 개인 정보 조회
	public Member selectMemberForInfo(String memberId);
	// 회원 가입 시 member_consumer 테이블에 추가
	public int insertMemberConsumer(Map<String, Object> map);
	// 회원 가입
	public int insertMember(Member member);
	// 로그인 처리를 위한 회원 정보 조회
	public Member selectMemberForLogin(Member member);
}
