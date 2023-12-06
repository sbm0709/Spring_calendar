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



    //그룹 생성
    @PostMapping("/create")
    public String createGroup(
            GroupDTO groupDTO,
            HttpSession session //
    ){
        UserDTO loginUserDTO = (UserDTO) session.getAttribute("loginedUser");
        groupService.group_create(groupDTO, loginUserDTO);

        return "redirect:/main/calendar";
    }

    //그룹 탈퇴/ 생성자일시 삭제
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


}
