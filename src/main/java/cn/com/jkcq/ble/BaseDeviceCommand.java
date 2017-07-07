package cn.com.jkcq.ble;

import java.util.UUID;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class BaseDeviceCommand implements DeviceCommand {
    private String commandName = "";
    private byte[] data = null;
    private UUID characteristicUUID = null;
    private UUID serviceUUID = null;

    public String getCommandName() {
        return this.commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public UUID getCharacteristicUUID() {
        return this.characteristicUUID;
    }

    public void setCharacteristicUUID(UUID uuid) {
        this.characteristicUUID = uuid;
    }

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }

    public void setServiceUUID(UUID serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = new byte[data.length];
        System.arraycopy(data,0, this.data, 0, data.length);
    }

}
