package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#venue">Official documentation of Venue</a>
 */
public class Venue {
    public Location location;
    public String title;
    public String address;
    public String foursquare_id;
    public String foursquare_type;
    public String google_place_id;
    public String google_place_type;
}
