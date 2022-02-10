package pl.w65154.helpdesk.init;

import pl.w65154.helpdesk.entity.Article;
import pl.w65154.helpdesk.entity.User;
import pl.w65154.helpdesk.repository.ArticleRepository;
import pl.w65154.helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class ArticleInitializer implements DataLoader{

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void load() {
        if (articleRepository.count() == 0) {
            User user = userRepository.findByUsername("admin");

            //1
            Article article = new Article();
            article.setTitle("Jak naprawić problem z brakiem internetu");
            article.setContent("Jeżeli internet nie działa, spróbuj najpierw zrestartować router.");

            LocalDateTime time = LocalDateTime.now();
            article.setCreationDate(time);
            article.setLastModifiedDate(time);
            article.setLastModifiedBy(user);

            article.setTags(new HashSet<>());
            article.getTags().add("internet");
            article.getTags().add("restart");
            article.getTags().add("niedziala");
            article.getTags().add("bląd");

            articleRepository.save(article);

            //2
            article = new Article();
            article.setTitle("Wolna prędkość łącza");
            article.setContent("Jeżeli Twój internet wolno działa, spróbuj uruchomić ponownie router lub podpiąć się przewodowo.");
            article.setCreationDate(time);
            article.setLastModifiedDate(time);
            article.setLastModifiedBy(user);

            article.setTags(new HashSet<>());
            article.getTags().add("internet");
            article.getTags().add("wolny");
            article.getTags().add("restart");
            article.getTags().add("przewodowo");
            article.getTags().add("wifi");
            article.getTags().add("spowolniony");

            articleRepository.save(article);

            //3
            article = new Article();
            article.setTitle("Router nie świeci");
            article.setContent("Jeżeli router nie świeci, upewnij się że ma zasilanie, sprawdź czy inny zasilacz również nie działa, jeśli nie pomoże, wymień router");
            article.setCreationDate(time);
            article.setLastModifiedDate(time);
            article.setLastModifiedBy(user);

            article.setTags(new HashSet<>());
            article.getTags().add("Router");
            article.getTags().add("wyłączony");
            article.getTags().add("zasilanie");
            article.getTags().add("lampki");
            article.getTags().add("internet");
            article.getTags().add("zasilacz");

            articleRepository.save(article);
        }
    }
}
