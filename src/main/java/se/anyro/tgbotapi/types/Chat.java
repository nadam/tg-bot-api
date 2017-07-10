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
        return Type.PRIVATE.toString().equals(type);
    }

    public boolean isGroupOrSupergroup() {
        Type type = getType();
        return type == Type.GROUP || type == Type.SUPERGROUP;
    }
}