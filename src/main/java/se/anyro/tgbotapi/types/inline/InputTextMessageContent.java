package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.ParseMode;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputtextmessagecontent">Official documentation of
 *      InputTextMessageContent</a>
 */
public class InputTextMessageContent extends InputMessageContent {
    public String message_text;
    public String parse_mode;
    public boolean disable_web_page_preview;

    public InputTextMessageContent(String messageText) {
        this.message_text = messageText;
    }

    public InputTextMessageContent(String messageText, ParseMode parseMode, boolean disableWebPagePreview) {
        this.message_text = messageText;
        if (parseMode != null) {
            this.parse_mode = parseMode.VALUE;
        }
        this.disable_web_page_preview = disableWebPagePreview;
    }
}
