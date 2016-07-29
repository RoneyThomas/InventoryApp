package me.roneythomas.inventoryapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import me.roneythomas.inventoryapp.databinding.InventoryDetailBinding;

/**
 * Created by roneythomas on 2016-07-28.
 */

public class InventoryDetailFragment extends Fragment {
    public static String NAME = "NAME";

    public static InventoryDetailFragment newInstance(String name) {

        Bundle args = new Bundle();
        args.putString(NAME, name);

        InventoryDetailFragment fragment = new InventoryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final InventoryDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_detail, container, false);
        Bundle bundle = getArguments();
        final Inventory inventory = InventoryLab.get(getActivity()).getInventory(bundle.getString(NAME));
        updateBinding(binding, inventory);
        View view = binding.getRoot();
        setHasOptionsMenu(true);
        binding.saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int quantity = Integer.parseInt(inventory.getQuantity());
                    if (quantity > 0) {
                        quantity--;
                        inventory.setQuantity(String.valueOf(quantity));
                        InventoryLab.get(getContext()).updateInventory(inventory);
                        updateBinding(binding, inventory);
                    }
                } catch (NumberFormatException e) {

                }
            }
        });
        binding.receivingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int quantity = Integer.parseInt(inventory.getQuantity());
                    quantity++;
                    inventory.setQuantity(String.valueOf(quantity));
                    InventoryLab.get(getContext()).updateInventory(inventory);
                    updateBinding(binding, inventory);
                } catch (NumberFormatException e) {

                }
            }
        });
        binding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InventoryLab.get(getContext()).deleteInventory(inventory);
                goBackStack();
            }
        });
        String uri = inventory.getUri();
        if (uri != null) {
            binding.imageView.setImageURI(Uri.parse(inventory.getUri()));
        }
        return view;
    }

    private void updateBinding(InventoryDetailBinding binding, Inventory inventory) {
        binding.setInventory(inventory);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBackStack() {
        getFragmentManager().popBackStackImmediate();
    }
}
