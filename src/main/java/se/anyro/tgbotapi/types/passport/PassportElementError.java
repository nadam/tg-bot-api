package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerror">PassportElementError</a>
 */
public class PassportElementError {
    public final String source;
    public String type;
    public String message;

    protected PassportElementError(String source) {
        this.source = source;
    }

    protected PassportElementError(String source, String type, String message) {
        this(source);
        this.type = type;
        this.message = message;
    }

    // Base class for single file errors (FrontSide, ReverseSide, Selfie, Unspecified, etc.)
    public static class File extends PassportElementError {
        public String file_hash;

        protected File(String source) {
            super(source);
        }

        protected File(String source, String type, String fileHash, String message) {
            super(source, type, message);
            this.file_hash = fileHash;
        }
    }

    // Base class for multi-file errors (TranslationFiles and Files)
    public static class Files extends PassportElementError {
        public String[] file_hashes;

        protected Files(String source) {
            super(source);
        }

        protected Files(String source, String type, String[] fileHashes, String message) {
            super(source, type, message);
            this.file_hashes = fileHashes;
        }
    }
}