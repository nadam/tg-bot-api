package se.anyro.tgbotapi.types.reply_markup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardmarkup">Official documentation of
 *      InlineKeyboardMarkup</a>
 */
public class InlineKeyboardMarkup extends ReplyMarkup {

    public InlineKeyboardButton[][] inline_keyboard;

    public InlineKeyboardMarkup() {
    }

    public InlineKeyboardMarkup(InlineKeyboardButton[]... buttonRows) {
        inline_keyboard = buttonRows;
    }

    /**
     * Converting strings arrays to InlineKeyboardButton objects.
     */
    public InlineKeyboardMarkup(String[]... buttonTextRows) {
        int numRows = buttonTextRows.length;
        inline_keyboard = new InlineKeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            int numButtons = buttonTextRows[i].length;
            InlineKeyboardButton[] buttonRow = new InlineKeyboardButton[numButtons];
            for (int j = 0; j < numButtons; ++j) {
                buttonRow[j] = InlineKeyboardButton.callbackData(buttonTextRows[i][j], buttonTextRows[i][j]);
            }
            inline_keyboard[i] = buttonRow;
        }
    }

    /**
     * Creates an InlineKeyboardMarkup with one button per line.
     */
    public static InlineKeyboardMarkup createVertical(InlineKeyboardButton... buttons) {
        int numButtons = buttons.length;
        InlineKeyboardButton[][] keyboard = new InlineKeyboardButton[numButtons][];
        for (int i = 0; i < numButtons; ++i) {
            keyboard[i] = new InlineKeyboardButton[] { buttons[i] };
        }
        return new InlineKeyboardMarkup(keyboard);
    }

    /**
     * Creates an InlineKeyboardMarkup with one button per line from strings. The buttons will have the callback data
     * set to the same string as the button text.
     */
    public static InlineKeyboardMarkup createVertical(String... buttonTexts) {
        int numRows = buttonTexts.length;
        InlineKeyboardButton[][] keyboard = new InlineKeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            InlineKeyboardButton button = InlineKeyboardButton.callbackData(buttonTexts[i], buttonTexts[i]);
            keyboard[i] = new InlineKeyboardButton[] { button };
        }
        return new InlineKeyboardMarkup(keyboard);
    }

    /**
     * Create an InlineKeyboardMarkup with just one row of buttons.
     */
    public static InlineKeyboardMarkup createHorizontal(InlineKeyboardButton... buttons) {
        int numButtons = buttons.length;
        InlineKeyboardButton[][] keyboard = new InlineKeyboardButton[1][numButtons];
        for (int i = 0; i < numButtons; ++i) {
            keyboard[0][i] = buttons[i];
        }
        return new InlineKeyboardMarkup(keyboard);
    }

    /**
     * Class for dynamically building a keyboard line by line. Call addRow() to add a new row and then call the
     * add*Button() methods to add buttons to that row. Finally call toMarkup() to create the InlineKeyboardMarkup
     * object.
     */
    public static class Builder {

        public List<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();

        public Builder addRow() {
            keyboard.add(new ArrayList<InlineKeyboardButton>());
            return this;
        }

        public Builder addRow(InlineKeyboardButton... buttons) {
            keyboard.add(Arrays.asList(buttons));
            return this;
        }

        public Builder addButton(InlineKeyboardButton button) {
            if (keyboard.size() == 0) {
                addRow();
            }
            keyboard.get(keyboard.size() - 1).add(button);
            return this;
        }

        public Builder addUrlButton(String text, String url) {
            return addButton(InlineKeyboardButton.url(text, url));
        }

        public Builder addCallbackDataButton(String text, String data) {
            return addButton(InlineKeyboardButton.callbackData(text, data));
        }

        public Builder addSwitchInlineQueryButton(String text, String query) {
            return addButton(InlineKeyboardButton.switchInlineQuery(text, query));
        }

        public InlineKeyboardMarkup toMarkup() {
            int numRows = this.keyboard.size();
            InlineKeyboardButton[][] keyboard = new InlineKeyboardButton[numRows][];
            for (int i = 0; i < numRows; ++i) {
                List<InlineKeyboardButton> row = this.keyboard.get(i);
                int numButtons = row.size();
                keyboard[i] = row.toArray(new InlineKeyboardButton[numButtons]);
            }
            return new InlineKeyboardMarkup(keyboard);
        }
    }
}