package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediavideo">Official documentation of InputMediaVideo</a>
 */
public class InputMediaVideo extends InputMedia {

    public String type = "video";
    public Integer width;
    public Integer height;
    public Integer duration;
    public Boolean supports_streaming;

    public InputMediaVideo() {
    }

    public InputMediaVideo(String media) {
        super(media);
    }

    public InputMediaVideo(String type, InputStream mediaStream, String filename) {
        super(mediaStream, filename);
    }

    public InputMediaVideo(String type, InputStream mediaStream, String filename, InputStream thumbStream) {
        super(mediaStream, filename, thumbStream);
    }
}
