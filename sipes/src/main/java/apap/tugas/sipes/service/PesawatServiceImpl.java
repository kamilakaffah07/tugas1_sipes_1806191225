package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

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

    @Override
    public long usiaPesawat(PesawatModel pesawat){

        LocalDate now = LocalDate.now();
        LocalDate tanggal_dibuat = pesawat.getTanggalDibuat().toLocalDate();
        //SELISIH HARI
        long noOfDaysBetween = DAYS.between(tanggal_dibuat,now);
        long usiaPesawat = noOfDaysBetween / 365;

        return usiaPesawat;
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {
        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();

        if (targetPesawat.getTanggalDibuat().equals(pesawat.getTanggalDibuat()) &&
        targetPesawat.getJenisPesawat().equals(pesawat.getJenisPesawat())){
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
        } else {

            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggalDibuat(pesawat.getTanggalDibuat());
            targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
            targetPesawat.setJenisPesawat(pesawat.getJenisPesawat());

            String jenis;
            if (pesawat.getJenisPesawat().equals("Komersial")) {
                jenis = "1";
            } else {
                jenis = "2";
            }

            String tipe;
            if (targetPesawat.getTipe().getNama().equals("Boeing")){
                tipe = "BO";
            } else if (targetPesawat.getTipe().getNama().equals("ATR")){
                tipe = "AT";
            } else if (targetPesawat.getTipe().getNama().equals("Airbus")){
                tipe = "AB";
            } else {
                tipe = "BB";
            }

            LocalDate localDate = pesawat.getTanggalDibuat().toLocalDate();
            int tahun = localDate.getYear();
            String tahunString = Integer.toString(tahun);
            String reverse = new StringBuffer(tahunString).reverse().toString();

            int tahunplus = tahun + 8;

            Random r = new Random();
            char a = (char)(r.nextInt(26) + 'A');
            char b = (char)(r.nextInt(26) + 'A');
            String random = String.valueOf(a) + String.valueOf(b);

            String nomorSeri = jenis + tipe + reverse + tahunplus + random;

            targetPesawat.setNomorSeri(nomorSeri);
        }

        pesawatDb.save(targetPesawat);
        return targetPesawat;
    }
}

