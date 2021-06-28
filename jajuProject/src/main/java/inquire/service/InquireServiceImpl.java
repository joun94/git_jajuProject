package inquire.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inquire.bean.InquireDTO;
import inquire.bean.InquirePaging;
import inquire.dao.InquireDAO;

@Service
public class InquireServiceImpl implements InquireService {
	@Autowired
	private HttpSession session;
	@Autowired
	private InquireDAO inquireDAO;
	@Autowired
	private InquirePaging inquirePaging;
	
	
	@Override
	public void inquireWrite(InquireDTO inquireDTO) {
		inquireDAO.inquireWrite(inquireDTO);
		
	}


	@Override
	public List<InquireDTO> getInquireList(String pg) {
		//1페이지당 3개
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, Integer>map = new HashedMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return inquireDAO.getInquireList(map);
	}


	@Override
	public InquireDTO getInquireView(String seq) {
		return inquireDAO.getInquireView(seq);
	}


	@Override
	public InquireDTO getInquireModify(String seq) {
		return inquireDAO.getInquireModify(seq);
	}

	@Override
	public void inquireModify(InquireDTO inquireDTO) {
		inquireDAO.inquireModify(inquireDTO);
		
	}


	@Override
	public InquirePaging inquirePaging(String pg) {
		int totalA = inquireDAO.getTotalA(); //총글수
		
		inquirePaging.setCurrentPage(Integer.parseInt(pg));//현재 페이지
		inquirePaging.setPageBlock(5);
		inquirePaging.setPageSize(10);
		inquirePaging.setTotalA(totalA);
		inquirePaging.makePagingHTML();
		
		return inquirePaging;
	}


	@Override
	public void inquireDelete(String seq) {
		inquireDAO.inquireDelete(seq);
		
	}


	@Override
	public void inquireReply(Map<String, String> map) {
		
		//원글 가져오기
		InquireDTO iDT = inquireDAO.getInquireView(map.get("pseq"));
		map.put("inquiry_id", iDT.getInquiry_id());
		map.put("inquiry_name",iDT.getInquiry_name());
		map.put("inquiryType", iDT.getInquiryType());
		map.put("inquiry_telephone", iDT.getInquiry_telephone());
		map.put("inquiry_image1", iDT.getInquiry_image1());
		map.put("inquiry_image2", iDT.getInquiry_image2());
		map.put("inquiry_image3", iDT.getInquiry_image3());
		
		map.put("inquiry_ref", iDT.getInquiry_ref()+"");
		map.put("inquiry_lev", iDT.getInquiry_lev()+"");
	
		inquireDAO.inquireReply(map);

	}
	


}
