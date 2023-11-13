package com.example.auctionapp.Fragment.Register;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.auctionapp.Interface.OnItemClickListener;
import com.example.auctionapp.R;
import com.example.auctionapp.databinding.FragmentStep3Binding;

import java.util.Calendar;


public class Step3 extends Fragment {

    private OnItemClickListener mItemClickListener;
    FragmentStep3Binding binding;
    Handler handler;
    Runnable runnable;
    Window window;
    private String id;
    private String email;
    private String rangeDate;

    private String rangeLocal;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step3, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        window = getActivity().getWindow();
        binding.rangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        (view1, year1, monthOfYear, dayOfMonth) -> {
                            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                            binding.rangeDate.setText(selectedDate);
                        },
                        year,
                        month,
                        day
                );

                datePickerDialog.show();
            }
        });
        binding.sixStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                binding.pb.setVisibility(View.VISIBLE);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        binding.pb.setVisibility(View.GONE);
                        if (mItemClickListener != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("Email", email);
                            bundle.putString("ID", id);
                            bundle.putString("rangeDate", rangeDate);
                            bundle.putString("rangeLocal", rangeLocal);
                            mItemClickListener.onItemClick(4, bundle);
                        }
//                        Toast.makeText(v.getContext(), "Bạn đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                };

                handler.postDelayed(runnable, 5000);

            }

        });
    }
}