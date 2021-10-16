package com.wonjin.study.boot.domain;

import com.wonjin.study.boot.repository.FreeBoardReplyRepository;
import com.wonjin.study.boot.repository.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTest {
    @Autowired
    FreeBoardRepository boardRepository;

    @Autowired
    FreeBoardReplyRepository replyRepository;

    @Test
    public void insertDummy() {
        IntStream.range(1, 200).forEach(i -> {
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... " + i);
            board.setContent("Free Content.... " + i);
            board.setWriter("user" + i % 10);

            boardRepository.save(board);
        });
    }

    // 양방향 - 게시판 객체를 얻어온 후 댓글 리스트에 댓글을 추가한 후 게시판 객체를 저장
    @Transactional
    @Test
    public void insertReply2Way() {
        Optional<FreeBoard> result = boardRepository.findById(199L);

        result.ifPresent(board -> {
            List<FreeBoardReply> replies = board.getReplies();

            FreeBoardReply reply = new FreeBoardReply();
            reply.setReply("REPLY....");
            reply.setReplyer("replyer00");
            reply.setBoard(board);

            replies.add(reply);

            board.setReplies(replies);

            boardRepository.save(board);
        });
    }

    // 단방향 - 게시판 객체를 생성하여 ID만 세팅한 후 댓글 객체에 포함시켜 댓글 객체를 저장
    @Test
    public void insertReply1Way() {
        FreeBoard board = new FreeBoard();
        board.setBno(199L);

        FreeBoardReply reply = new FreeBoardReply();
        reply.setReply("REPLY...");
        reply.setReplyer("wonjin");
        reply.setBoard(board);

        replyRepository.save(reply);
    }

    @Test
    public void testList1() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepository.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + " : " + board.getTitle());
        });
    }

    @Transactional
    @Test
    public void testList2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepository.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + " : " + board.getTitle() + " : " + board.getReplies().size());
        });
    }

    @Test
    public void testList3() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepository.getPage(page).forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}