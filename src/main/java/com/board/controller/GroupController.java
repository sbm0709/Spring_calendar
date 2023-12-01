package com.board.controller;



import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.service.GroupService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j2
@RequestMapping("/group")
@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;



    @PostMapping("/create")
    public String createGroup(
            GroupDTO groupDTO,
            String userDTO
//            HttpSession session //
    ){
        log.warn(userDTO);
        log.warn(groupDTO);
//        UserDTO loginUserDTO = (UserDTO) session.getAttribute("loginUser");
        groupService.group_create(groupDTO, userDTO);
        return "redirect:/";
    }


}
