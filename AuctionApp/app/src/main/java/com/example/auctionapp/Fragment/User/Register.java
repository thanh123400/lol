package com.example.auctionapp.Fragment.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auctionapp.Adapter.FavouriteAdapter;
import com.example.auctionapp.Adapter.RegisterAdapter;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.FragmentRegisterBinding;

import java.util.ArrayList;

public class Register extends Fragment {

    FragmentRegisterBinding fragmentRegisterBinding;
    String uID;
    ArrayList<BsCarHome> registerUser = new ArrayList<>();
    RegisterAdapter registerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRegisterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        return fragmentRegisterBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            uID = arguments.getString("userId");
        }
        addDB();
        onView();
    }

    private void onView() {
        registerAdapter = new RegisterAdapter(requireContext(), registerUser);

        fragmentRegisterBinding.rvRegister.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        fragmentRegisterBinding.rvRegister.setAdapter(registerAdapter);
    }

    private void addDB() {
        if (uID != null) {
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "10:30", "11:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "7:30", "8:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "8:30", "9:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "13:30", "14:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "14:30", "15:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "22/10/2023", "16:30", "17:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "20/10/2023", "10:30", "11:30"));
            registerUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", "24/10/2023", "10:30", "11:30"));

        }
    }
}