package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.User;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequery">Official documentation of InlineQuery</a>
 */
public class InlineQuery {
    public String id;
    public User from;
    public String query;
    public String offset;
}
