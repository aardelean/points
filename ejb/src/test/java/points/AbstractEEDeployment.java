package points;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import points.group.GroupServiceTest;
import points.message.MessageServiceTest;

import java.io.File;

/**
 * Created by aardelean on 03.10.2014.
 */
@RunWith(Arquillian.class)
public abstract class AbstractEEDeployment {

    private static final String earPath = "bundle/target/points";


    @Deployment(testable = true)
    public static EnterpriseArchive createEnterpriseArchive() throws Exception{
        try {
            new EmbeddedMysqlManager().init();
            File[] libraries = Maven.resolver().loadPomFromFile("ejb/pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
            return ShrinkWrap.create(EnterpriseArchive.class, "test-ear.ear")
                    .addAsModule(Testable.archiveToTest(createEJBArchive()))
                    .addAsModule(createTestWebArchive())
                    .addAsModule(createApiArchive())
                    .addAsLibraries(libraries)
                    .addAsModule(ShrinkWrap.create(ZipImporter.class, "mysql-connector-java-5.1.30.jar").importFrom(new File("test-war/target/test-war/WEB-INF/lib/mysql-connector-java-5.1.30.jar"))
                            .as(JavaArchive.class))
                    .addAsManifestResource(new File("test-war/src/main/resources/persistence.xml"))
                    .addAsManifestResource(new File(earPath + "/META-INF/MANIFEST.MF"))
                    .addAsManifestResource(new File("test-war/src/main/resources/application.xml"))
                    .addAsResource(new File("test-war/src/main/resources/log4j.properties"),
                            ArchivePaths.create("log4j.properties"));
        }catch (Exception e){
            new EmbeddedMysqlManager().destroy();
            throw e;
        }
    }

    @AfterClass
    public static void tearUp(){
        new EmbeddedMysqlManager().destroy();
    }


    private static JavaArchive createEJBArchive(){
        JavaArchive ejb = ShrinkWrap.create(ZipImporter.class, "ejb.jar").importFrom(new File("ejb/target/ejb-1.0-SNAPSHOT.jar"))
                .as(JavaArchive.class);
        ejb.addClasses(AbstractEEDeployment.class);
//        ejb.addClasses(EmbeddedMysqlManager.class);
//        ejb.addClasses(MysqldResource.class);
//        ejb.addClasses(DatabaseClient.class);
//        ejb.addClasses(PointsCacheDaoTest.class);
//        ejb.addClasses(UserDaoTest.class);
        ejb.addClasses(GroupServiceTest.class);
        ejb.addClasses(MessageServiceTest.class);
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
