import java.util.HashMap;
import java.util.Map;

public class Cpu implements ICpu {
    private final int[] memory = new int[1024];
    private final Map<String, Integer> registers = new HashMap<>();

    public Cpu() {
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);
    }

    @Override
    public void exec(Command c) {
        String name = c.getName();
        String[] args = c.getArgs();

        switch (name) {
            case "init":
                memory[Integer.parseInt(args[0])] = Integer.parseInt(args[1]);
                break;
            case "ld":
                registers.put(args[0], memory[Integer.parseInt(args[1])]);
                break;
            case "st":
                memory[Integer.parseInt(args[1])] = registers.get(args[0]);
                break;
            case "mv":
                registers.put(args[0], registers.get(args[1]));
                break;
            case "add":
                registers.put("d", registers.get("a") + registers.get("b"));
                break;
            case "sub":
                registers.put("d", registers.get("a") - registers.get("b"));
                break;
            case "mult":
                registers.put("d", registers.get("a") * registers.get("b"));
                break;
            case "div":
                registers.put("d", registers.get("a") / registers.get("c"));
                break;
            case "print":
                System.out.println(registers);
                break;
            default:
                System.out.println("Unknown command: ${name}" + name);
        }
    }
}
