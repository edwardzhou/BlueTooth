package cn.com.jkcq.ble.mocks;

import cn.com.jkcq.ble.ScanListener;

/**
 * Created by edwardzhou on 2017/7/9.
 * 设备扫描发生器
 * 用于模拟设备扫描过程中，根据不同需要发生设备
 * 如，可以模拟线程异步发现回调
 */
public interface DeviceScanEmitter {
    void emit(ScanListener listener);
}
