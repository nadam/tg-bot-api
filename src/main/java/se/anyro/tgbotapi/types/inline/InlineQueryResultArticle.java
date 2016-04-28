package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultarticle">Official documentation of
 *      InlineQueryResultArticle</a>
 */
public class InlineQueryResultArticle extends InlineQueryResult {
    public String type = "article";
    public String id;
    public String title;
    public InputMessageContent input_message_content;
    public InlineKeyboardMarkup reply_markup;
    public String url;
    public boolean hide_url;
    public String description;
    public String thumb_url;
    public int thumb_width;
    public int thumb_height;

    public InlineQueryResultArticle(String id, String title, String messageText) {
        this(id, title, new InputTextMessageContent(messageText));
    }
    
    public InlineQueryResultArticle(String id, String title, InputMessageContent inputMessageContent) {
        this.id = id;
        this.title = title;
        this.input_message_content = inputMessageContent;
    }
}
