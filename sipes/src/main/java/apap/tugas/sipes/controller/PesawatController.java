package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;


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

}

