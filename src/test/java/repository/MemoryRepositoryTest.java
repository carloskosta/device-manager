package repository;

import model.Device;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static model.Device.DeviceState.OFF;
import static model.Device.DeviceState.ON;

class MemoryRepositoryTest {

    private CrudRepository crudRepository;

    @BeforeEach
    void startUp() {
        crudRepository = new MemoryRepository();
    }

    @Test
    void shouldReturnTrueBecauseAddIsCorrect() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();
        //When
        var result = crudRepository.add(device);

        //Then
        Assertions.assertThat(result).isEqualTo(true);

    }


    @Test
    void shouldReturnFalseBecauseThereAreDuplicate() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();
        //When
        var result = Function.<Device>identity()
                .andThen(d -> crudRepository.add(d))
                .andThen(r -> crudRepository.add(device))
                .apply(device);

        //Then
        Assertions.assertThat(result).isEqualTo(false);

    }

    @Test
    void shouldReturnFalseBecauseThereAreDuplicatesWithOtherState() {
        //Given
        var deviceOn = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();
        var deviceOff = Device.builder()
                .deviceState(OFF)
                .name("PC")
                .build();
        //When
        var result = Function.<Device>identity()
                .andThen(d -> crudRepository.add(d))
                .andThen(r -> crudRepository.add(deviceOff))
                .apply(deviceOn);

        //Then
        Assertions.assertThat(result).isEqualTo(false);

    }

    @Test
    void shouldReturnTrueBecauseDeleteIsCorrect() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();

        //When
        var resultAdd = crudRepository.add(device);
        var resultDelete = crudRepository.delete("PC");

        //Then
        Assertions.assertThat(resultAdd).isEqualTo(true);
        Assertions.assertThat(resultDelete).isEqualTo(true);
    }

    @Test
    void shouldReturnFalseBecauseDeletedDeviceNotFound() {
        //When
        var resultDelete = crudRepository.delete("PC");

        //Then
        Assertions.assertThat(resultDelete).isEqualTo(false);
    }

    @Test
    void shouldReturnDeviceBecauseGetIsCorrect() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();

        //When
        var resultAdd = crudRepository.add(device);
        var resultGet = crudRepository.get("PC");

        //Then
        Assertions.assertThat(resultAdd).isEqualTo(true);
        Assertions.assertThat(resultGet).isEqualTo(device);
    }

    @Test
    void shouldReturnNullBecauseGotDeviceNotFound() {
        //When
        var resultGet = crudRepository.get("PC");

        //Then
        Assertions.assertThat(resultGet).isEqualTo(null);
    }

    @Test
    void shouldReturnTrueBecauseModifyIsCorrect() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();

        //When
        var resultAdd = crudRepository.add(device);
        var resultModify = crudRepository.modify("PC");

        //Then
        Assertions.assertThat(resultAdd).isEqualTo(true);
        Assertions.assertThat(resultModify).isEqualTo(true);
    }

    @Test
    void shouldReturnFalseBecauseModifiedDeviceNotFound() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();

        //When
        var resultAdd = crudRepository.add(device);
        var resultModify = crudRepository.modify("PD");

        //Then
        Assertions.assertThat(resultAdd).isEqualTo(true);
        Assertions.assertThat(resultModify).isEqualTo(false);
    }

    @Test
    void shouldReturnListWithDataBecauseDeviceWasAdded() {
        //Given
        var device = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();

        //When
        var resultAdd = crudRepository.add(device);
        var resultGetAll = crudRepository.getAll();

        //Then
        Assertions.assertThat(resultAdd).isEqualTo(true);
        Assertions.assertThat(resultGetAll).contains(device);
    }

    @Test
    void shouldReturnListWithDataBecauseDevicesWasAdded() {
        //Given
        var devicePC = Device.builder()
                .deviceState(ON)
                .name("PC")
                .build();
        var devicePD = Device.builder()
                .deviceState(OFF)
                .name("PD")
                .build();

        //When
        var resultAddPC = crudRepository.add(devicePC);
        var resultAddPD = crudRepository.add(devicePD);
        var resultGetAll = crudRepository.getAll();

        //Then
        Assertions.assertThat(resultAddPC).isEqualTo(true);
        Assertions.assertThat(resultAddPD).isEqualTo(true);
        Assertions.assertThat(resultGetAll).hasSize(2);
        Assertions.assertThat(resultGetAll).contains(devicePC, devicePD);
    }

    @Test
    void shouldReturnListWithoutDataBecauseDevicesWasNotAdded() {
        //When
        var resultGetAll = crudRepository.getAll();

        //Then
        Assertions.assertThat(resultGetAll).hasSize(0);
    }

    @Test
    void shouldReturnFalseBecauseAddDeviceIsNull() {
        //Give-When
        var device = crudRepository.add(null);
        //Then
        Assertions.assertThat(device).isEqualTo(false);
    }

    @Test
    void shouldReturnFalseBecauseDeleteDeviceIsNull() {
        //Give-When
        var result = crudRepository.delete(null);
        //Then
        Assertions.assertThat(result).isEqualTo(false);
    }

    @Test
    void shouldReturnNullBecauseNameIsNull() {
        //Give-When
        var device = crudRepository.get(null);
        //Then
        Assertions.assertThat(device).isEqualTo(null);
    }

    @Test
    void shouldReturnFalseBecauseModifyNameIsNull() {
        //Give-When
        var result = crudRepository.modify(null);
        //Then
        Assertions.assertThat(result).isEqualTo(false);
    }

}
