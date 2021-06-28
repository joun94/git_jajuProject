package saleboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SaleboardPaging {
	private int currentPage;//����������
	private int pageBlock;//����[1][2][3]����
	private int pageSize;// 1�������� 5����
	private int totalA;//�ѱۼ�
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA+pageSize-1)/pageSize;//�� ������ ��
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1;
		
		int endPage = startPage + pageBlock - 1;
		if(endPage > totalP) endPage = totalP;
		
		if(startPage > pageBlock) {
			pagingHTML.append("<span id='paging' class='prevPaging' onclick='saleboardPaging("+(startPage-1)+")'></span>");
		}
		
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage) {
				pagingHTML.append("<span id='currentPaging' onclick='saleboardPaging("+ i +")'><strong>"+i+"</strong></span>");
			} else {
				pagingHTML.append("<span id='paging' onclick='saleboardPaging("+ i +")'><strong>"+i+"<strong></span>");
			}
		}
		
		if(endPage < totalP) { 
			pagingHTML.append("<span id='paging' class='nextPaging' onclick='saleboardPaging("+(endPage+1)+")'></span>");
		}
		
	}
		
		
}


