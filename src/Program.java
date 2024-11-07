import java.util.*;

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
        Map<String, Integer> commandOccurrences = new HashMap<>();

        for (Command command : commands) {
            String name = command.getName();
            commandOccurrences.put(name, commandOccurrences.getOrDefault(name, 0) + 1);
        }

        String mostPopularInstruction = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : commandOccurrences.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularInstruction = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        if (mostPopularInstruction != null) {
            System.out.println("Самая популярная команда: " + mostPopularInstruction + " (" + maxCount + ")");
        } else {
            System.out.println("Команд нет.");
        }

        List<Map.Entry<String, Integer>> sortedCommands = new ArrayList<>(commandOccurrences.entrySet());
        sortedCommands.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("Команды по убыванию популярности:");
        for (Map.Entry<String, Integer> entry : sortedCommands) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
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
