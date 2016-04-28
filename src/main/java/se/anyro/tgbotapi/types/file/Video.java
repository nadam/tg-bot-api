package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#video">Video</a>
 */
public class Video {
    public String file_id;
    public int width;
    public int height;
    public int duration;
    public PhotoSize thumb;
    public String mime_type;
    public int file_size;
}
