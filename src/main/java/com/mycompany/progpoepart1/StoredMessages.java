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
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class StoredMessages {

    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();

    // Clears everything so tests don't duplicate
    public static void clearAll() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        Message.allMessages.clear();
        Message.total = 0;
    }

    // Test data from PDF - call this once at start
    public static void populateTestData(String sender) {
        clearAll();
        addMessage(new Message("+27834557896", "Did you get the cake?", "Sent", 1, sender));
        addMessage(new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", 2, sender));
        addMessage(new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard", 3, sender));
        addMessage(new Message("0838884567", "It is dinner time !", "Sent", 4, sender));
        addMessage(new Message("+27838884567", "Ok, I am leaving without you.", "Stored", 5, sender));
    }

    public static void addMessage(Message m) {
        messageHashes.add(m.getHash());
        messageIDs.add(m.getMessageID());
        if (m.getFlag().equalsIgnoreCase("Sent")) {
            sentMessages.add(m);
        } else if (m.getFlag().equalsIgnoreCase("Stored")) {
            storedMessages.add(m);
        } else if (m.getFlag().equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(m);
        }
        Message.allMessages.add(m);
    }

    // 2a - Sender + Recipient of stored
    public static String displaySenderRecipient() {
        String out = "";
        for (Message m : storedMessages) {
            out += "Sender: " + m.getSender() + " | Recipient: " + m.getRecipient() + "\n";
        }
        return out;
    }

    // 2b - Longest stored message
    public static String displayLongestMessage() {
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.getContent().length() > longest.getContent().length()) {
                longest = m;
            }
        }
        return longest.getContent();
    }

    // 2c - Search by Message ID
    public static String searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.getMessageID().equals(id) || m.getRecipient().equals(id)) {
                return m.getContent();
            }
        }
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id) || m.getRecipient().equals(id)) {
                return m.getContent();
            }
        }
        return "Message ID not found.";
    }

    // 2d - Search all for recipient
    public static String searchByRecipient(String recipient) {
        String out = "";
        for (Message m : sentMessages) {
            if (m.getRecipient().equals(recipient)) {
                out += m.getContent() + "\n";
            }
        }
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                out += m.getContent() + "\n";
            }
        }
        if (out.isEmpty()) {
            return "No messages for this recipient.";
        }
        return out;
    }

    // 2e - Delete by hash
    public static String deleteByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getHash().equals(hash)) {
                String msg = storedMessages.get(i).getContent();
                storedMessages.remove(i);
                messageHashes.remove(hash);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    // 2f - Full Report
    public static String displayReport() {
        String report = "=== SENT MESSAGES REPORT ===\n";
        for (Message m : sentMessages) {
            report += "Message Hash: " + m.getHash() + "\nRecipient: " + m.getRecipient() + "\nMessage: " + m.getContent() + "\n\n";
        }
        report += "=== STORED MESSAGES ===\n";
        for (Message m : storedMessages) {
            report += "Message Hash: " + m.getHash() + "\nRecipient: " + m.getRecipient() + "\nMessage: " + m.getContent() + "\n\n";
        }
        return report;
    }

    // JSON - Save stored to file (no extra library needed)
    public static void saveStoredToJSON() {
        try (FileWriter f = new FileWriter("storedMessages.json")) {
            f.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                Message m = storedMessages.get(i);
                f.write("  {\"messageID\":\"" + m.getMessageID() + "\", \"recipient\":\"" + m.getRecipient() + "\", \"message\":\"" + m.getContent() + "\", \"hash\":\"" + m.getHash() + "\"}");
                if (i < storedMessages.size() - 1) {
                    f.write(",\n");
                }
            }
            f.write("\n]");
            JOptionPane.showMessageDialog(null, "Stored messages saved to storedMessages.json");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving JSON");
        }
    }
}