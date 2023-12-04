package com.board.controller;

import com.board.dto.CalendarDTO;
import com.board.dto.UserDTO;
import com.board.mappers.CalendarMapper;
import com.board.service.CalendarService;
import com.board.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;
    private final GroupService groupService;

    // 유저 검색 후 결과 조회

    @GetMapping("main/calendar")
    public String select(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");
//        select 해서 정보 주기

        List<CalendarDTO> calendarDTOList = calendarService.select_individual_data(userDTO.getIdNo());
        session.setAttribute("calData", calendarDTOList);
        model.addAttribute("calData", calendarDTOList);
        return "main/calendar";
    }

    // 수정사항 저장
    @ResponseBody
    @PostMapping("/save")
    public void get_data(@RequestBody List<CalendarDTO> calendarDTOS, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");
        log.warn(userDTO.getIdNo());
        for (CalendarDTO cal : calendarDTOS) {
            cal.setIdNo(userDTO.getIdNo());
        }
        calendarService.save_data(calendarDTOS);
    }

    // 수정사항 삭제
    @ResponseBody
    @PostMapping("/delete")
    public void delete_data(@RequestBody CalendarDTO calendarDTO) {
        calendarService.delete_data(calendarDTO);
    }
}
