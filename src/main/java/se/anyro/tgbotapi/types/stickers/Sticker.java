package se.anyro.tgbotapi.types.stickers;

import se.anyro.tgbotapi.types.file.PhotoSize;

/**
 * @see <a href="https://core.telegram.org/bots/api#sticker">Sticker</a>
 */
public class Sticker {
    public String file_id;
    public int width;
    public int height;
    public PhotoSize thumb;
    public String emoji;
    public String set_name;
    public MaskPosition mask_position;
    public int file_size;
}