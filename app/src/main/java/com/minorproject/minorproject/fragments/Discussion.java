package com.minorproject.minorproject.fragments;


import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.minorproject.minorproject.R;
import com.minorproject.minorproject.activities.NavActivity;
import com.minorproject.minorproject.adapters.MessaageAdapter;
import com.minorproject.minorproject.models.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;


public class Discussion extends Fragment {

    private static final int CHOOSE_IMAGE = 101 ;
    private List<Message> messageList;
    //private List<ImageMessage> imageMessageList;
    private ProgressBar progressBar;
    private EditText editText;
    private ImageButton button;
    private DatabaseReference dbMessages;

    private RecyclerView recyclerView;
    Uri filePath;
    private MessaageAdapter adapter;
    //private ImageMessageAdapter imageMessageAdapter;
    private StorageReference mStorageRef;

    //String profileImageUrl;

    ImageView imageViewback;

    ImageView imageView,imageView1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chatq,container,false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.messageEditText);
        button = view.findViewById(R.id.sendButton);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        imageView = view.findViewById(R.id.addMessageImageView);
        imageView1 = view.findViewById(R.id.messageImageView);
        recyclerView = view.findViewById(R.id.messageRecyclerView);
        imageViewback = view.findViewById(R.id.back);
        dbMessages = FirebaseDatabase.getInstance().getReference("discussion");
        updateRecyclerViewMessage();

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
                FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
                floatingActionButton.show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),CHOOSE_IMAGE);


            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                String id = dbMessages.push().getKey();
                String message = editText.getText().toString().trim();

                Message message1;
                if(name!=null){
                    message1 = new Message(name,message,null);}

                else {

                    String phoneNumber  = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().toString();

                    message1 = new Message(phoneNumber,message,null);
                }
                dbMessages.child(id).setValue(message1);

                editText.setText(null);

                updateRecyclerViewMessage();


            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null&&data.getData()!=null) {

            filePath = data.getData();


            putImageInStorage();

        }
    }

    private void putImageInStorage() {
        if(filePath!=null){
            progressBar.setVisibility(View.VISIBLE);
            final String name  = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            final StorageReference sRef = mStorageRef.child("Images/" + System.currentTimeMillis() + "." + getFileExtension(filePath));


            sRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Message message;
                            if(name!=null){
                            message = new Message(name,null,uri.toString());}

                            else {

                                String phoneNumber  = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().toString();

                                message = new Message(phoneNumber,null,uri.toString());
                            }

                            String uploadId = dbMessages.push().getKey();
                            dbMessages.child(uploadId).setValue(message);
                            updateRecyclerViewMessage();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }


    }



    public String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }



    private void updateRecyclerViewMessage() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageList = new ArrayList<>();
        adapter = new MessaageAdapter(getActivity(), messageList);
        recyclerView.setAdapter(adapter);

        dbMessages.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    progressBar.setVisibility(View.GONE);
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        String name =  ds.child("name").getValue(String.class);
                        String message= ds.child("message").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        Message m;
                         m= new Message(name,message,imageUrl);
                        messageList.add(m);
                    }
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(messageList.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}