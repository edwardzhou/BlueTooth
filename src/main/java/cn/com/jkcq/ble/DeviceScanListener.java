package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public interface DeviceScanListener {
    void onDeviceFound(DeviceInfo deviceInfo);
}
