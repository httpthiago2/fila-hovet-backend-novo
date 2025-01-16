package dev.thiagogonzalez.filahovetapipoc.controller;

import dev.thiagogonzalez.filahovetapipoc.domain.dto.UsuarioDTO;
import dev.thiagogonzalez.filahovetapipoc.domain.enumeration.TipoPerfil;
import dev.thiagogonzalez.filahovetapipoc.domain.model.Usuario;
import dev.thiagogonzalez.filahovetapipoc.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll().stream().map(UsuarioDTO::new).map(usuarioDTO -> {
            usuarioDTO.setSenha(null);
            return usuarioDTO;
        }).collect(Collectors.toList());
    }

    @GetMapping("{usuarioId}")
    public UsuarioDTO findById(@PathVariable("usuarioId") Long usuarioId) {
        return new UsuarioDTO(usuarioService.findById(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping("{idMedico}")
    public Usuario update(@PathVariable("idMedico") Long idMedico, @RequestBody Usuario dadosNovosUsuario) {
        Usuario usuarioToUpdate = usuarioService.findById(idMedico);

        BeanUtils.copyProperties(dadosNovosUsuario, usuarioToUpdate, "id");
        return usuarioService.save(usuarioToUpdate);
    }

    @DeleteMapping("{idMedico}")
    private Boolean delete(@PathVariable("idMedico") Long idMedico) {
        return usuarioService.delete(idMedico);
    }

    @GetMapping("/find-by-perfil/{perfil}")
    public List<UsuarioDTO> findByPerfil(@PathVariable("perfil") String perfil) {
        return usuarioService
                .findByTipoPerfil(TipoPerfil.valueOf(perfil)).stream().map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

}
