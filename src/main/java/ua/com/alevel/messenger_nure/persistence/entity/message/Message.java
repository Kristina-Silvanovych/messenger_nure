package ua.com.alevel.messenger_nure.persistence.entity.message;

import ua.com.alevel.messenger_nure.persistence.entity.BaseEntity;

public class Message extends BaseEntity {

    private String context;

    private String recipient;

    private String userLogin;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
