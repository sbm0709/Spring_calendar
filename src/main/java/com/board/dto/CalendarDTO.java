package com.board.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {
    private int calendarNo;
    private String title;
    private String userContent;
    private LocalDateTime start;
    private LocalDateTime end;
    private String calendarType;
    private int idNo;
    private String allDay;
    private int groupNo;
    private String color;
}
