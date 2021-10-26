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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
        Pageable pageable = vo.makePageable(0, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate(vo.getType(), vo.getKeyword()), pageable);

        log.info("" + pageable);
        log.info("" + result);

        log.info("Total Page Number : " + result.getTotalPages());
        model.addAttribute("result", new PageMaker<>(result));
    }

    @GetMapping("/register")
    public void registerGET(@ModelAttribute("vo") WebBoard vo) {
        log.info("register get");
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
        log.info("register post");
        log.info("" + vo);

        webBoardRepository.save(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("BoardNo : " + bno);
        webBoardRepository.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("Modify Board No : " + bno);
        webBoardRepository.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
        log.info("Delete Board No : " + bno);
        webBoardRepository.deleteById(bno);
        rttr.addFlashAttribute("msg", "success");
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }
}
