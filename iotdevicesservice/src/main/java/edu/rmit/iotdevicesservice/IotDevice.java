package edu.rmit.iotdevicesservice;

public class IotDevice {

    private int idDevice;
    private int farmId;
    private String deviceType;  // e.g., "SOIL_MOISTURE", "WEATHER_STATION"
    private String location;    // e.g., "Paddock A"
    private String status;      // "ACTIVE" or "INACTIVE"
    private String lastUpdated; // e.g., "2026-10-23, 09:14:02"

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "IotDevice {" +
            "idDevice=" + idDevice +
            ", farmId=" + farmId +
            ", deviceType=" + deviceType +
            ", location=" + location +
            ", status=" + status +
            ", lastUpdated=" + lastUpdated +
            '}';
    }
}
