package com.wonjin.study.boot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
@Table(name = "tb1_free_replies")
@EqualsAndHashCode(of = "rno")
public class FreeBoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String reply;
    private String replyer;

    @ManyToOne
    private FreeBoard board;

    @CreationTimestamp
    private Timestamp replydate;
    @UpdateTimestamp
    private Timestamp updatedate;
}
