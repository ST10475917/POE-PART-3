/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepart1;

/**
 *
 * @author Student
 */

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public final class Message {

    public String messageID;
    int num;
    String recipient;
    String content;
    String hash;
    String flag;   // NEW for Part 3: Sent / Stored / Disregard
    String sender; // NEW for Part 3: your cell number
    static int total = 0;
    static ArrayList<Message> allMessages = new ArrayList<>();
    Random rand = new Random();

    // ===== YOUR OLD CONSTRUCTOR - KEPT =====
    public Message(int num, String recipient, String content) {
        this.messageID = "" + (1000000000L + rand.nextInt(900000000));
        this.num = num;
        this.recipient = recipient;
        this.content = content;
        this.hash = createMessageHash();
        this.flag = "Sent"; // default
        this.sender = "0838884567"; // your number
    }

    // ===== NEW CONSTRUCTOR FOR PART 3 TEST DATA =====
    public Message(String recipient, String content, String flag, int count, String sender) {
        this.messageID = "" + (1000000000L + rand.nextInt(900000000));
        this.num = count;
        this.recipient = recipient;
        this.content = content;
        this.flag = flag;
        this.sender = sender;
        this.hash = createMessageHash();
    }

    // ===== YOUR OLD METHODS - KEPT =====

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkMessageLength() {
        return content.length() <= 250;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured";
        } else if (recipient.startsWith("0") && recipient.length() == 10) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again";
        }
    }

    public String createMessageHash() {
        if (content == null || content.trim().isEmpty()) {
            return "00:0:NONE";
        }
        String[] words = content.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String last = words[words.length - 1].replaceAll("[^A-Za-z]", "").toUpperCase();
        String digits = recipient.replaceAll("[^0-9]", "");
        String first2 = digits.length() >= 2 ? digits.substring(0, 2) : "00";
        return first2 + ":" + num + ":" + first + last;
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1 -> {
                flag = "Sent";
                allMessages.add(this);
                total++;
                return "Message successfully sent.";
            }
            case 2 -> {
                flag = "Disregard";
                allMessages.add(this);
                return "Press 0 to delete message.";
            }
            case 3 -> {
                flag = "Stored";
                allMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            }
            default -> {
            }
        }
        return "Invalid choice.";
    }

    public String printMessages() {
        String output = "";
        for (Message m : allMessages) {
            output += "Message Hash: " + m.hash + "\nRecipient: " + m.recipient + "\nMessage: " + m.content + "\n\n";
        }
        return output.isEmpty() ? "No messages." : output;
    }

    public int returnTotalMessages() {
        return total;
    }

    public void storeMessage() {
        // For Part 3 - stored messages are kept in allMessages
        // and also saved to JSON by StoredMessages class
        JOptionPane.showMessageDialog(null, "Message stored.");
    }

    // ===== NEW GETTERS / SETTERS FOR PART 3 =====
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public String getMessage() { return content; }
    public String getHash() { return hash; }
    public String getMessageHash() { return hash; }
    public String getFlag() { return flag; }
    public String getSender() { return sender; }
    public void setFlag(String flag) { this.flag = flag; }
    public void setSender(String sender) { this.sender = sender; }

    @Override
    public String toString() {
        return "Message Hash: " + hash + "\nRecipient: " + recipient + "\nMessage: " + content;
    }
}