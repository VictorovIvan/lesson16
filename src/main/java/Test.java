import com.sun.jmx.snmp.ThreadContext;
import jdbc.*;
import object.Role;
import object.User;
import object.UserRole;
import org.apache.log4j.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.time.Month;
import java.util.Properties;
import java.util.UUID;

/*
 * Взять за основу ДЗ_13,
 * покрыть код логированием
 * в основных блоках try покрыть уровнем INFO
 * с исключениях catch покрыть уровнем ERROR
 * настроить конфигурацию логера, что бы все логи записывались в БД, таблица LOGS,
 * колонки ID, DATE, LOG_LEVEL, MESSAGE, EXCEPTION
 */

public class Test {
    static final Logger logger = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) throws IOException {
        LocalDate localDate = LocalDate.of(1950, Month.DECEMBER, 30);
        Role someRole = new Role(1, Role.enumName.Administration, "Some description about Administration");
        User someUser = new User(1, "ABC", localDate, 1, "London", "abra@cadab.ra", "Somebody user");
        UserRole someUserRole = new UserRole(1, 0, 0);
        Connection connectDataBase = ConnectDataBase.connectionDataBase(null);
        LoggerSet regsistratorForWriteToDatabase = new LoggerSet();
        try {
            connectDataBase.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RoleJDBC roleJDBC = new RoleJDBC(connectDataBase);
        UserJDBC userJDBC = new UserJDBC(connectDataBase);
        UserRoleJDBC userRoleJDBC = new UserRoleJDBC(connectDataBase);
        Properties propertiesLog4j = new Properties();
        propertiesLog4j.load(new FileInputStream("log4j.properties"));
        PropertyConfigurator.configure(propertiesLog4j);
        PropertyConfigurator.configure("log4j.properties");

        regsistratorForWriteToDatabase.setDebug(logger, "", "Start program");
        // Task02.a
        regsistratorForWriteToDatabase.setDebug(logger, "", "Task02.a");
        try {
            regsistratorForWriteToDatabase.setInfo(logger, "", "Check for Task02.a");
            roleJDBC.addRoleParametric(someRole);
            connectDataBase.commit();
            userJDBC.addUserParametric(someUser);
            connectDataBase.commit();
            userRoleJDBC.addRoleParametric(someUserRole);
            connectDataBase.commit();
        } catch (SQLException e) {
            regsistratorForWriteToDatabase.setError(logger, "SQLException", "exception:SQLException");
        }

        // Task02.b
        regsistratorForWriteToDatabase.setDebug(logger, "", "Task02.b");
        try {
            regsistratorForWriteToDatabase.setInfo(logger, "", "Check for Task02.b");
            roleJDBC.addRoleBatch(someRole);
            connectDataBase.commit();
            userJDBC.addUserBatch(someUser);
            connectDataBase.commit();
            userRoleJDBC.addUserRoleBatch(someUserRole);
            connectDataBase.commit();
        } catch (SQLException e) {
            regsistratorForWriteToDatabase.setError(logger, "SQLException", "exception:SQLException");
        }

        // Task03
        regsistratorForWriteToDatabase.setDebug(logger, "", "Task03");
        try {
            regsistratorForWriteToDatabase.setInfo(logger, "", "Check for Task03");
            userJDBC.getUserByLoginIdAndName(1, "ABC");
        } catch (SQLException e) {
            regsistratorForWriteToDatabase.setError(logger, "SQLException", "exception:SQLException");
        }


        // Task04.a
//        regsistratorForWriteToDatabase.setDebug(logger,"","Task04.a");
//        try {
//            regsistratorForWriteToDatabase.setInfo(logger,"","Check for Task04.a");
//            roleJDBC.addRoleParametric(someRole);
//            connectDataBase.commit();
//            Savepoint savepoint01 = connectDataBase.setSavepoint();
//            userJDBC.addUserParametric(someUser);
//            connectDataBase.commit();
//            Savepoint savepoint02 = connectDataBase.setSavepoint();
//            userRoleJDBC.addRoleParametric(someUserRole);
//            connectDataBase.commit();
//            Savepoint savepoint03 = connectDataBase.setSavepoint();
//        } catch (SQLException e) {
//            regsistratorForWriteToDatabase.setError(logger,"SQLException","exception:SQLException");
//        }

        // Task04.b
//        regsistratorForWriteToDatabase.setDebug(logger,"","Task04.b");
//        try {
//            regsistratorForWriteToDatabase.setInfo(logger,"","Check for Task04.b");
//            roleJDBC.addRoleParametric(someRole);
//            connectDataBase.commit();
//            Savepoint savepointA01 = connectDataBase.setSavepoint();
//            userJDBC.addUserParametric(someUser);
//            connectDataBase.commit();
//
//        } catch (SQLException e) {
//            regsistratorForWriteToDatabase.setError(logger,"SQLException","exception:SQLException");
//        }
//
//        try{
//        regsistratorForWriteToDatabase.setInfo(logger,"","Check for Task04.b.2");
//        userRoleJDBC.addRoleParametric(null);}
//        catch(NullPointerException nullPointer){
//        regsistratorForWriteToDatabase.setError(logger,"NullPointerException","exception:NullPointerException");
//            try {
//                regsistratorForWriteToDatabase.setInfo(logger,"","Check for setSavepointA02");
//                Savepoint savepointA02 = connectDataBase.setSavepoint();
//                connectDataBase.rollback(savepointA02);
//            } catch (SQLException e) {
//                MDC.put("id",UUID.randomUUID().toString());
//                MDC.put("exception","SQLException");
//                regsistratorForWriteToDatabase.setError(logger,"SQLException","exception:SQLException");
//            }
//            System.out.println("Return to savepointA02");
//            }
    }
}
