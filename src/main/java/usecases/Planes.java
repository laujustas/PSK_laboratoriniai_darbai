package usecases;

import entities.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.PlanesDAO;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.util.List;

@Model
public class Planes {
    @Inject
    private PlanesDAO planesDAO;

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
        this.planesDAO.persist(planeToCreate);
        return "plane?faces-redirect=true";
    }

    @Transactional
    public String deletePlane(Plane planeToDelete) {
        this.planesDAO.delete(planeToDelete);
        return "plane?faces-redirect=true";
    }
}
