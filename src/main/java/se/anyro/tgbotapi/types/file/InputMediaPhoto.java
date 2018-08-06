package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediaphoto">Official documentation of InputMediaPhoto</a>
 */
public class InputMediaPhoto extends InputMedia {

    public String type = "photo";

    public InputMediaPhoto() {
    }

    public InputMediaPhoto(String media) {
        super(media);
    }

    public InputMediaPhoto(String type, InputStream mediaStream, String filename) {
        super(mediaStream, filename);
    }
}
