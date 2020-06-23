package pers.kivi.javafragment.agent;

import org.apache.commons.io.FileUtils;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.ProtectionDomain;

/**
 * @author wangqiwei
 * @date 2020/05/20 1:00 PM
 */
public class ClassLogger implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            Path path = Paths.get("classes/" + className + ".class");
            FileUtils.writeByteArrayToFile(path.toFile(), classfileBuffer);
        } catch (Throwable ignored) { // ignored, don’t do this at home kids
            ignored.printStackTrace();
        } finally {
            return classfileBuffer;
        }
    }
}
