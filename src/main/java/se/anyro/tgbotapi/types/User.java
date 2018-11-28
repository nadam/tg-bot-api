package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#user">User</a>
 */
public class User {
    public int id;
    public boolean is_bot;
    public String first_name;
    public String last_name;
    public String username;
    public String language_code;

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