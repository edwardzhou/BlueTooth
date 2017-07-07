package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public interface DeviceAdapter {
    boolean isAdapterEnabled();

    int scanDevices();

    int doCommand(DeviceCommand command);

    ScanListener getScanListener();
    void setScanListener(ScanListener listener);

    RealTimeDataListener getRealTimeDataListener();
    void setRealTimeDataListener(RealTimeDataListener listener);
}
