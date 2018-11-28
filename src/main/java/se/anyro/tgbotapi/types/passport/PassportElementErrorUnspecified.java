package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrorunspecified">PassportElementErrorUnspecified</a>
 */
public class PassportElementErrorUnspecified extends PassportElementError {
    public String element_hash;

    public PassportElementErrorUnspecified() {
        super("unspecified");
    }

    public PassportElementErrorUnspecified(String type, String elementHash, String message) {
        super("unspecified", type, message);
        this.element_hash = elementHash;
    }
}