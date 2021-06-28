package mypage.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MessageDTO {

    private int message_seq;
    private String message_reader;
    private String message_writer;
    private String message_subject;
    private String message_content;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy.MM.dd")
    private Date message_date;
    int sale_seq;
    int message_change; 
}
