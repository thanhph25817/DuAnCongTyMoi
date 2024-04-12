package com.example.demo.config;

import com.example.demo.constant.Constants;
import com.example.demo.util.GetterUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class Labels {

    public interface Country {
        public static final String US = "en";
        public static final String VN = "vn";
    }

    public interface Language {
        public static final String EN = "en";
        public static final String VN = "vn";
    }

    private static MessageSource messageSource;

    public static final Locale VN = new Locale(Language.VN, Country.VN);
    public static final Locale EN = new Locale(Language.EN, Country.US);

    public static String getLabels(String key) {
        return getLabels1(key, null, getRequestLocale());
    }

    public static String getLabels1(String key, Object[] objs, Locale locale) {
        String ms = null;
        try {
            if (locale == null) {
                locale = getDefaultLocale();
            }
            ms = messageSource.getMessage(key, objs, locale);
        } catch (NoSuchMessageException ex) {
            log.error("Can not get label for key " + key + " , return default value.", ex);
        }
        return ms;

    }

    public static Locale getDefaultLocale() {
        // do not synchronize this method - see 4071298
        return VN;
    }

    public static Locale getRequestLocale() {
        String language = getLanguageFromRequest();

        switch (language) {
            case Language.VN:
                return VN;
            case Language.EN:
                return EN;
            default:
                return VN;
        }
    }

    public static String getLanguageFromRequest(){
    HttpServletRequest request=null;
    RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();

    if(requestAttributes instanceof ServletRequestAttributes){
        request = ((ServletRequestAttributes) requestAttributes).getRequest();
    }
        if (request == null) {
            return Language.VN;
        }

        return GetterUtil.getString(request.getHeader(Constants.DEFAULT_LOCALE), Language.VN);
    }

}
