package com.wonjin.study.boot.front.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MemberVO {
    private int no;
    private String id;
    private String password;
    private String name;
    private Timestamp regdate;
}
