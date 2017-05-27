package se.anyro.tgbotapi.types.payments;

/**
 * @see <a href="https://core.telegram.org/bots/api#orderinfo">OrderInfo</a>
 */
public class OrderInfo {
    public String name;
    public String phone_number;
    public String email;
    public ShippingAddress shipping_address;
}