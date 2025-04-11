package com.example.demo.Service;

import com.example.demo.Model.ItemMagico;
import com.example.demo.Model.Personagem;
import com.example.demo.Model.TipoItem;
import com.example.demo.Repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    ItemMagicoRepository itemMagicoRepository;

    public List<ItemMagico> listarItensMagicos(){
        return itemMagicoRepository.findAll();
    }

    public ItemMagico listarItemMagicoPorId(Long id){
        return itemMagicoRepository.findById(id).orElse(null);
    }

    public ItemMagico criarItemMagico(ItemMagico itemMagico){

        if ((itemMagico.getDefesa() > 10) || (itemMagico.getForca() > 10)){
            throw new  IllegalArgumentException("Os atributos Força e Defesa, podem ser no máximo 10.");
        } else if ((itemMagico.getDefesa() <= 0) && (itemMagico.getForca() < 0)) {
            throw new IllegalArgumentException("Não podem existir Itens com zero de Defesa e zero de Força.");
        }

        if (itemMagico.getTipoItem() == TipoItem.Armadura && (itemMagico.getForca() != 0)){
            throw new IllegalArgumentException("Quando um Item for do Tipo Armadura, a Força dele será OBRIGATORIAMENTE zero.");
        }

        if (itemMagico.getTipoItem() == TipoItem.Arma && (itemMagico.getDefesa() != 0)){
            throw new IllegalArgumentException("Quando um Item for do Tipo Arma, a Defesa dele será OBRIGATORIAMENTE zero.");
        }

        return itemMagicoRepository.save(itemMagico);
    }

    public ItemMagico deltarItemMagico(Long id){
        ItemMagico itemMagico = itemMagicoRepository.findById(id).orElse(null);
        itemMagicoRepository.delete(itemMagico);
        return itemMagico;
    }

}
