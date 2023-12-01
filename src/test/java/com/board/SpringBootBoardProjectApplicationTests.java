package com.board;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.GroupMapper;
import com.board.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBoardProjectApplicationTests {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupMapper groupMapper;

	@Test
	void contextLoads() {
//		groupMapper.user_group_insert(new GroupDTO());
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupName("testgroup");
		UserDTO userDTO = new UserDTO();
		userDTO.setId("test");
		userDTO.setIdNo(2);

//		groupDTO.setCreateIdNo();
		groupService.group_create(groupDTO, userDTO);
	}

}
