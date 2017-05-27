package se.anyro.tgbotapi.types.payments;

/**
 * @see <a href="https://core.telegram.org/bots/api#successfulpayment">SuccessfulPayment</a>
 */
public class SuccessfulPayment {
    public String currency;
    public int total_amount;
    public String invoice_payload;
    public String shipping_option_id;
    public OrderInfo[] order_info;
    public String telegram_payment_charge_id;
    public String provider_payment_charge_id;
}