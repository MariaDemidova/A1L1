package com.example.a1l1;

public class CalculLogic {

    private int firstArg;
    private int secondArg;
    private StringBuilder inputStr = new StringBuilder();
    private int actionSelected;
    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow,
    }

    public CalculLogic() {
        state = State.firstArgInput;
    }

    public void buttonPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.button0:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.button1:
                    inputStr.append("1");
                    break;
                case R.id.button2:
                    inputStr.append("2");
                    break;
                case R.id.button3:
                    inputStr.append("3");
                    break;
                case R.id.button4:
                    inputStr.append("4");
                    break;
                case R.id.button5:
                    inputStr.append("5");
                    break;
                case R.id.button6:
                    inputStr.append("6");
                    break;
                case R.id.button7:
                    inputStr.append("7");
                    break;
                case R.id.button8:
                    inputStr.append("8");
                    break;
                case R.id.button9:
                    inputStr.append("9");
                    break;
            }

        }

    }

    public void actionPressed(int actionId) {
        if (actionId == R.id.button_equals && state == State.secondArgInput && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.button_division:
                    inputStr.append(firstArg / secondArg);
                    break;
                case R.id.button_multiplication:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.button_minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.button_plus:
                    inputStr.append(firstArg + secondArg);
                    break;
            }
        } else if (inputStr.length() > 0 && state == State.firstArgInput && actionId != R.id.button_equals) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.button_plus:
                return '+';
            case R.id.button_minus:
                return '-';
            case R.id.button_multiplication:
                return '*';
            case R.id.button_division:
            default:
                return '/';
        }
    }

    public void reset() {
        state = State.firstArgInput;
        inputStr.setLength(0);
    }
}