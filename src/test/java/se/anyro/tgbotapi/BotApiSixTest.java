package se.anyro.tgbotapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.URLDecoder;

import org.junit.Test;

import se.anyro.tgbotapi.types.BotCommand;
import se.anyro.tgbotapi.types.BotCommandScopeChatMember;
import se.anyro.tgbotapi.types.ChatJoinRequest;
import se.anyro.tgbotapi.types.Chat;
import se.anyro.tgbotapi.types.User;
import se.anyro.tgbotapi.types.MenuButtonWebApp;
import se.anyro.tgbotapi.types.Update;
import se.anyro.tgbotapi.types.payments.LabeledPrice;

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
                0, 0, 0, false, false, false, false, false);
        assertTrue(api.request.contains("/createInvoiceLink?"));
        assertTrue(api.request.contains("currency=SEK"));
    }

    @Test
    public void handlesChatWithoutType() {
        assertFalse(new Chat().isPrivate());
    }
}
