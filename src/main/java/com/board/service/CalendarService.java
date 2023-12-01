package com.board.service;


import com.board.dto.CalendarDTO;
import com.board.mappers.CalendarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CalendarService {

    private final CalendarMapper calendarMapper;

    public void calendar_create(CalendarDTO calendarDTO){
        calendarMapper.calendar_create(calendarDTO);
    };

}
