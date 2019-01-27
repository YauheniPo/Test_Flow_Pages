package popo.flow.framework.util;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public final class ResourcePropertiesManager {

    private final Properties properties = new Properties();

    public ResourcePropertiesManager(@NonNull final String resourceName) {
        appendFromResource(resourceName);
    }

    public String getProperty(@NonNull final String key) {
        return this.properties.getProperty(key).trim();
    }

    public String getProperty(@NonNull final String key, @NonNull final String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public void setProperty(final String key, final String value) {
        this.properties.setProperty(key, value);
    }

    @SneakyThrows(IOException.class)
    private void appendFromResource(final String resourceName) {
        @Cleanup InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (inStream != null) {
            this.properties.load(inStream);
        } else {
            log.error(String.format("Resource \"%1$s\" could not be found", resourceName));
        }
    }
}