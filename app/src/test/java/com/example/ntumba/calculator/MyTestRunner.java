package com.example.ntumba.calculator;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

/**
 * Created by ntumba on 17-10-14.
 */



public class MyTestRunner extends RobolectricTestRunner {
    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public MyTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }



    @Override
    protected AndroidManifest getAppManifest(Config config){
        return new AndroidManifest(
                Fs.fileFromPath("/home/ntumba/AndroidStudioProjects/Calculator/app/src/main/AndroidManifest.xml"),
                Fs.fileFromPath("/home/ntumba/AndroidStudioProjects/Calculator/app/src/main/res"),
                Fs.fileFromPath("/home/ntumba/AndroidStudioProjects/Calculator/app/src/main/assets")
        );
    }
}
