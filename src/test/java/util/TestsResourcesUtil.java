package util;

import java.io.File;
import java.util.Objects;

public class TestsResourcesUtil {

    public static File getResource(String file) {
        return new File(Objects.requireNonNull(
                TestsResourcesUtil.class.getClassLoader()
                        .getResource(file))
                        .getFile()
        );
    }

    public static String getResourcePath(String file) {
        return getResource(file).getAbsolutePath();
    }
}
