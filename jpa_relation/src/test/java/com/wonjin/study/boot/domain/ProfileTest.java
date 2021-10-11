package com.wonjin.study.boot.domain;

import com.wonjin.study.boot.repository.MemberRepository;
import com.wonjin.study.boot.repository.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileTest {
    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;

    @Test
    public void testInsertMembers() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);

            memberRepo.save(member);
        });
    }

    @Test
    public void testInsertProfile() {
        Member member = new Member();
        member.setUid("user1");

        for (int i = 1; i < 5; i++) {
            Profile profile = new Profile();
            profile.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile.setCurrent(true);
            }

            profile.setMember(member);

            profileRepo.save(profile);
        }
    }
}