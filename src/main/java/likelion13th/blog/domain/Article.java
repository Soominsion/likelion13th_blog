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