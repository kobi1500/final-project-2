package com.example.sapir.shapeit;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.res.Configuration;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * this class is the main class that runs the entire application.
 * all actions between screens and screens, all functionalities are all operated in this class.
 */
public class MainActivity extends AppCompatActivity {
    //---------------------------------------------------------------------------------------------
    //constant variables
    private String MESSAGE = "Debug Message: ";
    private final int LOGIN = 1;
    private final int RESET_PASSWORD = 2;
    private final int MAIN = 3;
    private final int PERSONAL_AREA = 4;
    private final int PERSONAL_DETAILS = 5;
    private final int UPDATE_DETAILS = 6;
    private final int PERSONAL_PROGRESS = 7;
    private final int CALENDAR = 8;
    private final int NOTIFICATION = 9;
    private final int NAVIGATE = 10;
    private final int MESSAGENOTIFICATION = 11;


    // variable of count
    private int Screen = 0;

    //variables of widgets
    EditText username;
    EditText password;
    EditText email;
    EditText updateEmail;
    EditText updatePassword;
    EditText updateHeight;
    EditText updateWeight;

    CalendarView calendarView;

    private ArrayList<Calendar> CalendarList;
    private RecyclerView recyclerView;
    private AdapterCv mAdapter;

    ListView listv;

    LinearLayout login;
    LinearLayout main;
    LinearLayout Calendar;
    LinearLayout PersonalAreaPage;
    LinearLayout Personal_Detail;
    LinearLayout updatDetails;
    LinearLayout navigate;
    LinearLayout ResetPassword;
    LinearLayout NotificationPage;
    LinearLayout Message;
    LinearLayout PersonalProgress;

    Typeface tf;
    String id = null;

    CheckBox tvStayConnect;

    TextView tvCustomer;
    TextView tvPassword;
    TextView tvTimeTable;
    TextView tvLocation;
    TextView tvNotification;
    TextView tvPersonalArea;
    TextView tvHelloUser;
    TextView tvName;
    TextView tvName1;
    TextView tvLesName;
    TextView tvLesName1;
    TextView tvId;
    TextView tvId1;
    TextView tvMail;
    TextView tvMail1;
    TextView tvTypeOfSub;
    TextView tvTypeOfSub1;
    TextView tvsSubBalance;
    TextView tvsSubBalance1;
    TextView tvSubValidity;
    TextView tvSubValidity1;
    TextView tvTitle;
    TextView tvHeight;
    TextView tvHeight1;
    TextView tvWeight;
    TextView tvWeight1;
    TextView tvWeightUpdate;
    TextView tvHightUpdate;
    TextView tvMailUpdate;
    TextView tvPassUpdate;
    TextView tvTitleUpdate;
    TextView tvtitleLocation;
    TextView tvAddress;
    TextView tvTitleResetPssword;
    TextView tvInputEmail;
    TextView tvRPassword;
    TextView tvNotificationTitle;
    TextView tvtitleNotification;
    TextView tvMessage;


    Button tvEnter;
    Button tvRegister;
    Button tvResetPass;
    Button tvPersonalDetail;
    Button tvProgress;
    Button tvUpdateDetail;
    Button tvSave;
    Button tvLogOut;
    Button tvAprovalButton;

    ImageButton waze;
    ImageButton Personal_Area;
    ImageButton Calendar_btn;
    ImageButton Navigate;
    ImageButton Notification;

    IntentFilter intentFilter;
    PHP php = null;

    //---------------------------------------------------------------------------------------------

