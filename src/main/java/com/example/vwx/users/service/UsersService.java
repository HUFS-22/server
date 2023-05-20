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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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


    public List<SearchArtistDto> searchArtist(String word, List<String> keywords) throws BaseException {

        keywords.add(word);
        int len = keywords.size();

        HashMap<List<String>, Integer> map = new HashMap<>();
        List<SearchArtistDto> artistAllList = new ArrayList<>();
        for (String keyword : keywords) {
            List<List<String>> list = usersRepository.findSearchByKeyword(keyword);
            for (List<String> dto : list) {
                if (map.containsKey(dto)) {
                    map.put(dto, map.get(dto) + 1);
                } else {
                    map.put(dto, 1);
                }
            }
        }
        Iterator<Map.Entry<List<String>, Integer>> entry = map.entrySet().iterator();

        while (entry.hasNext()) {
            Map.Entry<List<String>, Integer> element = entry.next();
            if (element.getValue().equals(len)) {
                List<String> artist = element.getKey();
                SearchArtistDto dto = SearchArtistDto.builder()
                        .userId(Long.valueOf(artist.get(0)))
                        .userName(artist.get(1))
                        .belong(artist.get(2))
                        .job(artist.get(3))
                        .profileImageUrl(artist.get(4))
                        .build();
                artistAllList.add(dto);
            }
        }
        return artistAllList;
    }
}
