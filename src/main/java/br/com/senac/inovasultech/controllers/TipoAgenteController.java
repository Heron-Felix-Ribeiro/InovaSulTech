package br.com.senac.inovasultech.controllers;

import br.com.senac.inovasultech.useCases.tipoAgente.TipoAgenteService;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteRequestDom;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteResponseDom;
import br.com.senac.inovasultech.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipoagente")
@CrossOrigin
public class TipoAgenteController {

    @Autowired
    private TipoAgenteService tipoAgenteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarTipoAgente (@RequestBody TipoAgenteRequestDom tipoAgente) {

        try {
            TipoAgenteResponseDom response =
                    tipoAgenteService.criarTipoAgente(tipoAgente);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> carregarAllTipoAgente () {

        try {
            return ResponseEntity.ok(tipoAgenteService.listarTipoAgente());
        }
         catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
         }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarTipoAgente (@PathVariable Long id, @RequestBody TipoAgenteRequestDom tipoAgente){

        try {
            TipoAgenteResponseDom response = tipoAgenteService.atualizarTipoAgente(id, tipoAgente);

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));

        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTipoAgente (@PathVariable Long id) throws Exception {

        tipoAgenteService.deletarTipoAgente(id);

        return ResponseEntity.noContent().build();
    }
}
