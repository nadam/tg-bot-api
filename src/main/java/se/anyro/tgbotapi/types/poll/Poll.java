package se.anyro.tgbotapi.types.poll;

import se.anyro.tgbotapi.types.MessageEntity;

public class Poll {
    public String id;
    public String question;
    public PollOption[] options;
    public int total_voter_count;
    public boolean is_closed;
    public boolean is_anonymous;
    public String type;
    public boolean allows_multiple_answers;
    public int correct_option_id;
    public String explanation;
    public MessageEntity[] explanation_entities;
    public int open_period;
    public int close_date;
}
