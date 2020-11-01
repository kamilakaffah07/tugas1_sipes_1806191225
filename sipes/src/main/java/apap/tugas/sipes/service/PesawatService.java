package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import java.util.List;

public interface PesawatService {

    //Method untuk mendapatkan daftar Pesawat yang telah tersimpan
    List<PesawatModel> getPesawatList();

    void addPesawat(PesawatModel pesawat);

    PesawatModel getPesawatById(Long id);

    PesawatModel updatePesawat(PesawatModel pesawat);

    long usiaPesawat(PesawatModel pesawat);

}




