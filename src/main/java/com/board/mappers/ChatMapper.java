package com.board.mappers;


import com.board.dto.ChatDTO;
import com.board.dto.GroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {


    // 그룹 정보 주던지 넘버만 주던지
//    void chat_create(int groupNo);


    //채팅 치기
    void chat_insert(ChatDTO chatDTO);

    // 그룹 정보 받아서 채팅들 긁어오기
    List<ChatDTO> chat_select(GroupDTO groupDTO);

}
