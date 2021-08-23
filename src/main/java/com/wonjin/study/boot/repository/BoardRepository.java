package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findBoardByTitle(String title);
    Collection<Board> findByWriter(String writer);
    Collection<Board> findByWriterContaining(String writer);
    Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
    Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
    Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
}
