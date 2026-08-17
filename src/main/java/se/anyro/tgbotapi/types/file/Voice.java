package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#voice">Voice</a>
 */
public class Voice {
    public String file_id;
    public String file_unique_id;
    public int duration;
    public String mime_type;
    public long file_size;
}
