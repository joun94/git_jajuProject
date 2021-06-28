package inquire.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class InquireDTO {
	private int inquiry_seq;
	private String inquiry_id = "hong";
	private String inquiry_name = "홍길동";
	private String inquiryType;
	private String inquiry_telephone;
	private String inquiry_subject;
	private String inquiry_contents;
	private String inquiry_image1;
	private String inquiry_image2;
	private String inquiry_image3;
	private int inquiry_pseq;
	private int inquiry_ref;
	private int inquiry_lev;
	private String inquiry_state ="처리중";
//	private int inquiry_step;
//	private int inquiry_reply;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date logtime;


}
