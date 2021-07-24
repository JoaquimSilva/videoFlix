package br.com.videoflix.VideoFlix.controller;

import br.com.videoflix.VideoFlix.dto.VideoDTO;
import br.com.videoflix.VideoFlix.service.VideoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class VideoController {

    @Autowired
    private VideoServiceInterface videoServiceInterface;

    @GetMapping(value = "videos")
    public String getVideos() {
        return "Show! Tá funcionando!";
    }

    @GetMapping(value = "lista")
    public ResponseEntity lista() {
        return new ResponseEntity(videoServiceInterface.lista(), HttpStatus.OK);
    }

    @PostMapping(value = "videos")
    public ResponseEntity salvar(@RequestBody VideoDTO video) {
        return new ResponseEntity(videoServiceInterface.salvar(video), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/atualizar")
    public ResponseEntity atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid VideoDTO video) {
        return new ResponseEntity(videoServiceInterface.atualizar(id, video), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/deletar")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        return new ResponseEntity(videoServiceInterface.deletar(id), HttpStatus.OK);
    }

}
