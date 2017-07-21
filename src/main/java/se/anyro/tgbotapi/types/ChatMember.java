package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#chatmember">Official documentation of ChatMember</a>
 */
public class ChatMember {
    public User user;
    public String status;

    public int until_date; // Restricted and kicked only

    // Administrators only
    public boolean can_be_edited;
    public boolean can_change_info;
    public boolean can_post_messages;
    public boolean can_edit_messages;
    public boolean can_delete_messages;
    public boolean can_invite_users;
    public boolean can_restrict_members;
    public boolean can_pin_messages;
    public boolean can_promote_members;

    // Restricted only
    public boolean can_send_messages;
    public boolean can_send_media_messages;
    public boolean can_send_other_messages;
    public boolean can_add_web_page_previews;

    public enum Status {
        CREATOR, ADMINISTRATOR, MEMBER, RESTRICTED, LEFT, KICKED
    }

    /**
     * Get the status field as an enum value.
     */
    public Status getStatus() {
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isOwner() {
        return getStatus() == Status.CREATOR;
    }
}
