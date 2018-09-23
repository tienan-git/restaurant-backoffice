package jp.co.sparkworks.restaurant.backoffice.controller.api.param;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

import jp.co.sparkworks.restaurant.backoffice.controller.constants.ResultCodeConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BaseRes {
    private String code;
    private String message;
 

    public static BaseRes SUCCESS = newInstance(ResultCodeConstants.I000);

    private static ResourceBundleMessageSource messageSource;

    public static BaseRes newInstance(String code,  String ...param) {

        return new BaseRes(code, getMessage(code, param));
    }

    protected BaseRes() {

    }

    public static String getMessage(String code, String... param) {
        if (messageSource == null) {
            messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
        }
        log.debug("messageSource:{}", messageSource);
        String message = messageSource.getMessage(code, param , Locale.JAPAN);
        return message;
    }

    private BaseRes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
