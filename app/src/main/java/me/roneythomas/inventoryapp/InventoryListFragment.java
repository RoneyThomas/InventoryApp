package me.roneythomas.inventoryapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import me.roneythomas.inventoryapp.databinding.InventoryListBinding;
import me.roneythomas.inventoryapp.databinding.ListItemBinding;

/**
 * Created by roneythomas on 2016-07-26.
 */
public class InventoryListFragment extends ListFragment {

    ArrayList<Inventory> inventoryArrayList;

    public static InventoryListFragment newInstance() {

        Bundle args = new Bundle();

        InventoryListFragment fragment = new InventoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        InventoryListBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_list, container, false);
        View view = binding.getRoot();
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        inventoryArrayList = InventoryLab.get(getActivity()).getInventorys();
        InventoryAdapter adapter = new InventoryAdapter(getActivity(), inventoryArrayList);
        setListAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Fragment fragment = InventoryAddFragment.newInstance();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.contianer_main, fragment).addToBackStack("Edit").commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class InventoryAdapter extends ArrayAdapter<Inventory> {
        ListItemBinding binding;

        public InventoryAdapter(Context context, ArrayList<Inventory> inventories) {
            super(context, 0, inventories);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null || binding == null) {
                binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.list_item, parent, false);
                convertView = binding.getRoot();
            }
            binding.setInventory(inventoryArrayList.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = InventoryDetailFragment.newInstance(inventoryArrayList.get(position).getName());
                    getFragmentManager().beginTransaction().replace(R.id.contianer_main, fragment).addToBackStack("Detail").commit();
                }
            });
            return convertView;
        }
    }
}
