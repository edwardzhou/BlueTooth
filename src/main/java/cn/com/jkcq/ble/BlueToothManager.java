package cn.com.jkcq.ble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static cn.com.jkcq.ble.Constants.*;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BlueToothManager {
    private static BlueToothManager instance = null;
    private BlueAdapter adapter = null;
    private Map<String, BlueToothDevice> drivers = new HashMap<String, BlueToothDevice>();

    public BlueToothManager() {
        this.resetDefaultAdapter();
    }

    public BlueAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(BlueAdapter adapter) {
        this.adapter = adapter;
    }

    public static BlueToothManager getInstance() {
        if (instance == null) {
            instance = new BlueToothManager();
        }

        return instance;
    }

    public boolean isBlueToothEnabled() {
        return this.adapter.getEnabled();
    }

    public int scan() {
        if (! this.adapter.getEnabled()) {
           return BLE_R_BLUETOOTH_DISABLED;
        }

        //... do scan...
        return BLE_R_OK;
    }

    public List<DeviceInfo> getDevices() {
        return null;
    }

    public BlueToothDevice connectTo(DeviceInfo DeviceInfo) {
        String deviceName = DeviceInfo.deviceName;
        BlueToothDevice driver = this.drivers.get(deviceName);
        return driver;
    }


    public Map<String, BlueToothDevice> getSupportedDrivers() {
        return this.drivers;
    }

    public void registerDriver(BlueToothDevice device) {
        this.drivers.put(device.getDriverName(), device);
    }

    public void resetDefaultAdapter() {
        this.setAdapter(new BlueAdapter());
    }
}
