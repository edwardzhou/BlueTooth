package cn.com.jkcq.ble;

import cn.com.jkcq.ble.mocks.MockDeviceAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static cn.com.jkcq.ble.Constants.*;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BluetoothManager {
    private static BluetoothManager instance = null;
    private DeviceAdapter adapter = null;
    private Map<String, DeviceDriver> drivers = new HashMap<String, DeviceDriver>();
    private ScannerFactory scannerFactory = null;
    private RealTimeDataListener readTimeDataListener = null;
    private DeviceDriver connectedDriver = null;

    public BluetoothManager() {
        this.resetDefaultAdapter();
    }

    public ScannerFactory getScannerFactory() {
        return this.scannerFactory;
    }

    public void setScannerFactory(ScannerFactory scannerFactory) {
        this.scannerFactory = scannerFactory;
    }

    public DeviceAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(DeviceAdapter adapter) {
        this.adapter = adapter;
    }

    public static BluetoothManager getInstance() {
        if (instance == null) {
            instance = new BluetoothManager();
        }

        return instance;
    }

    public boolean isBlueToothEnabled() {
        return this.adapter.isAdapterEnabled();
    }

    public int scan(ScanListener listener) {
        if (! this.adapter.isAdapterEnabled()) {
           return BLE_R_BLUETOOTH_DISABLED;
        }
        DeviceScanner scanner = this.scannerFactory.getScanner();
        scanner.setListener(listener);
        scanner.start();
        //... do scan...
        return BLE_R_OK;
    }

    public List<DeviceInfo> getDevices() {
        return null;
    }

    public DeviceDriver connectTo(DeviceInfo DeviceInfo) {
        String deviceName = DeviceInfo.deviceName;
//        DeviceDriver driver = this.drivers.get(deviceName);
//
//        return driver;
        this.connectedDriver = this.drivers.get(deviceName);
        return this.connectedDriver;
    }


    public Map<String, DeviceDriver> getSupportedDrivers() {
        return this.drivers;
    }

    public void registerDriver(DeviceDriver device) {
        this.drivers.put(device.getDriverName(), device);
    }

    public void resetDefaultAdapter() {
        this.setAdapter(new MockDeviceAdapter());
    }

    public void setRealTimeDataListener(RealTimeDataListener listener) {
        this.readTimeDataListener = listener;
        this.connectedDriver.setRealTimeDataListener(listener);
    }

    public void sendCommand(String commandName) {
        DeviceCommand command = this.connectedDriver.createCommand(commandName);

        sendCommand(command);
    }

    public void sendCommand(DeviceCommand command) {
        this.connectedDriver.doCommand(command);
    }
}
