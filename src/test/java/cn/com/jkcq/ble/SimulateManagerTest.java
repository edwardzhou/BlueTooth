package cn.com.jkcq.ble;

import cn.com.jkcq.ble.drivers.simulate.SimulateDriverFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_REAL_TIME_DATA;


/**
 * Created by edwardzhou on 2017/7/7.
 */
public class SimulateManagerTest {

    private BleManager manager = null;

    @Before
    public void setUp() {
        manager = new BleManager();
        DeviceDriver simDriver = SimulateDriverFactory.createDriver();
        manager.registerDriver(simDriver);
    }

    /**
     * 测试模拟设备链接
     */
    @Test
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
