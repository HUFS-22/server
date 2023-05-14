package com.example.vwx.users.controller;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.users.dto.FilterRequestDto;
import com.example.vwx.users.service.FilteringService;
import com.example.vwx.users.service.MappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "아티스트 필터링 API")
@RestController
@RequiredArgsConstructor
public class FilteringController {

    private final FilteringService filteringService;
    private final MappingService mappingService;


    @ApiOperation(value = "아티스트 필터링 키워드 전송", notes = "아티스트에 대한 선택된 필터링 키워드를 전송한다. ")
    @ApiResponse(code = 3000, message = "회원가입되지 않은 유저입니다.")
    @PostMapping("/artist-keyword/{user-id}")
    public BaseResponse<String> sendKeyword(@RequestBody FilterRequestDto filterRequestDto,
                                            @PathVariable("user-id") Long userId) {
        try {
            String result = mappingService.save(filterRequestDto.getKeywords(), userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
