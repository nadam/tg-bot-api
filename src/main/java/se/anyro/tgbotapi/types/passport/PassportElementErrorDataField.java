package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportelementerrordatafield">PassportElementErrorDataField</a>
 */
public class PassportElementErrorDataField extends PassportElementError {
    public String field_name;
    public String data_hash;

    public PassportElementErrorDataField() {
        super("data");
    }

    public PassportElementErrorDataField(String type, String fieldName, String dataHash, String message) {
        super("data", type, message);
        this.field_name = fieldName;
        this.data_hash = dataHash;
    }
}