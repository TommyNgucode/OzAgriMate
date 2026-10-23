package edu.rmit.notificationsservice;

public class Subscription {

    private int idSubscription;
    private int userId;
    private int farmId;
    private String channel;      // "SMS", "EMAIL", "PUSH"
    private String destination;  // phone number, email address or device token

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Subscription {" +
            "idSubscription=" + idSubscription +
            ", userId=" + userId +
            ", farmId=" + farmId +
            ", channel=" + channel +
            ", destination=" + destination +
            '}';
    }
}
