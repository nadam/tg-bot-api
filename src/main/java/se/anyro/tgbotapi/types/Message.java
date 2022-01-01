package se.anyro.tgbotapi.types;

import se.anyro.tgbotapi.types.file.Audio;
import se.anyro.tgbotapi.types.file.Document;
import se.anyro.tgbotapi.types.file.PhotoSize;
import se.anyro.tgbotapi.types.file.Video;
import se.anyro.tgbotapi.types.file.VideoNote;
import se.anyro.tgbotapi.types.file.Voice;
import se.anyro.tgbotapi.types.games.Animation;
import se.anyro.tgbotapi.types.games.Game;
import se.anyro.tgbotapi.types.passport.PassportData;
import se.anyro.tgbotapi.types.payments.Invoice;
import se.anyro.tgbotapi.types.payments.SuccessfulPayment;
import se.anyro.tgbotapi.types.stickers.Sticker;

/**
 * @see <a href="https://core.telegram.org/bots/api#message">Message</a>
 */
public class Message {
    public int message_id;
    public User from;
    public int date;
    public Chat chat;
    public User forward_from;
    public Chat forward_from_chat; // Only for channels
    public int forward_from_message_id;
    public String forward_signature;
    public String forward_sender_name;
    public int forward_date;
    public Message reply_to_message;
    public int edit_date;
    public String author_signature;
    public String text;
    public MessageEntity[] entities;
    public MessageEntity[] caption_entities;
    public Audio audio;
    public Document document;
    public Animation animation;
    public Game game;
    public PhotoSize[] photo;
    public Sticker sticker;
    public Video video;
    public Voice voice;
    public VideoNote video_note;
    public String caption;
    public Contact contact;
    public Location location;
    public Venue venue;
    public User[] new_chat_members;
    public User left_chat_member;
    public String new_chat_title;
    public PhotoSize[] new_chat_photo;
    public boolean delete_chat_photo;
    public boolean group_chat_created;
    public boolean supergroup_chat_created;
    public boolean channel_chat_created;
    public long migrate_to_chat_id;
    public long migrate_from_chat_id;
    public Message pinned_message;
    public Invoice invoice;
    public SuccessfulPayment successful_payment;
    public String connected_website;
    public PassportData passport_data;

    private static final String VIDEO_MP4 = "video/mp4";

    /**
     * Returns text or caption depending on what is set or an empty string if none is available.
     */
    public String getSafeTextOrCaption() {
        if (text != null) {
            return text;
        } else if (caption != null) {
            return caption;
        } else {
            return "";
        }
    }

    public boolean isGif() {
        return document != null && VIDEO_MP4.equals(document.mime_type);
    }

    public boolean isReply() {
        return reply_to_message != null;
    }

    public boolean isForwardedFromChannel() {
        return forward_from_chat != null;
    }

    public boolean isForwardedFrom(long channel) {
        return forward_from_chat != null && forward_from_chat.id == channel;
    }

    /**
     * Did the user forward a message that isn't his/her own message?
     */
    public boolean isForwardedFromOther() {
        if (forward_date == 0) {
            return false; // Not forwarded
        }
        if (forward_from == null || from == null) {
            return true;
        }
        return from.id != forward_from.id;
    }

    /**
     * Returns true if the message is just a notification such as a new group name or a member leaving a group.
     */
    public boolean isNotification() {
        return new_chat_members != null || left_chat_member != null || new_chat_title != null || new_chat_photo != null
                || delete_chat_photo || group_chat_created || supergroup_chat_created || channel_chat_created
                || migrate_to_chat_id > 0 || migrate_from_chat_id > 0 || pinned_message != null;
    }
}