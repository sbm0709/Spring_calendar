package com.board.controller;

import com.board.dto.User;
import com.board.mappers.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    // 유저 로그인
    @PostMapping("/login")
    public String user_login(User user, HttpSession session){
        // DB에서 전달받은 데이터로 User를 조회한다
        User loginedUser = userMapper.user_login(user);
        // 조회된 유저가 없다는 것 == 로그인 실패!
        if(loginedUser == null){
            return "redirect:/main";
        }
        session.setAttribute("loginedUser", loginedUser);
        // 로그인 성공!
        return "redirect:/board/main";
    }
    // 유저 회원가입
    @PostMapping("/register")
    public String user_register(User user){
        // 전달받은 유저 정보로 회원가입을 시도한다
        userMapper.user_register(user);
        return "redirect:/main";
    }
}
