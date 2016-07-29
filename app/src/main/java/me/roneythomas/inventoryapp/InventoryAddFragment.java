package me.roneythomas.inventoryapp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.roneythomas.inventoryapp.databinding.InventoryAddBinding;


/**
 * Created by roneythomas on 2016-07-25.
 */
public class InventoryAddFragment extends Fragment {
    private static final int REQUEST_CODE = 9000;
    Inventory inventory = new Inventory();

    public static InventoryAddFragment newInstance() {

        Bundle args = new Bundle();

        InventoryAddFragment fragment = new InventoryAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final InventoryAddBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_add, container, false);
        View view = binding.getRoot();
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
            }
        });
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inventory.setName(binding.included.nameEditText.getText().toString());
                try {
                    inventory.setQuantity(binding.quantityEditText.getText().toString());
                    inventory.setPrice(binding.priceEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), R.string.no_quantity_price_error, Toast.LENGTH_SHORT).show();
                }
                saveInventory();
            }
        });
        setHasOptionsMenu(true);
        return view;
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String imageUri = String.valueOf(data.getData());
            inventory.setUri(imageUri);
        }
    }

    private void saveInventory() {
        InventoryLab inventoryLab = InventoryLab.get(getActivity());
        if (!(inventory.getName().isEmpty() || inventory.getQuantity().isEmpty() || inventory.getPrice().isEmpty())) {
            if (inventoryLab.getInventory(inventory.getName()) == null) {
                inventoryLab.addInventory(inventory);
            } else {
                inventoryLab.updateInventory(inventory);
            }
            goBackStack();
        } else {
            Toast.makeText(getActivity(), R.string.validation_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void goBackStack() {
        getFragmentManager().popBackStackImmediate();
    }
}
