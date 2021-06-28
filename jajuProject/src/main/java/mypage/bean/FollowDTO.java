package mypage.bean;

import lombok.Data;

@Data
public class FollowDTO {
    private int follow_seq; 
    private String member_id; 
    private String follow_id; 
    private int follow_state;
}
