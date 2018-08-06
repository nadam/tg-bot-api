package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#audio">Audio</a>
 */
public class Audio {
    public String file_id;
    public int duration;
    public String performer;
    public String title;
    public String mime_type;
    public int file_size;
    public PhotoSize thumb;
}
