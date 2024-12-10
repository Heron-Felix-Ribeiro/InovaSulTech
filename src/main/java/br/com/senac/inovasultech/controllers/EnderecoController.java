package br.com.senac.inovasultech.controllers;

import br.com.senac.inovasultech.dto.EnderecoDTO;
import br.com.senac.inovasultech.useCases.endereco.EnderecoService;
import br.com.senac.inovasultech.useCases.endereco.domains.EnderecoResponseDom;
import br.com.senac.inovasultech.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/endereco")
@CrossOrigin
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecoDTO endereco) {

        try {
            EnderecoResponseDom response =
                    enderecoService.criarEndereco(endereco);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarEnderecos() {

        try {
            return ResponseEntity.ok(enderecoService.listarEnderecos());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUmEndereco (@PathVariable Long id) {

        try {
            return ResponseEntity.ok(enderecoService.listarUmEndereco(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO endereco) {

        try {
            EnderecoResponseDom response =
                    enderecoService.atualizarEndereco(id, endereco);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEndereco (@PathVariable Long id) throws Exception {

        enderecoService.deletarEndereco(id);

        return ResponseEntity.noContent().build();
    }
}
