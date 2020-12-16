package com.example.Eshopsample.Acticities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.Eshopsample.PcScreens.PcScreenDatabaseHandler;
import com.example.Eshopsample.PcScreens.PcScreen;
import com.example.Eshopsample.PcTower.PcTower;
import com.example.Eshopsample.PcTower.PcTowerDatabaseHandler;
import com.example.Eshopsample.PersonalComputer.PersonalComputer;
import com.example.Eshopsample.PersonalComputer.PersonalComputerDatabaseHandler;
import com.example.Eshopsample.R;
import com.example.Eshopsample.Workstation.WorkStation;
import com.example.Eshopsample.Workstation.WorkstationDatabaseHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView eshopImageView;
    private MaterialButton pcTowerSelectButton;
    private MaterialButton pcScreenSelectButton;
    private MaterialButton personalComputersSelectButton;
    private MaterialButton workStationSelectButton;
    private MaterialButton goToCartActivityButton;

    //pc_tower_dialog
    private AlertDialog pcTowerDialog;
    private AlertDialog.Builder pcTowerDialogBuilder;
    private TextInputLayout memoryGbPcTowerTextInputLayout;
    private EditText memoryGbPcTowerEditText;
    private TextInputLayout cpuFrequencyGhzPcTowerTextInputLayout;
    private EditText cpuFrequencyGhzPcTowerEditText;
    private MaterialButton addToCartPcTowerButton;
    private MaterialButton cancelPcTowerButton;

    //pc_screen_dialog
    private AlertDialog pcScreenDialog;
    private AlertDialog.Builder pcScreenDialogBuilder;
    private TextInputLayout pcScreenDialogTextInputLayout;
    private EditText pcScreenDialogEditText;
    private MaterialButton addToCartPcScreenButton;
    private MaterialButton cancelPcScreenButton;

    //personal_computer_dialog
    private AlertDialog personalComputerDialog;
    private AlertDialog.Builder personalComputerDialogBuilder;
    private TextInputLayout memoryGbPersonalComputerTextInputLayout;
    private EditText memoryGbPersonalComputerEditText;
    private TextInputLayout cpuFrequencyGhzPersonalComputerTextInputLayout;
    private EditText cpuFrequencyGhzPersonalComputerEditText;
    private TextInputLayout pcScreenPersonalComputerTextInputLayout;
    private EditText pcScreenPersonalComputerDialogEditText;
    private TextInputLayout hardDiskPersonalComputerTextInputLayout;
    private EditText hardDiskPersonalComputerEditText;
    private MaterialButton addToCartPersonalComputerButton;
    private MaterialButton cancelPersonalComputerButton;
    //workstation dialog
    private AlertDialog workstationDialog;
    private AlertDialog.Builder workstationDialogBuilder;
    private TextInputLayout memoryGbWorkstationTextInputLayout;
    private EditText memoryGbWorkstationEditText;
    private TextInputLayout cpuFrequencyGhzWorkstationTextInputLayout;
    private EditText cpuFrequencyGhzWorkstationEditText;
    private TextInputLayout pcScreenWorkstationTextInputLayout;
    private EditText pcScreenWorkstationDialogEditText;
    private TextInputLayout hardDiskWorkstationTextInputLayout;
    private EditText hardDiskWorkstationEditText;
    private RadioButton windowsradioButton;
    private RadioButton linuxRadioButton;
    private MaterialButton addToCartWorkstationButton;
    private MaterialButton cancelWorkstationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFindViewsById();
        setOnClickListenersOnButtons();
