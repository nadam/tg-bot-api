package se.anyro.tgbotapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URLDecoder;

import org.junit.Test;

import com.google.gson.Gson;

import se.anyro.tgbotapi.types.BotCommand;
import se.anyro.tgbotapi.types.BotCommandScopeChatMember;
import se.anyro.tgbotapi.types.ChatJoinRequest;
import se.anyro.tgbotapi.types.Chat;
import se.anyro.tgbotapi.types.ChatAdministratorRights;
import se.anyro.tgbotapi.types.Message;
import se.anyro.tgbotapi.types.User;
import se.anyro.tgbotapi.types.MenuButtonWebApp;
import se.anyro.tgbotapi.types.Update;
import se.anyro.tgbotapi.types.payments.LabeledPrice;
import se.anyro.tgbotapi.types.MessageEntity;
import se.anyro.tgbotapi.types.stickers.Sticker;
import se.anyro.tgbotapi.types.stickers.StickerSet;
import se.anyro.tgbotapi.types.file.InputMediaPhoto;
import se.anyro.tgbotapi.types.reply_markup.ReplyKeyboardMarkup;

public class BotApiSixTest {
    private static class RecordingApi extends TgBotApi {
        String request;

        RecordingApi() { super("token", 1); }

        @Override
        public int callMethod(String url) {
            request = url;
            return 200;
        }

        @Override
        public <T> T callMethod(String url, Class<T> responseClass) {
            request = url;
            return null;
        }
    }

    @Test
    public void identifiesChatJoinRequests() {
        Update update = new Update();
        update.chat_join_request = new ChatJoinRequest();
        update.chat_join_request.chat = new Chat();
        update.chat_join_request.from = new User();
        assertEquals(Update.Type.CHAT_JOIN_REQUEST, update.getType());
        assertEquals(update.chat_join_request.chat, update.chat());
        assertEquals(update.chat_join_request.from, update.fromUser());
    }

    @Test
    public void sendsScopedCommandsAndLanguage() throws Exception {
        RecordingApi api = new RecordingApi();
        api.setMyCommands(new BotCommand[] { new BotCommand("start", "Start") },
                new BotCommandScopeChatMember(-100L, 42L), "sv");
        String decoded = URLDecoder.decode(api.request, "UTF-8");
        assertTrue(decoded.contains("scope={\"type\":\"chat_member\",\"chat_id\":\"-100\",\"user_id\":42}"));
        assertTrue(decoded.contains("language_code=sv"));
    }

    @Test
    public void sendsPaymentTips() throws IOException {
        RecordingApi api = new RecordingApi();
        LabeledPrice price = new LabeledPrice();
        price.label = "item";
        price.amount = 100;
        api.sendInvoice(1, "title", "description", "payload", "provider", "start", "USD",
                new LabeledPrice[] { price }, 50, new int[] { 10, 20 }, null, null,
                0, 0, 0, false, false, false, false, false, 0, null);
        assertTrue(api.request.contains("max_tip_amount=50"));
        assertTrue(api.request.contains("suggested_tip_amounts="));
    }

    @Test
    public void sendsInvoiceToChannelUsername() throws IOException {
        RecordingApi api = new RecordingApi();
        LabeledPrice price = new LabeledPrice();
        api.sendInvoice("@shop", "title", "description", "payload", "provider", "start", "USD",
                new LabeledPrice[] { price }, null, null, false, false, false, false, false);
        assertTrue(api.request.contains("chat_id=%40shop"));
    }

    @Test
    public void sendsVideoChatAdministratorRight() throws IOException {
        RecordingApi api = new RecordingApi();
        api.promoteChatMember(1L, 2L, false, true, false, false, false, false, false, false, false, false);
        assertTrue(api.request.contains("can_manage_video_chats=true"));
    }

    @Test
    public void sendsProtectContent() throws IOException {
        RecordingApi api = new RecordingApi();
        api.setProtectContent(true);
        api.sendMessage(1, "secret");
        assertTrue(api.request.contains("protect_content=true"));
    }

    @Test
    public void sendsJoinRequestInviteOptions() throws IOException {
        RecordingApi api = new RecordingApi();
        api.createChatInviteLink(1, "review", null, null, true);
        assertTrue(api.request.contains("name=review"));
        assertTrue(api.request.contains("creates_join_request=true"));
    }

    @Test
    public void sendsWebAppMenuButton() throws Exception {
        RecordingApi api = new RecordingApi();
        api.setChatMenuButton(1L, new MenuButtonWebApp("Open", "https://example.com/app"));
        String decoded = URLDecoder.decode(api.request, "UTF-8");
        assertTrue(decoded.contains("\"type\":\"web_app\""));
        assertTrue(decoded.contains("\"url\":\"https://example.com/app\""));
    }

    @Test
    public void sendsTextChatTitle() throws Exception {
        RecordingApi api = new RecordingApi();
        api.setChatTitle("@my chat", "Årets vinnare & gäster");
        assertTrue(api.request.contains("chat_id=%40my+chat"));
        assertTrue(api.request.contains("title=%C3%85rets+vinnare+%26+g%C3%A4ster"));
    }

