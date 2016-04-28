package se.anyro.tgbotapi.types;

/**
 * The Bot API supports basic formatting for messages. You can use bold and italic text, as well as inline links and
 * pre-formatted code in your bots' messages. Telegram clients will render them accordingly. You can use either
 * markdown-style or HTML-style formatting.
 * 
 * @see <a href="https://core.telegram.org/bots/api#formatting-options">Formatting options</a>
 */
public enum ParseMode {

    MARKDOWN("Markdown"),
    HTML("HTML");

    public final String VALUE;

    private ParseMode(String value) {
        VALUE = value;
    }
}
