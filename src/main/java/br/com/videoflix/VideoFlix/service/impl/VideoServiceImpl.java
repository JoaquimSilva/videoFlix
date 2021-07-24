package br.com.videoflix.VideoFlix.service.impl;


import br.com.videoflix.VideoFlix.dto.VideoDTO;
import br.com.videoflix.VideoFlix.firebase.FireBaseInitialization;
import br.com.videoflix.VideoFlix.service.VideoServiceInterface;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoServiceInterface {

    @Autowired
    private FireBaseInitialization fbi;

    @Override
    public List<VideoDTO> lista() {

        List<VideoDTO> response = new ArrayList<>();

        VideoDTO videoDTO;
        ApiFuture<QuerySnapshot> query = getCollection().get();

        try {
            for (DocumentSnapshot doc: query.get().getDocuments()) {
                videoDTO = doc.toObject(VideoDTO.class);
                videoDTO.setId(doc.getId());
                response.add(videoDTO);
            }
            return response;
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public Boolean salvar(VideoDTO videoDTO) {
        Map<String, Object> map = getStringObjectMap(videoDTO);
        ApiFuture<WriteResult> write =  getCollection().document().create(map);

        try {
            if (null != write.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    @Override
    public Boolean atualizar(String id, VideoDTO videoDTO) {
        Map<String, Object> map = getStringObjectMap(videoDTO);
        ApiFuture<WriteResult> write = getCollection().document(id).set(map);
        try {
            if (null != write.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deletar(String id) {
        ApiFuture<WriteResult> write = getCollection().document(id).delete();
        try {
            if (null != write.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    private CollectionReference getCollection() {
        return fbi.getFirestore().collection("videos");
    }

    private Map<String, Object> getStringObjectMap(VideoDTO videoDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("titulo", videoDTO.getTitulo());
        map.put("descricao", videoDTO.getDescricao());
        map.put("url", videoDTO.getUrl());
        return map;
    }

}
