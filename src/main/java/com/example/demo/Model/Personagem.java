package com.example.demo.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String nomeAventureiro;

    @Column(nullable = false)
    private Classe classe;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int forca;

    @Column(nullable = false)
    private int defesa;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    public Personagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public Personagem somarAtributos(){
        for(ItemMagico itemMagico : this.itensMagicos){
            this.forca = itemMagico.getForca() + this.forca;
            this.defesa = itemMagico.getDefesa() + this.defesa;
        }
        return this;
    }

    public Personagem removerAtributos(){
        for(ItemMagico itemMagico : this.itensMagicos){
            this.forca = itemMagico.getForca() - this.forca;
            this.defesa = itemMagico.getDefesa() - this.defesa;
        }
        return this;
    }

    public boolean temAmuleto(){
        for (ItemMagico itemMagico : this.itensMagicos){
            if (itemMagico.getTipoItem() == TipoItem.Amuleto){
                return true;
            }
        }
        return false;
    }
}
