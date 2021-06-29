package manager.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;

public interface ManagerDAO {

	public List<MemberDTO> getManagerMember(Map<String, String> map);

}
