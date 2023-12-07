package com.board.mappers;


import com.board.dto.CalendarDTO;
import com.board.dto.GroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

    // 저장
    void save (CalendarDTO calendarDTO);
    // 삭제
    void delete_data(CalendarDTO calendarDTO);
    // 개인 일정 찾기
    List<CalendarDTO> select_individual_data(int idNo);
    // 그룹 일정 찾기
    List<CalendarDTO> select_group_data(int groupNo);
    // 일정 수정
    void update_data(CalendarDTO calendarDTO, CalendarDTO oldCalendarDTO);
    // 그룹 일정 추가
    void save_group_data(CalendarDTO calendarDTO);

    List<CalendarDTO> select_list(int idNo);

}
