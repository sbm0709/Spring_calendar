package com.board.mappers;


import com.board.dto.CalendarDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {

    void calendar_create(CalendarDTO calendarDTO);

}
