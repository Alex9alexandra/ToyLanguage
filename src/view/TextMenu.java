package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String,Command> commands;
    public TextMenu(){commands=new HashMap<>();}
    public void addCommand(Command command){
        commands.put(command.getKey(),command);
    }
    private void printMenu(){
        commands.values().stream()
                .sorted((c1,c2)->Integer.compare(Integer.parseInt(c1.getKey()),Integer.parseInt(c2.getKey())))
                .forEach(command->{
                    String line=String.format("%4s : %s",command.getKey(),command.getDescription());
                    System.out.println(line);
                });
    }
    public void show(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Input the command: ");
            String key=scanner.nextLine();
            Command command=commands.get(key);
            if(command==null){
                System.out.println("Invalid command");
                continue;
            }
            try{
                command.execute();
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
