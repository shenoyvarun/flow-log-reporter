package src;

import java.util.List;

public class TagAssigner {
    private final LookupTable lookupTable;

    public TagAssigner(LookupTable lookupTable) {
        this.lookupTable = lookupTable;
    }

    public void assignTags(FlowLog flowLog) {
        List<String> tags = lookupTable.getTags(flowLog.getDestinationPort(), flowLog.getProtocol());
        tags.forEach(flowLog::addTag);
    }
}
