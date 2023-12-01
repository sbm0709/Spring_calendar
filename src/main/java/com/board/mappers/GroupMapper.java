package com.board.mappers;

import com.board.dto.GroupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {

    void group_create(GroupDTO groupDTO);
    void user_group_insert(int userIdNo);

}
