package mypage.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ScrapDTO {
	private int scrap_seq;//찜번호
	private String member_id;//회원아이디
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy.MM.dd")
	private Date scrap_date;//스크랩일자
	private int sale_seq;//원글)상품글번호
	private String sale_state;//원글)판매상태여부
	private String sale_subject;//원글)글제목
	private String sale_image1; //원글)글이미지경로1
}
