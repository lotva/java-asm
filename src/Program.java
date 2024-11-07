import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    private final List<Command> commands;
    private int minMemoryInUse = 1025;
    private int maxMemoryInUse = -1;

    public Program() {
        commands = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
        updateRangeOfMemory(command);
    }

    public Command get(int n) throws Exception {
        if (n < 0 || n >= commands.size()) throw new Exception("Out of memory");
        return commands.get(n);
    }

    public int getCommandsCount() {
        return commands.size();
    }

    public void getMostPopularInstruction() {
        Map<String, Long> commandOccurrences = commands.stream()
                .collect(Collectors.groupingBy(Command::getName, Collectors.counting()));

        commandOccurrences.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresentOrElse(
                        entry -> System.out.println("Самая популярная команда: " + entry.getKey() + " (" + entry.getValue() + ")"),
                        () -> System.out.println("Команд нет.")
                );

        System.out.println("Команды по убыванию популярности:");
        commandOccurrences.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " — " + entry.getValue()));
    }

    private void updateRangeOfMemory(Command c) {
        if (Objects.equals(c.getName(), "init")) {
            int memory = Integer.parseInt(c.getArgs()[0]);
            minMemoryInUse = Math.min(minMemoryInUse, memory);
            maxMemoryInUse = Math.max(maxMemoryInUse, memory);
        }
    }

    public String getRangeOfMemory() {
        if (minMemoryInUse == 1025 || maxMemoryInUse == -1) {
            return "Память не использована";
        }

        return "Диапазон адресов памяти: " + minMemoryInUse + "—" + maxMemoryInUse;
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
