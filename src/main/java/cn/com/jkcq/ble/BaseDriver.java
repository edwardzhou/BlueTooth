package cn.com.jkcq.ble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public abstract class BaseDriver implements DeviceDriver {

    /**
     * 指令工厂映射表, key 指令名称, value 指令工厂实例
     */
    protected Map<String, DeviceCommandFactory> factoriesMap = new HashMap<String, DeviceCommandFactory>();

    /**
     * 蓝牙特征/通道对照表
     */
    protected Map<UUID, Object> characteristicsMap = new HashMap<UUID, Object>();


    private RealTimeDataListener realTimeDataListener = null;

    private DeviceAdapter adapter = null;


    /**
     * 驱动版本号
     * @return
     */
    public String getDriverVersion() {
        return null;
    }

    /**
     * 蓝牙设备通讯协议号
     * @return
     */
    public String getProtocolVersion() {
        return null;
    }

    /**
     * 设备描述信息
     * @return
     */
    public DeviceInfo getDeviceInfo() {
        return null;
    }

    public List<String> getSupportedFeatures() {
        return null;
    }

    public boolean isSupported(String feature) {
        return false;
    }

    /**
     * 执行蓝牙指令
     * @param cmd
     * @return
     */
    public int doCommand(DeviceCommand cmd) {
//        return false;
        return this.adapter.doCommand(cmd);
    }

    /**
     * 登记驱动程序所支持的指令的工厂
     * 用于createCommand时，创建相应的指令
     * @param factory - 指令的工厂实例
     */
    public void registerCommandFactory(DeviceCommandFactory factory) {
        factoriesMap.put(factory.getCommandName(), factory);
    }

    /**
     * 根据 commandName (指令名称) 相应的指令工厂创建指令
     * @param commandName - 指令名称
     * @param params - 可变参数
     * @return @DeviceCommand
     */
    public DeviceCommand createCommand(String commandName, Object... params) {
        DeviceCommandFactory factory = this.factoriesMap.get(commandName);
        if (factory == null) {
            return null;
        }

        return factory.createCommand(params);
    }

    public RealTimeDataListener getRealTimeDataListener() {
        return this.realTimeDataListener;
    }

    public void setRealTimeDataListener(RealTimeDataListener listener) {
        this.realTimeDataListener = listener;
        this.adapter.setRealTimeDataListener(listener);
    }

    public DeviceAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(DeviceAdapter adapter) {
        this.adapter = adapter;
    }

}
