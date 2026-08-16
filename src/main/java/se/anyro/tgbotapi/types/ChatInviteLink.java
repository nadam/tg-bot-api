package se.anyro.tgbotapi.types;

public class ChatInviteLink {
    public String invite_link;
    public User creator;
    public boolean is_primary;
    public boolean is_revoked;
    public int expire_date;
    public int member_limit;
    public String name;
    public boolean creates_join_request;
    public int pending_join_request_count;
}
