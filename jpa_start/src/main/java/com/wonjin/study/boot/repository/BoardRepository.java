package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
    List<Board> findBoardByTitle(String title);
    Collection<Board> findByWriterContaining(String writer);
    Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
    Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
    Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
    List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
    Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
    @Query("select b from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    List<Board> findByTitle(String title);
    @Query("select b from Board b where b.content like %:content% and b.bno > 0 order by b.bno desc")
    List<Board> findByContent(@Param("content") String content);
    @Query("select b from #{#entityName} b where b.writer like %?1% and b.bno > 0 order by b.bno desc")
    List<Board> findByWriter(String writer);
    @Query("select b.bno, b.title, b.writer, b.regdate from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    List<Object[]> findByTitle2(String title);
    @Query(value = "select bno, title, writer from tb1_boards where title like CONCAT('%', ?1, '%') and bno > 0 order by bno desc", nativeQuery = true)
    List<Object[]> findByTitle3(String title);
    @Query("select b from Board b where b.bno > 0 order by b.bno desc")
    List<Board> findByPage(Pageable pageable);
}
