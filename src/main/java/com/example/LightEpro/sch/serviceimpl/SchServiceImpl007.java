package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;
import com.example.LightEpro.sch.mapper.SchMapper007;
import com.example.LightEpro.sch.service.SchService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl007 implements SchService007 {
    private final SchMapper007 schMapper007;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto007 modifySingleCal(SchRqDto007 schRqDto007) throws Exception {
        log.info("modifySingleCal Method Start !!!");
        log.info("modifySingleCal Method Request Data : " + schRqDto007);

        int curSeq = assignObject(schRqDto007);
        int deleteCalUsersCnt = deleteCalUsers(schRqDto007);
        int updateSingleCalCnt = updateSingleCal(schRqDto007);
        int insertCalOwnerCnt = insertCalOwner(schRqDto007);
        int insertCalManagerCnt = insertCalManager(schRqDto007.getManagers());

        SchRsDto007 schRsDto007 = SchRsDto007.builder()
                .calSeq(curSeq)
                .deleteCalUsersCnt(deleteCalUsersCnt)
                .updateSingleCalCnt(updateSingleCalCnt)
                .insertCalOwnerCnt(insertCalOwnerCnt)
                .insertCalManagerCnt(insertCalManagerCnt)
                .build();

        log.info("modifySingleCal Method Return Data : " + schRsDto007);
        log.info("modifySingleCal Method End !!!");
        return schRsDto007;
    }

    @Override
    public int assignObject(SchRqDto007 schRqDto007) throws Exception {
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto007);

        SchRqDto007.Emp emp = schRqDto007.getEmp();
        SchRqDto007.Calender calender = schRqDto007.getCalender();
        SchRqDto007.Owner owner = schRqDto007.getOwner();
        List<SchRqDto007.Manager> managers = schRqDto007.getManagers();

        int empSeq = emp.getEmpSeq();

        SchRqDto007.Calender createInfo = schMapper007.findCreateInfoByCalSeq(calender);
        int curSeq = calender.getCalSeq();
        int createSeq = createInfo.getCreateSeq();
        LocalDateTime createDate = createInfo.getCreateDate();

        calender.setCreateSeq(createSeq);
        calender.setCreateDate(createDate);
        calender.setModifySeq(empSeq);

        if (managers != null && managers.size() > 0) {
            for (SchRqDto007.Manager manager : managers) {
                manager.setCalSeq(curSeq);
                manager.setCreateDate(createDate);
                manager.setCreateSeq(createSeq);
                manager.setModifySeq(empSeq);
            }
        }

        log.info("assignObject Method Result Data : " + curSeq);
        log.info("assignObject Method End !!!");
        return curSeq;
    }

    @Override
    public int deleteCalUsers(SchRqDto007 schRqDto007) throws Exception {
        return 0;
    }

    @Override
    public int updateSingleCal(SchRqDto007 schRqDto007) throws Exception {
        return 0;
    }

    @Override
    public int insertCalOwner(SchRqDto007 schRqDto007) throws Exception {
        return 0;
    }

    @Override
    public int insertCalManager(List<SchRqDto007.Manager> managers) throws Exception {
        return 0;
    }
}
