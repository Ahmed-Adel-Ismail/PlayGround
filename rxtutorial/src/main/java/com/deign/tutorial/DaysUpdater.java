package com.deign.tutorial;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.xml.stream.Location;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * a functional class that adds to the Date passed the given days
 */
public class DaysUpdater implements BiFunction<Date, Integer, Date> {

    @Override
    public Date apply(@NonNull Date date, @NonNull Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}

//class Ticket {
//
//    private int id = (int) (Math.random() * 10000);
//
//    public int getId() {
//        return id;
//    }
//
//}
//
//class TicketsTeller {
//
//    private final List<Integer> soldTicketIds = new ArrayList<>();
//
//    public Ticket buyTicket() {
//        Ticket ticket = new Ticket();
//        soldTicketIds.add(ticket.getId());
//        return ticket;
//    }
//
//    public int soldTicketsCount() {
//        return soldTicketIds.size();
//    }
//}
//
//class Sound {
//
//    private int highFrequency;
//    private int lowFrequency;
//    private long repeatAfterMillis;
//
//    public Sound(int highFrequency, int lowFrequency, long repeatAfterMillis) {
//        this.highFrequency = highFrequency;
//        this.lowFrequency = lowFrequency;
//        this.repeatAfterMillis = repeatAfterMillis;
//    }
//
//    public void updateHighFrequency(int hertz) {
//        highFrequency += hertz;
//    }
//
//    public void updateLowFrequency(int hertz) {
//        lowFrequency += hertz;
//    }
//
//    public void updateRepeatAfterMillis(long millis) {
//        repeatAfterMillis += millis;
//    }
//
//    public Sound copy() {
//        return new Sound(highFrequency, lowFrequency, repeatAfterMillis);
//    }
//
//    public void playSound() {
//        // ...
//    }
//
//}
//
//class DelayFilter implements Function<Sound, Sound> {
//
//    @Override
//    public Sound apply(Sound sound) {
//        Sound newSound = sound.copy();
//        newSound.updateHighFrequency(100);
//        newSound.updateRepeatAfterMillis(20);
//        return newSound;
//    }
//}
//
//class EchoFilter implements Function<Sound, Sound> {
//
//    @Override
//    public Sound apply(Sound sound) {
//        Sound newSound = sound.copy();
//        newSound.updateLowFrequency(50);
//        newSound.updateRepeatAfterMillis(200);
//        return newSound;
//    }
//}
//
//class ExtraBassFilter implements Function<Sound, Sound> {
//
//    @Override
//    public Sound apply(Sound sound) {
//        Sound newSound = sound.copy();
//        newSound.updateLowFrequency(8000);
//        return newSound;
//    }
//}
//
//class LiveEffectFilter implements Function<Sound, Sound> {
//
//    @Override
//    public Sound apply(Sound sound) {
//        Sound newSound = sound.copy();
//        newSound.updateHighFrequency(800);
//        newSound.updateLowFrequency(400);
//        return newSound;
//    }
//}
//
//
//class Run {
//
//    public void playSound() {
//        Sound musicTrack = new Sound(0, 0, 0);
//        musicTrack = new LiveEffectFilter().apply(musicTrack);
//        musicTrack = new EchoFilter().apply(musicTrack);
//        musicTrack = new ExtraBassFilter().apply(musicTrack);
//        musicTrack = new DelayFilter().apply(musicTrack);
//        musicTrack.playSound();
//    }
//
//    public void updateFiles() {
//        StorageManager manager = new StorageManager();
//        manager.readOldFileFromDisk();
//        manager.scanOldFileForNewLines();
//        manager.scanOldFileForWhiteSpaces();
//        manager.fillInNewFile();
//    }
//}
//
//
//class StorageManager {
//
//    private static final String OLD_FILE_PATH = "my_old_text_file.txt";
//    private static final String NEW_FILE_PATH = "my_new_text_file.txt";
//    private File oldFile;
//    private File newFile;
//
//    public void readOldFileFromDisk() {
//        oldFile = new File(OLD_FILE_PATH);
//    }
//
//    public void scanOldFileForWhiteSpaces() {
//        if (oldFile != null) {
//            // process on oldFile variable
//        } else {
//            throw new UnsupportedOperationException("invoke readOldFileFromDisk() first");
//        }﻿
//    }
//
//    public void scanOldFileForNewLines() {
//        if (oldFile != null) {
//            // process on oldFile variable
//        } else {
//            throw new UnsupportedOperationException("invoke readOldFileFromDisk() first");
//        }﻿
//    }
//
//    public void fillInNewFile() {
//        newFile = new File(NEW_FILE_PATH);
//        // ...
//    }
//}
//
//
////class AndroidApplication extends Application {
////
////    @Override
////    public void onCreate() {
////        super.onCreate();
////        ApplicationInitializer initializer = new ApplicationInitializer();
////        initializer.initializeDatabase();
////        Date startDate = initializer.detectApplicationStartDate();
////        initializer.writeStartDateToDatabase(startDate);
////        initializer.initializeExternalLibraries();
////    }
////
////}
//
//
//class ApplicationInitializer {
//
//    public void initializeDatabase() {
//        // ...
//    }
//
//    public Date detectApplicationStartDate() {
//        return new Date();
//    }
//
//    public void writeStartDateToDatabase(Date date) {
//        // ...
//    }
//
//    public void initializeExternalLibraries() {
//        // ...
//    }
//
//}
//
//
//class Calculator {
//
//    private int firstNumber;
//    private int secondNumber;
//    private int result;
//
//    public int getFirstNumber() {
//        return firstNumber;
//    }
//
//    public void setFirstNumber(int firstNumber) {
//        this.firstNumber = firstNumber;
//    }
//
//    public int getSecondNumber() {
//        return secondNumber;
//    }
//
//    public void setSecondNumber(int secondNumber) {
//        this.secondNumber = secondNumber;
//    }
//
//    public int getResult() {
//        return result;
//    }
//
//    public void setResult(int result) {
//        this.result = result;
//    }
//}
//
//class Main {
//
//    public void sumValues(Calculator c) {
//        int result = c.getFirstNumber() + c.getSecondNumber();
//        c.setResult(result);
//    }
//
//    public void print(int key) {
//        switch (key) {
//            case 1:
//                System.out.println("ONE RECEIVED");
//                break;
//            case 2:
//                System.out.println("TWO RECEIVED");
//                break;
//            case 3:
//                System.out.println("THREE RECEIVED");
//                break;
//        }
//    }
//
//    public void takeAction(Printable printable) {
//        printable.print();
//    }
//
//}
//
//interface Printable {
//    void print();
//}
//
//
//class PrinterOne implements Printable {
//    @Override
//    public void print() {
//        System.out.println("ONE RECEIVED");
//    }
//}
//
//class PrinterTwo implements Printable {
//    @Override
//    public void print() {
//        System.out.println("TWO RECEIVED");
//    }
//}
//
//
//class NumberGenerator implements Callable<Integer> {
//    public Integer call() {
//        return (int) (Math.random() * 100);
//    }
//}
//
//class Printer {
//
//    private NumberGenerator generator;
//
//    public Printer(NumberGenerator generator) {
//        this.generator = generator;
//    }
//
//    public void printNumber() {
//        System.out.println(generator.call());
//    }
//}
//
//class PrinterWrapper {
//
//    private final Printer printer;
//
//    public PrinterWrapper(Printer printer) {
//        this.printer = printer;
//    }
//
//    public void print(NumberGenerator generator) {
//        printer.printNumber(generator);
//    }
//}
//
//
//class EmailSender {
//
//    public void send(SendParams params) {
//        // ...
//    }
//
//    public static class SendParams {
//        private String email;
//        private String title;
//        private String signature;
//        private String content;
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getSignature() {
//            return signature;
//        }
//
//        public void setSignature(String signature) {
//            this.signature = signature;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//    }
//
//}
//
//
//class LocationManager {
//
//    public boolean isGpsLocationEnabled() {
//        return true;
//    }
//
//    public boolean isNetworkLocationEnabled() {
//        return true;
//    }
//
//    public Location pollOneLocation() {
//        return null;
//    }
//
//    public void listenOnLocationChanges(Consumer<Location> listener) {
//        // listen on location
//    }
//
//}
//
//
//class LocationAvailability {
//
//    public boolean isGpsLocationEnabled() {
//        // ...
//    }
//
//    public boolean isNetworkLocationEnabled() {
//        // ...
//    }
//
//}
//
//class LocationRetriever {
//
//    public Location pollOneLocation() {
//        // ...
//    }
//
//    public void listenOnLocationChanges(Consumer<Location> listener) {
//        // listen on location
//    }
//}
//
//class GpsLocationAvailabilityChecker implements Callable<Boolean> {
//    @Override
//    public Boolean call() {
//        // check for Gps Location availability
//    }
//}
//
//class NetworkLocationAvailabilityChecker implements Callable<Boolean> {
//    @Override
//    public Boolean call() {
//        // check for Network Location availability
//    }
//}
//
//
//class OneLocationRetriever implements Callable<Location> {
//    @Override
//    public Location call() {
//        // poll one location
//    }
//}
//
//class MultipleLocationsRetriever implements Consumer<Consumer<Location>> {
//    @Override
//    public void accept(Consumer<Location> locationListener) {
//        // listen on location
//    }
//}
//
//
//class SendMessageApi implements Function<String, Boolean> {
//    @Override
//    public Boolean apply(@NonNull String messageContent) throws Exception {
//        // send the messageContent to server and return true
//        // if sent, or false if failed
//    }
//}
//
//class MessageFormatter implements Function<String, String> {
//    @Override
//    public String apply(@NonNull String originalMessage) throws Exception {
//        // format the originalMessage and return a formatted String
//    }
//}
//
//class MessageSender implements Function<String, Boolean> {
//
//    Function<String, String> messageFormatter;
//    Function<String, Boolean> messageApi;
//
//    @Override
//    public Boolean apply(@NonNull String message) throws Exception {
//        String formattedMessage = messageFormatter.apply(message);
//        return messageApi.apply(formattedMessage);
//    }
//}
//
//class Messanger {
//
//    Function<String, Boolean> messageSender;
//
//    public void onClickSendButton(String message) throws Exception {
//        boolean messageSent = messageSender.apply(message);
//        if (!messageSent) {
//            // show error
//        }
//    }
//
//}