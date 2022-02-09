package pl.w65154.helpdesk.controller;

import pl.w65154.helpdesk.domain.Statistics;
import pl.w65154.helpdesk.entity.Article;
import pl.w65154.helpdesk.exception.ResourceNotFoundException;
import pl.w65154.helpdesk.service.ArticleService;
import pl.w65154.helpdesk.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.file.AccessDeniedException;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    ArticleService articleService;

    @Autowired
    StatisticsService statisticsService;

    @ModelAttribute("topUpdatedArticles")
    public List<Article> getTopUpdatedArticles() {
        return articleService.findTop3ByOrderByLastModifiedDateDesc();
    }

    @ModelAttribute("statistics")
    public Statistics getStatistics() {
        return statisticsService.getTicketStatistics(new Statistics());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "/error/404";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException() {
        return "/error/403";
    }

}
