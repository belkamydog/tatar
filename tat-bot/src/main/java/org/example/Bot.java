package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    private void mainMenu(Update update){
        Message message = update.getMessage();
        SendMessage send_message = new SendMessage();
        send_message.enableMarkdown(true);
        send_message.setChatId(message.getChatId().toString());
        send_message.setText("main menu");

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
        edit_message_text.setText("main");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row1 = getButtons(bot_conf_.ROW_BTN_1_LIST);
        List<InlineKeyboardButton> row2 = getButtons(bot_conf_.ROW_BTN_2_LIST);
        rowsInline.add(row1);
        rowsInline.add(row2);

        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
    }
     private void Dict(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.setText(text_container_.ANSWER_FOR_DICTIONARY);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        String [] t1 = {bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN};
        List<String> r1 = Arrays.stream(t1).toList();
        List<InlineKeyboardButton> row = getButtons(r1);
        rowsInline.add(row);
        markupInline.setKeyboard(rowsInline);
        edit_message_text.setReplyMarkup(markupInline);
        execute(edit_message_text);
    }
    private void Lessons(Update update) throws TelegramApiException {
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText edit_message_text = new EditMessageText();
        edit_message_text.setChatId(String.valueOf(chat_id));
        edit_message_text.setMessageId((int) message_id);
        edit_message_text.setText("Уроки");

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
                    mainMenu(update);
                }
            }
        }
        else if (update.hasCallbackQuery()){
            try {
                answerTheMessage(update);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
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

    private void answerTheMessage(Update update) throws TelegramApiException {
        String input = update.getCallbackQuery().getData();
        if (input.equals(bot_conf_.NAME_DICT_BTN)) Dict(update);
        else if (input.equals(bot_conf_.NAME_LESSONS_BTN)) Lessons(update);
        else if (input.equals(bot_conf_.NAME_BACK_TO_MAIN_MENU_BTN)) MainMenu(update);

        else if (itsNameLesson(input, bot_conf_.LESSONS_ROW_1)) System.out.println();
        else if (itsNameLesson(input, bot_conf_.LESSONS_ROW_2)) System.out.println();
    }



    public void sendMsg (Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Словарь");
        keyboardFirstRow.add("Тесты");

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Уровень");
        keyboardSecondRow.add("Тексты");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
