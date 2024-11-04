import java.util.*;

public class Program implements Iterable<Command> {
    private final Command[] commands;
    private int currentPosition = 0;
    private int minMemoryInUse = 1025;
    private int maxMemoryInUse = -1;

    public Program() {
        commands = new Command[20];
    }

    public void add(Command command) {
        commands[currentPosition] = command;
        currentPosition++;

        RangeOfMemory(command);
    }

    public Command get(int n) throws Exception {
        if (n < 0 || n > currentPosition) throw new Exception("Out of memory");
        return commands[n];
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void MostPopularInstruction() {
        Map<String, Integer> commandOccurrencesCount = new HashMap<>();

        for (Command command : commands) {
            if (command == null) continue;
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
            return "Memory not use";
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
        return new Iterator<Command>() {
            private int i = -1;

            @Override
            public boolean hasNext() {
                return i + 1 < getCurrentPosition();
            }

            @Override
            public Command next() {
                i++;
                if (i >= 0 && i < getCurrentPosition()) {
                    return commands[i];
                }
                return null;
            }
        };
    }
}
