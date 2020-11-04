package repository;

import io.vavr.control.Try;
import model.Device;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class MemoryRepository implements CrudRepository {

    private final Set<Device> devices = new HashSet<>();

    @Override
    public boolean add(final Device device) {
        return Optional.ofNullable(device)
                .map(deviceNotNull -> {
                    if (devices.contains(deviceNotNull)
                            || devices.stream().anyMatch(d -> d.getName().equals(deviceNotNull.getName()))) {
                        System.out.println("Duplicated device");
                        return false;
                    }
                    return Try.of(() -> devices.add(device)).getOrElse(false);
                }).orElse(false);
    }

    @Override
    public boolean delete(final String name) {
        return Function.<String>identity()
                .andThen(nameDelete -> devices.removeIf(device -> device.getName().equalsIgnoreCase(nameDelete)))
                .andThen(result -> {
                    if (!result)
                        System.out.println("Device not found");
                    return result;
                }).apply(name);
    }

    @Override
    public Device get(final String name) {
        return devices.stream().filter(device -> device.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }


    @Override
    public boolean modify(final String name) {
        var changedDevice = Optional.ofNullable(this.get(name))
                .map(device -> {
                    devices.remove(device);
                    return device.changeState();
                }).orElseGet(() -> {
                    System.out.println("Device not found");
                    return null;
                });

        return Optional.ofNullable(changedDevice)
                .map(d -> Try.of(() -> this.devices.add(d)).getOrElse(false))
                .orElse(false);
    }

    @Override
    public Set<Device> getAll() {
        if (devices.size() == 0) {
            System.out.println("No devices added");
        }
        return devices;
    }
}
