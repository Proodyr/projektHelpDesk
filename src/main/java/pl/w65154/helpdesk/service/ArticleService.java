package pl.w65154.helpdesk.service;

import pl.w65154.helpdesk.entity.Article;
import pl.w65154.helpdesk.entity.User;
import pl.w65154.helpdesk.form.ArticleForm;
import pl.w65154.helpdesk.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }

    public Page<Article> findAllByOrderByCreationDateDesc(Pageable pageable) {
        return articleRepository.findAllByOrderByCreationDateDesc(pageable);
    }

    public List<Article> findTop5ByOrderByCreationDateDesc() {
        return articleRepository.findTop5ByOrderByCreationDateDesc();
    }

    public List<Article> findTop3ByOrderByLastModifiedDateDesc() {
        return articleRepository.findTop3ByOrderByLastModifiedDateDesc();
    }

    public Article saveNewArticle(ArticleForm formData, User creator) {
        LocalDateTime now = LocalDateTime.now();

        Article article = new Article();
        article.setLastModifiedBy(creator);
        article.setCreationDate(now);
        article.setLastModifiedDate(now);
        article.setTitle(formData.getTitle());
        article.setContent(formData.getContent());
        //3TODO: tags
        articleRepository.save(article);

        return article;
    }

    public void delete(Article entity) {
        articleRepository.delete(entity);
    }

    public Page<Article> searchByTitle(String text, Pageable pageable) {
        return articleRepository.findArticlesByTitleContainingIgnoreCase(text, pageable);
    }

    public Article saveModifiedArticle(ArticleForm formData, Article entity) {
        LocalDateTime now = LocalDateTime.now();

        entity.setLastModifiedDate(now);
        entity.setContent(formData.getContent());
        entity.setTitle(formData.getTitle());
        //TODO: tags
        articleRepository.save(entity);

        return entity;
    }
}
