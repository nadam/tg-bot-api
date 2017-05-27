package se.anyro.tgbotapi.types.payments;

/**
 * @see <a href="https://core.telegram.org/bots/api#shippingoption">ShippingOption</a>
 */
public class ShippingOption {
    public String id;
    public String title;
    public LabeledPrice[] prices;
}