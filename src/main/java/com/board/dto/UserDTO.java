package com.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private int idNo;
    private String id;
    private String pw;
    private File profile;
}
