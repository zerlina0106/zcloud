package cn.yyy.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
public class BaseService {
    protected final String CODE_SUCCESS = "1000";
    protected final String CODE_FAIL = "-1";
    protected final String USER_ID = "userId";
    protected final String USER_NAME = "userName";

}
