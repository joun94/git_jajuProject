package freeboard.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FreeboardCommentDTO {
    private int comment_seq;
    private int board_seq;
    private String board_id;
    private String comment_content;
    private int comment_ref;
    private int comment_lev;
    private int comment_step;
    private int comment_pseq;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date logtime;

}
