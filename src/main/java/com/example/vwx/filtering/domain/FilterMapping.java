package com.example.vwx.filtering.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import com.example.vwx.users.domain.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilterMapping extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "filterMapping_id")
    private Long id;

    @ManyToOne
    private Users users;
    
    @ManyToOne
    private Filtering filtering;

    @Builder
    public FilterMapping(Users users, Filtering filtering) {
        this.users = users;
        this.filtering = filtering;
    }
}
