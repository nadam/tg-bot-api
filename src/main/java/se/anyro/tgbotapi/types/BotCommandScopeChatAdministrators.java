package se.anyro.tgbotapi.types;
public class BotCommandScopeChatAdministrators extends BotCommandScope {
    public BotCommandScopeChatAdministrators(String chatId) { super("chat_administrators"); this.chat_id = chatId; }
    public BotCommandScopeChatAdministrators(long chatId) { this(String.valueOf(chatId)); }
}
