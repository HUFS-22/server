package com.example.vwx.users.service;

import com.example.vwx.users.repository.UsersRepository;
import com.example.vwx.common.domain.BaseException;
import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;

    public void join(JoinDto joinDto) throws BaseException {
        usersRepository.save(Users.ofUser(joinDto));
    }
}
