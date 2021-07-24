package br.com.videoflix.VideoFlix.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireBaseInitialization {


    @PostConstruct
    public void initialization() throws IOException {

        FileInputStream serviceAccount =
                new FileInputStream("/Users/rafael/Develop/VideoFlix/key-secret.json");

            FirebaseOptions db = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://videoflix-rafael-default-rtdb.firebaseio.com")
                    .build();

        if (FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(db);
        }
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }

}
