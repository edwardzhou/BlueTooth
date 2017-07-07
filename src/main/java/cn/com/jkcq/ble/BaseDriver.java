package cn.com.jkcq.ble;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public abstract class BaseDriver implements BlueToothDevice {

    protected Map<String, DeviceCommandFactory> factoriesMap = new HashMap<String, DeviceCommandFactory>();
//
//    public String getDriverName() {
//        return "AbstractBaseDriver";
//    }

    public String getDriverVersion() {
        return null;
    }

    public String getProtocolVersion() {
        return null;
    }

    public DeviceInfo getDeviceInfo() {
        return null;
    }

    public List<String> getSupportedFeatures() {
        return null;
    }

    public boolean isSupported(String feature) {
        return false;
    }

    public boolean doCommand(DeviceCommand cmd) {
        return false;
    }

    public void registerCommandFactory(DeviceCommandFactory factory) {
        factoriesMap.put(factory.getCommandName(), factory);
    }

    public DeviceCommand createCommand(String commandName, Object... params) {
        DeviceCommandFactory factory = this.factoriesMap.get(commandName);
        if (factory == null) {
            return null;
        }

        return factory.createCommand(params);
    }

}
