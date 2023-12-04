package com.board.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatDTO {
    private int chatNo;
    private String Content;
    private int groupNo;
}
