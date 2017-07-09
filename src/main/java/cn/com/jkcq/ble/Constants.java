package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/5.
 */
public interface Constants {

    /**
     * 正常 / 成功
     */
    int BLE_R_OK = 0;

    /**
     * 失败 / 错误
     */
    int BLE_R_ERROR = 1;


    /**
     * 蓝牙模块已关闭
     */
    int BLE_R_BLUETOOTH_DISABLED = 100;

    /**
     * 驱动不支持此蓝牙设备
     */
    int BLE_R_DEVICE_UNSUPPORTED = 101;

}
