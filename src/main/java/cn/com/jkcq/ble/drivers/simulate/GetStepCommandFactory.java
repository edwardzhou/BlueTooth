package cn.com.jkcq.ble.drivers.simulate;

import cn.com.jkcq.ble.BaseDeviceCommand;
import cn.com.jkcq.ble.DeviceCommand;
import cn.com.jkcq.ble.DeviceCommandFactory;

import java.util.UUID;

import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.CHARACTERISTIC_UUID_1;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_GET_STEP;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.SERVICE_UUID_1;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class GetStepCommandFactory implements DeviceCommandFactory {
    private static final String COMMAND_NAME = COMMAND_GET_STEP;
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
        cmd.setData(new byte[] {0x10, 0x20, 0x30, 0x40});

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
            instance = new GetStepCommandFactory();
        }
        return instance;
    }
}
