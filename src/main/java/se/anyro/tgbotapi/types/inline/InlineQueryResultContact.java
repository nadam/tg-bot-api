package se.anyro.tgbotapi.types.inline;

import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinequeryresultcontact">Official documentation of
 *      InlineQueryResultContact</a>
 */
public class InlineQueryResultContact extends InlineQueryResult {
    public String type = "contact";
    public String id;
    public String phone_number;
    public String first_name;
    public String last_name;
    public InlineKeyboardMarkup reply_markup;
    public InputMessageContent input_message_content;
    public String thumb_url;
    public int thumb_width;
    public int thumb_height;

    public InlineQueryResultContact(String id, String phoneNumber, String firstName, String lastName) {
        this.id = id;
        this.phone_number = phoneNumber;
        this.first_name = firstName;
        this.last_name = lastName; // Optional but added here for consistency
    }
}
