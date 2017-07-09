package cn.com.jkcq.ble.drivers.simulate;

import cn.com.jkcq.ble.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.com.jkcq.ble.Constants.BLE_R_OK;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_REAL_TIME_DATA;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class SimulateDriver extends BaseDriver {

    @Override
    public String getDriverName() {
        return "SimulateDriver";
    }

    @Override
    public int bindDevice(DeviceInfo deviceInfo) {
        return BLE_R_OK;
    }
}
