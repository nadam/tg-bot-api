package se.anyro.tgbotapi.types.poll;

import se.anyro.tgbotapi.types.User;

public class PollAnswer {
    public String poll_id;
    public User user;
    public int[] option_ids;
}
