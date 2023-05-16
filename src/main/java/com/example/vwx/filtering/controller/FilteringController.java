package com.example.vwx.filtering.controller;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.filtering.dto.FilterDto;
import com.example.vwx.filtering.service.FilteringService;
import com.example.vwx.filtering.service.MappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "아티스트 필터링 API")
@RestController
@RequiredArgsConstructor
public class FilteringController {

    private final MappingService mappingService;


    @ApiOperation(value = "아티스트 필터링 키워드 저장", notes = "아티스트에 대한 선택된 필터링 키워드를 저장한다.")
    @ApiResponse(code = 3000, message = "회원가입되지 않은 유저입니다.")
    @PostMapping("/artist-keyword/{user-id}")
    public BaseResponse<String> sendKeyword(@ApiParam(value = "필터링 키워드 리스트") @RequestBody FilterDto filterRequestDto,
                                            @ApiParam(value = "유저 ID", required = true, example = "1")
                                            @PathVariable("user-id") Long userId) {
        try {
            String result = mappingService.save(filterRequestDto.getKeywords(), userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ApiOperation(value = "아티스트 필터링 키워드 정보", notes = "아티스트에 대한 선택된 필터링 키워드 정보를 본다.")
    @ApiResponse(code = 3000, message = "회원가입되지 않은 유저입니다.")
    @GetMapping("/artist-keyword/{user-id}")
    public BaseResponse<List<String>> viewKeyword(@ApiParam(value = "유저 ID", required = true, example = "1")
                                                       @PathVariable("user-id") Long userId) {
        try {
            List<String> result = mappingService.findKeywordList(userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
