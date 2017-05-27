package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#videonote">VideoNote</a>
 */
public class VideoNote {
    public String file_id;
    public int length;
    public int duration;
    public PhotoSize thumb;
    public int file_size;
}
