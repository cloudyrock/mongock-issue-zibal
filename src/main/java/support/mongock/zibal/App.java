package support.mongock.zibal;


import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.SpringDataMongo3Driver;
import com.github.cloudyrock.spring.v5.MongockSpring5;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import support.mongock.zibal.changeLogs.DataBaseMongockChangeLogs;


@SpringBootApplication
public class App {

    public final static String DB_NAME = "amit_db";

    public static void main(String[] args) {
        getSpringAppBuilder().run(args);
    }


    public static SpringApplicationBuilder getSpringAppBuilder() {
        return new SpringApplicationBuilder().sources(App.class);
    }

    @Bean
    public MongockSpring5.MongockInitializingBeanRunner mongockInitializingBeanRunner(ApplicationContext springContext,
                                                                                      MongoClient mongoClient) {

        SpringDataMongo3Driver driver = null;
        try {

            MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, DB_NAME);

            MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
            driver = SpringDataMongo3Driver.withDefaultLock(mongoTemplate);
            driver.setIndexCreation(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

//debugger reaching here means its executed without error.
        return MongockSpring5.builder().setDriver(driver)
//                .addChangeLogsScanPackage("support.mongock.changeLogs")
//                .setEnabled(true)
                .addChangeLogClass(DataBaseMongockChangeLogs.class)
                .setSpringContext(springContext)
                .buildInitializingBeanRunner();
    }
}
