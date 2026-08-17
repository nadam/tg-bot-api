package se.anyro.tgbotapi.types;
public class MessageReactionUpdated {
    public Chat chat; public int message_id; public User user; public Chat actor_chat; public int date;
    public ReactionType[] old_reaction; public ReactionType[] new_reaction;
}
