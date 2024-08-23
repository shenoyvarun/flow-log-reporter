package src;

public class FlowLogValidator {
    public int validateVersion(String versionStr) {
        try {
            int version = Integer.parseInt(versionStr);
            if (version <= 0) {
                Utilities.logWarning("Invalid version: " + versionStr + ". Defaulting to version 1.");
                return 1;
            }
            return version;
        } catch (NumberFormatException e) {
            Utilities.logWarning("Invalid version: " + versionStr + ". Defaulting to version 1.");
            return 1;
        }
    }

    public int validatePort(String portStr) {
        try {
            int port = Integer.parseInt(portStr);
            if (port < 0 || port > 65535) {
                Utilities.logWarning("Invalid port: " + portStr + ". Defaulting to 0.");
                return 0;
            }
            return port;
        } catch (NumberFormatException e) {
            Utilities.logWarning("Invalid port: " + portStr + ". Defaulting to 0.");
            return 0;
        }
    }

    public int validateProtocol(String protocolStr) {
        try {
            int protocol = Integer.parseInt(protocolStr);
            if (protocol != 6 && protocol != 17 && protocol != 1) {
                Utilities.logWarning("Invalid protocol: " + protocolStr + ". Defaulting to TCP (6).");
                return 6;
            }
            return protocol;
        } catch (NumberFormatException e) {
            Utilities.logWarning("Invalid protocol: " + protocolStr + ". Defaulting to TCP (6).");
            return 6;
        }
    }

    public String validateAction(String action) {
        if (!"ACCEPT".equalsIgnoreCase(action) && !"REJECT".equalsIgnoreCase(action)) {
            Utilities.logWarning("Invalid action: " + action );
        }
        return action;
    }

    public int validateInteger(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " format: " + value + ". Must be a valid integer.", e);
        }
    }

    public long validateLong(String value, String fieldName) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " format: " + value + ". Must be a valid long.", e);
        }
    }
}
