package com.example.Eshopsample.Acticities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.Eshopsample.PcScreens.PcScreen;
import com.example.Eshopsample.PcScreens.PcScreenDatabaseHandler;
import com.example.Eshopsample.PcTower.PcTower;
import com.example.Eshopsample.PcTower.PcTowerDatabaseHandler;
import com.example.Eshopsample.PersonalComputer.PersonalComputer;
import com.example.Eshopsample.PersonalComputer.PersonalComputerDatabaseHandler;
import com.example.Eshopsample.R;
import com.example.Eshopsample.RecyclerView.CartItem;
import com.example.Eshopsample.RecyclerView.CartItemsAdapter;
import com.example.Eshopsample.Workstation.WorkStation;
import com.example.Eshopsample.Workstation.WorkstationDatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CardItemsActivity extends AppCompatActivity {


    private RecyclerView cartItemsRecyclerView;
    private CartItemsAdapter cartItemsAdapter;
    private List<CartItem> cartItemList=new ArrayList<>();
    private PcTowerDatabaseHandler pcTowerDatabaseHandler;
    private PcScreenDatabaseHandler pcScreenDatabaseHandler;
    private PersonalComputerDatabaseHandler personalComputerDatabaseHandler;
    private WorkstationDatabaseHandler workstationDatabaseHandler;
    private FloatingActionButton cleanCartFloatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_items);

        cartItemsRecyclerView=findViewById(R.id.cartItemsRecyclerView);
        cartItemsRecyclerView.setHasFixedSize(true);
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cleanCartFloatingButton=findViewById(R.id.cleanCartFloatingButton);


        pcTowerDatabaseHandler=new PcTowerDatabaseHandler(getApplicationContext());
        pcScreenDatabaseHandler=new PcScreenDatabaseHandler(getApplicationContext());
        personalComputerDatabaseHandler=new PersonalComputerDatabaseHandler(getApplicationContext());
        workstationDatabaseHandler=new WorkstationDatabaseHandler(getApplicationContext());

        for(PcTower pcTower:pcTowerDatabaseHandler.getAllPcTowers ()){

            CartItem cartItemMemoryGb=new CartItem();

            cartItemMemoryGb.setNameOfCartItem("pc tower memory GB");
            cartItemMemoryGb.setAttributeOfCartItem(String.valueOf(pcTower.getMemoryGb()));

            CartItem cartItemFrequenzyHz=new CartItem();

            cartItemFrequenzyHz.setNameOfCartItem("pc tower frequenzy GHz");
            cartItemFrequenzyHz.setAttributeOfCartItem(String.valueOf(pcTower.getCpuFrequencyGhz()));

            cartItemList.add(cartItemMemoryGb);
            cartItemList.add(cartItemFrequenzyHz);
        }
        for (PcScreen pcScreen:pcScreenDatabaseHandler.getAllPcScreens()){

            CartItem cartItemPcScreen=new CartItem();

            cartItemPcScreen.setNameOfCartItem("pc screen size inches");
            cartItemPcScreen.setAttributeOfCartItem(String.valueOf(pcScreen.getScreenSizeInches()));

            cartItemList.add(cartItemPcScreen);
        }
        for (PersonalComputer personalComputer:personalComputerDatabaseHandler.getAllPersonalComputers()){

            CartItem cartItemMemoryGB=new CartItem();

            cartItemMemoryGB.setNameOfCartItem("personal computer memory GB");
            cartItemMemoryGB.setAttributeOfCartItem(String.valueOf(personalComputer.getMemoryGb()));

            CartItem cartItemFrequencyHz=new CartItem();

            cartItemFrequencyHz.setNameOfCartItem("personal computer cpu frequency GHz");
            cartItemFrequencyHz.setAttributeOfCartItem(String.valueOf(personalComputer.getCpuFrequency()));

            CartItem cartItemPcScreen=new CartItem();

            cartItemPcScreen.setNameOfCartItem("personal computer screen size");
            cartItemPcScreen.setAttributeOfCartItem(String.valueOf(personalComputer.getScreenSizeInches()));

            CartItem cartItemHardDisk=new CartItem();

            cartItemHardDisk.setNameOfCartItem("personal computer hard disk GB");
            cartItemHardDisk.setAttributeOfCartItem(String.valueOf(personalComputer.getHardDiskGB()));

            cartItemList.add(cartItemMemoryGB);
            cartItemList.add(cartItemFrequencyHz);
            cartItemList.add(cartItemPcScreen);
            cartItemList.add(cartItemHardDisk);
        }

        for (WorkStation workStation:workstationDatabaseHandler.getAllWorkstations()){

            CartItem cartItemMemoryGB=new CartItem();

            cartItemMemoryGB.setNameOfCartItem("workstation memory GB");
            cartItemMemoryGB.setAttributeOfCartItem(String.valueOf(workStation.getMemoryGb()));

            CartItem cartItemFrequencyHz=new CartItem();

            cartItemFrequencyHz.setNameOfCartItem("workstation cpu frequency GHz");
            cartItemFrequencyHz.setAttributeOfCartItem(String.valueOf(workStation.getCpuFrequency()));

            CartItem cartItemPcScreen=new CartItem();

            cartItemPcScreen.setNameOfCartItem("workstation screen size inches");
            cartItemPcScreen.setAttributeOfCartItem(String.valueOf(workStation.getScreenSizeInches()));

            CartItem cartItemHardDisk=new CartItem();

            cartItemHardDisk.setNameOfCartItem("workstation hard disk GB");
            cartItemHardDisk.setAttributeOfCartItem(String.valueOf(workStation.getHardDiskGB()));

            CartItem cartItemOperatingSystem=new CartItem();

            cartItemOperatingSystem.setNameOfCartItem("workstation operating system");
            cartItemOperatingSystem.setAttributeOfCartItem(workStation.getOperatingSystem());

            cartItemList.add(cartItemMemoryGB);
            cartItemList.add(cartItemFrequencyHz);
            cartItemList.add(cartItemPcScreen);
            cartItemList.add(cartItemHardDisk);
            cartItemList.add(cartItemOperatingSystem);
        }

        cartItemsAdapter=new CartItemsAdapter(cartItemList);
        Log.i("list.size",String.valueOf(cartItemList.size()));
        cartItemsAdapter=new CartItemsAdapter(cartItemList);
        cartItemsRecyclerView.setAdapter(cartItemsAdapter);

        setCleanCartFloatingButtonMethod();
    }

    private void setCleanCartFloatingButtonMethod(){

        cleanCartFloatingButton.setOnClickListener(v -> {

            pcTowerDatabaseHandler=new PcTowerDatabaseHandler(getApplicationContext());
            pcScreenDatabaseHandler=new PcScreenDatabaseHandler(getApplicationContext());
            personalComputerDatabaseHandler=new PersonalComputerDatabaseHandler(getApplicationContext());
            workstationDatabaseHandler=new WorkstationDatabaseHandler(getApplicationContext());

            pcTowerDatabaseHandler.deleteAllPcTowers();
            pcScreenDatabaseHandler.deleteAllPcScreens();
            personalComputerDatabaseHandler.deleteAllPersonalComputers();
            workstationDatabaseHandler.deleteAllWorkstations();

            cartItemsAdapter=new CartItemsAdapter(new ArrayList<>());
            cartItemsRecyclerView.setAdapter(cartItemsAdapter);

        });
    }
}