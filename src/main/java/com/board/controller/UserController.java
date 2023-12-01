package com.board.controller;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.UserMapper;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;
    // 유저 로그인
    @PostMapping("/login")
    public String user_login(UserDTO userDTO, HttpSession session){
        UserDTO loginedUserDTO = userMapper.user_login(userDTO);

        if(loginedUserDTO == null){
            return "redirect:/";
        }
        session.setAttribute("loginedUser", loginedUserDTO);
        log.warn("login"+loginedUserDTO);
        //캘린더 화면으로 넘어감 임시로 다른 페이지 넘어가게함
        return "redirect:/main/calendar";
    }

    //회원가입페이지로 넘어오기
    @GetMapping("/register")
    public String user_register(){
        return "/user/register";
    }
    // 유저 회원가입
    @PostMapping("/register")
    public String user_register(UserDTO userDTO){
        userService.user_register(userDTO);
        //메인(로그인)화면이동
        return "redirect:/";
    }

    @PostMapping("/invite")
    public String group_invite_user(String id, GroupDTO groupDTO){

        userService.group_invite_user(id, groupDTO);
        return "redirect:/main/calendar";
    }
}
