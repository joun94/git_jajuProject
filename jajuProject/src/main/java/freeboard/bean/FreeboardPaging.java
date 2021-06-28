package freeboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class FreeboardPaging {
	private int currentPage;//현재페이지
	private int pageBlock;//[이전][1][2][3][다음]
	private int pageSize;//1페이지당 5개씩
	private int totalA;//총글수
	private StringBuffer pagingHTML;
	
	public void makePagingHTML(){
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize; //총페이지수
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1;
		
		
		int endPage = startPage + pageBlock - 1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock)
			//<span id=""> 대신에 class를 주었다
			//pagingHTML.append("<span id='paging' onclick='freeboardPaging("+(startPage-1)+")'>[이전]</span>");
			pagingHTML.append("<span class='paging prevPaging' onclick='freeboardPaging("+(startPage-1)+")'> </span>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage) {
				//pagingHTML.append("[ <span id='currentPaging' onclick='freeboardPaging("+i+")'>"+i+"</span> ]");
				pagingHTML.append("<span class='paging currentPaging' onclick='freeboardPaging("+i+")'>"+i+"</span>");
			}else {
				//pagingHTML.append("[ <span id='paging' onclick='freeboardPaging("+i+")'>"+i+"</span> ]");
				pagingHTML.append("<span class='paging thisPaging' onclick='freeboardPaging("+i+")'>"+i+"</span>");
			}
		}//for
		
		if(endPage < totalP)
			//pagingHTML.append("<span id='paging' onclick='freeboardPaging("+(endPage+1)+")'>[다음]</span>");
			pagingHTML.append("<span class='paging nextPaging' onclick='freeboardPaging("+(endPage+1)+")'></span>");
	}	
}
