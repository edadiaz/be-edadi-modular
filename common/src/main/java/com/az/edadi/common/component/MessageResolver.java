package com.az.edadi.common.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.Locale;


@RequiredArgsConstructor
public class MessageResolver {

    private final MessageSource messageSource;

    public String resolve(String messageCode, Object[] arg, Locale locale) {
        if (messageCode == null) {
            return null;
        }
        return messageSource.getMessage(messageCode, arg, locale);
    }

    public String resolve(String messageCode, Object[] arg) {
        return resolve(messageCode, arg, LocaleContextHolder.getLocale());
    }

    public String resolve(String messageCode) {
        return resolve(messageCode, null);
    }
}
