package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#chatpermissions">Official documentation of ChatPermissions</a>
 */
public class ChatPermissions {
    public boolean can_send_messages;
    public boolean can_send_media_messages;
    public boolean can_send_polls;
    public boolean can_send_other_messages;
    public boolean can_add_web_page_previews;
    public boolean can_change_info;
    public boolean can_invite_users;
    public boolean can_pin_messages;
    public boolean can_manage_topics;
}
