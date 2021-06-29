package manager.service;

import java.util.List;

import member.bean.MemberDTO;

public interface ManagerService {

	public List<MemberDTO> getManagerMember(String pg);

}
