package com.board.controller;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.UserMapper;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;


    // 유저 로그인
    @PostMapping("/login")
    public String user_login(UserDTO userDTO, HttpSession session){
        // DB에서 전달받은 데이터로 User를 조회한다
        UserDTO loginedUserDTO = userMapper.user_login(userDTO);
        // 조회된 유저가 없다는 것 == 로그인 실패!
        if(loginedUserDTO == null){
            return "redirect:/";
        }
        session.setAttribute("loginedUser", loginedUserDTO);
        // 로그인 성공!
        return "redirect:/board/main";
    }
    // 유저 회원가입
    @PostMapping("/register")
    public String user_register(UserDTO userDTO){
        // 전달받은 유저 정보로 회원가입을 시도한다
        userMapper.user_register(userDTO);
        return "redirect:/main";
    }

    @PostMapping("/invite")
    public String group_invite_user(String id, GroupDTO groupDTO){

        userService.group_invite_user(id, groupDTO);
        return "redirect:/main/calendar";
    }
}
