package notice.service;

import java.util.List;
import java.util.Map;

import notice.bean.NoticeDTO;
import notice.bean.NoticePaging;

public interface NoticeService {

	public void noticeWrite(NoticeDTO noticeDTO);
	
	public List<NoticeDTO> getNoticeList(String pg);

	public NoticePaging noticePaging(String pg);

	public NoticeDTO getNotice(String notice_seq);

	public List<NoticeDTO> getNoticeSearchList(Map<String, String> map);

	public NoticePaging noticePaging(Map<String, String> map);

	public NoticeDTO getPage(String notice_seq);
	
}
