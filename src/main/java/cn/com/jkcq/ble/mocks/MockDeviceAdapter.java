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
    /**
     * 蓝牙模块是否已打开
     */
    private boolean enabled = true;

    /**
     * 蓝牙扫描回调侦听
     */
    private ScanListener scanListener = null;

    /**
     * 设备实时数据回传侦听
     */
    private RealTimeDataListener realTimeDataListener = null;

    /**
     * 设备扫描发生器
     * 在调用scanDevices之前，必须先设置设备扫描发生器
     */
    private DeviceScanEmitter deviceScanEmitter = null;

    /**
     * 蓝牙设备是否已打开
     * @return
     */
    public boolean isAdapterEnabled() {
        return enabled;
    }

    /**
     * 设置蓝牙设备打开/关闭
     * @param enabled
     * @return
     */
    public MockDeviceAdapter setAdapterEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * 开始扫描设备
     * 注意: 必须先调用 setDeviceScanEmitter 配置扫描发生器
     * @return
     */
    public int scanDevices(){
        this.getDeviceScanEmitter().emit(this.scanListener);

        return BLE_R_OK;
    }

    /**
     * 执行蓝牙指令
     * @param command
     * @return
     */
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

    /**
     * 获取设备扫描发生器
     * @return
     */
    public DeviceScanEmitter getDeviceScanEmitter() {
        return deviceScanEmitter;
    }

    /**
     * 设置设备扫描发生器
     * @param deviceScanEmitter
     */
    public void setDeviceScanEmitter(DeviceScanEmitter deviceScanEmitter) {
        this.deviceScanEmitter = deviceScanEmitter;
    }
}
