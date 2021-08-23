package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {

}
