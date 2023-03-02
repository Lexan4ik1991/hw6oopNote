package src.personal.views;

import src.personal.controllers.UserController;
import src.personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
            String command = prompt("Введите команду: \n" +
                    "CREATE/" +
                    "READ/" +
                    "LIST/" +
                    "DELETE/" +
                    "UPDATE/" +
                    "EXIT\n");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    caseCreate();
                    break;
                case READ:
                    caseRead();
                    break;
                case LIST:

                    caseList();
                    break;
                case DELETE:
                    caseDeleted();
                    break;
                case UPDATE:
                    caseUpdate();
                    break;
            }
            }
            catch  (Exception ee){
                System.out.printf("%s something wrong \n ", ee.getMessage());
            }
        }
    }

    private void caseUpdate() throws Exception {
        String id = prompt("Enter id\n");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userController.deleteUser(id);
        caseCreate();
    }

    private void caseDeleted() {
        String id = prompt("Идентификатор пользователя: \n");
        userController.deleteUser(id);
        System.out.println("user deleted");
    }

    private void caseCreate() throws Exception {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void caseRead() {
        String id = prompt("Идентификатор пользователя: \n");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseList() {
        List<User> usersList = userController.readUsers();
        for (User user : usersList) {
            System.out.println(user);
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

}
