package se.anyro.tgbotapi.types;

/**
 * @see <a href="https://core.telegram.org/bots/api#sendchataction">ChatAction</a>
 */
public enum ChatAction {

    TYPING("typing"),
    UPLOAD_PHOTO("upload_photo"),
    RECORD_VIDEO("record_video"),
    UPLOAD_VIDEO("upload_video"),
    RECORD_AUDIO("record_audio"),
    UPLOAD_AUDIO("upload_audio"),
    UPLOAD_DOCUMENT("upload_document"),
    FIND_LOCATION("find_location"),
    RECORD_VIDEO_NOTE("record_video_note"),
    UPLOAD_VIDEO_NOTE("upload_video_note");

    public final String VALUE;

    private ChatAction(String value) {
        VALUE = value;
    }
}
