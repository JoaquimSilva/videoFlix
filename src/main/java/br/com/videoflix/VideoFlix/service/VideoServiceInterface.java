package br.com.videoflix.VideoFlix.service;

import br.com.videoflix.VideoFlix.dto.VideoDTO;

import java.util.List;

public interface VideoServiceInterface {

    List<VideoDTO> lista();

    Boolean salvar(VideoDTO videoDTO);

    Boolean atualizar(String id, VideoDTO videoDTO);

    Boolean deletar(String id);
}
