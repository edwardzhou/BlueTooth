package cn.com.jkcq.ble;


/**
 * Created by edwardzhou on 2017/7/3.
 */
public interface DeviceDriver {
    public String getDriverName();
    public String getDriverVersion();
    public String getProtocolVersion();
    public DeviceInfo getDeviceInfo();
//    public java.util.List<String> characterID;

    public java.util.List<String> getSupportedFeatures();

    public boolean isSupported(String feature);

    public boolean doCommand(DeviceCommand cmd);

    public void registerCommandFactory(DeviceCommandFactory factory);
}
