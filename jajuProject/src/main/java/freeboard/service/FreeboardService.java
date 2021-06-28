package freeboard.service;

import java.util.List;
import java.util.Map;

import freeboard.bean.FreeboardCommentDTO;
import freeboard.bean.FreeboardCommentPaging;
import freeboard.bean.FreeboardDTO;
import freeboard.bean.FreeboardPaging;

public interface FreeboardService {

	public void freeboardWrite(FreeboardDTO freeboardDTO);

	public FreeboardDTO getFreeboardView(int board_seq);

	public void freeboardModify(FreeboardDTO freeboardDTO);

	public void freeboardDelete(int board_seq);

	public FreeboardPaging freeboardPaging(String pg);

	public List<FreeboardDTO> getFreeboardList(String pg);

	public FreeboardPaging freeboardPaging(Map<String, String> map);

	public List<FreeboardDTO> getFreeboardSearchList(Map<String, String> map);

	public void writeFreeboardComment(Map<String, String> map);

	public List<FreeboardCommentDTO> getFreeboardComment(Map<String, String> map);

	public FreeboardCommentPaging freeboardCommentPaging(Map<String, String> map);

	public void writeReplyFreeboardComment(Map<String, String> map);

	public FreeboardCommentDTO getFreeboardCommentOne(String comment_seq);

	public void commentModify(Map<String, String> map);

	public void commentDelete(FreeboardCommentDTO freeboardCommentDTO);

	public void upHit_board(int board_seq);

	public FreeboardDTO getPage(int board_seq);


	
}
