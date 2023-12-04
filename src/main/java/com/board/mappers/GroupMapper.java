package com.board.mappers;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.dto.UserGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {


    //group 생성
    void group_create(GroupDTO groupDTO);
    
    //그룹생성자 user_group에 넣기
    void user_group_insert(GroupDTO groupDTO);


    //그룹 삭제
    void group_delete(GroupDTO groupDTO);

    //그룹 내 user No 가져오기
    List<Integer> in_group_user_select(GroupDTO groupDTO);


    //자신이 소속된 그룹No 가져오기
    List<Integer> user_belong_group_select(UserDTO userDTO);


    //그룹 넘버 가지고 그룹 이름 가져오기
    String user_belong_group_name_select(int groupNo);

}
