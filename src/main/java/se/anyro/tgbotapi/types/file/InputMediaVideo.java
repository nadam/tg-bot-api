package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediavideo">Official documentation of InputMediaVideo</a>
 */
public class InputMediaVideo extends InputMedia {

    public int width;
    public int height;
    public int duration;

    public InputMediaVideo() {
        super("video", null);
    }

    public InputMediaVideo(String media) {
        super("video", media);
    }

    public InputMediaVideo(String type, InputStream mediaStream, String filename) {
        super("video", mediaStream, filename);
    }

}
