import org.apache.commons.io.FileUtils;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Translator generator.
 *
 */
    @Mojo(name = "populate", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
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
        boolean created = keyPath.toFile().mkdir();
        LOGGER.debug("Temp folder created?  {}" , created);

        //Copy resources Files
        try {
            FileUtils.copyDirectory(resourcesPath.toFile(), keyPath.toFile());
        } catch (IOException e) {
        LOGGER.debug("Resources Files not copied");
        }

        //TODO File manipulation as Stream to generate a key file

    }
}
