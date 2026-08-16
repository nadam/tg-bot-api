package se.anyro.tgbotapi.types;

public class ChatMemberUpdated {
    public Chat chat;
    public User from;
    public int date;
    public ChatMember old_chat_member;
    public ChatMember new_chat_member;
    public ChatInviteLink invite_link;
}
