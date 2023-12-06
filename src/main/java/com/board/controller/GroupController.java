package com.board.controller;



import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.service.CalendarService;
import com.board.service.GroupService;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequestMapping("/group")
@Controller
@RequiredArgsConstructor
public class GroupController {


    private final GroupService groupService;

    private final UserService userService;




    @PostMapping("/create")
    public String createGroup(
            GroupDTO groupDTO,
            HttpSession session //
    ){

        log.fatal("group/create - post도착");
        log.warn(groupDTO);
        UserDTO loginUserDTO = (UserDTO) session.getAttribute("loginedUser");
        //log.warn(loginUserDTO);
        groupService.group_create(groupDTO, loginUserDTO);

        log.warn("그룹 생성 종료");

        return "redirect:/main/calendar";
    }

    @PostMapping("/secession")
    public String group_secession(HttpSession session, String groupNo){
        UserDTO user = (UserDTO) session.getAttribute("loginedUser");

        if(user.getIdNo() == groupService.group_created_idNo_select(Integer.parseInt(groupNo))) {
            groupService.group_delete(Integer.parseInt(groupNo));
            return "redirect:/main/calendar";
        }
        userService.group_secession_user(user.getIdNo(), Integer.parseInt(groupNo));

        return "redirect:/main/calendar";
    }

    // secession에 포함으로 안씀
//    @PostMapping("/delete")
//    public String group_delete(String groupNo){
//        groupService.group_delete(Integer.parseInt(groupNo));
//        return "redirect:/main/calendar";
//    }

}
