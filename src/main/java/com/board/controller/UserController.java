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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public String user_register(UserDTO userDTO, MultipartFile fileData){
        userService.user_register(userDTO, fileData);
        //메인(로그인)화면이동
        return "redirect:/";
    }

    //프로필 사진 표시
    @ResponseBody
    @GetMapping("/profile_download/{userID}")
    public byte[] view_profile(HttpSession session){
        UserDTO loginedUserDTO = (UserDTO) session.getAttribute("loginedUser");
        log.info(loginedUserDTO.getIdNo());
        return loginedUserDTO.getProfile();
    }

    @PostMapping("/invite")
    public String group_invite_user(String id, GroupDTO groupDTO){

        userService.group_invite_user(id, groupDTO);
        return "redirect:/main/calendar";
    }

    @PostMapping("/logout")
    public String user_logout(HttpSession session){
        //UserDTO loginedUserDTO = userMapper.user_login(userDTO);
        session.removeAttribute("loginedUser");

        return "redirect:/main";
    }
    //회원탈퇴 - db정보 지우기
    @PostMapping("/delete")
    public String user_delete(HttpSession session){
        log.info("회원 탈퇴");
        session.getAttribute("loginedUser");
        log.info(session.getAttribute("loginedUser"));
        userMapper.user_delete(session);
        return "redirect:/main";
    }
}
