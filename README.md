TgBotApi
========

This Java library implements all the methods and types in [Telegram's Bot API](https://core.telegram.org/bots) including the new features in 3.2. [Telegram](https://telegram.org/) is an instant messenger for various platforms.

If you find a bug or know some way to improve the library please report it to me as [an issue](https://github.com/nadam/tg-bot-api/issues) or [in private](https://telegram.me/nadam). Just don't ask me to add boiler plate stuff like getters and setters. There are lots of other Java libraries if you prefer that.

Technical scope and limitations
-------------------------------
- Uses GET methods with URL query string where possible (all methods except when sending files as multipart/form-data)
- Supports getUpdates(), but I've only tested it with [webhook](https://core.telegram.org/bots/api#setwebhook) so far
- [Making requests when getting updates](https://core.telegram.org/bots/api#making-requests-when-getting-updates) is not supported

Features
--------
- Full implementation of the API and hopefully quickly updated when new features are released
- Javadoc links to corresponding official documentation
- All types implemented as simple POJO classes with constructors and helper methods where necessary
- All methods implemented as Java methods with various parameter options
- A setSilent() method for bots who want to use disable_notification instead of having to set it each time you send a message
- Helper methods to make life easier, for instance `TgBotApi.sendReply()`, `TgBotApi.downloadFile()` and `TgBotApi.debug()`
- Enums and constants for common values

Including in your project
-------------------------
#### Dependencies
The project depends on [GSON](https://github.com/google/gson 2.5). I have currently only used it together with [Google App Engine](https://cloud.google.com/appengine/) (which includes GSON), but it should be possible to use it with any platform.

#### Binary
No binaries published yet. Will be published when the project is more stable.

#### Building from source code
A pom.xml for Maven is included in the project. For other options just make sure you include GSON 2.5 or later.

Using the library
-----------------
#### Telegram Bot API
If you are new to bots or bot development for Telegram check out the following links:
- [Introduction to bots](https://core.telegram.org/bots)
- [Telegram Bot API documentation](https://core.telegram.org/bots/api)
- [Bots FAQ](https://core.telegram.org/bots/faq)

#### Preparation
1. Setup your bot using [@BotFather](https://telegram.me/botfather) as described in the links above to get your bot username and token
2. Use [@userinfobot](https://telegram.me/userinfobot) or similar to get your user id

#### Sample code for Google App Engine using webhooks
1. For Google App Engine or similar, extend HttpServlet and implement the doPost() method.
2. Optionally implement the ErrorListener interface.
3. Create the `TgBotApi` object using your token and user id (it's good practice to put these values as constants in a separate file).
4. Parse the requests using `TgBotApi.parseFromWebhook()` to get the `Update` object
5. Optionally send a reply using `TgBotApi.sendMessage()` or `TgBotApi.sendReply()`

```java
public class ExampleServlet extends HttpServlet implements ErrorListener {

    private TgBotApi api;

    public ExampleServlet() {
        super();
        api = new TgBotApi(TOKEN, OWNER, this);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setStatus(200);

        String messageText;
        long chatId = 0;

        try {
            Update update = api.parseFromWebhook(req.getReader());            
            
            // Do something interesting,
            // extract the chatId from the update object and
            // decide an appropriate messageText to send as reply.
            
        } catch (Exception e) {
            api.debug(e);
            return;
        }

        api.sendMessage(chatId, messageText);
    }

    @Override
    public void onError(int errorCode, String description) {
        api.debug(new Exception("ErrorCode " + errorCode + ", " + description));
    }
}
```

The code for other platforms will look quite similar.

More information
----------------
#### Bots using TgBotApi
- [TgBotApi Example](https://github.com/nadam/tg-bot-api-example) [@TgBotApiExampleBot](https://telegram.me/TgBotApiExampleBot)
- [Userinfobot](https://github.com/nadam/userinfobot) [@userinfobot](https://telegram.me/userinfobot)
- Groupinfobot [@groupinfobot](https://telegram.me/groupinfobot)
- [Chat Bridge Bot](https://github.com/nadam/chatbridgebot) - [@chatbridgebot](https://telegram.me/chatbridgebot)
- [Json Dump Bot](https://github.com/nadam/jsondumpbot) - [@JsonDumpBot](https://telegram.me/JsonDumpBot)
- Jackpot Bot - [@jackpot_bot](https://telegram.me/jackpot_bot)

#### Advanced Java for Telegram
If you think the Bot API is too limiting you can use the Telegram API instead which is what ordinary Telegram clients use. You use it with an ordinary Telegram account or with a Bot account (using auth.importBotAuthorization).

- [Deepthought by Ruben Bermudez](https://github.com/rubenlagus/Deepthought)
- [Official outdated documentation](https://core.telegram.org/#telegram-api)

#### Other stuff
- [Google App Engine tutorial for Java](https://cloud.google.com/appengine/docs/java/gettingstarted/creating-guestbook)

License
----------------
[Apache License 2.0](LICENSE)