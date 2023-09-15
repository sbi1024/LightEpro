package com.example.LightEpro.common.mapper;

import com.example.LightEpro.common.dto.UserDetailsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailMapper {
    UserDetailsDto selectUser(String Id);
}
