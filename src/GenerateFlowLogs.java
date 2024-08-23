package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GenerateFlowLogs {

    private static final int NUM_LOGS = 500000;
    private static final List<String> ACTIONS = List.of("ACCEPT", "REJECT");
    private static final List<String> LOG_STATUSES = List.of("OK", "FAILED");
    private static final List<String> PROTOCOLS = List.of("6", "17", "1"); // TCP, UDP, ICMP

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Utilities.FLOW_LOG_FILE))) {
            Random random = new Random();

            // Generate logs
            IntStream.range(0, NUM_LOGS).forEach(i -> {
                try {
                    String log = generateFlowLog(random);
                    writer.write(log);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateFlowLog(Random random) {
        int version = random.nextInt(10) + 1; // Random version from 1 to 10
        String accountId = "123456789012"; // Fixed example value
        String interfaceId = "eni-" + generateRandomString(); // Random ENI ID
        String sourceIp = generateRandomIP();
        String destinationIp = generateRandomIP();
        int sourcePort = random.nextInt(65536);
        int destinationPort = random.nextInt(65536);
        String protocol = PROTOCOLS.get(random.nextInt(PROTOCOLS.size())); // Random protocol
        int packets = random.nextInt(1000);
        long bytes = random.nextInt(10_000) + 1;
        long startTime = 1620140761L + random.nextInt(3600); // Random start time
        long endTime = startTime + random.nextInt(3600);
        String action = ACTIONS.get(random.nextInt(ACTIONS.size()));
        String logStatus = LOG_STATUSES.get(random.nextInt(LOG_STATUSES.size()));

        // Format log line
        return String.format(
                "%d %s %s %s %s %d %d %s %d %d %d %d %s %s",
                version, accountId, interfaceId, sourceIp, destinationIp, destinationPort, sourcePort, protocol,
                packets, bytes, startTime, endTime, action, logStatus
        );
    }

    private static String generateRandomIP() {
        Random random = new Random();
        return String.format("%d.%d.%d.%d",
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
    }

    private static String generateRandomString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}