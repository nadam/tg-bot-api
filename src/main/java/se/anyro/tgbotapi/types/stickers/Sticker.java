package se.anyro.tgbotapi.types.stickers;

import se.anyro.tgbotapi.types.file.PhotoSize;

/**
 * @see <a href="https://core.telegram.org/bots/api#sticker">Sticker</a>
 */
public class Sticker {
    public String file_id;
    public String file_unique_id;
    public int width;
    public int height;
    public PhotoSize thumb;
    public String emoji;
    public String set_name;
    public MaskPosition mask_position;
    public boolean is_animated;
    public boolean is_video;
    public se.anyro.tgbotapi.types.file.File premium_animation;
    public int file_size;
}
