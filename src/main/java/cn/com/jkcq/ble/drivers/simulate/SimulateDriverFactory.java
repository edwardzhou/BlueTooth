package cn.com.jkcq.ble.drivers.simulate;

import cn.com.jkcq.ble.DeviceDriver;
import cn.com.jkcq.ble.RealTimeData;
import cn.com.jkcq.ble.mocks.MockDeviceAdapter;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public class SimulateDriverFactory {
    public static DeviceDriver createDriver() {
        SimulateDriver driver = new SimulateDriver();
        MockDeviceAdapter mockAdapter = new MockDeviceAdapter();
        driver.setAdapter(mockAdapter);
        driver.registerCommandFactory(GetStepCommandFactory.getInstance());
        driver.registerCommandFactory(RealTimeDataCommandFactory.getInstance());

        return driver;
    }
}
