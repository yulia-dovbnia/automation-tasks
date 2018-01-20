package twitter.properties;

public enum Keys {

    CONSUMER_KEY("Consumer.Key"),
    CONSUMER_SECRET("Consumer.Secret"),
    ACCESS_TOKEN("Access.Token"),
    ACCESS_TOKEN_SECRET("Access.TokenSecret"),
    PERSONAL_LOGIN("Personal.Login"),
    PERSONAL_PASSWORD("Personal.Password");

    private final String key;

    Keys(String key) {
        this.key = key;
    }

    public String getValue() {
        return key;
    }
}
