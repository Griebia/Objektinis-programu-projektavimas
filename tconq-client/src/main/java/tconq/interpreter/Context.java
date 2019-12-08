package tconq.interpreter;

public class Context {

    private String command;
    private String whatToAdd;
    private int amount;
    private boolean wrongInput;

    public Context (String input) {

        wrongInput = false;

        String[] parse = input.split(" ");

        if (parse.length < 3){
            wrongInput = true;
            return;
        }

        command = parse[0];
        try {
            amount = Integer.parseInt(parse[1]);
        }
        catch (NumberFormatException nfe) {
            wrongInput = true;
        }
        whatToAdd = parse [2];

    }

    public String getCommand() {
        return command.toLowerCase();
    }

    public int getAmount() {
        return amount;
    }

    public String getWhatToAdd() {
        return whatToAdd;
    }

    public boolean getWrongInput() {
        return wrongInput;
    }
}
