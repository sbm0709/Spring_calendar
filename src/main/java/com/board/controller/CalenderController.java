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
import java.util.*;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;
    private final GroupService groupService;
    private final UserService userService;

    // 유저 검색 후 결과 조회


    @GetMapping("main/calendar")
    public String select(
            Model model,
            HttpSession session,
            @RequestParam(required = false) String groupNo
    ) {

        UserDTO loginedUserDTO = (UserDTO) session.getAttribute("loginedUser");
        if(loginedUserDTO == null){
            return "redirect:/";
        }

        List<GroupDTO> groups = new ArrayList<GroupDTO>();
        if(userService.user_belong_groupNo(loginedUserDTO) != null){
            for (Integer groupNum : userService.user_belong_groupNo(loginedUserDTO)) {
//                log.warn("참여중인 그룹 : " + groupService.Select_user_group(groupNum));
                groups.add(groupService.Select_user_group(groupNum));
            }
        }

//        log.warn("calendar"+loginedUserDTO);

        model.addAttribute("groupNo", groupNo);
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
//        log.warn(calendarDTO);

        if(calendarDTO.getGroupNo() == 0 || calendarDTO.getGroupNo() == -1){
            calendarService.save_data(calendarDTO);
        }
        else{
            calendarService.save_group_data(calendarDTO);
        }
    }


    // 삭제
    @ResponseBody
    @PostMapping("/delete")
    public void delete_data(@RequestBody CalendarDTO calendarDTO) {
        calendarService.delete_data(calendarDTO);
    }


    // 일정 가져오기
    @ResponseBody
    @PostMapping("/set")
    public List<CalendarDTO> set_data(HttpSession session,String groupNo) {
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");

        if(Objects.equals(groupNo, "0")){
            List<CalendarDTO> individualList = calendarService.select_individual_data(userDTO.getIdNo());
            List<CalendarDTO> groupList = new ArrayList<>();

            if(userService.user_belong_groupNo(userDTO) != null){
                for (Integer groupNum : userService.user_belong_groupNo(userDTO)) {
                    groupList.addAll(calendarService.select_group_data(groupNum));
                }
            }

            individualList.addAll(groupList);
            return individualList;
        }
        else if(Objects.equals(groupNo, "-1")){
            return calendarService.select_individual_data(userDTO.getIdNo());
        }
        else{
            return calendarService.select_group_data(Integer.parseInt(groupNo));
        }


    }


    //수정
    @ResponseBody
    @PatchMapping("/update")
    public void update_data(@RequestBody List<CalendarDTO> calList) {
        calendarService.update_data(calList.get(0), calList.get(1));
    }
}
