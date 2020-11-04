package model;

import lombok.Builder;
import lombok.Data;

import static model.Device.DeviceState.OFF;
import static model.Device.DeviceState.ON;

@Data
@Builder
public class Device {
    private String name;
    private DeviceState deviceState;

    public Device changeState() {
        if (this.deviceState.equals(OFF)) {
            deviceState = ON;
        } else {
            deviceState = OFF;
        }
        return this;
    }

    public enum DeviceState {
        OFF,
        ON
    }
}
