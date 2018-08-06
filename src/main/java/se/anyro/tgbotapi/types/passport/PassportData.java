package se.anyro.tgbotapi.types.passport;

/**
 * @see <a href="https://core.telegram.org/bots/api#passportdata">PassportData</a>
 */
public class PassportData {
    public EncryptedPassportElement[] data;
    public EncryptedCredentials credentials;
}
