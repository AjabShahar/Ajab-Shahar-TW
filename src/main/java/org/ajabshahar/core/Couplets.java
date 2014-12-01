package org.ajabshahar.core;

import com.google.gson.Gson;
import org.ajabshahar.platform.daos.CoupletDAO;
import org.ajabshahar.platform.models.Couplet;

import java.util.List;

public class Couplets {

    private CoupletDAO coupletRepository;

    public Couplets(CoupletDAO coupletRepository) {
        this.coupletRepository = coupletRepository;
    }

    public List<org.ajabshahar.platform.models.Couplet> findBy(int id) {
        return coupletRepository.findBy(id);
    }

    public Couplet updateCouplet(String jsonCouplet) {

        Couplet couplet = new Gson().fromJson(jsonCouplet, Couplet.class);
        return coupletRepository.updateCouplet(couplet);
    }
}
