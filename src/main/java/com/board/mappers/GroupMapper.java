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

    //그룹 내 사용자들 no 가져오기 찾기
    List<Integer> in_group_user_select(GroupDTO groupDTO);


}
