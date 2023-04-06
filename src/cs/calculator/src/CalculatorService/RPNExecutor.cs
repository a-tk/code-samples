
using System;
using System.Collections;
public class RPNExecutor : ExecutorInterface 
{
    private Stack<double> executionStack;

    public RPNExecutor() {
        executionStack = new Stack<double>();
    }
    public void enterKey(Double value) {
        executionStack.Push(value);
    }
    public Double plusKey(Double value) {
        double operand = popStack();
        return operand + value;
    }
    public Double minusKey(Double value) {
        double operand = popStack();
        return operand - value;
    }
    public Double multiplyKey(Double value) {
        double operand = popStack();
        return operand * value;
    }
    public Double divideKey(Double value) {
        double operand = popStack();
        return operand / value;
    }
    public void clearKey() {
        executionStack.Clear();
    }

    private Double popStack() {
        return executionStack.Pop();
    }

    
}