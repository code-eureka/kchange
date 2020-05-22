package com.kchange.common.model;

import java.util.UUID;

public class Device {
    String id;
    String name;
    String os;
    String version;
    String uniqueDeviceIdentifier;
    StatusCode status;

    public Device(String name, String os, String version, String uniqueDeviceIdentifier) {
        this.name = name;
        this.os = os;
        this.version = version;
        this.uniqueDeviceIdentifier = uniqueDeviceIdentifier;
        this.id = UUID.randomUUID().toString();
        this.status = StatusCode.ACTIVE;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public String getVersion() {
        return version;
    }

    public String getUniqueDeviceIdentifier() {
        return uniqueDeviceIdentifier;
    }

    public StatusCode getStatus() {
        return status;
    }
}
