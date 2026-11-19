/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpoepart1;


import java.util.Scanner;

public class ProgPOEPART1 {

    // --- Part 1 methods ---
    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+") && number.length() >= 10 && number.length() <= 13;
    }
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@$%^&*].*");
    }
    public boolean checkPassword(String password) { return checkPasswordComplexity(password); }
    public boolean checkUsername(String username) { return checkUserName(username); }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Welcome to QuickChat.");
            
            OUTER:
            while (true) {
                System.out.println("\nMENU: 1) Send Messages  2) Show recently sent messages  3) Quit  4) Stored Messages");
                System.out.print("Choose: ");
                int menu = sc.nextInt();
                sc.nextLine();
                
                switch (menu) {
                    case 2 -> System.out.println("Coming Soon.");
                        
                    case 4 ->  {
                        StoredMessages.populateTestData("0838884567");
                        System.out.println("Stored: a) Sender & Recipient b) Longest c) Search by ID d) Search by Recipient e) Delete by Hash f) Report g) Save JSON");
                        System.out.print("Choose: ");
                        String choice = sc.nextLine();
                        if (choice.equalsIgnoreCase("a")) {
                            System.out.println(StoredMessages.displaySenderRecipient());
                        } else if (choice.equalsIgnoreCase("b")) {
                            System.out.println("Longest: " + StoredMessages.displayLongestMessage());
                        } else if (choice.equalsIgnoreCase("c")) {
                            System.out.print("Enter Message ID: ");
                            System.out.println(StoredMessages.searchByMessageID(sc.nextLine()));
                        } else if (choice.equalsIgnoreCase("d")) {
                            System.out.print("Enter Recipient: ");
                            System.out.println(StoredMessages.searchByRecipient(sc.nextLine()));
                        } else if (choice.equalsIgnoreCase("e")) {
                            System.out.print("Enter Hash: ");
                            System.out.println(StoredMessages.deleteByHash(sc.nextLine()));
                        } else if (choice.equalsIgnoreCase("f")) {
                            System.out.println(StoredMessages.displayReport());
                        } else if (choice.equalsIgnoreCase("g")) {
                            StoredMessages.saveStoredToJSON();
                        }
                    }
                    
                    case 3 -> {
                         System.out.println("Goodbye!");
                        break OUTER;
                    }
                    
                    case 1 ->  {
                        System.out.print("How many messages do you want to enter? ");
                        int howMany = sc.nextInt();
                        sc.nextLine();
                        for (int i = 1; i <= howMany; i++) {
                            System.out.println("\n--- Message " + i + " ---");
                            System.out.print("Enter recipient (+27718693002): ");
                            String rec = sc.nextLine();
                            System.out.print("Enter message: ");
                            String txt = sc.nextLine();
                            Message m = new Message(i - 1, rec, txt);
                            String checkRec = m.checkRecipientCell();
                            System.out.println(checkRec);
                            if (!checkRec.contains("successfully")) { i--; continue; }
                            if (!m.checkMessageLength()) {
                                System.out.println("Message exceeds 250 characters. Please reduce size.");
                                i--; continue;
                            }
                            System.out.println("Hash: " + m.hash + " | ID: " + m.messageID);
                            System.out.println("1) Send 2) Disregard 3) Store");
                            int c = sc.nextInt(); sc.nextLine();
                            System.out.println(m.SentMessage(c));
                            if (c == 1) { System.out.println("\nFULL DETAILS:\n" + m.printMessages()); }
                         System.out.println("Total messages sent.");
                        }
                    }

                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }
}