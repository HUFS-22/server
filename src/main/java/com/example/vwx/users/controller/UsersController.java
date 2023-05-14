package com.example.vwx.users.controller;

import com.example.vwx.users.service.UsersService;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.users.dto.JoinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저 API")
@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @ApiOperation(value = "회원가입", notes = "회원 더미데이터 만들기 위한 회원가입 API")
    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody JoinDto joinDto) {
        try {
            usersService.join(joinDto);
            return new BaseResponse<>("회원 가입되었습니다.");
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
