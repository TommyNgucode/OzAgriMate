package edu.rmit.notificationsservice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/notifications")
public class NotificationController {
    NotificationServiceLayer notificationServiceLayer;

    public NotificationController () {
        notificationServiceLayer = new NotificationServiceLayer();
    }

    @PostMapping ("/alerts")
    public ResponseEntity<Alert> createAlert (@RequestBody Alert alert) {
        if (alert.getRiskType() == null || alert.getSeverity() == null || alert.getMessage() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!notificationServiceLayer.farmExists(alert.getFarmId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(notificationServiceLayer.createAlert(alert), HttpStatus.CREATED);
    }

    @GetMapping ("/farms/{farmId}/alerts")
    public ResponseEntity<List<Alert>> getAlertsByFarm (@PathVariable int farmId) {
        if (!notificationServiceLayer.farmExists(farmId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notificationServiceLayer.getAlertsByFarm(farmId), HttpStatus.OK);
    }

    @PostMapping ("/subscriptions")
    public ResponseEntity<Subscription> addSubscription (@RequestBody Subscription subscription) {
        if (subscription.getChannel() == null || subscription.getDestination() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (notificationServiceLayer.subscriptionExists(subscription)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(notificationServiceLayer.addSubscription(subscription), HttpStatus.CREATED);
    }
}
