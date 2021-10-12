package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.PDSBoard;
import com.wonjin.study.boot.domain.PDSFile;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class PDSBoardRepositoryTest {
    @Autowired
    PDSBoardRepository repo;

    @Test
    public void testInsertPDS() {
        PDSBoard pds = new PDSBoard();
        pds.setPname("Document");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds");

        repo.save(pds);
    }
}