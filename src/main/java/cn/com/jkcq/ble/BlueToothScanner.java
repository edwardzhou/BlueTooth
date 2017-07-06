package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BlueToothScanner {
    private ScanListener listener = null;

    public void setListener(ScanListener listener) {
        this.listener = listener;
    }

    public void start() {

    }


    public void run() {
        //....
        this.listener.onDeviceFound(new DeviceInfo(/*....*/));
    }

}
