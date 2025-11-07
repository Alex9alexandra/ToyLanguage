package view;

import java.util.Scanner;

import controller.IController;

public class View {
    IController controller;

    public View(IController controller) {
        this.controller = controller;
    }

    public void printMenu() {
        System.out.println("1.Select the program : ");
        System.out.println("    1.int v; v=2;Print(v)");
        System.out.println("    2.int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("    3.bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("    4. ERROR FOR bool b; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("    5. ERROR FOR int a;int b; a=2+3/0;b=a+1;Print(b)");
        System.out.println("    6. ERROR FOR int a;int b; a=2+3(5;b=a+1;Print(b)");
        System.out.println("2.Program execution");
        System.out.println("0.Exit");

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        printMenu();

        try {
            System.out.println("Enter the program you want to run (1-6): ");
            int programId = scanner.nextInt();
            this.controller.setProgramStateID(programId - 1);

            System.out.println("Show execution steps? 1-yes, 0-no : ");
            int displayFlag = scanner.nextInt();

            controller.setDisplayFlag(displayFlag == 1);
            this.controller.allStepExecution();

        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

    }

}
