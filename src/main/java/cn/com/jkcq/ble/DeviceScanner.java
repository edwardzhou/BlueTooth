package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class DeviceScanner {
//
//    interface ScanListener {
//        void onDeviceFound(DeviceInfo deviceInfo);
//    }
//
    private ScanListener listener = null;
    private DeviceAdapter adapter = null;

    public ScanListener getListener() {
        return this.listener;
    }

    public void setListener(ScanListener listener) {
        this.listener = listener;
    }

    public DeviceAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(DeviceAdapter adapter) {
        this.adapter = adapter;
    }

    public void start() {
        this.adapter.setScanListener(this.listener);
        this.adapter.scanDevices();
    }

    public void run() {
        //....
        this.listener.onDeviceFound(new DeviceInfo(/*....*/));
    }

}
