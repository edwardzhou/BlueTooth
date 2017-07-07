package cn.com.jkcq.ble;

import java.util.UUID;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public interface DeviceCommandFactory {
    DeviceCommand createCommand(Object... params);
    String getCommandName();
    UUID getCharacteristicUUID();
    UUID getServiceUUID();
}
