package cn.com.jkcq.ble;

import cn.com.jkcq.ble.mocks.DeviceScanEmitter;
import cn.com.jkcq.ble.mocks.MockDeviceAdapter;
import cn.com.jkcq.ble.mocks.MockScannerFactory;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static cn.com.jkcq.ble.Constants.*;


/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BluetoothManagerTest {
    private BluetoothManager manager = null;

    @BeforeMethod
    public void setUp(){
        this.manager = new BluetoothManager();
    }

    public void tearDown(){
    }

    @Test
    public void testIsBlueToothEnabled_ForEnabled() {
        assertTrue(this.manager.isBlueToothEnabled());
    }

    @Test
    public void testIsBlueToothEnabled_ForDisabled() {
        MockDeviceAdapter disabledAdapter = new MockDeviceAdapter().setAdapterEnabled(false);
        this.manager.setAdapter(disabledAdapter);
        assertFalse(this.manager.isBlueToothEnabled());
    }

    @Test
    public void testScan_forOneDevice() {
        MockScannerFactory factory = new MockScannerFactory();
        MockDeviceAdapter deviceAdapter = new MockDeviceAdapter();
        deviceAdapter.setDeviceScanEmitter(new DeviceScanEmitter() {
            @Override
            public void emit(ScanListener listener) {
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.deviceName = "Device A";
                listener.onDeviceFound(deviceInfo);
            }
        });
        factory.setDeviceAdapter(deviceAdapter);

        this.manager.setScannerFactory(factory);
        ArrayList<DeviceInfo> devices = new ArrayList<DeviceInfo>();

        ScanListener listener = new ScanListener() {
            @Override
            public void onDeviceFound(DeviceInfo deviceInfo) {
                devices.add(deviceInfo);
            }
        };

        assertEquals(this.manager.scan(listener), BLE_R_OK);
        assertEquals(devices.size(), 1);
    }

    @Test
    public void testScan_for3Devices() {
        MockScannerFactory factory = new MockScannerFactory();
        MockDeviceAdapter deviceAdapter = new MockDeviceAdapter();
        deviceAdapter.setDeviceScanEmitter(new DeviceScanEmitter() {
            @Override
            public void emit(final ScanListener listener) {
                final List<DeviceInfo> devices = new ArrayList<DeviceInfo>();
                devices.add(new DeviceInfo("Device A"));
                devices.add(new DeviceInfo("Device B"));
                devices.add(new DeviceInfo("Device C"));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (DeviceInfo device : devices) {
                            listener.onDeviceFound(device);
                        }
                    }
                }).start();
            }
        });
        factory.setDeviceAdapter(deviceAdapter);
        this.manager.setScannerFactory(factory);

        ArrayList<DeviceInfo> devices = new ArrayList<DeviceInfo>();

        ScanListener listener = new ScanListener() {
            @Override
            public void onDeviceFound(DeviceInfo deviceInfo) {
                devices.add(deviceInfo);
            }
        };

        assertEquals(this.manager.scan(listener), BLE_R_OK);
        try {
            Thread.sleep(500);
        } catch(Exception ex) {

        }
        assertEquals(devices.size(), 3);
    }

    @Test
    public void testConnectNonMatchedDevice() {
        // this.manager.set....;

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "XXX";
        deviceInfo.deviceMac = "Non Exists";
        deviceInfo.deviceUUID = "Non Exists";

        DeviceDriver device = this.manager.connectTo(deviceInfo);
        assertNull(device);
    }

}
