package points;

import org.h2.tools.Server;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import points.user.UserDaoTest;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by aardelean on 03.10.2014.
 */
@RunWith(Arquillian.class)
public abstract class AbstractEEDeployment {

    private static Server server;

    private static final String dbConnection = "jdbc:h2:file"+System.getProperty("user.home")+"h2;MODE=MySql";

    private static final String earPath = "bundle/target/points";
    @Deployment(testable = true)
    public static EnterpriseArchive createEnterpriseArchive() {
        File[] libraries = Maven.resolver().loadPomFromFile("ejb/pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        return  ShrinkWrap.create(EnterpriseArchive.class, "test-ear.ear")
                .addAsModule(Testable.archiveToTest(createEJBArchive()))
                .addAsModule(createTestWebArchive())
                .addAsModule(createApiArchive())
                .addAsLibraries(libraries)
                .addAsLibraries(libraries)
                .addAsModule(ShrinkWrap.create(ZipImporter.class, "h2-1.3.176.jar").importFrom(new File("test-war/target/test-war/WEB-INF/lib/h2-1.3.176.jar"))
                        .as(JavaArchive.class))
                .addAsManifestResource(new File("test-war/src/main/resources/persistence.xml"))
                .addAsManifestResource(new File(earPath + "/META-INF/MANIFEST.MF"))
                .addAsManifestResource(new File("test-war/src/main/resources/application.xml"));

    }

    @BeforeClass
    public static void setUpDB(){
        try {
            server = Server.createTcpServer(dbConnection).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearUp(){
        server.stop();
    }


    private static JavaArchive createEJBArchive(){
        JavaArchive ejb = ShrinkWrap.create(ZipImporter.class, "ejb.jar").importFrom(new File("ejb/target/ejb-1.0-SNAPSHOT.jar"))
                .as(JavaArchive.class);
        ejb.addClasses(AbstractEEDeployment.class);
        ejb.addClasses(PointsCacheDaoTest.class);
        ejb.addClasses(UserDaoTest.class);
        ejb.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return ejb;
    }
    public static JavaArchive createApiArchive() {
        JavaArchive api =  ShrinkWrap.create(ZipImporter.class, "api.jar").importFrom(new File("api/target/api-1.0-SNAPSHOT.jar"))
                .as(JavaArchive.class);
        return api;

    }


    public static WebArchive createTestWebArchive() {
        WebArchive web =  ShrinkWrap.create(ZipImporter.class, "test-war.war").importFrom(new File("test-war/target/test-war.war"))
                .as(WebArchive.class);
        return web;

    }

}