    @Test
    public void createsInvoiceLink() throws Exception {
        RecordingApi api = new RecordingApi();
        api.createInvoiceLink("Title", "Description", "payload", "provider", "SEK",
                new se.anyro.tgbotapi.types.payments.LabeledPrice[0], 0, null, null, null,
                0, 0, 0, false, false, false, false, true, true, false);
        assertTrue(api.request.contains("/createInvoiceLink?"));
        assertTrue(api.request.contains("currency=SEK"));
        assertTrue(api.request.contains("send_phone_number_to_provider=true"));
        assertTrue(api.request.contains("send_email_to_provider=true"));
    }

    @Test
    public void handlesChatWithoutType() {
        assertFalse(new Chat().isPrivate());
    }

    @Test
    public void supportsCustomEmojiTypes() throws Exception {
        Gson gson = new Gson();
        MessageEntity entity = gson.fromJson(
                "{\"type\":\"custom_emoji\",\"offset\":0,\"length\":2,\"custom_emoji_id\":\"emoji-1\"}",
                MessageEntity.class);
        assertEquals(MessageEntity.Type.CUSTOM_EMOJI, entity.getType());
        assertEquals("emoji-1", entity.custom_emoji_id);

        Sticker sticker = gson.fromJson(
                "{\"type\":\"custom_emoji\",\"custom_emoji_id\":\"emoji-1\"}", Sticker.class);
        assertEquals("custom_emoji", sticker.type);
        assertEquals("emoji-1", sticker.custom_emoji_id);

        StickerSet stickerSet = gson.fromJson("{\"sticker_type\":\"custom_emoji\"}", StickerSet.class);
        assertEquals("custom_emoji", stickerSet.sticker_type);
    }

    @Test
    public void sendsBotApiSixTwoStickerParameters() throws Exception {
        RecordingApi api = new RecordingApi();
        api.getCustomEmojiStickers(new String[] { "emoji-1", "emoji-2" });
        assertTrue(URLDecoder.decode(api.request, "UTF-8")
                .contains("custom_emoji_ids=[\"emoji-1\",\"emoji-2\"]"));

        api.createNewStickerSet(1L, "set", "Title", "file-id", "🙂", "custom_emoji", null);
        assertTrue(api.request.contains("sticker_type=custom_emoji"));
    }

    @Test
    public void managesDefaultAdministratorRights() throws Exception {
        RecordingApi api = new RecordingApi();
        ChatAdministratorRights rights = new ChatAdministratorRights();
        rights.can_delete_messages = true;
        api.setMyDefaultAdministratorRights(rights, true);
        String decoded = URLDecoder.decode(api.request, "UTF-8");
        assertTrue(decoded.contains("rights={"));
        assertTrue(decoded.contains("\"can_delete_messages\":true"));
        assertTrue(decoded.contains("for_channels=true"));

        api.getMyDefaultAdministratorRights(true);
        assertTrue(api.request.contains("for_channels=true"));
    }

    @Test
    public void supportsForumTopics() throws Exception {
        RecordingApi api = new RecordingApi();
        api.sendMessage(-100L, "Topic message", null, false, 0, null, 42);
        assertTrue(api.request.contains("message_thread_id=42"));

        api.createForumTopic(-100L, "Announcements", 0x6FB9F0, "emoji-1");
        assertTrue(api.request.contains("/createForumTopic?"));
        assertTrue(api.request.contains("name=Announcements"));
        assertTrue(api.request.contains("icon_custom_emoji_id=emoji-1"));

        Message message = new Gson().fromJson(
                "{\"message_id\":1,\"message_thread_id\":42,\"is_topic_message\":true,"
                        + "\"forum_topic_created\":{\"name\":\"Announcements\",\"icon_color\":7322096}}",
                Message.class);
        assertEquals(42, message.message_thread_id);
        assertTrue(message.is_topic_message);
        assertEquals("Announcements", message.forum_topic_created.name);
    }

    @Test
    public void sendsMediaAndInvoicesToForumTopics() throws Exception {
        RecordingApi api = new RecordingApi();
        api.sendPhoto("@chat", "photo", null, null, 0, null, false, 42);
        assertTrue(api.request.contains("message_thread_id=42"));

        LabeledPrice price = new LabeledPrice();
        api.sendInvoice("@chat", "Title", "Description", "payload", "provider", "start", "SEK",
                new LabeledPrice[] { price }, 0, null, null, null, 0, 0, 0, false, false, false, false,
                false, 0, null, 42);
        assertTrue(api.request.contains("message_thread_id=42"));
    }

    @Test
    public void supportsBotApiSixFourMediaSpoilersAndPersistentKeyboards() throws Exception {
        RecordingApi api = new RecordingApi();
        api.sendPhoto(1L, "photo-id", null, null, 0, null, true);
        assertTrue(api.request.contains("has_spoiler=true"));

        InputMediaPhoto photo = new InputMediaPhoto("photo-id");
        photo.has_spoiler = true;
        assertTrue(new Gson().toJson(photo).contains("\"has_spoiler\":true"));

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(new String[] { "Open" });
        keyboard.is_persistent = true;
        assertTrue(new Gson().toJson(keyboard).contains("\"is_persistent\":true"));

        api.editGeneralForumTopic(-100L + "", "General");
        assertTrue(api.request.contains("/editGeneralForumTopic?"));
    }
}
