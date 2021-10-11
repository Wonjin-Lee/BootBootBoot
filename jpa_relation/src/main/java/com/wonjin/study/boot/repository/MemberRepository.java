package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {
//    @Query("select m.uid, count(p) from Member m left outer join Profile p on m.uid = p.member where m.uid = ?1 group by m")
    @Query("select m, p from Member m left outer join Profile p on m.uid = ?1 and p.current = true")
    List<Object[]> getMemberWithProfileCount(String uid);
}
