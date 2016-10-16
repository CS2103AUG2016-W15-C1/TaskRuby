package core.parser;

import java.util.Arrays;
import java.util.HashMap;

public class SimpleParser {
    public SimpleParser () {}
    
    public HashMap<String, String[]> parse(String input) {
        String[] tok = input.split("\\s+");
        HashMap<String, String[]> commands = new HashMap<String, String[]>();
        commands.put(tok[0], Arrays.copyOfRange(tok, 1, tok.length));
        return commands;
    }
    
}
