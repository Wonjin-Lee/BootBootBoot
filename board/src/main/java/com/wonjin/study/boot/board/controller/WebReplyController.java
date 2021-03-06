package com.wonjin.study.boot.board.controller;

import com.wonjin.study.boot.board.domain.WebBoard;
import com.wonjin.study.boot.board.domain.WebReply;
import com.wonjin.study.boot.board.repository.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {
    @Autowired
    private WebReplyRepository replyRepository;

    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
        log.info("Add Reply...");
        log.info("Board No : " + bno);
        log.info("Reply : " + reply);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        reply.setBoard(board);

        replyRepository.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<WebReply>> remove(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {
        log.info("Delete Reply : " + rno);

        replyRepository.deleteById(rno);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
        log.info("Modify Reply : " + reply);

        replyRepository.findById(reply.getRno()).ifPresent(origin -> {
            origin.setReplyText(reply.getReplyText());
            replyRepository.save(origin);
        });

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    @GetMapping("/{bno}")
    public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno") Long bno) {
        log.info("Get All Replies...");

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    private List<WebReply> getListByBoard(WebBoard board) throws RuntimeException {
        log.info("getListByBoard..." + board);
        return replyRepository.getRepliesOfBoard(board);
    }
}
