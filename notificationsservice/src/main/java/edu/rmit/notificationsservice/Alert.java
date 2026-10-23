package edu.rmit.notificationsservice;

public class Alert {

    private int idAlert;
    private int farmId;
    private String riskType;     // e.g., "FROST", "FLOOD", "DROUGHT"
    private String severity;     // "LOW", "MEDIUM", "HIGH"
    private String message;
    private String timestamp;    // e.g., "2026-10-23, 04:15:00"
    private boolean acknowledged;

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    @Override
    public String toString() {
        return "Alert {" +
            "idAlert=" + idAlert +
            ", farmId=" + farmId +
            ", riskType=" + riskType +
            ", severity=" + severity +
            ", message=" + message +
            ", timestamp=" + timestamp +
            ", acknowledged=" + acknowledged +
            '}';
    }
}
