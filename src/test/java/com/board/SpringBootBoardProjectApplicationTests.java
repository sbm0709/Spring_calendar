package com.board;

import com.board.dto.CalendarDTO;
import com.board.dto.GroupDTO;
import com.board.dto.UserDTO;
import com.board.mappers.GroupMapper;
import com.board.service.CalendarService;
import com.board.service.GroupService;
import com.board.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SpringBootBoardProjectApplicationTests {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupMapper groupMapper;

	@Autowired
	UserService userService;

	@Autowired
	CalendarService calendarService;

	@Test
	void contextLoads() {
//
	}

}
