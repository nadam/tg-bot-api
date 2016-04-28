package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#messageentity">Official documentation of MessageEntity</a>
 */
public class MessageEntity {
    public String type;
    public int offset;
    public int length;
    public String url;

    public enum Type {
        MENTION, HASHTAG, BOT_COMMAND, URL, EMAIL, BOLD, ITALIC, CODE, PRE, TEXT_LINK
    }

    /**
     * Get the type field as an enum value.
     */
    public Type getType() {
        return Type.valueOf(type.toUpperCase());
    }
}
