package se.anyro.tgbotapi.types.inline;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputlocationmessagecontent">Official documentation of
 *      InputLocationMessageContent</a>
 */
public class InputLocationMessageContent extends InputMessageContent {

    public float latitude;
    public float longitude;

    public InputLocationMessageContent(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}