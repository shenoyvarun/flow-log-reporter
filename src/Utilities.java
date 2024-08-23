package src;

import java.util.Map;

public class Utilities {
    public static final String FLOW_LOG_FILE = "data_sources/flow_logs.log";
    public static final String LOOKUP_TABLE_FILE = "data_sources/lookup_table.csv";
    public static final String TAG_REPORT_PREFIX = "reports/TagReport_";
    public static final String PROTOCOL_COUNT_REPORT_PREFIX = "reports/ProtocolCountReport_";
    public static final String CSV_SUFFIX = ".csv";

    public static final Map<String, Integer> protocolToId = Map.of(
            "tcp", 6,
            "udp", 17,
            "icmp", 1
    );

    public static final Map<Integer, String> idToProtocol = Map.of(
            6, "tcp",
            17, "udp",
            1, "icmp"
    );

    public static void logWarning(String message) {
        System.out.println("[WARNING] " + message);
    }
}
