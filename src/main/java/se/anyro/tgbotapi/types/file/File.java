package se.anyro.tgbotapi.types.file;

/**
 * Represents a file ready to be downloaded.
 * 
 * @see <a href="https://core.telegram.org/bots/api#file">File</a>
 */
public class File {
    public String file_id;
    public String file_unique_id;
    public long file_size;
    public String file_path;
}
