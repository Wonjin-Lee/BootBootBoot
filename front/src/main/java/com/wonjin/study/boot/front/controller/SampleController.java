package com.wonjin.study.boot.front.controller;

import com.wonjin.study.boot.front.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class SampleController {
    @GetMapping("/sample1")
    public void sample1(Model model) {
        model.addAttribute("greeting", "안녕하세요");
    }

    @GetMapping("/sample2")
    public void sample2(Model model) {
        MemberVO member = new MemberVO(123, "u00", "p00", "이원진", new Timestamp(System.currentTimeMillis()));

        model.addAttribute("member", member);
    }

    @GetMapping("/sample3")
    public void sample3(Model model) {
        List<MemberVO> memberList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            memberList.add(new MemberVO(i, "u0" + i, "p0" + i, "이원진" + i, new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("memberList", memberList);
    }

    @GetMapping("/sample4")
    public void sample4(Model model) {
        List<MemberVO> memberList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            memberList.add(new MemberVO(i, "u000" + i % 3, "p0000" + i % 3, "이원진" + i, new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("memberList", memberList);
    }

    @GetMapping("/sample6")
    public void sample6(Model model) {
        List<MemberVO> memberList = new ArrayList<>();

        IntStream.rangeClosed(0, 10).forEach(i -> {
            memberList.add(new MemberVO(i, "u0" + i, "p0" + i, "이원진" + i, new Timestamp(System.currentTimeMillis())));
        });

        model.addAttribute("memberList", memberList);

        String result = "SUCCESS";
        model.addAttribute("result", result);
    }
}
