package se.anyro.tgbotapi.types.payments;

/**
 * @see <a href="https://core.telegram.org/bots/api#shippingaddress">ShippingAddress</a>
 */
public class ShippingAddress {
    public String country_code;
    public String state;
    public String city;
    public String street_line1;
    public String street_line2;
    public String post_code;
}
