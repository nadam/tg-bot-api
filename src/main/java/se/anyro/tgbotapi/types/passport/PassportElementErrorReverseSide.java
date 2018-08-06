package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorreverseside">PassportElementErrorReverseSide</a>
 */
public class PassportElementErrorReverseSide extends PassportElementError.File {
    public PassportElementErrorReverseSide() {
        super("reverse_side");
    }

    protected PassportElementErrorReverseSide(String type, String fileHash, String message) {
        super("reverse_side", type, fileHash, message);
    }
}
