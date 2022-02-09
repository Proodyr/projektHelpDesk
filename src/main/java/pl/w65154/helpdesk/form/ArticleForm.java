package pl.w65154.helpdesk.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleForm {
    @NotNull
    String title;

    @NotNull
    String content;

    //TODO: tags
}
