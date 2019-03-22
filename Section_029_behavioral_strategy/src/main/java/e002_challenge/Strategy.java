package e002_challenge;

public interface Strategy {
    int performOperation(int num1, int num2);
}

class Addition implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 + num2;
    }
}


class Substration implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 - num2;
    }
}


class Multiplication implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 * num2;
    }
}