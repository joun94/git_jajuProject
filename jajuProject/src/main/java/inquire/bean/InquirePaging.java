package inquire.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class InquirePaging {
	private int currentPage; //현재페이지
	private int pageBlock; //[이전][1][2][3][다음]
	private int pageSize; //1페이지당 5개씩
	private int totalA; //총글수
	private StringBuffer pagingHTML; //편집이 가능하게 스트링버퍼를 사용했다.
	
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		//총페이지수
		int totalP =(totalA+pageSize-1)/pageSize; 
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock) 
			//pagingHTML.append("<span class='paging firstPaging' onclick='reportPaging("+(startPage)+")'> </span>");
			pagingHTML.append("<span class='paging prevPaging' onclick='inquirePaging("+(startPage-1)+")'> </span>");
		
		for(int i =startPage; i<=endPage; i++) {
			if(i==currentPage) {
				pagingHTML.append("<span class='paging currentPaging' onclick='inquirePaging("+i+")'>"+i+"</span>");
			}else {
				pagingHTML.append("<span class='paging thisPaging' onclick='inquirePaging("+i+")'>"+i+"</span>");
			}//else
		}//for
	
		if(endPage < totalP) 
			pagingHTML.append("<span class='paging nextPaging' onclick='inquirePaging("+(endPage+1)+")'></span>");
			//pagingHTML.append("<span class='paging lastPaging' onclick='reportPaging("+(endPage)+")'></span>");

	}
}