//        setPicassoForImages();
    }

    private void setOnClickListenersOnButtons() {

        pcTowerSelectButton.setOnClickListener(this);
        pcScreenSelectButton.setOnClickListener(this);
        personalComputersSelectButton.setOnClickListener(this);
        workStationSelectButton.setOnClickListener(this);
        goToCartActivityButton.setOnClickListener(this);
    }

    private void setFindViewsById() {

        eshopImageView = findViewById(R.id.eshopImage);
        pcTowerSelectButton = findViewById(R.id.pcTowerButton);
        pcScreenSelectButton = findViewById(R.id.pcScreenButton);
        personalComputersSelectButton = findViewById(R.id.personalComputerButton);
        workStationSelectButton = findViewById(R.id.workStationButton);
        goToCartActivityButton=findViewById(R.id.goToCardActivityButton);

    }


    private void goToCartActivityMethod(){

       Intent intent=new Intent(MainActivity.this,CardItemsActivity.class);
       startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pcTowerButton:

                selectPcTowerDialog(view);

                break;
            case R.id.pcScreenButton:

                selectPcScreenDialog(view);


                break;
            case R.id.personalComputerButton:

                selectPersonalComputerDialog(view);


                break;
            case R.id.workStationButton:

                selectWorkStationDialog(view);


                break;
            case R.id.goToCardActivityButton:

                goToCartActivityMethod();
        }

    }

    private void selectPcTowerDialog(View view) {



            pcTowerDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            view = getLayoutInflater().inflate(R.layout.select_pc_tower_dialog, null);
            pcTowerDialogBuilder.setView(view);
            pcTowerDialog = pcTowerDialogBuilder.create();
            pcTowerDialog.show();

            memoryGbPcTowerTextInputLayout = view.findViewById(R.id.memoryGbTextInputLayout);
            cpuFrequencyGhzPcTowerTextInputLayout = view.findViewById(R.id.cpuFrequencyTextInputLayout);

            memoryGbPcTowerTextInputLayout.setHint(getResources().getString(R.string.memory_gb));
            cpuFrequencyGhzPcTowerTextInputLayout.setHint(getResources().getString(R.string.cpu_frequency_in_Hz));

            memoryGbPcTowerEditText = view.findViewById(R.id.memoryGbPcTowerEditText);
            cpuFrequencyGhzPcTowerEditText = view.findViewById(R.id.cpuFrequencyPcTowerEditText);

            addToCartPcTowerButton = view.findViewById(R.id.addToCartPcTowerDialog);
            addToCartPcTowerButton.setOnClickListener(v -> {

                if (!memoryGbPcTowerEditText.getText().toString().trim().isEmpty()&&!cpuFrequencyGhzPcTowerEditText.getText().toString().trim().isEmpty()) {
                    PcTowerDatabaseHandler pcTowerDatabaseHandler=new PcTowerDatabaseHandler(getApplicationContext());
                    pcTowerDatabaseHandler.deleteAllPcTowers();
                    PcTower pcTower=new PcTower();
                    pcTower.setId(1);
                    pcTower.setCpuFrequencyGhz(Double.parseDouble(cpuFrequencyGhzPcTowerEditText.getText().toString().trim()));
                    pcTower.setMemoryGb(Integer.parseInt(memoryGbPcTowerEditText.getText().toString().trim()));


                    pcTowerDatabaseHandler.addPcTower(pcTower);

                    Toast.makeText(MainActivity.this,"Pc tower add to cart",Toast.LENGTH_LONG).show();
                    pcTowerDialog.dismiss();
                } else {
                    memoryGbPcTowerTextInputLayout.setError("must not be empty");
                    cpuFrequencyGhzPcTowerEditText.setError("must not be empty");
                }

            });

            cancelPcTowerButton = view.findViewById(R.id.cancelButtonPcTowerDialog);
            cancelPcTowerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pcTowerDialog.dismiss();
                }
            });

    }

    private void selectPcScreenDialog(View view) {

            pcScreenDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            view = getLayoutInflater().inflate(R.layout.select_pc_screen_dialog, null);
            pcScreenDialogBuilder.setView(view);
            pcScreenDialog = pcScreenDialogBuilder.create();
            pcScreenDialog.show();

            pcScreenDialogTextInputLayout = view.findViewById(R.id.pcScreenInchesDialogTextInputLayout);
            pcScreenDialogTextInputLayout.setHint(getResources().getString(R.string.pc_screen_inches));

            pcScreenDialogEditText = view.findViewById(R.id.pcScreenDialogEditText);

            addToCartPcScreenButton = view.findViewById(R.id.addToCartPcScreenDialog);
            addToCartPcScreenButton.setOnClickListener(v -> {
                if (!pcScreenDialogEditText.getText().toString().trim().isEmpty()) {

                    PcScreenDatabaseHandler pcScreenDatabaseHandler=new PcScreenDatabaseHandler(getApplicationContext());
                    pcScreenDatabaseHandler.deleteAllPcScreens();
                    PcScreen pcScreen =new PcScreen();
                    pcScreen.setId(1);
                    pcScreen.setScreenSizeInches(Integer.parseInt(pcScreenDialogEditText.getText().toString()));

                    pcScreenDatabaseHandler.addPcScreen(pcScreen);

                    Toast.makeText(MainActivity.this,"Pc screen add to cart",Toast.LENGTH_LONG).show();
                    pcScreenDialog.dismiss();
                } else {
                    pcScreenDialogTextInputLayout.setError("must not be empty");
                }

            });
            cancelPcScreenButton = view.findViewById(R.id.cancelButtonPcScreenDialog);
            cancelPcScreenButton.setOnClickListener(v -> {
                pcScreenDialog.dismiss();
            });

    }

    private void selectPersonalComputerDialog(View view) {

            personalComputerDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            view = getLayoutInflater().inflate(R.layout.select_personal_computer_dialog, null);
            personalComputerDialogBuilder.setView(view);
            personalComputerDialog= personalComputerDialogBuilder.create();
            personalComputerDialog.show();

            memoryGbPersonalComputerTextInputLayout = view.findViewById(R.id.memoryGbPersonalComputerTextInputLayout);
            cpuFrequencyGhzPersonalComputerTextInputLayout = view.findViewById(R.id.cpuFrequencyPersonalComputerTextInputLayout);
            pcScreenPersonalComputerTextInputLayout=view.findViewById(R.id.pcScreenInchesPersonalComputerDialogTextInputLayout);
            hardDiskPersonalComputerTextInputLayout=view.findViewById(R.id.hardDiskPersonalComputerDialogTextInputLayout);

            memoryGbPersonalComputerTextInputLayout.setHint(getResources().getString(R.string.memory_gb));
            cpuFrequencyGhzPersonalComputerTextInputLayout.setHint(getResources().getString(R.string.cpu_frequency_in_Hz));
            pcScreenPersonalComputerTextInputLayout.setHint(getResources().getString(R.string.pc_screen_inches));
            hardDiskPersonalComputerTextInputLayout.setHint(getResources().getString(R.string.hard_disk_gb));

            memoryGbPersonalComputerEditText = view.findViewById(R.id.memoryGbPersonalComputerEditText);
            cpuFrequencyGhzPersonalComputerEditText = view.findViewById(R.id.cpuFrequencyPersonalComputerEditText);
            pcScreenPersonalComputerDialogEditText=view.findViewById(R.id.pcScreenPersonalComputerDialogEditText);
            hardDiskPersonalComputerEditText=view.findViewById(R.id.hardDiskPersonalComputerDialogEditText);

            addToCartPersonalComputerButton = view.findViewById(R.id.addToCartPersonalComputerDialog);
            addToCartPersonalComputerButton.setOnClickListener(v -> {

                if(!memoryGbPersonalComputerEditText.getText().toString().trim().isEmpty()&&
                !cpuFrequencyGhzPersonalComputerEditText.getText().toString().isEmpty()&&
                !pcScreenPersonalComputerDialogEditText.getText().toString().isEmpty()&&
                !hardDiskPersonalComputerEditText.getText().toString().isEmpty()){

                    PersonalComputerDatabaseHandler personalComputerDatabaseHandler=new PersonalComputerDatabaseHandler(getApplicationContext());
                    personalComputerDatabaseHandler.deleteAllPersonalComputers();

                    PersonalComputer personalComputer=new PersonalComputer();
                    personalComputer.setId(1);
                    personalComputer.setMemoryGb(Integer.parseInt(memoryGbPersonalComputerEditText.getText().toString()));
                    personalComputer.setCpuFrequency(Double.parseDouble(cpuFrequencyGhzPersonalComputerEditText.getText().toString()));
                    personalComputer.setScreenSizeInches(Integer.parseInt(pcScreenPersonalComputerDialogEditText.getText().toString()));
                    personalComputer.setHardDiskGB(Integer.parseInt(hardDiskPersonalComputerEditText.getText().toString()));

                    personalComputerDatabaseHandler.addPersonalComputer(personalComputer);

                    Toast.makeText(MainActivity.this,"Personal computer add to cart",Toast.LENGTH_LONG).show();
                    personalComputerDialog.dismiss();
                }
                else{
                    memoryGbPersonalComputerTextInputLayout.setError("must not be empty");
                    cpuFrequencyGhzPersonalComputerTextInputLayout.setError("must not be empty");
                    pcScreenPersonalComputerTextInputLayout.setError("must not be empty");
                    hardDiskPersonalComputerTextInputLayout.setError("must not be empty");
                }
            });

            cancelPersonalComputerButton = view.findViewById(R.id.cancelButtonPersonalComputerDialog);
            cancelPersonalComputerButton.setOnClickListener(v -> personalComputerDialog.dismiss());

    }

    private void selectWorkStationDialog(View view) {



            workstationDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            view = getLayoutInflater().inflate(R.layout.select_workstation_dialog, null);
            workstationDialogBuilder.setView(view);
            workstationDialog= workstationDialogBuilder.create();
            workstationDialog.show();

            memoryGbWorkstationTextInputLayout = view.findViewById(R.id.memoryGbWorkstationTextInputLayout);
            cpuFrequencyGhzWorkstationTextInputLayout = view.findViewById(R.id.cpuFrequencyWorkstationTextInputLayout);
            pcScreenWorkstationTextInputLayout=view.findViewById(R.id.pcScreenInchesWorkstationDialogTextInputLayout);
            hardDiskWorkstationTextInputLayout=view.findViewById(R.id.hardDiskWorkstationDialogTextInputLayout);
            windowsradioButton=view.findViewById(R.id.windowsWorkstation);
            linuxRadioButton=view.findViewById(R.id.linuxWorkstation);

            memoryGbWorkstationTextInputLayout.setHint(getResources().getString(R.string.memory_gb));
            cpuFrequencyGhzWorkstationTextInputLayout.setHint(getResources().getString(R.string.cpu_frequency_in_Hz));
            pcScreenWorkstationTextInputLayout.setHint(getResources().getString(R.string.pc_screen_inches));
            hardDiskWorkstationTextInputLayout.setHint(getResources().getString(R.string.hard_disk_gb));

            memoryGbWorkstationEditText = view.findViewById(R.id.memoryGbWorkstaionEditText);
            cpuFrequencyGhzWorkstationEditText = view.findViewById(R.id.cpuFrequencyWorkstationEditText);
            pcScreenWorkstationDialogEditText=view.findViewById(R.id.pcScreenWorkstationDialogEditText);
            hardDiskWorkstationEditText=view.findViewById(R.id.hardDiskWorkstationDialogEditText);

            addToCartWorkstationButton = view.findViewById(R.id.addToCartWorkstationDialog);
            addToCartWorkstationButton.setOnClickListener(v -> {

                if(!memoryGbWorkstationEditText.getText().toString().trim().isEmpty()&&
                !cpuFrequencyGhzWorkstationEditText.getText().toString().trim().isEmpty()
                &&!pcScreenWorkstationDialogEditText.getText().toString().trim().isEmpty()
                &&!hardDiskWorkstationEditText.getText().toString().trim().isEmpty()
                &&(windowsradioButton.isChecked()||linuxRadioButton.isChecked())){

                    WorkstationDatabaseHandler workstationDatabaseHandler=new WorkstationDatabaseHandler(getApplicationContext());
                    workstationDatabaseHandler.deleteAllWorkstations();

                    WorkStation workStation=new WorkStation();
                    workStation.setMemoryGb(Integer.parseInt(memoryGbWorkstationEditText.getText().toString()));
                    workStation.setCpuFrequency(Double.parseDouble(cpuFrequencyGhzWorkstationEditText.getText().toString()));
                    workStation.setScreenSizeInches(Integer.parseInt(pcScreenWorkstationDialogEditText.getText().toString()));
                    workStation.setHardDiskGB(Integer.parseInt(hardDiskWorkstationEditText.getText().toString()));
                    if(windowsradioButton.isChecked()){
                        workStation.setOperatingSystem("windows");
                    }
                    if(linuxRadioButton.isChecked()){
                        workStation.setOperatingSystem("linux");
                    }
                    workstationDatabaseHandler.addWorkstation(workStation);

                    Toast.makeText(MainActivity.this,"Workstation add to cart",Toast.LENGTH_LONG).show();
                    workstationDialog.dismiss();
                }
                else{

                    memoryGbWorkstationTextInputLayout.setError("must not be empty");
                    cpuFrequencyGhzWorkstationTextInputLayout.setError("must not be empty");
                    pcScreenWorkstationTextInputLayout.setError("must not be empty");
                    hardDiskWorkstationTextInputLayout.setError("must not be empty");
                }
            });
            cancelWorkstationButton = view.findViewById(R.id.cancelButtonWorkstationDialog);
            cancelWorkstationButton.setOnClickListener(v -> workstationDialog.dismiss());

    }
}