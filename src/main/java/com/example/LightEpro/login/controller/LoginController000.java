package com.example.LightEpro.login.controller;

import com.example.LightEpro.login.dto.login000.LoginRqDto000;
import com.example.LightEpro.login.response.LoginResponse;
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
public class LoginController000 {
    // 로그인 API
    @RequestMapping(value = "/login000", method = {RequestMethod.GET, RequestMethod.POST})
    public LoginResponse login000(@RequestBody @Valid LoginRqDto000 loginRqDto000) throws Exception {


        return null;
    }
}
