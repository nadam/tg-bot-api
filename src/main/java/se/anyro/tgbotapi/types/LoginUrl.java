package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#loginurl">Official documentation of LoginUrl</a>
 */
public class LoginUrl {
    public String url;
    public String forward_text;
    public String bot_username;
    public boolean request_write_access;

    public LoginUrl(String url) {
        this.url = url;
    }
}
