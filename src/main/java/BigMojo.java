import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Translator generator.
 *
 */
    @Mojo(name = "populate", defaultPhase = LifecyclePhase.COMPILE)
    public class BigMojo extends AbstractMojo{
    final static Logger LOGGER = LoggerFactory.getLogger(BigMojo.class);


    public void execute() throws MojoExecutionException
        {
           if(LOGGER.isDebugEnabled()){
               LOGGER.debug(("Hello mojo"));
           }

           LOGGER.warn("Plug in is here");


        }
    }
