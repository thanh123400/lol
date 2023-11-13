package com.example.auctionapp.Fragment.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.auctionapp.Adapter.FavouriteAdapter;
import com.example.auctionapp.Adapter.homeAdapter;
import com.example.auctionapp.Model.BsCarHome;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.FragmentFavouriteBinding;

import java.util.ArrayList;

public class Favourite extends Fragment {
    FragmentFavouriteBinding fragmentFavouriteBinding;
    String uID;
    ArrayList<BsCarHome> favouriteUser = new ArrayList<>();
    FavouriteAdapter favouriteAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFavouriteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        return fragmentFavouriteBinding.getRoot();
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
        favouriteAdapter = new FavouriteAdapter(requireContext(), favouriteUser);

        fragmentFavouriteBinding.rvFavourite.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        fragmentFavouriteBinding.rvFavourite.setAdapter(favouriteAdapter);
    }

    private void addDB() {
        if (uID != null) {
            favouriteUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", 10));
            favouriteUser.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", 100));
            favouriteUser.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", 10));
            favouriteUser.add(new BsCarHome("30K-567.89", "Hà Nội", "Xe Con", 10));
            favouriteUser.add(new BsCarHome("47A-599.99", "Đắk Lắk", "Xe Con", 1000));
            favouriteUser.add(new BsCarHome("30K-555.55", "Hà Nội", "Xe Con", 10));
            favouriteUser.add(new BsCarHome("98A-666.66", "Bắc Giang", "Xe Con", 100));
            favouriteUser.add(new BsCarHome("36A-999.99", "Thanh Hóa", "Xe Con", 10));

        }
    }
}