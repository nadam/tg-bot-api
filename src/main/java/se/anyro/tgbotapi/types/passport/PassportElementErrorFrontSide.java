package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorfrontside">PassportElementErrorFrontSide</a>
 */
public class PassportElementErrorFrontSide extends PassportElementError.File {
    public PassportElementErrorFrontSide() {
        super("front_side");
    }

    protected PassportElementErrorFrontSide(String type, String fileHash, String message) {
        super("front_side", type, fileHash, message);
    }
}
