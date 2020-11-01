package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PenerbanganModel;

import apap.tugas.sipes.service.PenerbanganService;
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
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @RequestMapping("/penerbangan")
    public String listPenerbangan(Model model) {

        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "viewall-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());

        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){
        try{
            penerbanganService.addPenerbangan(penerbangan);
            model.addAttribute("penerbangan", penerbangan);
            return "add-penerbangan";
        } catch (Exception e){
            return "page-gagal";
        }

    }

    @GetMapping("/penerbangan/{idPenerbangan}")
    public String viewDetailPenerbangan(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        int flag;
        if (penerbangan.getPesawat() == null){
            flag = 0;
        } else {
            flag = 1; //punya pesawat
        }
        model.addAttribute("flag",flag);
        return "view-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String changePenerbanganFormPage(
            @PathVariable Long idPenerbangan,
            Model model
    ){

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganFormSubmit(

            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbanganUpdated);
        return "update-penerbangan";

    }

    @GetMapping("penerbangan/hapus/{idPenerbangan}")
    public String deletePenerbangan(
            @PathVariable Long idPenerbangan,
            Model model
    ){

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        String kodePenerbangan = penerbangan.getNomorPenerbangan();
        model.addAttribute("kodePenerbangan",kodePenerbangan);

        try{
            penerbanganService.deletePenerbangan(idPenerbangan);
            int flag = 0;
            model.addAttribute("flag", flag);
            return "delete-penerbangan";
        } catch (Exception e){
            int flag = 1;
            model.addAttribute("flag", flag);
            return "delete-penerbangan";
        }
    }




}