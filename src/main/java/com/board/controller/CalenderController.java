package com.board.controller;

import com.board.dto.CalendarDTO;
import com.board.dto.UserDTO;
import com.board.formatter.LocalDateFormatter;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;
    private final GroupService groupService;

    // 유저 검색 후 결과 조회

    @GetMapping("main/calendar")
    public String select(Model model,HttpSession session) {
        List<String> groupNames = new ArrayList<String>();
        if(session.getAttribute("belongGroup") != null) {
            for (Integer groupNo : (List<Integer>) session.getAttribute("belongGroup")) {
                log.warn("참여중인 그룹 : " + groupService.Select_user_group(groupNo));
                groupNames.add(groupService.Select_user_group(groupNo));
            }
        }
        model.addAttribute("belongGroup", groupNames);
        return "main/calendar";
    }

    // 수정사항 저장
    @ResponseBody
    @PostMapping("/save")
    public void get_data(@RequestBody CalendarDTO calendarDTO, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");

        calendarDTO.setStart(calendarDTO.getStart().plusHours(9));
        calendarDTO.setEnd(calendarDTO.getEnd().plusHours(9));
        calendarDTO.setIdNo(userDTO.getIdNo());
        log.warn(calendarDTO);
        calendarService.save_data(calendarDTO);
    }

    // 삭제
    @ResponseBody
    @PostMapping("/delete")
    public void delete_data(@RequestBody CalendarDTO calendarDTO) {
        calendarService.delete_data(calendarDTO);
    }

    // db 전송
    @ResponseBody
    @PostMapping("/set")
    public List<CalendarDTO> set_data(HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");
        return calendarService.select_individual_data(userDTO.getIdNo());
    }

    //수정
    @ResponseBody
    @PostMapping("/update")
    public void update_data(@RequestBody List<CalendarDTO> calList) {

        calendarService.update_data(calList.get(0), calList.get(1));

    }
}
