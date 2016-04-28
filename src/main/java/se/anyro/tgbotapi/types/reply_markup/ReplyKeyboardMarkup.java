package se.anyro.tgbotapi.types.reply_markup;

/**
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardmarkup">Official documentation of
 *      ReplyKeyboardMarkup</a>
 */
public class ReplyKeyboardMarkup extends ReplyMarkup {

    public KeyboardButton[][] keyboard;
    public boolean resize_keyboard;
    public boolean one_time_keyboard;
    public boolean selective;

    public ReplyKeyboardMarkup(KeyboardButton[]... buttonRows) {
        keyboard = buttonRows;
    }

    /**
     * Simplified constructor for one button per row.
     */
    public ReplyKeyboardMarkup(KeyboardButton... buttons) {
        int numButtons = buttons.length;
        keyboard = new KeyboardButton[numButtons][];
        for (int i = 0; i < numButtons; ++i) {
            keyboard[i] = new KeyboardButton[] { buttons[i] };
        }
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
     * One button per line from strings.
     */
    public ReplyKeyboardMarkup(String... buttonTexts) {
        int numRows = buttonTexts.length;
        keyboard = new KeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            keyboard[i] = new KeyboardButton[] { new KeyboardButton(buttonTexts[i]) };
        }
    }
}