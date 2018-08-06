package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorfiles">PassportElementErrorFiles</a>
 */
public class PassportElementErrorFiles extends PassportElementError {
    public String[] file_hashes;

    public PassportElementErrorFiles() {
        super("files");
    }

    public PassportElementErrorFiles(String type, String[] fileHashes, String message) {
        super("files", type, message);
        this.file_hashes = fileHashes;
    }
}