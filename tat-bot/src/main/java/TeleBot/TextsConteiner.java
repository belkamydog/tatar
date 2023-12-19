package TeleBot;

public class TextsConteiner {
    public final String DEVELOPER_PAGE = "Извините страничка в стадии разработки";

    public String text2 = "<b>bold</b>, <strong>bold</strong>\n" +
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


    private final String lp1 = """
            Урок первый 🅰️ Беренче дәрес
      
            Здравствуйте! Рəхим Итегез!
            
            Начнем обучение с татарского алфавита.
            Он составлен на основе русской графики, состоит из 39 букв:
            Аа    Зз    Пп    Чч
            <u>Әə</u>    Ии    Рр    Шш
            Бб    Йй    Сс    Щщ
            Вв    Кк    Тт    Ъъ
            Гг    Лл    Уу    Ыы
            Дд    Мм    <u>Үү</u>    Ьь
            Ее    Нн    Фф    Ээ
            Ёё    <u>Ңң</u>    Хх    Юю
            Жж    Оо    <u>Һһ</u>    Яя
            <u>Җҗ</u>    <u>Өө</u>    Цц
            Сколько дополнительных букв Вам удалось найти?
            <tg-spoiler>Как видите, в татарском языке есть шесть дополнительных букв, которых нет в русском языке.</tg-spoiler>
            """;

    private final String lp2 = """
            \\[ә]
            \\[ә] – этот звук иначе можно обозначить как очень мягкий \\[“а].
            Он близок к русскому \\[“а] в словах “сядь”, “глянь”, “ряд”.
            При произнесении буквы “ә” опустите кончик языка к нижним зубам.
            Кстати, звук \\[ә] есть в английском языке: black, hat – \\[blæk], \\[hæt].
            """;

    private final String lp3 = """
            Слова с буквой ә:
            
            әни - мама
            әти - папа
            әйдә - давай
            әйбер - вещь
            бәйрәм - праздник
            кәләм - карандаш
            дәрес - урок
            бәхет - счастье
            бәлән - c
            бәтә - заканчивается
            
            При произнесении \\[ә] самой частой является ошибка, появившаяся в силу влияния графики: 
                при чтении саму букву путают с русской ‘е’.
            Необходимо сразу отметить, что между ними нет никакого родства.
            Более того, звуки [е] и [ә] нередко взаимозаменяют себя в схожих позициях, влияя на значение слова.
            Например: ишетә – ишетте (слышит- услышал)
            """;

    public String [] lesson1 = {lp1, lp2, lp3};


}
//**сам ты жирный**
//        __курсив__
//        `код`
//        ~~перечеркнутый~~
//        ```блок кода```
//        ||скрытый текст||
//        Но что со ссылками? Ссылки в разметке markdown выглядят вот так:
//        (ссылка)[https://smth.com]