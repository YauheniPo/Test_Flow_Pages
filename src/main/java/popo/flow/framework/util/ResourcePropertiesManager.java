package popo.flow.framework.util;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.Objects;
import java.util.Properties;

@Log4j2
public final class ResourcePropertiesManager {

    private final Properties properties = new Properties();
    public static final String JAR_FILE_DIR;

    static {
        CodeSource codeSource = ResourcePropertiesManager.class.getProtectionDomain().getCodeSource();
        File jarFile = null;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JAR_FILE_DIR = Objects.requireNonNull(jarFile).getParentFile().getPath();
    }

    public ResourcePropertiesManager(@NonNull final String resourceName) {
        appendFromResource(resourceName);
        appendFromOverrideResource(resourceName);
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
        @Cleanup InputStream inStreamBase = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (inStreamBase != null) {
            this.properties.load(inStreamBase);
        } else {
            log.debug(String.format("Base resource \"%1$s\" could not be found", resourceName));
        }
    }

    private void appendFromOverrideResource(final String resourceName) {
        try {
            @Cleanup InputStream inStreamOverride = Files.newInputStream(Paths.get(JAR_FILE_DIR, resourceName));
            this.properties.load(inStreamOverride);
        } catch (Exception ex) {
            log.debug(String.format("Override resource \"%1$s\" could not be found", resourceName));
        }
    }
}