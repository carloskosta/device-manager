package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static model.Device.DeviceState.OFF;
import static model.Device.DeviceState.ON;

class DeviceTest {

    @Test
    void shouldReturnOffState() {
        //Given
        var device = Device.builder()
                .name("test")
                .deviceState(ON)
                .build();
        //When
        var result = device.changeState();

        //Then
        var expected = Device.builder()
                .name("test")
                .deviceState(OFF)
                .build();
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnOnState() {
        //Given
        var device = Device.builder()
                .name("test")
                .deviceState(OFF)
                .build();
        //When
        var result = device.changeState();

        //Then
        var expected = Device.builder()
                .name("test")
                .deviceState(ON)
                .build();
        Assertions.assertThat(result).isEqualTo(expected);
    }

}
