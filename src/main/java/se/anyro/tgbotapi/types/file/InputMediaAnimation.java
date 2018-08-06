package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * @see <a href="https://core.telegram.org/bots/api#inputmediaanimation">Official documentation of
 *      InputMediaAnimation</a>
 */
public class InputMediaAnimation extends InputMedia {

    public String type = "animation";
    public Integer width;
    public Integer height;
    public Integer duration;
    public Boolean supports_streaming;

    public InputMediaAnimation() {
    }

    public InputMediaAnimation(String media) {
        super(media);
    }

    public InputMediaAnimation(String type, InputStream mediaStream, String filename) {
        super(mediaStream, filename);
    }

    public InputMediaAnimation(String type, InputStream mediaStream, String filename, InputStream thumbStream) {
        super(mediaStream, filename, thumbStream);
    }
}
