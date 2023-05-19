package com.example.vwx.users.service;

import com.example.vwx.filtering.dto.FilterDto;
import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.dto.ChannelPortfolioDto;
import com.example.vwx.portfolio.repository.PortfolioRepository;
import com.example.vwx.users.dto.ChannelDto;
import com.example.vwx.users.dto.MyPageDto;
import com.example.vwx.users.dto.SearchArtistDto;
import com.example.vwx.users.repository.UsersRepository;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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


//    public List<SearchArtistDto> searchArtist(String word, List<String> keywords) throws BaseException {
//
//        List<SearchArtistDto> artistList = usersRepository.findSearchByKeyword(word);
//
//        List<SearchArtistDto> artistAllList = new ArrayList<>();
//        for (SearchArtistDto artist : artistList) {
//            SearchArtistDto dto = SearchArtistDto.builder()
//                    .userName(artist.getUserName())
//                    .profileImageUrl(artist.getProfileImageUrl())
//                    .job(artist.getJob())
//                    .belong(artist.getBelong())
//                    .build();
//            artistAllList.add(dto);
//        }
//        return artistAllList;
//    }
}
