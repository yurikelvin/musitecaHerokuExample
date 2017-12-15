package musiteca.musiteca.controller;

import musiteca.musiteca.model.*;
import musiteca.musiteca.repository.PlaylistRepository;
import musiteca.musiteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements CrudController<Usuario>{

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @RequestMapping(method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value="/u/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Usuario>> getAll() {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value="/u/", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deletarTodos() {
        usuarioService.removeAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @RequestMapping(value="/u/{name}" ,method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> get(@PathVariable String name) {

        Usuario usuarioAchado = usuarioService.getByName(name);

        HttpStatus resp = HttpStatus.OK;
        if(usuarioAchado == null) {
            resp = HttpStatus.NOT_FOUND;
        }



        return new ResponseEntity<>(usuarioAchado, resp);
    }

    @Override
    @RequestMapping(value="/u/{name}" ,method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> modificar(@PathVariable String name, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.update(usuario);
        HttpStatus resp = HttpStatus.OK;
        if(usuarioAtualizado == null) {
            resp = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(usuarioAtualizado, resp);
    }


    @Override
    @RequestMapping(value="/u/{name}" ,method=RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable String name) {
        usuarioService.removeByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @RequestMapping(value="/u/{name}/artistas",method=RequestMethod.GET)
//    public ResponseEntity<Collection<Artista>> getArtistas(@PathVariable String name) {
//        return new ResponseEntity<>(usuarioService.getArtistas(name), HttpStatus.OK);
//    }

//    @RequestMapping(value="/u/{name}/favoritos",method=RequestMethod.GET)
//    public ResponseEntity<Collection<Artista>> getFavoritos(@PathVariable String name) {
//        return new ResponseEntity<>(usuarioService.getFavoritos(name), HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/u/{name}/playlists",method=RequestMethod.GET)
//    public ResponseEntity<Collection<Playlist>> getPlaylists(@PathVariable String name) {
//        return new ResponseEntity<>(usuarioService.getPlaylists(name), HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/u/{name}/albuns",method=RequestMethod.GET)
//    public ResponseEntity<Collection<Album>> getAlbuns(@PathVariable String name) {
//        return new ResponseEntity<>(usuarioService.getAlbuns(name), HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/u/{name}/musicas",method=RequestMethod.GET)
//    public ResponseEntity<Collection<Musica>> getMusicas(@PathVariable String name) {
//        return new ResponseEntity<>(usuarioService.getMusicas(name), HttpStatus.OK);
//    }

}
