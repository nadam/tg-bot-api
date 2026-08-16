package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#location">Location</a>
 */
public class Location {
    public float latitude;
    public float longitude;
    public float horizontal_accuracy;
    public int live_period;
    public int heading;
    public int proximity_alert_radius;
}
