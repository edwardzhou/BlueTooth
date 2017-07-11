package cn.com.jkcq.ble;

import cn.com.jkcq.ble.mocks.DeviceScanEmitter;
import cn.com.jkcq.ble.mocks.MockDeviceAdapter;
import cn.com.jkcq.ble.mocks.MockScannerFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import static cn.com.jkcq.ble.Constants.*;


/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BluetoothManagerTest {
    private BluetoothManager manager = null;

    @Before
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
        /*
        模拟蓝牙发现一个设备
         */
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
        final ArrayList<DeviceInfo> devices = new ArrayList<DeviceInfo>();

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
        /*
        启用多线程，模拟蓝牙模块异步通知设备发现的过程
         */
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

        final ArrayList<DeviceInfo> devices = new ArrayList<DeviceInfo>();

        ScanListener listener = new ScanListener() {
            @Override
            public void onDeviceFound(DeviceInfo deviceInfo) {
                devices.add(deviceInfo);
            }
        };

        assertEquals(this.manager.scan(listener), BLE_R_OK);
        // 暂停500ms, 等待蓝牙发现
        try {
            Thread.sleep(500);
        } catch(Exception ex) {

        }
        assertEquals(devices.size(), 2);
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
