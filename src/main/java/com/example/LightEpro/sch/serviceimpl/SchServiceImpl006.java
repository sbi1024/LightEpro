package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.dto.sch006.SchRsDto006;
import com.example.LightEpro.sch.mapper.SchMapper005;
import com.example.LightEpro.sch.mapper.SchMapper006;
import com.example.LightEpro.sch.service.SchService006;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl006 implements SchService006 {
    private final SchMapper006 schMapper006;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto006 findSingleCal(SchRqDto006 schRqDto006) throws Exception {
        log.info("findSingleCal Method Start !!!");
        log.info("findSingleCal Method Request Data : " + schRqDto006);

        SchRsDto006.Calendar calendar = findCalendar(schRqDto006);
        List<SchRsDto006.CalendarUser> calendarUsers = findCalendarUsers(schRqDto006);

        SchRsDto006 schRsDto006 = SchRsDto006.builder()
                .cal(calendar)
                .calUsers(calendarUsers)
                .build();

        log.info("findSingleCal Method Return Data : " + schRsDto006);
        log.info("findSingleCal Method End !!!");
        // return
        return schRsDto006;
    }

    @Override
    public SchRsDto006.Calendar findCalendar(SchRqDto006 schRqDto006) throws Exception {
        log.info("findCalendar Method Start !!!");
        log.info("findCalendar Method Request Data : " + schRqDto006);

        SchRsDto006.Calendar calendar = schMapper006.selectCalendar(schRqDto006);

        log.info("findCalendar Method Return Data : " + calendar);
        log.info("findCalendar Method End !!!");
        // return
        return calendar;
    }

    @Override
    public List<SchRsDto006.CalendarUser> findCalendarUsers(SchRqDto006 schRqDto006) throws Exception {
        log.info("findCalendarUsers Method Start !!!");
        log.info("findCalendarUsers Method Request Data : " + schRqDto006);

        List<SchRsDto006.CalendarUser> calendarUsers = schMapper006.selectCalendarUsers(schRqDto006);

        log.info("findCalendarUsers Method Return Data : " + calendarUsers);
        log.info("findCalendarUsers Method End !!!");
        // return
        return calendarUsers;
    }
}
