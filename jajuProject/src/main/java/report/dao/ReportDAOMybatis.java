package report.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import report.bean.CommentDTO;
import report.bean.ReportDTO;

@Transactional
@Repository
public class ReportDAOMybatis implements ReportDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDTO getMemberName(String report_id) {
		return sqlSession.selectOne("memberreportSQL.getMemberName", report_id);
		
	}
	
	@Override
	public void reportWrite(ReportDTO reportDTO) {
		sqlSession.insert("memberreportSQL.reportWrite", reportDTO);
		//System.out.println("2");
		System.out.println(reportDTO);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("memberreportSQL.getTotalA");
	}
	
	@Override
	public int getIdTotalA(String report_id) {
		return sqlSession.selectOne("memberreportSQL.getIdTotalA", report_id);
	}

	@Override
	public List<ReportDTO> getReportList(Map<String, String> map) {
		//System.out.println(map.get("report_id"));
		return sqlSession.selectList("memberreportSQL.getReportList", map);
	}

	@Override
	public ReportDTO getReport(String report_seq) {
		return sqlSession.selectOne("memberreportSQL.getReport", Integer.parseInt(report_seq));
	}

	@Override
	public void reportModify(ReportDTO reportDTO) {
		sqlSession.update("memberreportSQL.reportModify", reportDTO);
		
	}

	@Override
	public void reportDelete(String report_seq) {
		sqlSession.delete("memberreportSQL.reportDelete", Integer.parseInt(report_seq));
		
	}

	@Override
	public void commentWrite(Map<String, String> map) {
		//System.out.println(map);
		sqlSession.insert("memberreportSQL.commentWrite", map);
		
	}

	@Override
	public List<CommentDTO> getCommentList(String report_seq) {
		return sqlSession.selectList("memberreportSQL.getCommentList", Integer.parseInt(report_seq));
	}

	@Override
	public CommentDTO getComment(String comment_seq) {
		return sqlSession.selectOne("memberreportSQL.getComment", Integer.parseInt(comment_seq));
	}

	@Override
	public void commentModify(CommentDTO commentDTO) {
		sqlSession.update("memberreportSQL.commentModify", commentDTO);
		
	}
	
	@Override
	public void commentDelete(Map<String, String> map) {
		sqlSession.delete("memberreportSQL.commentDelete", map);
		
	}

}
