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
        RangeOfMemory(command);
    }

    public Command get(int n) throws Exception {
        if (n < 0 || n >= commands.size()) throw new Exception("Out of memory");
        return commands.get(n);
    }

    public int getCurrentPosition() {
        return commands.size();
    }

    public void MostPopularInstruction() {
        Map<String, Integer> commandOccurrencesCount = new HashMap<>();

        for (Command command : commands) {
            String name = command.getName();
            commandOccurrencesCount.put(name, commandOccurrencesCount.getOrDefault(name, 0) + 1);
        }

        String mostPopularCommand = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : commandOccurrencesCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularCommand = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        if (mostPopularCommand != null) {
            System.out.println("Самая популярная команда: " + mostPopularCommand + " (использована " + maxCount + " раз)");
        } else {
            System.out.println("Команд нет.");
        }

        List<Map.Entry<String, Integer>> sortedCommands = new ArrayList<>(commandOccurrencesCount.entrySet());
        sortedCommands.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("Команды по убыванию популярности:");
        for (Map.Entry<String, Integer> entry : sortedCommands) {
            System.out.println("Команда: " + entry.getKey() + ", Количество: " + entry.getValue());
        }
    }

    public String GetRangeOfMemory() {
        if (minMemoryInUse == 1025 || maxMemoryInUse == -1) {
            return "Память не использована";
        }

        return "Диапазон адресов памяти: " + minMemoryInUse + "—" + maxMemoryInUse;
    }

    private void RangeOfMemory(Command c) {
        if (Objects.equals(c.getName(), "init")) {
            int memory = Integer.parseInt(c.getArgs()[0]);
            minMemoryInUse = Math.min(minMemoryInUse, memory);
            maxMemoryInUse = Math.max(maxMemoryInUse, memory);
        }
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
