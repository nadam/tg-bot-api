package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultgame">Official documentation of
 *      InlineQueryResultGame</a>
 */
public class InlineQueryResultGame extends InlineQueryResult {
    public String type = "game";
    public String id;
    public String game_short_name;
    public InlineKeyboardMarkup reply_markup;

    public InlineQueryResultGame(String id, String gameShortName) {
        this.id = id;
        this.game_short_name = gameShortName;
    }
}