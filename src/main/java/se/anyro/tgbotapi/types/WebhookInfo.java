package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#webhookinfo">WebhookInfo</a>
 */
public class WebhookInfo {
    public String url;
    public boolean has_custom_certificate;
    public int pending_update_count;
    public int last_error_date;
    public String last_error_message;
}