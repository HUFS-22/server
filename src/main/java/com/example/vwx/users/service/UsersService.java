package com.example.vwx.users.service;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.dto.ChannelPortfolioDto;
import com.example.vwx.portfolio.repository.PortfolioRepository;
import com.example.vwx.users.dto.ChannelDto;
import com.example.vwx.users.dto.MyPageDto;
import com.example.vwx.users.repository.UsersRepository;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.vwx.common.domain.BaseResponseStatus.NOT_FOUND_PORTFOLIO;
import static com.example.vwx.common.domain.BaseResponseStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final PortfolioRepository portfolioRepository;

    public void join(JoinDto joinDto) throws BaseException {
        usersRepository.save(Users.ofUser(joinDto));
    }

    public MyPageDto mypage(Long userId) throws BaseException {
        Users users = usersRepository.findById(userId).orElseThrow(()-> new BaseException(UNAUTHORIZED));
        return MyPageDto.builder()
                .userName(users.getUserName())
                .profileImageUrl(users.getProfileImageUrl())
                .build();
    }

    public ChannelDto channel(Long userId) throws BaseException {
        Users users = usersRepository.findById(userId).orElseThrow(()-> new BaseException(UNAUTHORIZED));
        List<Portfolio> portfolioList = portfolioRepository.findByUsers(users);
        return ChannelDto.builder()
                .userName(users.getUserName())
                .coverImageUrl(users.getCoverImageUrl())
                .profileImageUrl(users.getProfileImageUrl())
                .belong(users.getBelong())
                .job(users.getJob())
                .portfolioList(portfolioList.stream()
                        .map(portfolio -> ChannelPortfolioDto.builder()
                                .title(portfolio.getTitle())
                                .coverImageUrl(portfolio.getCoverImageUrl())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
