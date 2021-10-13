package com.wonjin.study.boot.repository;

import com.wonjin.study.boot.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {
    @Modifying
    @Query("update PDSFile f set f.pdsfile = ?2 where f.fno = ?1")
    public int updatePDSFile(Long fno, String newFileName);

    @Modifying
    @Query("delete from PDSFile f where f.fno = ?1")
    public int deletePDSFile(Long fno);

    @Query("select p, count(f) from PDSBoard p left outer join p.files f where p.pid > 0 group by p order by p.pid desc")
    public List<Object[]> getSummary();
}
