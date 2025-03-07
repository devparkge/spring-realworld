package github.devparkge.realworld.infrastructure.article.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity(name = "article_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleTagEntity {
    @Id
    @Column(name = "article_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer databaseId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "articleId", nullable = false)
    private ArticleEntity articleEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tagId", nullable = false)
    private TagEntity tagEntity;
    private Instant createdAt = Instant.now();
    private Instant updatedAt;

}
