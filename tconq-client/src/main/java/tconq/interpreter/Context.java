package tconq.interpreter;

public class Context {

    private String command;
    private String whatToAdd;
    private int amountToAdd;

    public Context (String input) {

        String[] parse = input.split(" ");

        command = parse[0];
        if (parse.length >= 2)
        {
            amountToAdd = Integer.parseInt(parse[1]);
            whatToAdd = parse [2];
        }

    }

    public String getCommand() {
        return command.toLowerCase();
    }

    public int getAmountToAdd() {
        return amountToAdd;
    }

    public String getWhatToAdd() {
        return whatToAdd;
    }
}
