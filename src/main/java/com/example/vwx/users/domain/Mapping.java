package com.example.vwx.users.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mapping extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "mapping_id")
    private Long id;

    @ManyToOne
    private Users users;
    
    @ManyToOne
    private Filtering filtering;

    @Builder
    public Mapping(Users users, Filtering filtering) {
        this.users = users;
        this.filtering = filtering;
    }
}
