package se.anyro.tgbotapi.types;
public class GiveawayWinners {
    public Chat chat; public int giveaway_message_id; public int winners_selection_date; public int winner_count;
    public User[] winners; public int additional_chat_count; public int premium_subscription_month_count;
    public int unclaimed_prize_count; public boolean only_new_members; public boolean was_refunded;
    public String prize_description;
}
