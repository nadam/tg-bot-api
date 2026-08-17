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
import se.anyro.tgbotapi.types.ChatInviteLink;
import se.anyro.tgbotapi.types.BotCommand;
import se.anyro.tgbotapi.types.BotCommandScope;
import se.anyro.tgbotapi.types.BotDescription;
import se.anyro.tgbotapi.types.BotShortDescription;
import se.anyro.tgbotapi.types.BotName;
import se.anyro.tgbotapi.types.SentWebAppMessage;
import se.anyro.tgbotapi.types.MenuButton;
import se.anyro.tgbotapi.types.ChatAction;
import se.anyro.tgbotapi.types.ChatMember;
import se.anyro.tgbotapi.types.ChatPermissions;
import se.anyro.tgbotapi.types.ChatAdministratorRights;
import se.anyro.tgbotapi.types.Message;
import se.anyro.tgbotapi.types.MessageId;
import se.anyro.tgbotapi.types.ForumTopic;
import se.anyro.tgbotapi.types.ParseMode;
import se.anyro.tgbotapi.types.ResponseParameters;
import se.anyro.tgbotapi.types.Update;
import se.anyro.tgbotapi.types.User;
import se.anyro.tgbotapi.types.WebhookInfo;
import se.anyro.tgbotapi.types.file.File;
import se.anyro.tgbotapi.types.file.InputMedia;
import se.anyro.tgbotapi.types.file.UserProfilePhotos;
import se.anyro.tgbotapi.types.games.GameHighScore;
import se.anyro.tgbotapi.types.inline.CallbackQuery;
import se.anyro.tgbotapi.types.inline.InlineQueryResult;
import se.anyro.tgbotapi.types.inline.InlineQueryResultsButton;
import se.anyro.tgbotapi.types.payments.LabeledPrice;
import se.anyro.tgbotapi.types.payments.ShippingOption;
import se.anyro.tgbotapi.types.poll.Poll;
import se.anyro.tgbotapi.types.reply_markup.InlineKeyboardMarkup;
import se.anyro.tgbotapi.types.reply_markup.ReplyMarkup;
import se.anyro.tgbotapi.types.stickers.MaskPosition;
import se.anyro.tgbotapi.types.stickers.Sticker;
import se.anyro.tgbotapi.types.stickers.InputSticker;
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
    private final String DELETE_WEBHOOK;
    private final String GET_WEBHOOK_INFO;
    private final String GET_ME;
    private final String LOG_OUT;
    private final String CLOSE;
    private final String SEND_MESSAGE;
    private final String FORWARD_MESSAGE;
    private final String COPY_MESSAGE;
    private final String SEND_PHOTO;
    private final String SEND_AUDIO;
    private final String SEND_DOCUMENT;
    private final String SEND_VIDEO;
    private final String SEND_ANIMATION;
    private final String SEND_VOICE;
    private final String SEND_VIDEO_NOTE;
    private final String SEND_MEDIA_GROUP;
    private final String SEND_LOCATION;
    private final String EDIT_MESSAGE_LIVE_LOCATION;
    private final String STOP_MESSAGE_LIVE_LOCATION;
    private final String SEND_VENUE;
    private final String SEND_CONTACT;
    private final String SEND_POLL;
    private final String STOP_POLL;
    private final String SEND_DICE;
    private final String GET_MY_COMMANDS;
    private final String SET_MY_COMMANDS;
    private final String SET_MY_DEFAULT_ADMINISTRATOR_RIGHTS;
    private final String GET_MY_DEFAULT_ADMINISTRATOR_RIGHTS;
    private final String SEND_CHAT_ACTION;
    private final String GET_USER_PROFILE_PHOTOS;
    private final String GET_FILE;
    private final String GET_FILE_URL;
    private final String KICK_CHAT_MEMBER;
    private final String LEAVE_CHAT;
    private final String UNBAN_CHAT_MEMBER;
    private final String RESTRICT_CHAT_MEMBER;
    private final String PROMOTE_CHAT_MEMBER;
    private final String SET_CHAT_ADMINISTRATOR_CUSTOM_TITLE;
    private final String EXPORT_CHAT_INVITE_LINK;
    private final String SET_CHAT_PHOTO;
    private final String DELETE_CHAT_PHOTO;
    private final String SET_CHAT_TITLE;
    private final String SET_CHAT_DESCRIPTION;
    private final String SET_CHAT_PERMISSIONS;
    private final String PIN_CHAT_MESSAGE;
    private final String UNPIN_CHAT_MESSAGE;
    private final String UNPIN_ALL_CHAT_MESSAGES;
    private final String GET_CHAT;
    private final String GET_CHAT_ADMINISTRATORS;
    private final String GET_CHAT_MEMBERS_COUNT;
    private final String GET_CHAT_MEMBER;
    private final String SET_CHAT_STICKER_SET;
    private final String DELETE_CHAT_STICKER_SET;
    private final String ANSWER_CALLBACK_QUERY;
    private final String EDIT_MESSAGE_TEXT;
    private final String EDIT_MESSAGE_CAPTION;
    private final String EDIT_MESSAGE_MEDIA;
    private final String EDIT_MESSAGE_REPLY_MARKUP;
    private final String DELETE_MESSAGE;
    private final String SEND_STICKER;
    private final String GET_STICKER_SET;
    private final String GET_CUSTOM_EMOJI_STICKERS;
    private final String UPLOAD_STICKER_FILE;
    private final String CREATE_NEW_STICKER_SET;
    private final String ADD_STICKER_TO_SET;
    private final String SET_STICKER_POSITION_IN_SET;
    private final String DELETE_STICKER_FROM_SET;
    private final String SET_STICKER_SET_THUMB;

    private final String ANSWER_INLINE_QUERY;
    private final String SEND_INVOICE;
    private final String ANSWER_SHIPPING_QUERY;
    private final String ANSWER_PRE_CHECKOUT_QUERY;
    private final String SEND_GAME;
    private final String SET_GAME_SCORE;
    private final String GET_GAME_HIGH_SCORES;
    private final String CREATE_FORUM_TOPIC;
    private final String EDIT_FORUM_TOPIC;
    private final String CLOSE_FORUM_TOPIC;
    private final String REOPEN_FORUM_TOPIC;
    private final String DELETE_FORUM_TOPIC;
    private final String UNPIN_ALL_FORUM_TOPIC_MESSAGES;
    private final String GET_FORUM_TOPIC_ICON_STICKERS;
    private final String EDIT_GENERAL_FORUM_TOPIC;
    private final String CLOSE_GENERAL_FORUM_TOPIC;
    private final String REOPEN_GENERAL_FORUM_TOPIC;
    private final String HIDE_GENERAL_FORUM_TOPIC;
    private final String UNHIDE_GENERAL_FORUM_TOPIC;

    private final String THUMB_FILENAME = "thumb_filename";

    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    private final long OWNER;

    private static final int READ_TIMEOUT = 8000;

    private boolean disableNotification = false;
    private boolean protectContent = false;

    private ErrorListener errorListener;

    private int lastResponseCode = 0;

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

        GET_UPDATES = BASE_URL + "/getUpdates";
        SET_WEBHOOK = BASE_URL + "/setWebhook";
        DELETE_WEBHOOK = BASE_URL + "/deleteWebhook";
        GET_WEBHOOK_INFO = BASE_URL + "/getWebhookInfo";
        GET_ME = BASE_URL + "/getMe";
        LOG_OUT = BASE_URL + "/logOut";
        CLOSE = BASE_URL + "/close";
        SEND_MESSAGE = BASE_URL + "/sendMessage?";
        FORWARD_MESSAGE = BASE_URL + "/forwardMessage?";
        COPY_MESSAGE = BASE_URL + "/copyMessage?";
        SEND_PHOTO = BASE_URL + "/sendPhoto";
        SEND_AUDIO = BASE_URL + "/sendAudio";
        SEND_DOCUMENT = BASE_URL + "/sendDocument";
        SEND_VIDEO = BASE_URL + "/sendVideo";
        SEND_ANIMATION = BASE_URL + "/sendAnimation";
        SEND_VOICE = BASE_URL + "/sendVoice";
        SEND_VIDEO_NOTE = BASE_URL + "/sendVideoNote";
        SEND_MEDIA_GROUP = BASE_URL + "/sendMediaGroup";
        SEND_LOCATION = BASE_URL + "/sendLocation?";
        EDIT_MESSAGE_LIVE_LOCATION = BASE_URL + "/editMessageLiveLocation?";
        STOP_MESSAGE_LIVE_LOCATION = BASE_URL + "/stopMessageLiveLocation?";
        SEND_VENUE = BASE_URL + "/sendVenue?";
        SEND_CONTACT = BASE_URL + "/sendContact?";
        SEND_POLL = BASE_URL + "/sendPoll?";
        STOP_POLL = BASE_URL + "/stopPoll?";
        SEND_DICE = BASE_URL + "/sendDice?";
        GET_MY_COMMANDS = BASE_URL + "/getMyCommands";
        SET_MY_COMMANDS = BASE_URL + "/setMyCommands?";
        SET_MY_DEFAULT_ADMINISTRATOR_RIGHTS = BASE_URL + "/setMyDefaultAdministratorRights?";
        GET_MY_DEFAULT_ADMINISTRATOR_RIGHTS = BASE_URL + "/getMyDefaultAdministratorRights?";
        SEND_CHAT_ACTION = BASE_URL + "/sendChatAction?";
        GET_USER_PROFILE_PHOTOS = BASE_URL + "/getUserProfilePhotos?";
        GET_FILE = BASE_URL + "/getFile?";
        GET_FILE_URL = "https://api.telegram.org/file/bot" + token + '/';
        KICK_CHAT_MEMBER = BASE_URL + "/kickChatMember?";
        UNBAN_CHAT_MEMBER = BASE_URL + "/unbanChatMember?";
        RESTRICT_CHAT_MEMBER = BASE_URL + "/restrictChatMember?";
        PROMOTE_CHAT_MEMBER = BASE_URL + "/promoteChatMember?";
        SET_CHAT_ADMINISTRATOR_CUSTOM_TITLE = BASE_URL + "/setChatAdministratorCustomTitle?";
        EXPORT_CHAT_INVITE_LINK = BASE_URL + "/exportChatInviteLink?";
        SET_CHAT_PHOTO = BASE_URL + "/setChatPhoto?";
        DELETE_CHAT_PHOTO = BASE_URL + "/deleteChatPhoto?";
        SET_CHAT_TITLE = BASE_URL + "/setChatTitle?";
        SET_CHAT_DESCRIPTION = BASE_URL + "/setChatDescription?";
        SET_CHAT_PERMISSIONS = BASE_URL + "/setChatPermissions?";
        PIN_CHAT_MESSAGE = BASE_URL + "/pinChatMessage?";
        UNPIN_CHAT_MESSAGE = BASE_URL + "/unpinChatMessage?";
        UNPIN_ALL_CHAT_MESSAGES = BASE_URL + "/unpinAllChatMessages?";
        LEAVE_CHAT = BASE_URL + "/leaveChat?";
        GET_CHAT = BASE_URL + "/getChat?";
        GET_CHAT_ADMINISTRATORS = BASE_URL + "/getChatAdministrators?";
        GET_CHAT_MEMBERS_COUNT = BASE_URL + "/getChatMembersCount?";
        GET_CHAT_MEMBER = BASE_URL + "/getChatMember?";
        SET_CHAT_STICKER_SET = BASE_URL + "/setChatStickerSet?";
        DELETE_CHAT_STICKER_SET = BASE_URL + "/deleteChatStickerSet?";
        ANSWER_CALLBACK_QUERY = BASE_URL + "/answerCallbackQuery?";
        EDIT_MESSAGE_TEXT = BASE_URL + "/editMessageText?";
        EDIT_MESSAGE_CAPTION = BASE_URL + "/editMessageCaption?";
        EDIT_MESSAGE_MEDIA = BASE_URL + "/editMessageMedia?";
        EDIT_MESSAGE_REPLY_MARKUP = BASE_URL + "/editMessageReplyMarkup?";
        DELETE_MESSAGE = BASE_URL + "/deleteMessage?";
        SEND_STICKER = BASE_URL + "/sendSticker";
        GET_STICKER_SET = BASE_URL + "/getStickerSet?";
        GET_CUSTOM_EMOJI_STICKERS = BASE_URL + "/getCustomEmojiStickers?";
        UPLOAD_STICKER_FILE = BASE_URL + "/uploadStickerFile";
        CREATE_NEW_STICKER_SET = BASE_URL + "/createNewStickerSet";
        ADD_STICKER_TO_SET = BASE_URL + "/addStickerToSet";
        SET_STICKER_POSITION_IN_SET = BASE_URL + "/setStickerPositionInSet?";
        DELETE_STICKER_FROM_SET = BASE_URL + "/deleteStickerFromSet?";
        SET_STICKER_SET_THUMB = BASE_URL + "/setStickerSetThumb";
        ANSWER_INLINE_QUERY = BASE_URL + "/answerInlineQuery?";
        SEND_INVOICE = BASE_URL + "/sendInvoice?";
        ANSWER_SHIPPING_QUERY = BASE_URL + "/answerShippingQuery?";
        ANSWER_PRE_CHECKOUT_QUERY = BASE_URL + "/answerPreCheckoutQuery?";
        SEND_GAME = BASE_URL + "/sendGame?";
        SET_GAME_SCORE = BASE_URL + "/setGameScore?";
        GET_GAME_HIGH_SCORES = BASE_URL + "/getGameHighScores?";
        CREATE_FORUM_TOPIC = BASE_URL + "/createForumTopic?";
        EDIT_FORUM_TOPIC = BASE_URL + "/editForumTopic?";
        CLOSE_FORUM_TOPIC = BASE_URL + "/closeForumTopic?";
        REOPEN_FORUM_TOPIC = BASE_URL + "/reopenForumTopic?";
        DELETE_FORUM_TOPIC = BASE_URL + "/deleteForumTopic?";
        UNPIN_ALL_FORUM_TOPIC_MESSAGES = BASE_URL + "/unpinAllForumTopicMessages?";
        GET_FORUM_TOPIC_ICON_STICKERS = BASE_URL + "/getForumTopicIconStickers";
        EDIT_GENERAL_FORUM_TOPIC = BASE_URL + "/editGeneralForumTopic?";
        CLOSE_GENERAL_FORUM_TOPIC = BASE_URL + "/closeGeneralForumTopic?";
        REOPEN_GENERAL_FORUM_TOPIC = BASE_URL + "/reopenGeneralForumTopic?";
        HIDE_GENERAL_FORUM_TOPIC = BASE_URL + "/hideGeneralForumTopic?";
        UNHIDE_GENERAL_FORUM_TOPIC = BASE_URL + "/unhideGeneralForumTopic?";

        OWNER = owner;
        this.errorListener = errorListener;
    }

    /**
     * Returns the last HTTP response code returned from Telegram server. Might return the wrong code in a
     * multi-threaded environment.
     */
    public int getLastResponseCode() {
        return lastResponseCode;
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
    public long getBotId() {
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

    public long getOwner() {
        return OWNER;
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
    public Update[] getUpdates(int offset, int limit, int timeout, String[] allowedUpdates) throws IOException {
        StringBuilder command = new StringBuilder(GET_UPDATES).append('?');
        command.append("limit=").append(limit);
        if (offset > 0) {
            command.append("&offset=").append(offset);
        }
        if (timeout > 0) {
            command.append("&timeout=").append(timeout);
        }
        if (allowedUpdates != null) {
            command.append("&allowed_updates=").append(urlEncode(GSON.toJson(allowedUpdates)));
        }
        // Make sure the read timeout is longer than the poll timeout
        int readTimeout = timeout * 1000 + READ_TIMEOUT;
        return callMethod(command.toString(), Update[].class, readTimeout);
    }

    /**
     * Use this method to receive incoming updates using long polling. An Array of Update objects is returned.
     *
     * Only use this method if you don't use a webhook.
     *
     * @see <a href="https://core.telegram.org/bots/api#getupdates">Official documentation of getUpdate</a>
     */
    public Update[] getUpdates(int offset, int limit, int timeout) throws IOException {
        return getUpdates(offset, limit, timeout, null);
    }

    /**
     * Use this method to receive incoming updates using long polling. An Array of Update objects is returned.
     *
     * Only use this method if you don't use a webhook.
     *
     * @see <a href="https://core.telegram.org/bots/api#getupdates">Official documentation of getUpdate</a>
     */
    public Update[] getUpdates() throws IOException {
        return callMethod(GET_UPDATES, Update[].class);
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
    public int setWebhook(String url, InputStream certificate, int maxConnections, String[] allowedUpdates)
            throws IOException {
        return setWebhook(url, certificate, null, maxConnections, allowedUpdates, false, null);
    }

    public int setWebhook(String url, InputStream certificate, String ipAddress, int maxConnections,
            String[] allowedUpdates, boolean dropPendingUpdates) throws IOException {
        return setWebhook(url, certificate, ipAddress, maxConnections, allowedUpdates, dropPendingUpdates, null);
    }

    public int setWebhook(String url, InputStream certificate, String ipAddress, int maxConnections,
            String[] allowedUpdates, boolean dropPendingUpdates, String secretToken) throws IOException {
        FileSender sender = new FileSender(SET_WEBHOOK);
        sender.addFormField("url", url);
        if (certificate != null) {
            sender.addFilePart("certificate", certificate, "certificate");
        }
        if (ipAddress != null) {
            sender.addFormField("ip_address", ipAddress);
        }
        if (maxConnections > 0) {
            sender.addFormField("max_connections", maxConnections);
        }
        if (allowedUpdates != null) {
            sender.addFormField("allowed_updates", urlEncode(GSON.toJson(allowedUpdates)));
        }
        if (dropPendingUpdates) {
            sender.addFormField("drop_pending_updates", "true");
        }
        if (secretToken != null) {
            sender.addFormField("secret_token", secretToken);
        }
        return sender.finish();
    }

    /**
     * Note! You can make this manually in the address field of your web browser instead of calling this method.
     * 
     * @see <a href="https://core.telegram.org/bots/api#deletewebhook">Official documentation of deleteWebhook</a>
     */
    public int deleteWebhook() throws IOException {
        return callMethod(DELETE_WEBHOOK);
    }

    public int deleteWebhook(boolean dropPendingUpdates) throws IOException {
        return callMethod(DELETE_WEBHOOK + "?drop_pending_updates=" + dropPendingUpdates);
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

    public int logOut() throws IOException {
        return callMethod(LOG_OUT);
    }

    public int close() throws IOException {
        return callMethod(CLOSE);
    }

    public ChatInviteLink createChatInviteLink(long chatId, Integer expireDate, Integer memberLimit)
            throws IOException {
        return createChatInviteLink(String.valueOf(chatId), null, expireDate, memberLimit, null);
    }

    public ChatInviteLink createChatInviteLink(String chatId, String name, Integer expireDate, Integer memberLimit,
            Boolean createsJoinRequest) throws IOException {
        String command = BASE_URL + "/createChatInviteLink?chat_id=" + urlEncode(chatId);
        if (name != null) command += "&name=" + urlEncode(name);
        if (expireDate != null) command += "&expire_date=" + expireDate;
        if (memberLimit != null) command += "&member_limit=" + memberLimit;
        if (createsJoinRequest != null) command += "&creates_join_request=" + createsJoinRequest;
        return callMethod(command, ChatInviteLink.class);
    }

    public ChatInviteLink createChatInviteLink(long chatId, String name, Integer expireDate, Integer memberLimit,
            Boolean createsJoinRequest) throws IOException {
        return createChatInviteLink(String.valueOf(chatId), name, expireDate, memberLimit, createsJoinRequest);
    }

    public ChatInviteLink editChatInviteLink(long chatId, String inviteLink, Integer expireDate, Integer memberLimit)
            throws IOException {
        return editChatInviteLink(String.valueOf(chatId), inviteLink, null, expireDate, memberLimit, null);
    }

    public ChatInviteLink editChatInviteLink(String chatId, String inviteLink, String name, Integer expireDate,
            Integer memberLimit, Boolean createsJoinRequest) throws IOException {
        String command = BASE_URL + "/editChatInviteLink?chat_id=" + urlEncode(chatId)
                + "&invite_link=" + urlEncode(inviteLink);
        if (name != null) command += "&name=" + urlEncode(name);
        if (expireDate != null) command += "&expire_date=" + expireDate;
        if (memberLimit != null) command += "&member_limit=" + memberLimit;
        if (createsJoinRequest != null) command += "&creates_join_request=" + createsJoinRequest;
        return callMethod(command, ChatInviteLink.class);
    }

    public ChatInviteLink editChatInviteLink(long chatId, String inviteLink, String name, Integer expireDate,
            Integer memberLimit, Boolean createsJoinRequest) throws IOException {
        return editChatInviteLink(String.valueOf(chatId), inviteLink, name, expireDate, memberLimit,
                createsJoinRequest);
    }

    public ChatInviteLink revokeChatInviteLink(long chatId, String inviteLink) throws IOException {
        return revokeChatInviteLink(String.valueOf(chatId), inviteLink);
    }

    public ChatInviteLink revokeChatInviteLink(String chatId, String inviteLink) throws IOException {
        return callMethod(BASE_URL + "/revokeChatInviteLink?chat_id=" + urlEncode(chatId)
                + "&invite_link=" + urlEncode(inviteLink), ChatInviteLink.class);
    }

    public SentWebAppMessage answerWebAppQuery(String webAppQueryId, String resultJson) throws IOException {
        String command = BASE_URL + "/answerWebAppQuery?web_app_query_id=" + urlEncode(webAppQueryId)
                + "&result=" + urlEncode(resultJson);
        return callMethod(command, SentWebAppMessage.class);
    }

    public SentWebAppMessage answerWebAppQuery(String webAppQueryId, InlineQueryResult result) throws IOException {
        return answerWebAppQuery(webAppQueryId, GSON.toJson(result));
    }

    public int setChatMenuButton(Long chatId, MenuButton menuButton) throws IOException {
        String command = BASE_URL + "/setChatMenuButton?";
        if (chatId != null) command += "chat_id=" + chatId + "&";
        if (menuButton != null) command += "menu_button=" + urlEncode(GSON.toJson(menuButton));
        return callMethod(command);
    }

    public MenuButton getChatMenuButton(Long chatId) throws IOException {
        String command = BASE_URL + "/getChatMenuButton" + (chatId == null ? "" : "?chat_id=" + chatId);
        return callMethod(command, MenuButton.class);
    }

    public int approveChatJoinRequest(long chatId, long userId) throws IOException {
        return approveChatJoinRequest(String.valueOf(chatId), userId);
    }

    public int approveChatJoinRequest(String chatId, long userId) throws IOException {
        return callMethod(BASE_URL + "/approveChatJoinRequest?chat_id=" + urlEncode(chatId) + "&user_id=" + userId);
    }

    public int declineChatJoinRequest(long chatId, long userId) throws IOException {
        return declineChatJoinRequest(String.valueOf(chatId), userId);
    }

    public int declineChatJoinRequest(String chatId, long userId) throws IOException {
        return callMethod(BASE_URL + "/declineChatJoinRequest?chat_id=" + urlEncode(chatId) + "&user_id=" + userId);
    }

    public int banChatSenderChat(long chatId, long senderChatId) throws IOException {
        return banChatSenderChat(String.valueOf(chatId), senderChatId);
    }

    public int banChatSenderChat(String chatId, long senderChatId) throws IOException {
        return callMethod(BASE_URL + "/banChatSenderChat?chat_id=" + urlEncode(chatId)
                + "&sender_chat_id=" + senderChatId);
    }

    public int unbanChatSenderChat(long chatId, long senderChatId) throws IOException {
        return unbanChatSenderChat(String.valueOf(chatId), senderChatId);
    }

    public int unbanChatSenderChat(String chatId, long senderChatId) throws IOException {
        return callMethod(BASE_URL + "/unbanChatSenderChat?chat_id=" + urlEncode(chatId)
                + "&sender_chat_id=" + senderChatId);
    }

    /**
     * Setting the API to silent will add the disable_notification parameter to all methods supporting that. I thought
     * this would be more convenient than having to set it in every method call. If you don't like it please let me
     * know.
     */
    public void setSilent(boolean value) {
        disableNotification = value;
    }

    /** Adds protect_content to all supported outgoing message methods. */
    public void setProtectContent(boolean value) {
        protectContent = value;
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(long chatId, String text) throws IOException {
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(chatId);
        command.append("&text=").append(urlEncode(text));
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(String channel, String text) throws IOException {
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&text=").append(urlEncode(text));
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public Message sendMessage(long chatId, String text, ParseMode parseMode, boolean disablePreview, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendMessage(chatId, text, parseMode, disablePreview, replyTo, replyMarkup, 0);
    }

    public Message sendMessage(long chatId, String text, ParseMode parseMode, boolean disablePreview, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(chatId);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&text=").append(urlEncode(text));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public int sendMessage(String channel, String text, ParseMode parseMode, boolean disablePreview, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendMessage(channel, text, parseMode, disablePreview, replyTo, replyMarkup, 0);
    }

    public int sendMessage(String channel, String text, ParseMode parseMode, boolean disablePreview, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_MESSAGE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&text=").append(urlEncode(text));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disablePreview) {
            command.append("&disable_web_page_preview=True");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
     * Helper method for sending a simple reply. Can also be done with sendMessage() above.
     * 
     * @param message
     *            message to reply to
     * @param text
     *            reply text
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public Message sendReply(Message message, String text) throws IOException {
        return sendMessage(message.chat.id, text, null, false, message.message_id, null);
    }

    /**
     * Helper method for sending a simple reply. Can also be done with sendMessage() above.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">Official documentation of sendMessage</a>
     */
    public Message sendReply(Message message, String text, ParseMode parseMode, boolean disablePreview,
            ReplyMarkup replyMarkup) throws IOException {
        return sendMessage(message.chat.id, text, parseMode, disablePreview, message.message_id, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public Message forwardMessage(long chatId, long fromChatId, int messageId) throws IOException {
        return forwardMessage(chatId, fromChatId, messageId, 0);
    }

    public Message forwardMessage(long chatId, long fromChatId, int messageId, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(chatId);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&from_chat_id=").append(fromChatId);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(long chatId, String fromChannel, int messageId) throws IOException {
        return forwardMessage(chatId, fromChannel, messageId, 0);
    }

    public int forwardMessage(long chatId, String fromChannel, int messageId, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(chatId);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&from_chat_id=").append(fromChannel);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, long fromChatId, int messageId) throws IOException {
        return forwardMessage(channel, fromChatId, messageId, 0);
    }

    public int forwardMessage(String channel, long fromChatId, int messageId, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&from_chat_id=").append(fromChatId);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, String fromChannel, int messageId) throws IOException {
        return forwardMessage(channel, fromChannel, messageId, 0);
    }

    public int forwardMessage(String channel, String fromChannel, int messageId, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(FORWARD_MESSAGE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&from_chat_id=").append(fromChannel);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public Message forwardMessage(long chatId, Message message) throws IOException {
        return forwardMessage(chatId, message.chat.id, message.message_id);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">Official documentation of forwardMessage</a>
     */
    public int forwardMessage(String channel, Message message) throws IOException {
        return forwardMessage(channel, message.chat.id, message.message_id);
    }

    public MessageId copyMessage(String channel, String fromChannel, int messageId, String caption,
            ParseMode parseMode, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return copyMessage(channel, fromChannel, messageId, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public MessageId copyMessage(String channel, String fromChannel, int messageId, String caption,
            ParseMode parseMode, int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(COPY_MESSAGE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&from_chat_id=").append(fromChannel);
        command.append("&message_id=").append(messageId);
        if (caption != null) command.append("&caption=").append(urlEncode(caption));
        if (parseMode != null) command.append("&parse_mode=").append(parseMode.VALUE);
        if (disableNotification) command.append("&disable_notification=true");
        if (protectContent) command.append("&protect_content=true");
        if (replyTo > 0) command.append("&reply_to_message_id=").append(replyTo);
        if (replyMarkup != null) command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        return callMethod(command.toString(), MessageId.class);
    }

    public MessageId copyMessage(long chatId, long fromChatId, int messageId) throws IOException {
        return copyMessage(String.valueOf(chatId), String.valueOf(fromChatId), messageId, null, null, 0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public Message sendPhoto(long chatId, String photo, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendPhoto(String.valueOf(chatId), photo, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendPhoto(long chatId, String photo, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendPhoto(String.valueOf(chatId), photo, caption, parseMode, replyTo, replyMarkup, hasSpoiler);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public Message sendPhoto(String channel, String photo, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendPhoto(channel, photo, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendPhoto(String channel, String photo, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendPhoto(channel, photo, caption, parseMode, replyTo, replyMarkup, hasSpoiler, 0);
    }

    public Message sendPhoto(String channel, String photo, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_PHOTO).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&photo=").append(photo);
        if (hasSpoiler) command.append("&has_spoiler=true");
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public Message sendPhoto(long chatId, InputStream photo, String filename, String caption, ParseMode parseMode,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendPhoto(String.valueOf(chatId), photo, filename, caption, parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendphoto">Official documentation of sendPhoto</a>
     */
    public Message sendPhoto(String channel, InputStream photo, String filename, String caption, ParseMode parseMode,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendPhoto(channel, photo, filename, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public Message sendPhoto(String channel, InputStream photo, String filename, String caption, ParseMode parseMode,
            int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_PHOTO);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("photo", photo, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        if (replyMarkup != null) {
            sender.addFormField("reply_markup", GSON.toJson(replyMarkup));
        }
        return sender.finish(Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public Message sendAudio(long chatId, String audio, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendAudio(String.valueOf(chatId), audio, caption, parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public Message sendAudio(String channel, String audio, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendAudio(channel, audio, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public Message sendAudio(String channel, String audio, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_AUDIO).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&audio=").append(audio);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(long chatId, InputStream audio, String caption, ParseMode parseMode, String filename,
            int duration, String performer, String title, InputStream thumb, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendAudio(String.valueOf(chatId), audio, caption, parseMode, filename, duration, performer, title,
                thumb, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendaudio">Official documentation of sendAudio</a>
     */
    public int sendAudio(String channel, InputStream audio, String caption, ParseMode parseMode, String filename,
            int duration, String performer, String title, InputStream thumb, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendAudio(channel, audio, caption, parseMode, filename, duration, performer, title, thumb, replyTo,
                replyMarkup, 0);
    }

    public int sendAudio(String channel, InputStream audio, String caption, ParseMode parseMode, String filename,
            int duration, String performer, String title, InputStream thumb, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_AUDIO);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("audio", audio, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
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
        if (thumb != null) {
            sender.addFormField("thumb", "attach://" + THUMB_FILENAME);
            sender.addFilePart(THUMB_FILENAME, thumb, THUMB_FILENAME);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
    public Message sendDocument(long chatId, String document, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendDocument(String.valueOf(chatId), document, caption, parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public Message sendDocument(String channel, String document, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendDocument(channel, document, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public Message sendDocument(String channel, String document, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_DOCUMENT).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&document=").append(document);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(long chatId, InputStream document, String filename, InputStream thumb, String caption, ParseMode parseMode,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendDocument(String.valueOf(chatId), document, filename, thumb, caption, parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddocument">Official documentation of sendDocument</a>
     */
    public int sendDocument(String channel, InputStream document, String filename, InputStream thumb, String caption,
            ParseMode parseMode,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendDocument(channel, document, filename, thumb, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public int sendDocument(String channel, InputStream document, String filename, InputStream thumb, String caption,
            ParseMode parseMode, int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_DOCUMENT);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("document", document, filename);
        if (thumb != null) {
            sender.addFormField("thumb", "attach://" + THUMB_FILENAME);
            sender.addFilePart(THUMB_FILENAME, thumb, THUMB_FILENAME);
        }
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
    public Message sendVideo(long chatId, String video, String caption, ParseMode parseMode) throws IOException {
        return sendVideo(String.valueOf(chatId), video, caption, parseMode, 0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public Message sendVideo(long chatId, String video, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVideo(String.valueOf(chatId), video, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendVideo(long chatId, String video, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendVideo(String.valueOf(chatId), video, caption, parseMode, replyTo, replyMarkup, hasSpoiler);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public Message sendVideo(String channel, String video, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVideo(channel, video, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendVideo(String channel, String video, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendVideo(channel, video, caption, parseMode, replyTo, replyMarkup, hasSpoiler, 0);
    }

    public Message sendVideo(String channel, String video, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_VIDEO).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&video=").append(video);
        if (hasSpoiler) command.append("&has_spoiler=true");
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, InputStream video, String filename, String caption, ParseMode parseMode,
            boolean supportsStreaming) throws IOException {
        return sendVideo(String.valueOf(chatId), video, filename, 0, 0, 0, null, caption, parseMode, supportsStreaming,
                0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(long chatId, InputStream video, String filename, int duration, int width, int height,
            InputStream thumb, String caption, ParseMode parseMode, boolean supportsStreaming, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVideo(String.valueOf(chatId), video, filename, duration, width, height, thumb, caption, parseMode,
                supportsStreaming, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideo">Official documentation of sendVideo</a>
     */
    public int sendVideo(String channel, InputStream video, String filename, int duration, int width, int height,
            InputStream thumb, String caption, ParseMode parseMode, boolean supportsStreaming, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVideo(channel, video, filename, duration, width, height, thumb, caption, parseMode,
                supportsStreaming, replyTo, replyMarkup, 0);
    }

    public int sendVideo(String channel, InputStream video, String filename, int duration, int width, int height,
            InputStream thumb, String caption, ParseMode parseMode, boolean supportsStreaming, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_VIDEO);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
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
        if (thumb != null) {
            sender.addFormField("thumb", "attach://" + THUMB_FILENAME);
            sender.addFilePart(THUMB_FILENAME, thumb, THUMB_FILENAME);
        }
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
        }
        if (supportsStreaming) {
            sender.addFormField("supports_streaming", "true");
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
     * @see <a href="https://core.telegram.org/bots/api#sendanimation">Official documentation of sendAnimation</a>
     */
    public Message sendAnimation(long chatId, String animation, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendAnimation(String.valueOf(chatId), animation, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendAnimation(long chatId, String animation, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendAnimation(String.valueOf(chatId), animation, caption, parseMode, replyTo, replyMarkup, hasSpoiler);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendanimation">Official documentation of sendAnimation</a>
     */
    public Message sendAnimation(String channel, String animation, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendAnimation(channel, animation, caption, parseMode, replyTo, replyMarkup, false);
    }

    public Message sendAnimation(String channel, String animation, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler) throws IOException {
        return sendAnimation(channel, animation, caption, parseMode, replyTo, replyMarkup, hasSpoiler, 0);
    }

    public Message sendAnimation(String channel, String animation, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, boolean hasSpoiler, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_ANIMATION).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&animation=").append(animation);
        if (hasSpoiler) command.append("&has_spoiler=true");
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendanimation">Official documentation of sendAnimation</a>
     */
    public int sendAnimation(long chatId, InputStream animation, String filename, int duration, int width, int height,
            InputStream thumb, String caption, ParseMode parseMode, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendAnimation(String.valueOf(chatId), animation, filename, duration, width, height, thumb, caption,
                parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendanimation">Official documentation of sendAnimation</a>
     */
    public int sendAnimation(String channel, InputStream animation, String filename, int duration, int width,
            int height, InputStream thumb, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendAnimation(channel, animation, filename, duration, width, height, thumb, caption, parseMode,
                replyTo, replyMarkup, 0);
    }

    public int sendAnimation(String channel, InputStream animation, String filename, int duration, int width,
            int height, InputStream thumb, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_ANIMATION);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("animation", animation, filename);
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (width != 0) {
            sender.addFormField("width", width);
        }
        if (height != 0) {
            sender.addFormField("height", height);
        }
        if (thumb != null) {
            sender.addFormField("thumb", "attach://" + THUMB_FILENAME);
            sender.addFilePart(THUMB_FILENAME, thumb, THUMB_FILENAME);
        }
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
    public Message sendVoice(long chatId, String voice, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVoice(String.valueOf(chatId), voice, caption, parseMode, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public Message sendVoice(String channel, String voice, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVoice(channel, voice, caption, parseMode, replyTo, replyMarkup, 0);
    }

    public Message sendVoice(String channel, String voice, String caption, ParseMode parseMode, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_VOICE).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&voice=").append(voice);
        if (caption != null) {
            command.append("&caption=").append(urlEncode(caption));
        }
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(long chatId, InputStream voice, String caption, ParseMode parseMode, String filename,
            int duration, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVoice(String.valueOf(chatId), voice, caption, parseMode, filename, duration, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvoice">Official documentation of sendVoice</a>
     */
    public int sendVoice(String channel, InputStream voice, String caption, ParseMode parseMode, String filename,
            int duration, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVoice(channel, voice, caption, parseMode, filename, duration, replyTo, replyMarkup, 0);
    }

    public int sendVoice(String channel, InputStream voice, String caption, ParseMode parseMode, String filename,
            int duration, int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_VOICE);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("voice", voice, filename);
        if (caption != null) {
            sender.addFormField("caption", caption);
        }
        if (parseMode != null) {
            sender.addFormField("parse_mode", parseMode.VALUE);
        }
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
     * @see <a href="https://core.telegram.org/bots/api#sendvideonote">Official documentation of sendVideoNote</a>
     */
    public int sendVideoNote(long chatId, String videoNote, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVideoNote(String.valueOf(chatId), videoNote, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideonote">Official documentation of sendVideoNote</a>
     */
    public int sendVideoNote(String channel, String videoNote, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVideoNote(channel, videoNote, replyTo, replyMarkup, 0);
    }

    public int sendVideoNote(String channel, String videoNote, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_VIDEO_NOTE).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&video_note=").append(videoNote);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
     * @see <a href="https://core.telegram.org/bots/api#sendvideonote">Official documentation of sendVideoNote</a>
     */
    public int sendVideoNote(long chatId, InputStream videoNote, String filename, int duration, int length,
            InputStream thumb, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVideoNote(String.valueOf(chatId), videoNote, filename, duration, length, thumb, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvideonote">Official documentation of sendVideoNote</a>
     */
    public int sendVideoNote(String channel, InputStream videoNote, String filename, int duration, int length,
            InputStream thumb, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVideoNote(channel, videoNote, filename, duration, length, thumb, replyTo, replyMarkup, 0);
    }

    public int sendVideoNote(String channel, InputStream videoNote, String filename, int duration, int length,
            InputStream thumb, int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_VIDEO_NOTE);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("video_note", videoNote, filename);
        if (duration != 0) {
            sender.addFormField("duration", duration);
        }
        if (length > 0) {
            sender.addFormField("length", length);
        }
        if (thumb != null) {
            sender.addFormField("thumb", "attach://" + THUMB_FILENAME);
            sender.addFilePart(THUMB_FILENAME, thumb, THUMB_FILENAME);
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
     * @see <a href="https://core.telegram.org/bots/api#sendmediagroup">Official documentation of sendMediaGroup</a>
     */
    public int sendMediaGroup(long chatId, InputMedia[] media, int replyTo) throws IOException {
        return sendMediaGroup(String.valueOf(chatId), media, replyTo);
    }

    /**
     * Note! Media must include 2-10 items and must only be photos or videos.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendmediagroup">Official documentation of sendMediaGroup</a>
     */
    public int sendMediaGroup(String channel, InputMedia[] media, int replyTo) throws IOException {
        return sendMediaGroup(channel, media, replyTo, 0);
    }

    public int sendMediaGroup(String channel, InputMedia[] media, int replyTo, int messageThreadId)
            throws IOException {
        FileSender sender = new FileSender(SEND_MEDIA_GROUP);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFormField("media", GSON.toJson(media));
        for (InputMedia im : media) {
            if (im.mediaStream != null) {
                sender.addFilePart(im.filename, im.mediaStream, im.filename);
            }
            if (im.thumbStream != null) {
                sender.addFilePart(im.getThumbFilename(), im.thumbStream, im.getThumbFilename());
            }
        }
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
        }
        if (replyTo > 0) {
            sender.addFormField("reply_to_message_id", replyTo);
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendlocation">Official documentation of sendLocation</a>
     */
    public int sendLocation(long chatId, float latitude, float longitude, int livePeriod, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendLocation(String.valueOf(chatId), latitude, longitude, 0, livePeriod, 0, 0, replyTo, replyMarkup);
    }

    public int sendLocation(long chatId, float latitude, float longitude, float horizontalAccuracy, int livePeriod,
            int heading, int proximityAlertRadius, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendLocation(String.valueOf(chatId), latitude, longitude, horizontalAccuracy, livePeriod, heading,
                proximityAlertRadius, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendlocation">Official documentation of sendLocation</a>
     */
    public int sendLocation(String channel, float latitude, float longitude, int livePeriod, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendLocation(channel, latitude, longitude, 0, livePeriod, 0, 0, replyTo, replyMarkup);
    }

    public int sendLocation(String channel, float latitude, float longitude, float horizontalAccuracy, int livePeriod,
            int heading, int proximityAlertRadius, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendLocation(channel, latitude, longitude, horizontalAccuracy, livePeriod, heading,
                proximityAlertRadius, replyTo, replyMarkup, 0);
    }

    public int sendLocation(String channel, float latitude, float longitude, float horizontalAccuracy, int livePeriod,
            int heading, int proximityAlertRadius, int replyTo, ReplyMarkup replyMarkup, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_LOCATION);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        if (horizontalAccuracy > 0) {
            command.append("&horizontal_accuracy=").append(horizontalAccuracy);
        }
        if (livePeriod > 0) {
            command.append("&live_period=").append(livePeriod);
        }
        if (heading > 0) {
            command.append("&heading=").append(heading);
        }
        if (proximityAlertRadius > 0) {
            command.append("&proximity_alert_radius=").append(proximityAlertRadius);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
     * @see <a href="https://core.telegram.org/bots/api#editmessagelivelocation">Official documentation of
     *      editMessageLiveLocation</a>
     */
    public int editMessageLiveLocation(long chatId, int messageId, float latitude, float longitude,
            ReplyMarkup replyMarkup) throws IOException {
        return editMessageLiveLocation(String.valueOf(chatId), messageId, latitude, longitude, 0, 0, 0, replyMarkup);
    }

    public int editMessageLiveLocation(long chatId, int messageId, float latitude, float longitude,
            float horizontalAccuracy, int heading, int proximityAlertRadius, ReplyMarkup replyMarkup)
            throws IOException {
        return editMessageLiveLocation(String.valueOf(chatId), messageId, latitude, longitude, horizontalAccuracy,
                heading, proximityAlertRadius, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagelivelocation">Official documentation of
     *      editMessageLiveLocation</a>
     */
    public int editMessageLiveLocation(String channel, int messageId, float latitude, float longitude,
            ReplyMarkup replyMarkup) throws IOException {
        return editMessageLiveLocation(channel, messageId, latitude, longitude, 0, 0, 0, replyMarkup);
    }

    public int editMessageLiveLocation(String channel, int messageId, float latitude, float longitude,
            float horizontalAccuracy, int heading, int proximityAlertRadius, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_LIVE_LOCATION);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        appendLiveLocationParameters(command, horizontalAccuracy, heading, proximityAlertRadius);
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagelivelocation">Official documentation of
     *      editMessageLiveLocation</a>
     */
    public int editMessageLiveLocation(String inlineMessageId, float latitude, float longitude, ReplyMarkup replyMarkup)
            throws IOException {
        return editMessageLiveLocation(inlineMessageId, latitude, longitude, 0, 0, 0, replyMarkup);
    }

    public int editMessageLiveLocation(String inlineMessageId, float latitude, float longitude,
            float horizontalAccuracy, int heading, int proximityAlertRadius, ReplyMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_LIVE_LOCATION);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        appendLiveLocationParameters(command, horizontalAccuracy, heading, proximityAlertRadius);
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    private void appendLiveLocationParameters(StringBuilder command, float horizontalAccuracy, int heading,
            int proximityAlertRadius) {
        if (horizontalAccuracy > 0) {
            command.append("&horizontal_accuracy=").append(horizontalAccuracy);
        }
        if (heading > 0) {
            command.append("&heading=").append(heading);
        }
        if (proximityAlertRadius > 0) {
            command.append("&proximity_alert_radius=").append(proximityAlertRadius);
        }
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#stopmessagelivelocation">Official documentation of
     *      stopMessageLiveLocation</a>
     */
    public int stopMessageLiveLocation(long chatId, int messageId, ReplyMarkup replyMarkup) throws IOException {
        return stopMessageLiveLocation(String.valueOf(chatId), messageId, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#stopmessagelivelocation">Official documentation of
     *      stopMessageLiveLocation</a>
     */
    public int stopMessageLiveLocation(String channel, int messageId, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(STOP_MESSAGE_LIVE_LOCATION);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#stopmessagelivelocation">Official documentation of
     *      stopMessageLiveLocation</a>
     */
    public int stopMessageLiveLocation(String inlineMessageId, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(STOP_MESSAGE_LIVE_LOCATION);
        command.append("inline_message_id=").append(inlineMessageId);
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(long chatId, String question, String[] options, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendPoll(String.valueOf(chatId), question, options, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(String channel, String question, String[] options, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendPoll(channel, question, options, replyTo, replyMarkup, 0);
    }

    public Message sendPoll(String channel, String question, String[] options, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_POLL);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&question=").append(urlEncode(question));
        command.append("&options=").append(urlEncode(GSON.toJson(options)));
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(long chatId, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, boolean isClosed, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendPoll(String.valueOf(chatId), question, options, isAnonymous, type, allowsMultipleAnswers,
                correctOptionId, isClosed, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(String channel, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, boolean isClosed, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendPoll(channel, question, options, isAnonymous, type, allowsMultipleAnswers, correctOptionId,
                isClosed, replyTo, replyMarkup, 0);
    }

    public Message sendPoll(String channel, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, boolean isClosed, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_POLL);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&question=").append(urlEncode(question));
        command.append("&options=").append(urlEncode(GSON.toJson(options)));
        command.append("&is_anonymous=").append(isAnonymous);
        if (type != null) {
            command.append("&type=").append(urlEncode(type));
        }
        if (allowsMultipleAnswers) {
            command.append("&allows_multiple_answers=true");
        }
        if (correctOptionId != null) {
            command.append("&correct_option_id=").append(correctOptionId);
        }
        if (isClosed) {
            command.append("&is_closed=true");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(long chatId, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, String explanation, ParseMode explanationParseMode,
            Integer openPeriod, Integer closeDate, boolean isClosed, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendPoll(String.valueOf(chatId), question, options, isAnonymous, type, allowsMultipleAnswers,
                correctOptionId, explanation, explanationParseMode, openPeriod, closeDate, isClosed, replyTo,
                replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendpoll">Official documentation of sendPoll</a>
     */
    public Message sendPoll(String channel, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, String explanation, ParseMode explanationParseMode,
            Integer openPeriod, Integer closeDate, boolean isClosed, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendPoll(channel, question, options, isAnonymous, type, allowsMultipleAnswers, correctOptionId,
                explanation, explanationParseMode, openPeriod, closeDate, isClosed, replyTo, replyMarkup, 0);
    }

    public Message sendPoll(String channel, String question, String[] options, boolean isAnonymous, String type,
            boolean allowsMultipleAnswers, Integer correctOptionId, String explanation, ParseMode explanationParseMode,
            Integer openPeriod, Integer closeDate, boolean isClosed, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_POLL);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&question=").append(urlEncode(question));
        command.append("&options=").append(urlEncode(GSON.toJson(options)));
        command.append("&is_anonymous=").append(isAnonymous);
        if (type != null) {
            command.append("&type=").append(urlEncode(type));
        }
        if (allowsMultipleAnswers) {
            command.append("&allows_multiple_answers=true");
        }
        if (correctOptionId != null) {
            command.append("&correct_option_id=").append(correctOptionId);
        }
        if (explanation != null) {
            command.append("&explanation=").append(urlEncode(explanation));
        }
        if (explanationParseMode != null) {
            command.append("&explanation_parse_mode=").append(explanationParseMode.VALUE);
        }
        if (openPeriod != null) {
            command.append("&open_period=").append(openPeriod);
        }
        if (closeDate != null) {
            command.append("&close_date=").append(closeDate);
        }
        if (isClosed) {
            command.append("&is_closed=true");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#stoppoll">Official documentation of stopPoll</a>
     */
    public Poll stopPoll(long chatId, int messageId, ReplyMarkup replyMarkup) throws IOException {
        return stopPoll(String.valueOf(chatId), messageId, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#stoppoll">Official documentation of stopPoll</a>
     */
    public Poll stopPoll(String channel, int messageId, ReplyMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(STOP_POLL);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Poll.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddice">Official documentation of sendDice</a>
     */
    public Message sendDice(long chatId, String emoji, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendDice(String.valueOf(chatId), emoji, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#senddice">Official documentation of sendDice</a>
     */
    public Message sendDice(String channel, String emoji, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendDice(channel, emoji, replyTo, replyMarkup, 0);
    }

    public Message sendDice(String channel, String emoji, int replyTo, ReplyMarkup replyMarkup, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_DICE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        if (emoji != null) {
            command.append("&emoji=").append(urlEncode(emoji));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
        }
        if (replyTo > 0) {
            command.append("&reply_to_message_id=").append(replyTo);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString(), Message.class);
    }

    public BotCommand[] getMyCommands() throws IOException {
        return callMethod(GET_MY_COMMANDS, BotCommand[].class);
    }

    public int setMyCommands(BotCommand[] commands) throws IOException {
        return callMethod(SET_MY_COMMANDS + "commands=" + urlEncode(GSON.toJson(commands)));
    }

    public int setMyCommands(BotCommand[] commands, BotCommandScope scope, String languageCode) throws IOException {
        String command = SET_MY_COMMANDS + "commands=" + urlEncode(GSON.toJson(commands));
        if (scope != null) command += "&scope=" + urlEncode(GSON.toJson(scope));
        if (languageCode != null) command += "&language_code=" + urlEncode(languageCode);
        return callMethod(command);
    }

    public BotCommand[] getMyCommands(BotCommandScope scope, String languageCode) throws IOException {
        String command = GET_MY_COMMANDS + "?";
        if (scope != null) command += "scope=" + urlEncode(GSON.toJson(scope)) + "&";
        if (languageCode != null) command += "language_code=" + urlEncode(languageCode);
        return callMethod(command, BotCommand[].class);
    }

    public int setMyDefaultAdministratorRights(ChatAdministratorRights rights) throws IOException {
        return setMyDefaultAdministratorRights(rights, false);
    }

    public int setMyDefaultAdministratorRights(ChatAdministratorRights rights, boolean forChannels)
            throws IOException {
        StringBuilder command = new StringBuilder(SET_MY_DEFAULT_ADMINISTRATOR_RIGHTS);
        if (rights != null) command.append("rights=").append(urlEncode(GSON.toJson(rights)));
        if (forChannels) command.append(rights == null ? "for_channels=true" : "&for_channels=true");
        return callMethod(command.toString());
    }

    public ChatAdministratorRights getMyDefaultAdministratorRights() throws IOException {
        return getMyDefaultAdministratorRights(false);
    }

    public ChatAdministratorRights getMyDefaultAdministratorRights(boolean forChannels) throws IOException {
        String command = GET_MY_DEFAULT_ADMINISTRATOR_RIGHTS + (forChannels ? "for_channels=true" : "");
        return callMethod(command, ChatAdministratorRights.class);
    }

    public int deleteMyCommands() throws IOException {
        return deleteMyCommands(null, null);
    }

    public int deleteMyCommands(BotCommandScope scope, String languageCode) throws IOException {
        String command = BASE_URL + "/deleteMyCommands?";
        if (scope != null) command += "scope=" + urlEncode(GSON.toJson(scope)) + "&";
        if (languageCode != null) command += "language_code=" + urlEncode(languageCode);
        return callMethod(command);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvenue">Official documentation of sendVenue</a>
     */
    public int sendVenue(long chatId, float latitude, float longitude, String title, String address,
            String foursquareId, String foursquareType, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVenue(String.valueOf(chatId), latitude, longitude, title, address, foursquareId, foursquareType,
                replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendvenue">Official documentation of sendVenue</a>
     */
    public int sendVenue(String channel, float latitude, float longitude, String title, String address,
            String foursquareId, String foursquareType, int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendVenue(channel, latitude, longitude, title, address, foursquareId, foursquareType, null, null,
                replyTo, replyMarkup);
    }

    public int sendVenue(String channel, float latitude, float longitude, String title, String address,
            String foursquareId, String foursquareType, String googlePlaceId, String googlePlaceType, int replyTo,
            ReplyMarkup replyMarkup) throws IOException {
        return sendVenue(channel, latitude, longitude, title, address, foursquareId, foursquareType, googlePlaceId,
                googlePlaceType, replyTo, replyMarkup, 0);
    }

    public int sendVenue(String channel, float latitude, float longitude, String title, String address,
            String foursquareId, String foursquareType, String googlePlaceId, String googlePlaceType, int replyTo,
            ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_VENUE);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&latitude=").append(latitude);
        command.append("&longitude=").append(longitude);
        command.append("&title=").append(urlEncode(title));
        command.append("&address=").append(urlEncode(address));
        if (foursquareId != null) {
            command.append("&foursquare_id=").append(foursquareId);
        }
        if (foursquareType != null) {
            command.append("&foursquare_type=").append(foursquareType);
        }
        if (googlePlaceId != null) {
            command.append("&google_place_id=").append(googlePlaceId);
        }
        if (googlePlaceType != null) {
            command.append("&google_place_type=").append(googlePlaceType);
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
    public int sendContact(long chatId, String phoneNumber, String firstName, String lastName, String vcard,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendContact(String.valueOf(chatId), phoneNumber, firstName, lastName, vcard, replyTo, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendcontact">Official documentation of sendContact</a>
     */
    public int sendContact(String channel, String phoneNumber, String firstName, String lastName, String vcard,
            int replyTo, ReplyMarkup replyMarkup) throws IOException {
        return sendContact(channel, phoneNumber, firstName, lastName, vcard, replyTo, replyMarkup, 0);
    }

    public int sendContact(String channel, String phoneNumber, String firstName, String lastName, String vcard,
            int replyTo, ReplyMarkup replyMarkup, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_CONTACT);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&phone_number=").append(phoneNumber);
        command.append("&first_name=").append(urlEncode(firstName));
        if (lastName != null) {
            command.append("&last_name=").append(urlEncode(lastName));
        }
        if (vcard != null) {
            command.append("&vcard=").append(urlEncode(vcard));
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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

    public int sendChatAction(long chatId, ChatAction action, int messageThreadId) throws IOException {
        return sendChatAction(String.valueOf(chatId), action, messageThreadId);
    }

    /**
     * Tell channel users that something is happening on the bot's side.
     * 
     * @see <a href="https://core.telegram.org/bots/api#sendchataction">Official documentation of sendChatAction</a>
     */
    public int sendChatAction(String channel, ChatAction action) throws IOException {
        return sendChatAction(channel, action, 0);
    }

    public int sendChatAction(String channel, ChatAction action, int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_CHAT_ACTION);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&action=").append(action.VALUE);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getuserprofilephotos">Official documentation of
     *      getUserProfilePhotos</a>
     */
    public UserProfilePhotos getUserProfilePhotos(long userId) throws IOException {
        return callMethod(GET_USER_PROFILE_PHOTOS + "user_id=" + userId, UserProfilePhotos.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getuserprofilephotos">Official documentation of
     *      getUserProfilePhotos</a>
     */
    public UserProfilePhotos getUserProfilePhotos(long userId, int offset, int limit) throws IOException {
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
     * Helper method for creating the file URL from a File object.
     */
    public String getFileUrl(File file) {
        return GET_FILE_URL + file.file_path;
    }

    /**
     * Helper method for creating the file URL from fileId.
     */
    public String getFileUrl(String fileId) throws IOException {
        File file = getFile(fileId);
        return getFileUrl(file);
    }

    /**
     * While the getFile() method only returns file info, this method actually downloads the file.
     */
    public byte[] downloadFile(File file) throws IOException {
        String url = GET_FILE_URL + file.file_path;
        HttpURLConnection con = createConnection(url);
        try {
            int responseCode = lastResponseCode = con.getResponseCode();
            if (responseCode >= 300) {
                handleErrorResponse(responseCode, con.getResponseMessage());
                return null;
            }
            InputStream stream = con.getInputStream();

            if (file.file_size > 0 && file.file_size <= Integer.MAX_VALUE) {
                byte[] result = new byte[(int) file.file_size];
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
            int responseCode = lastResponseCode = con.getResponseCode();
            if (responseCode >= 300) {
                handleErrorResponse(responseCode, con.getResponseMessage());
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
    public int kickChatMember(long chatId, long userId) throws IOException {
        return kickChatMember(String.valueOf(chatId), userId, 0);
    }

    /** Bot API 5.2 name for kickChatMember. */
    public int banChatMember(long chatId, long userId) throws IOException {
        return kickChatMember(chatId, userId);
    }

    public int banChatMember(String chatId, long userId) throws IOException {
        return banChatMember(chatId, userId, 0, null);
    }

    public int banChatMember(long chatId, long userId, int untilDate, Boolean revokeMessages) throws IOException {
        return banChatMember(String.valueOf(chatId), userId, untilDate, revokeMessages);
    }

    public int banChatMember(String chatId, long userId, int untilDate, Boolean revokeMessages) throws IOException {
        StringBuilder command = new StringBuilder(BASE_URL + "/banChatMember?");
        command.append("chat_id=").append(urlEncode(chatId));
        command.append("&user_id=").append(userId);
        if (untilDate > 0) command.append("&until_date=").append(untilDate);
        if (revokeMessages != null) command.append("&revoke_messages=").append(revokeMessages);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#kickchatmember">Official documentation of kickChatMember</a>
     */
    public int kickChatMember(long chatId, long userId, int untilDate) throws IOException {
        return kickChatMember(String.valueOf(chatId), userId, untilDate);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#kickchatmember">Official documentation of kickChatMember</a>
     */
    public int kickChatMember(String channel, long userId, int untilDate) throws IOException {
        StringBuilder command = new StringBuilder(KICK_CHAT_MEMBER);
        command.append("chat_id=").append(channel);
        command.append("&user_id=").append(userId);
        if (untilDate > 0) {
            command.append("&until_date=").append(untilDate);
        }
        return callMethod(command.toString());
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
    public int unbanChatMember(long chatId, long userId) throws IOException {
        return unbanChatMember(String.valueOf(chatId), userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#unbanchatmember">Official documentation of unbanChatMember</a>
     */
    public int unbanChatMember(String channel, long userId) throws IOException {
        return unbanChatMember(channel, userId, false);
    }

    public int unbanChatMember(String channel, long userId, boolean onlyIfBanned) throws IOException {
        return callMethod(UNBAN_CHAT_MEMBER + "chat_id=" + channel + "&user_id=" + userId
                + "&only_if_banned=" + onlyIfBanned);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#restrictchatmember">Official documentation of
     *      restrictChatMember</a>
     */
    public int restrictChatMember(long chatId, long userId, int untilDate, boolean canSendMessages,
            boolean canSendMediaMessages, boolean canSendOtherMessages, boolean canAddWebPagePreviews)
            throws IOException {
        return restrictChatMember(String.valueOf(chatId), userId, untilDate, canSendMessages, canSendMediaMessages,
                canSendOtherMessages, canAddWebPagePreviews);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#restrictchatmember">Official documentation of
     *      restrictChatMember</a>
     */
    public int restrictChatMember(String channel, long userId, int untilDate, boolean canSendMessages,
            boolean canSendMediaMessages, boolean canSendOtherMessages, boolean canAddWebPagePreviews)
            throws IOException {
        StringBuilder command = new StringBuilder(RESTRICT_CHAT_MEMBER);
        command.append("chat_id=").append(channel);
        command.append("&user_id=").append(userId);
        if (untilDate != 0) {
            command.append("&until_date=").append(untilDate);
        }
        if (canSendOtherMessages || canAddWebPagePreviews) { // Implies canSendMediaMessages
            if (canSendOtherMessages) {
                command.append("&can_send_other_messages=True");
            }
            if (canAddWebPagePreviews) {
                command.append("&can_add_web_page_previews=True");
            }
        } else if (canSendMediaMessages) { // Implies canSendMessages
            command.append("&can_send_media_messages=True");
        } else if (canSendMessages) {
            command.append("&can_send_messages=True");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#restrictchatmember">Official documentation of
     *      restrictChatMember</a>
     */
    public int restrictChatMember(long chatId, long userId, ChatPermissions permissions, int untilDate)
            throws IOException {
        return restrictChatMember(String.valueOf(chatId), userId, permissions, untilDate);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#restrictchatmember">Official documentation of
     *      restrictChatMember</a>
     */
    public int restrictChatMember(String channel, long userId, ChatPermissions permissions, int untilDate)
            throws IOException {
        return restrictChatMember(channel, userId, permissions, untilDate, false);
    }

    public int restrictChatMember(String channel, long userId, ChatPermissions permissions, int untilDate,
            boolean useIndependentChatPermissions) throws IOException {
        StringBuilder command = new StringBuilder(RESTRICT_CHAT_MEMBER);
        command.append("chat_id=").append(channel);
        command.append("&user_id=").append(userId);
        command.append("&permissions=").append(urlEncode(GSON.toJson(permissions)));
        if (untilDate != 0) {
            command.append("&until_date=").append(untilDate);
        }
        if (useIndependentChatPermissions) {
            command.append("&use_independent_chat_permissions=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#promotechatmember">Official documentation of
     *      promoteChatMember</a>
     */
    public int promoteChatMember(long chatId, long userId, boolean canChangeInfo, boolean canPostMessages,
            boolean canEditMessages, boolean canDeleteMessages, boolean canInviteUsers, boolean canRestrictMembers,
            boolean canPinMessages, boolean canPromoteMembers) throws IOException {
        return promoteChatMember(String.valueOf(chatId), userId, canChangeInfo, canPostMessages, canEditMessages,
                canDeleteMessages, canInviteUsers, canRestrictMembers, canPinMessages, canPromoteMembers);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#promotechatmember">Official documentation of
     *      promoteChatMember</a>
     */
    public int promoteChatMember(String channel, long userId, boolean canChangeInfo, boolean canPostMessages,
            boolean canEditMessages, boolean canDeleteMessages, boolean canInviteUsers, boolean canRestrictMembers,
            boolean canPinMessages, boolean canPromoteMembers) throws IOException {
        return promoteChatMember(channel, userId, false, canChangeInfo, canPostMessages, canEditMessages,
                canDeleteMessages, canInviteUsers, canRestrictMembers, canPinMessages, canPromoteMembers);
    }

    public int promoteChatMember(String channel, long userId, boolean isAnonymous, boolean canChangeInfo,
            boolean canPostMessages, boolean canEditMessages, boolean canDeleteMessages, boolean canInviteUsers,
            boolean canRestrictMembers, boolean canPinMessages, boolean canPromoteMembers) throws IOException {
        StringBuilder command = new StringBuilder(PROMOTE_CHAT_MEMBER);
        command.append("chat_id=").append(channel);
        command.append("&user_id=").append(userId);
        if (isAnonymous) {
            command.append("&is_anonymous=True");
        }
        if (canChangeInfo) {
            command.append("&can_change_info=True");
        }
        if (canPostMessages) {
            command.append("&can_post_messages=True");
        }
        if (canEditMessages) {
            command.append("&can_edit_messages=True");
        }
        if (canDeleteMessages) {
            command.append("&can_delete_messages=True");
        }
        if (canInviteUsers) {
            command.append("&can_invite_users=True");
        }
        if (canRestrictMembers) {
            command.append("&can_restrict_members=True");
        }
        if (canPinMessages) {
            command.append("&can_pin_messages=True");
        }
        if (canPromoteMembers) {
            command.append("&can_promote_members=True");
        }

        return callMethod(command.toString());
    }

    public int promoteChatMember(long chatId, long userId, boolean isAnonymous, boolean canManageVideoChats,
            boolean canChangeInfo, boolean canPostMessages, boolean canEditMessages, boolean canDeleteMessages,
            boolean canInviteUsers, boolean canRestrictMembers, boolean canPinMessages, boolean canPromoteMembers)
            throws IOException {
        return promoteChatMember(String.valueOf(chatId), userId, isAnonymous, canManageVideoChats, canChangeInfo,
                canPostMessages, canEditMessages, canDeleteMessages, canInviteUsers, canRestrictMembers,
                canPinMessages, canPromoteMembers, false);
    }

    public int promoteChatMember(String channel, long userId, boolean isAnonymous, boolean canManageVideoChats,
            boolean canChangeInfo, boolean canPostMessages, boolean canEditMessages, boolean canDeleteMessages,
            boolean canInviteUsers, boolean canRestrictMembers, boolean canPinMessages, boolean canPromoteMembers)
            throws IOException {
        return promoteChatMember(channel, userId, isAnonymous, canManageVideoChats, canChangeInfo, canPostMessages,
                canEditMessages, canDeleteMessages, canInviteUsers, canRestrictMembers, canPinMessages,
                canPromoteMembers, false);
    }

    public int promoteChatMember(String channel, long userId, boolean isAnonymous, boolean canManageVideoChats,
            boolean canChangeInfo, boolean canPostMessages, boolean canEditMessages, boolean canDeleteMessages,
            boolean canInviteUsers, boolean canRestrictMembers, boolean canPinMessages, boolean canPromoteMembers,
            boolean canManageTopics) throws IOException {
        StringBuilder command = new StringBuilder(PROMOTE_CHAT_MEMBER);
        command.append("chat_id=").append(urlEncode(channel)).append("&user_id=").append(userId);
        if (isAnonymous) command.append("&is_anonymous=true");
        if (canManageVideoChats) command.append("&can_manage_video_chats=true");
        if (canChangeInfo) command.append("&can_change_info=true");
        if (canPostMessages) command.append("&can_post_messages=true");
        if (canEditMessages) command.append("&can_edit_messages=true");
        if (canDeleteMessages) command.append("&can_delete_messages=true");
        if (canInviteUsers) command.append("&can_invite_users=true");
        if (canRestrictMembers) command.append("&can_restrict_members=true");
        if (canPinMessages) command.append("&can_pin_messages=true");
        if (canPromoteMembers) command.append("&can_promote_members=true");
        if (canManageTopics) command.append("&can_manage_topics=true");
        return callMethod(command.toString());
    }

    public ForumTopic createForumTopic(String chatId, String name, int iconColor, String iconCustomEmojiId)
            throws IOException {
        StringBuilder command = new StringBuilder(CREATE_FORUM_TOPIC).append("chat_id=").append(urlEncode(chatId))
                .append("&name=").append(urlEncode(name));
        if (iconColor != 0) command.append("&icon_color=").append(iconColor);
        if (iconCustomEmojiId != null) {
            command.append("&icon_custom_emoji_id=").append(urlEncode(iconCustomEmojiId));
        }
        return callMethod(command.toString(), ForumTopic.class);
    }

    public ForumTopic createForumTopic(long chatId, String name, int iconColor, String iconCustomEmojiId)
            throws IOException {
        return createForumTopic(String.valueOf(chatId), name, iconColor, iconCustomEmojiId);
    }

    public int editForumTopic(String chatId, int messageThreadId, String name, String iconCustomEmojiId)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_FORUM_TOPIC).append("chat_id=").append(urlEncode(chatId))
                .append("&message_thread_id=").append(messageThreadId);
        if (name != null) command.append("&name=").append(urlEncode(name));
        if (iconCustomEmojiId != null) {
            command.append("&icon_custom_emoji_id=").append(urlEncode(iconCustomEmojiId));
        }
        return callMethod(command.toString());
    }

    public int editForumTopic(long chatId, int messageThreadId, String name, String iconCustomEmojiId)
            throws IOException {
        return editForumTopic(String.valueOf(chatId), messageThreadId, name, iconCustomEmojiId);
    }

    private int callForumTopicMethod(String method, String chatId, int messageThreadId) throws IOException {
        return callMethod(method + "chat_id=" + urlEncode(chatId) + "&message_thread_id=" + messageThreadId);
    }

    public int closeForumTopic(String chatId, int messageThreadId) throws IOException {
        return callForumTopicMethod(CLOSE_FORUM_TOPIC, chatId, messageThreadId);
    }

    public int reopenForumTopic(String chatId, int messageThreadId) throws IOException {
        return callForumTopicMethod(REOPEN_FORUM_TOPIC, chatId, messageThreadId);
    }

    public int deleteForumTopic(String chatId, int messageThreadId) throws IOException {
        return callForumTopicMethod(DELETE_FORUM_TOPIC, chatId, messageThreadId);
    }

    public int unpinAllForumTopicMessages(String chatId, int messageThreadId) throws IOException {
        return callForumTopicMethod(UNPIN_ALL_FORUM_TOPIC_MESSAGES, chatId, messageThreadId);
    }

    public Sticker[] getForumTopicIconStickers() throws IOException {
        return callMethod(GET_FORUM_TOPIC_ICON_STICKERS, Sticker[].class);
    }

    public int editGeneralForumTopic(String chatId, String name) throws IOException {
        return callMethod(EDIT_GENERAL_FORUM_TOPIC + "chat_id=" + urlEncode(chatId) + "&name=" + urlEncode(name));
    }

    private int callGeneralForumTopicMethod(String method, String chatId) throws IOException {
        return callMethod(method + "chat_id=" + urlEncode(chatId));
    }

    public int closeGeneralForumTopic(String chatId) throws IOException {
        return callGeneralForumTopicMethod(CLOSE_GENERAL_FORUM_TOPIC, chatId);
    }

    public int reopenGeneralForumTopic(String chatId) throws IOException {
        return callGeneralForumTopicMethod(REOPEN_GENERAL_FORUM_TOPIC, chatId);
    }

    public int hideGeneralForumTopic(String chatId) throws IOException {
        return callGeneralForumTopicMethod(HIDE_GENERAL_FORUM_TOPIC, chatId);
    }

    public int unhideGeneralForumTopic(String chatId) throws IOException {
        return callGeneralForumTopicMethod(UNHIDE_GENERAL_FORUM_TOPIC, chatId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatadministratorcustomtitle">Official documentation of
     *      setChatAdministratorCustomTitle</a>
     */
    public int setChatAdministratorCustomTitle(long chatId, long userId, String customTitle) throws IOException {
        return setChatAdministratorCustomTitle(String.valueOf(chatId), userId, customTitle);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatadministratorcustomtitle">Official documentation of
     *      setChatAdministratorCustomTitle</a>
     */
    public int setChatAdministratorCustomTitle(String channel, long userId, String customTitle) throws IOException {
        String command = SET_CHAT_ADMINISTRATOR_CUSTOM_TITLE + "chat_id=" + channel + "&user_id=" + userId
                + "&custom_title=" + urlEncode(customTitle);
        return callMethod(command);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#exportchatinvitelink">Official documentation of
     *      exportChatInviteLink</a>
     */
    public String exportChatInviteLink(long chatId) throws IOException {
        return exportChatInviteLink(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#exportchatinvitelink">Official documentation of
     *      exportChatInviteLink</a>
     */
    public String exportChatInviteLink(String channel) throws IOException {
        return callMethod(EXPORT_CHAT_INVITE_LINK + "chat_id=" + channel, String.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatphoto">Official documentation of exportChatInviteLink</a>
     */
    public int setChatPhoto(long chatId, InputStream photo, String filename) throws IOException {
        return setChatPhoto(String.valueOf(chatId), photo, filename);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatphoto">Official documentation of exportChatInviteLink</a>
     */
    public int setChatPhoto(String channel, InputStream photo, String filename) throws IOException {
        FileSender sender = new FileSender(SET_CHAT_PHOTO);
        sender.addFormField("chat_id", channel);
        sender.addFilePart("photo", photo, filename);
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deletechatphoto">Official documentation of deleteChatPhoto</a>
     */
    public int deleteChatPhoto(long chatId) throws IOException {
        return deleteChatPhoto(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deletechatphoto">Official documentation of deleteChatPhoto</a>
     */
    public int deleteChatPhoto(String channel) throws IOException {
        return callMethod(DELETE_CHAT_PHOTO + "chat_id=" + channel);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchattitle">Official documentation of setChatTitle</a>
     */
    @Deprecated
    public int setChatTitle(long chatId, int title) throws IOException {
        return setChatTitle(String.valueOf(chatId), title);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchattitle">Official documentation of setChatTitle</a>
     */
    @Deprecated
    public int setChatTitle(String channel, int title) throws IOException {
        return setChatTitle(channel, String.valueOf(title));
    }

    public int setChatTitle(long chatId, String title) throws IOException {
        return setChatTitle(String.valueOf(chatId), title);
    }

    public int setChatTitle(String channel, String title) throws IOException {
        return callMethod(SET_CHAT_TITLE + "chat_id=" + urlEncode(channel) + "&title=" + urlEncode(title));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatdescription">Official documentation of
     *      setChatDescription</a>
     */
    @Deprecated
    public int setChatDescription(long chatId, int description) throws IOException {
        return setChatDescription(String.valueOf(chatId), description);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatdescription">Official documentation of
     *      setChatDescription</a>
     */
    @Deprecated
    public int setChatDescription(String channel, int description) throws IOException {
        return setChatDescription(channel, String.valueOf(description));
    }

    public int setChatDescription(long chatId, String description) throws IOException {
        return setChatDescription(String.valueOf(chatId), description);
    }

    public int setChatDescription(String channel, String description) throws IOException {
        return callMethod(SET_CHAT_DESCRIPTION + "chat_id=" + urlEncode(channel) + "&description="
                + urlEncode(description));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatpermissions">Official documentation of
     *      setChatPermissions</a>
     */
    public int setChatPermissions(long chatId, ChatPermissions permissions) throws IOException {
        return setChatPermissions(String.valueOf(chatId), permissions);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatpermissions">Official documentation of
     *      setChatPermissions</a>
     */
    public int setChatPermissions(String channel, ChatPermissions permissions) throws IOException {
        return setChatPermissions(channel, permissions, false);
    }

    public int setChatPermissions(String channel, ChatPermissions permissions, boolean useIndependentChatPermissions)
            throws IOException {
        String command = SET_CHAT_PERMISSIONS + "chat_id=" + channel + "&permissions="
                + urlEncode(GSON.toJson(permissions));
        if (useIndependentChatPermissions) {
            command += "&use_independent_chat_permissions=true";
        }
        return callMethod(command);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#pinchatmessage">Official documentation of pinChatMessage</a>
     */
    public int pinChatMessage(long chatId, String messageId) throws IOException {
        return pinChatMessage(String.valueOf(chatId), messageId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#pinchatmessage">Official documentation of pinChatMessage</a>
     */
    public int pinChatMessage(String channel, String messageId) throws IOException {
        StringBuilder command = new StringBuilder(PIN_CHAT_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#unpinchatmessage">Official documentation of unpinChatMessage</a>
     */
    public int unpinChatMessage(long chatId) throws IOException {
        return unpinChatMessage(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#unpinchatmessage">Official documentation of unpinChatMessage</a>
     */
    public int unpinChatMessage(String channel) throws IOException {
        return callMethod(UNPIN_CHAT_MESSAGE + "chat_id=" + channel);
    }

    public int unpinChatMessage(String channel, int messageId) throws IOException {
        return callMethod(UNPIN_CHAT_MESSAGE + "chat_id=" + channel + "&message_id=" + messageId);
    }

    public int unpinAllChatMessages(String channel) throws IOException {
        return callMethod(UNPIN_ALL_CHAT_MESSAGES + "chat_id=" + channel);
    }

    public int unpinAllChatMessages(long chatId) throws IOException {
        return unpinAllChatMessages(String.valueOf(chatId));
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

    /** Bot API 5.2 name for getChatMembersCount. */
    public int getChatMemberCount(long chatId) throws IOException {
        return getChatMembersCount(chatId);
    }

    public int getChatMemberCount(String chatId) throws IOException {
        return getChatMembersCount(chatId);
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
    public ChatMember getChatMember(long chatId, long userId) throws IOException {
        return getChatMember(String.valueOf(chatId), userId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getchatmember">Official documentation of getChatMember</a>
     */
    public ChatMember getChatMember(String channel, long userId) throws IOException {
        return callMethod(GET_CHAT_MEMBER + "chat_id=" + channel + "&user_id=" + userId, ChatMember.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatstickerset">Official documentation of
     *      setChatStickerSet</a>
     */
    public ChatMember setChatStickerSet(long chatId, String name) throws IOException {
        return setChatStickerSet(String.valueOf(chatId), name);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setchatstickerset">Official documentation of
     *      setChatStickerSet</a>
     */
    public ChatMember setChatStickerSet(String channel, String name) throws IOException {
        return callMethod(SET_CHAT_STICKER_SET + "chat_id=" + channel + "&sticker_set_name=" + name, ChatMember.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deletechatstickerset">Official documentation of
     *      deleteChatStickerSet</a>
     */
    public ChatMember deleteChatStickerSet(long chatId, long userId) throws IOException {
        return deleteChatStickerSet(String.valueOf(chatId));
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deletechatstickerset">Official documentation of
     *      deleteChatStickerSet</a>
     */
    public ChatMember deleteChatStickerSet(String channel) throws IOException {
        return callMethod(DELETE_CHAT_STICKER_SET + "chat_id=" + channel, ChatMember.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answercallbackquery">Official documentation of
     *      answerCallbackQuery</a>
     */
    public int answerCallbackQuery(CallbackQuery callbackQuery, String text, boolean showAlert, String url,
            int cacheTime) throws IOException {
        return answerCallbackQuery(callbackQuery.id, text, showAlert, url, cacheTime);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answercallbackquery">Official documentation of
     *      answerCallbackQuery</a>
     */
    public int answerCallbackQuery(String callbackQueryId, String text, boolean showAlert, String url, int cacheTime)
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
        if (cacheTime > 0) {
            command.append("&cache_time=").append(cacheTime);
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
    public int editMessageCaption(long chatId, int messageId, String caption, ParseMode parseMode,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        return editMessageCaption(String.valueOf(chatId), messageId, caption, parseMode, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagecaption">Official documentation of
     *      editMessageCaption</a>
     */
    public int editMessageCaption(String channel, int messageId, String caption, ParseMode parseMode,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_CAPTION);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&caption=").append(urlEncode(caption));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
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
    public int editMessageCaption(String inlineMessageId, String caption, ParseMode parseMode,
            InlineKeyboardMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_CAPTION);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&caption=").append(urlEncode(caption));
        if (parseMode != null) {
            command.append("&parse_mode=").append(parseMode.VALUE);
        }
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagemedia">Official documentation of editMessageMedia</a>
     */
    public int editMessageMedia(long chatId, int messageId, InputMedia media, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        return editMessageMedia(String.valueOf(chatId), messageId, media, replyMarkup);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagemedia">Official documentation of editMessageMedia</a>
     */
    public int editMessageMedia(String channel, int messageId, InputMedia media, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_MEDIA);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        command.append("&media=").append(urlEncode(GSON.toJson(media)));
        if (replyMarkup != null) {
            command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#editmessagemedia">Official documentation of editMessageMedia</a>
     */
    public int editMessageMedia(String inlineMessageId, InputMedia media, InlineKeyboardMarkup replyMarkup)
            throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_MEDIA);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&media=").append(urlEncode(GSON.toJson(media)));
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
    public int editMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup) throws IOException {
        StringBuilder command = new StringBuilder(EDIT_MESSAGE_REPLY_MARKUP);
        command.append("inline_message_id=").append(inlineMessageId);
        command.append("&reply_markup=").append(urlEncode(GSON.toJson(replyMarkup)));
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deleteMessage">Official documentation of deleteMessage</a>
     */
    public int deleteMessage(long chatId, int messageId) throws IOException {
        return deleteMessage(String.valueOf(chatId), messageId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deleteMessage">Official documentation of deleteMessage</a>
     */
    public int deleteMessage(String channel, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(DELETE_MESSAGE);
        command.append("chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
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
        return sendSticker(channel, sticker, replyTo, replyMarkup, 0);
    }

    public int sendSticker(String channel, String sticker, int replyTo, ReplyMarkup replyMarkup, int messageThreadId)
            throws IOException {
        StringBuilder command = new StringBuilder(SEND_STICKER).append('?');
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&sticker=").append(sticker);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
        return sendSticker(channel, sticker, filename, replyTo, replyMarkup, 0);
    }

    public int sendSticker(String channel, InputStream sticker, String filename, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        FileSender sender = new FileSender(SEND_STICKER);
        sender.addFormField("chat_id", channel);
        if (messageThreadId > 0) sender.addFormField("message_thread_id", messageThreadId);
        sender.addFilePart("sticker", sticker, filename);
        if (disableNotification) {
            sender.addFormField("disable_notification", "true");
        }
        if (protectContent) {
            sender.addFormField("protect_content", "true");
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
     * @see <a href="https://core.telegram.org/bots/api#getstickerset">Official documentation of getStickerSet</a>
     */
    public int getStickerSet(String name) throws IOException {
        StringBuilder command = new StringBuilder(GET_STICKER_SET).append("name=").append(name);
        return callMethod(command.toString());
    }

    public Sticker[] getCustomEmojiStickers(String[] customEmojiIds) throws IOException {
        String command = GET_CUSTOM_EMOJI_STICKERS + "custom_emoji_ids="
                + urlEncode(GSON.toJson(customEmojiIds));
        return callMethod(command, Sticker[].class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#uploadstickerfile">Official documentation of
     *      uploadStickerFile</a>
     */
    public int uploadStickerFile(long userId, InputStream pngSticker) throws IOException {
        FileSender sender = new FileSender(UPLOAD_STICKER_FILE);
        sender.addFormField("user_id", userId);
        sender.addFilePart("png_sticker", pngSticker, "sticker");
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#createnewstickerset">Official documentation of
     *      createNewStickerSet</a>
     */
    public int createNewStickerSet(long userId, String name, String title, InputStream pngSticker, String emojis,
            boolean isMasks, MaskPosition maskPosition) throws IOException {
        return createNewStickerSet(userId, name, title, pngSticker, emojis, isMasks ? "mask" : "regular",
                maskPosition);
    }

    public int createNewStickerSet(long userId, String name, String title, InputStream pngSticker, String emojis,
            String stickerType, MaskPosition maskPosition) throws IOException {
        FileSender sender = new FileSender(CREATE_NEW_STICKER_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFormField("title", title);
        sender.addFilePart("png_sticker", pngSticker, "sticker");
        sender.addFormField("emojis", emojis);
        sender.addFormField("sticker_type", stickerType);
        if (maskPosition != null) {
            sender.addFormField("mask_position", GSON.toJson(maskPosition));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#createnewstickerset">Official documentation of
     *      createNewStickerSet</a>
     */
    public int createNewStickerSet(long userId, String name, String title, String pngSticker, String emojis,
            boolean isMasks, MaskPosition maskPosition) throws IOException {
        return createNewStickerSet(userId, name, title, pngSticker, emojis, isMasks ? "mask" : "regular",
                maskPosition);
    }

    public int createNewStickerSet(long userId, String name, String title, String pngSticker, String emojis,
            String stickerType, MaskPosition maskPosition) throws IOException {
        StringBuilder command = new StringBuilder(CREATE_NEW_STICKER_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(name);
        command.append("&title=").append(title);
        command.append("&png_sticker=").append(pngSticker);
        command.append("&emojis=").append(emojis);
        command.append("&sticker_type=").append(stickerType);
        if (maskPosition != null) {
            command.append("&mask_position=").append(urlEncode(GSON.toJson(maskPosition)));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#addstickertoset">Official documentation of addStickerToSet</a>
     */
    public int addStickerToSet(long userId, String name, InputStream pngSticker, String emojis,
            MaskPosition maskPosition)
            throws IOException {
        FileSender sender = new FileSender(ADD_STICKER_TO_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFilePart("png_sticker", pngSticker, "sticker");
        sender.addFormField("emojis", emojis);
        if (maskPosition != null) {
            sender.addFormField("mask_position", GSON.toJson(maskPosition));
        }
        return sender.finish();
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#addstickertoset">Official documentation of addStickerToSet</a>
     */
    public int addStickerToSet(long userId, String name, String pngSticker, String emojis, MaskPosition maskPosition)
            throws IOException {
        StringBuilder command = new StringBuilder(ADD_STICKER_TO_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(name);
        command.append("&png_sticker=").append(pngSticker);
        command.append("&emojis=").append(emojis);
        if (maskPosition != null) {
            command.append("&mask_position=").append(urlEncode(GSON.toJson(maskPosition)));
        }
        return callMethod(command.toString());
    }

    public int createNewAnimatedStickerSet(long userId, String name, String title, InputStream tgsSticker,
            String emojis) throws IOException {
        FileSender sender = new FileSender(CREATE_NEW_STICKER_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFormField("title", title);
        sender.addFilePart("tgs_sticker", tgsSticker, "sticker.tgs");
        sender.addFormField("emojis", emojis);
        return sender.finish();
    }

    public int createNewVideoStickerSet(long userId, String name, String title, InputStream webmSticker,
            String emojis) throws IOException {
        FileSender sender = new FileSender(CREATE_NEW_STICKER_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFormField("title", title);
        sender.addFilePart("webm_sticker", webmSticker, "sticker.webm");
        sender.addFormField("emojis", emojis);
        return sender.finish();
    }

    public int createNewVideoStickerSet(long userId, String name, String title, String webmSticker,
            String emojis) throws IOException {
        StringBuilder command = new StringBuilder(CREATE_NEW_STICKER_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(urlEncode(name));
        command.append("&title=").append(urlEncode(title));
        command.append("&webm_sticker=").append(urlEncode(webmSticker));
        command.append("&emojis=").append(urlEncode(emojis));
        return callMethod(command.toString());
    }

    public int createNewAnimatedStickerSet(long userId, String name, String title, String tgsSticker,
            String emojis) throws IOException {
        StringBuilder command = new StringBuilder(CREATE_NEW_STICKER_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(name);
        command.append("&title=").append(urlEncode(title));
        command.append("&tgs_sticker=").append(urlEncode(tgsSticker));
        command.append("&emojis=").append(urlEncode(emojis));
        return callMethod(command.toString());
    }

    public int addAnimatedStickerToSet(long userId, String name, InputStream tgsSticker, String emojis)
            throws IOException {
        FileSender sender = new FileSender(ADD_STICKER_TO_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFilePart("tgs_sticker", tgsSticker, "sticker.tgs");
        sender.addFormField("emojis", emojis);
        return sender.finish();
    }

    public int addVideoStickerToSet(long userId, String name, InputStream webmSticker, String emojis)
            throws IOException {
        FileSender sender = new FileSender(ADD_STICKER_TO_SET);
        sender.addFormField("user_id", userId);
        sender.addFormField("name", name);
        sender.addFilePart("webm_sticker", webmSticker, "sticker.webm");
        sender.addFormField("emojis", emojis);
        return sender.finish();
    }

    public int addVideoStickerToSet(long userId, String name, String webmSticker, String emojis)
            throws IOException {
        StringBuilder command = new StringBuilder(ADD_STICKER_TO_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(urlEncode(name));
        command.append("&webm_sticker=").append(urlEncode(webmSticker));
        command.append("&emojis=").append(urlEncode(emojis));
        return callMethod(command.toString());
    }

    public int addAnimatedStickerToSet(long userId, String name, String tgsSticker, String emojis)
            throws IOException {
        StringBuilder command = new StringBuilder(ADD_STICKER_TO_SET).append('?');
        command.append("user_id=").append(userId);
        command.append("&name=").append(name);
        command.append("&tgs_sticker=").append(urlEncode(tgsSticker));
        command.append("&emojis=").append(urlEncode(emojis));
        return callMethod(command.toString());
    }

    public int setStickerSetThumb(String name, long userId, InputStream thumb) throws IOException {
        FileSender sender = new FileSender(SET_STICKER_SET_THUMB);
        sender.addFormField("name", name);
        sender.addFormField("user_id", userId);
        if (thumb != null) {
            sender.addFilePart("thumb", thumb, "thumb.png");
        }
        return sender.finish();
    }

    public int setStickerSetThumb(String name, long userId, String thumb) throws IOException {
        StringBuilder command = new StringBuilder(SET_STICKER_SET_THUMB).append('?');
        command.append("name=").append(name);
        command.append("&user_id=").append(userId);
        if (thumb != null) {
            command.append("&thumb=").append(urlEncode(thumb));
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setstickerpositioninset">Official documentation of
     *      setStickerPositionInSet</a>
     */
    public int setStickerPositionInSet(String sticker, int position) throws IOException {
        StringBuilder command = new StringBuilder(SET_STICKER_POSITION_IN_SET);
        command.append("sticker=").append(sticker);
        command.append("position=").append(position);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#deletestickerfromset">Official documentation of
     *      deleteStickerFromSet</a>
     */
    public int deleteStickerFromSet(String sticker) throws IOException {
        StringBuilder command = new StringBuilder(DELETE_STICKER_FROM_SET);
        command.append("sticker=").append(sticker);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answerinlinequery">Official documentation of
     *      answerInlineQuery</a>
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

    public int setMyDescription(String description, String languageCode) throws IOException {
        String command = BASE_URL + "/setMyDescription?description=" + urlEncode(description == null ? "" : description);
        if (languageCode != null) command += "&language_code=" + urlEncode(languageCode);
        return callMethod(command);
    }

    public int setMyName(String name, String languageCode) throws IOException {
        String command = BASE_URL + "/setMyName?name=" + urlEncode(name == null ? "" : name);
        if (languageCode != null) command += "&language_code=" + urlEncode(languageCode);
        return callMethod(command);
    }

    public BotName getMyName(String languageCode) throws IOException {
        String command = BASE_URL + "/getMyName?";
        if (languageCode != null) command += "language_code=" + urlEncode(languageCode);
        return callMethod(command, BotName.class);
    }

    public BotDescription getMyDescription(String languageCode) throws IOException {
        String command = BASE_URL + "/getMyDescription?";
        if (languageCode != null) command += "language_code=" + urlEncode(languageCode);
        return callMethod(command, BotDescription.class);
    }

    public int setMyShortDescription(String shortDescription, String languageCode) throws IOException {
        String command = BASE_URL + "/setMyShortDescription?short_description="
                + urlEncode(shortDescription == null ? "" : shortDescription);
        if (languageCode != null) command += "&language_code=" + urlEncode(languageCode);
        return callMethod(command);
    }

    public BotShortDescription getMyShortDescription(String languageCode) throws IOException {
        String command = BASE_URL + "/getMyShortDescription?";
        if (languageCode != null) command += "language_code=" + urlEncode(languageCode);
        return callMethod(command, BotShortDescription.class);
    }

    public int addStickerToSet(long userId, String name, InputSticker sticker) throws IOException {
        return callMethod(BASE_URL + "/addStickerToSet?user_id=" + userId + "&name=" + urlEncode(name)
                + "&sticker=" + urlEncode(GSON.toJson(sticker)));
    }

    public int createNewStickerSet(long userId, String name, String title, InputSticker[] stickers,
            String stickerFormat, String stickerType, boolean needsRepainting) throws IOException {
        String command = BASE_URL + "/createNewStickerSet?user_id=" + userId + "&name=" + urlEncode(name)
                + "&title=" + urlEncode(title) + "&stickers=" + urlEncode(GSON.toJson(stickers))
                + "&sticker_format=" + urlEncode(stickerFormat);
        if (stickerType != null) command += "&sticker_type=" + urlEncode(stickerType);
        if (needsRepainting) command += "&needs_repainting=true";
        return callMethod(command);
    }

    public int setCustomEmojiStickerSetThumbnail(String name, String customEmojiId) throws IOException {
        String command = BASE_URL + "/setCustomEmojiStickerSetThumbnail?name=" + urlEncode(name);
        if (customEmojiId != null) command += "&custom_emoji_id=" + urlEncode(customEmojiId);
        return callMethod(command);
    }

    public int setStickerSetTitle(String name, String title) throws IOException {
        return callMethod(BASE_URL + "/setStickerSetTitle?name=" + urlEncode(name) + "&title=" + urlEncode(title));
    }

    public int deleteStickerSet(String name) throws IOException {
        return callMethod(BASE_URL + "/deleteStickerSet?name=" + urlEncode(name));
    }

    public int setStickerEmojiList(String sticker, String[] emojiList) throws IOException {
        return callMethod(BASE_URL + "/setStickerEmojiList?sticker=" + urlEncode(sticker) + "&emoji_list="
                + urlEncode(GSON.toJson(emojiList)));
    }

    public int setStickerKeywords(String sticker, String[] keywords) throws IOException {
        return callMethod(BASE_URL + "/setStickerKeywords?sticker=" + urlEncode(sticker) + "&keywords="
                + urlEncode(GSON.toJson(keywords)));
    }

    public int setStickerMaskPosition(String sticker, MaskPosition maskPosition) throws IOException {
        return callMethod(BASE_URL + "/setStickerMaskPosition?sticker=" + urlEncode(sticker) + "&mask_position="
                + urlEncode(GSON.toJson(maskPosition)));
    }

    public int setStickerSetThumbnail(String name, long userId, String thumbnail) throws IOException {
        String command = BASE_URL + "/setStickerSetThumbnail?name=" + urlEncode(name) + "&user_id=" + userId;
        if (thumbnail != null) command += "&thumbnail=" + urlEncode(thumbnail);
        return callMethod(command);
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

    public int answerInlineQuery(String inlineQueryId, InlineQueryResult[] results, int cacheTime, boolean isPersonal,
            String nextOffset, InlineQueryResultsButton button) throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_INLINE_QUERY);
        command.append("inline_query_id=").append(urlEncode(inlineQueryId));
        command.append("&results=").append(urlEncode(GSON.toJson(results)));
        if (cacheTime >= 0) command.append("&cache_time=").append(cacheTime);
        if (isPersonal) command.append("&is_personal=true");
        if (nextOffset != null) command.append("&next_offset=").append(urlEncode(nextOffset));
        if (button != null) command.append("&button=").append(urlEncode(GSON.toJson(button)));
        return callMethod(command.toString());
    }

    public String createInvoiceLink(String title, String description, String payload, String providerToken,
            String currency, LabeledPrice[] prices, int maxTipAmount, int[] suggestedTipAmounts,
            String providerData, String photoUrl, int photoSize, int photoWidth, int photoHeight,
            boolean needName, boolean needPhoneNumber, boolean needEmail, boolean needShippingAddress,
            boolean sendPhoneNumberToProvider, boolean sendEmailToProvider, boolean isFlexible) throws IOException {
        StringBuilder command = new StringBuilder(BASE_URL + "/createInvoiceLink?");
        command.append("title=").append(urlEncode(title));
        command.append("&description=").append(urlEncode(description));
        command.append("&payload=").append(urlEncode(payload));
        command.append("&provider_token=").append(urlEncode(providerToken));
        command.append("&currency=").append(urlEncode(currency));
        command.append("&prices=").append(urlEncode(GSON.toJson(prices)));
        if (maxTipAmount > 0) command.append("&max_tip_amount=").append(maxTipAmount);
        if (suggestedTipAmounts != null) {
            command.append("&suggested_tip_amounts=").append(urlEncode(GSON.toJson(suggestedTipAmounts)));
        }
        if (providerData != null) command.append("&provider_data=").append(urlEncode(providerData));
        if (photoUrl != null) command.append("&photo_url=").append(urlEncode(photoUrl));
        if (photoSize > 0) command.append("&photo_size=").append(photoSize);
        if (photoWidth > 0) command.append("&photo_width=").append(photoWidth);
        if (photoHeight > 0) command.append("&photo_height=").append(photoHeight);
        if (needName) command.append("&need_name=true");
        if (needPhoneNumber) command.append("&need_phone_number=true");
        if (needEmail) command.append("&need_email=true");
        if (needShippingAddress) command.append("&need_shipping_address=true");
        if (sendPhoneNumberToProvider) command.append("&send_phone_number_to_provider=true");
        if (sendEmailToProvider) command.append("&send_email_to_provider=true");
        if (isFlexible) command.append("&is_flexible=true");
        return callMethod(command.toString(), String.class);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendinvoice">Official documentation of sendInvoice</a>
     */
    public int sendInvoice(long chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, String providerData, String photoUrl,
            boolean needName, boolean needPhoneNumber, boolean needEmail, boolean needShippingAddress,
            boolean isFlexible) throws IOException {
        return sendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices,
                providerData, photoUrl, 0, 0, 0, needName, needPhoneNumber, needEmail, needShippingAddress, isFlexible,
                0, null);
    }

    public int sendInvoice(String chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, String providerData, String photoUrl,
            int photoSize, int photoWidth, int photoHeight, boolean needName, boolean needPhoneNumber,
            boolean needEmail, boolean needShippingAddress, boolean isFlexible, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices,
                0, null, providerData, photoUrl, photoSize, photoWidth, photoHeight, needName, needPhoneNumber,
                needEmail, needShippingAddress, isFlexible, replyTo, replyMarkup);
    }

    public int sendInvoice(String chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, String providerData, String photoUrl,
            boolean needName, boolean needPhoneNumber, boolean needEmail, boolean needShippingAddress,
            boolean isFlexible) throws IOException {
        return sendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices,
                0, null, providerData, photoUrl, 0, 0, 0, needName, needPhoneNumber, needEmail, needShippingAddress,
                isFlexible, 0, null);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#sendinvoice">Official documentation of sendInvoice</a>
     */
    public int sendInvoice(long chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, String providerData, String photoUrl,
            int photoSize, int photoWidth, int photoHeight, boolean needName, boolean needPhoneNumber,
            boolean needEmail, boolean needShippingAddress, boolean isFlexible, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices,
                0, null, providerData, photoUrl, photoSize, photoWidth, photoHeight, needName, needPhoneNumber,
                needEmail, needShippingAddress, isFlexible, replyTo, replyMarkup);
    }

    public int sendInvoice(long chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, int maxTipAmount,
            int[] suggestedTipAmounts, String providerData, String photoUrl, int photoSize, int photoWidth,
            int photoHeight, boolean needName, boolean needPhoneNumber, boolean needEmail,
            boolean needShippingAddress, boolean isFlexible, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendInvoice(String.valueOf(chatId), title, description, payload, providerToken, startParameter,
                currency, prices, maxTipAmount, suggestedTipAmounts, providerData, photoUrl, photoSize, photoWidth,
                photoHeight, needName, needPhoneNumber, needEmail, needShippingAddress, isFlexible, replyTo,
                replyMarkup);
    }

    public int sendInvoice(String chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, int maxTipAmount,
            int[] suggestedTipAmounts, String providerData, String photoUrl, int photoSize, int photoWidth,
            int photoHeight, boolean needName, boolean needPhoneNumber, boolean needEmail,
            boolean needShippingAddress, boolean isFlexible, int replyTo, ReplyMarkup replyMarkup)
            throws IOException {
        return sendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices,
                maxTipAmount, suggestedTipAmounts, providerData, photoUrl, photoSize, photoWidth, photoHeight,
                needName, needPhoneNumber, needEmail, needShippingAddress, isFlexible, replyTo, replyMarkup, 0);
    }

    public int sendInvoice(String chatId, String title, String description, String payload, String providerToken,
            String startParameter, String currency, LabeledPrice[] prices, int maxTipAmount,
            int[] suggestedTipAmounts, String providerData, String photoUrl, int photoSize, int photoWidth,
            int photoHeight, boolean needName, boolean needPhoneNumber, boolean needEmail,
            boolean needShippingAddress, boolean isFlexible, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_INVOICE);
        command.append("chat_id=").append(urlEncode(chatId));
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&title=").append(urlEncode(title));
        command.append("&description=").append(urlEncode(description));
        command.append("&payload=").append(urlEncode(payload));
        command.append("&provider_token=").append(urlEncode(providerToken));
        command.append("&start_parameter=").append(urlEncode(startParameter));
        command.append("&currency=").append(urlEncode(currency));
        command.append("&prices=").append(urlEncode(GSON.toJson(prices)));
        if (maxTipAmount > 0) {
            command.append("&max_tip_amount=").append(maxTipAmount);
        }
        if (suggestedTipAmounts != null) {
            command.append("&suggested_tip_amounts=").append(urlEncode(GSON.toJson(suggestedTipAmounts)));
        }
        if (providerData != null) {
            command.append("&provider_data=").append(providerData);
        }
        if (photoUrl != null) {
            command.append("&photo_url=").append(photoUrl);
        }
        if (photoSize > 0) {
            command.append("&photo_size=").append(photoSize);
        }
        if (photoWidth > 0) {
            command.append("&photo_width=").append(photoWidth);
        }
        if (photoHeight > 0) {
            command.append("&photo_height=").append(photoHeight);
        }
        if (needName) {
            command.append("&need_name=true");
        }
        if (needPhoneNumber) {
            command.append("&need_phone_number=true");
        }
        if (needEmail) {
            command.append("&need_email=true");
        }
        if (needShippingAddress) {
            command.append("&need_shipping_address=true");
        }
        if (isFlexible) {
            command.append("&is_flexible=true");
        }
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
     * @see <a href="https://core.telegram.org/bots/api#answershippingquery">Official documentation of
     *      answerShippingQuery</a>
     */
    public int answerShippingQuery(String shippingQueryId, boolean ok, ShippingOption[] shippingOptions,
            String errorMessage) throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_SHIPPING_QUERY);
        command.append("shipping_query_id=").append(shippingQueryId);
        command.append("&ok=").append(ok);
        if (shippingOptions != null) {
            command.append("&shipping_options=").append(urlEncode(GSON.toJson(shippingOptions)));
        }
        if (errorMessage != null) {
            command.append("&error_message=").append(errorMessage);
        }
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#answerprecheckoutquery">Official documentation of
     *      answerPreCheckoutQuery</a>
     */
    public int answerPreCheckoutQuery(String preCheckoutQueryId, boolean ok, String errorMessage) throws IOException {
        StringBuilder command = new StringBuilder(ANSWER_PRE_CHECKOUT_QUERY);
        command.append("pre_checkout_query_id=").append(preCheckoutQueryId);
        command.append("&ok=").append(ok);
        if (errorMessage != null) {
            command.append("&error_message=").append(errorMessage);
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
        return sendGame(channel, gameShortName, replyTo, replyMarkup, 0);
    }

    public int sendGame(String channel, String gameShortName, int replyTo, ReplyMarkup replyMarkup,
            int messageThreadId) throws IOException {
        StringBuilder command = new StringBuilder(SEND_GAME);
        command.append("chat_id=").append(channel);
        if (messageThreadId > 0) command.append("&message_thread_id=").append(messageThreadId);
        command.append("&game_short_name=").append(gameShortName);
        if (disableNotification) {
            command.append("&disable_notification=true");
        }
        if (protectContent) {
            command.append("&protect_content=true");
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
    public int setGameScore(long userId, int score, boolean force, long chatId, int messageId) throws IOException {
        return setGameScore(userId, score, force, String.valueOf(chatId), messageId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setgamescore">Official documentation of setGameScore</a>
     */
    public int setGameScore(long userId, int score, boolean force, String channel, int messageId) throws IOException {
        StringBuilder command = new StringBuilder(SET_GAME_SCORE);
        command.append("user_id=").append(userId);
        command.append("&score=").append(score);
        if (force) {
            command.append("&force=True");
        }
        command.append("&chat_id=").append(channel);
        command.append("&message_id=").append(messageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#setgamescore">Official documentation of setGameScore</a>
     */
    public int setGameScore(long userId, int score, boolean force, String inlineMessageId) throws IOException {
        StringBuilder command = new StringBuilder(SET_GAME_SCORE);
        command.append("user_id=").append(userId);
        command.append("&score=").append(score);
        if (force) {
            command.append("&force=True");
        }
        command.append("&inline_message_id=").append(inlineMessageId);
        return callMethod(command.toString());
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getgamehighscores">Official documentation of
     *      getGameHighScores</a>
     */
    public GameHighScore[] getGameHighScores(long userId, long chatId, int messageId) throws IOException {
        return getGameHighScores(userId, String.valueOf(chatId), messageId);
    }

    /**
     * @see <a href="https://core.telegram.org/bots/api#getgamehighscores">Official documentation of
     *      getGameHighScores</a>
     */
    public GameHighScore[] getGameHighScores(long userId, String channel, int messageId) throws IOException {
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
    public GameHighScore[] getGameHighScores(long userId, String inlineMessageId) throws IOException {
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
        int responseCode = lastResponseCode = con.getResponseCode();
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
                    if (e instanceof HttpResponseException) {
                        throw e;
                    } else {
                        handleErrorResponse(responseCode, con.getResponseMessage());
                    }
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
        return callMethod(url, responseClass, READ_TIMEOUT);
    }

    private <T> T callMethod(String url, Class<T> responseClass, int readTimeout) throws IOException {
        HttpURLConnection con = createConnection(url, readTimeout);
        lastResponseCode = con.getResponseCode();
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
     * Helper methods for connecting to the Bot API with reasonable timeout values.
     */
    private HttpURLConnection createConnection(String url, int readTimeout) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(4000);
        con.setReadTimeout(readTimeout);
        con.connect();
        return con;
    }

    private HttpURLConnection createConnection(String url) throws IOException {
        return createConnection(url, READ_TIMEOUT);
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
    public void debug(String message, Throwable e) {
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
    public void debug(Throwable e) {
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
