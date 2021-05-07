package com.randikalakmal.firebasedemo.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.randikalakmal.firebasedemo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTION_NAME="products";

    public String saveProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApi = db.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApi.get().getUpdateTime().toString();
    }

    public Product getProductDetails(String name) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference document = db.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = document.get();

        DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

        Product product =null;
        if (documentSnapshot.exists()){
            product=documentSnapshot.toObject(Product.class);
            return product;
        }else{
            return null;
        }
    }

    public String updateProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> set = db.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return set.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> delete = db.collection(COLLECTION_NAME).document(name).delete();

        return "Product"+name+" has been deleted";
    }
}
