package cn.com.jkcq.ble;

import cn.com.jkcq.ble.drivers.simulate.SimulateDriver;

import org.testng.annotations.*;

import java.util.ArrayList;

import static org.testng.Assert.*;
import static cn.com.jkcq.ble.Constants.*;


/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BlueToothManagerTest {
    private BlueToothManager manager = null;

    @BeforeMethod
    public void setUp(){
        this.manager = new BlueToothManager();
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
    public void testConnect() {

        DeviceDriver driver = new SimulateDriver();
        this.manager.registerDriver(driver);

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "SimulateDriver";
        DeviceDriver device = this.manager.connectTo(deviceInfo);
        assertNotNull(device);
        assertEquals(device.getDriverName(), "SimulateDriver");
    }

    @Test
    public void testConnectNonMatchedDevice() {
        // this.manager.set....;

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "XXX";
        deviceInfo.deviceMac = "Non Exists";
        deviceInfo.deviceUUID = "Non Exists";

        DeviceDriver device = this.manager.connectTo(deviceInfo);
        assert device == null;
    }

    public void testConnectNonSupportedDevice() {
        // this.manager.set....;

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "XXX";
        deviceInfo.deviceMac = "Non Exists";
        deviceInfo.deviceUUID = "Non Exists";

        DeviceDriver device = this.manager.connectTo(deviceInfo);
        assert device == null;
    }


    public void testConnectToSimulator() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "Simulator";
        deviceInfo.deviceMac = "Non Exists";
        deviceInfo.deviceUUID = "Non Exists";

        DeviceDriver device = this.manager.connectTo(deviceInfo);
        assert device.getClass().getName() == "BlueToothSimulator";

    }
}
