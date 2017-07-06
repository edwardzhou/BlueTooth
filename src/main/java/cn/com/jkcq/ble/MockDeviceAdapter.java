package cn.com.jkcq.ble;

import static cn.com.jkcq.ble.Constants.BLE_R_OK;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class MockDeviceAdapter implements DeviceAdapter {
    private boolean enabled = true;

    private ScanListener scanListener = null;

    public boolean isAdapterEnabled() {
        return enabled;
    }

    public MockDeviceAdapter setAdapterEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public int scanDevices(){

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceUUID = "ABC";
        deviceInfo.deviceName = "MockDevice";

        this.scanListener.onDeviceFound(deviceInfo);

        return BLE_R_OK;
    }

    public ScanListener getScanListener() {
        return this.scanListener;
    }

    public void setScanListener(ScanListener listener) {
        this.scanListener = listener;
    }

}
