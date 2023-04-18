package usecases;

import interceptors.MyInterceptor;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.AsyncSeatGenerator;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateSeat implements Serializable {
    @Inject
    private AsyncSeatGenerator asyncSeatGenerator;

    private Future<Integer> seatGenerationTask = null;

    @MyInterceptor
    public String generateSeatNumber(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        seatGenerationTask = asyncSeatGenerator.generateSeatNumber();
        return "ticketDetails?faces-redirect=true&ticketId=" + requestParameters.get("ticketId");
    }

    public Boolean isGeneratorRunning(){
        return seatGenerationTask != null && !seatGenerationTask.isDone();
    }

    public String getGeneratorStatus() throws ExecutionException, InterruptedException {
        if(seatGenerationTask == null){
            return "Generator inactive";
        }else if(isGeneratorRunning()){
            return "Generating...";
        }
        return "Generated seat number: " + seatGenerationTask.get();
    }
}
