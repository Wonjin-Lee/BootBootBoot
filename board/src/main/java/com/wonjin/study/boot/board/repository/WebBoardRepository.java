package com.wonjin.study.boot.board.repository;

import com.wonjin.study.boot.board.domain.WebBoard;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

}
