public class Main {
    public static void main(String[] args) {
        Program program = new Program();

        program.add(new Command("init 10 20"));
        program.add(new Command("init", "11", "25"));
        program.add(new Command("init", "12", "5"));
        program.add(new Command("ld", "a", "10"));
        program.add(new Command("ld", "b", "11"));
        program.add(new Command("ld", "c", "12"));
        program.add(new Command("add"));
        program.add(new Command("print"));
        program.add(new Command("mv a d"));
        program.add(new Command("mv b c"));
        program.add(new Command("div"));
        program.add(new Command("print"));

        ICpu cpu = BCpu.build();
        Executer executer = new Executer(cpu);

        try {
            executer.run(program);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.print("Все команды: ");
        program.forEach(command -> System.out.print(command + " "));

        System.out.println();
        System.out.println(program.GetRangeOfMemory());

        program.MostPopularInstruction();
    }
}