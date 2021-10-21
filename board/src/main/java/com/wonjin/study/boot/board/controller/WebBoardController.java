package com.wonjin.study.boot.board.controller;

import com.wonjin.study.boot.board.domain.WebBoard;
import com.wonjin.study.boot.board.repository.WebBoardRepository;
import com.wonjin.study.boot.board.vo.PageMaker;
import com.wonjin.study.boot.board.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(PageVO vo, Model model) {
        Pageable pageable = vo.makePageable(0, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate(null, null), pageable);

        log.info("" + pageable);
        log.info("" + result);

        log.info("Total Page Number : " + result.getTotalPages());
        model.addAttribute("result", new PageMaker<>(result));
    }
}
