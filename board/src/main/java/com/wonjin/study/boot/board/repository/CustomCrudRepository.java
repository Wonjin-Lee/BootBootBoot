package com.wonjin.study.boot.board.repository;

import com.wonjin.study.boot.board.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {

}
