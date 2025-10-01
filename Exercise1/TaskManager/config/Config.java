package config;

import java.util.concurrent.atomic.AtomicBoolean;

public final class Config {
    public static final AtomicBoolean RUNNING = new AtomicBoolean(true);

    public static final int INPUT_RETRY = 3;

    private Config() {}
}
