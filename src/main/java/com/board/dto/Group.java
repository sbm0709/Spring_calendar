package com.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Group {
    private int groupNo;
    private String groupName;
    private int calendarNo;
    private int createId;
}
