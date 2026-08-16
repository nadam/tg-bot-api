package se.anyro.tgbotapi.types.inline;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputlocationmessagecontent">Official documentation of
 *      InputLocationMessageContent</a>
 */
public class InputLocationMessageContent extends InputMessageContent {

    public float latitude;
    public float longitude;
    public float horizontal_accuracy;
    public int live_period;
    public int heading;
    public int proximity_alert_radius;

    public InputLocationMessageContent(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
