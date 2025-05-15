package ex05;

import java.util.Scanner;

public class Menu {

    public enum Type {
        DEV,
        STANDARD
    }
    private Type mode = Type.STANDARD;
    private TransactionsService transactionsService;
    private Scanner scanner;

    public Menu(Type mode){
        scanner = new Scanner(System.in);
        this.mode = mode;
        transactionsService = new TransactionsService();
    }

    public void ShowMenuGuide(){
        System.out.println("""
                1. Add a user
                2. View user balances
                3. Perform a transfer
                4. View all transactions for a specific user
                5. DEV - remove a transfer by ID
                6. DEV - check transfer validity
                7. Finish execution""");
    }

    private int readInt(){

        if (scanner.hasNextInt()){
            return scanner.nextInt();
        }
        throw new RuntimeException("Must be int");
    }
    public void addUser(){
        printPrompt("Enter a user name and a balance");
        String line = scanner.nextLine();

        System.out.println(line);
        String[] _split = Parser.split(line, ' ');
        if (_split.length != 2){
            throw new RuntimeException("Not va");
        }
        String name = _split[0];
        int balance = Parser.parseInt(_split[1]);
        User newUser = new User(name, balance);
        this.transactionsService.addUserToService(newUser);
        System.out.println("User with id = "+ newUser.getId() + " is added");

    }
    public void viewUserBalance(){
        printPrompt("Enter a user ID");
        String line = scanner.nextLine();
        int userId = Parser.parseInt(line);
        int balance = transactionsService.getUserBalance(userId);
        String name = transactionsService.getUsers().getUserById(userId).getName();
        System.out.println(name + " - " + balance);
    }

    public void performTransfer(){
        printPrompt("Enter a sender ID, a recipient ID, and a transfer amount");
        String line = scanner.nextLine();
        String[] _split = Parser.split(line, ' ');
        if (_split.length != 3)
            throw new RuntimeException("All Fields are required : sender id, recipient id and transfer amount");
        int senderId = Parser.parseInt(_split[0]);
        int recipientId = Parser.parseInt(_split[1]);
        int amount = Parser.parseInt(_split[2]);
        transactionsService.performTransfer(senderId, recipientId, amount);
        System.out.println("The transfer is completed");
    }

    public void viewUserTransactions(){
        printPrompt("Enter user ID");
        String line = scanner.nextLine();
        String[] _split = Parser.split(line, ' ');
        int id = Parser.parseInt(line);
        User user = transactionsService.getUsers().getUserById(id);
        Transaction[] transactions = user.getTransactionArray();
        for (int i = 0; i < transactions.length; i++){
            System.out.println(transactions[i]);
        }
    }

    public void removeTransferByID(){
        printPrompt("Enter a user ID and a transfer ID");
        String line = scanner.nextLine();
        String[] _split = Parser.split(line, ' ');
        if (_split.length != 2)
            throw new RuntimeException("User ID and transfer ID required");
        int userID = Parser.parseInt(_split[0]);
        String uuid = _split[1];
        Transaction removerTransaction = transactionsService.removeUserIdTransaction(userID, uuid);
        User recipient = removerTransaction.getRecipient();
        String message = "Transfer To "+recipient.getName()+"(id = "+ recipient.getId() +") " +removerTransaction.getAmount() +" removed";
        System.out.println(message);
    }

    private void nonValidTransactionsInfo(Transaction transaction){
        User sender = transaction.getSender();
        User recipient = transaction.getRecipient();
        String message = recipient.getName()+"(id = "+recipient.getId()+") "+"has an unacknowledged transfer id = "+ transaction.getUuid()+" from "+sender.getName()+"(id = "+ sender.getId()+") for "+transaction.getAmount();
        System.out.println(message);
    }
    public void checkTransactionValidity(){
        printPrompt("Check results:");
        Transaction[] nonValidTransactions = transactionsService.checkValidity();
        for (int i =0 ;i < nonValidTransactions.length; i++){
            nonValidTransactionsInfo(nonValidTransactions[i]);
        }
    }

    public void printPrompt(String prompt){
        System.out.println(prompt);
        System.out.print("-> ");
    }

    public void commadRedirect(int command){
        if (command == 1)
            addUser();
        if(command == 2)
            viewUserBalance();
        if(command == 3)
            performTransfer();
        if(command == 4)
            viewUserTransactions();
        if(command == 5)
            removeTransferByID();
        if (command == 6)
            checkTransactionValidity();
        if (command == 7)
            System.exit(0);
    }

    public void start(){
        while(true){
            System.out.print("-> ");
            System.out.flush();
            try {

                String line = scanner.nextLine();
                int commandCode = Parser.parseInt(line);
                commadRedirect(commandCode);
            }catch (Exception e){
                System.out.println(e.getMessage());
//                System.err.flush();
            }
        }
    }
}
