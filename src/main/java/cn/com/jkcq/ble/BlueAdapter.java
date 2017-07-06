package cn.com.jkcq.ble;

/**
 * Created by edwardzhou on 2017/7/3.
 */
public class BlueAdapter {
    private boolean enabled = true;

    public boolean getEnabled() {
        return enabled;
    }

    public BlueAdapter setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
