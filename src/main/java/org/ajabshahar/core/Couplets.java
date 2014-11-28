package org.ajabshahar.core;

import org.ajabshahar.platform.daos.CoupletDAO;

import java.util.List;

public class Couplets {

    private CoupletDAO coupletRepository;

    public Couplets(CoupletDAO coupletRepository) {
        this.coupletRepository = coupletRepository;
    }

    public List<org.ajabshahar.platform.models.Couplet> findBy(int id) {
        return coupletRepository.findBy(id);
    }
}
