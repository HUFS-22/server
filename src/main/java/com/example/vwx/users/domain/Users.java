package com.example.vwx.users.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import com.example.vwx.users.dto.JoinDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String userName;
    private String loginId;
    private String password;
    private String coverImageUrl;
    private String profileImageUrl;
    private String belong;
    private String job;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mapping> mappings ;


    public static Users ofUser(JoinDto joinDto) {
        Users member = Users.builder()
                .userName(joinDto.getUserName())
                .loginId(joinDto.getLoginId())
                .password(joinDto.getPassword())
                .coverImageUrl(joinDto.getCoverImageUrl())
                .profileImageUrl(joinDto.getProfileImageUrl())
                .belong(joinDto.getBelong())
                .job(joinDto.getJob())
                .build();
        return member;
    }

}
