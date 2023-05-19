package com.example.vwx.filtering.service;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.filtering.domain.Filtering;
import com.example.vwx.filtering.domain.FilterMapping;
import com.example.vwx.users.domain.Users;
import com.example.vwx.filtering.repository.MappingRepository;
import com.example.vwx.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.vwx.common.domain.BaseResponseStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
@Transactional
public class MappingService {

    private final FilteringService filteringService;
    private final MappingRepository mappingRepository;
    private final UsersRepository usersRepository;

    public String save(List<String> keywords, Long userId) throws BaseException {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new BaseException(UNAUTHORIZED));
        if(keywords.size() == 0) return "등록할 키워드가 없습니다.";
        List<FilterMapping> isMapping = mappingRepository.findFilterMappingsByUsers(users);
        if (isMapping.size() != 0) {
            String result = update(keywords, users);
            return result;
        }
        keywords.stream()
                .map(keyword ->
                        filteringService.findByKeyword(keyword)
                                .orElseGet(() -> filteringService.save(keyword)))
                .forEach(keyword -> mapKeywordToUser(users, keyword));
        return "아티스트 필터링 키워드를 등록했습니다.";
    }

    private Long mapKeywordToUser(Users user, Filtering keyword){
        FilterMapping filterMapping = new FilterMapping(user, keyword);
        return mappingRepository.save(filterMapping).getId();
    }

    public List<String> findKeywordList(Long userId) throws BaseException {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new BaseException(UNAUTHORIZED));
        List<String> keywords = mappingRepository.findAllByUsers(user); // 키워드 아이디 리스트 가지고 오기
        return keywords;
    }

    @Transactional
    public String update(List<String> keywords, Users users) throws BaseException {
        mappingRepository.deleteAllByUsers(users);
        keywords.stream()
                .map(keyword ->
                        filteringService.findByKeyword(keyword)
                                .orElseGet(() -> filteringService.save(keyword)))
                .forEach(keyword -> mapKeywordToUser(users, keyword)); // 다시 등록
        return "아티스트 필터링 키워드를 등록했습니다.";

    }
}
