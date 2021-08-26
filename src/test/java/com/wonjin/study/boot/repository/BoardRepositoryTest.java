package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        Board board = new Board();
        board.setTitle("게시물의 제목");
        board.setContent("게시물 내용 넣기...");
        board.setWriter("user00");

        boardRepository.save(board);
    }

    @Test
    public void testRead() {
        boardRepository.findById(1L).ifPresent((board) -> {
            System.out.println(board);
        });
    }

    @Test
    public void testUpdate() {
        System.out.println("Read First...");
        Board board = boardRepository.findById(1L).orElseThrow();

        System.out.println("Update Title...");
        board.setTitle("수정된 제목입니다.");

        System.out.println("Call Save()...");
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {
        System.out.println("Delete Entity...");

        boardRepository.deleteById(1L);
    }

    // 200개 튜플 생성
    @Test
    public void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle("제목.." + i);
            board.setContent("내용.." + i);
            board.setWriter("user0" + (i % 20));
            boardRepository.save(board);
        }
    }

    @Test
    public void testByTitle() {
        boardRepository.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriter() {
        Collection<Board> results = boardRepository.findByWriter("user00");

        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByWriterContaining() {
        Collection<Board> results = boardRepository.findByWriterContaining("05");

        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByTitleAndBno() {
        Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 50L);

        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testBnoOrderBy() {
        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);

        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testBnoOrderByPaging() {
        Pageable paging = PageRequest.of(0, 10);

        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);

        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testBnoPagingSort() {
        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = boardRepository.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE : " + result.getSize());
        System.out.println("TOTAL PAGES : " + result.getTotalPages());
        System.out.println("TOTAL COUNT : " + result.getTotalElements());
        System.out.println("NEXT : " + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitle2() {
        boardRepository.findByTitle("17").forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitle17() {
        boardRepository.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByPaging() {
        Pageable pageable = PageRequest.of(0, 10);

        boardRepository.findByPage(pageable).forEach(board -> System.out.println(board));
    }
}