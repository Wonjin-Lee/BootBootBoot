package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
