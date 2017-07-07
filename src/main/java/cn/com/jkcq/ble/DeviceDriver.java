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

    /**
     * 根据 commandName (指令名称) 相应的指令工厂创建指令
     * @param commandName - 指令名称
     * @param params - 可变参数
     * @return @DeviceCommand
     */
    public DeviceCommand createCommand(String commandName, Object... params);

    /**
     * 登记驱动程序所支持的指令的工厂
     * 用于createCommand时，创建相应的指令
     * @param factory - 指令的工厂实例
     */
    public void registerCommandFactory(DeviceCommandFactory factory);
}
