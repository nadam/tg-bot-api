package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#user">User</a>
 */
public class User {
    public long id;
    public boolean is_bot;
    public String first_name;
    public String last_name;
    public String username;
    public String language_code;
    public boolean can_join_groups;
    public boolean can_read_all_group_messages;
    public boolean supports_inline_queries;
    public boolean is_premium;
    public boolean added_to_attachment_menu;

    public String getUrl() {
        return "tg://user?id=" + id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id).append(' ');
        builder.append(first_name);
        if (last_name != null) {
            builder.append(' ').append(last_name);
        }
        if (username != null) {
            builder.append(" @").append(username);
        }
        return builder.toString();
    }
}
