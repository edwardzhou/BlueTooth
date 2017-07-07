package cn.com.jkcq.ble;

import cn.com.jkcq.ble.drivers.simulate.SimulateDriver;
import cn.com.jkcq.ble.drivers.simulate.SimulateDriverFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_REAL_TIME_DATA;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public class SimulateManagerTest {

    private BluetoothManager manager = null;

    @BeforeMethod
    public void setUp() {
        manager = new BluetoothManager();
        DeviceDriver simDriver = SimulateDriverFactory.createDriver();
        manager.registerDriver(simDriver);
    }

    /**
     * 测试模拟设备链接
     */
    @Test(groups = {"Simulate"})
    public void testConnect() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "SimulateDriver";
        DeviceDriver connectedDriver = this.manager.connectTo(deviceInfo);
        assertNotNull(connectedDriver);
        assertEquals(connectedDriver.getDriverName(), "SimulateDriver");
    }

    public void testRealTimeDataRequest() {

    }

    /**
     * 测试是设备实时数据回传
     */
    @Test
    public void testReceiveOneTimeRealTimeData() {

        final RealTimeData[] realTimeData = {null};

        RealTimeDataListener listener = new RealTimeDataListener() {
            @Override
            public void onData(RealTimeData data) {
                realTimeData[0] = data;
            }
        };

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = "SimulateDriver";
        DeviceDriver connectedDriver = this.manager.connectTo(deviceInfo);

        manager.setRealTimeDataListener(listener);
        manager.sendCommand(COMMAND_REAL_TIME_DATA);

        // sleep (5);
        assertNotNull(realTimeData[0]);
        assertTrue(realTimeData[0].getSteps() > 0);

    }

}
