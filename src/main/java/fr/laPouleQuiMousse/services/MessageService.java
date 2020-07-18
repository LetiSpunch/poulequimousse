package fr.laPouleQuiMousse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service("messageService")
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String get(String key) {
        return get(key, null);

    }

    public String get(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return "??" + key + "_" + locale + "??";
        }
    }
}
