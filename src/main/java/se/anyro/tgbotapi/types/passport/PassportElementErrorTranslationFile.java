package se.anyro.tgbotapi.types.passport;

/**
 * @see <a
 *      href="https://core.telegram.org/bots/api#passportelementerrortranslationfile">PassportElementErrorTranslationFile</a>
 */
public class PassportElementErrorTranslationFile extends PassportElementError.File {
    public PassportElementErrorTranslationFile() {
        super("translation_file");
    }

    protected PassportElementErrorTranslationFile(String type, String fileHash, String message) {
        super("translation_file", type, fileHash, message);
    }
}