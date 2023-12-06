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

import java.util.Arrays;
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
//        log.warn("login"+loginedUserDTO); 절대 키지마!!!!!!!!!
        return "redirect:/main/calendar";
    }

    // 유저 회원가입
    @PostMapping("/register")
    public String user_register(UserDTO userDTO, MultipartFile fileData){
        log.info("register 들어옴");
        userService.user_register(userDTO, fileData);
        //메인(로그인)화면이동
        return "redirect:/";
    }

    //프로필 사진 표시
    @ResponseBody
    @GetMapping("/profile_download/")
    public byte[] view_profile(HttpSession session){
        UserDTO loginedUserDTO = (UserDTO) session.getAttribute("loginedUser");
//        log.warn("controller"+ Arrays.toString(loginedUserDTO.getProfile()));
        return loginedUserDTO.getProfile();
    }

    @PostMapping("/invite")
    public String group_invite_user(String id, String groupNo){
        userService.group_invite_user(id, Integer.parseInt(groupNo));
        return "redirect:/main/calendar";
    }
    //로그아웃
    @GetMapping("/logout")
    public String user_logout(HttpSession session){
        //UserDTO loginedUserDTO = userMapper.user_login(userDTO);
//        session.removeAttribute("loginedUser");
        session.invalidate();

        return "redirect:/";
    }
    //회원탈퇴
    @GetMapping("/delete")
    public String user_delete(HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");
        userMapper.user_delete(userDTO.getId());
        return "redirect:/";
    }


    //프로필 사진 변경
    @PostMapping("/change_img")
    public String change_profile(MultipartFile data, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("loginedUser");
        log.warn(data.getOriginalFilename());

        try {
            byte[] file = data.getBytes();
            userMapper.change_profile(file, userDTO.getIdNo());
            session.setAttribute("loginedUser", userMapper.user_select(userDTO.getId()));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        
        return "redirect:/main/calendar";
    }

}
