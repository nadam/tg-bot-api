package se.anyro.tgbotapi.types.reply_markup;

import se.anyro.tgbotapi.types.ChatAdministratorRights;

public class KeyboardButtonRequestChat {
    public int request_id;
    public boolean chat_is_channel;
    public Boolean chat_is_forum;
    public Boolean chat_has_username;
    public Boolean chat_is_created;
    public ChatAdministratorRights user_administrator_rights;
    public ChatAdministratorRights bot_administrator_rights;
    public Boolean bot_is_member;
}
