package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class MockScannerFactory implements ScannerFactory {

    public DeviceScanner getScanner() {
        DeviceScanner scanner = new DeviceScanner();
        DeviceAdapter adapter = new MockDeviceAdapter();
        scanner.setAdapter(adapter);
        return scanner;
    }
}
