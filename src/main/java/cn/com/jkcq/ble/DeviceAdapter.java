package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public interface DeviceAdapter {
    boolean isAdapterEnabled();

    int scanDevices();

    ScanListener getScanListener();
    void setScanListener(ScanListener listener);
}
