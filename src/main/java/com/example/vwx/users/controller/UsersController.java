package com.example.vwx.users.controller;

import com.example.vwx.filtering.dto.FilterDto;
import com.example.vwx.users.dto.ChannelDto;
import com.example.vwx.users.dto.MyPageDto;
import com.example.vwx.users.dto.SearchArtistDto;
import com.example.vwx.users.service.UsersService;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.users.dto.JoinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "마이페이지", notes = "메인화면에서 사람 모양 클릭할 때 이동되는 페이지")
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

    @ApiOperation(value = "채널", notes = "마이페이지의 '내 채널보기' 또는 아티스트 사진을 클릭했을 때 나오는 페이지")
    @GetMapping("/channel/{user-id}")
    public BaseResponse<ChannelDto> channel(@ApiParam(value = "유저 ID", example = "1")
                                                 @PathVariable("user-id") Long userId) {
        try {
            ChannelDto result = usersService.channel(userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ApiOperation(value = "아티스트 검색", notes = "발견에서 검색창에 통합 검색하고 나오는 아티스트 페이지.")
    @PostMapping("/search/artist")
    public  BaseResponse<List<SearchArtistDto>> searchArtist(@ApiParam(value = "검색어") @RequestParam(name = "q") String word,
                                                       @ApiParam(value = "필터링 키워드") @RequestBody(required = false) FilterDto keywords) {
        try {
            List<SearchArtistDto> result = usersService.searchArtist(word, keywords.getKeywords());
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
