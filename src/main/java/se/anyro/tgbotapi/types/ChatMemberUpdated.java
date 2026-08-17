package se.anyro.tgbotapi.types;

public class ChatMemberUpdated {
    public Chat chat;
    public User from;
    public int date;
    public ChatMember old_chat_member;
    public ChatMember new_chat_member;
    public ChatInviteLink invite_link;
    public boolean via_chat_folder_invite_link;
}
