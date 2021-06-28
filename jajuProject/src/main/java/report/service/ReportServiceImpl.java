package report.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import report.bean.CommentDTO;
import report.bean.ReportDTO;
import report.bean.ReportPaging;
import report.dao.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ReportPaging reportPaging;

	@Override
	public ReportDTO getReportInformation(Map<String, String> map) {
		
		return reportDAO.getReportInformation(map);
	}
	
	@Override
	public void reportWrite(ReportDTO reportDTO) {
		//System.out.println(reportDTO);
		reportDAO.reportWrite(reportDTO);
	}
	
	@Override
	public List<ReportDTO> getReportList(String pg, String report_id) {
		
		//System.out.println(memId+ "       2");
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10; //map에 언제 pg 값을 넣었나?
		int startNum = endNum-9;
		
		Map<String, String> map = new HashedMap<String, String>();
		map.put("report_id", report_id);
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
				
		return reportDAO.getReportList(map);
	}
	
	@Override
	public ReportPaging reportPaging(String pg, String report_id) {
		int totalA = reportDAO.getIdTotalA(report_id); //총글수
		
		reportPaging.setCurrentPage(Integer.parseInt(pg));//현재 페이지
		reportPaging.setPageBlock(5);
		reportPaging.setPageSize(10);
		reportPaging.setTotalA(totalA);
		reportPaging.makePagingHTML();
		
		return reportPaging;
	}

	@Override
	public ReportDTO getReport(String report_seq) {
		return reportDAO.getReport(report_seq);
	}

	@Override
	public void reportModify(ReportDTO reportDTO) {
		reportDAO.reportModify(reportDTO);
		
	}

	@Override
	public void reportDelete(String report_seq) {
		reportDAO.reportDelete(report_seq);
		
	}

	@Override
	public void commentWrite(Map<String, String> map) {
		reportDAO.commentWrite(map);
		
	}

	@Override
	public List<CommentDTO> getCommentList(String report_seq) {
		return reportDAO.getCommentList(report_seq);
	}

	@Override
	public CommentDTO getComment(String comment_seq) {
		return reportDAO.getComment(comment_seq);
	}

	@Override
	public void commentModify(CommentDTO commentDTO) {
		reportDAO.commentModify(commentDTO);
		
	}


	@Override
	public void commentDelete(Map<String, String> map) {
		reportDAO.commentDelete(map);
		
	}

}