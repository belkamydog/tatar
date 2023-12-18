package TeleBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

public class BotConfiguration {




    // buttons names
    public final String NAME_DICT_BTN = "Словарь";
    public final String NAME_BACK_TO_MAIN_MENU_BTN = "В главное меню";
    public final String NAME_LESSONS_BTN = "Уроки";
    public final String NAME_MAKE_TEST_BTN = "Пройти тест";
    public final String NAME_GRAMMAR_BTN = "Граматика";
    public final String NAME_MEM_BTN = "Мемасики";
    public final String NAME_LEVEL_TEST_BTN = "Уровень владения";
    public final String NEXT_BTN = "Вперед";
    public final String BACK_BTN = "Назад";


    public final String [] ROW_BTN_1 = {NAME_DICT_BTN, NAME_MAKE_TEST_BTN, NAME_GRAMMAR_BTN};
    public final List <String> ROW_BTN_1_LIST = Arrays.stream(ROW_BTN_1).toList();
    public final  String [] ROW_BTN_2 = {NAME_LESSONS_BTN, NAME_MEM_BTN, NAME_LEVEL_TEST_BTN};
    public final List <String> ROW_BTN_2_LIST = Arrays.stream(ROW_BTN_2).toList();

    public  final String [] LESSONS_ROW_1 = {"1","2","3","4","5"};
    public  final String [] LESSONS_ROW_2 = {"6","7","8","9","10"};

    public int  lesson_page = 0;
}

