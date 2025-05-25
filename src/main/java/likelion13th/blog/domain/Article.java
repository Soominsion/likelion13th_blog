package likelion13th.blog.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*

package likelion13th.blog.domain;

import java.time.LocalDateTime;

public class Article {

    private Long id; //고유 번호
    private String title; //제목
    private String content; //게시글
    private String author; //작성자
    private String password; //비밀번호
    private LocalDateTime createdAt; //생성일

    public Article(String content, Long id, String title, String author,String password) {
        this.content = content;
        this.id = id;
        this.title = title;
        this.author = author;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {return id; }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {return password;}
}
*/
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id // id 필드를 기본키(Primary Key)로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String author;

    public Article(String title, String content, String author,  String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}