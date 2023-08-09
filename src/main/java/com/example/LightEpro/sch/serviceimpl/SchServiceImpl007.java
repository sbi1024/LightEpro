package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;
import com.example.LightEpro.sch.mapper.SchMapper007;
import com.example.LightEpro.sch.service.SchService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl007 implements SchService007 {
    private final SchMapper007 schMapper007;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto007 modifySingleCal(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifySingleCal Method Start !!!");
        log.info("modifySingleCal Method Request Data : " + schRqDto007);

        // 캘린더 시퀀스 추출
        int calSeq = schRqDto007.getCalendar().getCalSeq();

        // 캘린더 구성원 일치 / 비일치를 확인한다
        checkCalUsers(schRqDto007);
        // 캘린더 정보를 수정 한다
        int modifyCalDetailInfoCnt = modifyCalDetailInfo(schRqDto007);
        // 캘린더 소유자 정보를 수정 한다
        int modifyCalOwnerCnt = modifyCalOwner(schRqDto007);
        // 캘린더 관리자 정보를 삭제 한다
        int removeCalManagersCnt = removeCalManagers(schRqDto007);
        // 캘린더 관리자 정보를 수정 한다
        int modifyCalManagersCnt = modifyCalManagers(schRqDto007);
        // 캘린더 관리자 정보를 등록 한다
        int createCalManagersCnt = createCalManagers(schRqDto007);

        // schRsDto007 객체 builder 패턴을 통해 객체 생성
        SchRsDto007 schRsDto007 = SchRsDto007.builder()
                .calSeq(calSeq)
                .modifyCalDetailInfoCnt(modifyCalDetailInfoCnt)
                .modifyCalOwnerCnt(modifyCalOwnerCnt)
                .removeCalManagersCnt(removeCalManagersCnt)
                .modifyCalManagersCnt(modifyCalManagersCnt)
                .createCalManagersCnt(createCalManagersCnt)
                .build();

        // method end log
        log.info("modifySingleCal Method Return Data : " + schRsDto007);
        log.info("modifySingleCal Method End !!!");

        // return
        return schRsDto007;
    }

    @Override
    public void checkCalUsers(SchRqDto007 schRqDto007) throws Exception {
        log.info("checkCalUsers Method Start !!!");
        log.info("checkCalUsers Method Request Data : " + schRqDto007);

        // 기존에 존재하는 캘린더 구성원 데이터 (소유자 정보 제외)
        List<SchRqDto007.CalendarUser> calendarUsers = schMapper007.findCalUsersInfoByCalSeq(schRqDto007);
        List<SchRqDto007.Manager> managers = schRqDto007.getManagers();

        // 요청값의 캘린더 관리자 정보가 null 이거나 size 0 인경우 메소드 return
        if (managers == null || managers.size() == 0) {
            return;
        }

        // 일치하는 캘린더 구성원 추출 (기존 캘린더 구성원 <-> 변경하고자 하는 캘린더 구성원)
        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = checkOriginMatchCalUsers(calendarUsers, managers);
        // 비 일치하는 캘린더 구성원 추출 (기존 캘린더 구성원 <-> 변경하고자 하는 캘린더 구성원)
        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = checkOriginNonMatchCalUsers(calendarUsers, managers);
        // 비 일치하는 캘린더 구성원 추출 (변경하고자 하는 캘린더 구성원 <-> 기존 캘린더 구성원)
        List<SchRqDto007.Manager> newNonMatchCalendarUsers = checkNewNonMatchCalUsers(calendarUsers, managers);

        // 요청값에 일치 / 비일치 캘린더 구성원 재 할당
        schRqDto007.setOriginMatchCalendarUsers(originMatchCalendarUsers);
        schRqDto007.setOriginNonMatchCalendarUsers(originNonMatchCalendarUsers);
        schRqDto007.setNewNonMatchCalendarUsers(newNonMatchCalendarUsers);

        // method end log
        log.info("checkCalUsers Method End !!!");
    }

    // 수정 처리 (update)
    @Override
    public List<SchRqDto007.CalendarUser> checkOriginMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                                   List<SchRqDto007.Manager> managers) throws Exception {
        // method start log
        log.info("checkOriginMatchCalUsers Method Start !!!");
        log.info("checkOriginMatchCalUsers Method calendarUsers : " + calendarUsers + " managers : " + managers);

        // stream을 통해 일치 데이터 확인
        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = calendarUsers.stream()
                .filter(calUser ->
                        managers.stream().anyMatch(
                                manager -> (
                                        (String.valueOf(calUser.getCdeSeq()).equals(String.valueOf(manager.getCdeSeq())))
                                                && (String.valueOf(calUser.getCdeType()).equals(String.valueOf(manager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("checkOriginMatchCalUsers Method Result Data : " + originMatchCalendarUsers);
        log.info("checkOriginMatchCalUsers Method End !!!");

        return originMatchCalendarUsers;
    }

    // 삭제 처리 (delete)
    @Override
    public List<SchRqDto007.CalendarUser> checkOriginNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                                      List<SchRqDto007.Manager> managers) throws Exception {
        // method start log
        log.info("checkOriginNonMatchCalUsers Method Start !!!");
        log.info("checkOriginNonMatchCalUsers Method calendarUsers : " + calendarUsers + " managers : " + managers);

        // stream을 통해 비 일치 데이터 확인
        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = calendarUsers.stream()
                .filter(calUser ->
                        managers.stream().noneMatch(
                                manager -> (
                                        (String.valueOf(calUser.getCdeSeq()).equals(String.valueOf(manager.getCdeSeq())))
                                                && (String.valueOf(calUser.getCdeType()).equals(String.valueOf(manager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("checkNewNonMatchCalUsers Method Result Data : " + originNonMatchCalendarUsers);
        log.info("checkNewNonMatchCalUsers Method End !!!");

        // return
        return originNonMatchCalendarUsers;
    }

    // 등록 처리 (insert)
    @Override
    public List<SchRqDto007.Manager> checkNewNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                              List<SchRqDto007.Manager> managers) throws Exception {
        // method start log
        log.info("checkNewNonMatchCalUsers Method Start !!!");
        log.info("checkNewNonMatchCalUsers Method calendarUsers : " + calendarUsers + " managers : " + managers);

        // stream을 통해 비 일치 데이터 확인
        List<SchRqDto007.Manager> newNonMatchCalendarUsers = managers.stream()
                .filter(manager ->
                        calendarUsers.stream().noneMatch(
                                calUser -> (
                                        (String.valueOf(manager.getCdeSeq()).equals(String.valueOf(calUser.getCdeSeq())))
                                                && (String.valueOf(manager.getCdeType()).equals(String.valueOf(calUser.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("checkNewNonMatchCalUsers Method Result Data : " + newNonMatchCalendarUsers);
        log.info("checkNewNonMatchCalUsers Method End !!!");

        // return
        return newNonMatchCalendarUsers;
    }

    @Override
    public int modifyCalDetailInfo(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyCalDetailInfo Method Start !!!");
        log.info("modifyCalDetailInfo Method Request Data : " + schRqDto007);

        int updateCalDetailInfoCnt = schMapper007.updateCalDetailInfo(schRqDto007);

        // method end log
        log.info("modifyCalDetailInfo Method Start !!!");
        log.info("modifyCalDetailInfo Method Request Data : " + updateCalDetailInfoCnt);

        // return
        return updateCalDetailInfoCnt;
    }

    @Override
    public int modifyCalOwner(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + schRqDto007);

        int updateCalOwnerCnt = schMapper007.updateCalOwner(schRqDto007);

        // method end log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + updateCalOwnerCnt);

        // return
        return updateCalOwnerCnt;
    }

    @Override
    public int removeCalManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = schRqDto007.getOriginNonMatchCalendarUsers();
        if (originNonMatchCalendarUsers == null || originNonMatchCalendarUsers.size() == 0) {
            return 0;
        }

        int updateManagersCnt = schMapper007.updateNonMatchManagers(schRqDto007);

        // method end log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + updateManagersCnt);

        // return
        return updateManagersCnt;
    }

    @Override
    public int modifyCalManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = schRqDto007.getOriginMatchCalendarUsers();
        if (originMatchCalendarUsers == null || originMatchCalendarUsers.size() == 0) {
            return 0;
        }

        int updateManagersCnt = schMapper007.updateOriginMatchManagers(schRqDto007);

        // method end log
        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + updateManagersCnt);

        // return
        return updateManagersCnt;
    }

    @Override
    public int createCalManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.Manager> newNonMatchCalendarUsers = schRqDto007.getNewNonMatchCalendarUsers();
        if (newNonMatchCalendarUsers == null || newNonMatchCalendarUsers.size() == 0) {
            return 0;
        }

        int insertManagersCnt = schMapper007.insertNewNonMatchManagers(schRqDto007);

        // method end log
        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + insertManagersCnt);

        // return
        return insertManagersCnt;
    }
}
