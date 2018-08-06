package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#animation">Animation</a>
 */
public class Animation {
    public String file_id;
    public int width;
    public int height;
    public int duration;
    public PhotoSize thumb;
    public String file_name;
    public String mime_type;
    public int file_size;
}
