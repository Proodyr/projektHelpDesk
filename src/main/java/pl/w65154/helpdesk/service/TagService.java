package pl.w65154.helpdesk.service;

import pl.w65154.helpdesk.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    ArticleRepository articleRepository;
}
