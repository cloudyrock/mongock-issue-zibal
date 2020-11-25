package support.mongock.zibal.changeLogs;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChangeLog
public class DataBaseMongockChangeLogs
{
    //debugger reaching here
    private final Logger log = LoggerFactory.getLogger(DataBaseMongockChangeLogs.class);


    // code to fix user schema
    @ChangeSet(order = "002", id = "user002", author = "santosh@zibal.com")
//debugger reaching here but updateUserSchema(DB db) method not executed.
    public void updateUserSchema(MongockTemplate db) {
//code to execute but debugger not reaching here.
    }
}
