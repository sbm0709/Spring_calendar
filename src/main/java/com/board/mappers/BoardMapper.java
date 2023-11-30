package com.board.mappers;

import com.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> get_all_boards();
    Board get_board(int no);
    void write_board(Board board);
    void modify_board(Board board);
}
