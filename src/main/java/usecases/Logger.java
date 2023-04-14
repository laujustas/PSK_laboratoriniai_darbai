package usecases;

import entities.Log;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import persistence.LoggerDAO;

import java.util.Date;

@Model
public class Logger {
    @Inject
    private LoggerDAO loggerDAO;

    @Transactional
    public void addLog(String methodName, String className){
        Log log = new Log();
        log.setMethodName(methodName);
        log.setClassName(className);
        log.setCreationDate(new Date());
        loggerDAO.persist(log);
    }
}
