import controller.Controller;
import model.state.ProgramState;
import model.statement.Statement;
import programs.Programs;
import repository.IRepository;
import repository.Repository;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;
import model.state.*;

import java.util.List;

        public class Interpreter {
            public static void main(String[] args) {

                List<Statement> programList = Programs.hardcodedPrograms;

                ProgramState prg1 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(0)
                );
                IRepository repo = new Repository(prg1, "log.txt");
                Controller ctr = new Controller(repo);


                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "Exit"));
                menu.addCommand(new RunExample("1", programList.get(0).toString(), ctr,0));
                menu.addCommand(new RunExample("2", programList.get(1).toString(), ctr,1));
                menu.addCommand(new RunExample("3", programList.get(2).toString(), ctr,2));
                menu.addCommand(new RunExample("4", programList.get(3).toString(), ctr,3));
                menu.addCommand(new RunExample("5", programList.get(4).toString(), ctr,4));
                menu.addCommand(new RunExample("6", programList.get(5).toString(), ctr,5));
                menu.addCommand(new RunExample("7", programList.get(6).toString(), ctr,6));
                menu.addCommand(new RunExample("8", programList.get(7).toString(), ctr,7));
                menu.addCommand(new RunExample("9", programList.get(8).toString(), ctr,8));
                menu.addCommand(new RunExample("10", programList.get(9).toString(), ctr,9));
                menu.addCommand(new RunExample("11", programList.get(10).toString(), ctr,10));
                menu.addCommand(new RunExample("12", programList.get(11).toString(), ctr,11));
                menu.addCommand(new RunExample("13", programList.get(12).toString(), ctr,12));
                menu.show();

            }
}
