package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediaphoto">Official documentation of InputMediaPhoto</a>
 */
public class InputMediaDocument extends InputMedia {

    public String type = "document";

    public InputMediaDocument() {
    }

    public InputMediaDocument(String media) {
        super(media);
    }

    public InputMediaDocument(String type, InputStream mediaStream, String filename) {
        super(mediaStream, filename);
    }

    public InputMediaDocument(String type, InputStream mediaStream, String filename, InputStream thumbStream) {
        super(mediaStream, filename, thumbStream);
    }
}
