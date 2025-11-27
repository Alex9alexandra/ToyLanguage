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
                IRepository repo1 = new Repository(prg1, "log1.txt");
                Controller ctr1 = new Controller(repo1);

                ProgramState prg2 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(1)
                );
                IRepository repo2 = new Repository(prg2, "log2.txt");
                Controller ctr2 = new Controller(repo2);

                ProgramState prg3 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(2)
                );
                IRepository repo3 = new Repository(prg3, "log3.txt");
                Controller ctr3 = new Controller(repo3);

                ProgramState prg4 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(3)
                );
                IRepository repo4 = new Repository(prg4, "log4.txt");
                Controller ctr4 = new Controller(repo4);

                ProgramState prg5 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(4)
                );
                IRepository repo5 = new Repository(prg5, "log5.txt");
                Controller ctr5 = new Controller(repo5);

                ProgramState prg6 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(5)
                );
                IRepository repo6 = new Repository(prg6, "log6.txt");
                Controller ctr6 = new Controller(repo6);

                ProgramState prg7 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(6)
                );
                IRepository repo7 = new Repository(prg7, "log7.txt");
                Controller ctr7 = new Controller(repo7);

                ProgramState prg8 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(7)
                );
                IRepository repo8 = new Repository(prg8, "log8.txt");
                Controller ctr8 = new Controller(repo8);

                ProgramState prg9 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(8)
                );
                IRepository repo9 = new Repository(prg9, "log9.txt");
                Controller ctr9 = new Controller(repo9);

                ProgramState prg10 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(9)
                );
                IRepository repo10 = new Repository(prg10, "log10.txt");
                Controller ctr10 = new Controller(repo10);

                ProgramState prg11 = new ProgramState(
                        new StackExecutionStack<>(),
                        new MapSymbolTable<>(),
                        new ListOut<>(),
                        new MapFileTable(),
                        new HeapTable<>(),
                        programList.get(10)
                );
                IRepository repo11 = new Repository(prg11, "log11.txt");
                Controller ctr11 = new Controller(repo11);


                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "Exit"));
                menu.addCommand(new RunExample("1", programList.get(0).toString(), ctr1,0));
                menu.addCommand(new RunExample("2", programList.get(1).toString(), ctr2,0));
                menu.addCommand(new RunExample("3", programList.get(2).toString(), ctr3,0));
                menu.addCommand(new RunExample("4", programList.get(3).toString(), ctr4,0));
                menu.addCommand(new RunExample("5", programList.get(4).toString(), ctr5,0));
                menu.addCommand(new RunExample("6", programList.get(5).toString(), ctr6,0));
                menu.addCommand(new RunExample("7", programList.get(6).toString(), ctr7,0));
                menu.addCommand(new RunExample("8", programList.get(7).toString(), ctr8,0));
                menu.addCommand(new RunExample("9", programList.get(8).toString(), ctr9,0));
                menu.addCommand(new RunExample("10", programList.get(9).toString(), ctr10,0));
                menu.addCommand(new RunExample("11", programList.get(10).toString(), ctr11,0));
                menu.show();

            }
}
