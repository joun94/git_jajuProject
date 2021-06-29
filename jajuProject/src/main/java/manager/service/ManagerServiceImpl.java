package manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.dao.ManagerDAO;
import member.bean.MemberDTO;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDAO managerDAO;

	@Override
	public List<MemberDTO> getManagerMember(String pg) {
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("startNum",startNum+"");
		map.put("endNum",endNum+"");
		
		return managerDAO.getManagerMember(map);
	}

}
