package services;

import interceptors.MyInterceptor;
import jakarta.enterprise.inject.Specializes;

import java.io.Serializable;

@Specializes
@MyInterceptor
public class FastPlaneRegistrationCheckerEU extends PlaneRegistrationCheckerEU implements Serializable {
    @Override
    public Boolean checkPlaneRegistration(String planeReg) {
        if(super.checkPlaneRegistration(planeReg)){
            //faster checking
            return true;
//            return false;
        }
        return false;
    }
}
