package se.anyro.tgbotapi.types;

public class MenuButton {
    public String type;
    public String text;
    public WebAppInfo web_app;
    public MenuButton(String type) { this.type = type; }
    public static MenuButton commands() { return new MenuButton("commands"); }
    public static MenuButton defaultButton() { return new MenuButton("default"); }
    public static MenuButton webApp(String text, String url) { MenuButton b = new MenuButton("web_app"); b.text = text; b.web_app = new WebAppInfo(url); return b; }
}
