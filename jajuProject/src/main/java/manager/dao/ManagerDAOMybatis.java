package manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Transactional
@Repository

public class ManagerDAOMybatis implements ManagerDAO{

	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<MemberDTO> getManagerMember(Map<String, String> map) {
		return sqlSession.selectList("managerSQL.getManagerMember",map);

	}

}
