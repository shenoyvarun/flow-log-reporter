package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FlowLogProcessor {
    private final FlowLogParser parser;
    private final TagAssigner tagAssigner;

    public FlowLogProcessor(FlowLogParser parser, TagAssigner tagAssigner) {
        this.parser = parser;
        this.tagAssigner = tagAssigner;
    }

    public List<FlowLog> processLogs(String logFilePath) throws IOException {
        List<FlowLog> flowLogs = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                FlowLog flowLog = parser.parse(line);
                tagAssigner.assignTags(flowLog);
                flowLogs.add(flowLog);
            }
        }
        return flowLogs;
    }
}
