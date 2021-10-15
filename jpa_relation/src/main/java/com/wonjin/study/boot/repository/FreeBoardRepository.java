package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.FreeBoard;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

}
