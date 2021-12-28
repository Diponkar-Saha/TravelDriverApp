package com.example.traveldriverapp.ui.notifications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.traveldriverapp.FacebookAuthActivity;
import com.example.traveldriverapp.LoginActivity;
import com.example.traveldriverapp.R;
import com.example.traveldriverapp.databinding.FragmentNotificationsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class NotificationsFragment extends Fragment {


    private FragmentNotificationsBinding binding;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    Uri imageUri;
    String myUri;
    StorageTask uploadTask;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String change="No";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Drivers");

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_profileFragment);

            }
        });
        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        databaseReference.child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        String phone = String.valueOf(dataSnapshot.child("phone").getValue());
                        String email = String.valueOf(dataSnapshot.child("email").getValue());
                        String name = String.valueOf(dataSnapshot.child("name").getValue());

                        binding.nameTextView.setText(phone);
                        binding.emailTextView.setText(email);
                        binding.textName.setText(name);
                        String image = String.valueOf(dataSnapshot.child("image").getValue());
                        Glide.with(getActivity())
                                .load(image)
                                .placeholder(R.drawable.sample_img)
                                .into(binding.profileImage);


                    }else {


                    }


                }else {


                }

            }
        });





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}