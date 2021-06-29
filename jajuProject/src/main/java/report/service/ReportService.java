package report.service;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import report.bean.CommentDTO;
import report.bean.ReportDTO;
import report.bean.ReportPaging;

public interface ReportService {
	
	public void reportWrite(ReportDTO reportDTO);
	
	public MemberDTO getMemberName(String report_id);
	
	public List<ReportDTO> getReportList(String pg, String report_id);

	public ReportPaging reportPaging(String pg, String report_id);

	public ReportDTO getReport(String report_seq);

	public void reportModify(ReportDTO reportDTO);

	public void reportDelete(String report_seq);

	
	public void commentWrite(Map<String, String> map);

	public List<CommentDTO> getCommentList(String report_seq);

	public CommentDTO getComment(String comment_seq);

	public void commentModify(CommentDTO commentDTO);

	public void commentDelete(Map<String, String> map);

}
