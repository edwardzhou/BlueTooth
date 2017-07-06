package cn.com.jkcq.ble;

import java.util.UUID;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public interface DeviceCommand {
    /**
     * 获取命令名称
     * @return String
     */
    String getCommandName();

    /**
     * 获取命令对应的特征通道
     * @return
     */
    UUID getUUID();

    /**
     * 指令字节数据
     * @return
     */
    byte[] getData();
}
