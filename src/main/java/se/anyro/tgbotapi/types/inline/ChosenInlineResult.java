package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.Location;
import se.anyro.tgbotapi.types.User;

/**
 * @see <a href="https://core.telegram.org/bots/api#choseninlineresult">Official documentation of ChosenInlineResult</a>
 */
public class ChosenInlineResult {
    public String result_id;
    public User from;
    public Location location;
    public String inline_message_id;
    public String query;
}
