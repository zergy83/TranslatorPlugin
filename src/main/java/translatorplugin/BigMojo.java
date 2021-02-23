package translatorplugin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Translator generator.
 *
 */
    @Mojo(name = "populate", defaultPhase = LifecyclePhase.COMPILE)
    public class BigMojo extends AbstractMojo {

    @Parameter(required = true)
    String baseDir;

    @Parameter(required = true)
    String targetDir;


    final static Logger LOGGER = LoggerFactory.getLogger(BigMojo.class);


    public void execute() throws MojoExecutionException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(("Hello mojo"));
        }

        LOGGER.debug("Plug in is here");
        LOGGER.debug("Project build directory is {}", baseDir);

        // get resourcesPath folder
        Path resourcesPath = Paths.get(baseDir).resolve("resources");

        if (resourcesPath.toFile().exists()){
            LOGGER.debug("Resources Exists");
        } else {
            LOGGER.debug("no resources");
        }

        //generate a processedResources Folder
        Path keyPath = Paths.get(targetDir).resolve("resourcesPath");
        keyPath.toFile().mkdir();

        //Copy resources Files
        try {
            FileUtils.copyDirectory(resourcesPath.toFile(), keyPath.toFile());
            LOGGER.debug("Resources Files copied");
            Serializor serializor = new Serializor();
            serializor.processFile(keyPath);

        } catch (IOException e) {
        LOGGER.debug("Resources Files not copied");
        }
    }
}
