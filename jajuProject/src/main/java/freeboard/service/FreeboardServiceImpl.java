package freeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freeboard.bean.FreeboardCommentDTO;
import freeboard.bean.FreeboardCommentPaging;
import freeboard.bean.FreeboardDTO;
import freeboard.bean.FreeboardPaging;
import freeboard.dao.FreeboardDAO;

@Service
public class FreeboardServiceImpl implements FreeboardService{
	
	@Autowired
	private FreeboardCommentPaging freeboardCommentPaging;
	
	@Autowired
	private FreeboardPaging freeboardPaging;

	@Autowired
	private FreeboardDAO freeboardDAO;

	@Override
	public void freeboardWrite(FreeboardDTO freeboardDTO) {
		if(freeboardDTO.getBoard_id() == null) {
			freeboardDTO.setBoard_id("");
		}
		
		if(freeboardDTO.getBoard_subject() == null) {
			freeboardDTO.setBoard_subject("");
		}
		
		if(freeboardDTO.getBoard_content() == null) {
			freeboardDTO.setBoard_content("");
		}
		
		if(freeboardDTO.getImage1() == null) {
			freeboardDTO.setImage1("");
		}
		
		if(freeboardDTO.getImage2() == null) {
			freeboardDTO.setImage2("");
		}
		if(freeboardDTO.getImage3() == null) {
			freeboardDTO.setImage3("");
		}
		
		freeboardDAO.freeboardWrite(freeboardDTO);
	}

	@Override
	public FreeboardDTO getFreeboardView(int board_seq) {
		return freeboardDAO.getFreeboardView(board_seq);
	}

	@Override
	public void freeboardModify(FreeboardDTO freeboardDTO) {

		if(freeboardDTO.getBoard_id() == null) {
			freeboardDTO.setBoard_id("");
		}
		
		if(freeboardDTO.getBoard_subject() == null) {
			freeboardDTO.setBoard_subject("");
		}
		
		if(freeboardDTO.getBoard_content() == null) {
			freeboardDTO.setBoard_content("");
		}
		
		if(freeboardDTO.getImage1() == null) {
			freeboardDTO.setImage1("");
		}
		
		if(freeboardDTO.getImage2() == null) {
			freeboardDTO.setImage2("");
		}
		if(freeboardDTO.getImage3() == null) {
			freeboardDTO.setImage3("");
		}
		
		freeboardDAO.freeboardModify(freeboardDTO);
		
	}

	@Override
	public void freeboardDelete(int board_seq) {
		freeboardDAO.freeboardDelete(board_seq);
		
	}

	@Override
	public FreeboardPaging freeboardPaging(String pg) {
		int totalA = freeboardDAO.getTotalA();
		
		freeboardPaging.setCurrentPage(Integer.parseInt(pg));
		freeboardPaging.setPageBlock(5);
		freeboardPaging.setPageSize(10);
		freeboardPaging.setTotalA(totalA);
		freeboardPaging.makePagingHTML();
		
		return freeboardPaging;
	}

	@Override
	public List<FreeboardDTO> getFreeboardList(String pg) {
		
		//1페이지당 10개씩
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return freeboardDAO.getFreeboardList(map);
	}

	@Override
	public FreeboardPaging freeboardPaging(Map<String, String> map) {
		int totalA = freeboardDAO.getSearchTotal(map); //검색한 총 글수
		
		freeboardPaging.setCurrentPage(Integer.parseInt(map.get("pg"))); //현재 페이지
		freeboardPaging.setPageBlock(5);
		freeboardPaging.setPageSize(10);
		freeboardPaging.setTotalA(totalA);
		freeboardPaging.makePagingHTML();
		
		return freeboardPaging;
	}

	@Override
	public List<FreeboardDTO> getFreeboardSearchList(Map<String, String> map) {
		//1페이지당 5개씩
		
		int endNum = Integer.parseInt(map.get("searchPg"))*10; //map에서 pg를 꺼냄
		int startNum = endNum - 9;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		return freeboardDAO.getFreeboardSearchList(map);
	}

	@Override
	public void writeFreeboardComment(Map<String, String> map) {
		freeboardDAO.writeFreeboardComment(map);
	}

	@Override
	public List<FreeboardCommentDTO> getFreeboardComment(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum +"");
		
		return freeboardDAO.getFreeboardComment(map);
	}

	@Override
	public FreeboardCommentPaging freeboardCommentPaging(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		freeboardCommentPaging.setCurrentPage(pg);
		freeboardCommentPaging.setPageBlock(3);
		freeboardCommentPaging.setPageSize(5);
		int board_seq = Integer.parseInt(map.get("board_seq"));
		int totalA = freeboardDAO.getCommentTotal2(board_seq);
		freeboardCommentPaging.setTotalA(totalA);
		
		freeboardCommentPaging.makePagingHTML();
		
		return freeboardCommentPaging;
	}

	@Override
	public void writeReplyFreeboardComment(Map<String, String> map) {
		FreeboardCommentDTO pDTO = freeboardDAO.getFreeboardCommentOne(map.get("comment_pseq"));
		map.put("comment_ref", pDTO.getComment_ref()+"");
		map.put("comment_lev", pDTO.getComment_lev()+"");
		map.put("comment_step", pDTO.getComment_step()+"");
		
		freeboardDAO.writeReplyFreeboardComment(map);
	}

	@Override
	public FreeboardCommentDTO getFreeboardCommentOne(String comment_seq) {
		return freeboardDAO.getFreeboardCommentOne(comment_seq);
	}

	@Override
	public void commentModify(Map<String, String> map) {
		freeboardDAO.commentModify(map);
	}

	@Override
	public void commentDelete(FreeboardCommentDTO freeboardCommentDTO) {
		freeboardDAO.commentDelete(freeboardCommentDTO);
		
	}

	@Override
	public void upHit_board(int board_seq) {
		freeboardDAO.upHit_board(board_seq);
	}

	@Override
	public FreeboardDTO getPage(int board_seq) {
		return freeboardDAO.getPage(board_seq);
	}

}
