public class Executer {
    private final ICpu cpu;

    public Executer(ICpu cpu) {
        this.cpu = cpu;
    }

    public void run(Command[] program) {
        for (Command command : program) {
            cpu.exec(command);
        }
    }
}
