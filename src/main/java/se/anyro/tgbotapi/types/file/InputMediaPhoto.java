package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediaphoto">Official documentation of InputMediaPhoto</a>
 */
public class InputMediaPhoto extends InputMedia {

    public InputMediaPhoto() {
        super("photo", null);
    }

    public InputMediaPhoto(String media) {
        super("photo", media);
    }

    public InputMediaPhoto(String type, InputStream mediaStream, String filename) {
        super("photo", mediaStream, filename);
    }
}
