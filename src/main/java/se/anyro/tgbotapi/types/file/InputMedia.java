package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

/**
 * Base class of the InputMedia classes.
 * 
 * @see <a href="https://core.telegram.org/bots/api#inputmedia">Official documentation of InputMedia</a>
 */
public abstract class InputMedia {

    public String type;
    public String media;
    public String caption;

    // Not part of JSON serialization
    public transient InputStream mediaStream;
    public transient String filename;
    
    protected InputMedia(String type) {
        this.type = type;
    }

    protected InputMedia(String type, String media) {
        this(type);
        this.media = media;
    }

    protected InputMedia(String type, InputStream mediaStream, String filename) {
        this(type);
        this.media = "attach://" + filename;
        this.mediaStream = mediaStream;
        this.filename = filename;
    }
}
