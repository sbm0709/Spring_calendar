package com.board.service;


import com.board.dto.CalendarDTO;
import com.board.mappers.CalendarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarMapper calendarMapper;

    public void calendar_create(CalendarDTO calendarDTO){
        calendarMapper.calendar_create(calendarDTO);
    };

    public void save_data(List<CalendarDTO> calendarDTOS) {
        calendarMapper.save_data(calendarDTOS);
    }

    public void delete_data(CalendarDTO calendarDTO) {
        calendarMapper.delete_data(calendarDTO);
    }
    
    public List<CalendarDTO> select_individual_data(int idNo) {
        return calendarMapper.select_individual_data(idNo);
    }



}
