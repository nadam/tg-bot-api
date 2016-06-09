package se.anyro.tgbotapi.types;

import se.anyro.tgbotapi.types.file.Audio;
import se.anyro.tgbotapi.types.file.Document;
import se.anyro.tgbotapi.types.file.PhotoSize;
import se.anyro.tgbotapi.types.file.Sticker;
import se.anyro.tgbotapi.types.file.Video;
import se.anyro.tgbotapi.types.file.Voice;

/**
 * @see <a href="https://core.telegram.org/bots/api#message">Message</a>
 */
public class Message {
    public int message_id;
    public User from;
    public int date;
    public Chat chat;
    public User forward_from;
    public Chat forward_from_chat;
    public int forward_date;
    public Message reply_to_message;
    public int edit_date;
    public String text;
    public MessageEntity[] entities;
    public Audio audio;
    public Document document;
    public PhotoSize[] photo;
    public Sticker sticker;
    public Video video;
    public Voice voice;
    public String caption;
    public Contact contact;
    public Location location;
    public Venue venue;
    public User new_chat_member;
    public User left_chat_member;
    public String new_chat_title;
    public PhotoSize[] new_chat_photo;
    public boolean delete_chat_photo;
    public boolean group_chat_created;
    public boolean supergroup_chat_created;
    public boolean channel_chat_created;
    public int migrate_to_chat_id;
    public int migrate_from_chat_id;
    public Message pinned_message;

    public boolean isReply() {
        return reply_to_message != null;
    }
}
