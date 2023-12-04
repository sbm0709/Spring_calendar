package com.board.controller;



import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequestMapping("/group")
@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;



    @PostMapping("/create")
    public String createGroup(
            GroupDTO groupDTO,
            HttpSession session //
    ){

        log.fatal("group/create - post도착");
        log.warn(groupDTO);
        UserDTO loginUserDTO = (UserDTO) session.getAttribute("loginedUser");
        log.warn(loginUserDTO);
        groupService.group_create(groupDTO, loginUserDTO);

        log.warn("그룹 생성 종료");

        return "redirect:/main/calendar";
    }


}
