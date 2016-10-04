package se.anyro.tgbotapi.types.games;

import se.anyro.tgbotapi.types.MessageEntity;
import se.anyro.tgbotapi.types.file.PhotoSize;

/**
 * @see <a href="https://core.telegram.org/bots/api#game">Game</a>
 */
public class Game {
    public String title;
    public String description;
    public PhotoSize[] photo;
    public String text;
    public MessageEntity[] text_entities;
    public Animation animation;
}