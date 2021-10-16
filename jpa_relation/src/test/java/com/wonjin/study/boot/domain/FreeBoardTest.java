package com.wonjin.study.boot.domain;

import com.wonjin.study.boot.repository.FreeBoardReplyRepository;
import com.wonjin.study.boot.repository.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}