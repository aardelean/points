package points.test.war;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Arquillian.class)
public class WebDeploymentTest {
    @Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(ZipImporter.class, "test.war").importFrom(new File("test-war/target/test-war.war"))
                .as(WebArchive.class);

    }


    @Test
    public void testInsertUser() {
    }
}