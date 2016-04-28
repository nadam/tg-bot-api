package se.anyro.tgbotapi.types;

import se.anyro.tgbotapi.types.inline.CallbackQuery;
import se.anyro.tgbotapi.types.inline.ChosenInlineResult;
import se.anyro.tgbotapi.types.inline.InlineQuery;

/**
 * @see <a href="https://core.telegram.org/bots/api#update">Update</a>
 */
public class Update {
    public int update_id;
    public Message message;
    public InlineQuery inline_query;
    public ChosenInlineResult chosen_inline_result;
    public CallbackQuery callback_query;

    public boolean isMessage() {
        return message != null;
    }

    public boolean isInlineQuery() {
        return inline_query != null;
    }

    public boolean isChosenInlineResult() {
        return chosen_inline_result != null;
    }

    public boolean isCallbackQuery() {
        return callback_query != null;
    }

    /**
     * Returns the user who sent the message. Might return null for messages in channels or new types of updates.
     */
    public User fromUser() {
        if (isMessage()) {
            return message.from;
        }
        if (isInlineQuery()) {
            return inline_query.from;
        }
        if (isChosenInlineResult()) {
            return chosen_inline_result.from;
        }
        if (isCallbackQuery()) {
            return callback_query.from;
        }
        return null;
    }

    /**
     * Returns the id of the user who sent the message. Might return 0 for messages in channels or new types of updates.
     */
    public int fromUserId() {
        User from = fromUser();
        if (from != null) {
            return from.id;
        }
        return 0;
    }
}