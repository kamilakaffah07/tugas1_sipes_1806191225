package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "pesawat")
public class PesawatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_seri", nullable = false, unique = true)
    private String nomorSeri;

    @Column(name = "usia")
    private long usia;

    public long getUsia() {
        return usia;
    }

    public void setUsia(long usia) {
        this.usia = usia;
    }

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_dibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Date tanggalDibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_pesawat", nullable = false)
    private String jenisPesawat;

    @NotNull
    @Size(max = 255)
    @Column(name = "tipe_temp", nullable = false)
    private String tipe_temp;


    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teknisiId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<TeknisiModel> listTeknisi;

    public List<TeknisiModel> getListTeknisi() {
        return listTeknisi;
    }

    public void setListTeknisi(List<TeknisiModel> listTeknisi) {
        this.listTeknisi = listTeknisi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipe_temp() {
        return tipe_temp;
    }

    public void setTipe_temp(String tipe_temp) {
        this.tipe_temp = tipe_temp;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Date tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getJenisPesawat() {
        return jenisPesawat;
    }

    public void setJenisPesawat(String jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }
}
