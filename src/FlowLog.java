package src;

import java.util.ArrayList;
import java.util.List;

public class FlowLog {

    private final int version;
    private final String accountId;
    private final String interfaceId;
    private final String sourceIp;
    private final String destinationIp;
    private final int destinationPort;
    private final int sourcePort;
    private final int protocol;
    private final int packets;
    private final long bytes;
    private final long startTime;
    private final long endTime;
    private final String action;
    private final String logStatus;
    private final List<String> tags;

    public FlowLog(String version, String accountId, String eni, String sourceIp, String destinationIp, String sourcePort,
                   String destinationPort, String protocol, String packets, String bytes, String startTime, String endTime, String action,
                   String logStatus) {
        FlowLogValidator flowLogValidator = new FlowLogValidator();

        this.version = flowLogValidator.validateVersion(version);
        this.accountId = accountId;
        this.interfaceId = eni;
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.sourcePort = flowLogValidator.validatePort(sourcePort);
        this.destinationPort = flowLogValidator.validatePort(destinationPort);
        this.protocol = flowLogValidator.validateProtocol(protocol);
        this.packets = flowLogValidator.validateInteger(packets, "packets");
        this.bytes = flowLogValidator.validateInteger(bytes, "bytes");
        this.startTime = flowLogValidator.validateLong(startTime, "startTime");
        this.endTime = flowLogValidator.validateLong(endTime, "endTime");
        this.action = flowLogValidator.validateAction(action);
        this.logStatus = logStatus;
        this.tags = new ArrayList<>();
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public int getProtocol() {
        return protocol;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "src.FlowLog{" +
                "version=" + version +
                ", accountId='" + accountId + '\'' +
                ", interfaceId='" + interfaceId + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", destinationIp='" + destinationIp + '\'' +
                ", destinationPort=" + destinationPort +
                ", sourcePort=" + sourcePort +
                ", protocol=" + protocol +
                ", packets=" + packets +
                ", bytes=" + bytes +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", action='" + action + '\'' +
                ", logStatus='" + logStatus + '\'' +
                ", tags=" + tags +
                '}';
    }
}
