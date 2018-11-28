package se.anyro.tgbotapi.types.passport;

/**
 * @see <a
 *      href="https://core.telegram.org/bots/api#passportelementerrortranslationfiles">PassportElementErrorTranslationFiles</a>
 */
public class PassportElementErrorTranslationFiles extends PassportElementError.Files {

    public PassportElementErrorTranslationFiles() {
        super("files");
    }

    public PassportElementErrorTranslationFiles(String type, String[] fileHashes, String message) {
        super("files", type, fileHashes, message);
    }
}