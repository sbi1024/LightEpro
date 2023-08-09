package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.response.SchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class EmpController000 {
    @RequestMapping(value = "/emp000", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse emp000(@RequestBody @Valid EmpRqDto000 empRqDto000) throws Exception {

        return null;
    }
}
