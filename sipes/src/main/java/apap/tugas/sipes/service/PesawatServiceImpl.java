package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService {

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getPesawatList() { return pesawatDb.findAll(); }

    @Override
    public void addPesawat(PesawatModel pesawat){ pesawatDb.save(pesawat);}

    @Override
    public PesawatModel getPesawatById(Long id){ return pesawatDb.findById(id).get(); }

}

