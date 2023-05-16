package com.example.vwx.portfolio.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "workCategory_id")
    private Long id;
    private String category;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Builder
    public WorkCategory(String category, Portfolio portfolio) {
        this.category = category;
        this.portfolio = portfolio;
    }
}
