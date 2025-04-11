package com.example.demo.Controller;


import com.example.demo.Model.ItemMagico;
import com.example.demo.Service.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemMagico")
public class ItemMagicoController {

    @Autowired
    ItemMagicoService itemMagicoService;

    @Operation(description = "Busca todos os itens magicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos itens magicos"),
            @ApiResponse(responseCode = "404", description = "Item não existe")
    })
    @GetMapping
    public ResponseEntity<List<ItemMagico>> listarItensMagicos(){
        return new ResponseEntity<>(itemMagicoService.listarItensMagicos(), HttpStatus.OK);
    }


    @Operation(description = "Busca o item magico pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o item magico selecionado"),
            @ApiResponse(responseCode = "404", description = "Item não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> listarItemMagicoPorId(@PathVariable Long id){
        return new ResponseEntity<>(itemMagicoService.listarItemMagicoPorId(id), HttpStatus.OK);
    }


    @Operation(description = "Cria item magico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna o item criado"),
            @ApiResponse(responseCode = "400", description = "Item não pode ser criado")
    })
    @PostMapping
    public ResponseEntity<?> criarItemMagico(@RequestBody ItemMagico itemMagico){
        try {
            return new ResponseEntity<>(itemMagicoService.criarItemMagico(itemMagico), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @Operation(description = "Deleta o item magico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta o item"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado para deletar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemMagico> deletarItemMagico(@PathVariable Long id){
        return new ResponseEntity<>(itemMagicoService.deltarItemMagico(id), HttpStatus.OK);
    }


}
