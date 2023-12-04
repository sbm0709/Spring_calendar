package com.board.service;


import com.board.dto.ChatDTO;
import com.board.mappers.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatService {


    private final ChatMapper chatMapper;

    public void insert_chat(ChatDTO chatDTO){
        chatMapper.chat_insert(chatDTO);
        log.warn("채팅 치기~");
    }


}
