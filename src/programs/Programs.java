package programs;

import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BooleanValue;
import model.value.IntegerValue;

import java.util.List;

public class Programs {
    public static final List<Statement> hardcodedPrograms = List.of(
            new CompoundStatement(
                    new VariableDeclarationStatement(new IntType(), "v"),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    new ValueExpression(new IntegerValue(2)), "v"),
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

                    ))


    );
}
