package member.dao;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {
	public MemberDTO login(Map<String, String> map);
	public MemberDTO getMember(String member_id);
	public void write(MemberDTO memberDTO);
	public void kakao_write(Map<String, String> map);
	public MemberDTO getId(Map<String, String> map);
	public MemberDTO getPwd(Map<String, String> map);
	public void modify(MemberDTO memberDTO);
}
