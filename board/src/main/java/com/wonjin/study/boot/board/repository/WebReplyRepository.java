package com.wonjin.study.boot.board.repository;

import com.wonjin.study.boot.board.domain.WebReply;
import org.springframework.data.repository.CrudRepository;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {
    
}
