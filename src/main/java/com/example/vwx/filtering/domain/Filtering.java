package com.example.vwx.filtering.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Filtering extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;
    private String keyword;

    @OneToMany(mappedBy = "filtering", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FilterMapping> filterMapping;

    @Builder
    public Filtering(String keyword) {
        this.keyword = keyword;
    }


}
