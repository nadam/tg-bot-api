package se.anyro.tgbotapi.types.payments;

import se.anyro.tgbotapi.types.User;

/**
 * @see <a href="https://core.telegram.org/bots/api#shippingquery">ShippingQuery</a>
 */
public class ShippingQuery {
    public String id;
    public User from;
    public String invoice_payload;
    public ShippingAddress shipping_address;
}