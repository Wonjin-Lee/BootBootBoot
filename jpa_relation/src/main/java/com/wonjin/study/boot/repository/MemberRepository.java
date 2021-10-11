package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {

}
