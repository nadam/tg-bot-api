package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#chatmember">Official documentation of ChatMember</a>
 */
public class ChatMember {
    public User user;
    public String status;

    public enum Status {
        CREATOR, ADMINISTRATOR, MEMBER, LEFT, KICKED
    }

    /**
     * Get the status field as an enum value.
     */
    public Status getStatus() {
        return Status.valueOf(status.toUpperCase());
    }
}
