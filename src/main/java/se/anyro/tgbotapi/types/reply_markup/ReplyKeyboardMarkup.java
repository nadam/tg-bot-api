package se.anyro.tgbotapi.types.reply_markup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardmarkup">Official documentation of
 *      ReplyKeyboardMarkup</a>
 */
public class ReplyKeyboardMarkup extends ReplyMarkup {

    public KeyboardButton[][] keyboard;
    public boolean resize_keyboard = true;
    public Boolean one_time_keyboard;
    /**
     * Targets: 1) users that are @mentioned in the text of the Message object; 2) if the bot's message is a reply (has
     * reply_to_message_id), sender of the original message.
     */
    public Boolean selective;

    public ReplyKeyboardMarkup(KeyboardButton[]... buttonRows) {
        keyboard = buttonRows;
    }

    /**
     * Converting array of strings to arrays of KeyboardButton objects.
     */
    public ReplyKeyboardMarkup(String[]... buttonTextRows) {
        int numRows = buttonTextRows.length;
        keyboard = new KeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            int numButtons = buttonTextRows[i].length;
            KeyboardButton[] buttonRow = new KeyboardButton[numButtons];
            for (int j = 0; j < numButtons; ++j) {
                buttonRow[j] = new KeyboardButton(buttonTextRows[i][j]);
            }
            keyboard[i] = buttonRow;
        }
    }

    /**
     * Create ReplyKeyboardMarkup with one button per row.
     */
    public static ReplyKeyboardMarkup createVertical(KeyboardButton... buttons) {
        int numRows = buttons.length;
        KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            keyboard[i] = new KeyboardButton[] { buttons[i] };
        }
        return new ReplyKeyboardMarkup(keyboard);
    }

    /**
     * Create ReplyKeyboardButton with one button per line from strings.
     */
    public static ReplyKeyboardMarkup createVertical(String... buttonTexts) {
        int numRows = buttonTexts.length;
        KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            keyboard[i] = new KeyboardButton[] { new KeyboardButton(buttonTexts[i]) };
        }
        return new ReplyKeyboardMarkup(keyboard);
    }

    /**
     * Create a ReplyKeyboardMarkup with just one row of buttons.
     */
    public static ReplyKeyboardMarkup createHorizontal(KeyboardButton... buttons) {
        int numButtons = buttons.length;
        KeyboardButton[][] keyboard = new KeyboardButton[1][numButtons];
        for (int i = 0; i < numButtons; ++i) {
            keyboard[0][i] = buttons[i];
        }
        return new ReplyKeyboardMarkup(keyboard);
    }

    /**
     * Create a ReplyKeyboardMarkup with just one row of buttons from strings.
     */
    public static ReplyKeyboardMarkup createHorizontal(String... buttonTexts) {
        int numButtons = buttonTexts.length;
        KeyboardButton[][] keyboard = new KeyboardButton[1][numButtons];
        for (int i = 0; i < numButtons; ++i) {
            keyboard[0][i] = new KeyboardButton(buttonTexts[i]);
        }
        return new ReplyKeyboardMarkup(keyboard);
    }

    /**
     * Class for dynamically building a keyboard line by line. Call addRow() to add a new row and then call the
     * addButton methods to add buttons to that row. Finally call toMarkup() to create the ReplyKeyboardMarkup object.
     */

    public static class Builder {

        public List<List<KeyboardButton>> keyboard = new ArrayList<List<KeyboardButton>>();

        public Builder addRow() {
            keyboard.add(new ArrayList<KeyboardButton>());
            return this;
        }

        public Builder addRow(KeyboardButton... buttons) {
            keyboard.add(Arrays.asList(buttons));
            return this;
        }

        public Builder addButton(KeyboardButton button) {
            keyboard.get(keyboard.size() - 1).add(button);
            return this;
        }

        public Builder addButton(String text) {
            addButton(new KeyboardButton(text));
            return this;
        }

        public Builder addContactButton(String text) {
            KeyboardButton button = new KeyboardButton(text);
            button.request_contact = true;
            return addButton(button);
        }

        public Builder addLocationButton(String text) {
            KeyboardButton button = new KeyboardButton(text);
            button.request_location = true;
            return addButton(button);
        }

        public ReplyKeyboardMarkup toMarkup() {
            int numRows = this.keyboard.size();
            KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
            for (int i = 0; i < numRows; ++i) {
                List<KeyboardButton> row = this.keyboard.get(i);
                int numButtons = row.size();
                keyboard[i] = row.toArray(new KeyboardButton[numButtons]);
            }
            return new ReplyKeyboardMarkup(keyboard);
        }

        public ReplyKeyboardMarkup toMarkup(boolean resize_keyboard) {
            int numRows = this.keyboard.size();
            KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
            for (int i = 0; i < numRows; ++i) {
                List<KeyboardButton> row = this.keyboard.get(i);
                int numButtons = row.size();
                keyboard[i] = row.toArray(new KeyboardButton[numButtons]);
            }

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(keyboard);

            markup.resize_keyboard = resize_keyboard;
            return markup;
        }

        public ReplyKeyboardMarkup toMarkup(boolean resize_keyboard, Boolean one_time_keyboard) {
            int numRows = this.keyboard.size();
            KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
            for (int i = 0; i < numRows; ++i) {
                List<KeyboardButton> row = this.keyboard.get(i);
                int numButtons = row.size();
                keyboard[i] = row.toArray(new KeyboardButton[numButtons]);
            }

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(keyboard);

            markup.resize_keyboard = resize_keyboard;
            markup.one_time_keyboard = one_time_keyboard;
            return markup;
        }

        public ReplyKeyboardMarkup toMarkup(boolean resize_keyboard, Boolean one_time_keyboard, Boolean selective) {
            int numRows = this.keyboard.size();
            KeyboardButton[][] keyboard = new KeyboardButton[numRows][];
            for (int i = 0; i < numRows; ++i) {
                List<KeyboardButton> row = this.keyboard.get(i);
                int numButtons = row.size();
                keyboard[i] = row.toArray(new KeyboardButton[numButtons]);
            }

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(keyboard);

            markup.resize_keyboard = resize_keyboard;
            markup.one_time_keyboard = one_time_keyboard;
            markup.selective = selective;
            return markup;
        }
    }
}