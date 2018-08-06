package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorfile">PassportElementErrorFile</a>
 */
public class PassportElementErrorFile extends PassportElementError.File {
    public PassportElementErrorFile() {
        super("file");
    }

    protected PassportElementErrorFile(String type, String fileHash, String message) {
        super("file", type, fileHash, message);
    }
}