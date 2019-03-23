package e002_challenge;

class Context {
    String input;  // John

    public Context(String input) {
        this.input = input;
    }

    public boolean getResult(String data) {
        if(input.contains(data))
            return true;
        else
            return false;
    }
}
