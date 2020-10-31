package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;

import java.util.List;
import java.util.Random;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;


    @GetMapping("/")
    private String home(){
        return "home";
    }

    @RequestMapping("/pesawat")
    public String listPesawat(Model model){

        // Mendapatkan semua PesawatModel
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        model.addAttribute("listPesawat", listPesawat);
        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){
        model.addAttribute("pesawat", new PesawatModel());

        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String addPesawatSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){
        String jenis;
        if (pesawat.getJenisPesawat().equals("Komersial")) {
            jenis = "1";
        } else {
            jenis = "2";
        }
        TipeModel tipe_sesungguhnya = tipeService.getTipeByNama(pesawat.getTipe_temp());
        pesawat.setTipe(tipe_sesungguhnya);
        String tipe;
        if (pesawat.getTipe().getNama().equals("Boeing")){
            tipe = "BO";
        } else if (pesawat.getTipe().getNama().equals("ATR")){
            tipe = "AT";
        } else if (pesawat.getTipe().getNama().equals("Airbus")){
            tipe = "AB";
        } else {
            tipe = "BB";
        }

        String tahun_temp = pesawat.getTanggalDibuat().substring(6);
        String tahun = tahun_temp.substring(3,4) + tahun_temp.substring(2,3)
                + tahun_temp.substring(1,2) + tahun_temp.substring(0,1);
        int tahunplus = Integer.parseInt(pesawat.getTanggalDibuat().substring(6)) + 8;

        Random r = new Random();
        char a = (char)(r.nextInt(26) + 'a');
        char b = (char)(r.nextInt(26) + 'a');
        String random = String.valueOf(a) + String.valueOf(b);
        String random1 = random.toUpperCase();

        String nomorSeri = jenis + tipe + tahun + tahunplus + random1;
        pesawat.setNomorSeri(nomorSeri);

        pesawatService.addPesawat(pesawat);

        model.addAttribute("pesawat", pesawat);

        return "add-pesawat";
    }

    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawat(
            @PathVariable Long idPesawat,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("pesawat", pesawat);
        int listTeknisi = pesawat.getListPesawatTeknisi().size();
        model.addAttribute("teknisi", listTeknisi);
        return "view-pesawat";
    }

}

