package cn.com.jkcq.ble;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;

//import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.*;

/**
 * Created by edwardzhou on 2017/7/5.
 */

@RunWith(Spectrum.class)
public class ManagerTest {
    {
        describe("Blue Tooth Manager", () -> {
            it("should be failed", ()-> {
                assert 1 == 2;
            });
        });
    }
}
