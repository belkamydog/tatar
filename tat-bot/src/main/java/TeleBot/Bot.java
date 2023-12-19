package TeleBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    BotConfiguration bot_conf_;
    TextsConteiner text_container_;
    public Bot(){
        bot_conf_ = new BotConfiguration();
        text_container_ = new TextsConteiner();
    }
    @Override
    public String getBotUsername() {
        return bot_conf_.BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return bot_conf_.BOT_TOKEN;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message != null && message.hasText()){
                if (message.getText().equals("/start")){
                    StartMenu(update);
                }
            }
        }
        else if (update.hasCallbackQuery()){
            try {
                answerTheMessage(update);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void StartMenu(Update update){
        Message message = update.getMessage();
        SendMessage send_message = new SendMessage();
        //send_message.enableMarkdown(true);
        send_message.enableHtml(true);
        send_message.setChatId(message.getChatId().toString());
        send_message.setText(bot_conf_.MAIN_MENU_NAME);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row1 = getButtons(bot_conf_.ROW_BTN_1_LIST);
        List<InlineKeyboardButton> row2 = getButtons(bot_conf_.ROW_BTN_2_LIST);
        rowsInline.add(row1);
        rowsInline.add(row2);

        markupInline.setKeyboard(rowsInline);
        send_message.setReplyMarkup(markupInline);
        SendMsg(send_message);
    }
    private void MainMenu(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.enableHtml(true);
        edit_message_text.setText(bot_conf_.MAIN_MENU_NAME);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row1 = getButtons(bot_conf_.ROW_BTN_1_LIST);
        List<InlineKeyboardButton> row2 = getButtons(bot_conf_.ROW_BTN_2_LIST);
        rowsInline.add(row1);
        rowsInline.add(row2);

        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
        bot_conf_.lesson_page = 0;
    }
    private void Dict(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.setText(text_container_.DEVELOPER_PAGE);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<InlineKeyboardButton> row = getButtons(r1);
        rowsInline.add(row);
        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
        bot_conf_.lesson_page = 0;
    }
    private void Lessons(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.enableHtml(true);
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.setText(bot_conf_.LESSONS_MENU_NAME);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row1 = getButtons(Arrays.stream(bot_conf_.LESSONS_ROW_1).toList());
        List<InlineKeyboardButton> row2 = getButtons(Arrays.stream(bot_conf_.LESSONS_ROW_2).toList());
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<InlineKeyboardButton> back = getButtons(r1);
        rowsInline.add(row1);
        rowsInline.add(row2);
        rowsInline.add(back);
        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
        bot_conf_.lesson_page = 0;
    }
    private void SendPic(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        SendPhoto photo = new SendPhoto();
        photo.setChatId(String.valueOf(chat_id));
        photo.setPhoto(new InputFile(new File("/Users/artemefimov/Documents/GitHub/tatarlingue/tatar/tat-bot/src/Elka-3.bmp")));
        photo.setCaption("Send Image");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<InlineKeyboardButton> row = getButtons(r1);
        rowsInline.add(row);
        markupInline.setKeyboard(rowsInline);
        photo.setReplyMarkup(markupInline);
        execute(photo);
    }
    private static List<InlineKeyboardButton> getButtons(List <String> names) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String i : names){
            var button = new InlineKeyboardButton();
            button.setText(i);
            button.setCallbackData(i);
            row.add(button);
        }
        return row;
    }
    private void SendMsg(SendMessage sm){
        try{
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean itsNameLesson(String user_input, String [] lessons){
        for (String i : lessons){
            if (i.equals(user_input)) {
                return true;
            }
        }
        return  false;
    }

    private void DoLesson(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();

        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.enableHtml(true);
        edit_message_text.setText(text_container_.lesson1[bot_conf_.lesson_page]);
        System.out.println(bot_conf_.lesson_page);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        String [] t2 = {bot_conf_.NEXT_BTN, bot_conf_.BACK_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<String> r2 = Arrays.stream(t2).toList();
        List<InlineKeyboardButton> row = getButtons(r1);
        List<InlineKeyboardButton> row2 = getButtons(r2);
        rowsInline.add(row);
        rowsInline.add(row2);
        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
    }

    private EditMessageText CreateLessonMessage(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();

        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.enableMarkdown(true);
        edit_message_text.setText(text_container_.lesson1[bot_conf_.lesson_page]);
        System.out.println(bot_conf_.lesson_page);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        String [] t2 = {bot_conf_.NEXT_BTN, bot_conf_.BACK_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<String> r2 = Arrays.stream(t2).toList();
        List<InlineKeyboardButton> row = getButtons(r1);
        List<InlineKeyboardButton> row2 = getButtons(r2);
        rowsInline.add(row);
        rowsInline.add(row2);
        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        return  edit_message_text;
    }

    private void PushNextBtn(Update update) throws TelegramApiException {
        if (bot_conf_.lesson_page < text_container_.lesson1.length) {
            bot_conf_.lesson_page++;
            EditMessageText em = CreateLessonMessage(update);
            execute(em);
        }
    }

    private void PushBackBtn(Update update) throws TelegramApiException {
        if (bot_conf_.lesson_page > 0) {
            bot_conf_.lesson_page--;
            EditMessageText em = CreateLessonMessage(update);
            execute(em);
        }
    }

    private void answerTheMessage(Update update) throws TelegramApiException, FileNotFoundException {
        String input = update.getCallbackQuery().getData();
        if (input.equals(bot_conf_.NAME_DICT_BTN)) Dict(update);
        else if (input.equals(bot_conf_.NAME_LESSONS_BTN)) Lessons(update);
        else if (input.equals(bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN)) MainMenu(update);
        else if (input.equals(bot_conf_.NAME_MEM_BTN)) Dict(update);
        else if (input.equals(bot_conf_.NAME_GRAMMAR_BTN)) Dict(update);
        else if (input.equals(bot_conf_.NAME_MAKE_TEST_BTN)) Dict(update);
        else if (input.equals(bot_conf_.NAME_LEVEL_TEST_BTN)) Dict(update);

        else if (itsNameLesson(input, bot_conf_.LESSONS_ROW_1)) DoLesson(update);
        else if (itsNameLesson(input, bot_conf_.LESSONS_ROW_2)) DoLesson(update);
        else if (input.equals(bot_conf_.NEXT_BTN)) PushNextBtn(update);
        else if (input.equals(bot_conf_.BACK_BTN)) PushBackBtn(update);
    }
}
