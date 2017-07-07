package cn.com.jkcq.ble.drivers.simulate;

import cn.com.jkcq.ble.BaseDeviceCommand;
import cn.com.jkcq.ble.DeviceCommand;
import cn.com.jkcq.ble.DeviceCommandFactory;

import java.util.UUID;

import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.*;

/**
 * Created by edwardzhou on 2017/7/7.
 */
public class RealTimeDataCommandFactory implements DeviceCommandFactory {
    private static final String COMMAND_NAME = COMMAND_REAL_TIME_DATA;
    private static final UUID CHARACTERISTIC_UUID = UUID.fromString(CHARACTERISTIC_UUID_1);
    private static final UUID SERVICE_UUID = UUID.fromString(SERVICE_UUID_1);

    private static DeviceCommandFactory instance = null;

    /**
     * 创建获取步数指令
     *
     * example:
     * DeviceCommand command = GetStepCommandFactory.getInstance().createCommand();
     *
     * @param params - 可变参数, 本指令无需传递任何参数
     * @return @DeviceCommand
     */
    public DeviceCommand createCommand(Object... params) {
        BaseDeviceCommand cmd = new BaseDeviceCommand();
        cmd.setCommandName(COMMAND_NAME);
        cmd.setCharacteristicUUID(CHARACTERISTIC_UUID);
        cmd.setServiceUUID(SERVICE_UUID);
        cmd.setData(new byte[] {(byte)0xBE, 0x02, 0x03, (byte)0xED}); //BE+02+03+ED

        return cmd;
    }

    /**
     * 指令名称
     * @return
     */
    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    /**
     * 对应特征/通道UUID
     * @return
     */
    @Override
    public UUID getCharacteristicUUID() {
        return CHARACTERISTIC_UUID;
    }

    /**
     * 对应服务UUID
     * @return
     */
    @Override
    public UUID getServiceUUID() {
        return SERVICE_UUID;
    }

    /**
     * 获取单例
     * @return
     */
    public static DeviceCommandFactory getInstance() {
        if (instance == null) {
            instance = new RealTimeDataCommandFactory();
        }
        return instance;
    }
}
