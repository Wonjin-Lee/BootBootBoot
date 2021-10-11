package com.wonjin.study.boot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb1_members")
@EqualsAndHashCode(of = "uid")
public class Member {

    @Id
    private String uid;
    private String upw;
    private String uname;
}
