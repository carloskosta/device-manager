package repository;

import model.Device;

import java.util.Set;

public interface CrudRepository {
    boolean add(final Device device);

    boolean delete(final String name);

    boolean modify(final String name);

    Device get(final String name);

    Set<Device> getAll();
}
