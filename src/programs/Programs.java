package programs;

import model.expression.*;
import model.statement.*;
import model.type.*;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.StringValue;

import java.util.List;

public class Programs {
    public static final List<Statement> hardcodedPrograms = List.of(
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "v"),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    new ValueExpression(new IntegerValue(3)), "v"),
                            new PrintStatement(
                                    new VariableExpression("v")))),
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "a"),
                    new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"),
                            new CompoundStatement(new AssignmentStatement(
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(2)), new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5)), '*'), '+'), "a"),
                                    new CompoundStatement(
                                            new AssignmentStatement(
                                                    new ArithmeticExpression(
                                                            new VariableExpression("a"), new ValueExpression(new IntegerValue(1)), '+'), "b"),
                                            new PrintStatement(new VariableExpression("b"))))

                    )),
            new CompoundStatement(
                    new VariableDeclarationStatement(new BoolType(), "a"),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new IntType(), "v"),
                            new CompoundStatement(
                                    new AssignmentStatement(new ValueExpression(new BooleanValue(true)), "a"),
                                    new CompoundStatement(
                                            new IfStatement(
                                                    new VariableExpression("a"),
                                                    new AssignmentStatement(new ValueExpression(new IntegerValue(2)), "v"),
                                                    new AssignmentStatement(new ValueExpression(new IntegerValue(3)), "v")),
                                            new PrintStatement(new VariableExpression("v")))))
            ),
            new CompoundStatement(
                    new VariableDeclarationStatement(new BoolType(), "b"),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new IntType(), "v"),
                            new CompoundStatement(
                                    new AssignmentStatement(new ValueExpression(new BooleanValue(true)), "a"),
                                    new CompoundStatement(
                                            new IfStatement(
                                                    new VariableExpression("a"),
                                                    new AssignmentStatement(new ValueExpression(new IntegerValue(2)), "v"),
                                                    new AssignmentStatement(new ValueExpression(new IntegerValue(3)), "v")),
                                            new PrintStatement(new VariableExpression("v")))))
            ),
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "a"),
                    new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"),
                            new CompoundStatement(new AssignmentStatement(
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(2)), new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(0)), '/'), '+'), "a"),
                                    new CompoundStatement(
                                            new AssignmentStatement(
                                                    new ArithmeticExpression(
                                                            new VariableExpression("a"), new ValueExpression(new IntegerValue(1)), '+'), "b"),
                                            new PrintStatement(new VariableExpression("b"))))

                    )),
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "a"),
                    new CompoundStatement(new VariableDeclarationStatement(new IntType(), "b"),
                            new CompoundStatement(new AssignmentStatement(
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(2)), new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5)), '('), '+'), "a"),
                                    new CompoundStatement(
                                            new AssignmentStatement(
                                                    new ArithmeticExpression(
                                                            new VariableExpression("a"), new ValueExpression(new IntegerValue(1)), '+'), "b"),
                                            new PrintStatement(new VariableExpression("b"))))

                    )),

            new CompoundStatement(
                    new VariableDeclarationStatement(new StrType(), "varf"),
                    new CompoundStatement(
                            new AssignmentStatement(new ValueExpression(new StringValue("test.in")),"varf"),
                            new CompoundStatement(
                                    new OpenRFileStatement(new VariableExpression("varf")),
                                    new CompoundStatement(
                                            new VariableDeclarationStatement(new IntType(), "varc"),
                                            new CompoundStatement(
                                                    new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                    new CompoundStatement(
                                                            new PrintStatement(new VariableExpression("varc")),
                                                            new CompoundStatement(
                                                                    new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VariableExpression("varc")),
                                                                            new CloseRFileStatement(new VariableExpression("varf"))
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            ),
            new CompoundStatement(
                    new VariableDeclarationStatement( new IntType(),"a"),
                    new CompoundStatement(
                            new VariableDeclarationStatement( new IntType(),"b"),
                            new CompoundStatement(
                                    new AssignmentStatement( new ValueExpression(new IntegerValue(5)),"a"),
                                    new CompoundStatement(
                                            new AssignmentStatement( new ValueExpression(new IntegerValue(7)),"b"),
                                            new IfStatement(
                                                    new RelationalExpression(
                                                            new VariableExpression("a"),
                                                            new VariableExpression("b"),
                                                            "<"
                                                    ),
                                                    new PrintStatement(new VariableExpression("a")),
                                                    new PrintStatement(new VariableExpression("b"))
                                            )
                                    )
                            )
                    )
            ),
            //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))

            new CompoundStatement(
                    new VariableDeclarationStatement(new RefType(new IntType()),"v"),
                    new CompoundStatement(
                            new NewStatement("v",new ValueExpression(new IntegerValue(20))),
                            new CompoundStatement(
                                    new VariableDeclarationStatement(new RefType(new RefType(new IntType())),"a"),
                                    new CompoundStatement(
                                            new NewStatement("a",new VariableExpression("v")),
                                            new CompoundStatement(
                                                    new NewStatement("v",new ValueExpression(new IntegerValue(30))),
                                                    new PrintStatement(
                                                            new ReadHeapExpression(new ReadHeapExpression((new VariableExpression("a"))))
                                                            )))))



            ),
            //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
            new CompoundStatement(
                    new VariableDeclarationStatement( new IntType(),"v"),
                    new CompoundStatement(
                            new AssignmentStatement(new ValueExpression(new IntegerValue(4)), "v"),
                            new CompoundStatement(
                                    new WhileStatement(
                                            new RelationalExpression(
                                                    new VariableExpression("v"),
                                                    new ValueExpression(new IntegerValue(0)),
                                                    ">"
                                            ),
                                            new CompoundStatement(
                                                    new PrintStatement(new VariableExpression("v")),
                                                    new AssignmentStatement(
                                                            new ArithmeticExpression(
                                                                    new VariableExpression("v"),
                                                                    new ValueExpression(new IntegerValue(1)),
                                                                    '-'
                                                            ),"v"
                                                    )
                                            )
                                    ),
                                    new PrintStatement(new VariableExpression("v"))

                            )
                    )
            ),
            //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
            new CompoundStatement(
                    new VariableDeclarationStatement(new RefType(new IntType()),"v"),
                    new CompoundStatement(
                            new NewStatement("v",new ValueExpression(new IntegerValue(20))),
                            new CompoundStatement(
                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                    new CompoundStatement(
                                            new WriteHeapStatement("v",new ValueExpression(new IntegerValue(30))),
                                            new PrintStatement(
                                                    new ArithmeticExpression(
                                                            new ReadHeapExpression(new VariableExpression("v")),
                                                            new ValueExpression(new IntegerValue(5)),
                                                            '+'
                                                    )
                                            )
                                    )
                            )
                    )
            ),
            //Ref int v;new(v,20);print(rH(a)); wH(v,30);print(rH(v)+5);
            new CompoundStatement(
                    new VariableDeclarationStatement(new RefType(new IntType()),"v"),
            new CompoundStatement(
                            new NewStatement("v",new ValueExpression(new IntegerValue(20))),
            new CompoundStatement(
                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))),
            new CompoundStatement(
                                            new WriteHeapStatement("v",new ValueExpression(new IntegerValue(30))),
            new PrintStatement(
                                                    new ArithmeticExpression(
                    new ReadHeapExpression(new VariableExpression("v")),
            new ValueExpression(new IntegerValue(5)),
            '+'
            )
            )
            )
            )
            )
            ),
            //int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v);print(rH(a)));  print(v);print(rH(a)
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(),"v"),
                    new CompoundStatement(
                            new VariableDeclarationStatement(new RefType(new IntType()),"a"),
                            new CompoundStatement(
                                    new AssignmentStatement(new ValueExpression(new IntegerValue(10)),"v"),
                                    new CompoundStatement(
                                            new NewStatement("a",new ValueExpression(new IntegerValue(22))),
                                            new CompoundStatement(
                                                    new ForkStatement(
                                                            new CompoundStatement(
                                                                    new WriteHeapStatement("a",new ValueExpression(new IntegerValue(30))),
                                                                    new CompoundStatement(
                                                                            new AssignmentStatement(new ValueExpression(new IntegerValue(32)),"v"),
                                                                            new CompoundStatement(
                                                                                    new PrintStatement(new VariableExpression("v")),
                                                                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                            )
                                                                    )
                                                            )
                                                    ),
                                                    new CompoundStatement(
                                                            new PrintStatement(new VariableExpression("v")),
                                                            new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                    )
                                            )
                                    )
                            )
                    )
            ),
            //int v; v=true;Print(v)
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "v"),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    new ValueExpression(new BooleanValue(true)), "v"),
                            new PrintStatement(
                                    new VariableExpression("v"))))
    );

}
