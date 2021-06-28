package mypage.bean;

import lombok.Data;

/*
create table keyword(
keyword_id varchar2(20) not null,
keyword1~5.
); */
@Data
public class MypageDTO {
	private String keyword_id;
	private String keyword1,keyword2,keyword3,keyword4,keyword5;

}
