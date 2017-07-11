package cn.com.jkcq.ble;


import cn.com.jkcq.ble.mocks.MockDeviceAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static cn.com.jkcq.ble.Constants.*;

/**
 * Created by edwardzhou on 2017/7/5.
 */


public class BluetoothManagerErrorTest {
    private BluetoothManager manager = null;

    @Before
    public void setUp() {
        this.manager = new BluetoothManager();
    }

    @Test
    public void testScan_forBluetoothDisabled() {
        this.manager.setAdapter(new MockDeviceAdapter().setAdapterEnabled(false));

        assertEquals(this.manager.scan(null), BLE_R_BLUETOOTH_DISABLED);
    }
}
