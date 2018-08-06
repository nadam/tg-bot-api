package se.anyro.tgbotapi.types.file;

import java.io.InputStream;

import se.anyro.tgbotapi.types.ParseMode;

/**
 * Base class of the InputMedia classes.
 * 
 * @see <a href="https://core.telegram.org/bots/api#inputmedia">Official documentation of InputMedia</a>
 */
public abstract class InputMedia {

    public String media;
    public String thumb;
    public String caption;
    public String parse_mode;

    // Not part of JSON serialization
    public transient String filename;
    public transient InputStream mediaStream;
    public transient InputStream thumbStream;
    
    public InputMedia() {
    }

    protected InputMedia(String media) {
        this.media = media;
    }

    protected InputMedia(InputStream mediaStream, String filename) {
        this.media = "attach://" + filename;
        this.filename = filename;
        this.mediaStream = mediaStream;
    }

    public InputMedia(InputStream mediaStream, String filename, InputStream thumbStream) {
        this(mediaStream, filename);
        this.thumb = "attach://" + getThumbFilename();
        this.thumbStream = thumbStream;
    }

    public void setParseMode(ParseMode parseMode) {
        this.parse_mode = parseMode.VALUE;
    }

    public String getThumbFilename() {
        return "thumb_" + filename;
    }
}