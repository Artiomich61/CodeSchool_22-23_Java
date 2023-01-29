package com.tgbot.TelegramBot.service;


import com.tgbot.TelegramBot.config.BotConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot{

    final BotConfig config;
    boolean upFile = false;
    boolean downFile = false;
    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> commandsList = new ArrayList<>();
        commandsList.add(new BotCommand("/start", "Sends a welcome message"));
        commandsList.add(new BotCommand("/mydata", "Get user info"));
        commandsList.add(new BotCommand("/upload", "Upload file"));
        commandsList.add(new BotCommand("/download", "Download file"));
        try{
            this.execute(new SetMyCommands(commandsList, new BotCommandScopeDefault(), null));
        }
        catch (TelegramApiException e){
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(upFile){
            if(update.hasMessage() && update.getMessage().hasDocument()){
                Document document = update.getMessage().getDocument();
                uploadFile(document.getFileName(), document.getFileId());
                sendMessage(update.getMessage().getChatId(), "Uploaded!");
                upFile = false;
            }
        } else if (downFile) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String fileName = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                downloadFile(chatId, fileName);
                downFile = false;
            }
        } else {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

                switch (messageText) {
                    case "/start":
                        StartCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    case "/mydata":
                        MyDataCommandReceived(chatId, update.getMessage().getChat());
                        break;
                    case "/upload":
                        UploadCommandReceived(chatId);
                        break;
                    case "/download":
                        DownloadCommandReceived(chatId);
                        break;
                    default:
                        sendMessage(chatId, "Sorry command wasn't recognized");
                        break;
                }
            }
        }
    }
    private void StartCommandReceived(long chatId, String name){
        String answer = "Hello, " + name + ", nice to meet you!";

        sendMessage(chatId, answer);
        log.info("Replied to user " + name);
    }

    private void MyDataCommandReceived(long chatId, Chat chat){
        String answer = "First name: " + chat.getFirstName() + "\nLast name: " + chat.getLastName()
                + "\nBiography: " + chat.getBio() + "\nUsername: " + chat.getUserName();

        sendMessage(chatId, answer);
        log.info("Replied to user " + chat.getFirstName());
    }

    private void UploadCommandReceived(long chatId){
        upFile = true;
        sendMessage(chatId, "Send me a file pls.");
    }

    private void DownloadCommandReceived(long chatId){
        downFile = true;
        sendMessage(chatId, "Send me a name of file pls.");
    }

    private void sendMessage(long chatId, String text){
        SendMessage message = new SendMessage();

        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void sendFile(long chatId, File file){
        SendDocument document = new SendDocument();

        document.setChatId(String.valueOf(chatId));
        document.setDocument(new InputFile(file));

        try {
            execute(document);
            System.out.println("Downloaded!");
        }
        catch (TelegramApiException e){
            log.error("Error with file sending: " + e.getMessage());
        }
    }

    @SneakyThrows
    private void uploadFile(String file_name, String file_id){
        URL url = new URL("https://api.telegram.org/bot" + getBotToken() + "/getFile?file_id=" + file_id);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String getFileResponse = bufferedReader.readLine();

        JSONObject jresult = new JSONObject(getFileResponse);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");

        File localFile = new File("src/main/resources/uploaded_files/" + file_name);
        InputStream inputStream = new URL("https://api.telegram.org/file/bot" + getBotToken() + "/" + file_path).openStream();

        FileUtils.copyInputStreamToFile(inputStream, localFile);

        bufferedReader.close();
        inputStream.close();

        System.out.println("Uploaded!");
    }

    private void downloadFile(long chatId, String fileName){
        File file = new File("src/main/resources/uploaded_files/" + fileName);
        sendFile(chatId, file);
    }

}
