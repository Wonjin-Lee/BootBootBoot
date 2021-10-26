package com.wonjin.study.boot.board.repository;

import com.wonjin.study.boot.board.domain.WebBoard;
import com.wonjin.study.boot.board.domain.WebReply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTest {
    @Autowired
    WebReplyRepository replyRepository;

    @Test
    public void testInsertReplies() {
        Long[] arr = {300L, 299L, 298L};

        Arrays.stream(arr).forEach(num -> {
            WebBoard board = new WebBoard();
            board.setBno(num);

            IntStream.rangeClosed(0, 9).forEach(i -> {
                WebReply reply = new WebReply();
                reply.setReplyText("Reply ..." + i);
                reply.setReplyer("replyer" + i);
                reply.setBoard(board);

                replyRepository.save(reply);
            });
        });
    }
}