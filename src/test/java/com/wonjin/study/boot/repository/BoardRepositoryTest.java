package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}