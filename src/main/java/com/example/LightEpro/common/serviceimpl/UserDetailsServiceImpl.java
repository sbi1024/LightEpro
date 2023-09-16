package com.example.LightEpro.common.serviceimpl;

import com.example.LightEpro.common.dto.UserDetailsDto;
import com.example.LightEpro.common.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailMapper userDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailsDto userDetailsDto = userDetailMapper.selectUser(username);

        return User.builder()
                .username(userDetailsDto.getId())
                .password(userDetailsDto.getPw())
                .roles("USER")
                .build();
    }
}
