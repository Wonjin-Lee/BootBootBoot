package com.wonjin.study.boot.board.repository;

import com.wonjin.study.boot.board.domain.WebBoard;
import com.wonjin.study.boot.board.domain.WebReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {
    @Query("select r from WebReply r where r.board = ?1 and r.rno > 0 order by r.rno asc")
    List<WebReply> getRepliesOfBoard(WebBoard board);
}
