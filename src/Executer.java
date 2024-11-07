public class Executer {
    private final ICpu cpu;

    public Executer(ICpu cpu) {
        this.cpu = cpu;
    }

    public void run(Program program) throws Exception {
        for (int i = 0; i < program.getCommandsCount(); i++) {
            cpu.exec(program.get(i));
        }
    }
}
