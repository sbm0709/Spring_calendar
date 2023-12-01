package com.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Calendar {
    private int calendarNo;
    private String title;
    private String userContent;
    private LocalDateTime start;
    private LocalDateTime end;
    private String calendarType;
    private int idNo;
}
