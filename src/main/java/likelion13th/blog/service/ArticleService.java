
package likelion13th.blog.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import likelion13th.blog.domain.Article;
import likelion13th.blog.dto.request.AddArticleRequest;
import likelion13th.blog.dto.request.DeleteRequest;
import likelion13th.blog.dto.request.UpdateArticleRequest;
import likelion13th.blog.dto.response.ArticleDetailResponse;
import likelion13th.blog.dto.response.ArticleResponse;
import likelion13th.blog.dto.response.CommentResponse;
import likelion13th.blog.dto.response.SimpleArticleResponse;
import likelion13th.blog.exception.ArticleNotFoundException;
import likelion13th.blog.exception.PermissionDeniedException;
import likelion13th.blog.repository.ArticleRepository;
import likelion13th.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleResponse addArticle(AddArticleRequest request){
        /*1. Article 객체 생성*/
        Article article=request.toEntity();

        /*2. 레포지토리에 저장*/
        article=articleRepository.save(article);

        /*3. ArticleResponse DTO 생성하여 반환*/
        return ArticleResponse.of(article);
    }

    public List<SimpleArticleResponse> getAllArticles(){
        /*1. JPA의 findAll() 을 사용하여 DB에 저장된 전체 Article을 List 형태로 가져오기*/
        List<Article> articleList = articleRepository.findAll();

        /*2. Article -> SimpleArticleResponse : 엔티티를 DTO로 변환*/
        List<SimpleArticleResponse> articleResponseList = articleList.stream()
                .map(article -> SimpleArticleResponse.of(article))
                .toList();

        /*3. articleResponseList (DTO 리스트) 반환 */
        return articleResponseList;

        }
    //단일 글 조회
    public ArticleDetailResponse getArticle(Long id){
        /* 1. 요청이 들어온 게시글 ID로 데이터베이스에서 게시글 찾기. 해당하는 게시글이 없으면 에러*/
        Article article=articleRepository.findById(id)
                .orElseThrow(()->new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        /*2. 해당 게시글에 달려있는 댓글들 가져오기*/
        List<CommentResponse> comments=getCommentList(article);

        /*3. ArticleResponse DTO 생성하여 반환 */
        return ArticleDetailResponse.of(article,comments);
    }

    //특정 게시글에 달려있는 댓글목록 가져오기
    private List<CommentResponse> getCommentList(Article article){
        return commentRepository.findByArticle(article).stream()
                .map(comment->CommentResponse.of(comment))
                .toList();
    }
    //글 수정
    @Transactional
    public ArticleResponse updateArticle(Long id, UpdateArticleRequest request)  {

        Article article=articleRepository.findById(id)
                .orElseThrow(()->new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        if(!article.getPassword().equals(request.getPassword())){
//        throw new RuntimeException("해당 글에 대한 수정 권한이 없습니다.");
            throw new PermissionDeniedException("해당 글에 대한 수정 권한이 없습니다.");
        }

        article.update(request.getTitle(),request.getContent());

        return ArticleResponse.of(article);

    }
    //글 삭제
    @Transactional
    public void deleteArticle(Long id, DeleteRequest request){
        /* 1. 요청이 들어온 게시글 ID로 데이터베이스에서 게시글 찾기. 해당하는 게시글이 없으면 에러*/
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        if(!request.getPassword().equals(article.getPassword())) {
//        throw new RuntimeException("해당 글에 대한 삭제 권한이 없습니다.");
            throw new PermissionDeniedException("해당 글에 대한 삭제 권한이 없습니다.");
        }
        articleRepository.deleteById(id);



    }


    }

