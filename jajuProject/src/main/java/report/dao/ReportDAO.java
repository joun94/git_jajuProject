package report.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import report.bean.CommentDTO;
import report.bean.ReportDTO;

public interface ReportDAO {
	
	public MemberDTO getMemberName(String report_id);

	public void reportWrite(ReportDTO reportDTO);

	public List<ReportDTO> getReportList(Map<String, String> map);

	public int getTotalA(); //총 글수
	
	public int getIdTotalA(String report_id); //ID 값의 총 글수

	public ReportDTO getReport(String report_seq);

	public void reportModify(ReportDTO reportDTO);

	public void reportDelete(String report_seq);

	
	public void commentWrite(Map<String, String> map);

	public List<CommentDTO> getCommentList(String report_seq);

	public CommentDTO getComment(String comment_seq);

	public void commentModify(CommentDTO commentDTO);

	public void commentDelete(Map<String, String> map);

}
