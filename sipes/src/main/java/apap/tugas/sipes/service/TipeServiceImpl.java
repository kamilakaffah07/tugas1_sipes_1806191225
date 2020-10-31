package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {

    @Autowired
    apap.tugas.sipes.repository.TipeDb TipeDb;

    @Override
    public List<TipeModel> getTipeList() { return TipeDb.findAll(); }

    @Override
    public TipeModel getTipeById(Long id) { return TipeDb.findById(id).get(); }

    @Override
    public TipeModel getTipeByNama(String nama) { return TipeDb.findByNama(nama).get(); }

}

