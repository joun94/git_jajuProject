package inquire.service;

import java.util.List;
import java.util.Map;

import inquire.bean.InquireDTO;
import inquire.bean.InquirePaging;



public interface InquireService {

	public void inquireWrite(InquireDTO inquireDTO);

	public List<InquireDTO> getInquireList(String pg);

	public InquireDTO getInquireView(String seq);

	public InquireDTO getInquireModify(String seq);

	public void inquireModify(InquireDTO inquireDTO);

	public InquirePaging inquirePaging(String pg);

	public void inquireDelete(String seq);

	public void inquireReply(Map<String, String> map);

}
