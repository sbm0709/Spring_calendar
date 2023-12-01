package com.board.controller;

import com.board.dto.CalendarDTO;
import com.board.mappers.CalendarMapper;
import com.board.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;


    @PostMapping("/save")
    public void get_data(List<CalendarDTO> calendarDTOS) {
        calendarService.save_data(calendarDTOS);
    }


    @PostMapping("/delete")
    public void delete_data(CalendarDTO calendarDTO) {
        calendarService.delete_data(calendarDTO);
    }
}
