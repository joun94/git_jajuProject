package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository
@Transactional
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDTO login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
	}

	@Override
	public MemberDTO getMember(String member_id) {
		return sqlSession.selectOne("memberSQL.getMember", member_id);
	}

	@Override
	public void write(MemberDTO memberDTO) {
		sqlSession.insert("memberSQL.write", memberDTO);
	}
	
	@Override
	public void kakao_write(Map<String, String> map) {
		sqlSession.insert("memberSQL.kakao_write", map);
	}

	@Override
	public MemberDTO getId(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getId", map);
	}
	
	@Override
	public void modify(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.modify", memberDTO);
	}

	@Override
	public MemberDTO getPwd(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getPwd", map);
	}
}
