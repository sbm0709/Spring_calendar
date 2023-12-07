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


    public void save_data(CalendarDTO calendarDTO) {
        calendarMapper.save(calendarDTO);
    }

    public void delete_data(CalendarDTO calendarDTO) {
        calendarMapper.delete_data(calendarDTO);
    }
    
    public List<CalendarDTO> select_individual_data(int idNo) {
        return calendarMapper.select_individual_data(idNo);
    }

    public List<CalendarDTO> select_group_data(int groupNo) {
        return calendarMapper.select_group_data(groupNo);
    }

    public void update_data(CalendarDTO cal, CalendarDTO oldCal) {
        calendarMapper.update_data(cal, oldCal);
    }

    public void save_group_data(CalendarDTO calendarDTO) {
        calendarMapper.save_group_data(calendarDTO);
    }

    public List<CalendarDTO> select_list(int idNo) {
        return calendarMapper.select_list(idNo);
    }


}
