package src;

public class DefaultFlowLogParser implements FlowLogParser {
    @Override
    public FlowLog parse(String logLine) {
        String[] parts = logLine.split(" ");
        return new FlowLog(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6],
                parts[7],
                parts[8],
                parts[9],
                parts[10],
                parts[11],
                parts[12],
                parts[13]
        );
    }
}
