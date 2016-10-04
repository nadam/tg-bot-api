package se.anyro.tgbotapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import se.anyro.tgbotapi.types.Chat;
import se.anyro.tgbotapi.types.ChatAction;
import se.anyro.tgbotapi.types.ChatMember;
import se.anyro.tgbotapi.types.Message;
import se.anyro.tgbotapi.types.ParseMode;
import se.anyro.tgbotapi.types.ResponseParameters;
import se.anyro.tgbotapi.types.Update;
import se.anyro.tgbotapi.types.User;
import se.anyro.tgbotapi.types.WebhookInfo;
import se.anyro.tgbotapi.types.file.File;
import se.anyro.tgbotapi.types.file.UserProfilePhotos;
import se.anyro.tgbotapi.types.games.GameHighScore;
import se.anyro.tgbotapi.types.inline.CallbackQuery;
import se.anyro.tgbotapi.types.inline.InlineQueryResult;
import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;
import se.anyro.tgbotapi.types.reply_markup.ReplyMarkup;
import se.anyro.tgbotapi.utils.FileSender;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TgBotApi {

    private User botUser;

    private final String BASE_URL;

    private final String GET_UPDATES;
    private final String SET_WEBHOOK;
    private final String GET_WEBHOOK_INFO;
    private final String GET_ME;
    private final String SEND_MESSAGE;
    private final String FORWARD_MESSAGE;
    private final String SEND_PHOTO;
    private final String SEND_AUDIO;
    private final String SEND_DOCUMENT;
    private final String SEND_STICKER;
    private final String SEND_VIDEO;
    private final String SEND_VOICE;
    private final String SEND_LOCATION;
    private final String SEND_VENUE;
    private final String SEND_CONTACT;
    private final String SEND_CHAT_ACTION;
    private final String GET_USER_PROFILE_PHOTOS;
    private final String GET_FILE;
    private final String GET_FILE_URL;
    private final String KICK_CHAT_MEMBER;
    private final String LEAVE_CHAT;
    private final String UNBAN_CHAT_MEMBER;
    private final String GET_CHAT;
    private final String GET_CHAT_ADMINISTRATORS;
    private final String GET_CHAT_MEMBERS_COUNT;
    private final String GET_CHAT_MEMBER;
    private final String ANSWER_CALLBACK_QUERY;
    private final String EDIT_MESSAGE_TEXT;
    private final String EDIT_MESSAGE_CAPTION;
    private final String EDIT_MESSAGE_REPLY_MARKUP;
    private final String ANSWER_INLINE_QUERY;
    private final String SEND_GAME;
    private final String SET_GAME_SCORE;
    private final String GET_GAME_HIGH_SCORES;

    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    private final long OWNER;

    private boolean disableNotification = false;

    private ErrorListener errorListener;

    public interface ErrorListener {
        void onError(int errorCode, String description);
    }

    /**
     * @param token
     *            Your Bot API token from BotFather.
     * @param owner
     *            Your user ID
     */
    public TgBotApi(String token, long owner) {
        this(token, owner, null);
    }

    /**
     * @param token
     *            Your Bot API token from BotFather.
     * @param owner
     *            Your user ID
     * @param errorListener
     *            Callback for http errors
     */
    public TgBotApi(String token, long owner, ErrorListener errorListener) {

        BASE_URL = "https://api.telegram.org/bot" + token;

        GET_UPDATES = BASE_URL + "/getUpdates?";
        SET_WEBHOOK = BASE_URL + "/setWebhook";
        GET_WEBHOOK_INFO = BASE_URL + "/getWebhookInfo";
        GET_ME = BASE_URL + "/getMe";
        SEND_MESSAGE = BASE_URL + "/sendMessage?";
        FORWARD_MESSAGE = BASE_URL + "/forwardMessage?";
        SEND_PHOTO = BASE_URL + "/sendPhoto";
        SEND_AUDIO = BASE_URL + "/sendAudio";
        SEND_DOCUMENT = BASE_URL + "/sendDocument";
        SEND_STICKER = BASE_URL + "/sendSticker";
        SEND_VIDEO = BASE_URL + "/sendVideo";
        SEND_VOICE = BASE_URL + "/sendVoice";
        SEND_LOCATION = BASE_URL + "/sendLocation?";
        SEND_VENUE = BASE_URL + "/sendVenue?";
        SEND_CONTACT = BASE_URL + "/sendContact?";
        SEND_CHAT_ACTION = BASE_URL + "/sendChatAction?";
        GET_USER_PROFILE_PHOTOS = BASE_URL + "/getUserProfilePhotos?";
        GET_FILE = BASE_URL + "/getFile?";
        GET_FILE_URL = "https://api.telegram.org/file/bot" + token + '/';
        KICK_CHAT_MEMBER = BASE_URL + "/kickChatMember?";
        UNBAN_CHAT_MEMBER = BASE_URL + "/unbanChatMember?";
        LEAVE_CHAT = BASE_URL + "/leaveChat?";
        GET_CHAT = BASE_URL + "/getChat?";
        GET_CHAT_ADMINISTRATORS = BASE_URL + "/getChatAdministrators?";
        GET_CHAT_MEMBERS_COUNT = BASE_URL + "/getChatMembersCount?";
        GET_CHAT_MEMBER = BASE_URL + "/getChatMember?";
        ANSWER_CALLBACK_QUERY = BASE_URL + "/answerCallbackQuery?";
        EDIT_MESSAGE_TEXT = BASE_URL + "/editMessageText?";
        EDIT_MESSAGE_CAPTION = BASE_URL + "/editMessageCaption?";
        EDIT_MESSAGE_REPLY_MARKUP = BASE_URL + "/editMessageReplyMarkup?";
        ANSWER_INLINE_QUERY = BASE_URL + "/answerInlineQuery?";
        SEND_GAME = BASE_URL + "/sendGame?";
        SET_GAME_SCORE = BASE_URL + "/setGameScore?";
        GET_GAME_HIGH_SCORES = BASE_URL + "/getGameHighScores?";

        OWNER = owner;
        this.errorListener = errorListener;
    }

    /**
     * Returns the name of the bot if possible or returns "Unknown".
     */
    public String getBotName() {
        if (botUser == null) {
            try {
                getMe();
            } catch (IOException e) {
                // ignore
            }
        }
        if (botUser == null) {
            return "Unknown";
        }
        return botUser.first_name;
    }

    /**
     * Returns the user id of the bot if possible or returns 0.
     */
    public int getBotId() {
        if (botUser == null) {
            try {
                getMe();
            } catch (IOException e) {
                // ignore
            }
        }
        if (botUser == null) {
            return 0;
        }
        return botUser.id;
    }

    public boolean isOwner(User user) {
        return user.id == OWNER;
    }

    /**
     * Parse the input in the webhook into a pojo of the json data received.
     */
    public Update parseFromWebhook(String json) {
        return GSON.fromJson(json, Update.class);
    }

    /**
     * Parse the input in the webhook into a pojo of the json data received.
     */
    public Update parseFromWebhook(Reader json) {
        return GSON.fromJson(json, Update.class);
    }

    /**
     * Use this method to receive incoming updates using long polling. An Array of Update objects is returned.
     * 
     * Only use this method if you don't use a webhook.
     * 
     * @see <a href="https://core.telegram.org/bots/api#getupdates">Official documentation of getUpdate</a>
     */
    public Update[] getUpdates(int offset, int limit, int timeout) throws IOException {
        return callMethod(GET_UPDATES + "offset=" + offset + "&limit=" + limit + "&timeout=" + timeout, Update[].class);
    }

    /**
     * Note! You can make this manually in the address field of your web browser instead of calling this method.
     * 
     * @see <a href="https://core.telegram.org/bots/api#setwebhook">Official documentation of setWebhook</a>
     */
    public int setWebhook(String url) throws IOException {
        return callMethod(SET_WEBHOOK + "?url=" + url);
    }

    /**
     * Set webhook using a self-signed certificate.
     * 
     * @see <a href="https://core.telegram.org/bots/api#setwebhook">Official documentation of setWebhook</a>
     * @see <a href="https://core.telegram.org/bots/self-signed">Telegram's self sign guide</a>
     */
    public int setWebhook(String url, InputStream certificate) throws IOException {
        FileSender sender = new FileSender(SET_WEBHOOK);
        sender.addFormField("url", url);
        sender.addFilePart("certificate", certificate, "certificate");
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getwebhookinfo">Official documentation of getWebhookInfo</a>
     */
    public WebhookInfo getWebhookInfo() throws IOException {
        return callMethod(GET_WEBHOOK_INFO, WebhookInfo.class);
    }

    /**
     * Returns basic information about the bot in form of a User object.
     * 
     * @see <a href="https://core.telegram.org/bots/api#getme">Official documentation of getMe</a>
     */
    public User getMe() throws IOException {
        User me = callMethod(GET_ME, User.class);
        if (me != null) {
            botUser = me;
        }
        return botUser;
    }

    /**
     * Setting the API to silent will add the disable_notification parameter to all methods supporting that. I thought
     * this would be more convenient than having to set it in every method call. If you don't like it please let me
     * know.
     */
    public void setSilent(boolean value) {
        disableNotification = value;
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(long chatId, String text) throws IOException {
        text = urlEncode(text);
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(chatId);
        command.append("&text=").append(text);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(String channel, String text) throws IOException {
        text = urlEncode(text);
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&text=").append(text);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(long chatId, String text, ParseMode parseMode, boolean disablePreview, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        text = urlEncode(text);
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(chatId);
        command.append("&text=").append(text);
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(String channel, String text, ParseMode parseMode, boolean disablePreview) throws IOException {
        text = urlEncode(text);
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&text=").append(text);
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        return callMethod(command.toString());
    }

    /**
     * Helper method for sending a simple reply. Can also be done with sendMessage() above.
     * 
     * @param message
     *            message to reply to
     * @param text
     *            reply text
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendReply(Message message, String text) throws IOException {
        return sendMessage(message.chat.id, text, null, false, message.message_id, null);
    }

    /**
     * Helper method for sending a simple reply. Can also be done with sendMessage() above.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendReply(Message message, String text, ParseMode parseMode, boolean disablePreview,
            ReplyMarkup replyMarkup) throws IOException {
        return sendMessage(message.chat.id, text, parseMode, disablePreview, message.message_id, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(long chatId, long fromChatId, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(chatId);
        command.append("&from_chat_id=").append(fromChatId);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(long chatId, String fromChannel, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(chatId);
        command.append("&from_chat_id=").append(fromChannel);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, long fromChatId, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&from_chat_id=").append(fromChatId);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, String fromChannel, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&from_chat_id=").append(fromChannel);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(long chatId, Message message) throws IOException {
        return forwardMessage(chatId, message.chat.id, message.message_id);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, Message message) throws IOException {
        return forwardMessage(channel, message.chat.id, message.message_id);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public int sendPhoto(long chatId, String photo, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendPhoto(String.valueOf(chatId), photo, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public int sendPhoto(String channel, String photo, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_PHOTO).append('?');
        command.append("chat_id=").append(channel);
        command.append("&photo=").append(photo);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public int sendPhoto(long chatId, InputStream photo, String filename, String caption, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendPhoto(String.valueOf(chatId), photo, filename, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public int sendPhoto(String channel, InputStream photo, String filename, String caption, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        FileSender sender = new FileSender(SEND_PHOTO);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("photo", photo, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(long chatId, String audio, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendAudio(String.valueOf(chatId), audio, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(String channel, String audio, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_AUDIO).append('?');
        command.append("chat_id=").append(channel);
        command.append("&audio=").append(audio);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(long chatId, InputStream audio, String caption, String filename, int duration,
            String performer, String title,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendAudio(String.valueOf(chatId), audio, caption, filename, duration, performer, title, replyTo,
                replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(String channel, InputStream audio, String caption, String filename, int duration,
            String performer,
            String title, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        FileSender sender = new FileSender(SEND_AUDIO);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("audio", audio, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (performer != null) {
            sender.addFormField("performer", performer);
        }
        if (title != null) {
            sender.addFormField("title", title);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(long chatId, String document, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendDocument(String.valueOf(chatId), document, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(String channel, String document, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_DOCUMENT).append('?');
        command.append("chat_id=").append(channel);
        command.append("&document=").append(document);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(long chatId, InputStream document, String filename, String caption, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendDocument(String.valueOf(chatId), document, filename, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(String channel, InputStream document, String filename, String caption, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        FileSender sender = new FileSender(SEND_DOCUMENT);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("document", document, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendsticker">Official documentation of sendSticker</a>
     */
    public int sendSticker(long chatId, String sticker, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendSticker(String.valueOf(chatId), sticker, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendsticker">Official documentation of sendSticker</a>
     */
    public int sendSticker(String channel, String sticker, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(SEND_STICKER).append('?');
        command.append("chat_id=").append(channel);
        command.append("&sticker=").append(sticker);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendsticker">Official documentation of sendSticker</a>
     */
    public int sendSticker(long chatId, InputStream sticker, String filename, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendSticker(String.valueOf(chatId), sticker, filename, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendsticker">Official documentation of sendSticker</a>
     */
    public int sendSticker(String channel, InputStream sticker, String filename, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        FileSender sender = new FileSender(SEND_STICKER);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("sticker", sticker, filename);
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, String video, String caption) throws IOException {
        return sendVideo(String.valueOf(chatId), video, caption, 0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, String video, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendVideo(String.valueOf(chatId), video, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(String channel, String video, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_VIDEO).append('?');
        command.append("chat_id=").append(channel);
        command.append("&video=").append(video);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, InputStream video, String filename, String caption) throws IOException {
        return sendVideo(String.valueOf(chatId), video, filename, 0, 0, 0, caption, 0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, InputStream video, String filename, int duration, int width, int height,
            String caption, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVideo(String.valueOf(chatId), video, filename, duration, width, height, caption, replyTo,
                replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(String channel, InputStream video, String filename, int duration, int width, int height,
            String caption, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        FileSender sender = new FileSender(SEND_VIDEO);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("video", video, filename);
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (width != 0) {
            sender.addFormField("width", width);
        }
        if (height != 0) {
            sender.addFormField("height", height);
        }
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(long chatId, String voice, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendVoice(String.valueOf(chatId), voice, caption, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(String channel, String voice, String caption, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_VOICE).append('?');
        command.append("chat_id=").append(channel);
        command.append("&voice=").append(voice);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(long chatId, InputStream voice, String caption, String filename, int duration, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVoice(String.valueOf(chatId), voice, caption, filename, duration, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(String channel, InputStream voice, String caption, String filename, int duration, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        FileSender sender = new FileSender(SEND_VOICE);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("voice", voice, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish();

    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendlocation">Official documentation of sendLocation</a>
     */
    public int sendLocation(long chatId, float latitude, float longitude, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendLocation(String.valueOf(chatId), latitude, longitude, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendlocation">Official documentation of sendLocation</a>
     */
    public int sendLocation(String channel, float latitude, float longitude, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(SEND_LOCATION);
        command.append("chat_id=").append(channel);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvenue">Official documentation of sendVenue</a>
     */
    public int sendVenue(long chatId, float latitude, float longitude, String title, String address,
            String foursquare_id, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVenue(String.valueOf(chatId), latitude, longitude, title, address, foursquare_id, replyTo,
                replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvenue">Official documentation of sendVenue</a>
     */
    public int sendVenue(String channel, float latitude, float longitude, String title, String address,
            String foursquare_id, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(SEND_VENUE);
        command.append("chat_id=").append(channel);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        command.append("&title=").append(urlEncode(title));
        command.append("&address=").append(urlEncode(address));
        if (foursquare_id != null) {
            command.append("&foursquare_id=").append(foursquare_id);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendcontact">Official documentation of sendContact</a>
     */
    public int sendContact(long chatId, String phoneNumber, String firstName, String lastName, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendContact(String.valueOf(chatId), phoneNumber, firstName, lastName, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendcontact">Official documentation of sendContact</a>
     */
    public int sendContact(String channel, String phoneNumber, String firstName, String lastName, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(SEND_CONTACT);
        command.append("chat_id=").append(channel);
        command.append("&phone_number=").append(phoneNumber);
        command.append("&first_name=").append(urlEncode(firstName));
        if (lastName != null) {
            command.append("&last_name=").append(urlEncode(lastName));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * Tell the chat user that something is happening on the bot's side.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendchataction">Official documentation of sendChatAction</a>
     */
    public int sendChatAction(long chatId, ChatAction action) throws IOException {
        return sendChatAction(String.valueOf(chatId), action);
    }

    /**
     * Tell channel users that something is happening on the bot's side.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendchataction">Official documentation of sendChatAction</a>
     */
    public int sendChatAction(String channel, ChatAction action) throws IOException {
        StringBuilder command = new StringBuilder(SEND_CHAT_ACTION);
        command.append("chat_id=").append(channel);
        command.append("&action=").append(action.VALUE);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getuserprofilephotos">Official documentation of
     *      getUserProfilePhotos</a>
     */
    public UserProfilePhotos getUserProfilePhotos(int userId) throws IOException {
        return callMethod(GET_USER_PROFILE_PHOTOS + "user_id=" + userId, UserProfilePhotos.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getuserprofilephotos">Official documentation of
     *      getUserProfilePhotos</a>
     */
    public UserProfilePhotos getUserProfilePhotos(int userId, int offset, int limit) throws IOException {
        return callMethod(GET_USER_PROFILE_PHOTOS + "user_id=" + userId + "&offset=" + offset + "&limit=" + limit,
                UserProfilePhotos.class);
    }

    /**
     * Use this method to get basic info about a file and prepare it for downloading. For the moment, bots can download
     * files of up to 20MB in size.
     * 
     * @see <a href="https://core.telegram.org/bots/api#getfile">Official documentation of getFile</a>
     */
    public File getFile(String fileId) throws IOException {
        return callMethod(GET_FILE + "file_id=" + fileId, File.class);
    }

    /**
     * While the getFile() method only returns file info, this method actually downloads the file.
     */
    public byte[] downloadFile(String fileId) throws IOException {
        File file = getFile(fileId);
        return downloadFile(file);
    }

    /**
     * While the getFile() method only returns file info, this method actually downloads the file.
     */
    public byte[] downloadFile(File file) throws IOException {
        String url = GET_FILE_URL + file.file_path;
        HttpURLConnection con = createConnection(url);
        try {
            if (con.getResponseCode() >= 300) {
                handleErrorResponse(con.getResponseCode(), con.getResponseMessage());
                return null;
            }
            InputStream stream = con.getInputStream();

            if (file.file_size > 0) {
                byte[] result = new byte[file.file_size];
                stream.read(result);
                return result;
            }

            return readFully(stream);
        } finally {
            closeInputStream(con);
        }
    }

    /**
     * Helper method for downloading from an arbitrary URL.
     */
    public byte[] downloadFromUrl(String url) throws IOException {
        HttpURLConnection con = createConnection(url);
        try {
            if (con.getResponseCode() >= 300) {
                handleErrorResponse(con.getResponseCode(), con.getResponseMessage());
                return null;
            }
            InputStream stream = con.getInputStream();
            return readFully(stream);
        } finally {
            closeInputStream(con);
        }
    }

    private byte[] readFully(InputStream stream) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(8192);
        int nRead;
        byte[] data = new byte[8192];
        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            os.write(data, 0, nRead);
        }
        return os.toByteArray();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#kickchatmember">Official documentation of kickChatMember</a>
     */
    public int kickChatMember(long chatId, int userId) throws IOException {
        return kickChatMember(String.valueOf(chatId), userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#kickchatmember">Official documentation of kickChatMember</a>
     */
    public int kickChatMember(String channel, int userId) throws IOException {
        return callMethod(KICK_CHAT_MEMBER + "chat_id=" + channel + "&user_id=" + userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#leavechat">Official documentation of leaveChat</a>
     */
    public int leaveChat(long chatId) throws IOException {
        return leaveChat(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#leavechat">Official documentation of leaveChat</a>
     */
    public int leaveChat(String channel) throws IOException {
        return callMethod(LEAVE_CHAT + "chat_id=" + channel);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#unbanchatmember">Official documentation of unbanChatMember</a>
     */
    public int unbanChatMember(long chatId, int userId) throws IOException {
        return unbanChatMember(String.valueOf(chatId), userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#unbanchatmember">Official documentation of unbanChatMember</a>
     */
    public int unbanChatMember(String channel, int userId) throws IOException {
        return callMethod(UNBAN_CHAT_MEMBER + "chat_id=" + channel + "&user_id=" + userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchat">Official documentation of getChat</a>
     */
    public Chat getChat(long chatId) throws IOException {
        return getChat(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchat">Official documentation of getChat</a>
     */
    public Chat getChat(String channel) throws IOException {
        return callMethod(GET_CHAT + "chat_id=" + channel, Chat.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchatadministrators">Official documentation of
     *      getChatAdministrators</a>
     */
    public ChatMember[] getChatAdministrators(long chatId) throws IOException {
        return getChatAdministrators(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchatadministrators">Official documentation of
     *      getChatAdministrators</a>
     */
    public ChatMember[] getChatAdministrators(String channel) throws IOException {
        return callMethod(GET_CHAT_ADMINISTRATORS + "chat_id=" + channel, ChatMember[].class);
    }

    /**
     * Returns the number of members or 0 if an error occurs.
     * 
     * @see <a href="https://core.telegram.org/bots/api#getchatmemberscount">Official documentation of
     *      getChatMembersCount</a>
     */
    public int getChatMembersCount(long chatId) throws IOException {
        return getChatMembersCount(String.valueOf(chatId));
    }

    /**
     * Returns the number of members or 0 if an error occurs.
     * 
     * @see <a href="https://core.telegram.org/bots/api#getchatmemberscount">Official documentation of
     *      getChatMembersCount</a>
     */
    public int getChatMembersCount(String channel) throws IOException {
        Integer result = callMethod(GET_CHAT_MEMBERS_COUNT + "chat_id=" + channel, Integer.class);
        return result != null ? result : 0;
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchatmember">Official documentation of getChatMember</a>
     */
    public ChatMember getChatMember(long chatId, int userId) throws IOException {
        return getChatMember(String.valueOf(chatId), userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchatmember">Official documentation of getChatMember</a>
     */
    public ChatMember getChatMember(String channel, int userId) throws IOException {
        return callMethod(GET_CHAT_MEMBER + "chat_id=" + channel + "&user_id=" + userId, ChatMember.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answercallbackquery">Official documentation of
     *      answerCallbackQuery</a>
     */
    public int answerCallbackQuery(CallbackQuery callbackQuery, String text, boolean showAlert, String url)
            throws IOException {
        return answerCallbackQuery(callbackQuery.id, text, showAlert, url);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answercallbackquery">Official documentation of
     *      answerCallbackQuery</a>
     */
    public int answerCallbackQuery(String callbackQueryId, String text, boolean showAlert, String url)
            throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_CALLBACK_QUERY);
        command.append("callback_query_id=").append(callbackQueryId);
        if (text != null) {
            command.append("&text=").append(urlEncode(text));
        }
        if (showAlert) {
            command.append("&show_alert=true");
        }
        if (url != null) {
            command.append("&url=").append(urlEncode(url));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagetext">Official documentation of editMessageText</a>
     */
    public int editMessageText(long chatId, int messageId, String text, ParseMode parseMode, boolean disablePreview,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        return editMessageText(String.valueOf(chatId), messageId, text, parseMode, disablePreview, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagetext">Official documentation of editMessageText</a>
     */
    public int editMessageText(String channel, int messageId, String text, ParseMode parseMode, boolean disablePreview,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_TEXT);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&text=").append(urlEncode(text));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagetext">Official documentation of editMessageText</a>
     */
    public int editMessageText(String inlineMessageId, String text, ParseMode parseMode, boolean disablePreview,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_TEXT);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&text=").append(urlEncode(text));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }
    
    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagecaption">Official documentation of
     *      editMessageCaption</a>
     */
    public int editMessageCaption(long chatId, int messageId, String caption, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        return editMessageCaption(String.valueOf(chatId), messageId, caption, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagecaption">Official documentation of
     *      editMessageCaption</a>
     */
    public int editMessageCaption(String channel, int messageId, String caption, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_CAPTION);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&caption=").append(urlEncode(caption));
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagecaption">Official documentation of
     *      editMessageCaption</a>
     */
    public int editMessageCaption(String inlineMessageId, String caption, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_CAPTION);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&caption=").append(urlEncode(caption));
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * Edit inline keyboard markup in chat.
     * 
     * @see <a href="https://core.telegram.org/bots/api#editmessagereplymarkup">Official documentation of
     *      editMessageReplyMarkup</a>
     */
    public int editMessageReplyMarkup(long chatId, int messageId, InlineKeyboardMarkup replyMarkup) throws IOException {
        return editMessageReplyMarkup(String.valueOf(chatId), messageId, replyMarkup);
    }

    /**
     * Edit inline keyboard markup in channel.
     * 
     * @see <a href="https://core.telegram.org/bots/api#editmessagereplymarkup">Official documentation of
     *      editMessageReplyMarkup</a>
     */
    public int editMessageReplyMarkup(String channel, int messageId, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_REPLY_MARKUP);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        return callMethod(command.toString());
    }

    /**
     * Edit inline keyboard markup for an inline message.
     * 
     * @see <a href="https://core.telegram.org/bots/api#editmessagereplymarkup">Official documentation of
     *      editMessageReplyMarkup</a>
     */
    public int editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_REPLY_MARKUP);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answerinlinequery">Official documentation of
     *      answerInlineQuery</a>
     * @see <a href="https://core.telegram.org/bots/inline#collecting-feedback">Collecting feedback</a>
     */
    public int answerInlineQuery(String inlineQueryId, InlineQueryResult[] results, boolean isPersonal)
            throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_INLINE_QUERY);
        command.append("inline_query_id=").append(inlineQueryId);
        command.append("&results=").append(urlEncode(GSON.toJson(results)));
        if (isPersonal) {
            command.append("&is_personal=True");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answerinlinequery">Official documentation of
     *      answerInlineQuery</a>
     */
    public int answerInlineQuery(String inlineQueryId, InlineQueryResult[] results, int cacheTime, boolean isPersonal,
            String nextOffset, String switchPmText, String switchPmParameter) throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_INLINE_QUERY);
        command.append("inline_query_id=").append(inlineQueryId);
        command.append("&results=").append(urlEncode(GSON.toJson(results)));
        if (cacheTime >= 0) {
            command.append("&cache_time=").append(cacheTime);
        }
        if (isPersonal) {
            command.append("&is_personal=True");
        }
        if (nextOffset != null) {
            command.append("&next_offset=").append(nextOffset);
        }
        if (switchPmText != null) {
            command.append("&switch_pm_text=").append(switchPmText);
            if (switchPmParameter != null) {
                command.append("&switch_pm_parameter=").append(switchPmParameter);
            }
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendgame">Official documentation of sendGame</a>
     */
    public int sendGame(long chatId, String gameShortName, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendGame(String.valueOf(chatId), gameShortName, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendgame">Official documentation of sendGame</a>
     */
    public int sendGame(String channel, String gameShortName, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(SEND_GAME);
        command.append("chat_id=").append(channel);
        command.append("&game_short_name=").append(gameShortName);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setgamescore">Official documentation of setGameScore</a>
     */
    public int setGameScore(int userId, int score, long chatId, int messageId, boolean editMessage) throws IOException {
        return setGameScore(userId, score, String.valueOf(chatId), messageId, editMessage);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setgamescore">Official documentation of setGameScore</a>
     */
    public int setGameScore(int userId, int score, String channel, int messageId, boolean editMessage)
            throws IOException {
        StringBuilder command = new StringBuilder(SET_GAME_SCORE);
        command.append("user_id=").append(userId);
        command.append("&score=").append(score);
        command.append("&chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        if (editMessage) {
            command.append("&edit_message=True");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setgamescore">Official documentation of setGameScore</a>
     */
    public int setGameScore(int userId, int score, String inlineMessageId, boolean editMessage) throws IOException {
        StringBuilder command = new StringBuilder(SET_GAME_SCORE);
        command.append("user_id=").append(userId);
        command.append("&score=").append(score);
        command.append("&inline_message_id=").append(inlineMessageId);
        if (editMessage) {
            command.append("&edit_message=True");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getgamehighscores">Official documentation of
     *      getGameHighScores</a>
     */
    public GameHighScore[] getGameHighScores(int userId, long chatId, int messageId)
            throws IOException {
        return getGameHighScores(userId, String.valueOf(chatId), messageId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getgamehighscores">Official documentation of
     *      getGameHighScores</a>
     */
    public GameHighScore[] getGameHighScores(int userId, String channel, int messageId)
            throws IOException {
        StringBuilder command = new StringBuilder(GET_GAME_HIGH_SCORES);
        command.append("user_id=").append(userId);
        command.append("&chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString(), GameHighScore[].class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getgamehighscores">Official documentation of
     *      getGameHighScores</a>
     */
    public GameHighScore[] getGameHighScores(int userId, String inlineMessageId) throws IOException {
        StringBuilder command = new StringBuilder(GET_GAME_HIGH_SCORES);
        command.append("user_id=").append(userId);
        command.append("&inline_message_id=").append(inlineMessageId);
        return callMethod(command.toString(), GameHighScore[].class);
    }

    /**
     * Call generic API method when you need the response code, but not the rest of the response data.
     */
    public int callMethod(String url) throws IOException {
        HttpURLConnection con = createConnection(url);
        int responseCode = con.getResponseCode();
        try {
            if (responseCode >= 300) {
                try {
                    InputStream stream = con.getInputStream();
                    try (Reader reader = new InputStreamReader(stream)) {
                        JsonObject response = (JsonObject) PARSER.parse(reader);
                        if (!response.getAsJsonPrimitive("ok").getAsBoolean()) {
                            handleErrorResponse(response);
                        } else {
                            handleErrorResponse(responseCode, con.getResponseMessage());
                        }
                    }
                } catch (Exception e) {
                    handleErrorResponse(responseCode, con.getResponseMessage());
                }
            }
        } finally {
            closeInputStream(con);
        }
        return responseCode;
    }

    /**
     * Calls an API method and returns the json result as a pojo.
     * 
     * @param url
     *            the full URL of the method
     * @param responseClass
     *            the Java class corresponding to the JSON response
     * @return the json result as a pojo
     * @throws IOException
     */
    public <T> T callMethod(String url, Class<T> responseClass) throws IOException {
        HttpURLConnection con = createConnection(url);
        InputStream stream = con.getInputStream();

        // From the documentation: "The response contains a JSON object, which always has a Boolean field ‘ok’ and may
        // have an optional String field ‘description’ with a human-readable description of the result. If ‘ok’ equals
        // true, the request was successful and the result of the query can be found in the ‘result’ field. In case of
        // an unsuccessful request, ‘ok’ equals false and the error is explained in the ‘description’. An Integer
        // ‘error_code’ field is also returned, but its contents are subject to change in the future."
        try (Reader reader = new InputStreamReader(stream)) {
            JsonObject response = (JsonObject) PARSER.parse(reader);
            if (response.getAsJsonPrimitive("ok").getAsBoolean()) {
                JsonElement result = response.get("result");
                return GSON.fromJson(result, responseClass);
            } else {
                handleErrorResponse(response);
                return null;
            }
        } finally {
            closeInputStream(con);
        }
    }

    private void handleErrorResponse(JsonObject response) throws HttpResponseException {
        int errorCode = response.getAsJsonPrimitive("error_code").getAsInt();
        String description = response.getAsJsonPrimitive("description").getAsString();
        JsonElement parameters = response.get("parameters");
        ResponseParameters responseParameters = GSON.fromJson(parameters, ResponseParameters.class);
        handleErrorResponse(errorCode, description, responseParameters);
    }

    private void handleErrorResponse(int errorCode, String description) throws HttpResponseException {
        handleErrorResponse(errorCode, description, null);
    }

    private void handleErrorResponse(int errorCode, String description, ResponseParameters responseParameters)
            throws HttpResponseException {
        if (errorListener != null) {
            errorListener.onError(errorCode, description);
        } else {
            throw new HttpResponseException(errorCode, description, responseParameters);
        }
    }

    /**
     * Helper method for connecting to the Bot API with reasonable timeout values.
     */
    private HttpURLConnection createConnection(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(4000);
        con.setReadTimeout(8000);
        con.connect();
        return con;
    }

    private void closeInputStream(HttpURLConnection con) {
        try {
            // Close to let the connection be reused
            con.getInputStream().close();
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * Send message to the bot owner.
     */
    public void debug(String message) {
        try {
            sendMessage(OWNER, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message and stack trace to the bot owner.
     */
    public void debug(String message, Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();
        if (stackTrace.length() > 800) {
            stackTrace = stackTrace.substring(0, 800);
        }
        try {
            if (message != null) {
                message += "\n" + stackTrace;
            } else {
                message = stackTrace;
            }
            sendMessage(OWNER, message);
        } catch (IOException ioe) {
            e.printStackTrace();
            ioe.printStackTrace();
        }
    }

    /**
     * Send stack trace to the bot owner.
     */
    public void debug(Exception e) {
        debug(null, e);
    }

    private static String urlEncode(String text) {
        try {
            return URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // Can't happen for utf-8
            return null;
        }
    }
}