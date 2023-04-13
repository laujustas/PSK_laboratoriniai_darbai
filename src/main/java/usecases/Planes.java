package usecases;

import entities.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.PlanesDAO;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import services.PlaneRegistrationChecker;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class Planes implements Serializable {
    @Inject
    private PlanesDAO planesDAO;

    @Inject
    private PlaneRegistrationChecker planeRegistrationChecker;

    @Getter @Setter
    private Plane planeToCreate = new Plane();

    private List<Plane> allPlanes;

    @PostConstruct
    public void init() {
        loadPlanes();
    }

    public void loadPlanes() {
        this.allPlanes = planesDAO.loadAll();
    }

    public List<Plane> getAllPlanes() {
        return allPlanes;
    }

    @Transactional
    public String createPlane() {

        if(!planeRegistrationChecker.checkPlaneRegistration(planeToCreate.getRegistration())){
            return "plane?faces-redirect=true";
        }

        this.planesDAO.persist(planeToCreate);
        return "plane?faces-redirect=true";
    }

    @Transactional
    public String deletePlane(Plane planeToDelete) {
        this.planesDAO.delete(planeToDelete);
        return "plane?faces-redirect=true";
    }
}
