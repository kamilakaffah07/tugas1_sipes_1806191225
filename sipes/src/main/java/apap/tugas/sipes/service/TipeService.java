package apap.tugas.sipes.service;
import apap.tugas.sipes.model.TipeModel;
import java.util.List;

public interface TipeService {


    //Method untuk mendapatkan daftar Tipe yang telah tersimpan
    List<TipeModel> getTipeList();

    TipeModel getTipeById(Long id);

    TipeModel getTipeByNama(String nama);

}











