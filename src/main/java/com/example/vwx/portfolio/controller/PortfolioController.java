package com.example.vwx.portfolio.controller;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.common.domain.BaseResponse;
import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.dto.PortfolioDto;
import com.example.vwx.portfolio.service.PortfolioService;
import com.example.vwx.portfolio.service.TagMappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "포트폴리오 API")
@RestController
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @ApiOperation(value = "포트폴리오 등록", notes = "포트폴리오를 등록한다.")
    @ApiResponse(code = 3000, message = "회원가입되지 않은 유저입니다.")
    @PostMapping("/portfolio/{user-id}")
    public BaseResponse<String> uploadPortfolio(@ApiParam(value = "포트폴리오 DTO") @RequestBody PortfolioDto portfolioDto,
                                                @ApiParam(value = "유저 ID", required = true, example = "1")
                                                @PathVariable("user-id") Long userId) {
        try {
            String result = portfolioService.uploadPortfolio(portfolioDto, userId);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ApiOperation(value = "포트폴리오 조회", notes = "포트폴리오 상세 내용을 조회한다.")
    @ApiResponse(code = 3020, message = "해당 포트폴리오 아이디에 대한 포트폴리오가 없습니다.")
    @GetMapping("/portfolio/{portfolio-id}")
    public BaseResponse<PortfolioDto> getPortfolio(@ApiParam(value = "포트폴리오 ID", required = true, example = "2")
                                                       @PathVariable("portfolio-id") Long portfolio) {
        try {
            PortfolioDto result = portfolioService.getPortfolio(portfolio);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
