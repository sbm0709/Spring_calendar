package com.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatDTO {
    private int chatNo;
    private String Content;
    private int groupNo;
}
