package cn.com.jkcq.ble.drivers.simulate;

import cn.com.jkcq.ble.BaseDeviceCommand;
import cn.com.jkcq.ble.DeviceCommand;
import cn.com.jkcq.ble.DeviceCommandFactory;

import java.util.UUID;

import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.CHARACTERISTIC_UUID_1;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_GET_STEP;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class GetStepCommandFactory implements DeviceCommandFactory {
    private static final String COMMAND_NAME = COMMAND_GET_STEP;
    private static final UUID uuid = UUID.fromString(CHARACTERISTIC_UUID_1);

    private static DeviceCommandFactory instance = null;

    public DeviceCommand createCommand(Object... params) {
        BaseDeviceCommand cmd = new BaseDeviceCommand();
        cmd.setCommandName(COMMAND_NAME);
        cmd.setCharacteristicUUID(uuid);
        cmd.setData(new byte[] {0x10, 0x20, 0x30, 0x40});

        return cmd;
    }

    public String getCommandName() {
        return COMMAND_NAME;
    }

    public UUID getUUID() {
        return uuid;
    }

    public static DeviceCommandFactory getInstance() {
        if (instance == null) {
            instance = new GetStepCommandFactory();
        }
        return instance;
    }
}
