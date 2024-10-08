public class Command {
    private final String name;
    private final String[] args;

    public Command(String command) {
        String[] parts = command.split(" ");
        this.name = parts[0];
        this.args = new String[parts.length - 1];
        System.arraycopy(parts, 1, this.args, 0, this.args.length);
    }

    public Command(String name, String... args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }
}
