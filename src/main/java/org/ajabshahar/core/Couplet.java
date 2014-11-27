package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CoupletDAO;

import java.util.List;

public class Couplet {

    private CoupletDAO coupletRepository;

    public Couplet(CoupletDAO coupletRepository) {
        this.coupletRepository = coupletRepository;
    }

    public List<org.ajabshahar.platform.models.Couplet> findBy(int id) {
        return coupletRepository.findBy(id);
    }
}
