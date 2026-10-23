package edu.rmit.iotdevicesservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IotDeviceServiceLayer {

    private List <IotDevice> iotDevices = new ArrayList<>();
    private String[] deviceTypes = {"SOIL_MOISTURE", "WEATHER_STATION", "WATER_LEVEL", "TEMPERATURE"};
    private String[] locations = {"Paddock A", "Paddock B", "North Shed", "Greenhouse"};
    private String[] statuses = {"ACTIVE", "INACTIVE"};

    public IotDeviceServiceLayer () {
        createDummyRecordIotDevices();
    }
    //create dummy data for 10 farms (farmId 1 to 10)
    public final void createDummyRecordIotDevices () {
        if (iotDevices.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                IotDevice iotDevice = new IotDevice();
                iotDevice.setIdDevice(new Random().nextInt(100,1000));
                iotDevice.setFarmId(i);
                iotDevice.setDeviceType(deviceTypes[new Random().nextInt(deviceTypes.length)]);
                iotDevice.setLocation(locations[new Random().nextInt(locations.length)]);
                iotDevice.setStatus(statuses[new Random().nextInt(statuses.length)]);
                LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(new Random().nextInt(500));
                iotDevice.setLastUpdated(localDateTime.format(getFormatter()));
                iotDevices.add(iotDevice);
            }
        }
    }

    public boolean farmExists (int farmId) {
        return farmId >= 1 && farmId <= 10;
    }

    public List <IotDevice> getAllDevicesByFarm (int farmId) {
        List <IotDevice> farmDevices = new ArrayList<>();
        for (IotDevice iotDevice : iotDevices) {
            if (iotDevice.getFarmId() == farmId) {
                farmDevices.add(iotDevice);
            }
        }
        return farmDevices;
    }

    public IotDevice getDeviceById (int idDevice) {
        for (IotDevice iotDevice : iotDevices) {
            if (iotDevice.getIdDevice() == idDevice) {
                return iotDevice;
            }
        }
        return null;
    }

    public IotDevice addDevice (int farmId, IotDevice iotDevice) {
        iotDevice.setIdDevice(new Random().nextInt(100,1000));
        iotDevice.setFarmId(farmId);
        iotDevice.setLastUpdated(LocalDateTime.now().format(getFormatter()));
        iotDevices.add(iotDevice);
        return iotDevice;
    }

    public IotDevice updateDevice (int idDevice, IotDevice updatedDevice) {
        IotDevice iotDevice = getDeviceById(idDevice);
        if (iotDevice == null) {
            return null;
        }
        iotDevice.setDeviceType(updatedDevice.getDeviceType());
        iotDevice.setLocation(updatedDevice.getLocation());
        iotDevice.setStatus(updatedDevice.getStatus());
        iotDevice.setLastUpdated(LocalDateTime.now().format(getFormatter()));
        return iotDevice;
    }

    public boolean deleteDevice (int idDevice) {
        IotDevice iotDevice = getDeviceById(idDevice);
        if (iotDevice == null) {
            return false;
        }
        iotDevices.remove(iotDevice);
        return true;
    }

    private DateTimeFormatter getFormatter () {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
    }
}
