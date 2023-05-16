package com.example.vwx.portfolio.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagMapping extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "TagMapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public TagMapping(Portfolio portfolio, Tag tag) {
        this.portfolio = portfolio;
        this.tag = tag;
    }
}
