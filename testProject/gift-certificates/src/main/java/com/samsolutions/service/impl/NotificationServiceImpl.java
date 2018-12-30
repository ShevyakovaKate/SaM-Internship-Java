package com.samsolutions.service.impl;

import com.samsolutions.service.NotificationService;
import com.samsolutions.service.pojo.NotificationMessage;
import com.samsolutions.service.pojo.NotificationMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addInfoMessage(String msgCode) {
        addNotificationMessage(NotificationMessageType.INFO, msgCode);
    }

    @Override
    public void addErrorMessage(String msgCode) {
        addNotificationMessage(NotificationMessageType.ERROR, msgCode);
    }

    private void addNotificationMessage(NotificationMessageType type, String msgCode) {
        List<NotificationMessage> notifyMessages;
        notifyMessages = (List<NotificationMessage>)
                httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        if (notifyMessages == null) {
            notifyMessages = new ArrayList<>();
        }
        //TODO GET FROM LOCALE
        notifyMessages.add(new NotificationMessage(type, msgCode));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }


}
