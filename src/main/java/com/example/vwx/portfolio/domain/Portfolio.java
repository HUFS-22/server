package com.example.vwx.portfolio.domain;

import com.example.vwx.common.domain.BaseTimeEntity;
import com.example.vwx.users.domain.Users;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "portfolio_id")
    private Long id;
    private String title;
    private String content;
    private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagMapping> tagMappings;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkCategory> workCategories;

    @Builder
    public Portfolio(String title, String coverImageUrl, String content, Users users) {
        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.content = content;
        this.users = users;
    }

}
