package src;

import java.io.IOException;
import java.util.List;

public class FlowLogReportingSystem {
    public static void main(String[] args) {
        try {
            // Load lookup table
            LookupTable lookupTable = new LookupTable();
            lookupTable.loadLookupTable(Utilities.LOOKUP_TABLE_FILE);

            // Initialize parser and tag assigner
            FlowLogParser parser = new DefaultFlowLogParser();
            TagAssigner tagAssigner = new TagAssigner(lookupTable);

            // Process flow logs
            FlowLogProcessor processor = new FlowLogProcessor(parser, tagAssigner);
            List<FlowLog> flowLogs = processor.processLogs(Utilities.FLOW_LOG_FILE);

            // Generate reports
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateTagReport(flowLogs);
            reportGenerator.generatePortProtocolReport(flowLogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
