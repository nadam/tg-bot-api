package se.anyro.tgbotapi.types.file;

/**
 * User profile photos as an array of arrays where each array is a single profile photo represented in different sizes.
 * 
 * @see <a href="https://core.telegram.org/bots/api#userprofilephotos">UserProfilePhotos</a>
 */
public class UserProfilePhotos {
    public int total_count;
    public PhotoSize[][] photos;
}
