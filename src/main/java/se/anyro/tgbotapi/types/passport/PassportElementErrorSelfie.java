package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorselfie">PassportElementErrorSelfie</a>
 */
public class PassportElementErrorSelfie extends PassportElementError.File {
    public PassportElementErrorSelfie() {
        super("selfie");
    }

    protected PassportElementErrorSelfie(String type, String fileHash, String message) {
        super("selfie", type, fileHash, message);
    }
}
