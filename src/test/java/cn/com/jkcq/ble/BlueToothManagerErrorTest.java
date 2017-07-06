package cn.com.jkcq.ble;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import static cn.com.jkcq.ble.Constants.*;

/**
 * Created by edwardzhou on 2017/7/5.
 */


public class BlueToothManagerErrorTest {
    private BlueToothManager manager = null;

    @BeforeMethod
    public void setUp() {
        this.manager = new BlueToothManager();
    }

    @Test
    public void testScan_forBluetoothDisabled() {
        this.manager.setAdapter(new MockDeviceAdapter().setAdapterEnabled(false));

        assertEquals(this.manager.scan(null), BLE_R_BLUETOOTH_DISABLED);
    }
}
