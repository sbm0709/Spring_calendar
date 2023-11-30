package com.board.controller;

import com.board.dto.Board;
import com.board.dto.User;
import com.board.mappers.BoardMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardMapper boardMapper;

    // 전체 게시판을 보여주는 메인 화면 GET
    @GetMapping("/main")
    public String view_main_board(
            Model model,
            @SessionAttribute(value = "loginedUser", required = false) User user
    ){
        log.info("/board/main - GET");
        // Session에 유저가 없다는 것은 로그인 하지 않고 왔다는 의미
        if(!login_check(user)) return "redirect:/main";
        // 로그인 되어있다면
        model.addAttribute("boards", boardMapper.get_all_boards());
        return "main/board";
    }

    // 하나의 게시물을 보여주는 화면
    @GetMapping("/view")
    public String view_board(
            @SessionAttribute(value = "loginedUser", required = false) User user,
            @RequestParam(defaultValue = "0") int no,
            Model model){
        log.info("/board/view - GET");
        // 사용자가 no 값을 이상하게 조작했다면, 혹은 로그인 되어있지 않다면
        if(no <= 0 || !login_check(user)){
            // 메인 화면으로 보낸다
            return "redirect:/board/main";
        }
        // 게시물을 조회한다
        Board board = boardMapper.get_board(no);
        // 해당 게시물의 조회수를 1 올린다
        board.setCount(board.getCount() + 1);
        // 조회수를 올리는 코드 => 게시물을 수정한다
        boardMapper.modify_board(board);
        // 화면에 board를 가지고 이동한다
        model.addAttribute("board", board);
        return "main/view";
    }

    // 게시판 글쓰기 창을 보여주는 GET
    @GetMapping("/write")
    public String view_write_board(){
        log.info("/board/write - GET");
        return "main/write";
    }

    // 게시판 글쓰기 POST
    @PostMapping("/write")
    public String write_board(
            Board board,
            @SessionAttribute("loginedUser") User user
    ){
        log.info("/board/write - POST");
        // 해당 유저가 로그인 되어있지 않다면 main으로
        if(!login_check(user)) return "redirect:/main";
        // 게시판 작성자의 아이디를 현재 로그인한 아이디로 설정한다
        board.setUsername(user.getId());
        // 게시판을 작성한다 - DB에 등록(write)
        boardMapper.write_board(board);
        // 게시판 메인 화면으로 이동
        return "redirect:/board/main";
    }

    // 게시판 글 수정
    @PostMapping("/modify")
    public String modify_board(Board board){
        log.info("/board/modify - POST");
        log.info(board);
        boardMapper.modify_board(board);
        return "redirect:/board/main";
    }

//    *************************************************************
    private boolean login_check(User user){
        if(user == null){
            log.warn("로그인이 되어있지 않음!");
            return false;
        }
        return true;
    }






}