    /**
     * the central function that runs the entire application.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.d(MESSAGE, "onCreate - START");

        getSupportActionBar().hide();
        php = new PHP();

        CalendarList = new ArrayList<>();
        CalendarList.add(new Calendar("", ""));
        CalendarList.add(new Calendar("", ""));

        findViews();
        setFont();
        showLoginScreen();
        MainListener();
        CalendarListener();
        PersonalAreaListener();
        detailsListener();
        UpdateDetailsListener();
        NavigateListener();
        ResetPasswordListener();
        NotificationListener();
        GetPassword();
        setLanguage();
        GoToWaze();
        ListViewListener();
        PersonalProgressListener();
        LogOut();
        setcalendar();


        Log.d(MESSAGE, "onCreate - END");
    }


    //---------------------------------------------------------------------------------------------

    /**
     * this function initializes all the widget variables to view the widgets defined in xml.
     */
    private void findViews() {
        Log.d(MESSAGE, "findViews - START");

        tf = Typeface.createFromAsset(getAssets(), "secular_one.ttf");
        username = (EditText) findViewById(R.id.us_n);
        password = (EditText) findViewById(R.id.pass);
        email = (EditText) findViewById(R.id.emailAddress);
        updateEmail = (EditText) findViewById(R.id.eM_update);
        updatePassword = (EditText) findViewById(R.id.pass_update);
        updateHeight = (EditText) findViewById(R.id.Height_update);
        updateWeight = (EditText) findViewById(R.id.Weight_update);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new AdapterCv(CalendarList);
        recyclerView.setAdapter(mAdapter);

        login = (LinearLayout) findViewById(R.id.login);
        main = (LinearLayout) findViewById(R.id.main);
        Calendar = (LinearLayout) findViewById(R.id.Calendar);
        PersonalAreaPage = (LinearLayout) findViewById(R.id.personalAreaPage);
        Personal_Detail = (LinearLayout) findViewById(R.id.Personal_Detail);
        updatDetails = (LinearLayout) findViewById(R.id.updateDetails);
        navigate = (LinearLayout) findViewById(R.id.navigate);
        ResetPassword = (LinearLayout) findViewById(R.id.resetPassword);
        NotificationPage = (LinearLayout) findViewById(R.id.notificationPage);
        Message = (LinearLayout) findViewById(R.id.layout_notification);
        PersonalProgress = (LinearLayout) findViewById(R.id.PersonalProgress);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        tvRegister = (Button) findViewById(R.id.register);

        tvCustomer = (TextView) findViewById(R.id.customer_name);
        tvPassword = (TextView) findViewById(R.id.password);
        tvStayConnect = (CheckBox) findViewById(R.id.stay_connect);
        tvEnter = (Button) findViewById(R.id.enter);
        tvResetPass = (Button) findViewById(R.id.reset_Password);
        tvTimeTable = (TextView) findViewById(R.id.TimeTable);
        tvNotification = (TextView) findViewById(R.id.notification1);
        tvPersonalArea = (TextView) findViewById(R.id.personalarea);
        tvHelloUser = (TextView) findViewById(R.id.hello_user);
        tvPersonalDetail = (Button) findViewById(R.id.personal_detail);
        tvProgress = (Button) findViewById(R.id.progress);
        tvUpdateDetail = (Button) findViewById(R.id.update_detail);
        tvSave = (Button) findViewById(R.id.save);
        tvName = (TextView) findViewById(R.id.name);
        tvName1 = (TextView) findViewById(R.id.name1);
        tvLesName = (TextView) findViewById(R.id.lesName);
        tvLesName1 = (TextView) findViewById(R.id.lesName1);
        tvId = (TextView) findViewById(R.id.C_id);
        tvId1 = (TextView) findViewById(R.id.C_id1);
        tvMail = (TextView) findViewById(R.id.mail);
        tvMail1 = (TextView) findViewById(R.id.mail1);
        tvTypeOfSub = (TextView) findViewById(R.id.type_of_subscription);
        tvTypeOfSub1 = (TextView) findViewById(R.id.type_of_subscription1);
        tvsSubBalance = (TextView) findViewById(R.id.subscription_balance);
        tvsSubBalance1 = (TextView) findViewById(R.id.subscription_balance1);
        tvSubValidity = (TextView) findViewById(R.id.subscription_validity);
        tvSubValidity1 = (TextView) findViewById(R.id.subscription_validity1);
        tvTitle = (TextView) findViewById(R.id.title);
        tvHeight = (TextView) findViewById(R.id.height);
        tvHeight1 = (TextView) findViewById(R.id.height1);
        tvWeight = (TextView) findViewById(R.id.weight);
        tvWeight1 = (TextView) findViewById(R.id.weight1);
        tvLocation = (TextView) findViewById(R.id.navigate_title);
        tvHightUpdate = (TextView) findViewById(R.id.height_update);
        tvWeightUpdate = (TextView) findViewById(R.id.weight_update);
        tvMailUpdate = (TextView) findViewById(R.id.email_update);
        tvPassUpdate = (TextView) findViewById(R.id.password_update);
        tvTitleUpdate = (TextView) findViewById(R.id.UpdateTitle);
        tvtitleLocation = (TextView) findViewById(R.id.navigate_title);
        tvAddress = (TextView) findViewById(R.id.address);
        tvLogOut = (Button) findViewById(R.id.logout);
        tvTitleResetPssword = (TextView) findViewById(R.id.title_resetPassword);
        tvInputEmail = (TextView) findViewById(R.id.input_email_address);
        tvRPassword = (TextView) findViewById(R.id.Password);
        tvAprovalButton = (Button) findViewById(R.id.Approval_Button);
        tvNotificationTitle = (TextView) findViewById(R.id.notification_title);
        tvtitleNotification = (TextView) findViewById(R.id.title_notification);
        tvMessage = (TextView) findViewById(R.id.message);

        listv = (ListView) findViewById(R.id.listv);


        waze = (ImageButton) findViewById(R.id.waze);
        Notification = (ImageButton) findViewById(R.id.notification);
        Personal_Area = (ImageButton) findViewById(R.id.personal_area);
        Calendar_btn = (ImageButton) findViewById(R.id.calendar);
        Navigate = (ImageButton) findViewById(R.id.navigate_btn);


        Log.d(MESSAGE, "findViews - END");
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function initializes all the widgets to a specified font.
     */
    private void setFont() {
        Log.d(MESSAGE, "setFont - START");
        tvCustomer.setTypeface(tf);
        tvPassword.setTypeface(tf);
        tvStayConnect.setTypeface(tf);
        tvEnter.setTypeface(tf);
        tvResetPass.setTypeface(tf);
        tvTimeTable.setTypeface(tf);
        tvNotification.setTypeface(tf);
        tvPersonalArea.setTypeface(tf);
        tvHelloUser.setTypeface(tf);
        tvPersonalDetail.setTypeface(tf);
        tvProgress.setTypeface(tf);
        tvUpdateDetail.setTypeface(tf);
        tvName.setTypeface(tf);
        tvName1.setTypeface(tf);
        tvLesName.setTypeface(tf);
        tvLesName1.setTypeface(tf);
        tvMail.setTypeface(tf);
        tvMail1.setTypeface(tf);
        tvTypeOfSub.setTypeface(tf);
        tvTypeOfSub1.setTypeface(tf);
        tvsSubBalance.setTypeface(tf);
        tvsSubBalance1.setTypeface(tf);
        tvSubValidity.setTypeface(tf);
        tvSubValidity1.setTypeface(tf);
        tvTitle.setTypeface(tf);
        tvId.setTypeface(tf);
        tvId1.setTypeface(tf);
        tvHeight1.setTypeface(tf);
        tvHeight.setTypeface(tf);
        tvWeight1.setTypeface(tf);
        tvWeight.setTypeface(tf);
        tvLocation.setTypeface(tf);
        tvHightUpdate.setTypeface(tf);
        tvWeightUpdate.setTypeface(tf);
        tvMailUpdate.setTypeface(tf);
        tvPassUpdate.setTypeface(tf);
        tvTitleUpdate.setTypeface(tf);
        tvSave.setTypeface(tf);
        tvtitleLocation.setTypeface(tf);
        tvAddress.setTypeface(tf);
        tvLogOut.setTypeface(tf);
        tvAprovalButton.setTypeface(tf);
        tvRPassword.setTypeface(tf);
        tvInputEmail.setTypeface(tf);
        tvTitleResetPssword.setTypeface(tf);
        tvNotificationTitle.setTypeface(tf);
        tvtitleNotification.setTypeface(tf);

        Log.d(MESSAGE, "setFont - END");
    }
    //--------------------------------------------------------------------------------------------

    /**
     * this function is a system function that activates the system button on the
     * phone to return to the previous screen.
     */
    @Override
    public void onBackPressed() {
        if (Screen == LOGIN) {
            finish();
        } else if (Screen == RESET_PASSWORD) {
            showLoginScreen();
        } else if (Screen == MAIN) {
            finish();
        } else if (Screen == PERSONAL_AREA) {
            showMainScreen();
        } else if (Screen == PERSONAL_DETAILS) {
            showPersonalAreaScreen();
        } else if (Screen == UPDATE_DETAILS) {
            showPersonalAreaScreen();
        } else if (Screen == PERSONAL_PROGRESS) {
            showPersonalAreaScreen();
        } else if (Screen == CALENDAR) {
            showMainScreen();
        } else if (Screen == NOTIFICATION) {
            showMainScreen();
        } else if (Screen == NAVIGATE) {
            showMainScreen();
        } else if (Screen == MESSAGENOTIFICATION) {
            showNotificationScreen();
        } else if (Screen == PERSONAL_PROGRESS) {
            showPersonalAreaScreen();
        }
    }

    //---------------------------------------------------------------------------------------------

    /**
     * This function shows the login screen.
     */
    private void showLoginScreen() {
        Screen = LOGIN;
        login.setVisibility(View.VISIBLE);
        main.setVisibility(View.GONE);
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the main screen.
     */
    public void showMainScreen() {
        Screen = MAIN;
        main.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);
        ResetPassword.setVisibility(View.GONE);


    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function activates the login screen.
     * this function checks the information entered: Username and password,
     * if correct The application goes to the main menu screen, but with incorrect details or an attempt to enter without
     * entering details, an appropriate error message is displayed.
     */
    public void MainListener() {
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String result = php.login(username.getText().toString(), password.getText().toString());

                    if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                        if (result.contains("pass")) {
//                            String filename=null;
//                            File file= new File(getApplicationContext().getFilesDir(),filename);
//
//                            filename = "myfile";
//                            String string = "Hello world!";
//                            FileOutputStream outputStream;
//
//                            try {
//                                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//                                outputStream.write(string.getBytes());
//                                outputStream.close();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                            if (result.split(",").length != 2) {
                                Toast.makeText(getApplicationContext(), "wrong content", Toast.LENGTH_LONG).show();
                                return;
                            }
                            id = result.split(",")[1];
                            if (id != null) {
                                SetName();
                                setListView();
                                GetCustomerDetails();
                                UpdateDetails();
                                showMainScreen();
                            } else {
                                Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_LONG).show();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "שם משתמש ו/או סיסמה לא נכונים!", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "אנא הזן שם משתמש וסיסמה!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the calendar screen.
     */
    public void showCalendarScreen() {
        Screen = CALENDAR;
        main.setVisibility(View.GONE);
        Calendar.setVisibility(View.VISIBLE);
        PersonalAreaPage.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Main Menu screen to the schedule screen.
     */
    public void CalendarListener() {
        Calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarScreen();
            }
        });
    }
    //---------------------------------------------------------------------------------------------

    /**
     * this function changes the language of the calendar into hebrew.
     */

    public void setLanguage() {
        String languageToLoad = "he"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    //--------------------------------------------------------------------------------------------

    /**
     * this function shows the PersonalArea screen.
     */
    public void showPersonalAreaScreen() {
        Screen = PERSONAL_AREA;
        main.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.VISIBLE);
    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Main Menu screen to the PersonalArea screen.
     */
    public void PersonalAreaListener() {
        Personal_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPersonalAreaScreen();
            }
        });
    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function shows the PersonalDetails screen.
     */
    public void showDetails() {
        Screen = PERSONAL_DETAILS;
        main.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.VISIBLE);

    }
    //--------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Personal Area screen to the PersonalDetails screen.
     */
    public void detailsListener() {
        tvPersonalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            }
        });

    }
    //--------------------------------------------------------------------------------------------

    /**
     * this function shows the Update Details screen.
     */
    public void showScreenUpdateDetail() {
        Screen = UPDATE_DETAILS;
        main.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.VISIBLE);
    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Personal Area screen to the UpdateDetails screen.
     */
    public void UpdateDetailsListener() {
        tvUpdateDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScreenUpdateDetail();
            }
        });
    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function shows the navigation screen.
     */
    public void showScreenNavigate() {
        Screen = NAVIGATE;
        main.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        navigate.setVisibility(View.VISIBLE);

    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Main Menu screen to the Navigation screen.
     */
    public void NavigateListener() {
        Navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScreenNavigate();
            }
        });

    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function activates the button on a navigation screen and one of two things will happen:
     * if the phone has waze the app will switch to the waze app and show the route to the location of the studio,
     * if no waze app will go to google play to download the waze app.
     */
    public void GoToWaze() {
        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Launch Waze to look for yifat:
                    String url = "https://waze.com/ul?q='קיבוץ יפעת";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // If Waze is not installed, open it in Google Play:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                    startActivity(intent);
                }
            }
        });
    }

    //--------------------------------------------------------------------------------------------

    /**
     * this function shows the Reset Password screen.
     */
    public void showScreenResetPassword() {
        Screen = RESET_PASSWORD;
        ResetPassword.setVisibility(View.VISIBLE);
        main.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        navigate.setVisibility(View.GONE);


    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Login Screen screen to the Reset Password screen.
     */
    public void ResetPasswordListener() {
        tvResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScreenResetPassword();
            }
        });


    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function activates the button on the password recovery screen: the trainee inserts his email and the
     * function checks that the email exists, so it returns the password if it does not display an error message.
     */
    public void GetPassword() {
        tvAprovalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String result = php.ResetPassword(email.getText().toString());
                    if (result.contains("pass")) {
                        tvRPassword.setText(result.split(",")[1]);
                    } else {
                        Toast.makeText(getApplicationContext(), "המייל לא נכון, אנא הכנס מייל נכון", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the alerts in the db table.
     * the alerts shown are for each individual trainee.
     * in addition, when you click the message header, you are moved to a screen that
     * displays the title and content of the message according to the title that you clicked.
     */
    public void setListView() {
        try {

            final String result = php.getnotificationbyid(id);
            final String[] items = result.split(",");
            ArrayList<String> titles = new ArrayList<>();

            final int TITLE = 1;
            final int MESSAGE = 2;

            for (int i = 0; i < items.length; i += 3) {
                titles.add(items[i + TITLE]);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, R.id.txtitem, titles.toArray(new String[titles.size()]));
            listv.setAdapter(adapter);

            listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tvtitleNotification.setText(items[TITLE + (3 * i)]);
                    tvMessage.setText(items[MESSAGE + (3 * i)]);
                    showMessage();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //---------------------------------------------------------------------------------------------

    /**
     * this function moves to the screen of the message itself after the message is clicked on the notifications screen.
     */
    public void ListViewListener() {
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMessage();

            }
        });
    }


    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the screen of the message.
     */
    public void showMessage() {
        Screen = MESSAGENOTIFICATION;
        Message.setVisibility(View.VISIBLE);
        NotificationPage.setVisibility(View.GONE);
        ResetPassword.setVisibility(View.GONE);
        main.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        navigate.setVisibility(View.GONE);
    }
    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the screen of the notifications screen.
     */
    public void showNotificationScreen() {
        Screen = NOTIFICATION;
        NotificationPage.setVisibility(View.VISIBLE);
        ResetPassword.setVisibility(View.GONE);
        main.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        navigate.setVisibility(View.GONE);

    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Main Menu screen to the Notification screen.
     */
    public void NotificationListener() {
        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationScreen();
            }
        });

    }
    //---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------

    /**
     * this function shows the name of the trainee on the screen of a personal area.
     */
    public void SetName() {
        try {

            String result = php.getcustomerdetails(id);

            if (result.contains("pass")) {
                tvHelloUser.setText("שלום" + result.split(",")[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function updates the trainee's information in db and displays an appropriate message if the update information is successful or not.
     * after the process the fields are reset.
     */
    public void UpdateDetails() {
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = php.updatecustomers(id, updateEmail.getText().toString(), updatePassword.getText().toString(), updateHeight.getText().toString(), updateWeight.getText().toString());
                    if (result.contains("pass")) {
                        Toast.makeText(getApplicationContext(), result.split(",")[1], Toast.LENGTH_LONG).show();
                        updateEmail.setText("");
                        updatePassword.setText("");
                        updateHeight.setText("");
                        updateWeight.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), result.split(",")[1], Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function displays the personal information of the trainee on the Personal Information screen.
     */
    public void GetCustomerDetails() {
        try {
            String result = php.getcustomerdetails(id);
            String[] sarResult = result.split(",");
            if (result.contains("pass")) {
                tvName1.setText(result.split(",")[1]);
                tvLesName1.setText(result.split(",")[2]);
                tvId1.setText(result.split(",")[3]);
                tvMail1.setText(result.split(",")[4]);
                tvHeight1.setText(result.split(",")[8]);
                tvWeight1.setText(result.split(",")[9]);
                tvTypeOfSub1.setText(result.split(",")[6]);
                tvsSubBalance1.setText(result.split(",")[10]);
                tvSubValidity1.setText(sarResult[sarResult.length - 1]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function exits the menu in the application and returns to the Login screen.
     * in addition it resets the fields of username and password.
     */
    public void LogOut() {
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = null;
                showLoginScreen();
                username.setText("");
                password.setText("");
            }
        });
    }

    //---------------------------------------------------------------------------------------------

    /**
     * this function shows the list of lessons that have been assigned according to the day's choice of hours.
     * the function shows the name of the lesson and the time in the system table in db.
     */
    public void setcalendar() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                String monthStr = String.valueOf(++month);
                String dayStr = String.valueOf(day);
                if (month < 10) {
                    monthStr = "0" + monthStr;
                }
                if (day < 10) {
                    dayStr = "0" + dayStr;
                }
                try {
                    CalendarList.clear();
                    String[] lessons = null;
                    String result = php.getlesson(year + "-" + monthStr + "-" + dayStr).trim();
                    if (result.contains("pass")) {
                        lessons = result.split(",");
                    }
                    if (lessons != null && lessons.length > 0) {
                        int LESSON_NAME = 2;
                        int TIME = 3;

                        for (int i = 0; i < lessons.length; i += 3) {

                            Calendar cal = new Calendar(lessons[i + LESSON_NAME], lessons[i + TIME]);
                            CalendarList.add(cal);
                        }
                        mAdapter = new AdapterCv(CalendarList);
                        recyclerView.setAdapter(mAdapter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAdapter = new AdapterCv(CalendarList);
                    recyclerView.setAdapter(mAdapter);
                }


            }
        });
    }
//---------------------------------------------------------------------------------------------

    /**
     * this function shows the screen of the Personal Progress screen.
     */

    public void ShowPersonalProgress() {
        Screen = PERSONAL_PROGRESS;
        PersonalProgress.setVisibility(View.VISIBLE);
        Message.setVisibility(View.GONE);
        NotificationPage.setVisibility(View.GONE);
        ResetPassword.setVisibility(View.GONE);
        main.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        Calendar.setVisibility(View.GONE);
        PersonalAreaPage.setVisibility(View.GONE);
        Personal_Detail.setVisibility(View.GONE);
        updatDetails.setVisibility(View.GONE);
        navigate.setVisibility(View.GONE);


    }
//---------------------------------------------------------------------------------------------

    /**
     * this function activates the button that moves the application from the Personal Area screen to the Personal Progress screen.
     */
    public void PersonalProgressListener() {
        tvProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPersonalProgress();
            }
        });


    }
}





