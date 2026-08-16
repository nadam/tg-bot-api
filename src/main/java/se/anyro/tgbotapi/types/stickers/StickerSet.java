package se.anyro.tgbotapi.types.stickers;


/**
 * @see <a href="https://core.telegram.org/bots/api#stickerset">StickerSet</a>
 */
public class StickerSet {
    public String name;
    public String title;
    public boolean isMasks;
    public boolean is_masks;
    public boolean is_animated;
    public se.anyro.tgbotapi.types.file.PhotoSize thumb;
    public Sticker[] stickers;
}
