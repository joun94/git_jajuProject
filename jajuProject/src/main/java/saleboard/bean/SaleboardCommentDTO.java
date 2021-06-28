package saleboard.bean;

import java.util.Date;

import lombok.Data;

@Data
public class SaleboardCommentDTO {
	
	private int comment_seq;
	private int sale_seq;
	private String member_id;
	private String comment_content;
	private int comment_ref;
	private int comment_lev;
	private int comment_step;
	private int comment_pseq;
	private Date logtime;
	
}
