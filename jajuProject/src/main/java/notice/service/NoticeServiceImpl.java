package notice.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import notice.bean.NoticeDTO;
import notice.bean.NoticePaging;
import notice.dao.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private NoticePaging noticePaging;

	@Override
	public void noticeWrite(NoticeDTO noticeDTO) {
		//System.out.println(reportDTO);
		noticeDAO.noticeWrite(noticeDTO);
		
	}
	
	@Override
	public List<NoticeDTO> getNoticeList(String pg) {
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, String> map = new HashedMap<String, String>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return noticeDAO.getNoticeList(map);
	}
	
	@Override
	public NoticePaging noticePaging(String pg) {
		int totalA = noticeDAO.getTotalA(); //총글수
		
		noticePaging.setCurrentPage(Integer.parseInt(pg));//현재 페이지
		noticePaging.setPageBlock(5);
		noticePaging.setPageSize(10);
		noticePaging.setTotalA(totalA);
		noticePaging.makePagingHTML();
		
		return noticePaging;
	}

	@Override
	public NoticeDTO getNotice(String notice_seq) {
		return noticeDAO.getNotice(notice_seq);
	}

	@Override
	public List<NoticeDTO> getNoticeSearchList(Map<String, String> map) {
		//System.out.println(map);
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(map.get("pg"))*10; //map에 언제 pg 값을 넣었나?
		int startNum = endNum-9;
		
		//pg, searchOption, keyword, startNum, endNum
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return noticeDAO.getNoticeSearchList(map);
	}

	@Override
	public NoticePaging noticePaging(Map<String, String> map) {
		int totalA = noticeDAO.getTotalSearchA(map); //검색한 총글수
		
		noticePaging.setCurrentPage(Integer.parseInt(map.get("pg")));//현재 페이지
		noticePaging.setPageBlock(5);
		noticePaging.setPageSize(10);
		noticePaging.setTotalA(totalA);
		noticePaging.makePagingHTML();
		
		return noticePaging;
	}

	@Override
	public NoticeDTO getPage(String notice_seq) {
		
		return noticeDAO.getPage(notice_seq);
	}

}
