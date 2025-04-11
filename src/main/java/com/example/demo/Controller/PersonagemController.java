package com.example.demo.Controller;

import com.example.demo.Model.ItemMagico;
import com.example.demo.Model.Personagem;
import com.example.demo.Service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/personagem")
@RestController
public class PersonagemController {

    @Autowired
    PersonagemService personagemService;



    @Operation(description = "Busca todos os personagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos personagens"),
            @ApiResponse(responseCode = "404", description = "Personagem não existe")
    })
    @GetMapping
    public ResponseEntity<List<Personagem>> listarPersonagens(){
        return new ResponseEntity<>(personagemService.listarPersonagens(), HttpStatus.OK);
    }

    @Operation(description = "Busca o personagem pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o personagem selecionado"),
            @ApiResponse(responseCode = "404", description = "Personagem não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> listarPersonagensPorId(@PathVariable Long id){
        return new ResponseEntity<>(personagemService.listarPorId(id), HttpStatus.OK);
    }


    @Operation(description = "Cria o personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna o personagem criado"),
            @ApiResponse(responseCode = "400", description = "Personagem não pode ser criado")
    })
    @PostMapping
    public  ResponseEntity<?> criarPersonagem(@RequestBody Personagem personagem){
        try {
            return new ResponseEntity<>(personagemService.criarPersonagem(personagem), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @Operation(description = "Atualiza o nome de aventureiro de um personagem pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Personagem não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public  ResponseEntity<Personagem> modificarPersonagem(@PathVariable Long id ,@RequestBody Personagem modificacao){
        return new ResponseEntity<>(personagemService.editarPersonagem(id,modificacao), HttpStatus.OK);
    }


    @Operation(description = "Adiciona item magico ao personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item vinculado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não é possível vincular o item"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}/adicionarItensMagicos/{idItemMagico}")
    public ResponseEntity<?> adicionarItensMagicos(@PathVariable(value = "id") Long id, @PathVariable(value = "idItemMagico") Long idItemMagico ){
        try {
            return new ResponseEntity<>(personagemService.adicionarItemMagico(id,idItemMagico), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(description = "Remove item magico ao personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não é possível remover o item"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}/removerItensMagicos/{idItemMagico}")
    public ResponseEntity<?> removerItensMagicos(@PathVariable(value = "id") Long id, @PathVariable(value = "idItemMagico") Long idItemMagico ){
        try {
            return new ResponseEntity<>(personagemService.removerItemMagico(id,idItemMagico), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(description = "Busca todos os itens magicos do personagens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos itens magicos do personagem"),
            @ApiResponse(responseCode = "404", description = "Itens não encontrados")
    })
    @GetMapping("/{id}/listarItensMagicosDoPersonagem")
    public List<ItemMagico> listarItensMagicosDoPersonagem(@PathVariable Long id){
        return personagemService.listarItensMagicos(id);
    }


    @Operation(description = "Busca o amuleto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o amuleto selecionado"),
            @ApiResponse(responseCode = "404", description = "Amuleto não existe")
    })
    @GetMapping("/{id}/buscarAmuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long id){
        return personagemService.buscarAmuleto(id);
    }


    @Operation(description = "Deleta o personagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta o personagem"),
            @ApiResponse(responseCode = "400", description = "Personagem não encontrado para deletar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Personagem> deletarPersonagem(@PathVariable Long id){
        return new ResponseEntity<>(personagemService.excluirPersonagem(id), HttpStatus.OK);
    }

}
