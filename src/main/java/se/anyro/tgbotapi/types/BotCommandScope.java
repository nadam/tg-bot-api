package se.anyro.tgbotapi.types;

/** Scope in which bot commands are available. */
public class BotCommandScope {
    public String type;
    public String chat_id;
    public Long user_id;

    public BotCommandScope(String type) { this.type = type; }
    public static BotCommandScope allPrivateChats() { return new BotCommandScope("all_private_chats"); }
    public static BotCommandScope allGroupChats() { return new BotCommandScope("all_group_chats"); }
    public static BotCommandScope allChatAdministrators() { return new BotCommandScope("all_chat_administrators"); }
    public static BotCommandScope chat(String chatId) { BotCommandScope s = new BotCommandScope("chat"); s.chat_id = chatId; return s; }
    public static BotCommandScope chatAdministrators(String chatId) { BotCommandScope s = chat(chatId); s.type = "chat_administrators"; return s; }
    public static BotCommandScope chatMember(String chatId, long userId) { BotCommandScope s = chat(chatId); s.type = "chat_member"; s.user_id = userId; return s; }
}
