package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LookupTable {
    private final Map<String, List<String>> lookupMap = new HashMap<>();

    public void loadLookupTable(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = Arrays.stream(line.split(","))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .toArray(String[]::new);

            int protocolId = Utilities.protocolToId.getOrDefault(parts[1], -1);
            if (protocolId != -1) {
                String key = parts[0] + "-" + protocolId;
                lookupMap.computeIfAbsent(key, k -> new ArrayList<>()).add(parts[2]);
            }
        }
    }

    public List<String> getTags(int port, int protocol) {
        String key = port + "-" + protocol;
        return lookupMap.getOrDefault(key, List.of("Untagged"));
    }
}
