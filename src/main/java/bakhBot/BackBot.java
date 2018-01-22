package bakhBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

/**
 * Created by Stan on 21.01.2018.
 */
public class BackBot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BackBot());
            System.out.println("Bot started");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        Message msg = updates.get(0).getMessage();
        String txt = msg.getText();
        System.out.println(txt);
//        SendDocument outSendDoc = new SendDocument();
//        File file = new File("c:\\temp\\New_Blank_Document.pdf");
//        outSendDoc.setNewDocument(file);
//        outSendDoc.setChatId(msg.getChatId());
        if (txt.equals("/start")) {
            sendMsg(msg, "Hi!");
//          sendDoc(outSendDoc);
        }

    }

    @Override
    public String getBotUsername() {
        return "BakhTestBot";
    }

    @Override
    public String getBotToken() {
        return "530379851:AAEFhReRF0It2YoklSwIJ7NiRNJjgJ_M6BY";
    }

    @Override
    public void onClosing() {

    }

    private void sendDoc(SendDocument sendDoc) {
        try {
            System.out.println("sending file" + sendDoc.toString());
            sendDocument(sendDoc);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("deprecation")
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
