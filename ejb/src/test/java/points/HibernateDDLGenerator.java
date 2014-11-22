package points;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import points.group.dto.Group;
import points.group.dto.UserStatus;
import points.message.dto.GroupMessage;
import points.message.dto.Message;
import points.message.dto.UserMessage;
import points.point.dto.Point;
import points.strategy.dto.LocationBasedStrategy;
import points.strategy.dto.Strategy;
import points.strategy.dto.TimeBasedStrategy;
import points.user.dto.Friend;
import points.user.dto.User;

public class HibernateDDLGenerator {
    public static void main(String[] args) { new HibernateDDLGenerator().execute();
    }
    private void execute() {
        Configuration configuration = new Configuration();
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(UserStatus.class);
        configuration.addAnnotatedClass(GroupMessage.class);
        configuration.addAnnotatedClass(Message.class);
        configuration.addAnnotatedClass(UserMessage.class);
        configuration.addAnnotatedClass(Point.class);
        configuration.addAnnotatedClass(LocationBasedStrategy.class);
        configuration.addAnnotatedClass(Strategy.class);
        configuration.addAnnotatedClass(TimeBasedStrategy.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Friend.class);



        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[] {"ddl", "mysql", "sql" }));
        boolean consolePrint = true;
        boolean exportInDatabase = false;
        schemaExport.create(consolePrint, exportInDatabase);
    }
}
