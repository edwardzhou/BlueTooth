package cn.com.jkcq.ble;

import java.util.UUID;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class BaseDeviceCommand implements DeviceCommand {
    private String commandName = "";
    private byte[] data = null;
    private UUID uuid = null;

    public String getCommandName() {
        return this.commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = new byte[data.length];
        System.arraycopy(data,0, this.data, 0, data.length);
    }

}
