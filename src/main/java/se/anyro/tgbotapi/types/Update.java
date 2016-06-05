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
    public Message edited_message;
    public InlineQuery inline_query;
    public ChosenInlineResult chosen_inline_result;
    public CallbackQuery callback_query;

    public enum Type {
        MESSAGE, EDITED_MESSAGE, INLINE_QUERY, CHOSEN_INLINE_RESULT, CALLBACK_QUERY
    }

    public boolean isMessage() {
        return message != null;
    }

    public boolean isEditedMessage() {
        return edited_message != null;
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

    public Type getType() {
        if (isMessage()) {
            return Type.MESSAGE;
        } else if (isEditedMessage()) {
            return Type.EDITED_MESSAGE;
        } else if (isInlineQuery()) {
            return Type.INLINE_QUERY;
        } else if (isChosenInlineResult()) {
            return Type.CHOSEN_INLINE_RESULT;
        } else if (isCallbackQuery()) {
            return Type.CALLBACK_QUERY;
        }
        return null;
    }

    /**
     * Returns the user who sent the message. Might return null for messages in channels or new types of updates.
     */
    public User fromUser() {
        switch (getType()) {
        case MESSAGE:
            return message.from;
        case EDITED_MESSAGE:
            return edited_message.from;
        case INLINE_QUERY:
            return inline_query.from;
        case CHOSEN_INLINE_RESULT:
            return chosen_inline_result.from;
        case CALLBACK_QUERY:
            return callback_query.from;
        default:
            return null;
        }
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