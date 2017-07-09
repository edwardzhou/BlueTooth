package cn.com.jkcq.ble.driver.simulate;

import cn.com.jkcq.ble.DeviceCommand;
import cn.com.jkcq.ble.DeviceDriver;
import cn.com.jkcq.ble.DeviceInfo;
import cn.com.jkcq.ble.drivers.simulate.GetStepCommandFactory;
import cn.com.jkcq.ble.drivers.simulate.SimulateDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static cn.com.jkcq.ble.Constants.BLE_R_OK;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.CHARACTERISTIC_UUID_1;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.COMMAND_GET_STEP;
import static cn.com.jkcq.ble.drivers.simulate.SimulateConstants.SERVICE_UUID_1;
import static org.testng.Assert.*;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class SimulateDriverTest {

    private DeviceDriver driver = null;

    @BeforeMethod
    public void setUp() {
        driver = new SimulateDriver();
        driver.registerCommandFactory(GetStepCommandFactory.getInstance());
    }

    @Test
    public void testBindDevice() {
        DeviceInfo deviceInfo = new DeviceInfo("SimulateDevice");
        assertEquals(driver.bindDevice(deviceInfo), BLE_R_OK);
    }

    @Test
    public void testCreateGetStepCommand() {
        DeviceCommand command = driver.createCommand(COMMAND_GET_STEP);
        assertNotNull(command);
        assertEquals(command.getCommandName(), COMMAND_GET_STEP);
        assertEquals(command.getCharacteristicUUID(), UUID.fromString(CHARACTERISTIC_UUID_1));
        assertEquals(command.getServiceUUID(), UUID.fromString(SERVICE_UUID_1));
        byte[] data = command.getData();
        assertEquals(data.length, 4);
        assertEquals(data, new byte[]{0x10, 0x20, 0x30, 0x40});
    }

    @Test
    public void testCreateUnknownCommand() {
        DeviceCommand command = driver.createCommand("NoSuchCommand");
        assertNull(command);
    }
}
