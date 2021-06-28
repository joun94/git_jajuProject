package freeboard.dao;

import java.util.List;
import java.util.Map;

import freeboard.bean.FreeboardCommentDTO;
import freeboard.bean.FreeboardDTO;

public interface FreeboardDAO {

	public void freeboardWrite(FreeboardDTO freeboardDTO);

	public FreeboardDTO getFreeboardView(int board_seq);

	public void freeboardModify(FreeboardDTO freeboardDTO);

	public void freeboardDelete(int board_seq);

	public int getTotalA();

	public List<FreeboardDTO> getFreeboardList(Map<String, String> map);

	public List<FreeboardDTO> getFreeboardSearchList(Map<String, String> map);

	public int getSearchTotal(Map<String, String> map);

	public void writeFreeboardComment(Map<String, String> map);

	public List<FreeboardCommentDTO> getFreeboardComment(Map<String, String> map);

	public FreeboardCommentDTO getFreeboardCommentOne(String string);

	public void writeReplyFreeboardComment(Map<String, String> map);

	public void commentModify(Map<String, String> map);

	public void commentDelete(FreeboardCommentDTO freeboardCommentDTO);

	public int getCommentTotal2(int board_seq);

	public void upHit_board(int board_seq);

	public FreeboardDTO getPage(int board_seq);


}
