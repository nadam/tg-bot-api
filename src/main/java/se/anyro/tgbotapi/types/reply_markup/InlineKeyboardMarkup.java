package se.anyro.tgbotapi.types.reply_markup;

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
     * Simplified constructor for one button per row.
     */
    public InlineKeyboardMarkup(InlineKeyboardButton... buttons) {
        int numButtons = buttons.length;
        inline_keyboard = new InlineKeyboardButton[numButtons][];
        for (int i = 0; i < numButtons; ++i) {
            inline_keyboard[i] = new InlineKeyboardButton[] { buttons[i] };
        }
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
     * One button per line from strings.
     */
    public InlineKeyboardMarkup(String... buttonTexts) {
        int numRows = buttonTexts.length;
        inline_keyboard = new InlineKeyboardButton[numRows][];
        for (int i = 0; i < numRows; ++i) {
            InlineKeyboardButton button = InlineKeyboardButton.callbackData(buttonTexts[i], buttonTexts[i]);
            inline_keyboard[i] = new InlineKeyboardButton[] { button };
        }
    }
}
