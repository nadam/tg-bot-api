package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#chat">Chat</a>
 */
public class Chat {
    public long id;
    public String type;
    public String title;
    public String username;
    public String first_name;
    public String last_name;
    public boolean all_members_are_administrators;
    public ChatPhoto photo;
    public String description;
    public String invite_link;
    public Message pinned_message;
    public String sticker_set_name;
    public boolean can_set_sticker_set;

    public enum Type {
        PRIVATE, GROUP, SUPERGROUP, CHANNEL
    }

    /**
     * Get the type field as an enum value.
     */
    public Type getType() {
        try {
            return Type.valueOf(type.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isPrivate() {
        return Type.PRIVATE.toString().equals(type.toUpperCase());
    }

    public boolean isGroupOrSupergroup() {
        Type type = getType();
        return type == Type.GROUP || type == Type.SUPERGROUP;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type).append(' ');
        builder.append(title);
        if (first_name != null) {
            builder.append(' ').append(first_name);
        }
        if (last_name != null) {
            builder.append(' ').append(last_name);
        }
        if (username != null) {
            builder.append(" @").append(username);
        }
        return super.toString();
    }
}