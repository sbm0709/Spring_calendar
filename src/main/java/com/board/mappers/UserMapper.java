package com.board.mappers;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.dto.UserGroupDTO;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    // 유저 회원가입
    void user_register(@Param("userDTO") UserDTO userDTO, @Param("data") byte[] data);

    // 유저 로그인
    UserDTO user_login(UserDTO userDTO);

    // id로 유저 찾기
    UserDTO user_select(String id);

    // 그룹에 유저 초대하기
    void user_invite(UserGroupDTO userGroupDTO);

    // 그룹 초대 시 중복초대 확인용 select
    UserDTO user_group_select(UserGroupDTO userGroupDTO);

    // 그룹 탈퇴
    void user_group_secession(UserGroupDTO userGroupDTO);

    //회원 탈퇴
    void user_delete(String id);

    // 프로필 사진 변경
    void change_profile(byte[] data, int idNo);

}




