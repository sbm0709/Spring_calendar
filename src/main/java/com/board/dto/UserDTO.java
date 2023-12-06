package com.board.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int idNo;
    private String id;
    private String pw;
    private byte[] profile;
}
