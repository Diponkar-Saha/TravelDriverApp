package com.example.traveldriverapp.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.traveldriverapp.R;
import com.example.traveldriverapp.databinding.FragmentChat3Binding;
import com.example.traveldriverapp.databinding.FragmentDashboardBinding;
import com.example.traveldriverapp.ui.dashboard.notification.Token;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class Chat3Fragment extends Fragment {

    private FragmentChat3Binding binding;

    private RecyclerView recyclerView;


    private UserAdapter userAdapter;
    private List<User> mUsers;
    FrameLayout frameLayout;
    TextView es_descp, es_title;

    FirebaseUser fuser;
    DatabaseReference reference,yyy;

    private List<Chatlist> usersList;
    static OnItemClick onItemClick;


    public static Chat3Fragment newInstance(OnItemClick click) {

        onItemClick = click;
        Bundle args = new Bundle();

        Chat3Fragment fragment = new Chat3Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentChat3Binding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.recycler_view3);
        frameLayout = view.findViewById(R.id.es_layout);
        es_descp = view.findViewById(R.id.es_descp);
        es_title = view.findViewById(R.id.es_title);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chatlist chatlist = snapshot.getValue(Chatlist.class);
                    Log.d("uid",chatlist.id.toString());
                    usersList.add(chatlist);
                }
                if (usersList.size() == 0) {
                    frameLayout.setVisibility(View.VISIBLE);
                } else {
                    frameLayout.setVisibility(View.GONE);
                }
/////
                //chatList();


                mUsers = new ArrayList<>();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mUsers.clear();


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            Log.d("uid",user.getUid().toString());

                            for (Chatlist chatlist : usersList) {

                                if (user != null && user.getUid() != null && chatlist.getId() != null && user.getUid().equals(chatlist.getId())) {

                                    mUsers.add(user);
                                }
                            }
                        }


                        userAdapter = new UserAdapter(getContext(), onItemClick, mUsers, true);
                        recyclerView.setAdapter(userAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






                ////
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // updateToken(FirebaseMessaging.getInstance().getToken().getResult());

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    return;
                }

                String token = task.getResult();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
                Token token1 = new Token(token);
                reference.child(fuser.getUid()).setValue(token1);
            }
        });


        return view;
    }


//    private void updateToken(String token){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
//        Token token1 = new Token(token);
//        reference.child(fuser.getUid()).setValue(token1);
//    }

    private void chatList() {
        mUsers = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Log.d("uid",user.getUid().toString());

                    for (Chatlist chatlist : usersList) {

                        if (user != null && user.getUid() != null && chatlist.getId() != null && user.getUid().equals(chatlist.getId())) {

                            mUsers.add(user);
                        }
                    }
                }


                userAdapter = new UserAdapter(getContext(), onItemClick, mUsers, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}