package com.wonjin.study.boot.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomWebBoard {
    Page<Object[]> getCustomPage(String type, String keyword, Pageable page);
}
