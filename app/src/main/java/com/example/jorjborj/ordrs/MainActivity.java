package com.example.jorjborj.ordrs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by jorjborj on 4/2/2018.
 */

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BottomNavigationView nav = (BottomNavigationView)findViewById(R.id.navbar);
        disableShiftMode(nav);

        // Large screen, LISTVIEW and adapters
        final View mainscreen = (View)findViewById(R.id.largeScreen);
        final ListView lv = (ListView)findViewById(R.id.itemsList);
        final ListView counterlv = (ListView)findViewById(R.id.counterlist);

        //dummy data for now
        final String[] FoodValues = new String[] {"Chicken Salad", "Nazareth Breakfast", "Antricot" };
        final String[] DrinksValues = new String[] { "Cola","Sprite","Fanta","Water" };
        final String[] DessertsValues = new String[] { "Chocolate Cake", "Truffle", "Donuts" };
        final String[] AlcoholValues = new String[] { "Carlsberg", "Tuborg", "Corona", "Whiskey", "Arak" };

        final ArrayList<String> orders = new ArrayList<String>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, FoodValues);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, DrinksValues);
        lv.setAdapter(adapter);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, DessertsValues);
        lv.setAdapter(adapter);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, AlcoholValues);

        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, orders);

        lv.setAdapter(adapter);
        counterlv.setAdapter(adapter4);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orders.add(FoodValues[position]);
                adapter4.notifyDataSetChanged();
            }
        });

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.food:
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                orders.add(FoodValues[position]);
                                adapter4.notifyDataSetChanged();

                            }
                        });
                        break;
                    case R.id.alcohol:
                        lv.setAdapter(adapter3);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                orders.add(AlcoholValues[position]);
                                adapter4.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.drinks:
                        lv.setAdapter(adapter1);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                orders.add(DrinksValues[position]);
                                adapter4.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.desserts:
                        lv.setAdapter(adapter2);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                orders.add(DessertsValues[position]);
                                adapter4.notifyDataSetChanged();
                            }
                        });
                        break;
                }
                return false;
            }
        });

    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }


}
