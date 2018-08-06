package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediaaudio">Official documentation of InputMediaAudio</a>
 */
public class InputMediaAudio extends InputMedia {

    public String type = "audio";
    public Integer duration;
    public String performer;
    public String title;
    public Boolean supports_streaming;

    public InputMediaAudio() {
    }

    public InputMediaAudio(String media) {
        super(media);
    }

    public InputMediaAudio(String type, InputStream mediaStream, String filename) {
        super(mediaStream, filename);
    }

    public InputMediaAudio(String type, InputStream mediaStream, String filename, InputStream thumbStream) {
        super(mediaStream, filename, thumbStream);
    }
}
