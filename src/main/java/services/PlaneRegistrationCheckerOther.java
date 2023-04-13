package services;

import jakarta.enterprise.inject.Alternative;

import java.io.Serializable;

@Alternative
public class PlaneRegistrationCheckerOther implements PlaneRegistrationChecker, Serializable {
    @Override
    public Boolean checkPlaneRegistration(String planeReg) {
        //checks if other region registration OK
        return false;
    }
}
