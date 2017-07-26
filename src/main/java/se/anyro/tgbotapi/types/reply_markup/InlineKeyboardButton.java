package se.anyro.tgbotapi.types.reply_markup;

import se.anyro.tgbotapi.types.games.CallbackGame;


/**
 * Note! You must use exactly one of the optional fields.
 * 
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardbutton">Official documentation of
 *      InlineKeyboardButton</a>
 */
public class InlineKeyboardButton {

    public String text;
    public String url;
    public String callback_data;
    public String switch_inline_query;
    public String switch_inline_query_current_chat;
    public CallbackGame callback_game;
    public Boolean pay;

    public InlineKeyboardButton(String text) {
        this.text = text;
    }

    /*
     * Helper methods for creating specific button types
     */

    public static InlineKeyboardButton url(String text, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.url = url;
        return button;
    }

    public static InlineKeyboardButton callbackData(String text, String data) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.callback_data = data;
        return button;
    }

    public static InlineKeyboardButton switchInlineQuery(String text, String query) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.switch_inline_query = query;
        return button;
    }

    public static InlineKeyboardButton switchInlineQueryCurrent(String text, String query) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.switch_inline_query_current_chat = query;
        return button;
    }

    public static InlineKeyboardButton callbackGame(String text) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.callback_game = new CallbackGame();
        return button;
    }

    public static InlineKeyboardButton pay(String text) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.pay = true;
        return button;
    }
}