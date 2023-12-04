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

import java.util.List;

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

        //로그인 성공 시 유저가 속한 groupNo 세션에 넘겨주기
        session.setAttribute("loginedUser", loginedUserDTO);
        if(userService.user_belong_groupNo(loginedUserDTO) != null){
            session.setAttribute("belongGroup", userService.user_belong_groupNo(loginedUserDTO));
            log.warn("belongGroup : " + userService.user_belong_groupNo(loginedUserDTO));
        }

        log.warn("login"+loginedUserDTO);

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
