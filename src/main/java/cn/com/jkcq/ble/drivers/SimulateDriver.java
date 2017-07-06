package cn.com.jkcq.ble.drivers;

import cn.com.jkcq.ble.BlueToothDevice;
import cn.com.jkcq.ble.DeviceInfo;
import cn.com.jkcq.ble.DeviceCommand;

import java.util.List;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class SimulateDriver implements BlueToothDevice {
    public String getDriverName() {
        return "SimulateDriver";
    }

    public String getDriverVersion() {
        return null;
    }

    public String getProtocolVersion() {
        return null;
    }

    public DeviceInfo getDeviceInfo() {
        return null;
    }

    public List<String> getSupportedFeatures() {
        return null;
    }

    public boolean isSupported(String feature) {
        return false;
    }

    public boolean doCommand(DeviceCommand cmd) {
        return false;
    }
}
