package se.anyro.tgbotapi.types;
public class BotCommandScopeChatMember extends BotCommandScope {
    public BotCommandScopeChatMember(String chatId, long userId) { super("chat_member"); this.chat_id = chatId; this.user_id = userId; }
    public BotCommandScopeChatMember(long chatId, long userId) { this(String.valueOf(chatId), userId); }
}
