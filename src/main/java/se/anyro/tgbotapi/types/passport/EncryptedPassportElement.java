package se.anyro.tgbotapi.types.passport;


/**
 * @see <a href="https://core.telegram.org/bots/api#encryptedpassportelement">EncryptedPassportElement</a>
 */
public class EncryptedPassportElement {
    public String type;
    public String data;
    public String phone_number;
    public String email;
    public PassportFile[] files;
    public PassportFile front_side;
    public PassportFile reverse_side;
    public PassportFile selfie;
    public PassportFile[] translation;
    public String hash;

    public enum Type {
        PERSONAL_DETAILS, PASSPORT, DRIVER_LICENSE, IDENTITY_CARD, INTERNAL_PASSPORT, ADDRESS, UTILITY_BILL, 
        BANK_STATEMENT, RENTAL_AGREEMENT, PASSPORT_REGISTRATION, TEMPORARY_REGISTRATION, PHONE_NUMBER, EMAIL
    }

    /**
     * Get the type field as an enum value.
     */
    public Type getType() {
        try {
            return Type.valueOf(type.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
