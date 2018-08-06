package se.anyro.tgbotapi.types.inline;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputvenuemessagecontent">Official documentation of
 *      InputVenueMessageContent</a>
 */
public class InputVenueMessageContent extends InputMessageContent {

    public float latitude;
    public float longitude;
    public String title;
    public String address;
    public String foursquare_id;
    public String foursquare_type;

    public InputVenueMessageContent(float latitude, float longitude, String title, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
    }
}