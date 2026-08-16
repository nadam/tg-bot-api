package se.anyro.tgbotapi.types;
public class BotCommandScopeChat extends BotCommandScope {
    public BotCommandScopeChat(String chatId) { super("chat"); this.chat_id = chatId; }
    public BotCommandScopeChat(long chatId) { this(String.valueOf(chatId)); }
}
