package com.board;

import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.GroupMapper;
import com.board.service.GroupService;
import com.board.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBoardProjectApplicationTests {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupMapper groupMapper;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
//		groupMapper.user_group_insert(new GroupDTO());
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroupName("testGroup");
		groupDTO.setGroupNo(10);
		UserDTO userDTO = new UserDTO();
		userDTO.setId("test");
		userDTO.setIdNo(3);
//		userService.group_invite_user("test2",groupDTO);
//		groupDTO.setCreateIdNo(2);
//		groupService.group_create(groupDTO, userDTO);
		userService.group_secession_user("test2",groupDTO);


//		groupService.group_delete();

	}

}
