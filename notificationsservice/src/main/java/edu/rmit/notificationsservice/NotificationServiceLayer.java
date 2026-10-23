package edu.rmit.notificationsservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificationServiceLayer {

    private List <Alert> alerts = new ArrayList<>();
    private List <Subscription> subscriptions = new ArrayList<>();
    private String[] riskTypes = {"FROST", "FLOOD", "DROUGHT", "HEATWAVE"};
    private String[] severities = {"LOW", "MEDIUM", "HIGH"};

    public NotificationServiceLayer () {
        createDummyRecordAlerts();
    }
    //create dummy data for 10 farms (farmId 1 to 10)
    public final void createDummyRecordAlerts () {
        if (alerts.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                Alert alert = new Alert();
                alert.setIdAlert(new Random().nextInt(1000,10000));
                alert.setFarmId(i);
                alert.setRiskType(riskTypes[new Random().nextInt(riskTypes.length)]);
                alert.setSeverity(severities[new Random().nextInt(severities.length)]);
                alert.setMessage(alert.getRiskType() + " risk detected on farm " + i);
                LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(new Random().nextInt(500));
                alert.setTimestamp(localDateTime.format(getFormatter()));
                alert.setAcknowledged(false);
                alerts.add(alert);
            }
        }
    }

    public boolean farmExists (int farmId) {
        return farmId >= 1 && farmId <= 10;
    }

    public Alert createAlert (Alert alert) {
        alert.setIdAlert(new Random().nextInt(1000,10000));
        alert.setTimestamp(LocalDateTime.now().format(getFormatter()));
        alert.setAcknowledged(false);
        alerts.add(alert);
        return alert;
    }

    public List <Alert> getAlertsByFarm (int farmId) {
        List <Alert> farmAlerts = new ArrayList<>();
        for (Alert alert : alerts) {
            if (alert.getFarmId() == farmId) {
                farmAlerts.add(alert);
            }
        }
        return farmAlerts;
    }

    public Subscription addSubscription (Subscription subscription) {
        subscription.setIdSubscription(new Random().nextInt(100,1000));
        subscriptions.add(subscription);
        return subscription;
    }

    public boolean subscriptionExists (Subscription subscription) {
        for (Subscription existing : subscriptions) {
            if (existing.getUserId() == subscription.getUserId()
                    && existing.getFarmId() == subscription.getFarmId()
                    && existing.getChannel().equals(subscription.getChannel())) {
                return true;
            }
        }
        return false;
    }

    private DateTimeFormatter getFormatter () {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
    }
}
