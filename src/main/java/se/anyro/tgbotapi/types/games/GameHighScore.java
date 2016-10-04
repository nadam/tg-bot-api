package se.anyro.tgbotapi.types.games;

import se.anyro.tgbotapi.types.User;

/**
 * One row of the high scores table for a game.
 * 
 * @see <a href="https://core.telegram.org/bots/api#gamehighscore">GameHighScore</a>
 */
public class GameHighScore {
    public int position;
    public User user;
    public int score;
}