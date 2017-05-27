package se.anyro.tgbotapi.types.payments;

/**
 * @see <a href="https://core.telegram.org/bots/api#invoice">Invoice</a>
 */
public class Invoice {
    public String title;
    public String description;
    public String start_parameter;
    public String currency;
    public int total_amount;
}