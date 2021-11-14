package com.wonjin.study.boot.board.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.wonjin.study.boot.board.domain.QWebBoard;
import com.wonjin.study.boot.board.domain.QWebReply;
import com.wonjin.study.boot.board.domain.WebBoard;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {
    public CustomCrudRepositoryImpl() {
        super(WebBoard.class);
    }

    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page) {
        log.info("Type : " + type);
        log.info("Keyword : " + keyword);
        log.info("Page : " + page);

        QWebBoard board = QWebBoard.webBoard;
        QWebReply reply = QWebReply.webReply;

        JPQLQuery<WebBoard> query = from(board);

        JPQLQuery<Tuple> tuple = query.select(board.bno, board.title, reply.count(), board.writer, board.regdate);

        tuple.leftJoin(reply);
        tuple.on(board.bno.eq(reply.board.bno));
        tuple.where(board.bno.gt(0L));

        if (type != null) {
            switch (type.toLowerCase()) {
                case "title":
                    tuple.where(board.title.like("%" + keyword + "%"));
                    break;
                case "content":
                    tuple.where(board.content.like("%" + keyword + "%"));
                    break;
                case "writer":
                    tuple.where(board.writer.like("%" + keyword + "%"));
                    break;
            }
        }

        tuple.groupBy(board.bno);
        tuple.orderBy(board.bno.desc());
        tuple.offset(page.getOffset());
        tuple.limit(page.getPageSize());

        List<Tuple> list = tuple.fetch();

        List<Object[]> resultList = new ArrayList<>();

        list.forEach(t -> {
            resultList.add(t.toArray());
        });

        long total = tuple.fetchCount();

        return new PageImpl<>(resultList, page, total);
    }
}
