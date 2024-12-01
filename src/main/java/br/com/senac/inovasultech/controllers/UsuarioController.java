package br.com.senac.inovasultech.controllers;

import br.com.senac.inovasultech.useCases.usuario.UsuarioService;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioLoginRequestDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioLoginResponseDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioRequestDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioResponseDom;
import br.com.senac.inovasultech.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario (@RequestBody UsuarioRequestDom usuario) {

        try {

            UsuarioResponseDom response = usuarioService.criarUsuario(usuario);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginRequestDom usuario){
        try{
            UsuarioLoginResponseDom usuarioResponse =
                    usuarioService.login(usuario);

            return ResponseEntity.ok(usuarioResponse);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @GetMapping("/carregar")
    public ResponseEntity<?> carregarTodos(){
        try {
            return ResponseEntity.ok(usuarioService.carregarUsuarios());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parseResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario (@PathVariable Long id) throws Exception {

        usuarioService.deletaraUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
