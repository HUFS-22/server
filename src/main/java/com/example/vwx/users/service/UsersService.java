package com.example.vwx.users.service;

import com.example.vwx.users.dto.MyPageDto;
import com.example.vwx.users.repository.UsersRepository;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.vwx.common.domain.BaseResponseStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;

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
}
