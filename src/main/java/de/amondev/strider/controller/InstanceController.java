package de.amondev.strider.controller;

import de.amondev.strider.instance.Instance;

import java.util.Map;
import java.util.Optional;

public class InstanceController {

    public static InstanceController instance;

    private Map<String, Instance> instanceMap;

    public InstanceController() {
        InstanceController.instance = this;
    }

    public Optional<Instance> instance(String key) {
        return Optional.ofNullable(instanceMap.get(key.toLowerCase()));
    }
}
