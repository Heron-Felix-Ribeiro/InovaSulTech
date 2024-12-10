package br.com.senac.inovasultech.controllers;

import br.com.senac.inovasultech.dto.AgenteCreateDTO;
import br.com.senac.inovasultech.dto.AgenteUpdateDTO;
import br.com.senac.inovasultech.useCases.agentes.AgentesService;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteResponseDom;
import br.com.senac.inovasultech.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agentes")
@CrossOrigin
public class AgenteController {

    @Autowired
    private AgentesService agentesService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarAgente (@RequestBody AgenteCreateDTO agente){

        try {
            AgenteResponseDom response =
                    agentesService.criarAgente(agente);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarAgentes() {

        try {
            return ResponseEntity.ok(agentesService.listarAgentes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/carregar/{id}")
    public ResponseEntity<?> carregarAgente(@PathVariable Long id){
        try {
            return ResponseEntity.ok(agentesService.listarUmAgente(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarAgenteFiltrados(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(agentesService.listarAgenteByIdTipoAgente(id));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAgente (@PathVariable Long id, @RequestBody AgenteUpdateDTO agente){

        try {
            AgenteResponseDom response =
                    agentesService.atualizarAgente(id, agente);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAgente (@PathVariable Long id) throws Exception {

        agentesService.deletarAgente(id);

        return ResponseEntity.noContent().build();
    }
}
