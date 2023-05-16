package com.example.vwx.users.controller;

import com.example.vwx.users.dto.MyPageDto;
import com.example.vwx.users.service.UsersService;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.users.dto.JoinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "마이페이지", notes = "메인화면에서 사람 모양 클릭할때 이동되는 페이지")
    @GetMapping("/mypage/{user-id}")
    public BaseResponse<MyPageDto> mypage(@ApiParam(value = "유저 ID", example = "1")
                                              @PathVariable("user-id") Long userId) {
        try {
            MyPageDto result = usersService.mypage(userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
