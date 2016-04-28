package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultvideo">Official documentation of
 *      InlineQueryResultVideo</a>
 */
public class InlineQueryResultVideo extends InlineQueryResult {
    public String type = "video";
    public String id;
    public String video_url;
    public String mime_type;
    public int video_width;
    public int video_height;
    public int video_duration;
    public String thumb_url;
    public String title;
    public String caption;
    public String description;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;

    public static final String MIME_HTML = "text/html";
    public static final String MIME_MP4 = "video/mp4";

    public InlineQueryResultVideo(String id, String videoUrl, String mimeType, String thumbUrl, String title) {
        this.id = id;
        this.video_url = videoUrl;
        this.mime_type = mimeType;
        this.thumb_url = thumbUrl;
        this.title = title;
    }
}
