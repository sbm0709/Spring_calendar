package com.board.service;


import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.board.mappers.GroupMapper;

@Service
@RequiredArgsConstructor
@Log4j2
public class GroupService {

    private final GroupMapper groupMapper;

    public void group_create(GroupDTO groupDTO, HttpSession session){
        UserDTO loginUserDTO = (UserDTO) session.getAttribute("loginUser");
        groupDTO.setCreateId(loginUserDTO.getId());
        groupMapper.group_create(groupDTO);
    }

}
