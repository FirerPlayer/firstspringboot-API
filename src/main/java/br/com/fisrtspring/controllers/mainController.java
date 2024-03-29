package br.com.fisrtspring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fisrtspring.models.Usuario;
import br.com.fisrtspring.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class mainController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String teste2(@PathVariable String name) {
        return "Bom dia " + name + "  !!!";
    }

    @GetMapping(value = "allusers")
    @ResponseBody
    public ResponseEntity<List<Usuario>> allUsers() {
        return new ResponseEntity<List<Usuario>>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "register")
    @ResponseBody
    public ResponseEntity<Usuario> newUser(@RequestBody Usuario user) {
        return new ResponseEntity<Usuario>(usuarioRepository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<Usuario> delUser(@RequestParam Long id) {
        Optional<Usuario> deleted = usuarioRepository.findById(id);
        if (deleted.isPresent()) {
            usuarioRepository.delete(deleted.get());
            return new ResponseEntity<Usuario>(deleted.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "select")
    @ResponseBody
    public ResponseEntity<Usuario> selectUser(@RequestParam Long iduser) {
        Optional<Usuario> selectedUser = usuarioRepository.findById(iduser);

        if (selectedUser.isPresent()) {
            return new ResponseEntity<Usuario>(selectedUser.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "update")
    @ResponseBody
    public ResponseEntity<Usuario> update(@RequestBody Usuario upUser) {
        usuarioRepository.saveAndFlush(upUser);
        return new ResponseEntity<Usuario>(upUser, HttpStatus.OK);
    }

    @GetMapping(value = "fName")
    @ResponseBody
    public ResponseEntity<List<Usuario>> userByName(@RequestParam(name = "name") String name) {

        return new ResponseEntity<List<Usuario>>(
                usuarioRepository.findAllName(name.trim().toLowerCase()),
                HttpStatus.OK);
    }
}
