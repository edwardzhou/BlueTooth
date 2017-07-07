package cn.com.jkcq.ble.mocks;

import cn.com.jkcq.ble.*;

import java.util.ArrayList;
import java.util.List;

import static cn.com.jkcq.ble.Constants.BLE_R_OK;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_REAL_TIME_DATA;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class MockDeviceAdapter implements DeviceAdapter {
    private boolean enabled = true;

    private ScanListener scanListener = null;

    private RealTimeDataListener realTimeDataListener = null;

    private List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();

    public List<DeviceInfo> getDeviceInfos() {
        return deviceInfos;
    }

    public boolean isAdapterEnabled() {
        return enabled;
    }

    public MockDeviceAdapter setAdapterEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public int scanDevices(){
        for (DeviceInfo info: this.deviceInfos) {
            this.scanListener.onDeviceFound(info);
        }

        return BLE_R_OK;
    }

    @Override
    public int doCommand(DeviceCommand command) {
        if (command.getCommandName() == COMMAND_REAL_TIME_DATA) {
            RealTimeData data = new RealTimeData();
            data.setSteps(5000);
            this.getRealTimeDataListener().onData(data);
        }

        return 0;
    }

    public ScanListener getScanListener() {
        return this.scanListener;
    }

    public void setScanListener(ScanListener listener) {
        this.scanListener = listener;
    }

    @Override
    public RealTimeDataListener getRealTimeDataListener() {
        return this.realTimeDataListener;
    }

    @Override
    public void setRealTimeDataListener(RealTimeDataListener listener) {
        this.realTimeDataListener = listener;
    }

}
