package com.example.demo.Service;

import com.example.demo.Model.ItemMagico;
import com.example.demo.Model.Personagem;
import com.example.demo.Model.TipoItem;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    @Autowired
    PersonagemRepository personagemRepository;

    @Autowired
    ItemMagicoService itemMagicoService;

    public List<Personagem> listarPersonagens(){
        return personagemRepository.findAll().stream().map(personagem -> personagem.somarAtributos()).collect(Collectors.toList());
    }

    public Personagem listarPorId(Long id){
        return personagemRepository.findById(id).orElse(null).somarAtributos();
    }

    public Personagem criarPersonagem(Personagem personagem){

        if (personagem.getForca() + personagem.getDefesa() > 10){
            throw new IllegalArgumentException("A soma da força e defesa não pode ultrapassar 10 pontos");
        }

        return personagemRepository.save(personagem);
    }

    public Personagem editarPersonagem(Long id, Personagem modificacao){
        Personagem personagem = personagemRepository.findById(id).orElse(null);

        personagem.setNomeAventureiro(modificacao.getNomeAventureiro());

        return personagemRepository.save(personagem).somarAtributos();

    }

    public Personagem excluirPersonagem(Long id){
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        personagemRepository.delete(personagem);
        return personagem;
    }

    public Personagem adicionarItemMagico(Long id, Long idItemMagico){
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        ItemMagico itemMagico = itemMagicoService.listarItemMagicoPorId(idItemMagico);

        if (itemMagico.getTipoItem() == TipoItem.Amuleto && personagem.temAmuleto()){
            throw new IllegalArgumentException("O personagem não pode possuir mais de um amuleto");
        }

        personagem.getItensMagicos().add(itemMagico);
        return personagemRepository.save(personagem);

    }

    public List<ItemMagico> listarItensMagicos(Long id){
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        return personagem.getItensMagicos();
    }

    public Personagem removerItemMagico(Long id, Long idItemMagico){

        int index = 0;
        Personagem personagem = personagemRepository.findById(id).orElse(null);

        for(ItemMagico itemMagico : personagem.getItensMagicos()){
            if(itemMagico.getId().equals(idItemMagico)){
                personagem.getItensMagicos().remove(index);
                return personagemRepository.save(personagem);
            }
            index++;
        }
        throw  new NoSuchElementException("Não há itens para ser removido");

    }

    public ItemMagico buscarAmuleto(Long id){
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        for (ItemMagico itemMagico : personagem.getItensMagicos()){
            if (itemMagico.getTipoItem() == TipoItem.Amuleto){
                return itemMagico;
            }
        }

        throw new NoSuchElementException("Amuleto não encontrado");
    }

}
