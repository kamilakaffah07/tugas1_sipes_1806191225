package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import apap.tugas.sipes.repository.PenerbanganDb;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {

    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getPenerbanganList() { return penerbanganDb.findAll(); }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){ penerbanganDb.save(penerbangan);}

    @Override
    public PenerbanganModel getPenerbanganById(Long id){ return penerbanganDb.findById(id).get(); }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {

        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getId()).get();

        targetPenerbangan.setNomorPenerbangan(penerbangan.getNomorPenerbangan());
        targetPenerbangan.setKodeBandaraAsal(penerbangan.getKodeBandaraAsal());
        targetPenerbangan.setKodeBandaraTujuan(penerbangan.getKodeBandaraTujuan());
        targetPenerbangan.setWaktuBerangkat(penerbangan.getWaktuBerangkat());

        penerbanganDb.save(targetPenerbangan);

        return targetPenerbangan;

    }

    @Override
    public void deletePenerbangan(Long idPenerbangan) { penerbanganDb.deleteById(idPenerbangan); }
}

