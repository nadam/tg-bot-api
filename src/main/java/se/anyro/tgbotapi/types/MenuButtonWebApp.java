package se.anyro.tgbotapi.types;
public class MenuButtonWebApp extends MenuButton {
    public MenuButtonWebApp(String text, WebAppInfo webApp) { super("web_app"); this.text = text; this.web_app = webApp; }
    public MenuButtonWebApp(String text, String url) { this(text, new WebAppInfo(url)); }
}
