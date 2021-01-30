import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Translator generator.
 *
 */
    @Mojo( name = "populate")
    public class BigMojo extends AbstractMojo
    {
        public void execute() throws MojoExecutionException
        {
            System.out.println("Hello mojo");
        }
    }
