package cn.com.jkcq.ble.driver.simulate;

import cn.com.jkcq.ble.DeviceCommand;
import cn.com.jkcq.ble.drivers.simulate.GetStepCommandFactory;
import cn.com.jkcq.ble.drivers.simulate.SimulateDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.*;

/**
 * Created by edwardzhou on 2017/7/6.
 */
public class SimulateDriverTest {

    private SimulateDriver driver = null;

    @BeforeMethod
    public void setUp() {
        driver = new SimulateDriver();
        driver.registerCommandFactory(GetStepCommandFactory.getInstance());
    }

    @Test
    public void testCreateGetStepCommand() {
        DeviceCommand command = driver.createCommand("GET_STEP");
        assertNotNull(command);
        assertEquals(command.getCommandName(), "GET_STEP");
        assertEquals(command.getUUID(), UUID.fromString("00000000-0000-0000-8000-000000000001"));
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
