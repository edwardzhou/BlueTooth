package cn.com.jkcq.ble;

import cn.com.jkcq.ble.drivers.simulate.SimulateDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public class SimulateManagerTest {

    private BlueToothManager manager = null;

    @BeforeMethod
    public void setUp() {
        manager = new BlueToothManager();
        DeviceDriver simDriver = new SimulateDriver();
        manager.registerDriver(simDriver);
    }

    @Test
    public void testConnect() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "SimulateDriver";
        DeviceDriver connectedDriver = this.manager.connectTo(deviceInfo);
        assertNotNull(connectedDriver);
        assertEquals(connectedDriver.getDriverName(), "SimulateDriver");
    }

    /**
     * 测试是设备实时
     */
    @Test
    public void testReceiveRealTimeData() {

    }

}
