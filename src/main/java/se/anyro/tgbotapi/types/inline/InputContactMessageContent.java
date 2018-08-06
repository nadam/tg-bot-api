package se.anyro.tgbotapi.types.inline;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputcontactmessagecontent">Official documentation of
 *      InputContactMessageContent</a>
 */
public class InputContactMessageContent extends InputMessageContent {
    public String phone_number;
    public String first_name;
    public String last_name;
    public String vcard;

    public InputContactMessageContent(String phoneNumber, String firstName, String lastName) {
        this.phone_number = phoneNumber;
        this.first_name = firstName;
        this.last_name = lastName; // Optional but added here for consistency
    }
}
