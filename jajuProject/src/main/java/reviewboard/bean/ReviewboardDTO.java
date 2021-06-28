package reviewboard.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReviewboardDTO {
	
	private int review_seq;
	private int sale_seq;
	private String sale_image1;
	private String member_id; //판매자 아이디
	private String review_id;
	private String sale_subject;
	private String review_subject;
	private String review_content;
	private String review_image1;
	private String review_image2;
	private String review_image3;
	private String review_manner;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date review_date;
	private int review_hit;
	
	private int prev_num; //이전글 글번호
	private int next_num; //다음글 글번호
	private String prev_subject; //이전글 제목
	private String next_subject; //다음글 제목
}
