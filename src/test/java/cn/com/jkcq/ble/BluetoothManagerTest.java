package cn.com.jkcq.ble;

import cn.com.jkcq.ble.mocks.MockDeviceAdapter;
import cn.com.jkcq.ble.mocks.MockScannerFactory;
import cn.com.jkcq.ble.mocks.MockScannerFactory3;
import org.testng.annotations.*;

import java.util.ArrayList;

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

        this.manager.setScannerFactory(new MockScannerFactory());
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
        this.manager.setScannerFactory(new MockScannerFactory3());
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
