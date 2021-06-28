package reviewboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ReviewboardPaging {
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
			pagingHTML.append("<span class='paging prevPaging' onclick='reviewboardPaging("+(startPage-1)+")'> </span>");
		
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage) {
				pagingHTML.append("<span class='paging currentPaging' onclick='reviewboardPaging("+i+")'>"+i+"</span>");
			}else {
				pagingHTML.append("<span class='paging thisPaging' onclick='reviewboardPaging("+i+")'>"+i+"</span>");
			}
		}//for
		
		if(endPage < totalP)
			pagingHTML.append("<span class='paging nextPaging' onclick='reviewboardPaging("+(endPage+1)+")'></span>");
	}	
}
