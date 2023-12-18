package TeleBot;

public class TextsConteiner {
    public final String ANSWER_FOR_DICTIONARY = "Какое слово нужно перевести?";

    public String [] lesson1 = {"Page1", "Page2", "Page3"};

    String text2 = "<b>bold</b>, <strong>bold</strong>\n" +
            "<i>italic</i>, <em>italic</em>\n" +
            "<u>underline</u>, <ins>underline</ins>\n" +
            "<s>strikethrough</s>, <strike>strikethrough</strike>, <del>strikethrough</del>\n" +
            "<span class=\"tg-spoiler\">spoiler</span>, <tg-spoiler>spoiler</tg-spoiler>\n" +
            "<b>bold <i>italic bold <s>italic bold strikethrough <span class=\"tg-spoiler\">italic bold strikethrough spoiler</span></s> <u>underline italic bold</u></i> bold</b>\n" +
            "<a href=\"http://www.example.com/\">inline URL</a>\n" +
            "<a href=\"tg://user?id=123456789\">inline mention of a user</a>\n" +
            "<tg-emoji emoji-id=\"5368324170671202286\">\uD83D\uDC4D</tg-emoji>\n" +
            "<code>inline fixed-width code</code>\n" +
            "<pre>pre-formatted fixed-width code block</pre>\n" +
            "<pre><code class=\"language-python\">pre-formatted fixed-width code block written in the Python programming language</code></pre>";


}
