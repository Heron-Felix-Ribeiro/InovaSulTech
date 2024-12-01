package br.com.senac.inovasultech.controllers;

import br.com.senac.inovasultech.useCases.cidade.CidadeService;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeRequestDom;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeResponseDom;
import br.com.senac.inovasultech.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cidade")
@CrossOrigin
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarCidade (@RequestBody CidadeRequestDom cidade){

        try {
            CidadeResponseDom cidadeResponse = cidadeService.criaCidade(cidade);

            return ResponseEntity.ok(cidadeResponse);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarCidade () {

        try {
            return ResponseEntity.ok(cidadeService.listarCidades());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarCidade (@PathVariable Long id) {

        cidadeService.deletarCidade(id);

        try {
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }
}
