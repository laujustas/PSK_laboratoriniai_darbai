package services;

import jakarta.enterprise.inject.Alternative;

import java.io.Serializable;

@Alternative
public class PlaneRegistrationCheckerEU implements PlaneRegistrationChecker, Serializable {
    @Override
    public Boolean checkPlaneRegistration(String planeReg) {
        //checks if EU registration OK
        return true;
    }
}
