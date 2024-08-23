package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {
    public void generateTagReport(List<FlowLog> flowLogs) {
        Map<String, Long> tagCounts = flowLogs.stream()
                .flatMap(fl -> fl.getTags().stream())
                .collect(Collectors.groupingBy(tag -> tag, Collectors.counting()));


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Utilities.TAG_REPORT_PREFIX + Instant.now() + Utilities.CSV_SUFFIX))) {
            writer.write("src.Tag,Count");
            writer.newLine();
            tagCounts.forEach((tag, count) -> {
                try {
                    writer.write(tag + "," + count);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("src.Tag Report Export Completed");
    }

    public void generatePortProtocolReport(List<FlowLog> flowLogs) {
        Map<String, Long> portProtocolCounts = flowLogs.stream()
                .collect(Collectors.groupingBy(fl -> fl.getDestinationPort() + "-" + fl.getProtocol(), Collectors.counting()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Utilities.PROTOCOL_COUNT_REPORT_PREFIX + Instant.now() + Utilities.CSV_SUFFIX))) {
            writer.write("Destination Port,Protocol,Count");
            writer.newLine();
            portProtocolCounts.forEach((key, count) -> {
                try {
                    String[] parts = key.split("-");
                    int port = Integer.parseInt(parts[0]);
                    int protocolId = Integer.parseInt(parts[1]);
                    String protocolName = Utilities.idToProtocol.getOrDefault(protocolId, "Unknown");
                    writer.write(port + "," + protocolName + "," + count);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Protocol Count Report Export Completed");
    }
}
