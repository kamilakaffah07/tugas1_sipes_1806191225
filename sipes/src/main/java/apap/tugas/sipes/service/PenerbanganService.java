package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import java.util.List;

public interface PenerbanganService {

    //Method untuk mendapatkan daftar Pesawat yang telah tersimpan
    List<PenerbanganModel> getPenerbanganList();

    void addPenerbangan(PenerbanganModel penerbangan);

    PenerbanganModel getPenerbanganById(Long id);

    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    public void deletePenerbangan(Long idPenerbangan);

}




