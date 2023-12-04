package com.board.controller;

import com.board.dto.CalendarDTO;
import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.formatter.LocalDateFormatter;
import com.board.mappers.CalendarMapper;
import com.board.service.CalendarService;
import com.board.service.GroupService;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
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
    private final UserService userService;

    // 유저 검색 후 결과 조회

    @GetMapping("main/calendar")
    public String select(Model model,HttpSession session) {
        List<GroupDTO> groups = new ArrayList<GroupDTO>();
        UserDTO loginedUserDTO = (UserDTO) session.getAttribute("loginedUser");
        log.warn(loginedUserDTO);

        if(userService.user_belong_groupNo(loginedUserDTO) != null){
            for (Integer groupNo : userService.user_belong_groupNo(loginedUserDTO)) {
                log.warn("참여중인 그룹 : " + groupService.Select_user_group(groupNo));
                groups.add(groupService.Select_user_group(groupNo));
            }
    }
        model.addAttribute("belongGroup", groups);
        model.addAttribute("selectedList", calendarService.select_list(loginedUserDTO.getIdNo()));
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

    // 개인 일정 가져오기
    @ResponseBody
    @PostMapping("/set")
    public List<CalendarDTO> set_data(
            HttpSession session,
            @RequestParam(required = false) String groupName
            ) {

        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");

        return calendarService.select_individual_data(userDTO.getIdNo());

    }

    // 그룹 일정 가져오기
    @ResponseBody
    @PostMapping("/set_group")
    public void set_group_data(int groupNo) {

    }


    //수정
    @ResponseBody
    @PatchMapping("/update")
    public void update_data(@RequestBody List<CalendarDTO> calList) {
        calendarService.update_data(calList.get(0), calList.get(1));
    }
}
