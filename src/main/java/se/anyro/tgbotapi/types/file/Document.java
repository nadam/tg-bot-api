package se.anyro.tgbotapi.types.file;

/**
 * @see <a href="https://core.telegram.org/bots/api#document">Document</a>
 */
public class Document {
    public String file_id;
    public PhotoSize thumb;
    public String file_name;
    public String mime_type;
    public int file_size;
}
