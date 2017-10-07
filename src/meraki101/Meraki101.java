/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meraki101;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.DateTimeStringConverter;
import javax.crypto.spec.SecretKeySpec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import Icons.standardElements;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.guigarage.responsive.ResponsiveHandler;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.print.PageLayout;
import javafx.stage.Popup;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import static java.lang.Integer.parseInt;
import javafx.application.Platform;
import javafx.collections.ObservableSet;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

/**
 *
 * @author Meraki
 */
public class Meraki101 extends Application {

    final double rem = Math.rint(new Text("").getLayoutBounds().getHeight());
    Label usernameNameLblTitle = new Label();
    PrinterClass PrinterClass = new PrinterClass();
    Printer printer;
    TextField hiddenBarcodex = new TextField();

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {

        //creating of the merakiBusinessDB if not exists
        merakiBusinessDBClass merakiDBObj = new merakiBusinessDBClass();
        merakiDBObj.createDataBase();
        busniessReg busniessReg = new busniessReg();
        standardElements se = new standardElements();
        //System.getProperty("user.dir");

        File receiptsFolder = new File(
                System.getProperty("user.dir") + "\\Receipts");
        if (!receiptsFolder.exists()) {
            receiptsFolder.mkdirs();
        }

        final Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        double gH = bounds.getHeight();
        double gW = bounds.getWidth();

        Login loginInstance = new Login();
        StackPane root = new StackPane();

        Scene scene = new Scene(root, gW * 0.96, gH * 0.9);
        if (gW <= 1025) {
            scene.getStylesheets().add(Meraki101.class.getResource(
                    "merakiStylesMd.css").toExternalForm());
        } else {
            scene.getStylesheets().add(Meraki101.class.getResource(
                    "merakiStyles.css").toExternalForm());
        }

        scene.getStylesheets().add("bootstrapfx.css");

        try {
            ArrayList<ArrayList<String>> businessDetail = merakiDBObj.processSQLGeneralSelect(
                    "SELECT * FROM businessdetails");
            if (businessDetail.isEmpty()) {
                StackPane root1 = new StackPane();
                root1.setId("root1");
                root1.getChildren().add(busniessReg.register());

                Scene scene1 = new Scene(root1, gW * 0.3, gH * 0.6);
                scene1.getStylesheets().add(Meraki101.class.getResource(
                        "merakiStyles.css").toExternalForm());
                scene1.getStylesheets().add("bootstrapfx.css");
                //primaryStage.resizableProperty().setValue(Boolean.FALSE);
                primaryStage.setTitle("Meraki-Registration");
                primaryStage.getIcons().add(se.image2("meraki.jpg"));
                primaryStage.setScene(scene1);
                ///responsiveFX
                ResponsiveHandler.addResponsiveToWindow(primaryStage);
                primaryStage.show();

            } else {
                root.getChildren().addAll(loginInstance.loginVBox());

                IdleMonitor idleMonitor = new IdleMonitor(Duration.minutes(5),
                        () -> root.getChildren().setAll(
                                loginInstance.loginVBox()), true);
                idleMonitor.register(scene, Event.ANY);

                primaryStage.setTitle("Meraki-" + businessDetail.get(0).get(1));
                primaryStage.getIcons().add(se.image2("meraki.jpg"));
                primaryStage.setScene(scene);
                ///responsiveFX
                ResponsiveHandler.addResponsiveToWindow(primaryStage);
                //primaryStage.resizableProperty().setValue(Boolean.FALSE);
                primaryStage.show();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

    }

    //////business registration form
    public class busniessReg {

        public GridPane register() {

            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();
            ///main grid pane
            GridPane registrationGP = new GridPane();
            registrationGP.setId("registrationGP");

            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            CryptoUtil CryptoUtil = new CryptoUtil();

            ///Business name label and TextField
            Label businessNameLbl = new Label("Business Name: ");
            businessNameLbl.setId("reg1");
            UpperCaseTextField businessNameTxtFld = new UpperCaseTextField();
            businessNameTxtFld.setId("reg1");
            businessNameTxtFld.getStyleClass().add("text-success");

            ///Proprietor's firstname name label and TextField
            Label firstnameLbl = new Label("Proprietor's First Name: ");
            firstnameLbl.setId("reg1");
            UpperCaseTextField firstnameTxtFld = new UpperCaseTextField();
            firstnameTxtFld.setId("reg1");
            firstnameTxtFld.getStyleClass().add("text-success");

            ///Proprietor's other names label and TextField
            Label othernamesLbl = new Label("Proprietor's Other Names: ");
            othernamesLbl.setId("reg1");
            UpperCaseTextField othernamesnameTxtFld = new UpperCaseTextField();
            othernamesnameTxtFld.setId("reg1");
            othernamesnameTxtFld.getStyleClass().add("text-success");

            ///Proprietor's phone number  label and TextField
            Label contactLbl = new Label("Phone Number: ");
            contactLbl.setId("reg1");
            PhoneNumberTextField contactTxtFld = new PhoneNumberTextField();
            contactTxtFld.setId("reg1");
            contactTxtFld.getStyleClass().add("text-warning");
            contactTxtFld.textProperty().addListener((ov, oldval, newValue) -> {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                try {
                    PhoneNumber number = phoneUtil.parse(newValue, "US");
                    contactTxtFld.setText(phoneUtil.format(number,
                            PhoneNumberFormat.NATIONAL));
                } catch (NumberParseException e) {

                }
            });

            ///Proprietor's username  label and TextField
            Label emailLbl = new Label("E-mail: ");
            emailLbl.setId("reg1");
            LowerCaseTextField emailTxtFld = new LowerCaseTextField();
            emailTxtFld.setId("reg1");
            emailTxtFld.getStyleClass().add("text-success");

            ///Proprietor's username  label and TextField
            Label usernameLbl = new Label("Username: ");
            usernameLbl.setId("reg1");
            LowerCaseTextField usernameTxtFld = new LowerCaseTextField();
            usernameTxtFld.setId("reg1");
            usernameTxtFld.getStyleClass().add("text-success");

            ///Proprietor's username  label and TextField
            Label passwordLbl = new Label("Password: ");
            passwordLbl.setId("reg1");
            PasswordField passwrdFld = new PasswordField();
            passwrdFld.setId("reg1");
            passwrdFld.getStyleClass().add("text-success");

            ///Proprietor's username  label and TextField
            Label verifyPasswordLbl = new Label(" Verify Password: ");
            verifyPasswordLbl.setId("reg1");
            PasswordField verifyPasswrdFld = new PasswordField();
            verifyPasswrdFld.setId("reg1");
            verifyPasswrdFld.getStyleClass().add("text-success");

            ///submit button
            Button submitBtn = new Button("Register");
            submitBtn.getStyleClass().setAll("btn", "btn-info");
            submitBtn.setOnAction(e -> {
                if (!businessNameTxtFld.getText().isEmpty() && !firstnameTxtFld.getText().isEmpty() && !othernamesnameTxtFld.getText().isEmpty() && !contactTxtFld.getText().isEmpty() && !emailTxtFld.getText().isEmpty() && !usernameTxtFld.getText().isEmpty() && !passwrdFld.getText().isEmpty() && !verifyPasswrdFld.getText().isEmpty()) {
                    if (passwrdFld.getText().equals(verifyPasswrdFld.getText())) {
                        if (verifyPasswrdFld.getText().length() < 8) {
                            Alert passwordAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Password Should Be At Least 8 Characters!",
                                    ButtonType.OK);
                            passwordAlert.showAndWait();
                        } else if (verifyPasswrdFld.getText().matches(
                                "[a-z]*[A-Z]*")) {
                            Alert passwordAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Password Should Contain At Least One Letter!",
                                    ButtonType.OK);
                            passwordAlert.showAndWait();
                        } else if (verifyPasswrdFld.getText().matches("[0-9]*")) {
                            Alert passwordAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Password Should Contain At Least One Number!",
                                    ButtonType.OK);
                            passwordAlert.showAndWait();
                        } else {
                            try {
                                merakiBusinessDBClass.processSQLInsert(
                                        "INSERT INTO businessdetails (businessName, primaryContact, email, contact) VALUES('" + businessNameTxtFld.getText() + "', '" + firstnameTxtFld.getText() + " " + othernamesnameTxtFld.getText() + "', '" + emailTxtFld.getText() + "', '" + contactTxtFld.getText() + "')");

                                merakiBusinessDBClass.processSQLInsert(
                                        "INSERT INTO users (firstName, otherNames,"
                                        + "username, password, email, contact, isAdmin, createdBy, businessDetails_detailId, isCreator)"
                                        + "VALUES ('" + "MERAKI" + "', '" + "ADMIN" + "', "
                                        + "'" + "merakiadmin" + "', '" + CryptoUtil.encrypt(
                                                "merakiadmin123!?veron",
                                                "merakiveron") + "',"
                                        + "'" + "ayebalebj@gmail.com" + "', '" + "0705488941" + "', "
                                        + "'1', '" + "merakiadmin " + "', 1, 3)");
                                merakiBusinessDBClass.processSQLInsert(
                                        "INSERT INTO users (firstName, otherNames,"
                                        + "username, password, email, contact, isAdmin, createdBy, businessDetails_detailId, isCreator)"
                                        + "VALUES ('" + firstnameTxtFld.getText() + "', '" + othernamesnameTxtFld.getText() + "', "
                                        + "'" + usernameTxtFld.getText() + "', '" + CryptoUtil.encrypt(
                                        passwrdFld.getText(), "merakiveron") + "',"
                                        + "'" + emailTxtFld.getText() + "', '" + contactTxtFld.getPhoneNumber() + "', "
                                        + "'1', '" + "merakiadmin " + "', 1, 2)");

                                Alert registrationAlert = new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Registration Successful!\n" + "Please, Close This WIndow And Relaunch Application To Login!",
                                        ButtonType.OK);
                                registrationAlert.showAndWait();
                                businessNameTxtFld.clear();
                                businessNameTxtFld.setEditable(false);
                                firstnameTxtFld.clear();
                                firstnameTxtFld.setEditable(false);
                                othernamesnameTxtFld.clear();
                                othernamesnameTxtFld.setEditable(false);
                                usernameTxtFld.clear();
                                usernameTxtFld.setEditable(false);
                                emailTxtFld.clear();
                                emailTxtFld.setEditable(false);
                                contactTxtFld.clear();
                                contactTxtFld.setEditable(false);
                                passwrdFld.clear();
                                passwrdFld.setEditable(false);
                                verifyPasswrdFld.clear();
                                verifyPasswrdFld.setEditable(false);

                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        Alert registrationAlert = new Alert(
                                Alert.AlertType.INFORMATION,
                                "Passwords Do Not Match!", ButtonType.OK);
                        registrationAlert.showAndWait();
                    }
                } else {
                    Alert registrationAlert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "All Fields Are Required For Complete Registration!!",
                            ButtonType.OK);
                    registrationAlert.showAndWait();
                }
            });

            Label regTtl = new Label("Meraki Business Registation");
            regTtl.setMinWidth(gW * 0.3);
            regTtl.paddingProperty().set(new Insets(20));
            regTtl.setId("loginWelcome");
            regTtl.getStyleClass().add("lbl-info");

            registrationGP.setVgap(gH * 0.015);
            registrationGP.addRow(1, businessNameLbl, businessNameTxtFld);
            registrationGP.addRow(2, firstnameLbl, firstnameTxtFld);
            registrationGP.addRow(3, othernamesLbl, othernamesnameTxtFld);
            registrationGP.addRow(4, emailLbl, emailTxtFld);
            registrationGP.addRow(5, contactLbl, contactTxtFld);
            registrationGP.addRow(6, usernameLbl, usernameTxtFld);
            registrationGP.addRow(7, passwordLbl, passwrdFld);
            registrationGP.addRow(8, verifyPasswordLbl, verifyPasswrdFld);
            registrationGP.addRow(9, new Label(), submitBtn);

            GridPane mainRegGP = new GridPane();
            mainRegGP.getStyleClass().addAll("form-group");
            mainRegGP.setVgap(gH * 0.015);
            mainRegGP.addRow(0, regTtl);
            mainRegGP.addRow(1, registrationGP);

            return mainRegGP;
        }

    }

    //////---%$page Configuration classes
    public class Login {

        public BorderPane loginVBox() {

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            standardElements se = new standardElements();

            //password encryptor-descriptor
            CryptoUtil CryptoUtil = new CryptoUtil();
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            //----Login Design
            //---main BorderPane
            BorderPane borderPaneLogin = new BorderPane();
            //VBox Login Final Container
            VBox vboxLoginFinalContainer = new VBox();
            vboxLoginFinalContainer.setId("vboxLogin");

            //HBoxes for the username details and password details
            HBox hboxUserNameLblTextBox = new HBox();
            hboxUserNameLblTextBox.setId("hboxLogin1");
            HBox hboxPasswordLblPasswordBox = new HBox();
            hboxPasswordLblPasswordBox.setId("hboxLogin1");
            HBox hboxLoginBtn = new HBox();
            hboxLoginBtn.setId("hboxLogin2");

            //Labels for the username and the password and welcome
            Label loginWelcome = new Label("Welcome");
            loginWelcome.setId("loginWelcome");
            HBox hboxLoginWelcome = new HBox();
            hboxLoginWelcome.getChildren().add(loginWelcome);
            hboxLoginWelcome.setId("hbox27");

            Label userNameLbl = new Label("Username");
            userNameLbl.setId("loginLbl1");
            userNameLbl.getStyleClass().addAll("visible-lg", "visible-md",
                    "visible-sm");

            Label passwordLbl = new Label("Password");
            passwordLbl.setId("loginLbl1");
            passwordLbl.getStyleClass().addAll("visible-lg", "visible-md",
                    "visible-sm");

            //Username textField
            TextField userNameTextFld = new TextField();
            userNameTextFld.setPromptText("Your username ");
            userNameTextFld.setId("loginTxtFld1");
            userNameTextFld.getStyleClass().addAll("text-success", "visible-lg",
                    "visible-md", "visible-sm");

            //Password field
            PasswordField passwordFld = new PasswordField();
            passwordFld.setPromptText("Your password ");
            passwordFld.setId("loginTxtFld1");
            passwordFld.getStyleClass().addAll("text-success", "visible-lg",
                    "visible-md", "visible-sm");

            //Login button
            Button btnLogin = new Button("Login");
            btnLogin.setId("buttons");
            btnLogin.getStyleClass().addAll("btn", "btn-info", "visible-lg",
                    "visible-md", "visible-sm");

            btnLogin.setOnAction(e -> {
                //---place next page here

                try {
                    ArrayList<ArrayList<String>> users = merakiBusinessDBClass.processSQLGeneralSelect(
                            "SELECT * FROM users WHERE username = '" + userNameTextFld.getText() + "'");

                    if (!users.isEmpty()) {
                        if (passwordFld.getText().equals(CryptoUtil.decrypt(
                                users.get(0).get(4), "merakiveron"))) {
                            if (parseInt(users.get(0).get(7)) > 0) {
                                if (users.get(0).get(8).equals("1")) {
                                    borderPaneLogin.getChildren().clear();
                                    //required main page class  ----you have to replace this with real usernames and id
                                    mainPage mainPage = new mainPage(
                                            users.get(0).get(0),
                                            users.get(0).get(3));

                                    borderPaneLogin.setCenter(
                                            mainPage.mainpage());
                                } else if (users.get(0).get(8).equals("0")) {
                                    borderPaneLogin.getChildren().clear();
                                    //required main page class  ----you have to replace this with real usernames and id
                                    mainPage mainPage = new mainPage(
                                            users.get(0).get(0),
                                            users.get(0).get(3));
                                    borderPaneLogin.setCenter(
                                            mainPage.mainpageNonAdmin());
                                }

                            } else {
                                Alert invalidUserAlert = new Alert(
                                        Alert.AlertType.WARNING,
                                        "Invalid Username and Password!",
                                        ButtonType.OK);
                                invalidUserAlert.showAndWait();
                            }
                        } else {
                            Alert invalidUserAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Username and Password not Matching!!",
                                    ButtonType.OK);
                            invalidUserAlert.showAndWait();
                        }

                    } else {
                        Alert invalidUserAlert = new Alert(
                                Alert.AlertType.WARNING,
                                "Please Insert Username And Password",
                                ButtonType.OK);
                        invalidUserAlert.showAndWait();
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Throwable ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            );

            passwordFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        ArrayList<ArrayList<String>> users = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT * FROM users WHERE username = '" + userNameTextFld.getText() + "'");

                        if (!users.isEmpty()) {
                            if (passwordFld.getText().equals(CryptoUtil.decrypt(
                                    users.get(0).get(4), "merakiveron"))) {
                                if (parseInt(users.get(0).get(7)) > 0) {
                                    if (users.get(0).get(8).equals("1")) {
                                        borderPaneLogin.getChildren().clear();
                                        //required main page class  ----you have to replace this with real usernames and id
                                        mainPage mainPage = new mainPage(
                                                users.get(0).get(0),
                                                users.get(0).get(3));

                                        borderPaneLogin.setCenter(
                                                mainPage.mainpage());
                                    } else if (users.get(0).get(8).equals("0")) {
                                        borderPaneLogin.getChildren().clear();
                                        //required main page class  ----you have to replace this with real usernames and id
                                        mainPage mainPage = new mainPage(
                                                users.get(0).get(0),
                                                users.get(0).get(3));
                                        borderPaneLogin.setCenter(
                                                mainPage.mainpageNonAdmin());
                                    }

                                } else {
                                    Alert invalidUserAlert = new Alert(
                                            Alert.AlertType.WARNING,
                                            "Invalid Username and Password!",
                                            ButtonType.OK);
                                    invalidUserAlert.showAndWait();
                                }
                            } else {
                                Alert invalidUserAlert = new Alert(
                                        Alert.AlertType.WARNING,
                                        "Username and Password not Matching!!",
                                        ButtonType.OK);
                                invalidUserAlert.showAndWait();
                            }

                        } else {
                            Alert invalidUserAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Please Insert Username And Password",
                                    ButtonType.OK);
                            invalidUserAlert.showAndWait();
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    } catch (UsbException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Throwable ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            //placing the username label and textField and password details in their containers
            hboxUserNameLblTextBox.setId("hbox27");
            hboxUserNameLblTextBox.setSpacing(gW * 0.005);
            hboxUserNameLblTextBox.getChildren().addAll(userNameLbl,
                    userNameTextFld);

            hboxPasswordLblPasswordBox.setId("hbox27");
            hboxPasswordLblPasswordBox.setSpacing(gW * 0.007);
            hboxPasswordLblPasswordBox.getChildren().addAll(passwordLbl,
                    passwordFld);

            hboxLoginBtn.getChildren().add(btnLogin);

            vboxLoginFinalContainer.getChildren().addAll(hboxLoginWelcome,
                    hboxUserNameLblTextBox, hboxPasswordLblPasswordBox,
                    hboxLoginBtn);

            ///StackPane for returnong... because its the easiest way to add the image
            StackPane loginSP = new StackPane();
            loginSP.getChildren().addAll(se.image("Best.jpg", gW * 0.96,
                    gH * 0.85), vboxLoginFinalContainer);

            //setting the center for the BorderPane
            borderPaneLogin.setCenter(loginSP);

            ///---setting the bottmom for the BorderPane
            //maker's label
            Label makersLbl = new Label(
                    "Meraki101-a Ayebale Bright Johnson Product");
            HBox hboxForMaker = new HBox();
            hboxForMaker.setId("hbox3");
            hboxForMaker.getChildren().add(makersLbl);
            hboxForMaker.setAlignment(Pos.CENTER);
            borderPaneLogin.setBottom(hboxForMaker);

            return borderPaneLogin;
        }

    }

    public class mainPage {

        String userId;
        String username;

        //Constructor for username and Id
        public mainPage(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        //method get user id
        public String userId() {
            return this.userId;
        }

        //method get username
        public String username() {
            return this.username;
        }

        public BorderPane mainpage() throws UsbException, Throwable {

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            //prequisite classes
            records records = new records();

            Login loginPage = new Login();
            userPage userPage = new userPage(this.userId, this.username);
            salesPage salesPage = new salesPage();
            otherExpensesPage otherExpensesPage = new otherExpensesPage(
                    this.userId, this.username);
            standardElements se = new standardElements();
            //major BorderPane
            BorderPane borderPaneMJTemp = new BorderPane();

            ////---HBox for menu bar and logout
            HBox menuBarAndLogout = new HBox();
            menuBarAndLogout.setId("menuBarAndLogout");
            menuBarAndLogout.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");
            menuBarAndLogout.setSpacing(gW * 0.43);

            ////---hbox(allowance) for username and logout btn
            HBox hboxLoginCreds = new HBox();
            hboxLoginCreds.setId("hbox1");
            HBox hboxCredsLogout = new HBox();
            hboxCredsLogout.setId("hbox2");

            //username configs
            Label usernameDisplayLbl = new Label("User:");
            usernameDisplayLbl.setMinWidth(gW * 0.05);
            usernameDisplayLbl.setMaxWidth(gW * 0.055);

            usernameNameLblTitle.setText(username);
            usernameNameLblTitle.setPrefWidth(gW * 0.07);

            hboxLoginCreds.getChildren().addAll(usernameDisplayLbl,
                    usernameNameLblTitle);

            //---log out button
            Button logoutBtn = new Button("Logout");
            logoutBtn.setId("button1");
            logoutBtn.getStyleClass().addAll("btn-info", "visible-sm",
                    "visible-md", "visible-lg");

            //setting action event handler for the logout button
            logoutBtn.setOnAction(e -> {
                StackPane sp = new StackPane();
                sp.getChildren().addAll(se.image("Best.jpg", gW * 0.96,
                        gH * 0.85), loginPage.loginVBox());
                borderPaneMJTemp.getChildren().clear();
                borderPaneMJTemp.setCenter(sp);
            }
            );

            //container for credentials and logout button
            hboxCredsLogout.getChildren().addAll(hboxLoginCreds, logoutBtn);

            ////----Main menu and its configurations
            //menu bar
            MenuBar mainMenu = new MenuBar();
            mainMenu.setId("mainMenu1");
            mainMenu.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");

            // --- Menu Inventory
            Menu menuInventory = new Menu("Inventory");
            menuInventory.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");
            // --- Menu Sales
            Menu menuSales = new Menu("Sales");
            menuSales.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");
            // --- Menu Records
            Menu menuRecords = new Menu("Records");
            menuRecords.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");
            // --- Menu Records
            Menu menuTransactions = new Menu("Transactions");
            menuTransactions.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");
            // --- Menu Records
            Menu menuAdditionalExpenses = new Menu("Expenses");
            menuAdditionalExpenses.getStyleClass().addAll("visible-xs",
                    "visible-sm", "visible-md", "visible-lg");
            // --- Menu Records
            Menu menuUsers = new Menu("Accounts");
            menuUsers.getStyleClass().addAll("visible-xs", "visible-sm",
                    "visible-md", "visible-lg");

            ////---menu items for the menus
            //for inventory
            final MenuItem addInventory = new MenuItem("Add Stock");
            final MenuItem masterData = new MenuItem("Master Data");
            final MenuItem editInventory = new MenuItem("Edit Stock");

            //adding the items to their menus
            menuInventory.getItems().addAll(addInventory, editInventory,
                    masterData);

            //for sales
            final MenuItem viewSales = new MenuItem("View Sales");

            viewSales.setOnAction(e -> {
                try {
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(salesPage.viewEditSales());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
            );

            //adding the items to their menus
            menuSales.getItems().addAll(viewSales);

            //for records
            final MenuItem inventoryRecords = new MenuItem("Inventory Records");
            final MenuItem salesRecords = new MenuItem("Sales Records");
            final MenuItem otherExpensesRecords = new MenuItem(
                    "Expenses Records");

            ////---enu item selection item listeners
            //--for add Inventory
            addInventory.setOnAction((ActionEvent e) -> {
                try {
                    borderPaneMJTemp.setCenter(null);
                    inventory inventoryClass = new inventory(this.userId, this.username, hiddenBarcodex);
                    try {
                        borderPaneMJTemp.setCenter(inventoryClass.addInventory());
                    } catch (ClassNotFoundException | UsbException | UnsupportedEncodingException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                                null, ex);
                    }
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //--for edit Inventory
            editInventory.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                try {
                    inventory inventoryClass = new inventory(this.userId, this.username, hiddenBarcodex);
                    borderPaneMJTemp.setCenter(inventoryClass.editInventory());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //--for edit Inventory
            masterData.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                try {
                    inventory inventoryClass = new inventory(this.userId, this.username, hiddenBarcodex);
                    borderPaneMJTemp.setCenter(inventoryClass.masterData());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //--for Inventory Records
            inventoryRecords.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(records.inventoryRecords());
            });

            //for Sales Records
            salesRecords.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(records.salesRecords());
            });

            //for expenses record
            otherExpensesRecords.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(records.expensesRecord());
            });

            //adding the items to their menus
            menuRecords.getItems().addAll(otherExpensesRecords, inventoryRecords,
                    salesRecords);

            //for transactions
            final MenuItem packetTransactions = new MenuItem("Packet Sell");
            final MenuItem retailTransactions = new MenuItem("Retail Sell");
            final MenuItem wholeSaleTransactions = new MenuItem("Whole Sale");

            packetTransactions.setOnAction((ActionEvent e) -> {
                try {
                    sellPage sellPage = new sellPage(this.userId, this.username, hiddenBarcodex);
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.packetSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException | UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Throwable ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            retailTransactions.setOnAction((ActionEvent e) -> {
                try {
                    sellPage sellPage = new sellPage(this.userId, this.username, hiddenBarcodex);
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.retailSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            wholeSaleTransactions.setOnAction((ActionEvent e) -> {
                try {
                    sellPage sellPage = new sellPage(this.userId, this.username, hiddenBarcodex);
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.wholeSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //adding the items to their menus
            menuTransactions.getItems().addAll(packetTransactions,
                    retailTransactions, wholeSaleTransactions);

            //for transactions
            final MenuItem addExpense = new MenuItem("Add New");
            final MenuItem editExpense = new MenuItem("Edit Expense");

            addExpense.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(otherExpensesPage.addExpenses());
            });

            editExpense.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(otherExpensesPage.editExpense());
            });

            //adding the items to their menus
            menuAdditionalExpenses.getItems().addAll(addExpense, editExpense);

            //for users
            final MenuItem addUser = new MenuItem("Create User");
            final MenuItem editUser = new MenuItem("Edit User");

            addUser.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(userPage.addUserPage());
            });

            editUser.setOnAction((ActionEvent e) -> {
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(userPage.editUserPage());
            });

            //preload
            try {
                sellPage sellPage = new sellPage(this.userId, this.username, hiddenBarcodex);
                borderPaneMJTemp.setCenter(null);
                borderPaneMJTemp.setCenter(sellPage.packetSell());
            } catch (ClassNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            //adding the items to their menus
            menuUsers.getItems().addAll(addUser, editUser);

            mainMenu.getMenus().addAll(menuInventory, menuSales, menuRecords,
                    menuTransactions, menuAdditionalExpenses, menuUsers);

            //--- setting up the top of the PorderPane
            menuBarAndLogout.getChildren().addAll(mainMenu, hboxCredsLogout);

            ////----setting up the BorderPane
            //setting the menu as the BorderPane top
            borderPaneMJTemp.setTop(menuBarAndLogout);

            ///---setting the bottmom for the BorderPane
            //maker's label
            Label makersLbl = new Label(
                    "Meraki101-a Ayebale Bright Johnson Product");
            HBox hboxForMaker = new HBox();
            hboxForMaker.setId("hbox3");
            hboxForMaker.getChildren().add(makersLbl);
            hboxForMaker.setAlignment(Pos.CENTER);
            borderPaneMJTemp.setBottom(hboxForMaker);

            return borderPaneMJTemp;
        }

        public BorderPane mainpageNonAdmin() throws UsbException, UnsupportedEncodingException {

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            //prequisite classes
            sellPage sellPage = new sellPage(this.userId, this.username, hiddenBarcodex);
            Login loginPage = new Login();
            standardElements se = new standardElements();

            //major BorderPane
            BorderPane borderPaneMJTemp = new BorderPane();

            ////---HBox for menu bar and logout
            HBox menuBarAndLogout = new HBox();
            menuBarAndLogout.setId("menuBarAndLogout1");
            menuBarAndLogout.setSpacing(gW * 0.7);

            ////---hbox(allowance) for username and logout btn
            HBox hboxLoginCreds = new HBox();
            hboxLoginCreds.setId("hbox1");
            HBox hboxCredsLogout = new HBox();
            hboxCredsLogout.setId("hbox2");

            //username configs
            Label usernameDisplayLbl = new Label("User:");
            usernameDisplayLbl.setMinWidth(gW * 0.05);
            usernameDisplayLbl.setMaxWidth(gW * 0.055);

            usernameNameLblTitle.setText(this.username);
            usernameNameLblTitle.setPrefWidth(gW * 0.07);

            hboxLoginCreds.getChildren().addAll(usernameDisplayLbl,
                    usernameNameLblTitle);

            //---log out button
            Button logoutBtn = new Button("Logout");
            logoutBtn.setId("button1");
            logoutBtn.getStyleClass().add("btn-info");

            //setting action event handler for the logout button
            logoutBtn.setOnAction(e -> {
                StackPane sp = new StackPane();
                sp.getChildren().addAll(se.image("Best.jpg", gW * 0.96,
                        gH * 0.85), loginPage.loginVBox());
                borderPaneMJTemp.getChildren().clear();
                borderPaneMJTemp.setCenter(sp);
            }
            );

            //container for credentials and logout button
            hboxCredsLogout.getChildren().addAll(hboxLoginCreds, logoutBtn);

            ////----Main menu and its configurations
            //menu bar
            MenuBar mainMenu = new MenuBar();
            mainMenu.setId("mainMenu1");

            //for transactions
            final MenuItem packetTransactions = new MenuItem("Packet Sell");
            final MenuItem retailTransactions = new MenuItem("Retail Sell");
            final MenuItem wholeSaleTransactions = new MenuItem("Whole Sale");

            packetTransactions.setOnAction((ActionEvent e) -> {
                try {
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.packetSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException | UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Throwable ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            retailTransactions.setOnAction((ActionEvent e) -> {
                try {
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.retailSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                } catch (UsbException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            wholeSaleTransactions.setOnAction((ActionEvent e) -> {
                try {
                    borderPaneMJTemp.setCenter(null);
                    borderPaneMJTemp.setCenter(sellPage.wholeSell());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            });

            Menu menuTransactions = new Menu("Transactions");

            //adding the items to their menus
            menuTransactions.getItems().addAll(packetTransactions,
                    retailTransactions, wholeSaleTransactions);

            mainMenu.getMenus().addAll(menuTransactions);

            //--- setting up the top of the PorderPane
            menuBarAndLogout.getChildren().addAll(mainMenu, hboxCredsLogout);

            ////----setting up the BorderPane
            //setting the menu as the BorderPane top
            borderPaneMJTemp.setTop(menuBarAndLogout);

            ///---setting the bottmom for the BorderPane
            //maker's label
            Label makersLbl = new Label(
                    "Meraki101-a Ayebale Bright Johnson Product");
            HBox hboxForMaker = new HBox();
            hboxForMaker.setId("hbox3");
            hboxForMaker.getChildren().add(makersLbl);
            hboxForMaker.setAlignment(Pos.CENTER);
            borderPaneMJTemp.setBottom(hboxForMaker);

            return borderPaneMJTemp;
        }
    }

    public class inventory {

        String userId;
        String username;
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        TextField barcodeInput = null;
        TextField intermediateBarcode = null;
        TextField retialBarcode = null;

        public inventory(String userId, String username, TextField barcodeHiddenInput) throws UsbException, UsbException, UnsupportedEncodingException {
            this.userId = userId;
            this.username = username;
            this.barcodeInput = barcodeHiddenInput;

            BarcodeClass BarcodeClass = new BarcodeClass(this.barcodeInput);
            UsbServices services = (org.usb4java.javax.Services) UsbHostManager.getUsbServices();

            UsbHub root = services.getRootUsbHub();
            BarcodeClass.findDevice(root);

            barcodeInput.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String str = this.barcodeInput.getText();
                    Thread.sleep(500);
                    if (str.equals(this.barcodeInput.getText()) && !str.equals("")) {
                        if (this.intermediateBarcode != null) {
                            this.intermediateBarcode.setText(str);
                        }
                        if (this.retialBarcode != null) {
                            this.retialBarcode.setText(str);
                        }
                        this.barcodeInput.setText("");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }

        public String getUserId() {
            return this.userId;
        }

        public String getUsername() {
            return this.username;
        }

        public HBox addInventory() throws ClassNotFoundException, UsbException, UnsupportedEncodingException {
            ///--prerequisites
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            insertUpdateStockUser insertUpdateStockUser = new insertUpdateStockUser();
            chartYVal chartYVal = new chartYVal();

            ///barcode class
            this.intermediateBarcode = new TextField();

            ////for items remembering
            ArrayList<ArrayList<String>> inventoryMemory = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory");

            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ////----add inventory main page
            HBox hboxMainMain = new HBox();
            hboxMainMain.setId("hbox5");
            VBox vboxAddInventoryPage = new VBox();

            ////---center top part table for the items
            GridPane GPInventoryTable = new GridPane();
            HBox hboxTitleLbl = new HBox();
            hboxTitleLbl.setId("hbox4");

            GridPane InventoryTableGP = new GridPane();
            //the label
            Label tableTitleLbl = new Label("Add Inventory");

            hboxTitleLbl.getChildren().add(tableTitleLbl);

            //--table
            TableView<InventoryItemsData> InventoryItems = new TableView<>();
            ObservableList<InventoryItemsData> InventoryItemsItems = FXCollections.observableArrayList();
            InventoryItems.setEditable(false);
            //InventoryItems.setPrefHeight(gH * 0.65);
            InventoryItems.setItems(InventoryItemsItems);

            InventoryItems.getColumns().clear();

            TableColumn InventoryItems_Barcode_ID = new TableColumn("Barcode/ID");
            InventoryItems_Barcode_ID.setId("remainingClms");
            InventoryItems_Barcode_ID.setPrefWidth(gW * 0.08);
            InventoryItems_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>(
                            "Barcode_ID"));
            InventoryItems.getColumns().addAll(InventoryItems_Barcode_ID);

            TableColumn InventoryItems_Name = new TableColumn("Name");
            InventoryItems_Name.setId("remainingClms");
            InventoryItems_Name.setPrefWidth(gW * 0.1);
            InventoryItems_Name.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>("Name"));
            InventoryItems.getColumns().addAll(InventoryItems_Name);

            TableColumn InventoryItems_Description = new TableColumn(
                    "Description");
            InventoryItems_Description.setId("remainingClms");
            InventoryItems_Description.setPrefWidth(gW * 0.1);
            InventoryItems_Description.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>(
                            "Description"));
            InventoryItems.getColumns().addAll(InventoryItems_Description);

            TableColumn InventoryItems_Type = new TableColumn("Type");
            InventoryItems_Type.setId("remainingClms");
            InventoryItems_Type.setPrefWidth(gW * 0.1);
            InventoryItems_Type.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>("Type"));
            InventoryItems.getColumns().addAll(InventoryItems_Type);

            TableColumn InventoryItems_Units = new TableColumn("Units");
            InventoryItems_Units.setId("qtyCell");
            InventoryItems_Units.setPrefWidth(gW * 0.07);
            InventoryItems_Units.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double qty = parseDouble(p.getValue().Units.getValue());
                    String finalQty = df.format(qty);
                    return new SimpleObjectProperty(finalQty);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_Units);

            TableColumn InventoryItems_UnitQuantity = new TableColumn(
                    "UnitQuantity");
            InventoryItems_UnitQuantity.setId("qtyCell");
            InventoryItems_UnitQuantity.setPrefWidth(gW * 0.1);
            InventoryItems_UnitQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double qty = parseDouble(
                            p.getValue().UnitQuantity.getValue());
                    String finalQty = df.format(qty);
                    return new SimpleObjectProperty(finalQty);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_UnitQuantity);

            TableColumn InventoryItems_PurchasePrice = new TableColumn(
                    "PurchasePrice");
            InventoryItems_PurchasePrice.setPrefWidth(gW * 0.1);
            InventoryItems_PurchasePrice.setId("moneyCell");
            InventoryItems_PurchasePrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(
                            p.getValue().PurchasePrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_PurchasePrice);

            TableColumn InventoryItems_SellingPrice = new TableColumn(
                    "SellingPrice");
            InventoryItems_SellingPrice.setId("moneyCell");
            InventoryItems_SellingPrice.setPrefWidth(gW * 0.1);
            InventoryItems_SellingPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(
                            p.getValue().SellingPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_SellingPrice);

            InventoryItemsItems.clear();
            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0;");
            selectInventoryItems.stream().forEach((selectInventoryItem) -> {
                InventoryItemsItems.add(new InventoryItemsData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(6), selectInventoryItem.get(7),
                        selectInventoryItem.get(8), selectInventoryItem.get(9)));
            });

            InventoryTableGP.addRow(0, InventoryItems);

            /////table part finally coming together
            GPInventoryTable.addRow(0, hboxTitleLbl);
            GPInventoryTable.addRow(1, InventoryTableGP);

            ////---- fully configuring the lower part of add new inventory
            //container for all lower part items
            VBox vboxLowerPartContainer = new VBox();
            vboxLowerPartContainer.setId("vbox1");

            //Hbox for the lower part identification with the lower part title label
            HBox hboxLowPartLbl = new HBox();
            hboxLowPartLbl.setId("hbox4");

            //title label
            Label lowPartTitleLbl = new Label("Add New Item(s)");

            hboxLowPartLbl.getChildren().add(lowPartTitleLbl);

            //hbox for the low part middle section
            //HBoxes for the middle descriptors
            //for unique id
            //the label
            Label uniqueIdLbl = new Label("Barcode/ID:");
            uniqueIdLbl.setWrapText(true);
            //textfield
            TextField uniqueIdTxtFld = new TextField();

            //for item name
            //the label
            Label itemNameLbl = new Label("Item Name:");
            itemNameLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemNameTxtFld = new UpperCaseTextField();

            //for item description
            //the label
            Label itemDecsriptionLbl = new Label("Description:");
            itemDecsriptionLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemDescriptionTxtFld = new UpperCaseTextField();

            //for item type
            //the label
            Label itemTypeLbl = new Label("Type:");
            itemTypeLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemTypeTxtFld = new UpperCaseTextField();

            //hbox for the low part middle section
            //HBoxes for the middle Low descriptors
            //for unique id
            //the label
            Label quantityLbl = new Label("Units:");
            quantityLbl.setWrapText(true);
            //the text area
            DecimalTextField quantityTxtFld = new DecimalTextField();

            //for unit quantity
            //the label
            Label unitQtyLbl = new Label("Unit Quantity:");
            unitQtyLbl.setWrapText(true);
            //the text Area
            DecimalTextField unitQtyTxtFld = new DecimalTextField();

            //for item description
            //the label
            Label purchasePriceLbl = new Label("Purchase Price:");
            purchasePriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField purchasePriceTxtFld = new DecimalTextField();

            //for Sale price
            //the label
            Label sellingPriceLbl = new Label("Selling Price:");
            sellingPriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField salePriceTxtFld = new DecimalTextField();

            //for the save/add/input button
            //the Button
            Button saveItemBtn = new Button("Add");
            saveItemBtn.getStyleClass().add("btn-info");

            GridPane upGP = new GridPane();
            upGP.setVgap(gH * 0.001);
            upGP.addRow(0, uniqueIdLbl, uniqueIdTxtFld, itemNameLbl,
                    itemNameTxtFld, itemDecsriptionLbl, itemDescriptionTxtFld,
                    itemTypeLbl, itemTypeTxtFld);
            upGP.addRow(1, quantityLbl, quantityTxtFld, unitQtyLbl,
                    unitQtyTxtFld, purchasePriceLbl, purchasePriceTxtFld,
                    sellingPriceLbl, salePriceTxtFld);
            upGP.addRow(2, new Label(), new Label(), new Label(), new Label(),
                    new Label(), new Label(), new Label(), saveItemBtn);

            uniqueIdTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
                for (int x = 0; x < inventoryMemory.size(); x++) {
                    if (!uniqueIdTxtFld.getText().isEmpty()) {
                        if (uniqueIdTxtFld.getText().equals(inventoryMemory.get(
                                x).get(1))) {
                            itemNameTxtFld.setText(inventoryMemory.get(x).get(2));
                            itemDescriptionTxtFld.setText(
                                    inventoryMemory.get(x).get(3));
                            itemTypeTxtFld.setText(inventoryMemory.get(x).get(4));
                            purchasePriceTxtFld.setText(
                                    inventoryMemory.get(x).get(8));
                            salePriceTxtFld.setText(
                                    inventoryMemory.get(x).get(9));
                            break;
                        }
                    } else {
                        itemNameTxtFld.clear();
                        itemDescriptionTxtFld.clear();
                        itemTypeTxtFld.clear();
                        purchasePriceTxtFld.clear();
                        salePriceTxtFld.clear();
                    }
                }
            });

            this.intermediateBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                uniqueIdTxtFld.setText(intermediateBarcode.getText());
            });

            saveItemBtn.setOnAction(e -> {
                try {
                    merakiBusinessDBClass.processSQLInsert(
                            insertUpdateStockUser.insertItem(
                                    uniqueIdTxtFld.getText(),
                                    itemNameTxtFld.getText(),
                                    itemDescriptionTxtFld.getText(),
                                    itemTypeTxtFld.getText(),
                                    quantityTxtFld.getDecimal(),
                                    unitQtyTxtFld.getDecimal(),
                                    purchasePriceTxtFld.getDecimal(),
                                    salePriceTxtFld.getDecimal()));
                    uniqueIdTxtFld.clear();
                    itemNameTxtFld.clear();
                    itemDescriptionTxtFld.clear();
                    itemTypeTxtFld.clear();
                    quantityTxtFld.clear();
                    unitQtyTxtFld.clear();
                    purchasePriceTxtFld.clear();
                    salePriceTxtFld.clear();

                    InventoryItemsItems.clear();
                    ArrayList<ArrayList<String>> selectInventoryItems1 = merakiBusinessDBClass.processSQLSelect(
                            "SELECT * FROM inventory WHERE remainingQuantity > 0;");
                    selectInventoryItems1.stream().forEach(
                            (selectInventoryItems11) -> {
                                InventoryItemsItems.add(new InventoryItemsData(
                                        selectInventoryItems11.get(1),
                                        selectInventoryItems11.get(2),
                                        selectInventoryItems11.get(3),
                                        selectInventoryItems11.get(4),
                                        selectInventoryItems11.get(6),
                                        selectInventoryItems11.get(7),
                                        selectInventoryItems11.get(8),
                                        selectInventoryItems11.get(9)));
                            });

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            });

            vboxLowerPartContainer.getChildren().add(upGP);

            vboxAddInventoryPage.getChildren().addAll(GPInventoryTable,
                    vboxLowerPartContainer);

            //Bar chart
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Items");
            yAxis.setLabel("Quantity");

            final BarChart<String, Number> qtyChart = new BarChart<>(xAxis,
                    yAxis);
            qtyChart.setPrefSize(gW * 0.25, gH * 0.8);
            qtyChart.setBarGap(gW * 0.003);
            qtyChart.setTitle("Stock Evaluation");

            //ScrollPane for the chart
            ScrollBar scrollB = new ScrollBar();
            scrollB.setOrientation(Orientation.HORIZONTAL);
            scrollB.setMaxWidth(gW * 0.21);

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(1));

            svc.setOnSucceeded((WorkerStateEvent t) -> {

                XYChart.Series qtyChartData = new XYChart.Series();
                if (!svc.getValue().equals(svc.getLastValue())) {

                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();
                    ObservableList<Series<String, Number>> allSeries = qtyChart.getData();
                    allSeries.stream().forEach((series) -> {
                        series.getData().stream().map((data) -> data.getNode()).map(
                                (node) -> node.parentProperty().get()).filter(
                                        (parent) -> (parent != null && parent instanceof Group)).map(
                                        (parent) -> (Group) parent).forEach(
                                        (group) -> {
                                            group.getChildren().clear();
                                        });
                    });
                    for (ArrayList<String> tableViewItemsSum1
                            : tableViewItemsSum) {
                        XYChart.Data<String, Number> data = new XYChart.Data(
                                tableViewItemsSum1.get(1) + "\n" + tableViewItemsSum1.get(
                                2), parseInt(tableViewItemsSum1.get(3)));

                        data.nodeProperty().addListener(
                                (ObservableValue<? extends Node> ov, Node oldNode, Node newNode) -> {
                                    if (newNode != null) {
                                        if (data.getYValue().intValue() <= 2) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: red;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() < 5 && data.getYValue().intValue() > 2) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: Orange;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() > 4 && data.getYValue().intValue() < 15) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: Yellow;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() > 14) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: #66ff66;");
                                            chartYVal.displayLabelForData(data);

                                        }
                                    }
                                });
                        qtyChartData.getData().add(data);//priceChartData.getData().add(new XYChart.Data(InventoryItems.getItems().get(x).getName(), parseInt(InventoryItems.getItems().get(x).getSellingPrice())));
                    }
                    qtyChart.setBarGap(0.5);
                    qtyChart.getData().add(qtyChartData/*, priceChartData*/);

                }
            });

            svc.start();

            //charts VBox
            VBox vboxChart = new VBox();
            vboxChart.setId("vbox9");
            vboxChart.getChildren().addAll(qtyChart, scrollB);
            vboxChart.setPrefSize(gW * 0.25, gH * 0.8);
            vboxChart.setMaxSize(gW * 0.25, gH * 0.8);

            //final conatinment
            hboxMainMain.getChildren().addAll(vboxAddInventoryPage, vboxChart);

            return hboxMainMain;
        }
        ////----looks exactly the same as addInventory--- make sure that does not cause issues

        public HBox editInventory() throws ClassNotFoundException {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            chartYVal chartYVal = new chartYVal();
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            insertUpdateStockUser insertUpdateStockUser = new insertUpdateStockUser();

            //barcode
            this.intermediateBarcode = new TextField();

            ////----edit inventory main page
            HBox hboxMainMain = new HBox();
            hboxMainMain.setId("hbox5");
            VBox vboxEditInventoryPage = new VBox();

            ////---center top part table for the items
            GridPane GPInventoryTable = new GridPane();
            GPInventoryTable.setVgap(gH * 0.005);
            HBox hboxTitleLbl = new HBox();
            hboxTitleLbl.setId("hbox4");

            HBox hboxInventoryTable = new HBox();
            //the label
            Label tableTitleLbl = new Label("Edit Inventory");

            hboxTitleLbl.getChildren().add(tableTitleLbl);

            //--table
            TableView<InventoryItemsData> InventoryItems = new TableView<>();
            ObservableList<InventoryItemsData> InventoryItemsItems = FXCollections.observableArrayList();
            InventoryItems.setEditable(false);
            InventoryItems.setPrefHeight(gH * 0.6);
            InventoryItems.setItems(InventoryItemsItems);

            InventoryItems.getColumns().clear();

            TableColumn InventoryItems_Barcode_ID = new TableColumn("Barcode/ID");
            InventoryItems_Barcode_ID.setId("remainingClms");
            InventoryItems_Barcode_ID.setPrefWidth(gW * 0.08);
            InventoryItems_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>(
                            "Barcode_ID"));
            InventoryItems.getColumns().addAll(InventoryItems_Barcode_ID);

            TableColumn InventoryItems_Name = new TableColumn("Name");
            InventoryItems_Name.setId("remainingClms");
            InventoryItems_Name.setPrefWidth(gW * 0.1);
            InventoryItems_Name.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>("Name"));
            InventoryItems.getColumns().addAll(InventoryItems_Name);

            TableColumn InventoryItems_Description = new TableColumn(
                    "Description");
            InventoryItems_Description.setId("remainingClms");
            InventoryItems_Description.setPrefWidth(gW * 0.1);
            InventoryItems_Description.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>(
                            "Description"));
            InventoryItems.getColumns().addAll(InventoryItems_Description);

            TableColumn InventoryItems_Type = new TableColumn("Type");
            InventoryItems_Type.setId("remainingClms");
            InventoryItems_Type.setPrefWidth(gW * 0.1);
            InventoryItems_Type.setCellValueFactory(
                    new PropertyValueFactory<InventoryItemsData, String>("Type"));
            InventoryItems.getColumns().addAll(InventoryItems_Type);

            TableColumn InventoryItems_Units = new TableColumn("Units");
            InventoryItems_Units.setPrefWidth(gW * 0.07);
            InventoryItems_Units.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().Units.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_Units);

            TableColumn InventoryItems_UnitQuantity = new TableColumn(
                    "UnitQuantity");
            InventoryItems_UnitQuantity.setPrefWidth(gW * 0.1);
            InventoryItems_UnitQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(
                            p.getValue().UnitQuantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_UnitQuantity);

            TableColumn InventoryItems_PurchasePrice = new TableColumn(
                    "PurchasePrice");
            InventoryItems_PurchasePrice.setPrefWidth(gW * 0.1);
            InventoryItems_PurchasePrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(
                            p.getValue().PurchasePrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_PurchasePrice);

            TableColumn InventoryItems_SellingPrice = new TableColumn(
                    "SellingPrice");
            InventoryItems_SellingPrice.setPrefWidth(gW * 0.1);
            InventoryItems_SellingPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryItemsData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryItemsData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(
                            p.getValue().SellingPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryItems.getColumns().addAll(InventoryItems_SellingPrice);

            InventoryItemsItems.clear();
            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0;");
            selectInventoryItems.stream().forEach((selectInventoryItem) -> {
                InventoryItemsItems.add(new InventoryItemsData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(6), selectInventoryItem.get(7),
                        selectInventoryItem.get(8), selectInventoryItem.get(9)));
            });

            //table HBox
            hboxInventoryTable.getChildren().add(InventoryItems);

            ////----search panel
            UpperCaseTextField searchTxtFld = new UpperCaseTextField();
            searchTxtFld.setPromptText("      type to search item");
            searchTxtFld.setPrefSize(gW * 0.15, gH * 0.03);

            //HBox for search text field
            HBox hboxSearchTxtFld = new HBox();
            hboxSearchTxtFld.setId("hbox13");
            hboxSearchTxtFld.getChildren().add(searchTxtFld);

            /////table part finally coming together
            GPInventoryTable.addRow(0, hboxTitleLbl);
            GPInventoryTable.addRow(1, hboxSearchTxtFld);
            GPInventoryTable.addRow(2, hboxInventoryTable);

            ////---- fully configuring the lower part of add new inventory
            //container for all lower part items
            VBox vboxLowerPartContainer = new VBox();
            vboxLowerPartContainer.setId("vbox1");

            //Hbox for the lower part identification with the lower part title label
            HBox hboxLowPartLbl = new HBox();
            hboxLowPartLbl.setId("hbox4");

            //title label
            Label lowPartTitleLbl = new Label("Edit Item(s)");

            hboxLowPartLbl.getChildren().add(lowPartTitleLbl);

            //hbox for the low part middle section
            HBox hboxItemDescription = new HBox();
            hboxItemDescription.setId("hbox7");

            //HBoxes for the middle descriptors
            //for unique id
            //the label
            Label uniqueIdLbl = new Label("Barcode/ID:");
            uniqueIdLbl.setWrapText(true);
            //the text area
            TextField uniqueIdTxtFld = new TextField();
            uniqueIdTxtFld.setEditable(false);

            //for item name
            //the label
            Label itemNameLbl = new Label("Item Name:");
            itemNameLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemNameTxtFld = new UpperCaseTextField();

            //for item description
            //the label
            Label itemDecsriptionLbl = new Label("Description:");
            itemDecsriptionLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemDescriptionTxtFld = new UpperCaseTextField();

            //for item type
            //the label
            Label itemTypeLbl = new Label("Type:");
            itemTypeLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemTypeTxtFld = new UpperCaseTextField();

            //whole low middle part coming together as one
            //hbox for the low part middle section
            //HBoxes for the middle Low descriptors
            //for unique id
            //the label
            Label quantityLbl = new Label("Units:");
            quantityLbl.setWrapText(true);
            //the text area
            DecimalTextField quantityTxtFld = new DecimalTextField();
            quantityTxtFld.setEditable(false);

            //for unit quantity
            //the label
            Label unitQtyLbl = new Label("Unit Quantity:");
            unitQtyLbl.setWrapText(true);
            //the text Area
            DecimalTextField unitQtyTxtFld = new DecimalTextField();
            unitQtyTxtFld.setEditable(false);

            //for item description
            //the label
            Label purcahsePriceLbl = new Label("Purchase Price:");
            purcahsePriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField purcahsePriceTxtFld = new DecimalTextField();

            //for Sale price
            //the label
            Label sellingPriceLbl = new Label("Selling Price:");
            sellingPriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField salePriceTxtFld = new DecimalTextField();

            //whole low middle-Low part coming together as one
            //for the save/add/input button
            //the Button
            Button saveItemBtn = new Button("Commit");
            saveItemBtn.getStyleClass().add("btn-info");

            GridPane upGP = new GridPane();
            upGP.setVgap(gH * 0.001);
            upGP.addRow(0, uniqueIdLbl, uniqueIdTxtFld, itemNameLbl,
                    itemNameTxtFld, itemDecsriptionLbl, itemDescriptionTxtFld,
                    itemTypeLbl, itemTypeTxtFld);
            upGP.addRow(1, quantityLbl, quantityTxtFld, unitQtyLbl,
                    unitQtyTxtFld, purcahsePriceLbl, purcahsePriceTxtFld,
                    sellingPriceLbl, salePriceTxtFld);
            upGP.addRow(2, new Label(), new Label(), new Label(), new Label(),
                    new Label(), new Label(), new Label(), saveItemBtn);

            vboxLowerPartContainer.getChildren().add(upGP);

            vboxEditInventoryPage.getChildren().addAll(GPInventoryTable,
                    vboxLowerPartContainer);

            //Bar chart
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel("Items");
            yAxis.setLabel("Quantity");

            final BarChart<String, Number> qtyChart = new BarChart<>(xAxis,
                    yAxis);
            qtyChart.setPrefSize(gW * 0.25, gH * 0.8);
            qtyChart.setTitle("Stock Evaluation");

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(1));

            svc.setOnSucceeded((WorkerStateEvent t) -> {

                XYChart.Series qtyChartData = new XYChart.Series();
                if (!svc.getValue().equals(svc.getLastValue())) {

                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();
                    ObservableList<Series<String, Number>> allSeries = qtyChart.getData();
                    allSeries.stream().forEach((series) -> {
                        series.getData().stream().map((data) -> data.getNode()).map(
                                (node) -> node.parentProperty().get()).filter(
                                        (parent) -> (parent != null && parent instanceof Group)).map(
                                        (parent) -> (Group) parent).forEach(
                                        (group) -> {
                                            group.getChildren().clear();
                                        });
                    });
                    for (ArrayList<String> tableViewItemsSum1
                            : tableViewItemsSum) {
                        XYChart.Data<String, Number> data = new XYChart.Data(
                                tableViewItemsSum1.get(1) + "\n" + tableViewItemsSum1.get(
                                2), parseInt(tableViewItemsSum1.get(3)));

                        data.nodeProperty().addListener(
                                (ObservableValue<? extends Node> ov, Node oldNode, Node newNode) -> {
                                    if (newNode != null) {
                                        if (data.getYValue().intValue() <= 2) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: red;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() < 5 && data.getYValue().intValue() > 2) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: Orange;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() > 4 && data.getYValue().intValue() < 15) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: Yellow;");
                                            chartYVal.displayLabelForData(data);
                                        } else if (data.getYValue().intValue() > 14) {
                                            newNode.setStyle(
                                                    "-fx-bar-fill: #66ff66;");
                                            chartYVal.displayLabelForData(data);

                                        }
                                    }
                                });
                        qtyChartData.getData().clear();
                        qtyChartData.getData().add(data);//priceChartData.getData().add(new XYChart.Data(InventoryItems.getItems().get(x).getName(), parseInt(InventoryItems.getItems().get(x).getSellingPrice())));
                    }
                    qtyChart.setBarGap(0.5);
                    qtyChart.getData().add(qtyChartData/*, priceChartData*/);

                }
            });

            svc.start();

            uniqueIdTxtFld.textProperty().addListener((observable, oldValue, newValue) -> {
                for (int x = 0; x < selectInventoryItems.size(); x++) {
                    if (!uniqueIdTxtFld.getText().isEmpty()) {
                        if (uniqueIdTxtFld.getText().equals(selectInventoryItems.get(
                                x).get(1))) {
                            itemNameTxtFld.setText(selectInventoryItems.get(x).get(2));
                            itemDescriptionTxtFld.setText(
                                    selectInventoryItems.get(x).get(3));
                            itemTypeTxtFld.setText(selectInventoryItems.get(x).get(4));
                            purcahsePriceTxtFld.setText(
                                    selectInventoryItems.get(x).get(8));
                            salePriceTxtFld.setText(
                                    selectInventoryItems.get(x).get(9));
                            break;
                        }
                    } else {
                        itemNameTxtFld.clear();
                        itemDescriptionTxtFld.clear();
                        itemTypeTxtFld.clear();
                        purcahsePriceTxtFld.clear();
                        salePriceTxtFld.clear();
                    }
                }
            });

            this.intermediateBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                uniqueIdTxtFld.setText(intermediateBarcode.getText());
            });

            searchTxtFld.textProperty().addListener(e -> {
                if (searchTxtFld.getText() != null) {
                    InventoryItems.setItems(InventoryItemsItems);
                    final FilteredList<InventoryItemsData> filteredList = InventoryItems.getItems().filtered(
                            p -> p.getName().contains(searchTxtFld.getText()) || p.getBarcode_ID().contains(
                            searchTxtFld.getText()) || p.getType().contains(
                            searchTxtFld.getText()) || p.getDescription().contains(
                            searchTxtFld.getText()));
                    InventoryItems.setItems(filteredList);

                }
            });

            InventoryItems.setRowFactory(PI -> {
                TableRow<InventoryItemsData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        uniqueIdTxtFld.clear();
                        uniqueIdTxtFld.setText(row.getItem().getBarcode_ID());

                        itemNameTxtFld.clear();
                        itemNameTxtFld.setText(row.getItem().getName());

                        itemDescriptionTxtFld.clear();
                        itemDescriptionTxtFld.setText(
                                row.getItem().getDescription());

                        itemTypeTxtFld.clear();
                        itemTypeTxtFld.setText(row.getItem().getType());

                        quantityTxtFld.clear();
                        quantityTxtFld.setText(row.getItem().getUnits());

                        unitQtyTxtFld.clear();
                        unitQtyTxtFld.setText(row.getItem().getUnitQuantity());

                        purcahsePriceTxtFld.clear();
                        purcahsePriceTxtFld.setText(
                                row.getItem().getPurchasePrice());

                        salePriceTxtFld.clear();
                        salePriceTxtFld.setText(row.getItem().getSellingPrice());
                    }
                });
                return row;
            });

            saveItemBtn.setOnAction(e -> {
                try {
                    merakiBusinessDBClass.processSQLUpdate(
                            insertUpdateStockUser.updateAllItems(
                                    uniqueIdTxtFld.getText(),
                                    itemNameTxtFld.getText(),
                                    itemDescriptionTxtFld.getText(),
                                    itemTypeTxtFld.getText(),
                                    purcahsePriceTxtFld.getDecimal(),
                                    salePriceTxtFld.getDecimal()));
                    Alert exceptionAlert = new Alert(
                            Alert.AlertType.CONFIRMATION, "Item Updated! ",
                            ButtonType.OK);
                    exceptionAlert.showAndWait();
                    uniqueIdTxtFld.clear();
                    itemNameTxtFld.clear();
                    itemDescriptionTxtFld.clear();
                    itemTypeTxtFld.clear();
                    quantityTxtFld.clear();
                    unitQtyTxtFld.clear();
                    purcahsePriceTxtFld.clear();
                    salePriceTxtFld.clear();

                    InventoryItemsItems.clear();
                    ArrayList<ArrayList<String>> selectInventoryItems1 = merakiBusinessDBClass.processSQLSelect(
                            "SELECT * FROM inventory WHERE remainingQuantity > 0;");
                    for (ArrayList<String> selectInventoryItems11
                            : selectInventoryItems1) {
                        InventoryItemsItems.add(new InventoryItemsData(
                                selectInventoryItems11.get(1),
                                selectInventoryItems11.get(2),
                                selectInventoryItems11.get(3),
                                selectInventoryItems11.get(4),
                                selectInventoryItems11.get(6),
                                selectInventoryItems11.get(7),
                                selectInventoryItems11.get(8),
                                selectInventoryItems11.get(9)));
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            });

            //charts VBox
            VBox vboxChart = new VBox();
            vboxChart.getChildren().add(qtyChart);
            vboxChart.setPrefSize(gW * 0.25, gH * 0.8);
            vboxChart.setMaxSize(gW * 0.25, gH * 0.8);

            //final conatinment
            hboxMainMain.getChildren().addAll(vboxEditInventoryPage, vboxChart);

            return hboxMainMain;
        }

        public HBox masterData() throws ClassNotFoundException {
            ///--prerequisites
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            insertUpdateStockUser insertUpdateStockUser = new insertUpdateStockUser();
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            //barcode
            this.intermediateBarcode = new TextField();

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {

                    return new selectAllInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(2.5));

            //pick suff from the inventory table... required at the start
            ScheduledService<ArrayList<ArrayList<String>>> svc1 = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorWholePacketInventoryTask();
                }
            };
            svc1.setPeriod(Duration.seconds(2.5));

            ////----edit inventory main page
            HBox hboxMainMain1 = new HBox();
            hboxMainMain1.setId("hbox5");
            VBox vboxInventoryData = new VBox();

            ////---center top part table for the items
            VBox vboxInventoryTable = new VBox();
            vboxInventoryTable.setId("vbox2");
            HBox hboxTitleLbl = new HBox();
            hboxTitleLbl.setId("hbox4");

            HBox hboxInventoryTable = new HBox();
            //the label
            Label tableTitleLbl = new Label("Inventory MasterData");

            hboxTitleLbl.getChildren().add(tableTitleLbl);

            //--table
            TableView<InventoryGenDataData> InventoryGenData = new TableView<>();
            ObservableList<InventoryGenDataData> InventoryGenDataItems = FXCollections.observableArrayList();
            InventoryGenData.setEditable(false);
            InventoryGenData.setPrefHeight(gH * 0.6);
            InventoryGenData.setItems(InventoryGenDataItems);

            InventoryGenData.getColumns().clear();

            TableColumn InventoryGenData_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            InventoryGenData_Barcode_ID.setId("remainingClms");
            InventoryGenData_Barcode_ID.setPrefWidth(gW * 0.1);
            InventoryGenData_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "Barcode_ID"));
            InventoryGenData.getColumns().addAll(InventoryGenData_Barcode_ID);

            TableColumn InventoryGenData_ItemName = new TableColumn("ItemName");
            InventoryGenData_ItemName.setId("remainingClms");
            InventoryGenData_ItemName.setPrefWidth(gW * 0.1);
            InventoryGenData_ItemName.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "ItemName"));
            InventoryGenData.getColumns().addAll(InventoryGenData_ItemName);

            TableColumn InventoryGenData_Description = new TableColumn(
                    "Description");
            InventoryGenData_Description.setId("remainingClms");
            InventoryGenData_Description.setPrefWidth(gW * 0.1);
            InventoryGenData_Description.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "Description"));
            InventoryGenData.getColumns().addAll(InventoryGenData_Description);

            TableColumn InventoryGenData_Type = new TableColumn("Type");
            InventoryGenData_Type.setId("remainingClms");
            InventoryGenData_Type.setPrefWidth(gW * 0.1);
            InventoryGenData_Type.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "Type"));
            InventoryGenData.getColumns().addAll(InventoryGenData_Type);

            TableColumn InventoryGenData_InputQuantity = new TableColumn(
                    "InputQuantity");
            InventoryGenData_InputQuantity.setId("qtyCell");
            InventoryGenData_InputQuantity.setPrefWidth(gW * 0.1);
            InventoryGenData_InputQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryGenDataData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryGenDataData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###,###");
                    double money = parseDouble(
                            p.getValue().InputQuantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryGenData.getColumns().addAll(InventoryGenData_InputQuantity);

            TableColumn InventoryGenData_RemainingQuantity = new TableColumn(
                    "RemainingQuantity");
            InventoryGenData_RemainingQuantity.setId("qtyCell");
            InventoryGenData_RemainingQuantity.setPrefWidth(gW * 0.1);
            InventoryGenData_RemainingQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryGenDataData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryGenDataData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###,###");
                    double money = parseDouble(
                            p.getValue().RemainingQuantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryGenData.getColumns().addAll(
                    InventoryGenData_RemainingQuantity);

            TableColumn InventoryGenData_UnitQuantity = new TableColumn(
                    "UnitQuantity");
            InventoryGenData_UnitQuantity.setId("qtyCell");
            InventoryGenData_UnitQuantity.setPrefWidth(gW * 0.1);
            InventoryGenData_UnitQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryGenDataData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryGenDataData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###,###");
                    double money = parseDouble(
                            p.getValue().UnitQuantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryGenData.getColumns().addAll(InventoryGenData_UnitQuantity);

            TableColumn InventoryGenData_PurchasePrice = new TableColumn(
                    "PurchasePrice");
            InventoryGenData_PurchasePrice.setId("moneyCell");
            InventoryGenData_PurchasePrice.setPrefWidth(gW * 0.1);
            InventoryGenData_PurchasePrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryGenDataData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryGenDataData, Boolean> p) {

                    double money = parseDouble(
                            p.getValue().PurchasePrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryGenData.getColumns().addAll(InventoryGenData_PurchasePrice);

            TableColumn InventoryGenData_UnitPrice = new TableColumn("UnitPrice");
            InventoryGenData_UnitPrice.setId("moneyCell");
            InventoryGenData_UnitPrice.setPrefWidth(gW * 0.1);
            InventoryGenData_UnitPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<InventoryGenDataData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<InventoryGenDataData, Boolean> p) {

                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            InventoryGenData.getColumns().addAll(InventoryGenData_UnitPrice);

            TableColumn InventoryGenData_CreatedBy = new TableColumn("CreatedBy");
            InventoryGenData_CreatedBy.setId("remainingClms");
            InventoryGenData_CreatedBy.setPrefWidth(gW * 0.1);
            InventoryGenData_CreatedBy.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "CreatedBy"));
            InventoryGenData.getColumns().addAll(InventoryGenData_CreatedBy);

            TableColumn InventoryGenData_CreatedOn = new TableColumn("CreatedOn");
            InventoryGenData_CreatedOn.setId("remainingClms");
            InventoryGenData_CreatedOn.setPrefWidth(gW * 0.1);
            InventoryGenData_CreatedOn.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "CreatedOn"));
            InventoryGenData.getColumns().addAll(InventoryGenData_CreatedOn);

            TableColumn InventoryGenData_FullyConsumedOn = new TableColumn(
                    "FullyConsumedOn");
            InventoryGenData_FullyConsumedOn.setId("remainingClms");
            InventoryGenData_FullyConsumedOn.setPrefWidth(gW * 0.1);
            InventoryGenData_FullyConsumedOn.setCellValueFactory(
                    new PropertyValueFactory<InventoryGenDataData, String>(
                            "FullyConsumedOn"));
            InventoryGenData.getColumns().addAll(
                    InventoryGenData_FullyConsumedOn);;

            svc.setOnSucceeded((WorkerStateEvent t) -> {
                if (!svc.getValue().equals(svc.getLastValue())) {
                    InventoryGenDataItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();

                    tableViewItemsSum.stream().forEach((tableViewItemsSum1) -> {
                        InventoryGenDataItems.add(new InventoryGenDataData(
                                tableViewItemsSum1.get(12),
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5),
                                tableViewItemsSum1.get(6),
                                tableViewItemsSum1.get(7),
                                tableViewItemsSum1.get(8),
                                tableViewItemsSum1.get(9),
                                tableViewItemsSum1.get(10),
                                tableViewItemsSum1.get(11)));
                    });

                }
            });
            ///this redundancy is necessary because at first, the scheduledservice does not start immediately hence the restarts
            ///Meaning that whenever you see this redundancy, remember that its necessary
            svc.start();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();

            //table HBox
            hboxInventoryTable.getChildren().add(InventoryGenData);

            ////----search panel
            UpperCaseTextField searchTxtFld = new UpperCaseTextField();
            searchTxtFld.setPromptText("      type to search item");
            searchTxtFld.setPrefSize(gW * 0.15, gH * 0.03);

            //HBox for search text field
            HBox hboxSearchTxtFld = new HBox();
            hboxSearchTxtFld.setId("hbox13");
            hboxSearchTxtFld.getChildren().add(searchTxtFld);

            /////table part finally coming together
            vboxInventoryTable.getChildren().addAll(hboxTitleLbl,
                    hboxSearchTxtFld, hboxInventoryTable);

            ////---- fully configuring the lower part of add new inventory
            //container for all lower part items
            //Hbox for the lower part identification with the lower part title label
            HBox hboxLowPartLbl = new HBox();
            hboxLowPartLbl.setId("hbox4");

            //title label
            Label lowPartTitleLbl = new Label("Edit Item(s)");

            hboxLowPartLbl.getChildren().add(lowPartTitleLbl);

            //hbox for the low part middle section
            HBox hboxItemDescription = new HBox();
            hboxItemDescription.setId("hbox43");

            //HBoxes for the middle descriptors
            //for unique id
            //the label
            Label uniqueIdLbl = new Label("Barcode/ID:");
            uniqueIdLbl.setWrapText(true);
            //the text area
            TextField uniqueIdTxtFld = new TextField();
            uniqueIdTxtFld.setEditable(false);

            //for item name
            //the label
            Label itemNameLbl = new Label("Item Name:");
            itemNameLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemNameTxtFld = new UpperCaseTextField();

            //for item description
            //the label
            Label itemDecsriptionLbl = new Label("Description:");
            itemDecsriptionLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemDescriptionTxtFld = new UpperCaseTextField();
            itemDescriptionTxtFld.setMinWidth(gW * 0.15);

            //for item type
            //the label
            Label itemTypeLbl = new Label("Type:");
            itemTypeLbl.setWrapText(true);
            //the text Area
            UpperCaseTextField itemTypeTxtFld = new UpperCaseTextField();

            //HBoxes for the middle Low descriptors
            //for unique id
            //the label
            Label quantityLbl = new Label("Units:");
            quantityLbl.setWrapText(true);
            //the text area
            DecimalTextField quantityTxtFld = new DecimalTextField();

            //for unit quantity
            //the label
            Label unitQtyLbl = new Label("Unit Quantity:");
            unitQtyLbl.setWrapText(true);
            //the text Area
            DecimalTextField unitQtyTxtFld = new DecimalTextField();

            //whole low middle part coming together as one
            //hbox for the low part middle section
            //for item description
            //the label
            Label purcahsePriceLbl = new Label("Purchase Price:");
            purcahsePriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField purcahsePriceTxtFld = new DecimalTextField();

            //for Sale price
            //the label
            Label sellingPriceLbl = new Label("Selling Price:");
            sellingPriceLbl.setWrapText(true);
            //the text Area
            DecimalTextField salePriceTxtFld = new DecimalTextField();

            //whole low middle-Low part coming together as one
            //for the save/add/input button
            //the Button
            Button saveItemBtn = new Button("Commit");
            saveItemBtn.getStyleClass().add("btn-info");

            GridPane upGP = new GridPane();
            upGP.setVgap(gH * 0.002);
            upGP.setHgap(gW * 0.009);
            upGP.addRow(0, uniqueIdLbl, uniqueIdTxtFld, itemNameLbl,
                    itemNameTxtFld, itemDecsriptionLbl, itemDescriptionTxtFld,
                    itemTypeLbl, itemTypeTxtFld, quantityLbl, quantityTxtFld);
            upGP.addRow(1, unitQtyLbl, unitQtyTxtFld, purcahsePriceLbl,
                    purcahsePriceTxtFld, sellingPriceLbl, salePriceTxtFld);
            upGP.addRow(2, new Label(), new Label(), new Label(), new Label(),
                    new Label(), new Label(), new Label(), new Label(),
                    new Label(), saveItemBtn);

            vboxInventoryData.getChildren().addAll(vboxInventoryTable, upGP);

            InventoryGenData.setRowFactory(PI -> {
                TableRow<InventoryGenDataData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        uniqueIdTxtFld.clear();
                        uniqueIdTxtFld.setText(row.getItem().getBarcode_ID());

                        itemNameTxtFld.clear();
                        itemNameTxtFld.setText(row.getItem().getItemName());

                        itemDescriptionTxtFld.clear();
                        itemDescriptionTxtFld.setText(
                                row.getItem().getDescription());

                        itemTypeTxtFld.clear();
                        itemTypeTxtFld.setText(row.getItem().getType());

                        quantityTxtFld.clear();
                        quantityTxtFld.setText(
                                row.getItem().getRemainingQuantity());

                        unitQtyTxtFld.clear();
                        unitQtyTxtFld.setText(row.getItem().getUnitQuantity());

                        purcahsePriceTxtFld.clear();
                        purcahsePriceTxtFld.setText(
                                row.getItem().getPurchasePrice().replaceAll(",",
                                        "").replaceAll("/=", "").replace(".00",
                                        ""));

                        salePriceTxtFld.clear();
                        salePriceTxtFld.setText(
                                row.getItem().getUnitPrice().replaceAll(",", "").replaceAll(
                                        "/=", "").replace(".00", ""));
                    }
                });
                return row;
            });

            searchTxtFld.textProperty().addListener(e -> {
                if (searchTxtFld.getText() != null) {
                    InventoryGenData.setItems(InventoryGenDataItems);
                    final FilteredList<InventoryGenDataData> filteredList = InventoryGenData.getItems().filtered(
                            p -> p.getItemName().contains(searchTxtFld.getText()) || p.getBarcode_ID().contains(
                            searchTxtFld.getText()) || p.getType().contains(
                            searchTxtFld.getText()) || p.getDescription().contains(
                            searchTxtFld.getText()));
                    InventoryGenData.setItems(filteredList);

                }
            });

            saveItemBtn.setOnAction(e -> {
                try {
                    merakiBusinessDBClass.processSQLUpdate(
                            "UPDATE inventory SET barcode_ID = '" + uniqueIdTxtFld.getText() + "', itemName = '" + itemNameTxtFld.getText() + "', description = '" + itemDescriptionTxtFld.getText() + "', type = '" + itemTypeTxtFld.getText() + "', remainingQuantity = '" + parseDouble(
                            quantityTxtFld.getDecimal()) + "', unitQuantity = '" + parseDouble(
                            unitQtyTxtFld.getDecimal()) + "', purchasePrice = '" + parseDouble(
                            purcahsePriceTxtFld.getDecimal()) + "', unitPrice = '" + parseDouble(
                            salePriceTxtFld.getDecimal()) + "' WHERE itemId = '" + parseInt(
                            InventoryGenData.getSelectionModel().getSelectedItem().getItemId()) + "'");
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();
                    svc.restart();

                    Alert exceptionAlert = new Alert(
                            Alert.AlertType.CONFIRMATION, "Item Updated! ",
                            ButtonType.OK);
                    exceptionAlert.showAndWait();
                    uniqueIdTxtFld.clear();
                    itemNameTxtFld.clear();
                    itemDescriptionTxtFld.clear();
                    itemTypeTxtFld.clear();
                    quantityTxtFld.clear();
                    unitQtyTxtFld.clear();
                    purcahsePriceTxtFld.clear();
                    salePriceTxtFld.clear();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            });

            //final conatinment
            hboxMainMain1.getChildren().addAll(vboxInventoryData);

            ////Main TabPane and its tabs
            Tab inventoryTab = new Tab(" Inventory ");
            inventoryTab.setContent(hboxMainMain1);
            Tab wholeSellTab = new Tab(" WholeSell ");
            Tab retailSellTab = new Tab("Retail Prices");
//            Tab expiryDateTab = new Tab("Expiry Dates");
//            Tab reportsTab = new Tab("  Reports  ");
//            Tab wholeSellDiscountsTab = new Tab("WholeSell");

            UpperCaseTextField searchWholeSaleTxtFld = new UpperCaseTextField();

            //HBox for search text field
            HBox hboxSearchWholeSellTxtFld = new HBox();
            hboxSearchWholeSellTxtFld.setId("hbox13");
            hboxSearchWholeSellTxtFld.getChildren().add(searchWholeSaleTxtFld);

            TableView<WholeSellConfigData> WholeSellConfig = new TableView<>();
            ObservableList<WholeSellConfigData> WholeSellConfigItems = FXCollections.observableArrayList();
            WholeSellConfig.setEditable(false);
            WholeSellConfig.setPrefHeight(gH * 0.78);
            WholeSellConfig.setItems(WholeSellConfigItems);

            WholeSellConfig.getColumns().clear();

            TableColumn WholeSellConfig_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            WholeSellConfig_Barcode_ID.setId("remainingClms");
            WholeSellConfig_Barcode_ID.setPrefWidth(gW * 0.1);
            WholeSellConfig_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "Barcode_ID"));
            WholeSellConfig.getColumns().addAll(WholeSellConfig_Barcode_ID);

            TableColumn WholeSellConfig_ItemName = new TableColumn("ItemName");
            WholeSellConfig_ItemName.setId("remainingClms");
            WholeSellConfig_ItemName.setPrefWidth(gW * 0.1);
            WholeSellConfig_ItemName.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "ItemName"));
            WholeSellConfig.getColumns().addAll(WholeSellConfig_ItemName);

            TableColumn WholeSellConfig_Description = new TableColumn(
                    "Description");
            WholeSellConfig_Description.setPrefWidth(gW * 0.13);
            WholeSellConfig_Description.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "Description"));
            WholeSellConfig.getColumns().addAll(WholeSellConfig_Description);

            TableColumn WholeSellConfig_Type = new TableColumn("Type");
            WholeSellConfig_Type.setPrefWidth(gW * 0.1);
            WholeSellConfig_Type.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>("Type"));
            WholeSellConfig.getColumns().addAll(WholeSellConfig_Type);

            TableColumn WholeSellConfig_MinimumAmount = new TableColumn(
                    "Minimum Allowed\n" + "       Quantity");
            WholeSellConfig_MinimumAmount.setId("qtyCell");
            WholeSellConfig_MinimumAmount.setPrefWidth(gW * 0.1);
            WholeSellConfig_MinimumAmount.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "MinimunQuantity"));
            WholeSellConfig.getColumns().addAll(WholeSellConfig_MinimumAmount);

            TableColumn WholeSellConfig_PurchasePrice = new TableColumn(
                    "Normal Unit Price");
            WholeSellConfig_PurchasePrice.setId("moneyCell");
            WholeSellConfig_PurchasePrice.setPrefWidth(gW * 0.1);
            WholeSellConfig_PurchasePrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WholeSellConfigData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<WholeSellConfigData, Boolean> p) {

                    double money = parseDouble(
                            p.getValue().PurchasePrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);

                }

            });
            WholeSellConfig.getColumns().addAll(WholeSellConfig_PurchasePrice);

            TableColumn WholeSellConfig_WholeSellPrice = new TableColumn(
                    "WholeSellPrice");
            WholeSellConfig_WholeSellPrice.setId("moneyCell");
            WholeSellConfig_WholeSellPrice.setPrefWidth(gW * 0.1);
            WholeSellConfig_WholeSellPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WholeSellConfigData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<WholeSellConfigData, Boolean> p) {

                    double money = parseDouble(
                            p.getValue().WholeSellPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            WholeSellConfig.getColumns().addAll(WholeSellConfig_WholeSellPrice);

            svc1.setOnSucceeded((WorkerStateEvent t) -> {

                if (!svc1.getValue().equals(svc1.getLastValue())) {
                    WholeSellConfigItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc1.getValue();
                    tableViewItemsSum.stream().forEach((tableViewItemsSum1) -> {
                        WholeSellConfigItems.add(new WholeSellConfigData(
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5),
                                tableViewItemsSum1.get(6)));
                    });

                }
            });
            svc1.start();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();

            VBox editWholeSell = new VBox();
            editWholeSell.setId("hbox48");
            GridPane editWholeSellGP = new GridPane();
            editWholeSellGP.setVgap(gH * 0.015);
            editWholeSellGP.setHgap(gW * 0.015);
            editWholeSell.getChildren().add(editWholeSellGP);

            //Item Barcode label and TextField
            Label barcodeLbl1 = new Label("Barcode/ID: ");
            //TextField barcodeTxtFld
            TextField barcodeTxtFld1 = new TextField();
            barcodeTxtFld1.setEditable(false);

            editWholeSellGP.addRow(0, barcodeLbl1, barcodeTxtFld1);

            //Item Name label and TextField
            Label itemNameLbl1 = new Label("Name: ");
            //TextField barcodeTxtFld
            TextField itemNameTxtFld1 = new TextField();
            itemNameTxtFld1.setEditable(false);

            editWholeSellGP.addRow(1, itemNameLbl1, itemNameTxtFld1);

            //Item Description label and TextField
            Label descriptionLbl1 = new Label("Description: ");
            //TextField barcodeTxtFld
            TextField descriptionTxtFld1 = new TextField();
            descriptionTxtFld1.setEditable(false);

            editWholeSellGP.addRow(2, descriptionLbl1, descriptionTxtFld1);

            //Item Type label and TextField
            Label typeLbl1 = new Label("Type: ");
            //TextField barcodeTxtFld
            TextField typeTxtFld1 = new TextField();
            typeTxtFld1.setEditable(false);

            editWholeSellGP.addRow(3, typeLbl1, typeTxtFld1);

            //Item Minimum Quantity label and TextField
            Label minimumQtyLbl = new Label("Minimum Quantity: ");
            //TextField barcodeTxtFld
            DecimalTextField minQtyTxtFld1 = new DecimalTextField();;

            editWholeSellGP.addRow(4, minimumQtyLbl, minQtyTxtFld1);

            //Item UnitPrice label and TextField
            Label unitPriceLbl = new Label("WholeSell Price: ");
            //TextField barcodeTxtFld
            DecimalTextField unitPriceTxtFld = new DecimalTextField();

            editWholeSellGP.addRow(5, unitPriceLbl, unitPriceTxtFld);

            //commit button
            Button saveBtn = new Button(" Save ");
            saveBtn.getStyleClass().add("btn-info");
            Label mchLbl = new Label();
            editWholeSellGP.addRow(6, mchLbl, saveBtn);

            WholeSellConfig.setRowFactory(PI -> {
                TableRow<WholeSellConfigData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        barcodeTxtFld1.clear();
                        barcodeTxtFld1.setText(row.getItem().getBarcode_ID());

                        itemNameTxtFld1.clear();
                        itemNameTxtFld1.setText(row.getItem().getItemName());

                        descriptionTxtFld1.clear();
                        descriptionTxtFld1.setText(
                                row.getItem().getDescription());

                        typeTxtFld1.clear();
                        typeTxtFld1.setText(row.getItem().getType());

                        minQtyTxtFld1.clear();

                        unitPriceTxtFld.clear();

                    }
                });
                return row;
            });

            saveBtn.setOnAction(e -> {
                if (!minQtyTxtFld1.getText().isEmpty() && !unitPriceTxtFld.getText().isEmpty()) {
                    try {
                        merakiBusinessDBClass.processSQLUpdate(
                                "UPDATE inventory SET wholeSellPrice = '" + parseDouble(
                                        unitPriceTxtFld.getDecimal()) + "', minWSQty = '" + parseInt(
                                minQtyTxtFld1.getDecimal()) + "' WHERE barcode_ID = '" + barcodeTxtFld1.getText() + "'");
                        svc1.restart();
                        svc1.restart();
                        svc1.restart();
                        svc1.restart();

                        Alert exceptionAlert = new Alert(
                                Alert.AlertType.INFORMATION,
                                "Item Can Now Be Sold As WholeSell Item!!",
                                ButtonType.OK);
                        exceptionAlert.showAndWait();
                        barcodeTxtFld1.clear();
                        itemNameTxtFld1.clear();
                        descriptionTxtFld1.clear();
                        typeTxtFld1.clear();
                        minQtyTxtFld1.clear();
                        unitPriceTxtFld.clear();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
            });

            HBox vboxWhlSllAndSearch = new HBox();
            vboxWhlSllAndSearch.setId("hbox13");

            UpperCaseTextField searchWhlStockTxtFld = new UpperCaseTextField();
            searchWhlStockTxtFld.setPromptText("     Type To Search ");
            searchWhlStockTxtFld.setMaxWidth(gW * 0.15);
            vboxWhlSllAndSearch.getChildren().add(searchWhlStockTxtFld);

            searchWhlStockTxtFld.textProperty().addListener(e -> {
                if (searchWhlStockTxtFld.getText() != null) {
                    WholeSellConfig.setItems(WholeSellConfigItems);
                    final FilteredList<WholeSellConfigData> filteredList = WholeSellConfig.getItems().filtered(
                            p -> p.getItemName().contains(
                                    searchWhlStockTxtFld.getText()) || p.getBarcode_ID().contains(
                            searchWhlStockTxtFld.getText()) || p.getType().contains(
                            searchWhlStockTxtFld.getText()) || p.getDescription().contains(
                            searchWhlStockTxtFld.getText()));
                    WholeSellConfig.setItems(filteredList);

                }
            });

            this.intermediateBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                searchTxtFld.setText(intermediateBarcode.getText());
                searchWhlStockTxtFld.setText(intermediateBarcode.getText());
            });

            GridPane wholeSaleGP = new GridPane();
            GridPane wholeSellSearchAndTbl = new GridPane();
            wholeSellSearchAndTbl.addRow(0, vboxWhlSllAndSearch);
            wholeSellSearchAndTbl.addRow(1, WholeSellConfig);
            wholeSaleGP.addRow(0, editWholeSell, wholeSellSearchAndTbl);
            wholeSellTab.setContent(wholeSaleGP);

            retailSellTab.setContent(editRetailPrices());

            TabPane masterDataTB = new TabPane();
            masterDataTB.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
            masterDataTB.getTabs().addAll(inventoryTab, wholeSellTab,
                    retailSellTab);

            //final conatinment
            HBox hboxMainMain = new HBox();
            hboxMainMain.getChildren().addAll(masterDataTB);

            return hboxMainMain;
        }

        public GridPane editRetailPrices() {
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            ScheduledService<ArrayList<ArrayList<String>>> svc1 = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {

                    return new monitorRetailInventoryTask();
                }
            };
            svc1.setPeriod(Duration.seconds(2.5));

            this.retialBarcode = new TextField();

            UpperCaseTextField searchWholeSaleTxtFld = new UpperCaseTextField();

            this.retialBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                searchWholeSaleTxtFld.setText(retialBarcode.getText());
            });

            //HBox for search text field
            HBox hboxSearchWholeSellTxtFld = new HBox();
            hboxSearchWholeSellTxtFld.setId("hbox13");
            hboxSearchWholeSellTxtFld.getChildren().add(searchWholeSaleTxtFld);

            TableView<RetailSellConfigData> RetailSellConfig = new TableView<>();
            ObservableList<RetailSellConfigData> RetailSellConfigItems = FXCollections.observableArrayList();
            RetailSellConfig.setEditable(false);
            RetailSellConfig.setPrefHeight(gH * 0.78);
            RetailSellConfig.setItems(RetailSellConfigItems);

            RetailSellConfig.getColumns().clear();

            TableColumn RetailSellConfig_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            RetailSellConfig_Barcode_ID.setId("remainingClms");
            RetailSellConfig_Barcode_ID.setPrefWidth(gW * 0.15);
            RetailSellConfig_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "Barcode_ID"));
            RetailSellConfig.getColumns().addAll(RetailSellConfig_Barcode_ID);

            TableColumn RetailSellConfig_ItemName = new TableColumn("ItemName");
            RetailSellConfig_ItemName.setId("remainingClms");
            RetailSellConfig_ItemName.setPrefWidth(gW * 0.15);
            RetailSellConfig_ItemName.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "ItemName"));
            RetailSellConfig.getColumns().addAll(RetailSellConfig_ItemName);

            TableColumn RetailSellConfig_Description = new TableColumn(
                    "Description");
            RetailSellConfig_Description.setPrefWidth(gW * 0.15);
            RetailSellConfig_Description.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "Description"));
            RetailSellConfig.getColumns().addAll(RetailSellConfig_Description);

            TableColumn RetailSellConfig_Type = new TableColumn("Type");
            RetailSellConfig_Type.setPrefWidth(gW * 0.1);
            RetailSellConfig_Type.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>("Type"));
            RetailSellConfig.getColumns().addAll(RetailSellConfig_Type);

            TableColumn RetailSellConfig_MinimumAmount = new TableColumn(
                    "Total\n" + "       Quantity");
            RetailSellConfig_MinimumAmount.setId("qtyCell");
            RetailSellConfig_MinimumAmount.setPrefWidth(gW * 0.1);
            RetailSellConfig_MinimumAmount.setCellValueFactory(
                    new PropertyValueFactory<WholeSellConfigData, String>(
                            "TotalQuantity"));
            RetailSellConfig.getColumns().addAll(RetailSellConfig_MinimumAmount);

            TableColumn RetailSellConfig_PurchasePrice = new TableColumn(
                    "Unit Price");
            RetailSellConfig_PurchasePrice.setId("moneyCell");
            RetailSellConfig_PurchasePrice.setPrefWidth(gW * 0.1);
            RetailSellConfig_PurchasePrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<RetailSellConfigData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<RetailSellConfigData, Boolean> p) {

                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);

                }

            });
            RetailSellConfig.getColumns().addAll(RetailSellConfig_PurchasePrice);

            svc1.setOnSucceeded((WorkerStateEvent t) -> {

                if (!svc1.getValue().equals(svc1.getLastValue())) {
                    RetailSellConfigItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc1.getValue();
                    tableViewItemsSum.stream().forEach((tableViewItemsSum1) -> {
                        RetailSellConfigItems.add(new RetailSellConfigData(
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(6),
                                tableViewItemsSum1.get(7)));
                    });

                }
            });
            svc1.start();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();
            svc1.restart();

            VBox editWholeSell = new VBox();
            editWholeSell.setId("hbox48");
            GridPane editRetailSellGP = new GridPane();
            editRetailSellGP.setVgap(gH * 0.015);
            editRetailSellGP.setHgap(gW * 0.015);
            editWholeSell.getChildren().add(editRetailSellGP);

            editRetailSellGP.addRow(0, new Label());

            //Item Barcode label and TextField
            Label barcodeLbl1 = new Label("Barcode/ID: ");
            //TextField barcodeTxtFld
            TextField barcodeTxtFld1 = new TextField();
            barcodeTxtFld1.setEditable(false);

            editRetailSellGP.addRow(1, barcodeLbl1, barcodeTxtFld1);

            //Item Name label and TextField
            Label itemNameLbl1 = new Label("Name: ");
            //TextField barcodeTxtFld
            TextField itemNameTxtFld1 = new TextField();
            itemNameTxtFld1.setEditable(false);

            editRetailSellGP.addRow(2, itemNameLbl1, itemNameTxtFld1);

            //Item Description label and TextField
            Label descriptionLbl1 = new Label("Description: ");
            //TextField barcodeTxtFld
            TextField descriptionTxtFld1 = new TextField();
            descriptionTxtFld1.setEditable(false);

            editRetailSellGP.addRow(3, descriptionLbl1, descriptionTxtFld1);

            //Item Type label and TextField
            Label typeLbl1 = new Label("Type: ");
            //TextField barcodeTxtFld
            TextField typeTxtFld1 = new TextField();
            typeTxtFld1.setEditable(false);

            editRetailSellGP.addRow(4, typeLbl1, typeTxtFld1);

            //Item Minimum Quantity label and TextField
            Label totalQtyLbl = new Label("Total Quantity: ");
            //TextField barcodeTxtFld
            DecimalTextField totalQtyTxtFld1 = new DecimalTextField();

            editRetailSellGP.addRow(5, totalQtyLbl, totalQtyTxtFld1);

            //Item UnitPrice label and TextField
            Label unitPriceLbl = new Label("New Unit Price: ");
            //TextField barcodeTxtFld
            DecimalTextField unitPriceTxtFld = new DecimalTextField();

            editRetailSellGP.addRow(6, unitPriceLbl, unitPriceTxtFld);

            //commit button
            Button saveBtn = new Button(" Save ");
            saveBtn.getStyleClass().add("btn-info");
            Label mchLbl = new Label();
            editRetailSellGP.addRow(7, mchLbl, saveBtn);

            RetailSellConfig.setRowFactory(PI -> {
                TableRow<RetailSellConfigData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        barcodeTxtFld1.clear();
                        barcodeTxtFld1.setText(row.getItem().getBarcode_ID());

                        itemNameTxtFld1.clear();
                        itemNameTxtFld1.setText(row.getItem().getItemName());

                        descriptionTxtFld1.clear();
                        descriptionTxtFld1.setText(
                                row.getItem().getDescription());

                        typeTxtFld1.clear();
                        typeTxtFld1.setText(row.getItem().getType());

                        totalQtyTxtFld1.clear();
                        totalQtyTxtFld1.setText(row.getItem().getTotalQuantity());

                        unitPriceTxtFld.clear();

                    }
                });
                return row;
            });

            saveBtn.setOnAction(e -> {
                if (!unitPriceTxtFld.getText().isEmpty() && !totalQtyTxtFld1.getText().isEmpty()) {
                    try {
                        ArrayList<ArrayList<String>> selectPacketInventoryItem = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT itemId FROM inventory WHERE barcode_ID = '" + barcodeTxtFld1.getText() + "'");
                        selectPacketInventoryItem.stream().forEach(
                                (selectPacketInventoryItemVal) -> {
                                    try {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE retialinventory SET unitPrice = '" + parseDouble(
                                                        unitPriceTxtFld.getDecimal()) + "', remainingQty = " + parseInt(totalQtyTxtFld1.getText()) + " "
                                                + "WHERE inventory_itemId = '" + parseInt(
                                                        selectPacketInventoryItemVal.get(
                                                                0)) + "' AND remainingQty > 0 ORDER BY inventory_itemId DESC LIMIT 1");
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(
                                                Meraki101.class.getName()).log(
                                                Level.SEVERE, null, ex);
                                    }
                                });
                        svc1.restart();
                        svc1.restart();
                        svc1.restart();
                        svc1.restart();

                        Alert exceptionAlert = new Alert(
                                Alert.AlertType.INFORMATION, "Item Updated!!",
                                ButtonType.OK);
                        exceptionAlert.showAndWait();
                        barcodeTxtFld1.clear();
                        itemNameTxtFld1.clear();
                        descriptionTxtFld1.clear();
                        typeTxtFld1.clear();
                        totalQtyTxtFld1.clear();
                        unitPriceTxtFld.clear();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
            });

            HBox vboxWhlSllAndSearch = new HBox();
            vboxWhlSllAndSearch.setId("hbox13");

            UpperCaseTextField searchWhlStockTxtFld = new UpperCaseTextField();
            searchWhlStockTxtFld.setPromptText("     Type To Search ");
            searchWhlStockTxtFld.setMaxWidth(gW * 0.15);
            vboxWhlSllAndSearch.getChildren().add(searchWhlStockTxtFld);

            searchWhlStockTxtFld.textProperty().addListener(e -> {
                if (searchWhlStockTxtFld.getText() != null) {
                    RetailSellConfig.setItems(RetailSellConfigItems);
                    final FilteredList<RetailSellConfigData> filteredList = RetailSellConfig.getItems().filtered(
                            p -> p.getItemName().contains(
                                    searchWhlStockTxtFld.getText()) || p.getBarcode_ID().contains(
                            searchWhlStockTxtFld.getText()) || p.getType().contains(
                            searchWhlStockTxtFld.getText()) || p.getDescription().contains(
                            searchWhlStockTxtFld.getText()));
                    RetailSellConfig.setItems(filteredList);

                }
            });

            GridPane RetailSaleGP = new GridPane();
            RetailSaleGP.setHgap(gW * 0.025);
            GridPane retailSellSearchAndTbl = new GridPane();
            retailSellSearchAndTbl.addRow(0, vboxWhlSllAndSearch);
            retailSellSearchAndTbl.addRow(1, RetailSellConfig);
            RetailSaleGP.addRow(0, editRetailSellGP, retailSellSearchAndTbl);

            return RetailSaleGP;
        }

    }

    public class records {

        private final Desktop desktop = Desktop.getDesktop();

        public BorderPane inventoryRecords() {

            Stage stage1 = new Stage();

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ////---Inventory Records Main Page Configuration
            BorderPane inventoryRecordsBP = new BorderPane();

            //selection menu
            Label menuTitleLbl = new Label("Selection Menu");
            HBox hboxMenuTitleLbl = new HBox();
            hboxMenuTitleLbl.setId("hbox13");
            hboxMenuTitleLbl.getChildren().add(menuTitleLbl);

            //toggle group for those buttons
            final ToggleGroup toggleGroup2 = new ToggleGroup();

            //radio buttons for selecting the kind os display
            RadioButton generalStockRBtn = new RadioButton("General Stock");
            generalStockRBtn.setToggleGroup(toggleGroup2);
            generalStockRBtn.setSelected(true);

            RadioButton removedStockRBtn = new RadioButton("Removed Stock");
            removedStockRBtn.setToggleGroup(toggleGroup2);

            //Foreword Before use of the stock analysis
            TextArea forewordTextArea = new TextArea();
            forewordTextArea.setId("textArea1");
            forewordTextArea.setMinSize(gW * 0.18, gH * 0.6);
            forewordTextArea.setMaxSize(gW * 0.18, gH * 0.6);
            forewordTextArea.setEditable(false);
            forewordTextArea.setFocusTraversable(false);
            forewordTextArea.setText("                 HELP    \n"
                    + "To configure the graphs/chart, \n"
                    + "make the selections within \n"
                    + "the graph controls as desired \n"
                    + "for the desired representation.\n\n"
                    + "Different combinations can be \n"
                    + "used to obtain a more precise \n"
                    + "representation.\n\n"
                    + "However, the user may not make \n"
                    + "a selection when general \n"
                    + "representation is required\n\n"
                    + "Select 'General Stock' to analyze \n"
                    + "items in stock and 'Removed Stock'\n"
                    + "to analyze removed stock");

            HBox hboxForeword = new HBox();
            hboxForeword.getChildren().add(forewordTextArea);

            VBox vboxSelectInventoryRecords = new VBox();
            vboxSelectInventoryRecords.setId("vbox4");
            vboxSelectInventoryRecords.setMinWidth(gW * 0.2);
            vboxSelectInventoryRecords.getChildren().addAll(hboxMenuTitleLbl,
                    generalStockRBtn, removedStockRBtn, hboxForeword);

            //seeting the menu on the left of the inventory page
            inventoryRecordsBP.setLeft(vboxSelectInventoryRecords);

            //main configuration/container for charts
            VBox vboxChartsMajContainer = new VBox();
            vboxChartsMajContainer.setId("vbox3");

            ////---chart area
            //setting Title
            Label chartsTitleLbl = new Label("Inventory/Stock Analyzer");

            //HBox for the title
            HBox hboxChartTitleLbl = new HBox();
            hboxChartTitleLbl.setId("hbox13");
            hboxChartTitleLbl.getChildren().add(chartsTitleLbl);

            ///Toggle group for the radio buttons
            final ToggleGroup radioBtnsGroup = new ToggleGroup();

            //radio butons for selecting the charts to show
            RadioButton barGraphRBtn = new RadioButton("Bar Graph");
            barGraphRBtn.setToggleGroup(radioBtnsGroup);
            barGraphRBtn.setSelected(true);

            RadioButton lineGraphRBtn = new RadioButton("Line Graph");
            lineGraphRBtn.setToggleGroup(radioBtnsGroup);

            RadioButton pieChartRBtn = new RadioButton("Pie Chart");
            pieChartRBtn.setToggleGroup(radioBtnsGroup);

            //HBox for the ToggleGroup
            HBox hboxToggleGroup = new HBox();
            hboxToggleGroup.setId("hbox13");
            hboxToggleGroup.getChildren().addAll(barGraphRBtn, lineGraphRBtn,
                    pieChartRBtn);

            //the bar graph configuration
            final CategoryAxis xAxis1 = new CategoryAxis();
            final NumberAxis yAxis1 = new NumberAxis();

            xAxis1.setLabel("Inventory Items");
            yAxis1.setLabel("Quantity");

            final BarChart<String, Number> inventoryRecordsBarChart = new BarChart<>(
                    xAxis1, yAxis1);
            inventoryRecordsBarChart.setMinWidth(gW * 0.45);

            inventoryRecordsBarChart.setTitle("Quantity Against Items");

            ///the line Graph
            final CategoryAxis xAxis2 = new CategoryAxis();
            final NumberAxis yAxis2 = new NumberAxis();

            xAxis2.setLabel("Inventory Items");
            yAxis2.setLabel("Quantity");

            final LineChart<String, Number> inventoryRecordsLineChart = new LineChart<>(
                    xAxis2, yAxis2);
            inventoryRecordsLineChart.setMinWidth(gW * 0.45);

            inventoryRecordsLineChart.setTitle("Quantity Against Items");

            ///The PieChart
            final PieChart inventoryRecordsPieChart = new PieChart();
            inventoryRecordsPieChart.setTitle("Inventory Constitution");
            inventoryRecordsPieChart.setMinWidth(gW * 0.45);

            HBox hboxChartArea = new HBox();

            //just to cater for the already selected radio button bar chart
            hboxChartArea.getChildren().clear();
            hboxChartArea.getChildren().add(inventoryRecordsBarChart);

            radioBtnsGroup.selectedToggleProperty().addListener(
                    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                        if (radioBtnsGroup.getSelectedToggle() != null) {
                            if (barGraphRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        inventoryRecordsBarChart);
                            } else if (lineGraphRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        inventoryRecordsLineChart);
                            } else if (pieChartRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        inventoryRecordsPieChart);
                            }
                        }
                    });

            //the charts section wrapping up as one thing
            vboxChartsMajContainer.getChildren().addAll(hboxChartTitleLbl,
                    hboxToggleGroup, hboxChartArea);

            ////---the summary table
            ////---HBox for the charts and the summaryTable
            HBox hboxChartsSummaryTbl = new HBox();

            //table
            TableView<InventorySummaryData> InventorySummary = new TableView<>();
            ObservableList<InventorySummaryData> InventorySummaryItems = FXCollections.observableArrayList();
            InventorySummary.setEditable(false);
            InventorySummary.setPrefHeight(gH * 0.56);
            InventorySummary.setItems(InventorySummaryItems);

            InventorySummary.getColumns().clear();

            TableColumn InventorySummary_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            InventorySummary_Barcode_ID.setPrefWidth(gW * 0.1);
            InventorySummary_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<InventorySummaryData, String>(
                            "Barcode/ID"));
            InventorySummary.getColumns().addAll(InventorySummary_Barcode_ID);

            TableColumn InventorySummary_ItemName = new TableColumn("ItemName");
            InventorySummary_ItemName.setPrefWidth(gW * 0.1);
            InventorySummary_ItemName.setCellValueFactory(
                    new PropertyValueFactory<InventorySummaryData, String>(
                            "ItemName"));
            InventorySummary.getColumns().addAll(InventorySummary_ItemName);

            TableColumn InventorySummary_TotalValue = new TableColumn(
                    "TotalValue");
            InventorySummary_TotalValue.setPrefWidth(gW * 0.1);
            InventorySummary_TotalValue.setCellValueFactory(
                    new PropertyValueFactory<InventorySummaryData, String>(
                            "TotalValue"));
            InventorySummary.getColumns().addAll(InventorySummary_TotalValue);

            //table into HBox with Title Label
            Label summaryTableTitleLbl = new Label("Summary Table");

            HBox hboxSummaryTblTitle = new HBox();
            hboxSummaryTblTitle.setId("hbox13");
            hboxSummaryTblTitle.getChildren().add(summaryTableTitleLbl);
            hboxChartsSummaryTbl.getChildren().add(InventorySummary);

            VBox vboxFinalSummaryTbl = new VBox();
            vboxFinalSummaryTbl.setId("vbox3");
            vboxFinalSummaryTbl.getChildren().addAll(hboxSummaryTblTitle,
                    hboxChartsSummaryTbl);

            //final center HBox
            HBox hboxChartAndSummary = new HBox();
            hboxChartAndSummary.setId("hbox7");
            hboxChartAndSummary.getChildren().addAll(vboxChartsMajContainer,
                    vboxFinalSummaryTbl);

            ////----Confgurations for the lower central part... these configure the graphs
            //Title for the configurations
            Label configurationsTitleLbl = new Label("Graph Controls");

            //hbox graph control container
            HBox hboxGraphTitlelbl = new HBox();
            hboxGraphTitlelbl.setId("hbox13");
            hboxGraphTitlelbl.getChildren().add(configurationsTitleLbl);

            //Labels for the Dates
            Label initialDate1Lbl = new Label("Starting Date:");
            Label finalDate1Lbl = new Label("End Date:");

            //Date Comparison for the graph controls
            DatePicker initialDatePk1 = new DatePicker();
            DatePicker finalDatePk1 = new DatePicker();

            //DatePicker containers
            HBox hboxInitialDate1 = new HBox();
            hboxInitialDate1.setId("hbox26");
            HBox hboxFinalDate1 = new HBox();
            hboxFinalDate1.setId("hbox26");
            HBox hboxFinalDates1Config = new HBox();
            hboxFinalDates1Config.setId("hbox14");

            //placing all of them in their containers
            hboxInitialDate1.getChildren().addAll(initialDate1Lbl,
                    initialDatePk1);
            hboxFinalDate1.getChildren().addAll(finalDate1Lbl, finalDatePk1);
            hboxFinalDates1Config.getChildren().addAll(hboxInitialDate1,
                    hboxFinalDate1);

            ///---Configuration by Quantity
            //Labels
            Label minimumQty1Lbl = new Label("Minimum Quantity:");
            Label maximumQty1Lbl = new Label("Maximum Quantity:");

            //TextFields for the Quantity Values
            DecimalTextField minimumQty1TxtFld = new DecimalTextField();
            DecimalTextField minimumQty2TxtFld = new DecimalTextField();

            //HBoxes for the quantity controls
            HBox hboxMinimumQty1 = new HBox();
            hboxMinimumQty1.setId("hbox26");
            HBox hboxMaximumQty1 = new HBox();
            hboxMaximumQty1.setId("hbox26");
            HBox hboxFinalQty1 = new HBox();
            hboxFinalQty1.setId("hbox15");

            //placing things in their containers
            hboxMinimumQty1.getChildren().addAll(minimumQty1Lbl,
                    minimumQty1TxtFld);
            hboxMaximumQty1.getChildren().addAll(maximumQty1Lbl,
                    minimumQty2TxtFld);
            hboxFinalQty1.getChildren().addAll(hboxMinimumQty1, hboxMaximumQty1);

            ///---configuration by selling price
            //Labels
            Label minimumSellingP1Lbl = new Label("Minimum Selling Price:");
            Label mixamumSellingP1Lbl = new Label("Maximum Selling Price:");

            //TextFields for holding the values
            TextField minimumSellingP1TxtFld = new TextField();
            TextField maximumSellingP1TxtFld = new TextField();

            //HBoxes for the selling prices containers
            HBox hboxMinimumSellingP1 = new HBox();
            hboxMinimumSellingP1.setId("hbox26");
            HBox hboxMaximumSellingP1 = new HBox();
            hboxMaximumSellingP1.setId("hbox26");
            HBox hboxSellingPConfig1 = new HBox();
            hboxSellingPConfig1.setId("hbox16");

            ///Placing the things in their containers
            hboxMinimumSellingP1.getChildren().addAll(minimumSellingP1Lbl,
                    minimumSellingP1TxtFld);
            hboxMaximumSellingP1.getChildren().addAll(mixamumSellingP1Lbl,
                    maximumSellingP1TxtFld);
            hboxSellingPConfig1.getChildren().addAll(hboxMinimumSellingP1,
                    hboxMaximumSellingP1);

            ////---placing the chart configuration controls in a general container
            VBox vboxConfigTools = new VBox();
            vboxConfigTools.setId("vbox3");
            vboxConfigTools.setPrefWidth(gW * 0.45);
            vboxConfigTools.getChildren().addAll(hboxGraphTitlelbl,
                    hboxFinalDates1Config, hboxFinalQty1, hboxSellingPConfig1);

            //prining and report configurations
            //Report printer title
            Label printer1Lbl = new Label("Report Printer");

            ///HBox for the printer Title
            HBox hboxPrinter1Lbl = new HBox();
            hboxPrinter1Lbl.setId("hbox13");
            hboxPrinter1Lbl.getChildren().add(printer1Lbl);

            //Configure Report Title
            Label reportTitle1Lbl = new Label("Report Title:");
            //Text Field For Report title
            UpperCaseTextField reportTitle1TxtFld = new UpperCaseTextField();
            reportTitle1TxtFld.setPrefWidth(gW * 0.25);

            //HBox for the report Title
            HBox hboxReportTitle = new HBox();
            hboxReportTitle.setId("hbox26");
            hboxReportTitle.getChildren().addAll(reportTitle1Lbl,
                    reportTitle1TxtFld);

            //Attachment
            Hyperlink attachment1HLink = new Hyperlink("Attach Document");
            //File Chooser
            FileChooser chooseAttachment1FChooser = new FileChooser();

            //HBox for the file chooser and containment
            HBox hboxFileChooserHLink = new HBox();
            hboxFileChooserHLink.setAlignment(Pos.CENTER_RIGHT);
            hboxFileChooserHLink.getChildren().addAll(attachment1HLink);

            //setting the action for the hyperlink
            attachment1HLink.setOnAction((ActionEvent e) -> {
                chooseAttachment1FChooser.setTitle("Attach Document");
                File fileAttachment1 = chooseAttachment1FChooser.showOpenDialog(
                        stage1);
                if (fileAttachment1 != null) {
                    openFile(fileAttachment1);
                }
            });

            //print button
            Button print1Btn = new Button("Print Report");
            print1Btn.getStyleClass().add("btn-info");
            HBox hboxPrint1Btn = new HBox();
            hboxPrint1Btn.setAlignment(Pos.CENTER_RIGHT);
            hboxPrint1Btn.getChildren().add(print1Btn);

            ///---final containment for the report printer
            VBox vboxReportsPrinter = new VBox();
            vboxReportsPrinter.setId("vbox3");
            vboxReportsPrinter.setPrefWidth(gW * 0.3);
            vboxReportsPrinter.getChildren().addAll(hboxPrinter1Lbl,
                    hboxReportTitle, hboxFileChooserHLink, hboxPrint1Btn);

            HBox hboxReportsAndTools = new HBox();
            hboxReportsAndTools.setId("hbox7");
            hboxReportsAndTools.getChildren().addAll(vboxConfigTools,
                    vboxReportsPrinter);

            ///placing the charts and config tools in their VBox
            VBox vboxChartAnsTools = new VBox();
            vboxChartAnsTools.getChildren().addAll(hboxChartAndSummary,
                    hboxReportsAndTools);

            //BorderPane Setting Center
            inventoryRecordsBP.setCenter(vboxChartAnsTools);

            ///---filtering selection of them menu
            toggleGroup2.selectedToggleProperty().addListener(
                    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                        if (toggleGroup2.getSelectedToggle() != null) {
                            if (generalStockRBtn.isSelected()) {
                                Stage stage2 = new Stage();
                                ///--prerequisites
                                final Screen screen1 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds1 = screen1.getVisualBounds();
                                double gH1 = bounds1.getHeight();
                                double gW1 = bounds1.getWidth();
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer1 = new VBox();
                                vboxChartsMajContainer1.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl1 = new Label(
                                        "Inventory/Stock Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl1 = new HBox();
                                hboxChartTitleLbl1.setId("hbox13");
                                hboxChartTitleLbl1.getChildren().add(
                                        chartsTitleLbl1);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup1 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn1 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn1.setToggleGroup(radioBtnsGroup1);
                                barGraphRBtn1.setSelected(true);
                                RadioButton lineGraphRBtn1 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn1.setToggleGroup(radioBtnsGroup1);
                                RadioButton pieChartRBtn1 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn1.setToggleGroup(radioBtnsGroup1);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup1 = new HBox();
                                hboxToggleGroup1.setId("hbox13");
                                hboxToggleGroup1.getChildren().addAll(
                                        barGraphRBtn1, lineGraphRBtn1,
                                        pieChartRBtn1);
                                //the bar graph configuration
                                final CategoryAxis xAxis3 = new CategoryAxis();
                                final NumberAxis yAxis3 = new NumberAxis();
                                xAxis3.setLabel("Inventory Items");
                                yAxis3.setLabel("Quantity");
                                final BarChart<String, Number> inventoryRecordsBarChart1 = new BarChart<>(
                                        xAxis3, yAxis3);
                                inventoryRecordsBarChart1.setMinWidth(gW1 * 0.45);
                                inventoryRecordsBarChart1.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis4 = new CategoryAxis();
                                final NumberAxis yAxis4 = new NumberAxis();
                                xAxis4.setLabel("Inventory Items");
                                yAxis4.setLabel("Quantity");
                                final LineChart<String, Number> inventoryRecordsLineChart1 = new LineChart<>(
                                        xAxis4, yAxis4);
                                inventoryRecordsLineChart1.setMinWidth(
                                        gW1 * 0.45);
                                inventoryRecordsLineChart1.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart inventoryRecordsPieChart1 = new PieChart();
                                inventoryRecordsPieChart1.setTitle(
                                        "Inventory Constitution");
                                inventoryRecordsPieChart1.setMinWidth(gW1 * 0.45);
                                HBox hboxChartArea1 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea1.getChildren().clear();
                                hboxChartArea1.getChildren().add(
                                        inventoryRecordsBarChart1);
                                radioBtnsGroup1.selectedToggleProperty().addListener(
                                        new ChangeListener<Toggle>() {
                                    public void changed(
                                            ObservableValue<? extends Toggle> ov,
                                            Toggle old_toggle, Toggle new_toggle) {
                                        if (radioBtnsGroup1.getSelectedToggle() != null) {
                                            if (barGraphRBtn1.isSelected()) {
                                                hboxChartArea1.getChildren().clear();
                                                hboxChartArea1.getChildren().add(
                                                        inventoryRecordsBarChart1);
                                            } else if (lineGraphRBtn1.isSelected()) {
                                                hboxChartArea1.getChildren().clear();
                                                hboxChartArea1.getChildren().add(
                                                        inventoryRecordsLineChart1);
                                            } else if (pieChartRBtn1.isSelected()) {
                                                hboxChartArea1.getChildren().clear();
                                                hboxChartArea1.getChildren().add(
                                                        inventoryRecordsPieChart1);
                                            }
                                        }
                                    }
                                });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer1.getChildren().addAll(
                                        hboxChartTitleLbl1, hboxToggleGroup1,
                                        hboxChartArea1);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl1 = new HBox();
                                //table
                                TableView<InventorySummaryData> InventorySummary1 = new TableView<>();
                                ObservableList<InventorySummaryData> InventorySummaryItems1 = FXCollections.observableArrayList();
                                InventorySummary1.setEditable(false);
                                InventorySummary1.setPrefHeight(gH1 * 0.56);
                                InventorySummary1.setItems(
                                        InventorySummaryItems1);
                                InventorySummary1.getColumns().clear();
                                TableColumn InventorySummary_Barcode_ID1 = new TableColumn(
                                        "Barcode/ID");
                                InventorySummary_Barcode_ID1.setPrefWidth(
                                        gW1 * 0.1);
                                InventorySummary_Barcode_ID1.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "Barcode/ID"));
                                InventorySummary1.getColumns().addAll(
                                        InventorySummary_Barcode_ID1);
                                TableColumn InventorySummary_ItemName1 = new TableColumn(
                                        "ItemName");
                                InventorySummary_ItemName1.setPrefWidth(
                                        gW1 * 0.1);
                                InventorySummary_ItemName1.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "ItemName"));
                                InventorySummary1.getColumns().addAll(
                                        InventorySummary_ItemName1);
                                TableColumn InventorySummary_TotalValue1 = new TableColumn(
                                        "TotalValue");
                                InventorySummary_TotalValue1.setPrefWidth(
                                        gW1 * 0.1);
                                InventorySummary_TotalValue1.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "TotalValue"));
                                InventorySummary1.getColumns().addAll(
                                        InventorySummary_TotalValue1);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl1 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle1 = new HBox();
                                hboxSummaryTblTitle1.setId("hbox13");
                                hboxSummaryTblTitle1.getChildren().add(
                                        summaryTableTitleLbl1);
                                hboxChartsSummaryTbl1.getChildren().add(
                                        InventorySummary1);
                                VBox vboxFinalSummaryTbl1 = new VBox();
                                vboxFinalSummaryTbl1.setId("vbox3");
                                vboxFinalSummaryTbl1.getChildren().addAll(
                                        hboxSummaryTblTitle1,
                                        hboxChartsSummaryTbl1);
                                //final center HBox
                                HBox hboxChartAndSummary1 = new HBox();
                                hboxChartAndSummary1.setId("hbox7");
                                hboxChartAndSummary1.getChildren().addAll(
                                        vboxChartsMajContainer1,
                                        vboxFinalSummaryTbl1);
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl1 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl1 = new HBox();
                                hboxGraphTitlelbl1.setId("hbox13");
                                hboxGraphTitlelbl1.getChildren().add(
                                        configurationsTitleLbl1);
                                //Labels for the Dates
                                Label initialDate1Lbl1 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl1 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk2 = new DatePicker();
                                DatePicker finalDatePk2 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate2 = new HBox();
                                hboxInitialDate2.setId("hbox26");
                                HBox hboxFinalDate2 = new HBox();
                                hboxFinalDate2.setId("hbox26");
                                HBox hboxFinalDates1Config1 = new HBox();
                                hboxFinalDates1Config1.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate2.getChildren().addAll(
                                        initialDate1Lbl1, initialDatePk2);
                                hboxFinalDate2.getChildren().addAll(
                                        finalDate1Lbl1, finalDatePk2);
                                hboxFinalDates1Config1.getChildren().addAll(
                                        hboxInitialDate2, hboxFinalDate2);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl1 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl1 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld1 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld1 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty2 = new HBox();
                                hboxMinimumQty2.setId("hbox26");
                                HBox hboxMaximumQty2 = new HBox();
                                hboxMaximumQty2.setId("hbox26");
                                HBox hboxFinalQty2 = new HBox();
                                hboxFinalQty2.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty2.getChildren().addAll(
                                        minimumQty1Lbl1, minimumQty1TxtFld1);
                                hboxMaximumQty2.getChildren().addAll(
                                        maximumQty1Lbl1, minimumQty2TxtFld1);
                                hboxFinalQty2.getChildren().addAll(
                                        hboxMinimumQty2, hboxMaximumQty2);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl1 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl1 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld1 = new TextField();
                                TextField maximumSellingP1TxtFld1 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP2 = new HBox();
                                hboxMinimumSellingP2.setId("hbox26");
                                HBox hboxMaximumSellingP2 = new HBox();
                                hboxMaximumSellingP2.setId("hbox26");
                                HBox hboxSellingPConfig2 = new HBox();
                                hboxSellingPConfig2.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP2.getChildren().addAll(
                                        minimumSellingP1Lbl1,
                                        minimumSellingP1TxtFld1);
                                hboxMaximumSellingP2.getChildren().addAll(
                                        mixamumSellingP1Lbl1,
                                        maximumSellingP1TxtFld1);
                                hboxSellingPConfig2.getChildren().addAll(
                                        hboxMinimumSellingP2,
                                        hboxMaximumSellingP2);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools1 = new VBox();
                                vboxConfigTools1.setId("vbox3");
                                vboxConfigTools1.setPrefWidth(gW1 * 0.45);
                                vboxConfigTools1.getChildren().addAll(
                                        hboxGraphTitlelbl1,
                                        hboxFinalDates1Config1, hboxFinalQty2,
                                        hboxSellingPConfig2);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl1 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl1 = new HBox();
                                hboxPrinter1Lbl1.setId("hbox13");
                                hboxPrinter1Lbl1.getChildren().add(printer1Lbl1);
                                //Configure Report Title
                                Label reportTitle1Lbl1 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld1 = new UpperCaseTextField();
                                reportTitle1TxtFld1.setPrefWidth(gW1 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle1 = new HBox();
                                hboxReportTitle1.getChildren().addAll(
                                        reportTitle1Lbl1, reportTitle1TxtFld1);
                                //Attachment
                                Hyperlink attachment1HLink1 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser1 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink1 = new HBox();
                                hboxFileChooserHLink1.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink1.getChildren().addAll(
                                        attachment1HLink1);
                                //setting the action for the hyperlink
                                attachment1HLink1.setOnAction(
                                        new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        chooseAttachment1FChooser1.setTitle(
                                                "Attach Document");
                                        File fileAttachment1 = chooseAttachment1FChooser1.showOpenDialog(
                                                stage2);
                                        if (fileAttachment1 != null) {
                                            openFile(fileAttachment1);
                                        }
                                    }
                                });
                                //print button
                                Button print1Btn1 = new Button("Print Report");
                                print1Btn.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn1 = new HBox();
                                hboxPrint1Btn1.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn1.getChildren().add(print1Btn1);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter1 = new VBox();
                                vboxReportsPrinter1.setId("vbox3");
                                vboxReportsPrinter1.setPrefWidth(gW1 * 0.3);
                                vboxReportsPrinter1.getChildren().addAll(
                                        hboxPrinter1Lbl1, hboxReportTitle1,
                                        hboxFileChooserHLink1, hboxPrint1Btn1);
                                HBox hboxReportsAndTools1 = new HBox();
                                hboxReportsAndTools1.setId("hbox7");
                                hboxReportsAndTools1.getChildren().addAll(
                                        vboxConfigTools1, vboxReportsPrinter1);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools1 = new VBox();
                                vboxChartAnsTools1.getChildren().addAll(
                                        hboxChartAndSummary1,
                                        hboxReportsAndTools1);
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools1);
                            } else if (removedStockRBtn.isSelected()) {
                                Stage stage3 = new Stage();
                                ///--prerequisites
                                final Screen screen2 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds2 = screen2.getVisualBounds();
                                double gH2 = bounds2.getHeight();
                                double gW2 = bounds2.getWidth();
                                //selection menu
                                Label menuTitleLbl1 = new Label("Selection Menu");
                                HBox hboxMenuTitleLbl1 = new HBox();
                                hboxMenuTitleLbl1.setId("hbox13");
                                hboxMenuTitleLbl1.getChildren().add(
                                        menuTitleLbl1);
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer2 = new VBox();
                                vboxChartsMajContainer2.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl2 = new Label(
                                        "Removed Inventory/Stock Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl2 = new HBox();
                                hboxChartTitleLbl2.setId("hbox13");
                                hboxChartTitleLbl2.getChildren().add(
                                        chartsTitleLbl2);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup2 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn2 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn2.setToggleGroup(radioBtnsGroup2);
                                barGraphRBtn2.setSelected(true);
                                RadioButton lineGraphRBtn2 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn2.setToggleGroup(radioBtnsGroup2);
                                RadioButton pieChartRBtn2 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn2.setToggleGroup(radioBtnsGroup2);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup2 = new HBox();
                                hboxToggleGroup2.setId("hbox13");
                                hboxToggleGroup2.getChildren().addAll(
                                        barGraphRBtn2, lineGraphRBtn2,
                                        pieChartRBtn2);
                                //the bar graph configuration
                                final CategoryAxis xAxis5 = new CategoryAxis();
                                final NumberAxis yAxis5 = new NumberAxis();
                                xAxis5.setLabel("Removed Items");
                                yAxis5.setLabel("Quantity");
                                final BarChart<String, Number> inventoryRecordsBarChart2 = new BarChart<>(
                                        xAxis5, yAxis5);
                                inventoryRecordsBarChart2.setMinWidth(gW2 * 0.45);
                                inventoryRecordsBarChart2.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis6 = new CategoryAxis();
                                final NumberAxis yAxis6 = new NumberAxis();
                                xAxis6.setLabel("Removed Items");
                                yAxis6.setLabel("Quantity");
                                final LineChart<String, Number> inventoryRecordsLineChart2 = new LineChart<>(
                                        xAxis6, yAxis6);
                                inventoryRecordsLineChart2.setMinWidth(
                                        gW2 * 0.45);
                                inventoryRecordsLineChart2.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart inventoryRecordsPieChart2 = new PieChart();
                                inventoryRecordsPieChart2.setTitle(
                                        "Inventory Constitution");
                                inventoryRecordsPieChart2.setMinWidth(gW2 * 0.45);
                                HBox hboxChartArea2 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea2.getChildren().clear();
                                hboxChartArea2.getChildren().add(
                                        inventoryRecordsBarChart2);
                                radioBtnsGroup2.selectedToggleProperty().addListener(
                                        new ChangeListener<Toggle>() {
                                    public void changed(
                                            ObservableValue<? extends Toggle> ov,
                                            Toggle old_toggle, Toggle new_toggle) {
                                        if (radioBtnsGroup2.getSelectedToggle() != null) {
                                            if (barGraphRBtn2.isSelected()) {
                                                hboxChartArea2.getChildren().clear();
                                                hboxChartArea2.getChildren().add(
                                                        inventoryRecordsBarChart2);
                                            } else if (lineGraphRBtn2.isSelected()) {
                                                hboxChartArea2.getChildren().clear();
                                                hboxChartArea2.getChildren().add(
                                                        inventoryRecordsLineChart2);
                                            } else if (pieChartRBtn2.isSelected()) {
                                                hboxChartArea2.getChildren().clear();
                                                hboxChartArea2.getChildren().add(
                                                        inventoryRecordsPieChart2);
                                            }
                                        }
                                    }
                                });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer2.getChildren().addAll(
                                        hboxChartTitleLbl2, hboxToggleGroup2,
                                        hboxChartArea2);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl2 = new HBox();
                                //table
                                TableView<InventorySummaryData> InventorySummary2 = new TableView<>();
                                ObservableList<InventorySummaryData> InventorySummaryItems2 = FXCollections.observableArrayList();
                                InventorySummary2.setEditable(false);
                                InventorySummary2.setPrefHeight(gH2 * 0.56);
                                InventorySummary2.setItems(
                                        InventorySummaryItems2);
                                InventorySummary2.getColumns().clear();
                                TableColumn InventorySummary_Barcode_ID2 = new TableColumn(
                                        "Barcode/ID");
                                InventorySummary_Barcode_ID2.setPrefWidth(
                                        gW2 * 0.1);
                                InventorySummary_Barcode_ID2.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "Barcode/ID"));
                                InventorySummary2.getColumns().addAll(
                                        InventorySummary_Barcode_ID2);
                                TableColumn InventorySummary_ItemName2 = new TableColumn(
                                        "ItemName");
                                InventorySummary_ItemName2.setPrefWidth(
                                        gW2 * 0.1);
                                InventorySummary_ItemName2.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "ItemName"));
                                InventorySummary2.getColumns().addAll(
                                        InventorySummary_ItemName2);
                                TableColumn InventorySummary_TotalValue2 = new TableColumn(
                                        "TotalValue");
                                InventorySummary_TotalValue2.setPrefWidth(
                                        gW2 * 0.1);
                                InventorySummary_TotalValue2.setCellValueFactory(
                                        new PropertyValueFactory<InventorySummaryData, String>(
                                                "TotalValue"));
                                InventorySummary2.getColumns().addAll(
                                        InventorySummary_TotalValue2);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl2 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle2 = new HBox();
                                hboxSummaryTblTitle2.setId("hbox13");
                                hboxSummaryTblTitle2.getChildren().add(
                                        summaryTableTitleLbl2);
                                hboxChartsSummaryTbl2.getChildren().add(
                                        InventorySummary2);
                                VBox vboxFinalSummaryTbl2 = new VBox();
                                vboxFinalSummaryTbl2.setId("vbox3");
                                vboxFinalSummaryTbl2.getChildren().addAll(
                                        hboxSummaryTblTitle2,
                                        hboxChartsSummaryTbl2);
                                //final center HBox
                                HBox hboxChartAndSummary2 = new HBox();
                                hboxChartAndSummary2.setId("hbox7");
                                hboxChartAndSummary2.getChildren().addAll(
                                        vboxChartsMajContainer2,
                                        vboxFinalSummaryTbl2);
                                ////////////////////////
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl2 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl2 = new HBox();
                                hboxGraphTitlelbl2.setId("hbox13");
                                hboxGraphTitlelbl2.getChildren().add(
                                        configurationsTitleLbl2);
                                //Labels for the Dates
                                Label initialDate1Lbl2 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl2 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk3 = new DatePicker();
                                DatePicker finalDatePk3 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate3 = new HBox();
                                hboxInitialDate3.setId("hbox26");
                                HBox hboxFinalDate3 = new HBox();
                                hboxFinalDate3.setId("hbox26");
                                HBox hboxFinalDates1Config2 = new HBox();
                                hboxFinalDates1Config2.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate3.getChildren().addAll(
                                        initialDate1Lbl2, initialDatePk3);
                                hboxFinalDate3.getChildren().addAll(
                                        finalDate1Lbl2, finalDatePk3);
                                hboxFinalDates1Config2.getChildren().addAll(
                                        hboxInitialDate3, hboxFinalDate3);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl2 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl2 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld2 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld2 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty3 = new HBox();
                                hboxMinimumQty3.setId("hbox26");
                                HBox hboxMaximumQty3 = new HBox();
                                hboxMaximumQty3.setId("hbox26");
                                HBox hboxFinalQty3 = new HBox();
                                hboxFinalQty3.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty3.getChildren().addAll(
                                        minimumQty1Lbl2, minimumQty1TxtFld2);
                                hboxMaximumQty3.getChildren().addAll(
                                        maximumQty1Lbl2, minimumQty2TxtFld2);
                                hboxFinalQty3.getChildren().addAll(
                                        hboxMinimumQty3, hboxMaximumQty3);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl2 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl2 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld2 = new TextField();
                                TextField maximumSellingP1TxtFld2 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP3 = new HBox();
                                hboxMinimumSellingP3.setId("hbox26");
                                HBox hboxMaximumSellingP3 = new HBox();
                                hboxMaximumSellingP3.setId("hbox26");
                                HBox hboxSellingPConfig3 = new HBox();
                                hboxSellingPConfig3.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP3.getChildren().addAll(
                                        minimumSellingP1Lbl2,
                                        minimumSellingP1TxtFld2);
                                hboxMaximumSellingP3.getChildren().addAll(
                                        mixamumSellingP1Lbl2,
                                        maximumSellingP1TxtFld2);
                                hboxSellingPConfig3.getChildren().addAll(
                                        hboxMinimumSellingP3,
                                        hboxMaximumSellingP3);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools2 = new VBox();
                                vboxConfigTools2.setId("vbox3");
                                vboxConfigTools2.setPrefWidth(gW2 * 0.45);
                                vboxConfigTools2.getChildren().addAll(
                                        hboxGraphTitlelbl2,
                                        hboxFinalDates1Config2, hboxFinalQty3,
                                        hboxSellingPConfig3);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl2 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl2 = new HBox();
                                hboxPrinter1Lbl2.setId("hbox13");
                                hboxPrinter1Lbl2.getChildren().add(printer1Lbl2);
                                //Configure Report Title
                                Label reportTitle1Lbl2 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld2 = new UpperCaseTextField();
                                reportTitle1TxtFld2.setPrefWidth(gW2 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle2 = new HBox();
                                hboxReportTitle2.getChildren().addAll(
                                        reportTitle1Lbl2, reportTitle1TxtFld2);
                                //Attachment
                                Hyperlink attachment1HLink2 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser2 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink2 = new HBox();
                                hboxFileChooserHLink2.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink2.getChildren().addAll(
                                        attachment1HLink2);
                                //setting the action for the hyperlink
                                attachment1HLink2.setOnAction(
                                        new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        chooseAttachment1FChooser2.setTitle(
                                                "Attach Document");
                                        File fileAttachment1 = chooseAttachment1FChooser2.showOpenDialog(
                                                stage3);
                                        if (fileAttachment1 != null) {
                                            openFile(fileAttachment1);
                                        }
                                    }
                                });
                                //print button
                                Button print1Btn2 = new Button("Print Report");
                                print1Btn2.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn2 = new HBox();
                                hboxPrint1Btn2.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn2.getChildren().add(print1Btn2);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter2 = new VBox();
                                vboxReportsPrinter2.setId("vbox3");
                                vboxReportsPrinter2.setPrefWidth(gW2 * 0.3);
                                vboxReportsPrinter2.getChildren().addAll(
                                        hboxPrinter1Lbl2, hboxReportTitle2,
                                        hboxFileChooserHLink2, hboxPrint1Btn2);
                                HBox hboxReportsAndTools2 = new HBox();
                                hboxReportsAndTools2.setId("hbox7");
                                hboxReportsAndTools2.getChildren().addAll(
                                        vboxConfigTools2, vboxReportsPrinter2);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools2 = new VBox();
                                vboxChartAnsTools2.getChildren().addAll(
                                        hboxChartAndSummary2,
                                        hboxReportsAndTools2);
                                ////////////////////////
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools2);
                            }
                        }
                    });

            return inventoryRecordsBP;
        }

        public BorderPane salesRecords() {
            Stage stage1 = new Stage();

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ////---Inventory Records Main Page Configuration
            BorderPane inventoryRecordsBP = new BorderPane();

            //selection menu
            Label menuTitleLbl = new Label("Selection Menu");
            HBox hboxMenuTitleLbl = new HBox();
            hboxMenuTitleLbl.setId("hbox13");
            hboxMenuTitleLbl.getChildren().add(menuTitleLbl);

            //toggle group for those buttons
            final ToggleGroup toggleGroup2 = new ToggleGroup();

            //radio buttons for selecting the kind os display
            RadioButton generalSalesRBtn = new RadioButton("General Sales");
            generalSalesRBtn.setToggleGroup(toggleGroup2);
            generalSalesRBtn.setSelected(true);

            RadioButton packetSalesRBtn = new RadioButton("Packet Sales");
            packetSalesRBtn.setToggleGroup(toggleGroup2);

            RadioButton retailSalesRBtn = new RadioButton("Retail Sales");
            retailSalesRBtn.setToggleGroup(toggleGroup2);

            RadioButton wholeSalesRBtn = new RadioButton("Whole Sales");
            wholeSalesRBtn.setToggleGroup(toggleGroup2);

            //Foreword Before use of the stock analysis
            TextArea forewordTextArea = new TextArea();
            forewordTextArea.setId("textArea1");
            forewordTextArea.setMinSize(gW * 0.18, gH * 0.55);
            forewordTextArea.setMaxSize(gW * 0.18, gH * 0.55);
            forewordTextArea.setEditable(false);
            forewordTextArea.setFocusTraversable(false);
            forewordTextArea.setText("                 HELP    \n"
                    + "To configure the graphs/chart, \n"
                    + "make the selections within \n"
                    + "the graph controls as desired \n"
                    + "for the desired representation.\n\n"
                    + "Different combinations can be \n"
                    + "used to obtain a more precise \n"
                    + "representation.\n\n"
                    + "However, the user may not make \n"
                    + "a selection when general \n"
                    + "representation is required\n\n"
                    + "Select 'General Sales' to analyze \n"
                    + "all items in Sale, 'Packet Sales'\n"
                    + "to analyze Packet in Sale,\n"
                    + "'Retail Sale' to analyze items sold\n"
                    + "in Retail and 'Whole Sale' to\n"
                    + "analyze items sold in Whole Sale");

            HBox hboxForeword = new HBox();
            hboxForeword.getChildren().add(forewordTextArea);

            VBox vboxSelectInventoryRecords = new VBox();
            vboxSelectInventoryRecords.setId("vbox4");
            vboxSelectInventoryRecords.setMinWidth(gW * 0.2);
            vboxSelectInventoryRecords.getChildren().addAll(hboxMenuTitleLbl,
                    generalSalesRBtn, packetSalesRBtn, retailSalesRBtn,
                    wholeSalesRBtn, hboxForeword);

            //seeting the menu on the left of the inventory page
            inventoryRecordsBP.setLeft(vboxSelectInventoryRecords);

            //main configuration/container for charts
            VBox vboxChartsMajContainer = new VBox();
            vboxChartsMajContainer.setId("vbox3");

            ////---chart area
            //setting Title
            Label chartsTitleLbl = new Label("General Sales Analyzer");

            //HBox for the title
            HBox hboxChartTitleLbl = new HBox();
            hboxChartTitleLbl.setId("hbox13");
            hboxChartTitleLbl.getChildren().add(chartsTitleLbl);

            ///Toggle group for the radio buttons
            final ToggleGroup radioBtnsGroup = new ToggleGroup();

            //radio butons for selecting the charts to show
            RadioButton barGraphRBtn = new RadioButton("Bar Graph");
            barGraphRBtn.setToggleGroup(radioBtnsGroup);
            barGraphRBtn.setSelected(true);

            RadioButton lineGraphRBtn = new RadioButton("Line Graph");
            lineGraphRBtn.setToggleGroup(radioBtnsGroup);

            RadioButton pieChartRBtn = new RadioButton("Pie Chart");
            pieChartRBtn.setToggleGroup(radioBtnsGroup);

            //HBox for the ToggleGroup
            HBox hboxToggleGroup = new HBox();
            hboxToggleGroup.setId("hbox13");
            hboxToggleGroup.getChildren().addAll(barGraphRBtn, lineGraphRBtn,
                    pieChartRBtn);

            //the bar graph configuration
            final CategoryAxis xAxis1 = new CategoryAxis();
            final NumberAxis yAxis1 = new NumberAxis();

            xAxis1.setLabel("Sold Items");
            yAxis1.setLabel("Quantity");

            final BarChart<String, Number> generalSalesRecordsBarChart = new BarChart<>(
                    xAxis1, yAxis1);
            generalSalesRecordsBarChart.setMinWidth(gW * 0.45);

            generalSalesRecordsBarChart.setTitle("Quantity Against Items");

            ///the line Graph
            final CategoryAxis xAxis2 = new CategoryAxis();
            final NumberAxis yAxis2 = new NumberAxis();

            xAxis2.setLabel("Sold Items");
            yAxis2.setLabel("Quantity");

            final LineChart<String, Number> generalSalesRecordsLineChart = new LineChart<>(
                    xAxis2, yAxis2);
            generalSalesRecordsLineChart.setMinWidth(gW * 0.45);

            generalSalesRecordsLineChart.setTitle("Quantity Against Items");

            ///The PieChart
            final PieChart generalSalesRecordsPieChart = new PieChart();
            generalSalesRecordsPieChart.setTitle("Sold Items Composition");
            generalSalesRecordsPieChart.setMinWidth(gW * 0.45);

            HBox hboxChartArea = new HBox();

            //just to cater for the already selected radio button bar chart
            hboxChartArea.getChildren().clear();
            hboxChartArea.getChildren().add(generalSalesRecordsBarChart);

            radioBtnsGroup.selectedToggleProperty().addListener(
                    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                        if (radioBtnsGroup.getSelectedToggle() != null) {
                            if (barGraphRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        generalSalesRecordsBarChart);
                            } else if (lineGraphRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        generalSalesRecordsLineChart);
                            } else if (pieChartRBtn.isSelected()) {
                                hboxChartArea.getChildren().clear();
                                hboxChartArea.getChildren().add(
                                        generalSalesRecordsPieChart);
                            }
                        }
                    });

            //the charts section wrapping up as one thing
            vboxChartsMajContainer.getChildren().addAll(hboxChartTitleLbl,
                    hboxToggleGroup, hboxChartArea);

            ////---the summary table
            ////---HBox for the charts and the summaryTable
            HBox hboxChartsSummaryTbl = new HBox();

            //table
            TableView<SummaryGeneralSalesData> SummaryGeneralSales = new TableView<>();
            ObservableList<SummaryGeneralSalesData> SummaryGeneralSalesItems = FXCollections.observableArrayList();
            SummaryGeneralSales.setEditable(false);
            SummaryGeneralSales.setPrefHeight(gH * 0.56);
            SummaryGeneralSales.setItems(SummaryGeneralSalesItems);

            SummaryGeneralSales.getColumns().clear();

            TableColumn SummaryGeneralSales_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            SummaryGeneralSales_Barcode_ID.setPrefWidth(gW * 0.1);
            SummaryGeneralSales_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<SummaryGeneralSalesData, String>(
                            "Barcode/ID"));
            SummaryGeneralSales.getColumns().addAll(
                    SummaryGeneralSales_Barcode_ID);

            TableColumn SummaryGeneralSales_ItemName = new TableColumn(
                    "ItemName");
            SummaryGeneralSales_ItemName.setPrefWidth(gW * 0.1);
            SummaryGeneralSales_ItemName.setCellValueFactory(
                    new PropertyValueFactory<SummaryGeneralSalesData, String>(
                            "ItemName"));
            SummaryGeneralSales.getColumns().addAll(SummaryGeneralSales_ItemName);

            TableColumn SummaryGeneralSales_Amount = new TableColumn("Amount");
            SummaryGeneralSales_Amount.setPrefWidth(gW * 0.1);
            SummaryGeneralSales_Amount.setCellValueFactory(
                    new PropertyValueFactory<SummaryGeneralSalesData, String>(
                            "Amount"));
            SummaryGeneralSales.getColumns().addAll(SummaryGeneralSales_Amount);

            //table into HBox with Title Label
            Label summaryTableTitleLbl = new Label("Summary Table");

            HBox hboxSummaryTblTitle = new HBox();
            hboxSummaryTblTitle.setId("hbox13");
            hboxSummaryTblTitle.getChildren().add(summaryTableTitleLbl);
            hboxChartsSummaryTbl.getChildren().add(SummaryGeneralSales);

            VBox vboxFinalSummaryTbl = new VBox();
            vboxFinalSummaryTbl.setId("vbox3");
            vboxFinalSummaryTbl.getChildren().addAll(hboxSummaryTblTitle,
                    hboxChartsSummaryTbl);

            //final center HBox
            HBox hboxChartAndSummary = new HBox();
            hboxChartAndSummary.setId("hbox7");
            hboxChartAndSummary.getChildren().addAll(vboxChartsMajContainer,
                    vboxFinalSummaryTbl);

            ////----Confgurations for the lower central part... these configure the graphs
            //Title for the configurations
            Label configurationsTitleLbl = new Label("Graph Controls");

            //hbox graph control container
            HBox hboxGraphTitlelbl = new HBox();
            hboxGraphTitlelbl.setId("hbox13");
            hboxGraphTitlelbl.getChildren().add(configurationsTitleLbl);

            //Labels for the Dates
            Label initialDate1Lbl = new Label("Starting Date:");
            Label finalDate1Lbl = new Label("End Date:");

            //Date Comparison for the graph controls
            DatePicker initialDatePk1 = new DatePicker();
            DatePicker finalDatePk1 = new DatePicker();

            //DatePicker containers
            HBox hboxInitialDate1 = new HBox();
            hboxInitialDate1.setId("hbox26");
            HBox hboxFinalDate1 = new HBox();
            hboxFinalDate1.setId("hbox26");
            HBox hboxFinalDates1Config = new HBox();
            hboxFinalDates1Config.setId("hbox14");

            //placing all of them in their containers
            hboxInitialDate1.getChildren().addAll(initialDate1Lbl,
                    initialDatePk1);
            hboxFinalDate1.getChildren().addAll(finalDate1Lbl, finalDatePk1);
            hboxFinalDates1Config.getChildren().addAll(hboxInitialDate1,
                    hboxFinalDate1);

            ///---Configuration by Quantity
            //Labels
            Label minimumQty1Lbl = new Label("Minimum Quantity:");
            Label maximumQty1Lbl = new Label("Maximum Quantity:");

            //TextFields for the Quantity Values
            DecimalTextField minimumQty1TxtFld = new DecimalTextField();
            DecimalTextField minimumQty2TxtFld = new DecimalTextField();

            //HBoxes for the quantity controls
            HBox hboxMinimumQty1 = new HBox();
            hboxMinimumQty1.setId("hbox26");
            HBox hboxMaximumQty1 = new HBox();
            hboxMaximumQty1.setId("hbox26");
            HBox hboxFinalQty1 = new HBox();
            hboxFinalQty1.setId("hbox15");

            //placing things in their containers
            hboxMinimumQty1.getChildren().addAll(minimumQty1Lbl,
                    minimumQty1TxtFld);
            hboxMaximumQty1.getChildren().addAll(maximumQty1Lbl,
                    minimumQty2TxtFld);
            hboxFinalQty1.getChildren().addAll(hboxMinimumQty1, hboxMaximumQty1);

            ///---configuration by selling price
            //Labels
            Label minimumSellingP1Lbl = new Label("Minimum Selling Price:");
            Label mixamumSellingP1Lbl = new Label("Maximum Selling Price:");

            //TextFields for holding the values
            TextField minimumSellingP1TxtFld = new TextField();
            TextField maximumSellingP1TxtFld = new TextField();

            //HBoxes for the selling prices containers
            HBox hboxMinimumSellingP1 = new HBox();
            hboxMinimumSellingP1.setId("hbox26");
            HBox hboxMaximumSellingP1 = new HBox();
            hboxMaximumSellingP1.setId("hbox26");
            HBox hboxSellingPConfig1 = new HBox();
            hboxSellingPConfig1.setId("hbox16");

            ///Placing the things in their containers
            hboxMinimumSellingP1.getChildren().addAll(minimumSellingP1Lbl,
                    minimumSellingP1TxtFld);
            hboxMaximumSellingP1.getChildren().addAll(mixamumSellingP1Lbl,
                    maximumSellingP1TxtFld);
            hboxSellingPConfig1.getChildren().addAll(hboxMinimumSellingP1,
                    hboxMaximumSellingP1);

            ////---placing the chart configuration controls in a general container
            VBox vboxConfigTools = new VBox();
            vboxConfigTools.setId("vbox3");
            vboxConfigTools.setPrefWidth(gW * 0.45);
            vboxConfigTools.getChildren().addAll(hboxGraphTitlelbl,
                    hboxFinalDates1Config, hboxFinalQty1, hboxSellingPConfig1);

            //prining and report configurations
            //Report printer title
            Label printer1Lbl = new Label("Report Printer");

            ///HBox for the printer Title
            HBox hboxPrinter1Lbl = new HBox();
            hboxPrinter1Lbl.setId("hbox13");
            hboxPrinter1Lbl.getChildren().add(printer1Lbl);

            //Configure Report Title
            Label reportTitle1Lbl = new Label("Report Title:");
            //Text Field For Report title
            UpperCaseTextField reportTitle1TxtFld = new UpperCaseTextField();
            reportTitle1TxtFld.setPrefWidth(gW * 0.25);

            //HBox for the report Title
            HBox hboxReportTitle = new HBox();
            hboxReportTitle.setId("hbox26");
            hboxReportTitle.getChildren().addAll(reportTitle1Lbl,
                    reportTitle1TxtFld);

            //Attachment
            Hyperlink attachment1HLink = new Hyperlink("Attach Document");
            //File Chooser
            FileChooser chooseAttachment1FChooser = new FileChooser();

            //HBox for the file chooser and containment
            HBox hboxFileChooserHLink = new HBox();
            hboxFileChooserHLink.setAlignment(Pos.CENTER_RIGHT);
            hboxFileChooserHLink.getChildren().addAll(attachment1HLink);

            //setting the action for the hyperlink
            attachment1HLink.setOnAction((ActionEvent e) -> {
                chooseAttachment1FChooser.setTitle("Attach Document");
                File fileAttachment1 = chooseAttachment1FChooser.showOpenDialog(
                        stage1);
                if (fileAttachment1 != null) {
                    openFile(fileAttachment1);
                }
            });

            //print button
            Button print1Btn = new Button("Print Report");
            print1Btn.getStyleClass().add("btn-info");
            HBox hboxPrint1Btn = new HBox();
            hboxPrint1Btn.setAlignment(Pos.CENTER_RIGHT);
            hboxPrint1Btn.getChildren().add(print1Btn);

            ///---final containment for the report printer
            VBox vboxReportsPrinter = new VBox();
            vboxReportsPrinter.setId("vbox3");
            vboxReportsPrinter.setPrefWidth(gW * 0.3);
            vboxReportsPrinter.getChildren().addAll(hboxPrinter1Lbl,
                    hboxReportTitle, hboxFileChooserHLink, hboxPrint1Btn);

            HBox hboxReportsAndTools = new HBox();
            hboxReportsAndTools.setId("hbox7");
            hboxReportsAndTools.getChildren().addAll(vboxConfigTools,
                    vboxReportsPrinter);

            ///placing the charts and config tools in their VBox
            VBox vboxChartAnsTools = new VBox();
            vboxChartAnsTools.getChildren().addAll(hboxChartAndSummary,
                    hboxReportsAndTools);

            //BorderPane Setting Center
            inventoryRecordsBP.setCenter(vboxChartAnsTools);

            ///---filtering selection of them menu
            toggleGroup2.selectedToggleProperty().addListener(
                    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                        if (toggleGroup2.getSelectedToggle() != null) {
                            if (generalSalesRBtn.isSelected()) {
                                Stage stage2 = new Stage();
                                ///--prerequisites
                                final Screen screen1 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds1 = screen1.getVisualBounds();
                                double gH1 = bounds1.getHeight();
                                double gW1 = bounds1.getWidth();
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer1 = new VBox();
                                vboxChartsMajContainer1.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl1 = new Label(
                                        "General Sales Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl1 = new HBox();
                                hboxChartTitleLbl1.setId("hbox13");
                                hboxChartTitleLbl1.getChildren().add(
                                        chartsTitleLbl1);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup1 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn1 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn1.setToggleGroup(radioBtnsGroup1);
                                barGraphRBtn1.setSelected(true);
                                RadioButton lineGraphRBtn1 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn1.setToggleGroup(radioBtnsGroup1);
                                RadioButton pieChartRBtn1 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn1.setToggleGroup(radioBtnsGroup1);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup1 = new HBox();
                                hboxToggleGroup1.setId("hbox13");
                                hboxToggleGroup1.getChildren().addAll(
                                        barGraphRBtn1, lineGraphRBtn1,
                                        pieChartRBtn1);
                                //the bar graph configuration
                                final CategoryAxis xAxis3 = new CategoryAxis();
                                final NumberAxis yAxis3 = new NumberAxis();
                                xAxis3.setLabel("Sold Items");
                                yAxis3.setLabel("Quantity");
                                final BarChart<String, Number> generalSalesRecordsBarChart1 = new BarChart<>(
                                        xAxis3, yAxis3);
                                generalSalesRecordsBarChart1.setMinWidth(
                                        gW1 * 0.45);
                                generalSalesRecordsBarChart1.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis4 = new CategoryAxis();
                                final NumberAxis yAxis4 = new NumberAxis();
                                xAxis4.setLabel("Sold Items");
                                yAxis4.setLabel("Quantity");
                                final LineChart<String, Number> generalSalesRecordsLineChart1 = new LineChart<>(
                                        xAxis4, yAxis4);
                                generalSalesRecordsLineChart1.setMinWidth(
                                        gW1 * 0.45);
                                generalSalesRecordsLineChart1.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart generalSalesRecordsPieChart1 = new PieChart();
                                generalSalesRecordsPieChart1.setTitle(
                                        "Sold Items Composition");
                                generalSalesRecordsPieChart1.setMinWidth(
                                        gW1 * 0.45);
                                HBox hboxChartArea1 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea1.getChildren().clear();
                                hboxChartArea1.getChildren().add(
                                        generalSalesRecordsBarChart1);
                                radioBtnsGroup1.selectedToggleProperty().addListener(
                                        (ObservableValue<? extends Toggle> ov1, Toggle old_toggle1, Toggle new_toggle1) -> {
                                            if (radioBtnsGroup1.getSelectedToggle() != null) {
                                                if (barGraphRBtn1.isSelected()) {
                                                    hboxChartArea1.getChildren().clear();
                                                    hboxChartArea1.getChildren().add(
                                                            generalSalesRecordsBarChart1);
                                                } else if (lineGraphRBtn1.isSelected()) {
                                                    hboxChartArea1.getChildren().clear();
                                                    hboxChartArea1.getChildren().add(
                                                            generalSalesRecordsLineChart1);
                                                } else if (pieChartRBtn1.isSelected()) {
                                                    hboxChartArea1.getChildren().clear();
                                                    hboxChartArea1.getChildren().add(
                                                            generalSalesRecordsPieChart1);
                                                }
                                            }
                                        });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer1.getChildren().addAll(
                                        hboxChartTitleLbl1, hboxToggleGroup1,
                                        hboxChartArea1);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl1 = new HBox();
                                //table
                                TableView<SummaryGeneralSalesData> SummaryGeneralSales1 = new TableView<>();
                                ObservableList<SummaryGeneralSalesData> SummaryGeneralSalesItems1 = FXCollections.observableArrayList();
                                SummaryGeneralSales1.setEditable(false);
                                SummaryGeneralSales1.setPrefHeight(gH1 * 0.56);
                                SummaryGeneralSales1.setItems(
                                        SummaryGeneralSalesItems1);
                                SummaryGeneralSales1.getColumns().clear();
                                TableColumn SummaryGeneralSales_Barcode_ID1 = new TableColumn(
                                        "Barcode/ID");
                                SummaryGeneralSales_Barcode_ID1.setPrefWidth(
                                        gW1 * 0.1);
                                SummaryGeneralSales_Barcode_ID1.setCellValueFactory(
                                        new PropertyValueFactory<SummaryGeneralSalesData, String>(
                                                "Barcode/ID"));
                                SummaryGeneralSales1.getColumns().addAll(
                                        SummaryGeneralSales_Barcode_ID1);
                                TableColumn SummaryGeneralSales_ItemName1 = new TableColumn(
                                        "ItemName");
                                SummaryGeneralSales_ItemName1.setPrefWidth(
                                        gW1 * 0.1);
                                SummaryGeneralSales_ItemName1.setCellValueFactory(
                                        new PropertyValueFactory<SummaryGeneralSalesData, String>(
                                                "ItemName"));
                                SummaryGeneralSales1.getColumns().addAll(
                                        SummaryGeneralSales_ItemName1);
                                TableColumn SummaryGeneralSales_Amount1 = new TableColumn(
                                        "Amount");
                                SummaryGeneralSales_Amount1.setPrefWidth(
                                        gW1 * 0.1);
                                SummaryGeneralSales_Amount1.setCellValueFactory(
                                        new PropertyValueFactory<SummaryGeneralSalesData, String>(
                                                "Amount"));
                                SummaryGeneralSales1.getColumns().addAll(
                                        SummaryGeneralSales_Amount1);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl1 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle1 = new HBox();
                                hboxSummaryTblTitle1.setId("hbox13");
                                hboxSummaryTblTitle1.getChildren().add(
                                        summaryTableTitleLbl1);
                                hboxChartsSummaryTbl1.getChildren().add(
                                        SummaryGeneralSales1);
                                VBox vboxFinalSummaryTbl1 = new VBox();
                                vboxFinalSummaryTbl1.setId("vbox3");
                                vboxFinalSummaryTbl1.getChildren().addAll(
                                        hboxSummaryTblTitle1,
                                        hboxChartsSummaryTbl1);
                                //final center HBox
                                HBox hboxChartAndSummary1 = new HBox();
                                hboxChartAndSummary1.setId("hbox7");
                                hboxChartAndSummary1.getChildren().addAll(
                                        vboxChartsMajContainer1,
                                        vboxFinalSummaryTbl1);
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl1 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl1 = new HBox();
                                hboxGraphTitlelbl1.setId("hbox13");
                                hboxGraphTitlelbl1.getChildren().add(
                                        configurationsTitleLbl1);
                                //Labels for the Dates
                                Label initialDate1Lbl1 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl1 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk2 = new DatePicker();
                                DatePicker finalDatePk2 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate2 = new HBox();
                                hboxInitialDate2.setId("hbox26");
                                HBox hboxFinalDate2 = new HBox();
                                hboxFinalDate2.setId("hbox26");
                                HBox hboxFinalDates1Config1 = new HBox();
                                hboxFinalDates1Config1.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate2.getChildren().addAll(
                                        initialDate1Lbl1, initialDatePk2);
                                hboxFinalDate2.getChildren().addAll(
                                        finalDate1Lbl1, finalDatePk2);
                                hboxFinalDates1Config1.getChildren().addAll(
                                        hboxInitialDate2, hboxFinalDate2);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl1 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl1 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld1 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld1 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty2 = new HBox();
                                hboxMinimumQty2.setId("hbox26");
                                HBox hboxMaximumQty2 = new HBox();
                                hboxMaximumQty2.setId("hbox26");
                                HBox hboxFinalQty2 = new HBox();
                                hboxFinalQty2.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty2.getChildren().addAll(
                                        minimumQty1Lbl1, minimumQty1TxtFld1);
                                hboxMaximumQty2.getChildren().addAll(
                                        maximumQty1Lbl1, minimumQty2TxtFld1);
                                hboxFinalQty2.getChildren().addAll(
                                        hboxMinimumQty2, hboxMaximumQty2);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl1 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl1 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld1 = new TextField();
                                TextField maximumSellingP1TxtFld1 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP2 = new HBox();
                                hboxMinimumSellingP2.setId("hbox26");
                                HBox hboxMaximumSellingP2 = new HBox();
                                hboxMaximumSellingP2.setId("hbox26");
                                HBox hboxSellingPConfig2 = new HBox();
                                hboxSellingPConfig2.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP2.getChildren().addAll(
                                        minimumSellingP1Lbl1,
                                        minimumSellingP1TxtFld1);
                                hboxMaximumSellingP2.getChildren().addAll(
                                        mixamumSellingP1Lbl1,
                                        maximumSellingP1TxtFld1);
                                hboxSellingPConfig2.getChildren().addAll(
                                        hboxMinimumSellingP2,
                                        hboxMaximumSellingP2);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools1 = new VBox();
                                vboxConfigTools1.setId("vbox3");
                                vboxConfigTools1.setPrefWidth(gW1 * 0.45);
                                vboxConfigTools1.getChildren().addAll(
                                        hboxGraphTitlelbl1,
                                        hboxFinalDates1Config1, hboxFinalQty2,
                                        hboxSellingPConfig2);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl1 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl1 = new HBox();
                                hboxPrinter1Lbl1.setId("hbox13");
                                hboxPrinter1Lbl1.getChildren().add(printer1Lbl1);
                                //Configure Report Title
                                Label reportTitle1Lbl1 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld1 = new UpperCaseTextField();
                                reportTitle1TxtFld1.setPrefWidth(gW1 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle1 = new HBox();
                                hboxReportTitle1.setId("hbox26");
                                hboxReportTitle1.getChildren().addAll(
                                        reportTitle1Lbl1, reportTitle1TxtFld1);
                                //Attachment
                                Hyperlink attachment1HLink1 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser1 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink1 = new HBox();
                                hboxFileChooserHLink1.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink1.getChildren().addAll(
                                        attachment1HLink1);
                                //setting the action for the hyperlink
                                attachment1HLink1.setOnAction(
                                        (ActionEvent e) -> {
                                            chooseAttachment1FChooser1.setTitle(
                                                    "Attach Document");
                                            File fileAttachment1 = chooseAttachment1FChooser1.showOpenDialog(
                                                    stage2);
                                            if (fileAttachment1 != null) {
                                                openFile(fileAttachment1);
                                            }
                                        });
                                //print button
                                Button print1Btn1 = new Button("Print Report");
                                print1Btn.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn1 = new HBox();
                                hboxPrint1Btn1.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn1.getChildren().add(print1Btn1);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter1 = new VBox();
                                vboxReportsPrinter1.setId("vbox3");
                                vboxReportsPrinter1.setPrefWidth(gW1 * 0.3);
                                vboxReportsPrinter1.getChildren().addAll(
                                        hboxPrinter1Lbl1, hboxReportTitle1,
                                        hboxFileChooserHLink1, hboxPrint1Btn1);
                                HBox hboxReportsAndTools1 = new HBox();
                                hboxReportsAndTools1.setId("hbox7");
                                hboxReportsAndTools1.getChildren().addAll(
                                        vboxConfigTools1, vboxReportsPrinter1);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools1 = new VBox();
                                vboxChartAnsTools1.getChildren().addAll(
                                        hboxChartAndSummary1,
                                        hboxReportsAndTools1);
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools1);
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools1);
                            } else if (packetSalesRBtn.isSelected()) {
                                Stage stage3 = new Stage();
                                ///--prerequisites
                                final Screen screen2 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds2 = screen2.getVisualBounds();
                                double gH2 = bounds2.getHeight();
                                double gW2 = bounds2.getWidth();
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer2 = new VBox();
                                vboxChartsMajContainer2.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl2 = new Label(
                                        "Packet Sales Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl2 = new HBox();
                                hboxChartTitleLbl2.setId("hbox13");
                                hboxChartTitleLbl2.getChildren().add(
                                        chartsTitleLbl2);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup2 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn2 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn2.setToggleGroup(radioBtnsGroup2);
                                barGraphRBtn2.setSelected(true);
                                RadioButton lineGraphRBtn2 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn2.setToggleGroup(radioBtnsGroup2);
                                RadioButton pieChartRBtn2 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn2.setToggleGroup(radioBtnsGroup2);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup2 = new HBox();
                                hboxToggleGroup2.setId("hbox13");
                                hboxToggleGroup2.getChildren().addAll(
                                        barGraphRBtn2, lineGraphRBtn2,
                                        pieChartRBtn2);
                                //the bar graph configuration
                                final CategoryAxis xAxis5 = new CategoryAxis();
                                final NumberAxis yAxis5 = new NumberAxis();
                                xAxis5.setLabel("Sold Items");
                                yAxis5.setLabel("Packets/Quantity");
                                final BarChart<String, Number> packetSalesRecordsBarChart = new BarChart<>(
                                        xAxis5, yAxis5);
                                packetSalesRecordsBarChart.setMinWidth(
                                        gW2 * 0.45);
                                packetSalesRecordsBarChart.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis6 = new CategoryAxis();
                                final NumberAxis yAxis6 = new NumberAxis();
                                xAxis6.setLabel("Sold Items");
                                yAxis6.setLabel("Packets/Quantity");
                                final LineChart<String, Number> packetSalesRecordsLineChart = new LineChart<>(
                                        xAxis6, yAxis6);
                                packetSalesRecordsLineChart.setMinWidth(
                                        gW2 * 0.45);
                                packetSalesRecordsLineChart.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart packetSalesRecordsPieChart = new PieChart();
                                packetSalesRecordsPieChart.setTitle(
                                        "Packet Sales Composition");
                                packetSalesRecordsPieChart.setMinWidth(
                                        gW2 * 0.45);
                                HBox hboxChartArea2 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea2.getChildren().clear();
                                hboxChartArea2.getChildren().add(
                                        packetSalesRecordsBarChart);
                                radioBtnsGroup2.selectedToggleProperty().addListener(
                                        (ObservableValue<? extends Toggle> ov1, Toggle old_toggle1, Toggle new_toggle1) -> {
                                            if (radioBtnsGroup2.getSelectedToggle() != null) {
                                                if (barGraphRBtn2.isSelected()) {
                                                    hboxChartArea2.getChildren().clear();
                                                    hboxChartArea2.getChildren().add(
                                                            packetSalesRecordsBarChart);
                                                } else if (lineGraphRBtn2.isSelected()) {
                                                    hboxChartArea2.getChildren().clear();
                                                    hboxChartArea2.getChildren().add(
                                                            packetSalesRecordsLineChart);
                                                } else if (pieChartRBtn2.isSelected()) {
                                                    hboxChartArea2.getChildren().clear();
                                                    hboxChartArea2.getChildren().add(
                                                            packetSalesRecordsPieChart);
                                                }
                                            }
                                        });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer2.getChildren().addAll(
                                        hboxChartTitleLbl2, hboxToggleGroup2,
                                        hboxChartArea2);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl2 = new HBox();
                                //table
                                TableView<PacketSalesSummaryData> PacketSalesSummary = new TableView<>();
                                ObservableList<PacketSalesSummaryData> PacketSalesSummaryItems = FXCollections.observableArrayList();
                                PacketSalesSummary.setEditable(false);
                                PacketSalesSummary.setPrefHeight(gH2 * 0.56);
                                PacketSalesSummary.setItems(
                                        PacketSalesSummaryItems);
                                PacketSalesSummary.getColumns().clear();
                                TableColumn PacketSalesSummary_Barcode_ID = new TableColumn(
                                        "Barcode/ID");
                                PacketSalesSummary_Barcode_ID.setPrefWidth(
                                        gW2 * 0.1);
                                PacketSalesSummary_Barcode_ID.setCellValueFactory(
                                        new PropertyValueFactory<PacketSalesSummaryData, String>(
                                                "Barcode/ID"));
                                PacketSalesSummary.getColumns().addAll(
                                        PacketSalesSummary_Barcode_ID);
                                TableColumn PacketSalesSummary_ItemName = new TableColumn(
                                        "ItemName");
                                PacketSalesSummary_ItemName.setPrefWidth(
                                        gW2 * 0.1);
                                PacketSalesSummary_ItemName.setCellValueFactory(
                                        new PropertyValueFactory<PacketSalesSummaryData, String>(
                                                "ItemName"));
                                PacketSalesSummary.getColumns().addAll(
                                        PacketSalesSummary_ItemName);
                                TableColumn PacketSalesSummary_Amount = new TableColumn(
                                        "Amount");
                                PacketSalesSummary_Amount.setPrefWidth(gW2 * 0.1);
                                PacketSalesSummary_Amount.setCellValueFactory(
                                        new PropertyValueFactory<PacketSalesSummaryData, String>(
                                                "Amount"));
                                PacketSalesSummary.getColumns().addAll(
                                        PacketSalesSummary_Amount);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl2 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle2 = new HBox();
                                hboxSummaryTblTitle2.setId("hbox13");
                                hboxSummaryTblTitle2.getChildren().add(
                                        summaryTableTitleLbl2);
                                hboxChartsSummaryTbl2.getChildren().add(
                                        PacketSalesSummary);
                                VBox vboxFinalSummaryTbl2 = new VBox();
                                vboxFinalSummaryTbl2.setId("vbox3");
                                vboxFinalSummaryTbl2.getChildren().addAll(
                                        hboxSummaryTblTitle2,
                                        hboxChartsSummaryTbl2);
                                //final center HBox
                                HBox hboxChartAndSummary2 = new HBox();
                                hboxChartAndSummary2.setId("hbox7");
                                hboxChartAndSummary2.getChildren().addAll(
                                        vboxChartsMajContainer2,
                                        vboxFinalSummaryTbl2);
                                ////////////////////////
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl2 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl2 = new HBox();
                                hboxGraphTitlelbl2.setId("hbox13");
                                hboxGraphTitlelbl2.getChildren().add(
                                        configurationsTitleLbl2);
                                //Labels for the Dates
                                Label initialDate1Lbl2 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl2 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk3 = new DatePicker();
                                DatePicker finalDatePk3 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate3 = new HBox();
                                HBox hboxFinalDate3 = new HBox();
                                HBox hboxFinalDates1Config2 = new HBox();
                                hboxFinalDates1Config2.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate3.getChildren().addAll(
                                        initialDate1Lbl2, initialDatePk3);
                                hboxFinalDate3.getChildren().addAll(
                                        finalDate1Lbl2, finalDatePk3);
                                hboxFinalDates1Config2.getChildren().addAll(
                                        hboxInitialDate3, hboxFinalDate3);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl2 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl2 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld2 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld2 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty3 = new HBox();
                                HBox hboxMaximumQty3 = new HBox();
                                HBox hboxFinalQty3 = new HBox();
                                hboxFinalQty3.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty3.getChildren().addAll(
                                        minimumQty1Lbl2, minimumQty1TxtFld2);
                                hboxMaximumQty3.getChildren().addAll(
                                        maximumQty1Lbl2, minimumQty2TxtFld2);
                                hboxFinalQty3.getChildren().addAll(
                                        hboxMinimumQty3, hboxMaximumQty3);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl2 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl2 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld2 = new TextField();
                                TextField maximumSellingP1TxtFld2 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP3 = new HBox();
                                HBox hboxMaximumSellingP3 = new HBox();
                                HBox hboxSellingPConfig3 = new HBox();
                                hboxSellingPConfig3.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP3.getChildren().addAll(
                                        minimumSellingP1Lbl2,
                                        minimumSellingP1TxtFld2);
                                hboxMaximumSellingP3.getChildren().addAll(
                                        mixamumSellingP1Lbl2,
                                        maximumSellingP1TxtFld2);
                                hboxSellingPConfig3.getChildren().addAll(
                                        hboxMinimumSellingP3,
                                        hboxMaximumSellingP3);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools2 = new VBox();
                                vboxConfigTools2.setId("vbox3");
                                vboxConfigTools2.setPrefWidth(gW2 * 0.45);
                                vboxConfigTools2.getChildren().addAll(
                                        hboxGraphTitlelbl2,
                                        hboxFinalDates1Config2, hboxFinalQty3,
                                        hboxSellingPConfig3);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl2 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl2 = new HBox();
                                hboxPrinter1Lbl2.setId("hbox13");
                                hboxPrinter1Lbl2.getChildren().add(printer1Lbl2);
                                //Configure Report Title
                                Label reportTitle1Lbl2 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld2 = new UpperCaseTextField();
                                reportTitle1TxtFld2.setPrefWidth(gW2 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle2 = new HBox();
                                hboxReportTitle2.setId("hbox26");
                                hboxReportTitle2.getChildren().addAll(
                                        reportTitle1Lbl2, reportTitle1TxtFld2);
                                //Attachment
                                Hyperlink attachment1HLink2 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser2 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink2 = new HBox();
                                hboxFileChooserHLink2.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink2.getChildren().addAll(
                                        attachment1HLink2);
                                //setting the action for the hyperlink
                                attachment1HLink2.setOnAction(
                                        (ActionEvent e) -> {
                                            chooseAttachment1FChooser2.setTitle(
                                                    "Attach Document");
                                            File fileAttachment1 = chooseAttachment1FChooser2.showOpenDialog(
                                                    stage3);
                                            if (fileAttachment1 != null) {
                                                openFile(fileAttachment1);
                                            }
                                        });
                                //print button
                                Button print1Btn2 = new Button("Print Report");
                                print1Btn2.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn2 = new HBox();
                                hboxPrint1Btn2.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn2.getChildren().add(print1Btn2);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter2 = new VBox();
                                vboxReportsPrinter2.setId("vbox3");
                                vboxReportsPrinter2.setPrefWidth(gW2 * 0.3);
                                vboxReportsPrinter2.getChildren().addAll(
                                        hboxPrinter1Lbl2, hboxReportTitle2,
                                        hboxFileChooserHLink2, hboxPrint1Btn2);
                                HBox hboxReportsAndTools2 = new HBox();
                                hboxReportsAndTools2.setId("hbox7");
                                hboxReportsAndTools2.getChildren().addAll(
                                        vboxConfigTools2, vboxReportsPrinter2);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools2 = new VBox();
                                vboxChartAnsTools2.getChildren().addAll(
                                        hboxChartAndSummary2,
                                        hboxReportsAndTools2);
                                ////////////////////////
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools2);
                            } else if (retailSalesRBtn.isSelected()) {
                                Stage stage4 = new Stage();
                                ///--prerequisites
                                final Screen screen3 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds3 = screen3.getVisualBounds();
                                double gH3 = bounds3.getHeight();
                                double gW3 = bounds3.getWidth();
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer3 = new VBox();
                                vboxChartsMajContainer3.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl3 = new Label(
                                        "Retail Sales Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl3 = new HBox();
                                hboxChartTitleLbl3.setId("hbox13");
                                hboxChartTitleLbl3.getChildren().add(
                                        chartsTitleLbl3);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup3 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn3 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn3.setToggleGroup(radioBtnsGroup3);
                                barGraphRBtn3.setSelected(true);
                                RadioButton lineGraphRBtn3 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn3.setToggleGroup(radioBtnsGroup3);
                                RadioButton pieChartRBtn3 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn3.setToggleGroup(radioBtnsGroup3);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup3 = new HBox();
                                hboxToggleGroup3.setId("hbox13");
                                hboxToggleGroup3.getChildren().addAll(
                                        barGraphRBtn3, lineGraphRBtn3,
                                        pieChartRBtn3);
                                //the bar graph configuration
                                final CategoryAxis xAxis7 = new CategoryAxis();
                                final NumberAxis yAxis7 = new NumberAxis();
                                xAxis7.setLabel("Sold Items");
                                yAxis7.setLabel("Retail Quantity");
                                final BarChart<String, Number> retailSalesRecordsBarChart = new BarChart<>(
                                        xAxis7, yAxis7);
                                retailSalesRecordsBarChart.setMinWidth(
                                        gW3 * 0.45);
                                retailSalesRecordsBarChart.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis8 = new CategoryAxis();
                                final NumberAxis yAxis8 = new NumberAxis();
                                xAxis8.setLabel("Sold Items");
                                yAxis8.setLabel("Retail Quantity");
                                final LineChart<String, Number> retailSalesRecordsLineChart = new LineChart<>(
                                        xAxis8, yAxis8);
                                retailSalesRecordsLineChart.setMinWidth(
                                        gW3 * 0.45);
                                retailSalesRecordsLineChart.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart retailSalesRecordsPieChart = new PieChart();
                                retailSalesRecordsPieChart.setTitle(
                                        "Retail Items Composition");
                                retailSalesRecordsPieChart.setMinWidth(
                                        gW3 * 0.45);
                                HBox hboxChartArea3 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea3.getChildren().clear();
                                hboxChartArea3.getChildren().add(
                                        retailSalesRecordsBarChart);
                                radioBtnsGroup3.selectedToggleProperty().addListener(
                                        (ObservableValue<? extends Toggle> ov1, Toggle old_toggle1, Toggle new_toggle1) -> {
                                            if (radioBtnsGroup3.getSelectedToggle() != null) {
                                                if (barGraphRBtn3.isSelected()) {
                                                    hboxChartArea3.getChildren().clear();
                                                    hboxChartArea3.getChildren().add(
                                                            retailSalesRecordsBarChart);
                                                } else if (lineGraphRBtn3.isSelected()) {
                                                    hboxChartArea3.getChildren().clear();
                                                    hboxChartArea3.getChildren().add(
                                                            retailSalesRecordsLineChart);
                                                } else if (pieChartRBtn3.isSelected()) {
                                                    hboxChartArea3.getChildren().clear();
                                                    hboxChartArea3.getChildren().add(
                                                            retailSalesRecordsPieChart);
                                                }
                                            }
                                        });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer3.getChildren().addAll(
                                        hboxChartTitleLbl3, hboxToggleGroup3,
                                        hboxChartArea3);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl3 = new HBox();
                                //table
                                TableView<RetailSalesSummaryData> RetailSalesSummary = new TableView<>();
                                ObservableList<RetailSalesSummaryData> RetailSalesSummaryItems = FXCollections.observableArrayList();
                                RetailSalesSummary.setEditable(false);
                                RetailSalesSummary.setPrefHeight(gH3 * 0.56);
                                RetailSalesSummary.setItems(
                                        RetailSalesSummaryItems);
                                RetailSalesSummary.getColumns().clear();
                                TableColumn RetailSalesSummary_Barcode_ID = new TableColumn(
                                        "Barcode/ID");
                                RetailSalesSummary_Barcode_ID.setPrefWidth(
                                        gW3 * 0.1);
                                RetailSalesSummary_Barcode_ID.setCellValueFactory(
                                        new PropertyValueFactory<RetailSalesSummaryData, String>(
                                                "Barcode/ID"));
                                RetailSalesSummary.getColumns().addAll(
                                        RetailSalesSummary_Barcode_ID);
                                TableColumn RetailSalesSummary_ItemName = new TableColumn(
                                        "ItemName");
                                RetailSalesSummary_ItemName.setPrefWidth(
                                        gW3 * 0.1);
                                RetailSalesSummary_ItemName.setCellValueFactory(
                                        new PropertyValueFactory<RetailSalesSummaryData, String>(
                                                "ItemName"));
                                RetailSalesSummary.getColumns().addAll(
                                        RetailSalesSummary_ItemName);
                                TableColumn RetailSalesSummary_Amount = new TableColumn(
                                        "Amount");
                                RetailSalesSummary_Amount.setPrefWidth(gW3 * 0.1);
                                RetailSalesSummary_Amount.setCellValueFactory(
                                        new PropertyValueFactory<RetailSalesSummaryData, String>(
                                                "Amount"));
                                RetailSalesSummary.getColumns().addAll(
                                        RetailSalesSummary_Amount);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl3 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle3 = new HBox();
                                hboxSummaryTblTitle3.setId("hbox13");
                                hboxSummaryTblTitle3.getChildren().add(
                                        summaryTableTitleLbl3);
                                hboxChartsSummaryTbl3.getChildren().add(
                                        RetailSalesSummary);
                                VBox vboxFinalSummaryTbl3 = new VBox();
                                vboxFinalSummaryTbl3.setId("vbox3");
                                vboxFinalSummaryTbl3.getChildren().addAll(
                                        hboxSummaryTblTitle3,
                                        hboxChartsSummaryTbl3);
                                //final center HBox
                                HBox hboxChartAndSummary3 = new HBox();
                                hboxChartAndSummary3.setId("hbox7");
                                hboxChartAndSummary3.getChildren().addAll(
                                        vboxChartsMajContainer3,
                                        vboxFinalSummaryTbl3);
                                ////////////////////////
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl3 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl3 = new HBox();
                                hboxGraphTitlelbl3.setId("hbox13");
                                hboxGraphTitlelbl3.getChildren().add(
                                        configurationsTitleLbl3);
                                //Labels for the Dates
                                Label initialDate1Lbl3 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl3 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk4 = new DatePicker();
                                DatePicker finalDatePk4 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate4 = new HBox();
                                hboxInitialDate4.setId("hbox26");
                                HBox hboxFinalDate4 = new HBox();
                                hboxFinalDate4.setId("hbox26");
                                HBox hboxFinalDates1Config3 = new HBox();
                                hboxFinalDates1Config3.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate4.getChildren().addAll(
                                        initialDate1Lbl3, initialDatePk4);
                                hboxFinalDate4.getChildren().addAll(
                                        finalDate1Lbl3, finalDatePk4);
                                hboxFinalDates1Config3.getChildren().addAll(
                                        hboxInitialDate4, hboxFinalDate4);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl3 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl3 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld3 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld3 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty4 = new HBox();
                                hboxMinimumQty4.setId("hbox26");
                                HBox hboxMaximumQty4 = new HBox();
                                hboxMaximumQty4.setId("hbox26");
                                HBox hboxFinalQty4 = new HBox();
                                hboxFinalQty4.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty4.getChildren().addAll(
                                        minimumQty1Lbl3, minimumQty1TxtFld3);
                                hboxMaximumQty4.getChildren().addAll(
                                        maximumQty1Lbl3, minimumQty2TxtFld3);
                                hboxFinalQty4.getChildren().addAll(
                                        hboxMinimumQty4, hboxMaximumQty4);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl3 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl3 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld3 = new TextField();
                                TextField maximumSellingP1TxtFld3 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP4 = new HBox();
                                hboxMinimumSellingP4.setId("hbox26");
                                HBox hboxMaximumSellingP4 = new HBox();
                                hboxMaximumSellingP4.setId("hbox26");
                                HBox hboxSellingPConfig4 = new HBox();
                                hboxSellingPConfig4.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP4.getChildren().addAll(
                                        minimumSellingP1Lbl3,
                                        minimumSellingP1TxtFld3);
                                hboxMaximumSellingP4.getChildren().addAll(
                                        mixamumSellingP1Lbl3,
                                        maximumSellingP1TxtFld3);
                                hboxSellingPConfig4.getChildren().addAll(
                                        hboxMinimumSellingP4,
                                        hboxMaximumSellingP4);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools3 = new VBox();
                                vboxConfigTools3.setId("vbox3");
                                vboxConfigTools3.setPrefWidth(gW3 * 0.45);
                                vboxConfigTools3.getChildren().addAll(
                                        hboxGraphTitlelbl3,
                                        hboxFinalDates1Config3, hboxFinalQty4,
                                        hboxSellingPConfig4);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl3 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl3 = new HBox();
                                hboxPrinter1Lbl3.setId("hbox13");
                                hboxPrinter1Lbl3.getChildren().add(printer1Lbl3);
                                //Configure Report Title
                                Label reportTitle1Lbl3 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld3 = new UpperCaseTextField();
                                reportTitle1TxtFld3.setPrefWidth(gW3 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle3 = new HBox();
                                hboxReportTitle3.setId("hbox26");
                                hboxReportTitle3.getChildren().addAll(
                                        reportTitle1Lbl3, reportTitle1TxtFld3);
                                //Attachment
                                Hyperlink attachment1HLink3 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser3 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink3 = new HBox();
                                hboxFileChooserHLink3.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink3.getChildren().addAll(
                                        attachment1HLink3);
                                //setting the action for the hyperlink
                                attachment1HLink3.setOnAction(
                                        (ActionEvent e) -> {
                                            chooseAttachment1FChooser3.setTitle(
                                                    "Attach Document");
                                            File fileAttachment1 = chooseAttachment1FChooser3.showOpenDialog(
                                                    stage4);
                                            if (fileAttachment1 != null) {
                                                openFile(fileAttachment1);
                                            }
                                        });
                                //print button
                                Button print1Btn3 = new Button("Print Report");
                                print1Btn3.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn3 = new HBox();
                                hboxPrint1Btn3.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn3.getChildren().add(print1Btn3);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter3 = new VBox();
                                vboxReportsPrinter3.setId("vbox3");
                                vboxReportsPrinter3.setPrefWidth(gW3 * 0.3);
                                vboxReportsPrinter3.getChildren().addAll(
                                        hboxPrinter1Lbl3, hboxReportTitle3,
                                        hboxFileChooserHLink3, hboxPrint1Btn3);
                                HBox hboxReportsAndTools3 = new HBox();
                                hboxReportsAndTools3.setId("hbox7");
                                hboxReportsAndTools3.getChildren().addAll(
                                        vboxConfigTools3, vboxReportsPrinter3);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools3 = new VBox();
                                vboxChartAnsTools3.getChildren().addAll(
                                        hboxChartAndSummary3,
                                        hboxReportsAndTools3);
                                ////////////////////////
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools3);
                            } else if (wholeSalesRBtn.isSelected()) {
                                Stage stage5 = new Stage();
                                ///--prerequisites
                                final Screen screen4 = Screen.getPrimary();
                                javafx.geometry.Rectangle2D bounds4 = screen4.getVisualBounds();
                                double gH4 = bounds4.getHeight();
                                double gW4 = bounds4.getWidth();
                                //main configuration/container for charts
                                VBox vboxChartsMajContainer4 = new VBox();
                                vboxChartsMajContainer4.setId("vbox3");
                                ////---chart area
                                //setting Title
                                Label chartsTitleLbl4 = new Label(
                                        "Whole Sale Analyzer");
                                //HBox for the title
                                HBox hboxChartTitleLbl4 = new HBox();
                                hboxChartTitleLbl4.setId("hbox13");
                                hboxChartTitleLbl4.getChildren().add(
                                        chartsTitleLbl4);
                                ///Toggle group for the radio buttons
                                final ToggleGroup radioBtnsGroup4 = new ToggleGroup();
                                //radio butons for selecting the charts to show
                                RadioButton barGraphRBtn4 = new RadioButton(
                                        "Bar Graph");
                                barGraphRBtn4.setToggleGroup(radioBtnsGroup4);
                                barGraphRBtn4.setSelected(true);
                                RadioButton lineGraphRBtn4 = new RadioButton(
                                        "Line Graph");
                                lineGraphRBtn4.setToggleGroup(radioBtnsGroup4);
                                RadioButton pieChartRBtn4 = new RadioButton(
                                        "Pie Chart");
                                pieChartRBtn4.setToggleGroup(radioBtnsGroup4);
                                //HBox for the ToggleGroup
                                HBox hboxToggleGroup4 = new HBox();
                                hboxToggleGroup4.setId("hbox13");
                                hboxToggleGroup4.getChildren().addAll(
                                        barGraphRBtn4, lineGraphRBtn4,
                                        pieChartRBtn4);
                                //the bar graph configuration
                                final CategoryAxis xAxis9 = new CategoryAxis();
                                final NumberAxis yAxis9 = new NumberAxis();
                                xAxis9.setLabel("Sold Items");
                                yAxis9.setLabel("Whole Sale Quantity");
                                final BarChart<String, Number> wholeSaleRecordsBarChart = new BarChart<>(
                                        xAxis9, yAxis9);
                                wholeSaleRecordsBarChart.setMinWidth(gW4 * 0.45);
                                wholeSaleRecordsBarChart.setTitle(
                                        "Quantity Against Items");
                                ///the line Graph
                                final CategoryAxis xAxis10 = new CategoryAxis();
                                final NumberAxis yAxis10 = new NumberAxis();
                                xAxis10.setLabel("Sold Items");
                                yAxis10.setLabel("Whole Sale Quantity");
                                final LineChart<String, Number> wholeSaleRecordsLineChart = new LineChart<>(
                                        xAxis10, yAxis10);
                                wholeSaleRecordsLineChart.setMinWidth(gW4 * 0.45);
                                wholeSaleRecordsLineChart.setTitle(
                                        "Quantity Against Items");
                                ///The PieChart
                                final PieChart wholeSaleRecordsPieChart = new PieChart();
                                wholeSaleRecordsPieChart.setTitle(
                                        "Whole Sale Composition");
                                wholeSaleRecordsPieChart.setMinWidth(gW4 * 0.45);
                                HBox hboxChartArea4 = new HBox();
                                //just to cater for the already selected radio button bar chart
                                hboxChartArea4.getChildren().clear();
                                hboxChartArea4.getChildren().add(
                                        wholeSaleRecordsBarChart);
                                radioBtnsGroup4.selectedToggleProperty().addListener(
                                        (ObservableValue<? extends Toggle> ov1, Toggle old_toggle1, Toggle new_toggle1) -> {
                                            if (radioBtnsGroup4.getSelectedToggle() != null) {
                                                if (barGraphRBtn4.isSelected()) {
                                                    hboxChartArea4.getChildren().clear();
                                                    hboxChartArea4.getChildren().add(
                                                            wholeSaleRecordsBarChart);
                                                } else if (lineGraphRBtn4.isSelected()) {
                                                    hboxChartArea4.getChildren().clear();
                                                    hboxChartArea4.getChildren().add(
                                                            wholeSaleRecordsLineChart);
                                                } else if (pieChartRBtn4.isSelected()) {
                                                    hboxChartArea4.getChildren().clear();
                                                    hboxChartArea4.getChildren().add(
                                                            wholeSaleRecordsPieChart);
                                                }
                                            }
                                        });
                                //the charts section wrapping up as one thing
                                vboxChartsMajContainer4.getChildren().addAll(
                                        hboxChartTitleLbl4, hboxToggleGroup4,
                                        hboxChartArea4);
                                ////---the summary table
                                ////---HBox for the charts and the summaryTable
                                HBox hboxChartsSummaryTbl4 = new HBox();
                                //table
                                TableView<WholeSaleSummaryData> WholeSaleSummary = new TableView<>();
                                ObservableList<WholeSaleSummaryData> WholeSaleSummaryItems = FXCollections.observableArrayList();
                                WholeSaleSummary.setEditable(false);
                                WholeSaleSummary.setPrefHeight(gH4 * 0.56);
                                WholeSaleSummary.setItems(WholeSaleSummaryItems);
                                WholeSaleSummary.getColumns().clear();
                                TableColumn WholeSaleSummary_Barcode_ID = new TableColumn(
                                        "Barcode_ID");
                                WholeSaleSummary_Barcode_ID.setPrefWidth(
                                        gW4 * 0.1);
                                WholeSaleSummary_Barcode_ID.setCellValueFactory(
                                        new PropertyValueFactory<WholeSaleSummaryData, String>(
                                                "Barcode_ID"));
                                WholeSaleSummary.getColumns().addAll(
                                        WholeSaleSummary_Barcode_ID);
                                TableColumn WholeSaleSummary_ItemName = new TableColumn(
                                        "ItemName");
                                WholeSaleSummary_ItemName.setPrefWidth(gW4 * 0.1);
                                WholeSaleSummary_ItemName.setCellValueFactory(
                                        new PropertyValueFactory<WholeSaleSummaryData, String>(
                                                "ItemName"));
                                WholeSaleSummary.getColumns().addAll(
                                        WholeSaleSummary_ItemName);
                                TableColumn WholeSaleSummary_Amount = new TableColumn(
                                        "Amount");
                                WholeSaleSummary_Amount.setPrefWidth(gW4 * 0.1);
                                WholeSaleSummary_Amount.setCellValueFactory(
                                        new PropertyValueFactory<WholeSaleSummaryData, String>(
                                                "Amount"));
                                WholeSaleSummary.getColumns().addAll(
                                        WholeSaleSummary_Amount);
                                //table into HBox with Title Label
                                Label summaryTableTitleLbl4 = new Label(
                                        "Summary Table");
                                HBox hboxSummaryTblTitle4 = new HBox();
                                hboxSummaryTblTitle4.setId("hbox13");
                                hboxSummaryTblTitle4.getChildren().add(
                                        summaryTableTitleLbl4);
                                hboxChartsSummaryTbl4.getChildren().add(
                                        WholeSaleSummary);
                                VBox vboxFinalSummaryTbl4 = new VBox();
                                vboxFinalSummaryTbl4.setId("vbox3");
                                vboxFinalSummaryTbl4.getChildren().addAll(
                                        hboxSummaryTblTitle4,
                                        hboxChartsSummaryTbl4);
                                //final center HBox
                                HBox hboxChartAndSummary4 = new HBox();
                                hboxChartAndSummary4.setId("hbox7");
                                hboxChartAndSummary4.getChildren().addAll(
                                        vboxChartsMajContainer4,
                                        vboxFinalSummaryTbl4);
                                ////////////////////////
                                ////----Confgurations for the lower central part... these configure the graphs
                                //Title for the configurations
                                Label configurationsTitleLbl4 = new Label(
                                        "Graph Controls");
                                //hbox graph control container
                                HBox hboxGraphTitlelbl4 = new HBox();
                                hboxGraphTitlelbl4.setId("hbox13");
                                hboxGraphTitlelbl4.getChildren().add(
                                        configurationsTitleLbl4);
                                //Labels for the Dates
                                Label initialDate1Lbl4 = new Label(
                                        "Starting Date:");
                                Label finalDate1Lbl4 = new Label("End Date:");
                                //Date Comparison for the graph controls
                                DatePicker initialDatePk5 = new DatePicker();
                                DatePicker finalDatePk5 = new DatePicker();
                                //DatePicker containers
                                HBox hboxInitialDate5 = new HBox();
                                hboxInitialDate5.setId("hbox26");
                                HBox hboxFinalDate5 = new HBox();
                                hboxFinalDate5.setId("hbox26");
                                HBox hboxFinalDates1Config4 = new HBox();
                                hboxFinalDates1Config4.setId("hbox14");
                                //placing all of them in their containers
                                hboxInitialDate5.getChildren().addAll(
                                        initialDate1Lbl4, initialDatePk5);
                                hboxFinalDate5.getChildren().addAll(
                                        finalDate1Lbl4, finalDatePk5);
                                hboxFinalDates1Config4.getChildren().addAll(
                                        hboxInitialDate5, hboxFinalDate5);
                                ///---Configuration by Quantity
                                //Labels
                                Label minimumQty1Lbl4 = new Label(
                                        "Minimum Quantity:");
                                Label maximumQty1Lbl4 = new Label(
                                        "Maximum Quantity:");
                                //TextFields for the Quantity Values
                                DecimalTextField minimumQty1TxtFld4 = new DecimalTextField();
                                DecimalTextField minimumQty2TxtFld4 = new DecimalTextField();
                                //HBoxes for the quantity controls
                                HBox hboxMinimumQty5 = new HBox();
                                hboxMinimumQty5.setId("hbox26");
                                HBox hboxMaximumQty5 = new HBox();
                                hboxMaximumQty5.setId("hbox26");
                                HBox hboxFinalQty5 = new HBox();
                                hboxFinalQty5.setId("hbox15");
                                //placing things in their containers
                                hboxMinimumQty5.getChildren().addAll(
                                        minimumQty1Lbl4, minimumQty1TxtFld4);
                                hboxMaximumQty5.getChildren().addAll(
                                        maximumQty1Lbl4, minimumQty2TxtFld4);
                                hboxFinalQty5.getChildren().addAll(
                                        hboxMinimumQty5, hboxMaximumQty5);
                                ///---configuration by selling price
                                //Labels
                                Label minimumSellingP1Lbl4 = new Label(
                                        "Minimum Selling Price:");
                                Label mixamumSellingP1Lbl4 = new Label(
                                        "Maximum Selling Price:");
                                //TextFields for holding the values
                                TextField minimumSellingP1TxtFld4 = new TextField();
                                TextField maximumSellingP1TxtFld4 = new TextField();
                                //HBoxes for the selling prices containers
                                HBox hboxMinimumSellingP5 = new HBox();
                                hboxMinimumSellingP5.setId("hbox26");
                                HBox hboxMaximumSellingP5 = new HBox();
                                hboxMaximumSellingP5.setId("hbox26");
                                HBox hboxSellingPConfig5 = new HBox();
                                hboxSellingPConfig5.setId("hbox16");
                                ///Placing the things in their containers
                                hboxMinimumSellingP5.getChildren().addAll(
                                        minimumSellingP1Lbl4,
                                        minimumSellingP1TxtFld4);
                                hboxMaximumSellingP5.getChildren().addAll(
                                        mixamumSellingP1Lbl4,
                                        maximumSellingP1TxtFld4);
                                hboxSellingPConfig5.getChildren().addAll(
                                        hboxMinimumSellingP5,
                                        hboxMaximumSellingP5);
                                ////---placing the chart configuration controls in a general container
                                VBox vboxConfigTools4 = new VBox();
                                vboxConfigTools4.setId("vbox3");
                                vboxConfigTools4.setPrefWidth(gW4 * 0.45);
                                vboxConfigTools4.getChildren().addAll(
                                        hboxGraphTitlelbl4,
                                        hboxFinalDates1Config4, hboxFinalQty5,
                                        hboxSellingPConfig5);
                                //prining and report configurations
                                //Report printer title
                                Label printer1Lbl4 = new Label("Report Printer");
                                ///HBox for the printer Title
                                HBox hboxPrinter1Lbl4 = new HBox();
                                hboxPrinter1Lbl4.setId("hbox13");
                                hboxPrinter1Lbl4.getChildren().add(printer1Lbl4);
                                //Configure Report Title
                                Label reportTitle1Lbl4 = new Label(
                                        "Report Title:");
                                //Text Field For Report title
                                UpperCaseTextField reportTitle1TxtFld4 = new UpperCaseTextField();
                                reportTitle1TxtFld4.setPrefWidth(gW4 * 0.25);
                                //HBox for the report Title
                                HBox hboxReportTitle4 = new HBox();
                                hboxReportTitle4.setId("hbox26");
                                hboxReportTitle4.getChildren().addAll(
                                        reportTitle1Lbl4, reportTitle1TxtFld4);
                                //Attachment
                                Hyperlink attachment1HLink4 = new Hyperlink(
                                        "Attach Document");
                                //File Chooser
                                FileChooser chooseAttachment1FChooser4 = new FileChooser();
                                //HBox for the file chooser and containment
                                HBox hboxFileChooserHLink4 = new HBox();
                                hboxFileChooserHLink4.setAlignment(
                                        Pos.CENTER_RIGHT);
                                hboxFileChooserHLink4.getChildren().addAll(
                                        attachment1HLink4);
                                //setting the action for the hyperlink
                                attachment1HLink4.setOnAction(
                                        (ActionEvent e) -> {
                                            chooseAttachment1FChooser4.setTitle(
                                                    "Attach Document");
                                            File fileAttachment1 = chooseAttachment1FChooser4.showOpenDialog(
                                                    stage5);
                                            if (fileAttachment1 != null) {
                                                openFile(fileAttachment1);
                                            }
                                        });
                                //print button
                                Button print1Btn4 = new Button("Print Report");
                                print1Btn4.getStyleClass().add("btn-info");
                                HBox hboxPrint1Btn4 = new HBox();
                                hboxPrint1Btn4.setAlignment(Pos.CENTER_RIGHT);
                                hboxPrint1Btn4.getChildren().add(print1Btn4);
                                ///---final containment for the report printer
                                VBox vboxReportsPrinter4 = new VBox();
                                vboxReportsPrinter4.setId("vbox3");
                                vboxReportsPrinter4.setPrefWidth(gW4 * 0.3);
                                vboxReportsPrinter4.getChildren().addAll(
                                        hboxPrinter1Lbl4, hboxReportTitle4,
                                        hboxFileChooserHLink4, hboxPrint1Btn4);
                                HBox hboxReportsAndTools4 = new HBox();
                                hboxReportsAndTools4.setId("hbox7");
                                hboxReportsAndTools4.getChildren().addAll(
                                        vboxConfigTools4, vboxReportsPrinter4);
                                ///placing the charts and config tools in their VBox
                                VBox vboxChartAnsTools4 = new VBox();
                                vboxChartAnsTools4.getChildren().addAll(
                                        hboxChartAndSummary4,
                                        hboxReportsAndTools4);
                                ////////////////////////
                                //BorderPane Setting Center
                                inventoryRecordsBP.setCenter(vboxChartAnsTools4);
                            }
                        }
                    });

            return inventoryRecordsBP;
        }

        public BorderPane expensesRecord() {
            Stage stage1 = new Stage();

            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ////---Inventory Records Main Page Configuration
            BorderPane expensesRecordsBP = new BorderPane();

            //Foreword Before use of the stock analysis
            TextArea forewordTextArea = new TextArea();
            forewordTextArea.setId("textArea1");
            forewordTextArea.setMinSize(gW * 0.18, gH * 0.6);
            forewordTextArea.setMaxSize(gW * 0.18, gH * 0.6);
            forewordTextArea.setEditable(false);
            forewordTextArea.setFocusTraversable(false);
            forewordTextArea.setText("                 HELP    \n"
                    + "To configure the graphs/chart, \n"
                    + "make the selections within \n"
                    + "the graph controls as desired \n"
                    + "for the desired representation.\n\n"
                    + "Different combinations can be \n"
                    + "used to obtain a more precise \n"
                    + "representation.\n\n"
                    + "However, the user may not make \n"
                    + "a selection when general \n"
                    + "representation is required\n\n"
            );

            HBox hboxForeword = new HBox();
            hboxForeword.getChildren().add(forewordTextArea);

            VBox vboxSelectInventoryRecords = new VBox();
            vboxSelectInventoryRecords.setId("vbox4");
            vboxSelectInventoryRecords.setMinWidth(gW * 0.2);
            vboxSelectInventoryRecords.getChildren().add(hboxForeword);

            //seeting the menu on the left of the inventory page
            expensesRecordsBP.setLeft(vboxSelectInventoryRecords);

            //main configuration/container for charts
            VBox vboxChartsMajContainer = new VBox();
            vboxChartsMajContainer.setId("vbox3");

            ////---chart area
            //setting Title
            Label chartsTitleLbl = new Label("Business Expenses Analyzer");

            //HBox for the title
            HBox hboxChartTitleLbl = new HBox();
            hboxChartTitleLbl.setId("hbox13");
            hboxChartTitleLbl.getChildren().add(chartsTitleLbl);

            ///Toggle group for the radio buttons
            final ToggleGroup radioBtnsGroup = new ToggleGroup();

            //radio butons for selecting the charts to show
            RadioButton barGraphRBtn = new RadioButton("Bar Graph");
            barGraphRBtn.setToggleGroup(radioBtnsGroup);
            barGraphRBtn.setSelected(true);

            RadioButton lineGraphRBtn = new RadioButton("Line Graph");
            lineGraphRBtn.setToggleGroup(radioBtnsGroup);

            RadioButton pieChartRBtn = new RadioButton("Pie Chart");
            pieChartRBtn.setToggleGroup(radioBtnsGroup);

            //HBox for the ToggleGroup
            HBox hboxToggleGroup = new HBox();
            hboxToggleGroup.setId("hbox13");
            hboxToggleGroup.getChildren().addAll(barGraphRBtn, lineGraphRBtn,
                    pieChartRBtn);

            //the bar graph configuration
            final CategoryAxis xAxis1 = new CategoryAxis();
            final NumberAxis yAxis1 = new NumberAxis();

            xAxis1.setLabel("Amount");
            yAxis1.setLabel("Products/Services");

            final BarChart<String, Number> inventoryRecordsBarChart = new BarChart<>(
                    xAxis1, yAxis1);
            inventoryRecordsBarChart.setMinWidth(gW * 0.45);

            inventoryRecordsBarChart.setTitle(
                    "Amount Spent Against Products/Services");

            ///the line Graph
            final CategoryAxis xAxis2 = new CategoryAxis();
            final NumberAxis yAxis2 = new NumberAxis();

            xAxis2.setLabel("Amount");
            yAxis2.setLabel("Products/Services");

            final LineChart<String, Number> inventoryRecordsLineChart = new LineChart<>(
                    xAxis2, yAxis2);
            inventoryRecordsLineChart.setMinWidth(gW * 0.45);

            inventoryRecordsLineChart.setTitle(
                    "Amount Spent Against Products/Services");

            ///The PieChart
            final PieChart inventoryRecordsPieChart = new PieChart();
            inventoryRecordsPieChart.setTitle("Expenses Composition");
            inventoryRecordsPieChart.setMinWidth(gW * 0.45);

            HBox hboxChartArea = new HBox();

            //just to cater for the already selected radio button bar chart
            hboxChartArea.getChildren().clear();
            hboxChartArea.getChildren().add(inventoryRecordsBarChart);

            radioBtnsGroup.selectedToggleProperty().addListener(
                    new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ov,
                        Toggle old_toggle, Toggle new_toggle) {
                    if (radioBtnsGroup.getSelectedToggle() != null) {
                        if (barGraphRBtn.isSelected()) {
                            hboxChartArea.getChildren().clear();
                            hboxChartArea.getChildren().add(
                                    inventoryRecordsBarChart);
                        } else if (lineGraphRBtn.isSelected()) {
                            hboxChartArea.getChildren().clear();
                            hboxChartArea.getChildren().add(
                                    inventoryRecordsLineChart);
                        } else if (pieChartRBtn.isSelected()) {
                            hboxChartArea.getChildren().clear();
                            hboxChartArea.getChildren().add(
                                    inventoryRecordsPieChart);
                        }
                    }
                }
            });

            //the charts section wrapping up as one thing
            vboxChartsMajContainer.getChildren().addAll(hboxChartTitleLbl,
                    hboxToggleGroup, hboxChartArea);

            ////---the summary table
            ////---HBox for the charts and the summaryTable
            HBox hboxChartsSummaryTbl = new HBox();

            //table
            TableView<ExpensesSummaryData> ExpensesSummary = new TableView<>();
            ObservableList<ExpensesSummaryData> ExpensesSummaryItems = FXCollections.observableArrayList();
            ExpensesSummary.setEditable(false);
            ExpensesSummary.setPrefHeight(gH * 0.56);
            ExpensesSummary.setItems(ExpensesSummaryItems);

            ExpensesSummary.getColumns().clear();

            TableColumn ExpensesSummary_Product_Service = new TableColumn(
                    "Product/Service");
            ExpensesSummary_Product_Service.setPrefWidth(gW * 0.1);
            ExpensesSummary_Product_Service.setCellValueFactory(
                    new PropertyValueFactory<ExpensesSummaryData, String>(
                            "Product/Service"));
            ExpensesSummary.getColumns().addAll(ExpensesSummary_Product_Service);

            TableColumn ExpensesSummary_PaidTo = new TableColumn("PaidTo");
            ExpensesSummary_PaidTo.setPrefWidth(gW * 0.1);
            ExpensesSummary_PaidTo.setCellValueFactory(
                    new PropertyValueFactory<ExpensesSummaryData, String>(
                            "PaidTo"));
            ExpensesSummary.getColumns().addAll(ExpensesSummary_PaidTo);

            TableColumn ExpensesSummary_Amount = new TableColumn("Amount");
            ExpensesSummary_Amount.setPrefWidth(gW * 0.1);
            ExpensesSummary_Amount.setCellValueFactory(
                    new PropertyValueFactory<ExpensesSummaryData, String>(
                            "Amount"));
            ExpensesSummary.getColumns().addAll(ExpensesSummary_Amount);

            //table into HBox with Title Label
            Label summaryTableTitleLbl = new Label("Summary Table");

            HBox hboxSummaryTblTitle = new HBox();
            hboxSummaryTblTitle.setId("hbox13");
            hboxSummaryTblTitle.getChildren().add(summaryTableTitleLbl);
            hboxChartsSummaryTbl.getChildren().add(ExpensesSummary);

            VBox vboxFinalSummaryTbl = new VBox();
            vboxFinalSummaryTbl.setId("vbox3");
            vboxFinalSummaryTbl.getChildren().addAll(hboxSummaryTblTitle,
                    hboxChartsSummaryTbl);

            //final center HBox
            HBox hboxChartAndSummary = new HBox();
            hboxChartAndSummary.setId("hbox7");
            hboxChartAndSummary.getChildren().addAll(vboxChartsMajContainer,
                    vboxFinalSummaryTbl);

            ////----Confgurations for the lower central part... these configure the graphs
            //Title for the configurations
            Label configurationsTitleLbl = new Label("Graph Controls");

            //hbox graph control container
            HBox hboxGraphTitlelbl = new HBox();
            hboxGraphTitlelbl.setId("hbox13");
            hboxGraphTitlelbl.getChildren().add(configurationsTitleLbl);

            //Labels for the Dates
            Label initialDate1Lbl = new Label("Starting Date:");
            Label finalDate1Lbl = new Label("End Date:");

            //Date Comparison for the graph controls
            DatePicker initialDatePk1 = new DatePicker();
            DatePicker finalDatePk1 = new DatePicker();

            //DatePicker containers
            HBox hboxInitialDate1 = new HBox();
            hboxInitialDate1.setId("hbox26");
            HBox hboxFinalDate1 = new HBox();
            hboxFinalDate1.setId("hbox26");
            HBox hboxFinalDates1Config = new HBox();
            hboxFinalDates1Config.setId("hbox18");

            //placing all of them in their containers
            hboxInitialDate1.getChildren().addAll(initialDate1Lbl,
                    initialDatePk1);
            hboxFinalDate1.getChildren().addAll(finalDate1Lbl, finalDatePk1);
            hboxFinalDates1Config.getChildren().addAll(hboxInitialDate1,
                    hboxFinalDate1);

            ///---Configuration by Quantity
            //Labels
            Label minimumAmount1Lbl = new Label("Minimum Amount:");
            Label maximumAmount1Lbl = new Label("Maximum Amount:");

            //TextFields for the Quantity Values
            DecimalTextField minimumAmount1TxtFld = new DecimalTextField();
            DecimalTextField minimumAmount2TxtFld = new DecimalTextField();

            //HBoxes for the quantity controls
            HBox hboxMinimumAmount1 = new HBox();
            hboxMinimumAmount1.setId("hbox26");
            HBox hboxMaximumAmount1 = new HBox();
            hboxMaximumAmount1.setId("hbox26");
            HBox hboxFinalAmount1 = new HBox();
            hboxFinalAmount1.setId("hbox19");

            //placing things in their containers
            hboxMinimumAmount1.getChildren().addAll(minimumAmount1Lbl,
                    minimumAmount1TxtFld);
            hboxMaximumAmount1.getChildren().addAll(maximumAmount1Lbl,
                    minimumAmount2TxtFld);
            hboxFinalAmount1.getChildren().addAll(hboxMinimumAmount1,
                    hboxMaximumAmount1);

            ///---configuration by selling price
            //Labels
            Label minimumPdtService1Lbl = new Label("Product/Service:");

            //TextFields for holding the values
            UpperCaseTextField minimumPdtService1TxtFld = new UpperCaseTextField();

            //HBoxes for the selling prices containers
            HBox hboxPdtServiceConfig1 = new HBox();
            hboxPdtServiceConfig1.setId("hbox20");

            ///Placing the things in their containers
            hboxPdtServiceConfig1.getChildren().addAll(minimumPdtService1Lbl,
                    minimumPdtService1TxtFld);

            ////---placing the chart configuration controls in a general container
            VBox vboxConfigTools = new VBox();
            vboxConfigTools.setId("vbox3");
            vboxConfigTools.setPrefWidth(gW * 0.45);
            vboxConfigTools.getChildren().addAll(hboxGraphTitlelbl,
                    hboxFinalDates1Config, hboxFinalAmount1,
                    hboxPdtServiceConfig1);

            //prining and report configurations
            //Report printer title
            Label printer1Lbl = new Label("Report Printer");

            ///HBox for the printer Title
            HBox hboxPrinter1Lbl = new HBox();
            hboxPrinter1Lbl.setId("hbox13");
            hboxPrinter1Lbl.getChildren().add(printer1Lbl);

            //Configure Report Title
            Label reportTitle1Lbl = new Label("Report Title:");
            //Text Field For Report title
            UpperCaseTextField reportTitle1TxtFld = new UpperCaseTextField();
            reportTitle1TxtFld.setPrefWidth(gW * 0.25);

            //HBox for the report Title
            HBox hboxReportTitle = new HBox();
            hboxReportTitle.setId("hbox26");
            hboxReportTitle.getChildren().addAll(reportTitle1Lbl,
                    reportTitle1TxtFld);

            //Attachment
            Hyperlink attachment1HLink = new Hyperlink("Attach Document");
            //File Chooser
            FileChooser chooseAttachment1FChooser = new FileChooser();

            //HBox for the file chooser and containment
            HBox hboxFileChooserHLink = new HBox();
            hboxFileChooserHLink.setAlignment(Pos.CENTER_RIGHT);
            hboxFileChooserHLink.getChildren().addAll(attachment1HLink);

            //setting the action for the hyperlink
            attachment1HLink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    chooseAttachment1FChooser.setTitle("Attach Document");
                    File fileAttachment1 = chooseAttachment1FChooser.showOpenDialog(
                            stage1);
                    if (fileAttachment1 != null) {
                        openFile(fileAttachment1);
                    }
                }
            });

            //print button
            Button print1Btn = new Button("Print Report");
            print1Btn.getStyleClass().add("btn-info");
            HBox hboxPrint1Btn = new HBox();
            hboxPrint1Btn.setAlignment(Pos.CENTER_RIGHT);
            hboxPrint1Btn.getChildren().add(print1Btn);

            ///---final containment for the report printer
            VBox vboxReportsPrinter = new VBox();
            vboxReportsPrinter.setId("vbox3");
            vboxReportsPrinter.setPrefWidth(gW * 0.3);
            vboxReportsPrinter.getChildren().addAll(hboxPrinter1Lbl,
                    hboxReportTitle, hboxFileChooserHLink, hboxPrint1Btn);

            HBox hboxReportsAndTools = new HBox();
            hboxReportsAndTools.setId("hbox7");
            hboxReportsAndTools.getChildren().addAll(vboxConfigTools,
                    vboxReportsPrinter);

            ///placing the charts and config tools in their VBox
            VBox vboxChartAnsTools = new VBox();
            vboxChartAnsTools.getChildren().addAll(hboxChartAndSummary,
                    hboxReportsAndTools);

            //BorderPane Setting Center
            expensesRecordsBP.setCenter(vboxChartAnsTools);

            return expensesRecordsBP;
        }

        private void openFile(File file) {

            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(
                        records.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        }
    }

    public class sellPage {

        String userID;
        String username;
        DecimalFormat df = new DecimalFormat("###,###,###");
        String cur = "UGX";
        TextField txf = new TextField();
        TextField hiddenBarcodeInput;
        TextField retailBarcode = null;
        TextField packetBarcode = null;

        public sellPage(String userID, String username, TextField hiddenBarcodex) throws UsbException, UnsupportedEncodingException {
            this.userID = userID;
            this.username = username;
            this.hiddenBarcodeInput = hiddenBarcodex;
            BarcodeClass BarcodeClass = new BarcodeClass(this.hiddenBarcodeInput);
            //UsbServices services = (org.usb4java.javax.Services) UsbHostManager.getUsbServices();

            //UsbHub root = services.getRootUsbHub();
            //BarcodeClass.findDevice(root);

            this.hiddenBarcodeInput.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    String str = this.hiddenBarcodeInput.getText();
                    Thread.sleep(500);
                    if (str.equals(this.hiddenBarcodeInput.getText()) && !str.equals("")) {
                        if (retailBarcode != null) {
                            retailBarcode.setText(str);
                        }
                        if (packetBarcode != null) {
                            packetBarcode.setText(str);
                        }
                        this.hiddenBarcodeInput.setText("");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        public String userIDProperty() {
            return this.userID;
        }

        public String usernameProperty() {
            return this.username;
        }

        public BorderPane packetSell() throws ClassNotFoundException, UsbException, UnsupportedEncodingException, Throwable {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            packetBarcode = new TextField();

            receipts receipt = new receipts();
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            //Main Page Configuration using Main BorderPane
            BorderPane packetSellMajBP = new BorderPane();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0");

            //pick suff from the inventory table... required at the start
            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorPacketInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(2.5));

            //Packet Inventory Title
            Label packetInventoryTitleLbl = new Label("Packet Inventory/Stock");
            //HBox to hold the title
            HBox hboxPacketInventoryTitle = new HBox();
            hboxPacketInventoryTitle.setId("hbox4");
            hboxPacketInventoryTitle.getChildren().add(packetInventoryTitleLbl);

            //the search panel
            UpperCaseTextField searchPktStockTxtFld = new UpperCaseTextField();
            searchPktStockTxtFld.setPromptText("Type to search item");

            //
            Button retailItemBtn = new Button("Retail Item");
            retailItemBtn.getStyleClass().add("btn-info");
            //search panel HBox container
            HBox hboxSearchPktStock = new HBox();
            hboxSearchPktStock.setId("hbox42");
            hboxSearchPktStock.getChildren().addAll(searchPktStockTxtFld,
                    retailItemBtn);

            //Packet Inventory Table to list items to be sold
            TableView<PacketInventoryData> PacketInventory = new TableView<>();
            ObservableList<PacketInventoryData> PacketInventoryItems = FXCollections.observableArrayList();
            PacketInventory.setEditable(false);
            PacketInventory.setPrefHeight(gH * 0.5);
            PacketInventory.setItems(PacketInventoryItems);

            PacketInventory.getColumns().clear();

            TableColumn PacketInventory_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            PacketInventory_Barcode_ID.setId("remainingClms");
            PacketInventory_Barcode_ID.setPrefWidth(gW * 0.08);
            PacketInventory_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<PacketInventoryData, String>(
                            "Barcode_ID"));
            PacketInventory.getColumns().addAll(PacketInventory_Barcode_ID);

            TableColumn PacketInventory_ItemName = new TableColumn("ItemName");
            PacketInventory_ItemName.setId("remainingClms");
            PacketInventory_ItemName.setPrefWidth(gW * 0.08);
            PacketInventory_ItemName.setCellValueFactory(
                    new PropertyValueFactory<PacketInventoryData, String>(
                            "ItemName"));
            PacketInventory.getColumns().addAll(PacketInventory_ItemName);

            TableColumn PacketInventory_Description = new TableColumn(
                    "Description");
            PacketInventory_Description.setId("remainingClms");
            PacketInventory_Description.setPrefWidth(gW * 0.08);
            PacketInventory_Description.setCellValueFactory(
                    new PropertyValueFactory<PacketInventoryData, String>(
                            "Description"));
            PacketInventory.getColumns().addAll(PacketInventory_Description);

            TableColumn PacketInventory_Type = new TableColumn("Type");
            PacketInventory_Type.setId("remainingClms");
            PacketInventory_Type.setPrefWidth(gW * 0.08);
            PacketInventory_Type.setCellValueFactory(
                    new PropertyValueFactory<PacketInventoryData, String>("Type"));
            PacketInventory.getColumns().addAll(PacketInventory_Type);

            TableColumn PacketInventory_RemainingQuantity = new TableColumn(
                    "RemainingQty");
            PacketInventory_RemainingQuantity.setId("qtyCell");
            PacketInventory_RemainingQuantity.setPrefWidth(gW * 0.07);
            PacketInventory_RemainingQuantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<PacketInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<PacketInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(
                            p.getValue().RemainingQuantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            PacketInventory.getColumns().addAll(
                    PacketInventory_RemainingQuantity);

            TableColumn PacketInventory_UnitPrice = new TableColumn("UnitPrice");
            PacketInventory_UnitPrice.setId("moneyCell");
            PacketInventory_UnitPrice.setPrefWidth(gW * 0.06);
            PacketInventory_UnitPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<PacketInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<PacketInventoryData, Boolean> p) {
                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            PacketInventory.getColumns().addAll(PacketInventory_UnitPrice);

            selectInventoryItems.stream().forEach((selectInventoryItem) -> {
                PacketInventoryItems.add(new PacketInventoryData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(6), selectInventoryItem.get(9)));
            });

            ///binding table to service data
            svc.setOnSucceeded((WorkerStateEvent t) -> {
                if (!svc.getValue().equals(svc.getLastValue())) {
                    PacketInventoryItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();
                    tableViewItemsSum.stream().forEach((tableViewItemsSum1) -> {
                        PacketInventoryItems.add(new PacketInventoryData(
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5)));
                    });

                }
            });
            svc.start();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();

            //Table Container
            HBox hboxPacketTbl = new HBox();
            hboxPacketTbl.getChildren().add(PacketInventory);

            //VBox for the packet Inventory Title, search panel and table
            VBox vboxPacketInventory = new VBox();
            vboxPacketInventory.setId("vbox3");
            vboxPacketInventory.getChildren().addAll(hboxPacketInventoryTitle,
                    hboxSearchPktStock, hboxPacketTbl);

            //Title for the Temporary packet table
            Label tempPacketSaleLbl = new Label("Buyer's Packet Cart");
            tempPacketSaleLbl.setId("label2");
            //its HBox
            HBox hboxTempPacketSale = new HBox();
            hboxTempPacketSale.setId("hbox13");
            hboxTempPacketSale.getChildren().add(tempPacketSaleLbl);

            //Temporary sell table for the Packet items leaving stock
            TableView<TempPacketSaleData> TempPacketSale = new TableView<>();
            ObservableList<TempPacketSaleData> TempPacketSaleItems = FXCollections.observableArrayList();
            TempPacketSale.setEditable(false);
            TempPacketSale.setPrefHeight(gH * 0.5);
            TempPacketSale.setItems(TempPacketSaleItems);

            TempPacketSale.getColumns().clear();

            TableColumn TempPacketSale_Barcode_ID = new TableColumn("Barcode/ID");
            TempPacketSale_Barcode_ID.setId("remainingClms");
            TempPacketSale_Barcode_ID.setPrefWidth(gW * 0.08);
            TempPacketSale_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<TempPacketSaleData, String>(
                            "Barcode_ID"));
            TempPacketSale.getColumns().addAll(TempPacketSale_Barcode_ID);

            TableColumn TempPacketSale_ItemName = new TableColumn("ItemName");
            TempPacketSale_ItemName.setId("remainingClms");
            TempPacketSale_ItemName.setPrefWidth(gW * 0.08);
            TempPacketSale_ItemName.setCellValueFactory(
                    new PropertyValueFactory<TempPacketSaleData, String>(
                            "ItemName"));
            TempPacketSale.getColumns().addAll(TempPacketSale_ItemName);

            TableColumn TempPacketSale_Description = new TableColumn(
                    "Description");
            TempPacketSale_Description.setId("remainingClms");
            TempPacketSale_Description.setPrefWidth(gW * 0.08);
            TempPacketSale_Description.setCellValueFactory(
                    new PropertyValueFactory<TempPacketSaleData, String>(
                            "Description"));
            TempPacketSale.getColumns().addAll(TempPacketSale_Description);

            TableColumn TempPacketSale_QtySold = new TableColumn("QtySold");
            TempPacketSale_QtySold.setId("qtyCell");
            TempPacketSale_QtySold.setPrefWidth(gW * 0.05);
            TempPacketSale_QtySold.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempPacketSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempPacketSaleData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().QtySold.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempPacketSale.getColumns().addAll(TempPacketSale_QtySold);

            TableColumn TempPacketSale_Amount = new TableColumn("Amount");
            TempPacketSale_Amount.setId("moneyCell");
            TempPacketSale_Amount.setPrefWidth(gW * 0.08);
            TempPacketSale_Amount.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempPacketSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempPacketSaleData, Boolean> p) {
                    double money = parseDouble(p.getValue().Amount.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempPacketSale.getColumns().addAll(TempPacketSale_Amount);

            //Container for the temp table
            HBox hboxTempTable = new HBox();
            hboxTempTable.getChildren().add(TempPacketSale);

            //////design a good html receipt using the webView and place it in the HBox container
            WebView receiptWebView = new WebView();
            //the define receipt

            //webView HBox Container
            HBox hboxReceiptWebView = new HBox();
            hboxReceiptWebView.setId("hbox21");
            hboxReceiptWebView.setMinSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.setMaxSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.getChildren().add(receiptWebView);

            //Lower help panel
            TextArea helpTextArea = new TextArea();
            helpTextArea.setEditable(false);
            helpTextArea.setText("         HELP\n"
                    + "Double-Click item\n"
                    + "to sell.\n\n"
                    + "Double-Click item\n"
                    + "to cancel sale");
            helpTextArea.setMinSize(gW * 0.177, gH * 0.196);
            helpTextArea.setMaxSize(gW * 0.177, gH * 0.196);

            HBox hboxHelpTxtArea = new HBox();
            hboxHelpTxtArea.setId("hbox21");
            hboxHelpTxtArea.getChildren().add(helpTextArea);
            hboxHelpTxtArea.setMinSize(gW * 0.18, gH * 0.2);
            hboxHelpTxtArea.setMaxSize(gW * 0.18, gH * 0.2);

            //VBox for the temporary sale table
            VBox vboxTempSaleTable = new VBox();
            vboxTempSaleTable.setId("vbox6");
            vboxTempSaleTable.getChildren().addAll(hboxTempPacketSale,
                    hboxTempTable);

            ////checkout and payments panel
            ///Label for the title
            Label selectedItemLbl = new Label("Checkout And Sell");
            //HBox for its containment
            HBox hboxSelectedItemsLbl = new HBox();
            hboxSelectedItemsLbl.setId("hbox13");
            hboxSelectedItemsLbl.getChildren().add(selectedItemLbl);

            ///Label and textField for the barcode/ID that is of the selected item
            Label selectedItemBCodeIDLbl = new Label("Barcode/ID:");
            TextField selectedItemBCodeIDTxtFld = new TextField();
            selectedItemBCodeIDTxtFld.setEditable(false);
            selectedItemBCodeIDTxtFld.setMinWidth(gW * 0.098);
            selectedItemBCodeIDTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxSelectedBCodeID = new HBox();
            hboxSelectedBCodeID.setId("hbox26");
            hboxSelectedBCodeID.getChildren().addAll(selectedItemBCodeIDLbl,
                    selectedItemBCodeIDTxtFld);

            ///Label and TextField for the selected item name
            Label selectedItemNameLbl = new Label("ItemName:");
            TextField selectedItemNameTxtFld = new TextField();
            selectedItemNameTxtFld.setMinWidth(gW * 0.098);
            selectedItemNameTxtFld.setMaxWidth(gW * 0.098);
            selectedItemNameTxtFld.setEditable(false);
            //Hbox for their containment
            HBox hboxSelectedItemName = new HBox();
            hboxSelectedItemName.setId("hbox26");
            hboxSelectedItemName.getChildren().addAll(selectedItemNameLbl,
                    selectedItemNameTxtFld);

            //Label and TextField for the Quantiyt to checkOut
            Label quantityCheckOutLbl = new Label("CheckOut Qty:");
            DecimalTextField quantityCheckOutTxtFld = new DecimalTextField();
            quantityCheckOutTxtFld.setMinWidth(gW * 0.098);
            quantityCheckOutTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxQtyCheckOut = new HBox();
            hboxQtyCheckOut.setId("hbox26");
            hboxQtyCheckOut.getChildren().addAll(quantityCheckOutLbl,
                    quantityCheckOutTxtFld);

            //HBox to carry checkOut button
            Button checkOutBtn = new Button("Cart Item");
            checkOutBtn.getStyleClass().add("btn-info");
            //HBox for containment
            HBox hboxCheckOutBtn = new HBox();
            hboxCheckOutBtn.setId("hbox23");
            hboxCheckOutBtn.getChildren().add(checkOutBtn);

            ///HBox for the lower middle section of the checkout/cart part
            HBox hboxSelectedItem = new HBox();
            hboxSelectedItem.setId("hbox7");
            hboxSelectedItem.getChildren().addAll(hboxSelectedBCodeID,
                    hboxSelectedItemName, hboxQtyCheckOut);

            //VBox for the checkout button and item
            VBox vboxChkOutItemBtn = new VBox();
            vboxChkOutItemBtn.setId("vbox1");
            vboxChkOutItemBtn.getChildren().addAll(hboxSelectedItem,
                    hboxCheckOutBtn);

            ////Lowest central Part for Amount paid and Change
            ///Title for the selling part
            Label paymentTitleLbl = new Label("Payment");
            //Hbox for containment
            HBox hboxPaymnetTitleLbl = new HBox();
            hboxPaymnetTitleLbl.setId("hbox13");
            hboxPaymnetTitleLbl.getChildren().add(paymentTitleLbl);

            //label and TextField for paid amount
            Label totalAmountLbl = new Label("Total Cost:");
            TextField totalAmountTxtFld = new TextField();
            totalAmountTxtFld.setEditable(false);
            totalAmountTxtFld.setId("txtFld1");

            totalAmountTxtFld.setMaxWidth(gW * 0.098);
            totalAmountTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxTotalAmount = new HBox();
            hboxTotalAmount.setId("hbox28");
            hboxTotalAmount.getChildren().addAll(totalAmountLbl,
                    totalAmountTxtFld);

            //label and TextField for paid amount
            Label paidAmountLbl = new Label("Payment:");
            DecimalTextField paidAmountTxtFld = new DecimalTextField();

            paidAmountTxtFld.setMaxWidth(gW * 0.098);
            paidAmountTxtFld.setMinWidth(gW * 0.098);

            //HBox for their containment
            HBox hboxPaidAmount = new HBox();
            hboxPaidAmount.setId("hbox26");
            hboxPaidAmount.getChildren().addAll(paidAmountLbl, paidAmountTxtFld);

            //label and TextField for the change remaining
            Label changeLbl = new Label("Change:");
            TextField changeTxtFld = new TextField();
            changeTxtFld.setId("txtFld1");
            changeTxtFld.setEditable(false);
            changeTxtFld.setMaxWidth(gW * 0.098);
            changeTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxChange = new HBox();
            hboxChange.setId("hbox41");
            hboxChange.getChildren().addAll(changeLbl, changeTxtFld);

            //Sell Commit Transaction Button
            Button commitTransactionBtn = new Button("     Sell     ");
            commitTransactionBtn.getStyleClass().add("btn-info");

            //HBox for the button's containment
            HBox hboxCommitTransactionBtn = new HBox();
            hboxCommitTransactionBtn.setId("hbox23");
            hboxCommitTransactionBtn.getChildren().add(commitTransactionBtn);

            //VBox for the selling
            VBox vboxSellTransaction = new VBox();
            vboxSellTransaction.setId("vbox1");
            vboxSellTransaction.getChildren().addAll(hboxPaidAmount,
                    hboxCommitTransactionBtn);

            //HBox for the whole lowest part
            HBox hboxFullTransaction = new HBox();
            hboxFullTransaction.setId("hbox24");
            hboxFullTransaction.getChildren().addAll(hboxTotalAmount,
                    vboxSellTransaction, hboxChange);

            //VBox for the checkOut and sell
            VBox vboxChkOutAndSell = new VBox();
            vboxChkOutAndSell.setId("vbox3");
            vboxChkOutAndSell.getChildren().addAll(hboxSelectedItemsLbl,
                    vboxChkOutItemBtn, hboxPaymnetTitleLbl, hboxFullTransaction);

            //Final HBox for the Centre part
            HBox hboxBPCenter1 = new HBox();
            hboxBPCenter1.getChildren().addAll(vboxPacketInventory,
                    vboxTempSaleTable);

            ////left lower part configuration
            ///label for the title
            Label cancelDiscountLbl = new Label("Cancel-Discount Sale");
            //HBox for containment
            HBox hboxCancelDiscountLbl = new HBox();
            hboxCancelDiscountLbl.setId("hbox13");
            hboxCancelDiscountLbl.getChildren().add(cancelDiscountLbl);

            ///Cancel ButtonhboxCancelCartItem
            Button cancelCartItemBtn = new Button("Remove Item");
            cancelCartItemBtn.getStyleClass().add("btn-danger");
            //hbox for containment
            HBox hboxCancelCartItem = new HBox();
            hboxCancelCartItem.setId("hbox23");
            hboxCancelCartItem.getChildren().add(cancelCartItemBtn);

            //whole thing coming together
            VBox vboxDiscountAndCancel = new VBox();
            vboxDiscountAndCancel.setId("vbox7");
            vboxDiscountAndCancel.setMinWidth(gW * 0.3165);
            vboxDiscountAndCancel.getChildren().addAll(hboxCancelDiscountLbl,
                    hboxCancelCartItem);

            ///HBxo for the lowest part
            HBox hboxSellAndDiscount = new HBox();
            hboxSellAndDiscount.setId("hbox7");
            hboxSellAndDiscount.getChildren().addAll(vboxChkOutAndSell,
                    vboxDiscountAndCancel);

            //vbox for the centre total config
            VBox vboxBPcenter1 = new VBox();
            vboxBPcenter1.getChildren().addAll(hboxBPCenter1,
                    hboxSellAndDiscount);

            //setting center for the main border pane
            packetSellMajBP.setCenter(vboxBPcenter1);

            //Receipt part
            Label receiptTitleLbl = new Label("Receipt Issued");
            receiptTitleLbl.setId("label1");
            //HBxo for the receipt title
            HBox hboxReceiptTitile = new HBox();
            hboxReceiptTitile.setId("hbox22");
            hboxReceiptTitile.getChildren().add(receiptTitleLbl);

            PacketInventory.setRowFactory(PI -> {
                TableRow<PacketInventoryData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                row.getItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                row.getItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                        quantityCheckOutTxtFld.requestFocus();
                    }
                });
                return row;
            });

            PacketInventory.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                    if (PacketInventory.getSelectionModel().getSelectedItem() != null) {

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                PacketInventory.getSelectionModel().getSelectedItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                PacketInventory.getSelectionModel().getSelectedItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                        quantityCheckOutTxtFld.requestFocus();
                    }

                } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            commitTransactionBtn.setOnAction((ActionEvent e) -> {
                DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                String totalAmt = totalAmountTxtFld.getText();
                String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                        "/=", "");
                String totalAmountStr = totalAmountStr1.replace(",", "");

                if (!paidAmountTxtFld.getText().isEmpty()) {
                    double totalAmountValue = parseDouble(totalAmountStr);
                    String change = df3.format(parseDouble(
                            paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                    if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                        ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                        ArrayList<String> receiptRows;

                        for (int x = 0; x < TempPacketSale.getItems().size();
                                x++) {
                            try {
                                ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect(
                                        "SELECT itemID, remainingQuantity, purchasePrice FROM inventory WHERE (barcode_ID='" + TempPacketSale.getItems().get(
                                                x).getBarcode_ID() + "' AND remainingQuantity > 0)");
                                int boughtQty = parseInt(
                                        TempPacketSale.getItems().get(x).getQtySold());
                                for (int i = 0; i < recordsIDs.size(); i++) {
                                    if (parseInt(recordsIDs.get(i).get(1)) < boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE inventory SET remainingQuantity = 0, fullyConsumedOn = '" + Timestamp.valueOf(
                                                        LocalDateTime.now()) + "' WHERE itemID = '" + recordsIDs.get(
                                                i).get(0) + "'");
                                        boughtQty = boughtQty - parseInt(
                                                recordsIDs.get(i).get(1));
                                    } else if (parseInt(recordsIDs.get(i).get(1)) >= boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE inventory SET remainingQuantity = '" + (parseInt(
                                                        recordsIDs.get(i).get(1)) - boughtQty) + "' WHERE itemID = '" + recordsIDs.get(
                                                i).get(0) + "'");
                                        break;
                                    }
                                }
                                try {
                                    merakiBusinessDBClass.processSQLInsert(
                                            "INSERT INTO sales (barcodeID, itemName, description, type, "
                                            + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                            + "VALUES ('" + TempPacketSale.getItems().get(
                                                    x).getBarcode_ID() + "',"
                                            + "'" + TempPacketSale.getItems().get(
                                                    x).getItemName() + "', "
                                            + "'" + TempPacketSale.getItems().get(
                                                    x).getDescription() + "', "
                                            + "'" + TempPacketSale.getItems().get(
                                                    x).getType() + "', "
                                            + "'" + parseInt(
                                                    TempPacketSale.getItems().get(
                                                            x).getQtySold()) + "', "
                                            + "'" + parseDouble(
                                                    TempPacketSale.getItems().get(
                                                            x).getUnitPrice()) + "', "
                                            + "'" + parseDouble(
                                                    (TempPacketSale.getItems().get(
                                                            x).getAmount().replaceAll(
                                                                    "/=", "")).replaceAll(
                                                                    ",", "")) + "', "
                                            + "'" + 1 + "','" + parseDouble(
                                                    recordsIDs.get(0).get(2)) + "', '" + parseInt(
                                            this.userIDProperty()) + "')");
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                            svc.restart();
                            svc.restart();
                            svc.restart();

                            receiptRows = new ArrayList<>();
                            receiptRows.add(
                                    TempPacketSale.getItems().get(x).getItemName());
                            receiptRows.add(
                                    TempPacketSale.getItems().get(x).getQtySold());
                            receiptRows.add((parseDouble(
                                    TempPacketSale.getItems().get(x).getAmount()) / parseInt(
                                    TempPacketSale.getItems().get(x).getQtySold())) + "");
                            receiptRows.add(df3.format(parseDouble(
                                    TempPacketSale.getItems().get(x).getAmount())) + "/=");

                            receiptVariables.add(receiptRows);
                        }

                        if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) == 0) {
                            try {
                                changeTxtFld.setText("0.00/=");
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempPacketSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        } else if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) > 0) {
                            try {
                                changeTxtFld.setText(change);
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempPacketSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        }
                    }

                }

            });

            paidAmountTxtFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                    String totalAmt = totalAmountTxtFld.getText();
                    String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                            "/=", "");
                    String totalAmountStr = totalAmountStr1.replace(",", "");

                    if (!paidAmountTxtFld.getDecimal().isEmpty()) {
                        double totalAmountValue = parseDouble(totalAmountStr);
                        String change = df3.format(parseDouble(
                                paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                        if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                            ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                            ArrayList<String> receiptRows;
                            for (int x = 0; x < TempPacketSale.getItems().size();
                                    x++) {
                                try {
                                    ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect(
                                            "SELECT itemID, remainingQuantity, purchasePrice FROM inventory WHERE (barcode_ID='" + TempPacketSale.getItems().get(
                                                    x).getBarcode_ID() + "' AND remainingQuantity > 0)");
                                    int boughtQty = parseInt(
                                            TempPacketSale.getItems().get(x).getQtySold());
                                    for (int i = 0; i < recordsIDs.size(); i++) {
                                        if (parseInt(recordsIDs.get(i).get(1)) < boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE inventory SET remainingQuantity = 0 WHERE itemID = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            boughtQty = boughtQty - parseInt(
                                                    recordsIDs.get(i).get(1));
                                        } else if (parseInt(
                                                recordsIDs.get(i).get(1)) >= boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE inventory SET remainingQuantity = '" + (parseInt(
                                                            recordsIDs.get(i).get(
                                                                    1)) - boughtQty) + "' WHERE itemID = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            break;
                                        }
                                    }

                                    try {
                                        merakiBusinessDBClass.processSQLInsert(
                                                "INSERT INTO sales (barcodeID, itemName, description, type, "
                                                + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                                + "VALUES ('" + TempPacketSale.getItems().get(
                                                        x).getBarcode_ID() + "',"
                                                + "'" + TempPacketSale.getItems().get(
                                                        x).getItemName() + "', "
                                                + "'" + TempPacketSale.getItems().get(
                                                        x).getDescription() + "', "
                                                + "'" + TempPacketSale.getItems().get(
                                                        x).getType() + "', "
                                                + "'" + parseInt(
                                                        TempPacketSale.getItems().get(
                                                                x).getQtySold()) + "', "
                                                + "'" + parseDouble(
                                                        TempPacketSale.getItems().get(
                                                                x).getUnitPrice()) + "', "
                                                + "'" + parseDouble(
                                                        (TempPacketSale.getItems().get(
                                                                x).getAmount().replaceAll(
                                                                        "/=", "")).replaceAll(
                                                                        ",", "")) + "', "
                                                + "'" + 1 + "','" + parseDouble(
                                                        recordsIDs.get(0).get(2)) + "', '" + parseInt(
                                                this.userIDProperty()) + "')");
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(
                                                Meraki101.class.getName()).log(
                                                Level.SEVERE, null, ex);
                                    }
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                                svc.restart();
                                svc.restart();
                                svc.restart();

                                receiptRows = new ArrayList<>();
                                receiptRows.add(
                                        TempPacketSale.getItems().get(x).getItemName());
                                receiptRows.add(
                                        TempPacketSale.getItems().get(x).getQtySold());
                                receiptRows.add((parseDouble(
                                        TempPacketSale.getItems().get(x).getAmount()) / parseInt(
                                        TempPacketSale.getItems().get(x).getQtySold())) + "");
                                receiptRows.add(df3.format(parseDouble(
                                        TempPacketSale.getItems().get(x).getAmount())) + "/=");

                                receiptVariables.add(receiptRows);
                            }

                            if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) == 0) {
                                try {
                                    changeTxtFld.setText("0.00/=");
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                    TempPacketSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            } else if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) > 0) {
                                try {
                                    changeTxtFld.setText(change);
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);
                                    TempPacketSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            }
                        }

                    }

                }
            });

            checkOutBtn.setOnAction(e -> {
                if (PacketInventory.getSelectionModel().getSelectedItem() != null && !quantityCheckOutTxtFld.getDecimal().isEmpty()) {
                    double remainingQty = parseDouble(
                            PacketInventory.getSelectionModel().getSelectedItem().getRemainingQuantity());
                    double soldQty = parseDouble(
                            quantityCheckOutTxtFld.getDecimal());
                    if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                        String barcodeID = PacketInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                        String itemName = PacketInventory.getSelectionModel().getSelectedItem().getItemName();
                        String description = PacketInventory.getSelectionModel().getSelectedItem().getDescription();
                        String type = PacketInventory.getSelectionModel().getSelectedItem().getType();
                        String qtySold = quantityCheckOutTxtFld.getDecimal();
                        double unitPrice = parseDouble(
                                PacketInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                        String totalCost = (soldQty * unitPrice) + "";

                        if (!TempPacketSaleItems.isEmpty()) {
                            for (int x = 0; x < TempPacketSaleItems.size(); x++) {
                                if (TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID)) {
                                    String newQuantity = (parseInt(
                                            TempPacketSaleItems.get(x).getQtySold()) + parseInt(
                                            qtySold)) + "";
                                    String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                    TempPacketSaleItems.set(x,
                                            new TempPacketSaleData(barcodeID,
                                                    itemName, description, type,
                                                    newQuantity, unitPrice + "",
                                                    newAmount));
                                    break;
                                } else if (!TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID) && x < (TempPacketSaleItems.size() - 1)) {
                                    continue;
                                } else {
                                    TempPacketSaleItems.add(
                                            new TempPacketSaleData(barcodeID,
                                                    itemName, description, type,
                                                    qtySold, unitPrice + "",
                                                    totalCost));
                                    break;
                                }
                            }

                        } else if (TempPacketSaleItems.isEmpty()) {
                            TempPacketSaleItems.add(new TempPacketSaleData(
                                    barcodeID, itemName, description, type,
                                    qtySold, unitPrice + "", totalCost));

                        }

                        svc.cancel();
                        svc.cancel();

                        PacketInventoryItems.get(
                                PacketInventory.getSelectionModel().getSelectedIndex()).setRemainingQuantity(
                                (parseInt(
                                        PacketInventory.getSelectionModel().getSelectedItem().getRemainingQuantity().replaceAll(
                                                ",", "")) - parseInt(qtySold)) + "");

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemNameTxtFld.clear();
                        quantityCheckOutTxtFld.clear();

                        PacketInventory.requestFocus();
                    }

                }
            }
            );

            TempPacketSaleItems.addListener(
                    (javafx.collections.ListChangeListener.Change<? extends TempPacketSaleData> pChange) -> {
                        while (pChange.next()) {
                            // Do your changes here
                            Double totalSalesValue = 0.00;
                            for (int x = 0; x < TempPacketSale.getItems().size();
                            x++) {
                                String totalSalesVal = TempPacketSale.getItems().get(
                                        x).Amount.get().replace("/=", "");
                                totalSalesValue = totalSalesValue + parseDouble(
                                        totalSalesVal);
                            }

                            DecimalFormat df2 = new DecimalFormat(
                                    "###,###,###.00");
                            totalAmountTxtFld.setText(
                                    df2.format(totalSalesValue) + "/=");
                        }
                    }
            );

            quantityCheckOutTxtFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.SHIFT)) {
                    if (PacketInventory.getSelectionModel().getSelectedItem() != null && !quantityCheckOutTxtFld.getText().isEmpty()) {
                        double remainingQty = parseDouble(
                                PacketInventory.getSelectionModel().getSelectedItem().getRemainingQuantity());
                        double soldQty = parseDouble(
                                quantityCheckOutTxtFld.getDecimal());
                        if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                            String barcodeID = PacketInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                            String itemName = PacketInventory.getSelectionModel().getSelectedItem().getItemName();
                            String description = PacketInventory.getSelectionModel().getSelectedItem().getDescription();
                            String type = PacketInventory.getSelectionModel().getSelectedItem().getType();
                            String qtySold = quantityCheckOutTxtFld.getDecimal();
                            double unitPrice = parseDouble(
                                    PacketInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                            String totalCost = (soldQty * unitPrice) + "";
                            if (!TempPacketSaleItems.isEmpty()) {
                                for (int x = 0; x < TempPacketSaleItems.size();
                                        x++) {
                                    if (TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                            barcodeID)) {
                                        String newQuantity = (parseInt(
                                                TempPacketSaleItems.get(x).getQtySold()) + parseInt(
                                                qtySold)) + "";
                                        String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                        TempPacketSaleItems.set(x,
                                                new TempPacketSaleData(barcodeID,
                                                        itemName, description,
                                                        type, newQuantity,
                                                        unitPrice + "",
                                                        newAmount));
                                        break;
                                    } else if (!TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                            barcodeID) && x < (TempPacketSaleItems.size() - 1)) {
                                        continue;
                                    } else {
                                        TempPacketSaleItems.add(
                                                new TempPacketSaleData(barcodeID,
                                                        itemName, description,
                                                        type, qtySold,
                                                        unitPrice + "",
                                                        totalCost));
                                        break;
                                    }
                                }

                            } else if (TempPacketSaleItems.isEmpty()) {
                                TempPacketSaleItems.add(new TempPacketSaleData(
                                        barcodeID, itemName, description, type,
                                        qtySold, unitPrice + "", totalCost));

                            }

                            svc.cancel();
                            svc.cancel();

                            PacketInventoryItems.get(
                                    PacketInventory.getSelectionModel().getSelectedIndex()).setRemainingQuantity(
                                    (parseInt(
                                            PacketInventory.getSelectionModel().getSelectedItem().getRemainingQuantity().replaceAll(
                                                    ",", "")) - parseInt(qtySold)) + "");

                            selectedItemBCodeIDTxtFld.clear();
                            selectedItemNameTxtFld.clear();
                            quantityCheckOutTxtFld.clear();

                            PacketInventory.requestFocus();
                        }

                    }
                } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            TempPacketSale.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            cancelCartItemBtn.setOnAction(e -> {
                if (TempPacketSale.getSelectionModel().getSelectedItem() != null) {
                    TempPacketSale.getItems().remove(
                            TempPacketSale.getSelectionModel().getSelectedIndex());
                }
            });

            retailItemBtn.setOnAction(e -> {

                if (PacketInventory.getSelectionModel().getSelectedItem() != null) {

                    ArrayList<ArrayList<String>> recordsIDs;
                    try {
                        recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT itemId, barcode_ID, "
                                + "unitQuantity, CEILING((purchasePrice/unitQuantity)/50)*50 as retPPrice, "
                                + "CEILING((unitPrice/unitQuantity)/100)*100 AS retUPrice, remainingQuantity "
                                + "FROM inventory WHERE (barcode_ID='" + PacketInventory.getSelectionModel().getSelectedItem().getBarcode_ID() + "' "
                                + "AND remainingQuantity > 0) LIMIT 1");
                        try {
                            ArrayList<ArrayList<String>> checkList = merakiBusinessDBClass.processSQLRetailSelect(
                                    "SELECT retialId, inventory_itemId FROM retialinventory WHERE (remainingQty > 0 AND inventory_itemId='" + recordsIDs.get(
                                            0).get(0) + "') LIMIT 1");
                            String lastItemPrice = merakiBusinessDBClass.processSQLGeneralSelect("SELECT unitPrice FROM merakibusinessdb.retialinventory WHERE (remainingQty = 0 AND inventory_itemId = " + recordsIDs.get(
                                    0).get(0) + ") ORDER BY inventory_itemId DESC LIMIT 1").get(0).get(0);

                            double itemPrice;
                            if (lastItemPrice != null) {
                                itemPrice = Double.parseDouble(lastItemPrice);
                            } else {
                                itemPrice = Double.parseDouble(recordsIDs.get(0).get(4));
                            }

                            if (checkList.isEmpty()) {
                                try {
                                    int status = merakiBusinessDBClass.processSQLInsert(
                                            "INSERT INTO retialinventory (packetQty, remainingQty, purchasePrice, "
                                            + "unitPrice, packetValue, retailedBy, inventory_itemId) VALUES (" + parseInt(
                                                    recordsIDs.get(0).get(2)) + ", "
                                            + "" + parseInt(
                                                    recordsIDs.get(0).get(2)) + ", "
                                            + "" + parseDouble(
                                                    recordsIDs.get(0).get(3)) + ", "
                                            + "" + itemPrice + ", "
                                            + "" + (itemPrice * parseInt(recordsIDs.get(0).get(2))) + ", "
                                            + "" + 1 + ", "
                                            + "" + parseInt(recordsIDs.get(0).get(0)) + ")");
                                    svc.restart();
                                    svc.restart();
                                    svc.restart();
                                    svc.restart();
                                    if (status == 1) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE inventory SET remainingQuantity = '" + (parseInt(
                                                        recordsIDs.get(0).get(5)) - 1) + "' WHERE itemID = '" + parseInt(
                                                recordsIDs.get(0).get(0)) + "'");
                                        svc.restart();
                                        svc.restart();
                                        svc.restart();
                                        svc.restart();

                                        Alert exceptionAlert = new Alert(
                                                Alert.AlertType.INFORMATION,
                                                "Item Added In Retail!",
                                                ButtonType.OK);
                                        exceptionAlert.showAndWait();
                                    }
                                    svc.cancel();
                                    svc.restart();
                                    svc.restart();
                                    svc.restart();
                                    svc.restart();

                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            } else if (!checkList.isEmpty()) {
                                txf.setText("Item Already Exists In Retail!");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Meraki101.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
                svc.restart();
                svc.restart();
                svc.restart();
                svc.restart();
            }
            );

            packetBarcode.textProperty().addListener((ObservableValue<? extends String> ov, String oldV, String newV) -> {
                try {
                    String str = packetBarcode.getText();
                    Thread.sleep(500);
                    if (str.equals(packetBarcode.getText()) && !str.isEmpty()) {
                        FilteredList<PacketInventoryData> filteredList = PacketInventory.getItems().filtered(p -> p.getBarcode_ID().equals(str));
                        FilteredList<TempPacketSaleData> cartFilteredList = TempPacketSale.getItems().filtered(p -> p.getBarcode_ID().equals(str));
                        if (cartFilteredList != null && !cartFilteredList.isEmpty()) {
                            if (filteredList != null && !filteredList.isEmpty()) {
                                double remainingQty = parseDouble(filteredList.get(0).getRemainingQuantity());
                                int soldQty = 1;
                                int totalInStock = Integer.parseInt(filteredList.get(0).getRemainingQuantity());
                                int cartItemsQty = Integer.parseInt(cartFilteredList.get(0).getQtySold());

                                if (totalInStock > cartItemsQty) {
                                    String barcodeID = filteredList.get(0).getBarcode_ID();
                                    String itemName = filteredList.get(0).getItemName();
                                    String description = filteredList.get(0).getDescription();
                                    String type = filteredList.get(0).getType();
                                    String qtySold = "" + soldQty;
                                    double unitPrice = parseDouble(filteredList.get(0).getUnitPrice());

                                    String totalCost = (soldQty * unitPrice) + "";
                                    if (!TempPacketSaleItems.isEmpty()) {
                                        for (int x = 0; x < TempPacketSaleItems.size();
                                                x++) {
                                            if (TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                                    barcodeID)) {
                                                String newQuantity = (parseInt(
                                                        TempPacketSaleItems.get(x).getQtySold()) + parseInt(
                                                        qtySold)) + "";
                                                String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                                TempPacketSaleItems.set(x,
                                                        new TempPacketSaleData(barcodeID,
                                                                itemName, description,
                                                                type, newQuantity,
                                                                unitPrice + "",
                                                                newAmount));
                                                break;
                                            } else if (!TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                                    barcodeID) && x < (TempPacketSaleItems.size() - 1)) {
                                                continue;
                                            } else {
                                                TempPacketSaleItems.add(
                                                        new TempPacketSaleData(barcodeID,
                                                                itemName, description,
                                                                type, qtySold,
                                                                unitPrice + "",
                                                                totalCost));
                                                break;
                                            }
                                        }

                                    } else if (TempPacketSaleItems.isEmpty()) {
                                        TempPacketSaleItems.add(new TempPacketSaleData(
                                                barcodeID, itemName, description, type,
                                                qtySold, unitPrice + "", totalCost));

                                    }
                                } else {
                                    Platform.runLater(() -> {
                                        Alert exceptionAlert = new Alert(
                                                Alert.AlertType.INFORMATION,
                                                "Sorry, Item Out of Stock!",
                                                ButtonType.OK);
                                        exceptionAlert.showAndWait();
                                    });
                                }
                            }
                        } else if (cartFilteredList.isEmpty()) {
                            if (filteredList != null && !filteredList.isEmpty()) {
                                double remainingQty = parseDouble(filteredList.get(0).getRemainingQuantity());
                                int soldQty = 1;
                                int totalInStock = Integer.parseInt(filteredList.get(0).getRemainingQuantity());
                                String barcodeID = filteredList.get(0).getBarcode_ID();
                                String itemName = filteredList.get(0).getItemName();
                                String description = filteredList.get(0).getDescription();
                                String type = filteredList.get(0).getType();
                                String qtySold = "" + soldQty;
                                double unitPrice = parseDouble(filteredList.get(0).getUnitPrice());

                                String totalCost = (soldQty * unitPrice) + "";
                                if (!TempPacketSaleItems.isEmpty()) {
                                    for (int x = 0; x < TempPacketSaleItems.size();
                                            x++) {
                                        if (TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                                barcodeID)) {
                                            String newQuantity = (parseInt(
                                                    TempPacketSaleItems.get(x).getQtySold()) + parseInt(
                                                    qtySold)) + "";
                                            String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                            TempPacketSaleItems.set(x,
                                                    new TempPacketSaleData(barcodeID,
                                                            itemName, description,
                                                            type, newQuantity,
                                                            unitPrice + "",
                                                            newAmount));
                                            break;
                                        } else if (!TempPacketSaleItems.get(x).getBarcode_ID().equals(
                                                barcodeID) && x < (TempPacketSaleItems.size() - 1)) {
                                            continue;
                                        } else {
                                            TempPacketSaleItems.add(
                                                    new TempPacketSaleData(barcodeID,
                                                            itemName, description,
                                                            type, qtySold,
                                                            unitPrice + "",
                                                            totalCost));
                                            break;
                                        }
                                    }

                                } else if (TempPacketSaleItems.isEmpty()) {
                                    TempPacketSaleItems.add(new TempPacketSaleData(
                                            barcodeID, itemName, description, type,
                                            qtySold, unitPrice + "", totalCost));

                                }
                            } else if (filteredList.isEmpty()) {
                                Platform.runLater(() -> {
                                    Alert exceptionAlert = new Alert(
                                            Alert.AlertType.INFORMATION,
                                            "Sorry, No Such Item Found!",
                                            ButtonType.OK);
                                    exceptionAlert.showAndWait();
                                });
                            }
                        }
                        packetBarcode.setText("");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            searchPktStockTxtFld.textProperty()
                    .addListener(e -> {
                        if (searchPktStockTxtFld.getText() != null) {
                            PacketInventory.setItems(PacketInventoryItems);
                            final FilteredList<PacketInventoryData> filteredList = PacketInventory.getItems().filtered(
                                    p -> p.getItemName().contains(
                                            searchPktStockTxtFld.getText()) || p.getBarcode_ID().contains(
                                    searchPktStockTxtFld.getText()) || p.getDescription().contains(
                                    searchPktStockTxtFld.getText()) || p.getType().contains(
                                    searchPktStockTxtFld.getText()));
                            PacketInventory.setItems(filteredList);

                        }
                    }
                    );

            //final VBox for the receipt Printing Part
            VBox vboxReceiptIssued = new VBox();

            vboxReceiptIssued.setId(
                    "vbox5");
            vboxReceiptIssued.getChildren()
                    .addAll(hboxReceiptTitile, hboxReceiptWebView,
                            hboxHelpTxtArea);

            packetSellMajBP.setLeft(vboxReceiptIssued);

            //final return method
            return packetSellMajBP;
        }

        public BorderPane retailSell() throws ClassNotFoundException, UsbException, UsbException, UnsupportedEncodingException {
            ///--prerequisites
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            receipts receipt = new receipts();
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            retailBarcode = null;
            retailBarcode = null;

            retailBarcode = new TextField();

            //Main Page Configuration using Main BorderPane
            BorderPane retailSellMajBP = new BorderPane();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLGeneralSelect(
                    "SELECT * FROM retialinventory WHERE remainingQty > 0");

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {

                    return new monitorRetailInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(2.5));

            //Retail Inventory Title
            Label retailInventoryTitleLbl = new Label("Retail Inventory/Stock");
            //HBox to hold the title
            HBox hboxRetailInventoryTitle = new HBox();
            hboxRetailInventoryTitle.setId("hbox4");
            hboxRetailInventoryTitle.getChildren().add(retailInventoryTitleLbl);

            //the search panel
            UpperCaseTextField searchRtlStockTxtFld = new UpperCaseTextField();
            searchRtlStockTxtFld.setPromptText("Type to search item");
            //search panel HBox container
            HBox hboxSearchRtlStock = new HBox();
            hboxSearchRtlStock.setId("hbox13");
            hboxSearchRtlStock.getChildren().add(searchRtlStockTxtFld);

            //Retail Inventory Table to list items to be sold
            TableView<RetailInventoryData> RetailInventory = new TableView<>();
            ObservableList<RetailInventoryData> RetailInventoryItems = FXCollections.observableArrayList();
            RetailInventory.setEditable(false);
            RetailInventory.setPrefHeight(gH * 0.5);
            RetailInventory.setItems(RetailInventoryItems);

            RetailInventory.getColumns().clear();

            TableColumn RetailInventory_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            RetailInventory_Barcode_ID.setId("remainingClms");
            RetailInventory_Barcode_ID.setPrefWidth(gW * 0.06);
            RetailInventory_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<RetailInventoryData, String>(
                            "Barcode_ID"));
            RetailInventory.getColumns().addAll(RetailInventory_Barcode_ID);

            TableColumn RetailInventory_ItemName = new TableColumn("ItemName");
            RetailInventory_ItemName.setId("remainingClms");
            RetailInventory_ItemName.setPrefWidth(gW * 0.08);
            RetailInventory_ItemName.setCellValueFactory(
                    new PropertyValueFactory<RetailInventoryData, String>(
                            "ItemName"));
            RetailInventory.getColumns().addAll(RetailInventory_ItemName);

            TableColumn RetailInventory_Description = new TableColumn(
                    "Description");
            RetailInventory_Description.setId("remainingClms");
            RetailInventory_Description.setPrefWidth(gW * 0.08);
            RetailInventory_Description.setCellValueFactory(
                    new PropertyValueFactory<RetailInventoryData, String>(
                            "Description"));
            RetailInventory.getColumns().addAll(RetailInventory_Description);

            TableColumn RetailInventory_Type = new TableColumn("Type");
            RetailInventory_Type.setId("remainingClms");
            RetailInventory_Type.setPrefWidth(gW * 0.06);
            RetailInventory_Type.setCellValueFactory(
                    new PropertyValueFactory<RetailInventoryData, String>("Type"));
            RetailInventory.getColumns().addAll(RetailInventory_Type);

            TableColumn RetailInventory_PacketQty = new TableColumn("PacketQty");
            RetailInventory_PacketQty.setId("qtyCell");
            RetailInventory_PacketQty.setPrefWidth(gW * 0.045);
            RetailInventory_PacketQty.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<RetailInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<RetailInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().PacketQty.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            RetailInventory.getColumns().addAll(RetailInventory_PacketQty);

            TableColumn RetailInventory_RemainingQty = new TableColumn(
                    "RemainingQty");
            RetailInventory_RemainingQty.setId("qtyCell");
            RetailInventory_RemainingQty.setPrefWidth(gW * 0.067);
            RetailInventory_RemainingQty.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<RetailInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<RetailInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(
                            p.getValue().RemainingQty.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            RetailInventory.getColumns().addAll(RetailInventory_RemainingQty);

            TableColumn RetailInventory_UnitPrice = new TableColumn("UnitPrice");
            RetailInventory_UnitPrice.setId("moneyCell");
            RetailInventory_UnitPrice.setPrefWidth(gW * 0.06);
            RetailInventory_UnitPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<RetailInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<RetailInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            RetailInventory.getColumns().addAll(RetailInventory_UnitPrice);

            svc.setOnSucceeded((WorkerStateEvent t) -> {
                if (!svc.getValue().equals(svc.getLastValue())) {
                    RetailInventoryItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();

                    for (ArrayList<String> tableViewItemsSum1
                            : tableViewItemsSum) {
                        RetailInventoryItems.add(new RetailInventoryData(
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5),
                                tableViewItemsSum1.get(6),
                                tableViewItemsSum1.get(7)));
                    }

                }
            });
            svc.start();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();

            //Table Container
            HBox hboxRetailTbl = new HBox();
            hboxRetailTbl.getChildren().add(RetailInventory);

            //VBox for the Retail Inventory Title, search panel and table
            VBox vboxRetailInventory = new VBox();
            vboxRetailInventory.setId("vbox3");
            vboxRetailInventory.getChildren().addAll(hboxRetailInventoryTitle,
                    hboxSearchRtlStock, hboxRetailTbl);

            //Title for the Temporary Retail table
            Label tempRetailSaleLbl = new Label("Buyer's Retail Cart");
            tempRetailSaleLbl.setId("label2");
            //its HBox
            HBox hboxTempRetailSale = new HBox();
            hboxTempRetailSale.setId("hbox13");
            hboxTempRetailSale.getChildren().add(tempRetailSaleLbl);

            //Temporary sell table for the Retail items leaving stock
            TableView<TempRetailSaleData> TempRetailSale = new TableView<>();
            ObservableList<TempRetailSaleData> TempRetailSaleItems = FXCollections.observableArrayList();
            TempRetailSale.setEditable(false);
            TempRetailSale.setPrefHeight(gH * 0.5);
            TempRetailSale.setItems(TempRetailSaleItems);

            TempRetailSale.getColumns().clear();

            TableColumn TempRetailSale_Barcode_ID = new TableColumn("Barcode/ID");
            TempRetailSale_Barcode_ID.setId("remainingClms");
            TempRetailSale_Barcode_ID.setPrefWidth(gW * 0.08);
            TempRetailSale_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<TempRetailSaleData, String>(
                            "Barcode_ID"));
            TempRetailSale.getColumns().addAll(TempRetailSale_Barcode_ID);

            TableColumn TempRetailSale_ItemName = new TableColumn("ItemName");
            TempRetailSale_ItemName.setId("remainingClms");
            TempRetailSale_ItemName.setPrefWidth(gW * 0.08);
            TempRetailSale_ItemName.setCellValueFactory(
                    new PropertyValueFactory<TempRetailSaleData, String>(
                            "ItemName"));
            TempRetailSale.getColumns().addAll(TempRetailSale_ItemName);

            TableColumn TempRetailSale_QtySold = new TableColumn("QtySold");
            TempRetailSale_QtySold.setId("qtyCell");
            TempRetailSale_QtySold.setPrefWidth(gW * 0.08);
            TempRetailSale_QtySold.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempRetailSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempRetailSaleData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().QtySold.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempRetailSale.getColumns().addAll(TempRetailSale_QtySold);

            TableColumn TempRetailSale_Amount = new TableColumn("Amount");
            TempRetailSale_Amount.setId("moneyCell");
            TempRetailSale_Amount.setPrefWidth(gW * 0.08);
            TempRetailSale_Amount.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempRetailSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempRetailSaleData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().Amount.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempRetailSale.getColumns().addAll(TempRetailSale_Amount);
            //Container for the temp table
            HBox hboxTempTable = new HBox();
            hboxTempTable.getChildren().add(TempRetailSale);

            //VBox for the temporary sale table
            VBox vboxTempSaleTable = new VBox();
            vboxTempSaleTable.setId("vbox6");
            vboxTempSaleTable.getChildren().addAll(hboxTempRetailSale,
                    hboxTempTable);

            ////checkout and payments panel
            ///Label for the title
            Label selectedItemLbl = new Label("Checkout And Sell");
            //HBox for its containment
            HBox hboxSelectedItemsLbl = new HBox();
            hboxSelectedItemsLbl.setId("hbox13");
            hboxSelectedItemsLbl.getChildren().add(selectedItemLbl);

            ///Label and textField for the barcode/ID that is of the selected item
            Label selectedItemBCodeIDLbl = new Label("Barcode/ID:");
            TextField selectedItemBCodeIDTxtFld = new TextField();
            selectedItemBCodeIDTxtFld.setEditable(false);
            selectedItemBCodeIDTxtFld.setMinWidth(gW * 0.098);
            selectedItemBCodeIDTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxSelectedBCodeID = new HBox();
            hboxSelectedBCodeID.setId("hbox26");
            hboxSelectedBCodeID.getChildren().addAll(selectedItemBCodeIDLbl,
                    selectedItemBCodeIDTxtFld);

            ///Label and TextField for the selected item name
            Label selectedItemNameLbl = new Label("ItemName:");
            TextField selectedItemNameTxtFld = new TextField();
            selectedItemNameTxtFld.setMinWidth(gW * 0.098);
            selectedItemNameTxtFld.setMaxWidth(gW * 0.098);
            selectedItemNameTxtFld.setEditable(false);
            //Hbox for their containment
            HBox hboxSelectedItemName = new HBox();
            hboxSelectedItemName.setId("hbox26");
            hboxSelectedItemName.getChildren().addAll(selectedItemNameLbl,
                    selectedItemNameTxtFld);

            //Label and TextField for the Quantiyt to checkOut
            Label quantityCheckOutLbl = new Label("CheckOut Qty:");
            DecimalTextField quantityCheckOutTxtFld = new DecimalTextField();
            quantityCheckOutTxtFld.setMinWidth(gW * 0.098);
            quantityCheckOutTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxQtyCheckOut = new HBox();
            hboxQtyCheckOut.setId("hbox26");
            hboxQtyCheckOut.getChildren().addAll(quantityCheckOutLbl,
                    quantityCheckOutTxtFld);

            //HBox to carry checkOut button
            Button checkOutBtn = new Button("Cart Item");
            checkOutBtn.getStyleClass().add("btn-info");
            //HBox for containment
            HBox hboxCheckOutBtn = new HBox();
            hboxCheckOutBtn.setId("hbox23");
            hboxCheckOutBtn.getChildren().add(checkOutBtn);

            ///HBox for the lower middle section of the checkout/cart part
            HBox hboxSelectedItem = new HBox();
            hboxSelectedItem.setId("hbox7");
            hboxSelectedItem.getChildren().addAll(hboxSelectedBCodeID,
                    hboxSelectedItemName, hboxQtyCheckOut);

            //VBox for the checkout button and item
            VBox vboxChkOutItemBtn = new VBox();
            vboxChkOutItemBtn.setId("vbox1");
            vboxChkOutItemBtn.getChildren().addAll(hboxSelectedItem,
                    hboxCheckOutBtn);

            ////Lowest central Part for Amount paid and Change
            ///Title for the selling part
            Label paymentTitleLbl = new Label("Payment");
            //Hbox for containment
            HBox hboxPaymnetTitleLbl = new HBox();
            hboxPaymnetTitleLbl.setId("hbox13");
            hboxPaymnetTitleLbl.getChildren().add(paymentTitleLbl);

            //label and TextField for paid amount
            Label totalAmountLbl = new Label("Total Cost:");
            TextField totalAmountTxtFld = new TextField();
            totalAmountTxtFld.setEditable(false);
            totalAmountTxtFld.setId("txtFld1");

            totalAmountTxtFld.setMaxWidth(gW * 0.098);
            totalAmountTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxTotalAmount = new HBox();
            hboxTotalAmount.setId("hbox28");
            hboxTotalAmount.getChildren().addAll(totalAmountLbl,
                    totalAmountTxtFld);

            //label and TextField for paid amount
            Label paidAmountLbl = new Label("Payment:");
            DecimalTextField paidAmountTxtFld = new DecimalTextField();

            paidAmountTxtFld.setMaxWidth(gW * 0.098);
            paidAmountTxtFld.setMinWidth(gW * 0.098);

            //HBox for their containment
            HBox hboxPaidAmount = new HBox();
            hboxPaidAmount.setId("hbox26");
            hboxPaidAmount.getChildren().addAll(paidAmountLbl, paidAmountTxtFld);

            //label and TextField for the change remaining
            Label changeLbl = new Label("Change:");
            TextField changeTxtFld = new TextField();
            changeTxtFld.setId("txtFld1");
            changeTxtFld.setEditable(false);
            changeTxtFld.setMaxWidth(gW * 0.098);
            changeTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxChange = new HBox();
            hboxChange.setId("hbox41");
            hboxChange.getChildren().addAll(changeLbl, changeTxtFld);

            //Sell Commit Transaction Button
            Button commitTransactionBtn = new Button("     Sell     ");
            commitTransactionBtn.getStyleClass().add("btn-info");

            //HBox for the button's containment
            HBox hboxCommitTransactionBtn = new HBox();
            hboxCommitTransactionBtn.setId("hbox23");
            hboxCommitTransactionBtn.getChildren().add(commitTransactionBtn);

            //VBox for the selling
            VBox vboxSellTransaction = new VBox();
            vboxSellTransaction.setId("vbox1");
            vboxSellTransaction.getChildren().addAll(hboxPaidAmount,
                    hboxCommitTransactionBtn);

            //HBox for the whole lowest part
            HBox hboxFullTransaction = new HBox();
            hboxFullTransaction.setId("hbox24");
            hboxFullTransaction.getChildren().addAll(hboxTotalAmount,
                    vboxSellTransaction, hboxChange);

            //VBox for the checkOut and sell
            VBox vboxChkOutAndSell = new VBox();
            vboxChkOutAndSell.setId("vbox3");
            vboxChkOutAndSell.getChildren().addAll(hboxSelectedItemsLbl,
                    vboxChkOutItemBtn, hboxPaymnetTitleLbl, hboxFullTransaction);

            //Final HBox for the Centre part
            HBox hboxBPCenter1 = new HBox();
            hboxBPCenter1.getChildren().addAll(vboxRetailInventory,
                    vboxTempSaleTable);

            ////left lower part configuration
            ///label for the title
            Label cancelDiscountLbl = new Label("Cancel-Discount Sale");
            //HBox for containment
            HBox hboxCancelDiscountLbl = new HBox();
            hboxCancelDiscountLbl.setId("hbox13");
            hboxCancelDiscountLbl.getChildren().add(cancelDiscountLbl);

            ///Cancel ButtonhboxCancelCartItem
            Button cancelCartItemBtn = new Button("Remove Item");
            cancelCartItemBtn.getStyleClass().add("btn-danger");
            //hbox for containment
            HBox hboxCancelCartItem = new HBox();
            hboxCancelCartItem.setId("hbox23");
            hboxCancelCartItem.getChildren().add(cancelCartItemBtn);

            //whole thing coming together
            VBox vboxDiscountAndCancel = new VBox();
            vboxDiscountAndCancel.setId("vbox7");
            vboxDiscountAndCancel.setMinWidth(gW * 0.3165);
            vboxDiscountAndCancel.getChildren().addAll(hboxCancelDiscountLbl,
                    hboxCancelCartItem);

            ///HBxo for the lowest part
            HBox hboxSellAndDiscount = new HBox();
            hboxSellAndDiscount.setId("hbox7");
            hboxSellAndDiscount.getChildren().addAll(vboxChkOutAndSell,
                    vboxDiscountAndCancel);

            //vbox for the centre total config
            VBox vboxBPcenter1 = new VBox();
            vboxBPcenter1.getChildren().addAll(hboxBPCenter1,
                    hboxSellAndDiscount);

            //setting center for the main border pane
            retailSellMajBP.setCenter(vboxBPcenter1);

            //Receipt part
            Label receiptTitleLbl = new Label("Receipt Issued");
            receiptTitleLbl.setId("label1");
            //HBxo for the receipt title
            HBox hboxReceiptTitile = new HBox();
            hboxReceiptTitile.setId("hbox22");
            hboxReceiptTitile.getChildren().add(receiptTitleLbl);

            //////design a good html receipt using the webView and place it in the HBox container
            WebView receiptWebView = new WebView();

            //webView HBox Container
            HBox hboxReceiptWebView = new HBox();
            hboxReceiptWebView.setId("hbox21");
            hboxReceiptWebView.setMinSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.setMaxSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.getChildren().add(receiptWebView);

            RetailInventory.setRowFactory(RI -> {
                TableRow<RetailInventoryData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                row.getItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                row.getItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                    }
                });
                return row;
            });

            RetailInventory.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                    if (RetailInventory.getSelectionModel().getSelectedItem() != null) {

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                RetailInventory.getSelectionModel().getSelectedItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                        quantityCheckOutTxtFld.requestFocus();
                    }

                } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            commitTransactionBtn.setOnAction((ActionEvent e) -> {
                DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                String totalAmt = totalAmountTxtFld.getText();
                String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                        "/=", "");
                String totalAmountStr = totalAmountStr1.replace(",", "");

                if (!paidAmountTxtFld.getDecimal().isEmpty()) {
                    double totalAmountValue = parseDouble(totalAmountStr);
                    String change = df3.format(parseDouble(
                            paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                    if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                        ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                        ArrayList<String> receiptRows;
                        for (int x = 0; x < TempRetailSale.getItems().size();
                                x++) {
                            try {
                                ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect("SELECT a.retialId, "
                                        + "b.barcode_ID, b.itemName, b.description, b.type, a.remainingQty, a.purchasePrice, a.unitPrice "
                                        + "FROM retialinventory AS a LEFT JOIN inventory AS b ON a.inventory_itemId = b.itemId WHERE "
                                        + "b.barcode_ID = '" + TempRetailSale.getItems().get(x).getBarcode_ID() + "' AND a.remainingQty > 0;");
                                int boughtQty = parseInt(
                                        TempRetailSale.getItems().get(x).getQtySold());
                                for (ArrayList<String> recordsID : recordsIDs) {
                                    if (parseInt(recordsID.get(5)) < boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE retialinventory SET remainingQty = 0 WHERE retialId = '" + recordsID.get(
                                                        0) + "'");
                                        boughtQty = boughtQty - parseInt(
                                                recordsID.get(1));
                                    } else if (parseInt(recordsID.get(5)) >= boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE retialinventory SET remainingQty = '" + (parseInt(
                                                        recordsID.get(5)) - boughtQty) + "' WHERE retialId = '" + recordsID.get(
                                                0) + "'");
                                        break;
                                    }
                                }

                                try {
                                    merakiBusinessDBClass.processSQLInsert(
                                            "INSERT INTO sales (barcodeID, itemName, description, type, "
                                            + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                            + "VALUES ('" + TempRetailSale.getItems().get(
                                                    x).getBarcode_ID() + "',"
                                            + "'" + TempRetailSale.getItems().get(
                                                    x).getItemName() + "', "
                                            + "'" + TempRetailSale.getItems().get(
                                                    x).getDescription() + "', "
                                            + "'" + TempRetailSale.getItems().get(
                                                    x).getType() + "', "
                                            + "'" + parseInt(
                                                    TempRetailSale.getItems().get(
                                                            x).getQtySold()) + "', "
                                            + "'" + parseDouble(
                                                    TempRetailSale.getItems().get(
                                                            x).getUnitPrice()) + "', "
                                            + "'" + parseDouble(
                                                    (TempRetailSale.getItems().get(
                                                            x).getAmount().replaceAll(
                                                                    "/=", "")).replaceAll(
                                                                    ",", "")) + "', "
                                            + "'" + 2 + "','" + (parseDouble(
                                                    recordsIDs.get(0).get(6))) + "', '" + parseInt(
                                            this.userIDProperty()) + "')");
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                            svc.restart();
                            svc.restart();
                            svc.restart();
                            svc.restart();

                            receiptRows = new ArrayList<>();
                            receiptRows.add(
                                    TempRetailSale.getItems().get(x).getItemName());
                            receiptRows.add(
                                    TempRetailSale.getItems().get(x).getQtySold());
                            receiptRows.add((parseDouble(
                                    TempRetailSale.getItems().get(x).getAmount()) / parseInt(
                                    TempRetailSale.getItems().get(x).getQtySold())) + "");
                            receiptRows.add(df3.format(parseDouble(
                                    TempRetailSale.getItems().get(x).getAmount())) + "/=");

                            receiptVariables.add(receiptRows);
                        }

                        if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) == 0) {
                            try {
                                changeTxtFld.setText("0.00/=");
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempRetailSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        } else if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) > 0) {
                            try {
                                changeTxtFld.setText(change);
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempRetailSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
            });

            paidAmountTxtFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                    String totalAmt = totalAmountTxtFld.getText();
                    String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                            "/=", "");
                    String totalAmountStr = totalAmountStr1.replace(",", "");

                    if (!paidAmountTxtFld.getText().isEmpty()) {
                        double totalAmountValue = parseDouble(totalAmountStr);
                        String change = df3.format(parseDouble(
                                paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                        if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                            ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                            ArrayList<String> receiptRows;
                            for (int x = 0; x < TempRetailSale.getItems().size();
                                    x++) {
                                try {
                                    ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect("SELECT a.retialId, "
                                            + "b.barcode_ID, b.itemName, b.description, b.type, a.remainingQty, a.purchasePrice, a.unitPrice "
                                            + "FROM retialinventory AS a LEFT JOIN inventory AS b ON a.inventory_itemId = b.itemId WHERE "
                                            + "b.barcode_ID = '" + TempRetailSale.getItems().get(x).getBarcode_ID() + "' AND a.remainingQty > 0;");
                                    int boughtQty = parseInt(
                                            TempRetailSale.getItems().get(x).getQtySold());
                                    for (int i = 0; i < recordsIDs.size(); i++) {
                                        if (parseInt(recordsIDs.get(i).get(5)) < boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE retialinventory SET remainingQty = 0 WHERE retialId = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            boughtQty = boughtQty - parseInt(
                                                    recordsIDs.get(i).get(1));
                                        } else if (parseInt(
                                                recordsIDs.get(i).get(5)) >= boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE retialinventory SET remainingQty = '" + (parseInt(
                                                            recordsIDs.get(i).get(
                                                                    5)) - boughtQty) + "' WHERE retialId = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            break;
                                        }
                                    }

                                    try {
                                        merakiBusinessDBClass.processSQLInsert(
                                                "INSERT INTO sales (barcodeID, itemName, description, type, "
                                                + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                                + "VALUES ('" + TempRetailSale.getItems().get(
                                                        x).getBarcode_ID() + "',"
                                                + "'" + TempRetailSale.getItems().get(
                                                        x).getItemName() + "', "
                                                + "'" + TempRetailSale.getItems().get(
                                                        x).getDescription() + "', "
                                                + "'" + TempRetailSale.getItems().get(
                                                        x).getType() + "', "
                                                + "'" + parseInt(
                                                        TempRetailSale.getItems().get(
                                                                x).getQtySold()) + "', "
                                                + "'" + parseDouble(
                                                        TempRetailSale.getItems().get(
                                                                x).getUnitPrice()) + "', "
                                                + "'" + parseDouble(
                                                        (TempRetailSale.getItems().get(
                                                                x).getAmount().replaceAll(
                                                                        "/=", "")).replaceAll(
                                                                        ",", "")) + "', "
                                                + "'" + 2 + "','" + (parseDouble(
                                                        recordsIDs.get(0).get(6))) + "', '" + parseInt(
                                                this.userIDProperty()) + "')");
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(
                                                Meraki101.class.getName()).log(
                                                Level.SEVERE, null, ex);
                                    }
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                                svc.restart();
                                svc.restart();
                                svc.restart();
                                svc.restart();

                                receiptRows = new ArrayList<>();
                                receiptRows.add(
                                        TempRetailSale.getItems().get(x).getItemName());
                                receiptRows.add(
                                        TempRetailSale.getItems().get(x).getQtySold());
                                receiptRows.add((parseDouble(
                                        TempRetailSale.getItems().get(x).getAmount()) / parseInt(
                                        TempRetailSale.getItems().get(x).getQtySold())) + "");
                                receiptRows.add(df3.format(parseDouble(
                                        TempRetailSale.getItems().get(x).getAmount())) + "/=");

                                receiptVariables.add(receiptRows);
                            }

                            if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) == 0) {
                                try {
                                    changeTxtFld.setText("0.00/=");
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                    TempRetailSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            } else if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) > 0) {
                                try {
                                    changeTxtFld.setText(change);
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                    TempRetailSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            }
                        }

                    }
                }
            });

            checkOutBtn.setOnAction(e -> {
                if (RetailInventory.getSelectionModel().getSelectedItem() != null) {
                    double remainingQty = parseDouble(
                            RetailInventory.getSelectionModel().getSelectedItem().getRemainingQty());
                    double soldQty = parseDouble(
                            quantityCheckOutTxtFld.getDecimal());
                    if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                        String barcodeID = RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                        String itemName = RetailInventory.getSelectionModel().getSelectedItem().getItemName();
                        String description = RetailInventory.getSelectionModel().getSelectedItem().getDescription();
                        String Type = RetailInventory.getSelectionModel().getSelectedItem().getType();
                        String qtySold = quantityCheckOutTxtFld.getDecimal();
                        double unitPrice = parseDouble(
                                RetailInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                        String totalCost = (soldQty * unitPrice) + "";

                        if (!TempRetailSaleItems.isEmpty()) {
                            for (int x = 0; x < TempRetailSaleItems.size(); x++) {
                                if (TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID)) {
                                    String newQuantity = (parseInt(
                                            TempRetailSaleItems.get(x).getQtySold()) + parseInt(
                                            qtySold)) + "";
                                    String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                    TempRetailSaleItems.set(x,
                                            new TempRetailSaleData(barcodeID,
                                                    itemName, description, Type,
                                                    newQuantity, unitPrice + "",
                                                    newAmount));
                                    break;
                                } else if (!TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID) && x < (TempRetailSaleItems.size() - 1)) {
                                    continue;
                                } else {
                                    TempRetailSaleItems.add(
                                            new TempRetailSaleData(barcodeID,
                                                    itemName, description, Type,
                                                    qtySold, unitPrice + "",
                                                    totalCost));
                                    break;
                                }
                            }

                        } else if (TempRetailSaleItems.isEmpty()) {
                            TempRetailSaleItems.add(new TempRetailSaleData(
                                    barcodeID, itemName, description, Type,
                                    qtySold, unitPrice + "", totalCost));

                        }

                        svc.cancel();
                        svc.cancel();

                        RetailInventoryItems.get(
                                RetailInventory.getSelectionModel().getSelectedIndex()).setRemainingQty(
                                (parseInt(
                                        RetailInventory.getSelectionModel().getSelectedItem().getRemainingQty().replaceAll(
                                                ",", "")) - parseInt(qtySold)) + "");

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemNameTxtFld.clear();
                        quantityCheckOutTxtFld.clear();

                        RetailInventory.requestFocus();
                    } else if (remainingQty < soldQty && quantityCheckOutTxtFld.getText() != null) {
                        Label cartQty = new Label();
                        for (int x = 0; x < TempRetailSaleItems.size(); x++) {
                            if (TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                    RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID())) {
                                cartQty.setText(x + "");
                                break;
                            }

                        }
                        Alert itemOutOfStockAlert = new Alert(
                                Alert.AlertType.WARNING,
                                "Maximum Quantity Remaining (" + TempRetailSaleItems.get(
                                        parseInt(cartQty.getText())).getQtySold() + ") Has Been Cart For Item: " + RetailInventory.getSelectionModel().getSelectedItem().getItemName() + " (" + RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID() + ")",
                                ButtonType.OK);
                        itemOutOfStockAlert.showAndWait();
                    }

                }
            });

            quantityCheckOutTxtFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.SHIFT)) {
                    if (RetailInventory.getSelectionModel().getSelectedItem() != null) {
                        double remainingQty = parseDouble(
                                RetailInventory.getSelectionModel().getSelectedItem().getRemainingQty());
                        double soldQty = parseDouble(
                                quantityCheckOutTxtFld.getDecimal());
                        if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                            String barcodeID = RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                            String itemName = RetailInventory.getSelectionModel().getSelectedItem().getItemName();
                            String description = RetailInventory.getSelectionModel().getSelectedItem().getDescription();
                            String Type = RetailInventory.getSelectionModel().getSelectedItem().getType();
                            String qtySold = quantityCheckOutTxtFld.getDecimal();
                            double unitPrice = parseDouble(
                                    RetailInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                            changeTxtFld.clear();

                            String totalCost = (soldQty * unitPrice) + "";
                            if (!TempRetailSaleItems.isEmpty()) {
                                for (int x = 0; x < TempRetailSaleItems.size();
                                        x++) {
                                    if (TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                            barcodeID)) {
                                        String newQuantity = (parseInt(
                                                TempRetailSaleItems.get(x).getQtySold()) + parseInt(
                                                qtySold)) + "";
                                        String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                        TempRetailSaleItems.set(x,
                                                new TempRetailSaleData(barcodeID,
                                                        itemName, description,
                                                        Type, newQuantity,
                                                        unitPrice + "",
                                                        newAmount));
                                        break;
                                    } else if (!TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                            barcodeID) && x < (TempRetailSaleItems.size() - 1)) {
                                        continue;
                                    } else {
                                        TempRetailSaleItems.add(
                                                new TempRetailSaleData(barcodeID,
                                                        itemName, description,
                                                        Type, qtySold,
                                                        unitPrice + "",
                                                        totalCost));
                                        break;
                                    }
                                }

                            } else if (TempRetailSaleItems.isEmpty()) {
                                TempRetailSaleItems.add(new TempRetailSaleData(
                                        barcodeID, itemName, description, Type,
                                        qtySold, unitPrice + "", totalCost));

                            }

                            svc.cancel();
                            svc.cancel();

                            RetailInventoryItems.get(
                                    RetailInventory.getSelectionModel().getSelectedIndex()).setRemainingQty(
                                    (parseInt(
                                            RetailInventory.getSelectionModel().getSelectedItem().getRemainingQty().replaceAll(
                                                    ",", "")) - parseInt(qtySold)) + "");

                            selectedItemBCodeIDTxtFld.clear();
                            selectedItemNameTxtFld.clear();
                            quantityCheckOutTxtFld.clear();

                            RetailInventory.requestFocus();
                        } else if (remainingQty < soldQty && quantityCheckOutTxtFld.getText() != null) {
                            Label cartQty = new Label();
                            for (int x = 0; x < TempRetailSaleItems.size(); x++) {
                                if (TempRetailSaleItems.get(x).getBarcode_ID().equals(
                                        RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID())) {
                                    cartQty.setText(x + "");
                                    break;
                                }

                            }
                            Alert itemOutOfStockAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Maximum Quantity Remaining (" + TempRetailSaleItems.get(
                                            parseInt(cartQty.getText())).getQtySold() + ") Has Been Cart For Item: " + RetailInventory.getSelectionModel().getSelectedItem().getItemName() + " (" + RetailInventory.getSelectionModel().getSelectedItem().getBarcode_ID() + ")",
                                    ButtonType.OK);
                            itemOutOfStockAlert.showAndWait();
                        }

                    }
                } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            TempRetailSaleItems.addListener(
                    (javafx.collections.ListChangeListener.Change<? extends TempRetailSaleData> pChange) -> {
                        while (pChange.next()) {
                            // Do your changes here
                            Double totalSalesValue = 0.00;
                            for (int x = 0; x < TempRetailSale.getItems().size();
                            x++) {
                                String totalSalesVal = TempRetailSale.getItems().get(
                                        x).Amount.get().replace("/=", "");
                                totalSalesValue = totalSalesValue + parseDouble(
                                        totalSalesVal);
                            }

                            DecimalFormat df2 = new DecimalFormat("###,###.00");
                            totalAmountTxtFld.setText(
                                    df2.format(totalSalesValue) + "/=");
                        }
                    });

            searchRtlStockTxtFld.textProperty().addListener(e -> {
                if (searchRtlStockTxtFld.getText() != null) {
                    RetailInventory.setItems(RetailInventoryItems);
                    final FilteredList<RetailInventoryData> filteredList = RetailInventory.getItems().filtered(
                            p -> p.getItemName().contains(
                                    searchRtlStockTxtFld.getText()) || p.getBarcode_ID().contains(
                            searchRtlStockTxtFld.getText()) || p.getDescription().contains(
                            searchRtlStockTxtFld.getText()) || p.getType().contains(
                            searchRtlStockTxtFld.getText()));
                    RetailInventory.setItems(filteredList);

                }
            });

            ///barcode scans
            retailBarcode.textProperty().addListener((ObservableValue<? extends String> ov, String oldV, String newV) -> {

                try {
                    String str = retailBarcode.getText();
                    Thread.sleep(500);
                    if (str.equals(retailBarcode.getText()) && !str.isEmpty()) {
                        final FilteredList<RetailInventoryData> filteredList = RetailInventory.getItems().filtered(
                                p -> p.getBarcode_ID().equals(str));
                        if (!filteredList.isEmpty()) {
                            selectedItemBCodeIDTxtFld.clear();
                            selectedItemBCodeIDTxtFld.setText(
                                    filteredList.get(0).getBarcode_ID());

                            selectedItemNameTxtFld.clear();
                            selectedItemNameTxtFld.setText(filteredList.get(0).getItemName());

                            quantityCheckOutTxtFld.clear();
                        } else {
                            Platform.runLater(() -> {
                                Alert exceptionAlert = new Alert(
                                        Alert.AlertType.INFORMATION,
                                        "Sorry, No Such Item Found!",
                                        ButtonType.OK);
                                exceptionAlert.showAndWait();
                            });
                        }
                        retailBarcode.setText("");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            /////////
            cancelCartItemBtn.setOnAction(e -> {
                if (TempRetailSale.getSelectionModel().getSelectedItem() != null) {
                    TempRetailSale.getItems().remove(
                            TempRetailSale.getSelectionModel().getSelectedIndex());
                    if (TempRetailSaleItems.isEmpty()) {
                        svc.restart();
                        svc.restart();
                        svc.restart();
                        svc.restart();
                    }
                }
            });

            //Lower help panel
            TextArea helpTextArea = new TextArea();
            helpTextArea.setEditable(false);
            helpTextArea.setText("         HELP\n"
                    + "Double-Click item\n"
                    + "to sell.\n\n"
                    + "Double-Click item\n"
                    + "to cancel sale");
            helpTextArea.setMinSize(gW * 0.177, gH * 0.196);
            helpTextArea.setMaxSize(gW * 0.177, gH * 0.196);

            HBox hboxHelpTxtArea = new HBox();
            hboxHelpTxtArea.setId("hbox21");
            hboxHelpTxtArea.getChildren().add(helpTextArea);
            hboxHelpTxtArea.setMinSize(gW * 0.18, gH * 0.2);
            hboxHelpTxtArea.setMaxSize(gW * 0.18, gH * 0.2);

            //final VBox for the receipt Printing Part
            VBox vboxReceiptIssued = new VBox();
            vboxReceiptIssued.setId("vbox5");
            vboxReceiptIssued.getChildren().addAll(hboxReceiptTitile,
                    hboxReceiptWebView, hboxHelpTxtArea);

            retailSellMajBP.setLeft(vboxReceiptIssued);

            //final return method
            return retailSellMajBP;
        }

        public BorderPane wholeSell() throws ClassNotFoundException {
            ///--prerequisites
            receipts receipt = new receipts();
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE (wholeSellPrice > 0.00 AND minWSQty > 0)");

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorWholeSellInventoryTask();
                }
            };
            svc.setPeriod(Duration.seconds(2.5));

            //Main Page Configuration using Main BorderPane
            BorderPane wholeSellMajBP = new BorderPane();

            //Whole Inventory Title
            Label wholeInventoryTitleLbl = new Label("WholeSell Inventory/Stock");
            //HBox to hold the title
            HBox hboxWholeInventoryTitle = new HBox();
            hboxWholeInventoryTitle.setId("hbox4");
            hboxWholeInventoryTitle.getChildren().add(wholeInventoryTitleLbl);

            //the search panel
            UpperCaseTextField searchWhlStockTxtFld = new UpperCaseTextField();
            searchWhlStockTxtFld.setPromptText("Type to search item");
            //search panel HBox container
            HBox hboxSearchWhlStock = new HBox();
            hboxSearchWhlStock.setId("hbox13");
            hboxSearchWhlStock.getChildren().add(searchWhlStockTxtFld);

            //Whole Inventory Table to list items to be sold
            TableView<WholeSaleInventoryData> WholeSaleInventory = new TableView<>();
            ObservableList<WholeSaleInventoryData> WholeSaleInventoryItems = FXCollections.observableArrayList();
            WholeSaleInventory.setEditable(false);
            WholeSaleInventory.setPrefHeight(gH * 0.5);
            WholeSaleInventory.setItems(WholeSaleInventoryItems);

            WholeSaleInventory.getColumns().clear();

            TableColumn WholeSaleInventory_Barcode_ID = new TableColumn(
                    "Barcode/ID");
            WholeSaleInventory_Barcode_ID.setId("remainingClms");
            WholeSaleInventory_Barcode_ID.setPrefWidth(gW * 0.08);
            WholeSaleInventory_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<WholeSaleInventoryData, String>(
                            "Barcode_ID"));
            WholeSaleInventory.getColumns().addAll(WholeSaleInventory_Barcode_ID);

            TableColumn WholeSaleInventory_ItemName = new TableColumn("ItemName");
            WholeSaleInventory_ItemName.setId("remainingClms");
            WholeSaleInventory_ItemName.setPrefWidth(gW * 0.08);
            WholeSaleInventory_ItemName.setCellValueFactory(
                    new PropertyValueFactory<WholeSaleInventoryData, String>(
                            "ItemName"));
            WholeSaleInventory.getColumns().addAll(WholeSaleInventory_ItemName);

            TableColumn WholeSaleInventory_Description = new TableColumn(
                    "Description");
            WholeSaleInventory_Description.setId("remainingClms");
            WholeSaleInventory_Description.setPrefWidth(gW * 0.08);
            WholeSaleInventory_Description.setCellValueFactory(
                    new PropertyValueFactory<WholeSaleInventoryData, String>(
                            "Description"));
            WholeSaleInventory.getColumns().addAll(
                    WholeSaleInventory_Description);

            TableColumn WholeSaleInventory_Type = new TableColumn("Type");
            WholeSaleInventory_Type.setId("remainingClms");
            WholeSaleInventory_Type.setPrefWidth(gW * 0.08);
            WholeSaleInventory_Type.setCellValueFactory(
                    new PropertyValueFactory<WholeSaleInventoryData, String>(
                            "Type"));
            WholeSaleInventory.getColumns().addAll(WholeSaleInventory_Type);

            TableColumn WholeSaleInventory_RemainingQty = new TableColumn(
                    "RemainingQty");
            WholeSaleInventory_RemainingQty.setId("qtyCell");
            WholeSaleInventory_RemainingQty.setPrefWidth(gW * 0.07);
            WholeSaleInventory_RemainingQty.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(
                            p.getValue().RemainingQty.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            WholeSaleInventory.getColumns().addAll(
                    WholeSaleInventory_RemainingQty);

            TableColumn WholeSaleInventory_MinWSQty = new TableColumn(
                    "Min WholeSell Qty");
            WholeSaleInventory_MinWSQty.setId("qtyCell");
            WholeSaleInventory_MinWSQty.setPrefWidth(gW * 0.07);
            WholeSaleInventory_MinWSQty.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().MinWSQty.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            WholeSaleInventory.getColumns().addAll(WholeSaleInventory_MinWSQty);

            TableColumn WholeSaleInventory_UnitPrice = new TableColumn(
                    "UnitPrice");
            WholeSaleInventory_UnitPrice.setId("moneyCell");
            WholeSaleInventory_UnitPrice.setPrefWidth(gW * 0.06);
            WholeSaleInventory_UnitPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<WholeSaleInventoryData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            WholeSaleInventory.getColumns().addAll(WholeSaleInventory_UnitPrice);

            svc.setOnSucceeded((WorkerStateEvent t) -> {
                if (!svc.getValue().equals(svc.getLastValue())) {
                    WholeSaleInventoryItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();
                    for (ArrayList<String> tableViewItemsSum1
                            : tableViewItemsSum) {
                        WholeSaleInventoryItems.add(new WholeSaleInventoryData(
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5),
                                tableViewItemsSum1.get(6)));
                    }

                }
            });
            svc.start();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();

            //Table Container
            HBox hboxPacketTbl = new HBox();
            hboxPacketTbl.getChildren().add(WholeSaleInventory);

            //VBox for the Whole Inventory Title, search panel and table
            VBox vboxPacketInventory = new VBox();
            vboxPacketInventory.setId("vbox3");
            vboxPacketInventory.getChildren().addAll(hboxWholeInventoryTitle,
                    hboxSearchWhlStock, hboxPacketTbl);

            //Title for the Temporary Whole table
            Label tempWholeSaleLbl = new Label("Buyer's WholeSell Cart");
            tempWholeSaleLbl.setId("label2");
            //its HBox
            HBox hboxTempWholeSale = new HBox();
            hboxTempWholeSale.setId("hbox13");
            hboxTempWholeSale.getChildren().add(tempWholeSaleLbl);

            //Temporary sell table for the Whole items leaving stock
            TableView<TempWholeSaleData> TempWholeSale = new TableView<>();
            ObservableList<TempWholeSaleData> TempWholeSaleItems = FXCollections.observableArrayList();
            TempWholeSale.setEditable(false);
            TempWholeSale.setPrefHeight(gH * 0.5);
            TempWholeSale.setItems(TempWholeSaleItems);

            TempWholeSale.getColumns().clear();

            TableColumn TempWholeSale_Barcode_ID = new TableColumn("Barcode/ID");
            TempWholeSale_Barcode_ID.setId("remainingClms");
            TempWholeSale_Barcode_ID.setPrefWidth(gW * 0.08);
            TempWholeSale_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<TempWholeSaleData, String>(
                            "Barcode_ID"));
            TempWholeSale.getColumns().addAll(TempWholeSale_Barcode_ID);

            TableColumn TempWholeSale_ItemName = new TableColumn("ItemName");
            TempWholeSale_ItemName.setId("remainingClms");
            TempWholeSale_ItemName.setPrefWidth(gW * 0.08);
            TempWholeSale_ItemName.setCellValueFactory(
                    new PropertyValueFactory<TempWholeSaleData, String>(
                            "ItemName"));
            TempWholeSale.getColumns().addAll(TempWholeSale_ItemName);

            TableColumn TempWholeSale_QtySold = new TableColumn("QtySold");
            TempWholeSale_QtySold.setId("qtyCell");
            TempWholeSale_QtySold.setPrefWidth(gW * 0.08);
            TempWholeSale_QtySold.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempWholeSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempWholeSaleData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().QtySold.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempWholeSale.getColumns().addAll(TempWholeSale_QtySold);

            TableColumn TempWholeSale_Amount = new TableColumn("Amount");
            TempWholeSale_Amount.setId("moneyCell");
            TempWholeSale_Amount.setPrefWidth(gW * 0.08);
            TempWholeSale_Amount.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<TempWholeSaleData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<TempWholeSaleData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().Amount.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            TempWholeSale.getColumns().addAll(TempWholeSale_Amount);

            //Container for the temp table
            HBox hboxTempTable = new HBox();
            hboxTempTable.getChildren().add(TempWholeSale);

            //VBox for the temporary sale table
            VBox vboxTempSaleTable = new VBox();
            vboxTempSaleTable.setId("vbox6");
            vboxTempSaleTable.getChildren().addAll(hboxTempWholeSale,
                    hboxTempTable);

            ////checkout and payments panel
            ///Label for the title
            Label selectedItemLbl = new Label("Checkout And Sell");
            //HBox for its containment
            HBox hboxSelectedItemsLbl = new HBox();
            hboxSelectedItemsLbl.setId("hbox13");
            hboxSelectedItemsLbl.getChildren().add(selectedItemLbl);

            ///Label and textField for the barcode/ID that is of the selected item
            Label selectedItemBCodeIDLbl = new Label("Barcode/ID:");
            TextField selectedItemBCodeIDTxtFld = new TextField();
            selectedItemBCodeIDTxtFld.setEditable(false);
            selectedItemBCodeIDTxtFld.setMinWidth(gW * 0.098);
            selectedItemBCodeIDTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxSelectedBCodeID = new HBox();
            hboxSelectedBCodeID.setId("hbox26");
            hboxSelectedBCodeID.getChildren().addAll(selectedItemBCodeIDLbl,
                    selectedItemBCodeIDTxtFld);

            ///Label and TextField for the selected item name
            Label selectedItemNameLbl = new Label("ItemName:");
            TextField selectedItemNameTxtFld = new TextField();
            selectedItemNameTxtFld.setMinWidth(gW * 0.098);
            selectedItemNameTxtFld.setMaxWidth(gW * 0.098);
            selectedItemNameTxtFld.setEditable(false);
            //Hbox for their containment
            HBox hboxSelectedItemName = new HBox();
            hboxSelectedItemName.setId("hbox26");
            hboxSelectedItemName.getChildren().addAll(selectedItemNameLbl,
                    selectedItemNameTxtFld);

            //Label and TextField for the Quantiyt to checkOut
            Label quantityCheckOutLbl = new Label("CheckOut Qty:");
            DecimalTextField quantityCheckOutTxtFld = new DecimalTextField();
            quantityCheckOutTxtFld.setMinWidth(gW * 0.098);
            quantityCheckOutTxtFld.setMaxWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxQtyCheckOut = new HBox();
            hboxQtyCheckOut.setId("hbox26");
            hboxQtyCheckOut.getChildren().addAll(quantityCheckOutLbl,
                    quantityCheckOutTxtFld);

            //HBox to carry checkOut button
            Button checkOutBtn = new Button("Cart Item");
            checkOutBtn.getStyleClass().add("btn-info");
            //HBox for containment
            HBox hboxCheckOutBtn = new HBox();
            hboxCheckOutBtn.setId("hbox23");
            hboxCheckOutBtn.getChildren().add(checkOutBtn);

            ///HBox for the lower middle section of the checkout/cart part
            HBox hboxSelectedItem = new HBox();
            hboxSelectedItem.setId("hbox7");
            hboxSelectedItem.getChildren().addAll(hboxSelectedBCodeID,
                    hboxSelectedItemName, hboxQtyCheckOut);

            //VBox for the checkout button and item
            VBox vboxChkOutItemBtn = new VBox();
            vboxChkOutItemBtn.setId("vbox1");
            vboxChkOutItemBtn.getChildren().addAll(hboxSelectedItem,
                    hboxCheckOutBtn);

            ////Lowest central Part for Amount paid and Change
            ///Title for the selling part
            Label paymentTitleLbl = new Label("Payment");
            //Hbox for containment
            HBox hboxPaymnetTitleLbl = new HBox();
            hboxPaymnetTitleLbl.setId("hbox13");
            hboxPaymnetTitleLbl.getChildren().add(paymentTitleLbl);

            //label and TextField for paid amount
            Label totalAmountLbl = new Label("Total Cost:");
            TextField totalAmountTxtFld = new TextField();
            totalAmountTxtFld.setEditable(false);
            totalAmountTxtFld.setId("txtFld1");

            totalAmountTxtFld.setMaxWidth(gW * 0.098);
            totalAmountTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxTotalAmount = new HBox();
            hboxTotalAmount.setId("hbox28");
            hboxTotalAmount.getChildren().addAll(totalAmountLbl,
                    totalAmountTxtFld);

            //label and TextField for paid amount
            Label paidAmountLbl = new Label("Payment:");
            DecimalTextField paidAmountTxtFld = new DecimalTextField();

            paidAmountTxtFld.setMaxWidth(gW * 0.098);
            paidAmountTxtFld.setMinWidth(gW * 0.098);

            //HBox for their containment
            HBox hboxPaidAmount = new HBox();
            hboxPaidAmount.setId("hbox26");
            hboxPaidAmount.getChildren().addAll(paidAmountLbl, paidAmountTxtFld);

            //label and TextField for the change remaining
            Label changeLbl = new Label("Change:");
            TextField changeTxtFld = new TextField();
            changeTxtFld.setId("txtFld1");
            changeTxtFld.setEditable(false);
            changeTxtFld.setMaxWidth(gW * 0.098);
            changeTxtFld.setMinWidth(gW * 0.098);
            //HBox for their containment
            HBox hboxChange = new HBox();
            hboxChange.setId("hbox41");
            hboxChange.getChildren().addAll(changeLbl, changeTxtFld);

            //Sell Commit Transaction Button
            Button commitTransactionBtn = new Button("     Sell     ");
            commitTransactionBtn.getStyleClass().add("btn-info");

            //HBox for the button's containment
            HBox hboxCommitTransactionBtn = new HBox();
            hboxCommitTransactionBtn.setId("hbox23");
            hboxCommitTransactionBtn.getChildren().add(commitTransactionBtn);

            //VBox for the selling
            VBox vboxSellTransaction = new VBox();
            vboxSellTransaction.setId("vbox1");
            vboxSellTransaction.getChildren().addAll(hboxPaidAmount,
                    hboxCommitTransactionBtn);

            //HBox for the whole lowest part
            HBox hboxFullTransaction = new HBox();
            hboxFullTransaction.setId("hbox24");
            hboxFullTransaction.getChildren().addAll(hboxTotalAmount,
                    vboxSellTransaction, hboxChange);

            //VBox for the checkOut and sell
            VBox vboxChkOutAndSell = new VBox();
            vboxChkOutAndSell.setId("vbox3");
            vboxChkOutAndSell.getChildren().addAll(hboxSelectedItemsLbl,
                    vboxChkOutItemBtn, hboxPaymnetTitleLbl, hboxFullTransaction);

            //Final HBox for the Centre part
            HBox hboxBPCenter1 = new HBox();
            hboxBPCenter1.getChildren().addAll(vboxPacketInventory,
                    vboxTempSaleTable);

            ////left lower part configuration
            ///label for the title
            Label cancelDiscountLbl = new Label("Cancel-Discount Sale");
            //HBox for containment
            HBox hboxCancelDiscountLbl = new HBox();
            hboxCancelDiscountLbl.setId("hbox13");
            hboxCancelDiscountLbl.getChildren().add(cancelDiscountLbl);

            ///Cancel ButtonhboxCancelCartItem
            Button cancelCartItemBtn = new Button("Remove Item");
            cancelCartItemBtn.getStyleClass().add("btn-danger");
            //hbox for containment
            HBox hboxCancelCartItem = new HBox();
            hboxCancelCartItem.setId("hbox23");
            hboxCancelCartItem.getChildren().add(cancelCartItemBtn);

            //whole thing coming together
            VBox vboxDiscountAndCancel = new VBox();
            vboxDiscountAndCancel.setId("vbox7");
            vboxDiscountAndCancel.setMinWidth(gW * 0.3165);
            vboxDiscountAndCancel.getChildren().addAll(hboxCancelDiscountLbl,
                    hboxCancelCartItem);

            ///HBxo for the lowest part
            HBox hboxSellAndDiscount = new HBox();
            hboxSellAndDiscount.setId("hbox7");
            hboxSellAndDiscount.getChildren().addAll(vboxChkOutAndSell,
                    vboxDiscountAndCancel);

            //vbox for the centre total config
            VBox vboxBPcenter1 = new VBox();
            vboxBPcenter1.getChildren().addAll(hboxBPCenter1,
                    hboxSellAndDiscount);

            //setting center for the main border pane
            wholeSellMajBP.setCenter(vboxBPcenter1);

            //Receipt part
            Label receiptTitleLbl = new Label("Receipt Issued");
            receiptTitleLbl.setId("label1");
            //HBxo for the receipt title
            HBox hboxReceiptTitile = new HBox();
            hboxReceiptTitile.setId("hbox22");
            hboxReceiptTitile.getChildren().add(receiptTitleLbl);

            //////design a good html receipt using the webView and place it in the HBox container
            WebView receiptWebView = new WebView();
            //the define receipt

            //webView HBox Container
            HBox hboxReceiptWebView = new HBox();
            hboxReceiptWebView.setId("hbox21");
            hboxReceiptWebView.setMinSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.setMaxSize(gW * 0.18, gH * 0.5);
            hboxReceiptWebView.getChildren().add(receiptWebView);

            WholeSaleInventory.setRowFactory(RI -> {
                TableRow<WholeSaleInventoryData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                row.getItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                row.getItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                    }
                });
                return row;
            });

            WholeSaleInventory.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.RIGHT)) {
                    if (WholeSaleInventory.getSelectionModel().getSelectedItem() != null) {

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemBCodeIDTxtFld.setText(
                                WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID());

                        selectedItemNameTxtFld.clear();
                        selectedItemNameTxtFld.setText(
                                WholeSaleInventory.getSelectionModel().getSelectedItem().getItemName());

                        quantityCheckOutTxtFld.clear();
                        quantityCheckOutTxtFld.requestFocus();
                    }

                } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                    paidAmountTxtFld.requestFocus();
                }
            });

            commitTransactionBtn.setOnAction((ActionEvent e) -> {
                DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                String totalAmt = totalAmountTxtFld.getText();
                String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                        "/=", "");
                String totalAmountStr = totalAmountStr1.replace(",", "");

                if (!paidAmountTxtFld.getDecimal().isEmpty()) {
                    double totalAmountValue = parseDouble(totalAmountStr);
                    String change = df3.format(parseDouble(
                            paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                    if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                        ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                        ArrayList<String> receiptRows;

                        for (int x = 0; x < TempWholeSale.getItems().size(); x++) {
                            try {
                                ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect(
                                        "SELECT itemID, remainingQuantity, purchasePrice FROM inventory WHERE (barcode_ID='" + TempWholeSale.getItems().get(
                                                x).getBarcode_ID() + "' AND remainingQuantity > 0)");
                                int boughtQty = parseInt(
                                        TempWholeSale.getItems().get(x).getQtySold());
                                for (int i = 0; i < recordsIDs.size(); i++) {
                                    if (parseInt(recordsIDs.get(i).get(1)) < boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE inventory SET remainingQuantity = 0, fullyConsumedOn = '" + Timestamp.valueOf(
                                                        ((LocalDateTime.now() + "").replaceAll(
                                                                "T", " ")).substring(
                                                                        0, 18)) + "' WHERE itemID = '" + recordsIDs.get(
                                                        i).get(0) + "'");
                                        boughtQty = boughtQty - parseInt(
                                                recordsIDs.get(i).get(1));
                                    } else if (parseInt(recordsIDs.get(i).get(1)) >= boughtQty) {
                                        merakiBusinessDBClass.processSQLUpdate(
                                                "UPDATE inventory SET remainingQuantity = '" + (parseInt(
                                                        recordsIDs.get(i).get(1)) - boughtQty) + "' WHERE itemID = '" + recordsIDs.get(
                                                i).get(0) + "'");
                                        break;
                                    }
                                }
                                try {
                                    merakiBusinessDBClass.processSQLInsert(
                                            "INSERT INTO sales (barcodeID, itemName, description, type, "
                                            + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                            + "VALUES ('" + TempWholeSale.getItems().get(
                                                    x).getBarcode_ID() + "',"
                                            + "'" + TempWholeSale.getItems().get(
                                                    x).getItemName() + "', "
                                            + "'" + TempWholeSale.getItems().get(
                                                    x).getDescription() + "', "
                                            + "'" + TempWholeSale.getItems().get(
                                                    x).getType() + "', "
                                            + "'" + parseInt(
                                                    TempWholeSale.getItems().get(
                                                            x).getQtySold()) + "', "
                                            + "'" + parseDouble(
                                                    TempWholeSale.getItems().get(
                                                            x).getUnitPrice()) + "', "
                                            + "'" + parseDouble(
                                                    (TempWholeSale.getItems().get(
                                                            x).getAmount().replaceAll(
                                                                    "/=", "")).replaceAll(
                                                                    ",", "")) + "', "
                                            + "'" + 3 + "','" + parseDouble(
                                                    recordsIDs.get(0).get(2)) + "', '" + parseInt(
                                            this.userIDProperty()) + "')");
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                            ///this redundancy is necessary
                            svc.restart();
                            svc.restart();
                            svc.restart();
                            svc.restart();

                            receiptRows = new ArrayList<>();
                            receiptRows.add(
                                    TempWholeSale.getItems().get(x).getItemName());
                            receiptRows.add(
                                    TempWholeSale.getItems().get(x).getQtySold());
                            receiptRows.add((parseDouble(
                                    TempWholeSale.getItems().get(x).getAmount()) / parseInt(
                                    TempWholeSale.getItems().get(x).getQtySold())) + "");
                            receiptRows.add(df3.format(parseDouble(
                                    TempWholeSale.getItems().get(x).getAmount())) + "/=");

                            receiptVariables.add(receiptRows);
                        }

                        if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) == 0) {
                            try {
                                changeTxtFld.setText("0.00/=");
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempWholeSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        } else if (parseDouble(
                                (change.replaceAll("/=", "")).replaceAll(",", "")) > 0) {
                            try {
                                changeTxtFld.setText(change);
                                String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                TempWholeSale.getItems().clear();
                                totalAmountTxtFld.setText(totalAmt);
                                paidAmountTxtFld.clear();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Meraki101.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }

                        }
                    }

                }

            });

            paidAmountTxtFld.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    DecimalFormat df3 = new DecimalFormat("###,###,###.00");
                    String totalAmt = totalAmountTxtFld.getText();
                    String totalAmountStr1 = totalAmountTxtFld.getText().replace(
                            "/=", "");
                    String totalAmountStr = totalAmountStr1.replace(",", "");

                    if (!paidAmountTxtFld.getDecimal().isEmpty()) {
                        double totalAmountValue = parseDouble(totalAmountStr);
                        String change = df3.format(parseDouble(
                                paidAmountTxtFld.getDecimal()) - totalAmountValue) + "/=";

                        if ((parseDouble(paidAmountTxtFld.getDecimal()) - totalAmountValue) >= 0) {

                            ArrayList<ArrayList<String>> receiptVariables = new ArrayList<>();
                            ArrayList<String> receiptRows;
                            for (int x = 0; x < TempWholeSale.getItems().size();
                                    x++) {
                                try {
                                    ArrayList<ArrayList<String>> recordsIDs = merakiBusinessDBClass.processSQLGeneralSelect(
                                            "SELECT itemID, remainingQuantity, purchasePrice FROM inventory WHERE (barcode_ID='" + TempWholeSale.getItems().get(
                                                    x).getBarcode_ID() + "' AND remainingQuantity > 0)");
                                    int boughtQty = parseInt(
                                            TempWholeSale.getItems().get(x).getQtySold());
                                    for (int i = 0; i < recordsIDs.size(); i++) {
                                        if (parseInt(recordsIDs.get(i).get(1)) < boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE inventory SET remainingQuantity = 0 WHERE itemID = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            boughtQty = boughtQty - parseInt(
                                                    recordsIDs.get(i).get(1));
                                        } else if (parseInt(
                                                recordsIDs.get(i).get(1)) >= boughtQty) {
                                            merakiBusinessDBClass.processSQLUpdate(
                                                    "UPDATE inventory SET remainingQuantity = '" + (parseInt(
                                                            recordsIDs.get(i).get(
                                                                    1)) - boughtQty) + "' WHERE itemID = '" + recordsIDs.get(
                                                            i).get(0) + "'");
                                            break;
                                        }
                                    }

                                    try {
                                        merakiBusinessDBClass.processSQLInsert(
                                                "INSERT INTO sales (barcodeID, itemName, description, type, "
                                                + "quantity, unitPrice, totalAmount, saleType, purchasePrice, users_userId)"
                                                + "VALUES ('" + TempWholeSale.getItems().get(
                                                        x).getBarcode_ID() + "',"
                                                + "'" + TempWholeSale.getItems().get(
                                                        x).getItemName() + "', "
                                                + "'" + TempWholeSale.getItems().get(
                                                        x).getDescription() + "', "
                                                + "'" + TempWholeSale.getItems().get(
                                                        x).getType() + "', "
                                                + "'" + parseInt(
                                                        TempWholeSale.getItems().get(
                                                                x).getQtySold()) + "', "
                                                + "'" + parseDouble(
                                                        TempWholeSale.getItems().get(
                                                                x).getUnitPrice()) + "', "
                                                + "'" + parseDouble(
                                                        (TempWholeSale.getItems().get(
                                                                x).getAmount().replaceAll(
                                                                        "/=", "")).replaceAll(
                                                                        ",", "")) + "', "
                                                + "'" + 3 + "','" + parseDouble(
                                                        recordsIDs.get(0).get(2)) + "', '" + parseInt(
                                                this.userIDProperty()) + "')");
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(
                                                Meraki101.class.getName()).log(
                                                Level.SEVERE, null, ex);
                                    }
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }
                                ///this redundancy is necessary
                                svc.restart();
                                svc.restart();
                                svc.restart();
                                svc.restart();

                                receiptRows = new ArrayList<>();
                                receiptRows.add(
                                        TempWholeSale.getItems().get(x).getItemName());
                                receiptRows.add(
                                        TempWholeSale.getItems().get(x).getQtySold());
                                receiptRows.add((parseDouble(
                                        TempWholeSale.getItems().get(x).getAmount()) / parseInt(
                                        TempWholeSale.getItems().get(x).getQtySold())) + "");
                                receiptRows.add(df3.format(parseDouble(
                                        TempWholeSale.getItems().get(x).getAmount())) + "/=");

                                receiptVariables.add(receiptRows);
                            }

                            if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) == 0) {
                                try {
                                    changeTxtFld.setText("0.00/=");
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                    TempWholeSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            } else if (parseDouble(
                                    (change.replaceAll("/=", "")).replaceAll(",",
                                            "")) > 0) {
                                try {
                                    changeTxtFld.setText(change);
                                    String receiptHTML = receipt.receiptBuilder(receiptVariables,
                                        totalAmountTxtFld.getText(),
                                        df3.format(parseDouble(
                                                paidAmountTxtFld.getDecimal())) + "/=",
                                        change);
                                receiptWebView.getEngine().loadContent(receiptHTML);
                                PrinterClass.print(receiptHTML);

                                    TempWholeSale.getItems().clear();
                                    totalAmountTxtFld.setText(totalAmt);
                                    paidAmountTxtFld.clear();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Meraki101.class.getName()).log(
                                            Level.SEVERE, null, ex);
                                }

                            }
                        }

                    }

                }
            });

            checkOutBtn.setOnAction(e -> {
                if (parseInt(quantityCheckOutTxtFld.getDecimal()) < parseInt(
                        WholeSaleInventory.getSelectionModel().getSelectedItem().getMinWSQty())) {
                    Alert passwordAlert = new Alert(Alert.AlertType.WARNING,
                            "WholeSell Item Quantity Should At Least Be " + WholeSaleInventory.getSelectionModel().getSelectedItem().getMinWSQty(),
                            ButtonType.OK);
                    passwordAlert.showAndWait();
                } else if (WholeSaleInventory.getSelectionModel().getSelectedItem() != null && !quantityCheckOutTxtFld.getDecimal().isEmpty()) {
                    double remainingQty = parseDouble(
                            WholeSaleInventory.getSelectionModel().getSelectedItem().getRemainingQty());
                    double soldQty = parseDouble(
                            quantityCheckOutTxtFld.getDecimal());
                    if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                        String barcodeID = WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                        String itemName = WholeSaleInventory.getSelectionModel().getSelectedItem().getItemName();
                        String description = WholeSaleInventory.getSelectionModel().getSelectedItem().getDescription();
                        String type = WholeSaleInventory.getSelectionModel().getSelectedItem().getType();
                        String qtySold = quantityCheckOutTxtFld.getDecimal();
                        double unitPrice = parseDouble(
                                WholeSaleInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                        String totalCost = (soldQty * unitPrice) + "";

                        if (!TempWholeSaleItems.isEmpty()) {
                            for (int x = 0; x < TempWholeSaleItems.size(); x++) {
                                if (TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID)) {
                                    String newQuantity = (parseInt(
                                            TempWholeSaleItems.get(x).getQtySold()) + parseInt(
                                            qtySold)) + "";
                                    String newAmount = (parseInt(newQuantity) * unitPrice) + "";
                                    TempWholeSaleItems.set(x,
                                            new TempWholeSaleData(barcodeID,
                                                    itemName, description, type,
                                                    newQuantity, unitPrice + "",
                                                    newAmount));
                                    break;
                                } else if (!TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                        barcodeID) && x < (TempWholeSaleItems.size() - 1)) {
                                    continue;
                                } else {
                                    TempWholeSaleItems.add(
                                            new TempWholeSaleData(barcodeID,
                                                    itemName, description, type,
                                                    qtySold, unitPrice + "",
                                                    totalCost));
                                    break;
                                }
                            }

                        } else if (TempWholeSaleItems.isEmpty()) {
                            TempWholeSaleItems.add(new TempWholeSaleData(
                                    barcodeID, itemName, description, type,
                                    qtySold, unitPrice + "", totalCost));

                        }

                        svc.cancel();
                        svc.cancel();

                        WholeSaleInventoryItems.get(
                                WholeSaleInventory.getSelectionModel().getSelectedIndex()).setRemainingQty(
                                (parseInt(
                                        WholeSaleInventory.getSelectionModel().getSelectedItem().getRemainingQty().replaceAll(
                                                ",", "")) - parseInt(qtySold)) + "");

                        selectedItemBCodeIDTxtFld.clear();
                        selectedItemNameTxtFld.clear();
                        quantityCheckOutTxtFld.clear();

                        WholeSaleInventory.requestFocus();
                    } else if (remainingQty < soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                        Label cartQty = new Label();
                        for (int x = 0; x < TempWholeSaleItems.size(); x++) {
                            if (TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                    WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID())) {
                                cartQty.setText(x + "");
                                break;
                            }

                        }
                        Alert itemOutOfStockAlert = new Alert(
                                Alert.AlertType.WARNING,
                                "Maximum Quantity Remaining (" + TempWholeSaleItems.get(
                                        parseInt(cartQty.getText())).getQtySold() + ") Has Been Cart For Item: " + WholeSaleInventory.getSelectionModel().getSelectedItem().getItemName() + " (" + WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID() + ")",
                                ButtonType.OK);
                        itemOutOfStockAlert.showAndWait();
                    }

                }
            }
            );

            TempWholeSaleItems.addListener(
                    (javafx.collections.ListChangeListener.Change<? extends TempWholeSaleData> pChange) -> {
                        while (pChange.next()) {
                            // Do your changes here
                            Double totalSalesValue = 0.00;
                            for (int x = 0; x < TempWholeSale.getItems().size();
                            x++) {
                                String totalSalesVal = TempWholeSale.getItems().get(
                                        x).Amount.get().replace("/=", "");
                                totalSalesValue = totalSalesValue + parseDouble(
                                        totalSalesVal);
                            }

                            DecimalFormat df2 = new DecimalFormat(
                                    "###,###,###.00");
                            totalAmountTxtFld.setText(
                                    df2.format(totalSalesValue) + "/=");
                        }
                    }
            );

            quantityCheckOutTxtFld.setOnKeyPressed(
                    (KeyEvent ke) -> {
                        if (ke.getCode().equals(KeyCode.SHIFT)) {

                            if (parseInt(quantityCheckOutTxtFld.getDecimal()) < parseInt(
                            WholeSaleInventory.getSelectionModel().getSelectedItem().getMinWSQty())) {
                                Alert passwordAlert = new Alert(
                                        Alert.AlertType.WARNING,
                                        "WholeSell Item Quantity Should At Least Be " + WholeSaleInventory.getSelectionModel().getSelectedItem().getMinWSQty(),
                                        ButtonType.OK);
                                passwordAlert.showAndWait();
                            } else if (WholeSaleInventory.getSelectionModel().getSelectedItem() != null && !quantityCheckOutTxtFld.getDecimal().isEmpty()) {
                                double remainingQty = parseDouble(
                                        WholeSaleInventory.getSelectionModel().getSelectedItem().getRemainingQty());
                                double soldQty = parseDouble(
                                        quantityCheckOutTxtFld.getDecimal());
                                if (remainingQty >= soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                                    String barcodeID = WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID();
                                    String itemName = WholeSaleInventory.getSelectionModel().getSelectedItem().getItemName();
                                    String description = WholeSaleInventory.getSelectionModel().getSelectedItem().getDescription();
                                    String type = WholeSaleInventory.getSelectionModel().getSelectedItem().getType();
                                    String qtySold = quantityCheckOutTxtFld.getDecimal();
                                    double unitPrice = parseDouble(
                                            WholeSaleInventory.getSelectionModel().getSelectedItem().getUnitPrice());

                                    String totalCost = (soldQty * unitPrice) + "";
                                    if (!TempWholeSaleItems.isEmpty()) {
                                        for (int x = 0;
                                        x < TempWholeSaleItems.size(); x++) {
                                            if (TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                                    barcodeID)) {
                                                String newQuantity = (parseInt(
                                                        TempWholeSaleItems.get(x).getQtySold()) + parseInt(
                                                qtySold)) + "";
                                                String newAmount = (parseInt(
                                                        newQuantity) * unitPrice) + "";
                                                TempWholeSaleItems.set(x,
                                                        new TempWholeSaleData(
                                                                barcodeID,
                                                                itemName,
                                                                description,
                                                                type,
                                                                newQuantity,
                                                                unitPrice + "",
                                                                newAmount));
                                                break;
                                            } else if (!TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                                    barcodeID) && x < (TempWholeSaleItems.size() - 1)) {
                                                continue;
                                            } else {
                                                TempWholeSaleItems.add(
                                                        new TempWholeSaleData(
                                                                barcodeID,
                                                                itemName,
                                                                description,
                                                                type, qtySold,
                                                                unitPrice + "",
                                                                totalCost));
                                                break;
                                            }
                                        }

                                    } else if (TempWholeSaleItems.isEmpty()) {
                                        TempWholeSaleItems.add(
                                                new TempWholeSaleData(barcodeID,
                                                        itemName, description,
                                                        type, qtySold,
                                                        unitPrice + "",
                                                        totalCost));

                                    }

                                    svc.cancel();
                                    svc.cancel();

                                    WholeSaleInventoryItems.get(
                                            WholeSaleInventory.getSelectionModel().getSelectedIndex()).setRemainingQty(
                                            (parseInt(
                                                    WholeSaleInventory.getSelectionModel().getSelectedItem().getRemainingQty().replaceAll(
                                                            ",", "")) - parseInt(
                                                    qtySold)) + "");

                                    selectedItemBCodeIDTxtFld.clear();
                                    selectedItemNameTxtFld.clear();
                                    quantityCheckOutTxtFld.clear();

                                    WholeSaleInventory.requestFocus();
                                } else if (remainingQty < soldQty && quantityCheckOutTxtFld.getDecimal() != null) {
                                    Label cartQty = new Label();
                                    for (int x = 0;
                                    x < TempWholeSaleItems.size(); x++) {
                                        if (TempWholeSaleItems.get(x).getBarcode_ID().equals(
                                                WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID())) {
                                            cartQty.setText(x + "");
                                            break;
                                        }

                                    }
                                    Alert itemOutOfStockAlert = new Alert(
                                            Alert.AlertType.WARNING,
                                            "Maximum Quantity Remaining (" + TempWholeSaleItems.get(
                                                    parseInt(cartQty.getText())).getQtySold() + ") Has Been Cart For Item: " + WholeSaleInventory.getSelectionModel().getSelectedItem().getItemName() + " (" + WholeSaleInventory.getSelectionModel().getSelectedItem().getBarcode_ID() + ")",
                                            ButtonType.OK);
                                    itemOutOfStockAlert.showAndWait();
                                }
                            } else if (ke.getCode().equals(KeyCode.CONTROL)) {
                                paidAmountTxtFld.requestFocus();
                            }

                        }
                    }
            );

            TempWholeSale.setOnKeyPressed(
                    (KeyEvent ke) -> {
                        if (ke.getCode().equals(KeyCode.CONTROL)) {
                            paidAmountTxtFld.requestFocus();
                        }
                    }
            );

            cancelCartItemBtn.setOnAction(e
                    -> {
                if (TempWholeSale.getSelectionModel().getSelectedItem() != null) {
                    TempWholeSale.getItems().remove(
                            TempWholeSale.getSelectionModel().getSelectedIndex());
                }

            }
            );

            searchWhlStockTxtFld.textProperty()
                    .addListener(e -> {
                        if (searchWhlStockTxtFld.getText() != null) {
                            WholeSaleInventory.setItems(WholeSaleInventoryItems);
                            final FilteredList<WholeSaleInventoryData> filteredList = WholeSaleInventory.getItems().filtered(
                                    p -> p.getItemName().contains(
                                            searchWhlStockTxtFld.getText()) || p.getBarcode_ID().contains(
                                    searchWhlStockTxtFld.getText()) || p.getDescription().contains(
                                    searchWhlStockTxtFld.getText()) || p.getType().contains(
                                    searchWhlStockTxtFld.getText()));
                            WholeSaleInventory.setItems(filteredList);

                        }
                    }
                    );

            //Lower help panel
            TextArea helpTextArea = new TextArea();

            helpTextArea.setEditable(
                    false);
            helpTextArea.setText(
                    "         HELP\n"
                    + "Double-Click item\n"
                    + "to sell.\n\n"
                    + "Double-Click item\n"
                    + "to cancel sale");
            helpTextArea.setMinSize(gW
                    * 0.177, gH * 0.196);
            helpTextArea.setMaxSize(gW
                    * 0.177, gH * 0.196);

            HBox hboxHelpTxtArea = new HBox();

            hboxHelpTxtArea.setId(
                    "hbox21");
            hboxHelpTxtArea.getChildren()
                    .add(helpTextArea);
            hboxHelpTxtArea.setMinSize(gW
                    * 0.18, gH * 0.2);
            hboxHelpTxtArea.setMaxSize(gW
                    * 0.18, gH * 0.2);

            //final VBox for the receipt Printing Part
            VBox vboxReceiptIssued = new VBox();

            vboxReceiptIssued.setId(
                    "vbox5");
            vboxReceiptIssued.getChildren()
                    .addAll(hboxReceiptTitile, hboxReceiptWebView,
                            hboxHelpTxtArea);

            wholeSellMajBP.setLeft(vboxReceiptIssued);

            //final return method
            return wholeSellMajBP;
        }

    }

    public class userPage {

        String userId;
        String username;

        public userPage(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getUsername() {
            return this.username;
        }

        public BorderPane addUserPage() {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            CryptoUtil CryptoUtil = new CryptoUtil();
            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            //Main add userPage page
            BorderPane addUserMajBP = new BorderPane();

            //Title for the table
            Label userTbltitleLbl = new Label("Current User Accounts");
            userTbltitleLbl.setId("label3");
            //HBox for containment
            HBox hboxUserTblLbl = new HBox();
            hboxUserTblLbl.setMinWidth(gW * 0.951);
            hboxUserTblLbl.setMaxWidth(gW * 0.951);
            hboxUserTblLbl.setId("hbox13");
            hboxUserTblLbl.getChildren().add(userTbltitleLbl);

            //User Accounts table
            TableView<UserAccountsData> UserAccounts = new TableView<>();
            ObservableList<UserAccountsData> UserAccountsItems = FXCollections.observableArrayList();
            UserAccounts.setEditable(false);
            UserAccounts.setPrefHeight(gH * 0.5);
            UserAccounts.setItems(UserAccountsItems);

            UserAccounts.getColumns().clear();

            TableColumn UserAccounts_FirstName = new TableColumn("FirstName");
            UserAccounts_FirstName.setPrefWidth(gW * 0.15);
            UserAccounts_FirstName.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "FirstName"));
            UserAccounts.getColumns().addAll(UserAccounts_FirstName);

            TableColumn UserAccounts_OtherNames = new TableColumn("OtherNames");
            UserAccounts_OtherNames.setPrefWidth(gW * 0.2);
            UserAccounts_OtherNames.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "OtherNames"));
            UserAccounts.getColumns().addAll(UserAccounts_OtherNames);

            TableColumn UserAccounts_UserName = new TableColumn("UserName");
            UserAccounts_UserName.setPrefWidth(gW * 0.1);
            UserAccounts_UserName.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "UserName"));
            UserAccounts.getColumns().addAll(UserAccounts_UserName);

            TableColumn UserAccounts_E_mail = new TableColumn("E-mail");
            UserAccounts_E_mail.setPrefWidth(gW * 0.2);
            UserAccounts_E_mail.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>("E_mail"));
            UserAccounts.getColumns().addAll(UserAccounts_E_mail);

            TableColumn UserAccounts_Contact = new TableColumn("Contact");
            UserAccounts_Contact.setPrefWidth(gW * 0.1);
            UserAccounts_Contact.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>("Contact"));
            UserAccounts.getColumns().addAll(UserAccounts_Contact);

            TableColumn UserAccounts_CreatedBy = new TableColumn("CreatedBy");
            UserAccounts_CreatedBy.setPrefWidth(gW * 0.1);
            UserAccounts_CreatedBy.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "CreatedBy"));
            UserAccounts.getColumns().addAll(UserAccounts_CreatedBy);

            TableColumn UserAccounts_CreatedOn = new TableColumn("CreatedOn");
            UserAccounts_CreatedOn.setPrefWidth(gW * 0.1);
            UserAccounts_CreatedOn.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "CreatedOn"));
            UserAccounts.getColumns().addAll(UserAccounts_CreatedOn);

            try {
                UserAccountsItems.clear();
                ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                        "SELECT * FROM users WHERE status > 0");
                for (ArrayList<String> usersArrayList1 : usersArrayList) {
                    UserAccountsItems.add(new UserAccountsData(
                            usersArrayList1.get(0), usersArrayList1.get(1),
                            usersArrayList1.get(2), usersArrayList1.get(3),
                            usersArrayList1.get(5), usersArrayList1.get(6),
                            usersArrayList1.get(9), usersArrayList1.get(10),
                            usersArrayList1.get(13)));
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            //HBox for Table containment
            HBox hboxUserAccountTbl = new HBox();
            hboxUserAccountTbl.getChildren().add(UserAccounts);

            //the lower add user part
            Label addUserAccTitleLbl = new Label("Setup User Account");
            //HBox forcontainment
            HBox hboxAddUserAccTitle = new HBox();
            hboxAddUserAccTitle.setMinWidth(gW * 0.951);
            hboxAddUserAccTitle.setMaxWidth(gW * 0.951);
            hboxAddUserAccTitle.setId("hbox4");
            hboxAddUserAccTitle.getChildren().add(addUserAccTitleLbl);

            //the lowest user account controls
            //first name label and TextField
            Label firstNameLbl = new Label("First Name:");
            UpperCaseTextField firstNameTxtFld = new UpperCaseTextField();

            //HBox for containment
            HBox hboxFirstName = new HBox();
            hboxFirstName.setId("hbox26");
            hboxFirstName.getChildren().addAll(firstNameLbl, firstNameTxtFld);

            //Other names label and TextField
            Label otherNamesLbl = new Label("Other Names:");
            UpperCaseTextField otherNamesTxtFld = new UpperCaseTextField();
            otherNamesTxtFld.setMinWidth(gW * 0.178);
            otherNamesTxtFld.setMaxWidth(gW * 0.178);
            //HBox for their containment
            HBox hboxOtherNames = new HBox();
            hboxOtherNames.setId("hbox26");
            hboxOtherNames.getChildren().addAll(otherNamesLbl, otherNamesTxtFld);

            //Official designation label and TextField
            ObservableList<String> options = FXCollections.observableArrayList(
                    "ADMINISTRATOR",
                    "SALE'S PERSON"
            );

            Label workDesignationLbl = new Label("Designation:");
            ComboBox workDesignationComboBx = new ComboBox(options);
            workDesignationComboBx.setMinWidth(gW * 0.13);
            workDesignationComboBx.setMaxWidth(gW * 0.13);
            workDesignationComboBx.setPromptText("Choose Account Rights");
            workDesignationComboBx.setEditable(false);
            //HBox for their containment
            HBox hboxWorkDesignation = new HBox();
            hboxWorkDesignation.setId("hbox26");
            hboxWorkDesignation.getChildren().addAll(workDesignationLbl,
                    workDesignationComboBx);

            //Email label and TextField
            Label emailLbl = new Label("E-mail:");
            LowerCaseTextField emailTextField = new LowerCaseTextField();
            emailTextField.setMinWidth(gW * 0.2);
            emailTextField.setMaxWidth(gW * 0.2);
            //HBox for containment
            HBox hboxEmail = new HBox();
            hboxEmail.setId("hbox26");
            hboxEmail.getChildren().addAll(emailLbl, emailTextField);

            //Phone number/contact Label and textField
            Label phoneNumberLbl = new Label("Contact:");
            DecimalTextField phoneNumberTxtFld = new DecimalTextField();
            //HBox for their containment
            HBox hboxPhoneNumber = new HBox();
            hboxPhoneNumber.setId("hbox26");
            hboxPhoneNumber.getChildren().addAll(phoneNumberLbl,
                    phoneNumberTxtFld);

            ///--HBox for the basic User configuration control containment
            HBox hboxUserConfigControls = new HBox();
            hboxUserConfigControls.setId("hbox25");
            hboxUserConfigControls.getChildren().addAll(hboxFirstName,
                    hboxOtherNames, hboxWorkDesignation, hboxEmail,
                    phoneNumberLbl, hboxPhoneNumber);

            //username and password Label and textField
            Label usernameLbl = new Label("Username:");
            LowerCaseTextField usernameTxtFld = new LowerCaseTextField();
            //HBox for their containment
            HBox hboxUsername = new HBox();
            hboxUsername.setId("hbox26");
            hboxUsername.getChildren().addAll(usernameLbl, usernameTxtFld);

            Label passwordLbl = new Label("Password:");
            PasswordField passwordFld = new PasswordField();
            //HBox for their containment
            HBox hboxPassword = new HBox();
            hboxPassword.setId("hbox26");
            hboxPassword.getChildren().addAll(passwordLbl, passwordFld);

            HBox hboxUsernamePwd = new HBox();
            hboxUsernamePwd.setId("hbox47");
            hboxUsernamePwd.getChildren().addAll(hboxUsername, hboxPassword);

            ///The create user button
            Button createUserBtn = new Button("Create User");
            createUserBtn.getStyleClass().add("btn-success");

            createUserBtn.setOnAction(e -> {
                if (passwordFld.getText().length() < 8) {
                    Alert passwordAlert = new Alert(Alert.AlertType.WARNING,
                            "Password Should Be At Least 8 Characters!",
                            ButtonType.OK);
                    passwordAlert.showAndWait();
                } else if (passwordFld.getText().matches("[a-z]*[A-Z]*")) {
                    Alert passwordAlert = new Alert(Alert.AlertType.WARNING,
                            "Password Should Contain At Least One Letter!",
                            ButtonType.OK);
                    passwordAlert.showAndWait();
                } else if (passwordFld.getText().matches("[0-9]*")) {
                    Alert passwordAlert = new Alert(Alert.AlertType.WARNING,
                            "Password Should Contain At Least One Number!",
                            ButtonType.OK);
                    passwordAlert.showAndWait();
                } else if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                        "ADMINISTRATOR")) {
                    try {
                        merakiBusinessDBClass.processSQLInsert(
                                "INSERT INTO users (firstName, otherNames,"
                                + "username, password, email, contact, isAdmin, createdBy, businessDetails_detailId, isCreator)"
                                + "VALUES ('" + firstNameTxtFld.getText() + "', '" + otherNamesTxtFld.getText() + "', "
                                + "'" + usernameTxtFld.getText() + "', '" + CryptoUtil.encrypt(
                                passwordFld.getText(), "merakiveron") + "',"
                                + "'" + emailTextField.getText() + "', '" + phoneNumberTxtFld.getText() + "', "
                                + "'1', '" + this.userId + "', 1, 1)");

                        UserAccounts.getItems().clear();
                        ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT * FROM users");

                        for (ArrayList<String> usersArrayList1 : usersArrayList) {
                            UserAccountsItems.add(new UserAccountsData(
                                    usersArrayList1.get(0), usersArrayList1.get(
                                    1), usersArrayList1.get(2),
                                    usersArrayList1.get(3), usersArrayList1.get(
                                    5), usersArrayList1.get(6),
                                    usersArrayList1.get(9), usersArrayList1.get(
                                    10), usersArrayList1.get(13)));
                        }
                        firstNameTxtFld.clear();
                        otherNamesTxtFld.clear();
                        emailTextField.clear();
                        phoneNumberTxtFld.clear();
                        usernameTxtFld.clear();
                        passwordFld.clear();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                } else if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                        "SALE'S PERSON")) {
                    try {
                        merakiBusinessDBClass.processSQLInsert(
                                "INSERT INTO users (firstName, otherNames,"
                                + "username, password, email, contact, createdBy, businessDetails_detailId)"
                                + "VALUES ('" + firstNameTxtFld.getText() + "', '" + otherNamesTxtFld.getText() + "', "
                                + "'" + usernameTxtFld.getText() + "', '" + CryptoUtil.encrypt(
                                passwordFld.getText(), "merakiveron") + "',"
                                + "'" + emailTextField.getText() + "', '" + phoneNumberTxtFld.getText() + "', "
                                + "'" + this.userId + "', 1)");

                        UserAccounts.getItems().clear();
                        ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT * FROM users WHERE status > 0");

                        for (ArrayList<String> usersArrayList1 : usersArrayList) {
                            UserAccountsItems.add(new UserAccountsData(
                                    usersArrayList1.get(0), usersArrayList1.get(
                                    1), usersArrayList1.get(2),
                                    usersArrayList1.get(3), usersArrayList1.get(
                                    5), usersArrayList1.get(6),
                                    usersArrayList1.get(9), usersArrayList1.get(
                                    10), usersArrayList1.get(13)));
                        }

                        firstNameTxtFld.clear();
                        otherNamesTxtFld.clear();
                        emailTextField.clear();
                        phoneNumberTxtFld.clear();
                        usernameTxtFld.clear();
                        passwordFld.clear();

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }

            });

            //HBox for containment
            HBox hboxCreateUserBtn = new HBox();
            hboxCreateUserBtn.setId("hbox23");
            hboxCreateUserBtn.getChildren().add(createUserBtn);
            hboxCreateUserBtn.setMinWidth(gW * 0.951);
            hboxCreateUserBtn.setMaxWidth(gW * 0.951);

            //VBox for center containment
            VBox vboxCenterBP1 = new VBox();
            vboxCenterBP1.setId("vbox8");
            vboxCenterBP1.getChildren().addAll(hboxUserTblLbl,
                    hboxUserAccountTbl, hboxAddUserAccTitle,
                    hboxUserConfigControls, hboxUsernamePwd, hboxCreateUserBtn);

            //Main BP center configuration
            addUserMajBP.setCenter(vboxCenterBP1);

            //final return method
            return addUserMajBP;
        }

        public BorderPane editUserPage() {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            Label userIdLbl = new Label();

            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
            CryptoUtil CryptoUtil = new CryptoUtil();

            //Main edit userPage page
            BorderPane editUserMajBP = new BorderPane();

            //Title for the table
            Label userTbltitleLbl = new Label("Edit User Accounts");
            userTbltitleLbl.setId("label3");
            //HBox for containment
            HBox hboxUserTblLbl = new HBox();
            hboxUserTblLbl.setMinWidth(gW * 0.951);
            hboxUserTblLbl.setMaxWidth(gW * 0.951);
            hboxUserTblLbl.setId("hbox13");
            hboxUserTblLbl.getChildren().add(userTbltitleLbl);

            //Delete Account Button
            Button deleteAccountBtn = new Button("Delete User");
            deleteAccountBtn.getStyleClass().add("btn-danger");
            //HBox for containment
            HBox hboxDeleteUserAccount = new HBox();
            hboxDeleteUserAccount.setMinWidth(gW * 0.951);
            hboxDeleteUserAccount.setMaxWidth(gW * 0.951);
            hboxDeleteUserAccount.setId("hbox29");
            hboxDeleteUserAccount.getChildren().add(deleteAccountBtn);

            //User Accounts table
            TableView<UserAccountsData> UserAccounts = new TableView<>();
            ObservableList<UserAccountsData> UserAccountsItems = FXCollections.observableArrayList();
            UserAccounts.setEditable(false);
            UserAccounts.setPrefHeight(gH * 0.5);
            UserAccounts.setItems(UserAccountsItems);

            UserAccounts.getColumns().clear();

            TableColumn UserAccounts_FirstName = new TableColumn("FirstName");
            UserAccounts_FirstName.setPrefWidth(gW * 0.15);
            UserAccounts_FirstName.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "FirstName"));
            UserAccounts.getColumns().addAll(UserAccounts_FirstName);

            TableColumn UserAccounts_OtherNames = new TableColumn("OtherNames");
            UserAccounts_OtherNames.setPrefWidth(gW * 0.2);
            UserAccounts_OtherNames.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "OtherNames"));
            UserAccounts.getColumns().addAll(UserAccounts_OtherNames);

            TableColumn UserAccounts_UserName = new TableColumn("UserName");
            UserAccounts_UserName.setPrefWidth(gW * 0.1);
            UserAccounts_UserName.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "UserName"));
            UserAccounts.getColumns().addAll(UserAccounts_UserName);

            TableColumn UserAccounts_E_mail = new TableColumn("E-mail");
            UserAccounts_E_mail.setPrefWidth(gW * 0.2);
            UserAccounts_E_mail.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>("E_mail"));
            UserAccounts.getColumns().addAll(UserAccounts_E_mail);

            TableColumn UserAccounts_Contact = new TableColumn("Contact");
            UserAccounts_Contact.setPrefWidth(gW * 0.1);
            UserAccounts_Contact.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>("Contact"));
            UserAccounts.getColumns().addAll(UserAccounts_Contact);

            TableColumn UserAccounts_CreatedBy = new TableColumn("CreatedBy");
            UserAccounts_CreatedBy.setPrefWidth(gW * 0.1);
            UserAccounts_CreatedBy.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "CreatedBy"));
            UserAccounts.getColumns().addAll(UserAccounts_CreatedBy);

            TableColumn UserAccounts_CreatedOn = new TableColumn("CreatedOn");
            UserAccounts_CreatedOn.setPrefWidth(gW * 0.1);
            UserAccounts_CreatedOn.setCellValueFactory(
                    new PropertyValueFactory<UserAccountsData, String>(
                            "CreatedOn"));
            UserAccounts.getColumns().addAll(UserAccounts_CreatedOn);

            try {
                UserAccountsItems.clear();
                ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                        "SELECT * FROM users WHERE status > 0");
                for (ArrayList<String> usersArrayList1 : usersArrayList) {
                    UserAccountsItems.add(new UserAccountsData(
                            usersArrayList1.get(0), usersArrayList1.get(1),
                            usersArrayList1.get(2), usersArrayList1.get(3),
                            usersArrayList1.get(5), usersArrayList1.get(6),
                            usersArrayList1.get(9), usersArrayList1.get(10),
                            usersArrayList1.get(13)));
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Meraki101.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            //HBox for Table containment
            HBox hboxUserAccountTbl = new HBox();
            hboxUserAccountTbl.getChildren().add(UserAccounts);

            //the lower aedit user part
            Label addUserAccTitleLbl = new Label("Update User Account");
            //HBox forcontainment
            HBox hboxAddUserAccTitle = new HBox();
            hboxAddUserAccTitle.setMinWidth(gW * 0.951);
            hboxAddUserAccTitle.setMaxWidth(gW * 0.951);
            hboxAddUserAccTitle.setId("hbox4");
            hboxAddUserAccTitle.getChildren().add(addUserAccTitleLbl);

            //the lowest user account controls
            //first name label and TextField
            Label firstNameLbl = new Label("First Name:");
            UpperCaseTextField firstNameTxtFld = new UpperCaseTextField();

            //HBox for containment
            HBox hboxFirstName = new HBox();
            hboxFirstName.setId("hbox26");
            hboxFirstName.getChildren().addAll(firstNameLbl, firstNameTxtFld);

            //Other names label and TextField
            Label otherNamesLbl = new Label("Other Names:");
            UpperCaseTextField otherNamesTxtFld = new UpperCaseTextField();
            otherNamesTxtFld.setMinWidth(gW * 0.178);
            otherNamesTxtFld.setMaxWidth(gW * 0.178);
            //HBox for their containment
            HBox hboxOtherNames = new HBox();
            hboxOtherNames.setId("hbox26");
            hboxOtherNames.getChildren().addAll(otherNamesLbl, otherNamesTxtFld);

            //Official designation label and TextField
            ObservableList<String> options = FXCollections.observableArrayList(
                    "ADMINISTRATOR",
                    "SALE'S PERSON"
            );

            Label workDesignationLbl = new Label("Designation:");
            ComboBox workDesignationComboBx = new ComboBox(options);
            workDesignationComboBx.setMinWidth(gW * 0.13);
            workDesignationComboBx.setMaxWidth(gW * 0.13);
            workDesignationComboBx.setPromptText("Choose Account Rights");
            workDesignationComboBx.setEditable(false);
            //HBox for their containment
            HBox hboxWorkDesignation = new HBox();
            hboxWorkDesignation.setId("hbox26");
            hboxWorkDesignation.getChildren().addAll(workDesignationLbl,
                    workDesignationComboBx);

            //Email label and TextField
            Label emailLbl = new Label("E-mail:");
            LowerCaseTextField emailTextField = new LowerCaseTextField();
            emailTextField.setMinWidth(gW * 0.2);
            emailTextField.setMaxWidth(gW * 0.2);
            //HBox for containment
            HBox hboxEmail = new HBox();
            hboxEmail.setId("hbox26");
            hboxEmail.getChildren().addAll(emailLbl, emailTextField);

            //Phone number/contact Label and textField
            Label phoneNumberLbl = new Label("Contact:");
            DecimalTextField phoneNumberTxtFld = new DecimalTextField();
            //HBox for their containment
            HBox hboxPhoneNumber = new HBox();
            hboxPhoneNumber.setId("hbox26");
            hboxPhoneNumber.getChildren().addAll(phoneNumberLbl,
                    phoneNumberTxtFld);

            ///--HBox for the basic User configuration control containment
            HBox hboxUserConfigControls = new HBox();
            hboxUserConfigControls.setId("hbox25");
            hboxUserConfigControls.getChildren().addAll(hboxFirstName,
                    hboxOtherNames, hboxWorkDesignation, hboxEmail,
                    phoneNumberLbl, hboxPhoneNumber);

            //username and password Label and textField
            Label usernameLbl = new Label("Username:");
            LowerCaseTextField usernameTxtFld = new LowerCaseTextField();
            //HBox for their containment
            HBox hboxUsername = new HBox();
            hboxUsername.setId("hbox26");
            hboxUsername.getChildren().addAll(usernameLbl, usernameTxtFld);

            Label passwordLbl = new Label("Password:");
            PasswordField passwordFld = new PasswordField();
            //HBox for their containment
            HBox hboxPassword = new HBox();
            hboxPassword.setId("hbox26");
            hboxPassword.getChildren().addAll(passwordLbl, passwordFld);

            HBox hboxUsernamePwd = new HBox();
            hboxUsernamePwd.setId("hbox47");
            hboxUsernamePwd.getChildren().addAll(hboxUsername, hboxPassword);

            ///The create user button
            Button editUserBtn = new Button("Update User");
            editUserBtn.getStyleClass().add("btn-info");
            Button deleteUserBtn = new Button("Delete User");
            deleteUserBtn.getStyleClass().add("btn-danger");

            editUserBtn.setOnAction(e -> {
                if (passwordFld.getText().isEmpty()) {

                    if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                            "ADMINISTRATOR")) {
                        try {
                            merakiBusinessDBClass.processSQLInsert(
                                    "UPDATE users SET firstName = '" + firstNameTxtFld.getText() + "', otherNames = '" + otherNamesTxtFld.getText() + "',"
                                    + "username = '" + usernameTxtFld.getText() + "', email = '" + emailTextField.getText() + "',"
                                    + "contact = '" + phoneNumberTxtFld.getText() + "', isAdmin = '1', createdBy = '" + this.userId + "', businessDetails_detailId = 1 WHERE userId = '" + parseInt(
                                    userIdLbl.getText()) + "'");

                            UserAccounts.getItems().clear();
                            ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                    "SELECT * FROM users");

                            for (ArrayList<String> usersArrayList1
                                    : usersArrayList) {
                                UserAccountsItems.add(new UserAccountsData(
                                        usersArrayList1.get(0),
                                        usersArrayList1.get(1),
                                        usersArrayList1.get(2),
                                        usersArrayList1.get(3),
                                        usersArrayList1.get(5),
                                        usersArrayList1.get(6),
                                        usersArrayList1.get(9),
                                        usersArrayList1.get(10),
                                        usersArrayList1.get(13)));
                            }

                            firstNameTxtFld.clear();
                            otherNamesTxtFld.clear();
                            emailTextField.clear();
                            phoneNumberTxtFld.clear();
                            usernameTxtFld.clear();
                            passwordFld.clear();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Meraki101.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    } else if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                            "SALE'S PERSON")) {
                        try {
                            merakiBusinessDBClass.processSQLInsert(
                                    "UPDATE users SET firstName = '" + firstNameTxtFld.getText() + "', otherNames = '" + otherNamesTxtFld.getText() + "',"
                                    + "username = '" + usernameTxtFld.getText() + "', email = '" + emailTextField.getText() + "',"
                                    + "contact = '" + phoneNumberTxtFld.getText() + "', createdBy = '" + this.userId + "', businessDetails_detailId = 1 WHERE userId = '" + parseInt(
                                    userIdLbl.getText()) + "'");

                            UserAccounts.getItems().clear();
                            ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                    "SELECT * FROM users WERE status > 0");

                            for (ArrayList<String> usersArrayList1
                                    : usersArrayList) {
                                UserAccountsItems.add(new UserAccountsData(
                                        usersArrayList1.get(0),
                                        usersArrayList1.get(1),
                                        usersArrayList1.get(2),
                                        usersArrayList1.get(3),
                                        usersArrayList1.get(5),
                                        usersArrayList1.get(6),
                                        usersArrayList1.get(9),
                                        usersArrayList1.get(10),
                                        usersArrayList1.get(13)));
                            }

                            firstNameTxtFld.clear();
                            otherNamesTxtFld.clear();
                            emailTextField.clear();
                            phoneNumberTxtFld.clear();
                            usernameTxtFld.clear();
                            passwordFld.clear();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Meraki101.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    }

                } else if (!passwordFld.getText().isEmpty()) {
                    if (passwordFld.getText().length() < 8) {
                        Alert creatorAlert = new Alert(Alert.AlertType.WARNING,
                                "Password Insufficient Length!", ButtonType.OK);
                        creatorAlert.showAndWait();
                    } else if (passwordFld.getText().matches("[a-z]*[A-Z]*")) {
                        Alert creatorAlert = new Alert(Alert.AlertType.WARNING,
                                "Password Must Contain At Least One Letter!",
                                ButtonType.OK);
                        creatorAlert.showAndWait();
                    } else if (passwordFld.getText().matches("[0-9]*")) {
                        Alert creatorAlert = new Alert(Alert.AlertType.WARNING,
                                "Password MustCcontain At Least One Number!",
                                ButtonType.OK);
                        creatorAlert.showAndWait();
                    } else if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                            "ADMINISTRATOR")) {
                        try {
                            merakiBusinessDBClass.processSQLInsert(
                                    "UPDATE users SET firstName = '" + firstNameTxtFld.getText() + "', otherNames = '" + otherNamesTxtFld.getText() + "',"
                                    + "username = '" + usernameTxtFld.getText() + "', password = '" + CryptoUtil.encrypt(
                                    passwordFld.getText(), "merakiveron") + "', email = '" + emailTextField.getText() + "',"
                                    + "contact = '" + phoneNumberTxtFld.getText() + "', isAdmin = '1', createdBy = '" + this.userId + "', businessDetails_detailId = 1 WHERE userId = '" + parseInt(
                                    userIdLbl.getText()) + "'");

                            UserAccounts.getItems().clear();
                            ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                    "SELECT * FROM users");

                            for (ArrayList<String> usersArrayList1
                                    : usersArrayList) {
                                UserAccountsItems.add(new UserAccountsData(
                                        usersArrayList1.get(0),
                                        usersArrayList1.get(1),
                                        usersArrayList1.get(2),
                                        usersArrayList1.get(3),
                                        usersArrayList1.get(5),
                                        usersArrayList1.get(6),
                                        usersArrayList1.get(9),
                                        usersArrayList1.get(10),
                                        usersArrayList1.get(13)));
                            }
                            firstNameTxtFld.clear();
                            otherNamesTxtFld.clear();
                            emailTextField.clear();
                            phoneNumberTxtFld.clear();
                            usernameTxtFld.clear();
                            passwordFld.clear();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Meraki101.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    } else if (workDesignationComboBx.getSelectionModel().getSelectedItem().toString().equals(
                            "SALE'S PERSON")) {
                        try {
                            merakiBusinessDBClass.processSQLInsert(
                                    "UPDATE users SET firstName = '" + firstNameTxtFld.getText() + "', otherNames = '" + otherNamesTxtFld.getText() + "',"
                                    + "username = '" + usernameTxtFld.getText() + "', password = '" + CryptoUtil.encrypt(
                                    passwordFld.getText(), "merakiveron") + "', email = '" + emailTextField.getText() + "',"
                                    + "contact = '" + phoneNumberTxtFld.getText() + "', createdBy = '" + this.userId + "', businessDetails_detailId = 1 WHERE userId = '" + parseInt(
                                    userIdLbl.getText()) + "'");

                            UserAccounts.getItems().clear();
                            ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                    "SELECT * FROM users WHERE status > 0");

                            for (ArrayList<String> usersArrayList1
                                    : usersArrayList) {
                                UserAccountsItems.add(new UserAccountsData(
                                        usersArrayList1.get(0),
                                        usersArrayList1.get(1),
                                        usersArrayList1.get(2),
                                        usersArrayList1.get(3),
                                        usersArrayList1.get(5),
                                        usersArrayList1.get(6),
                                        usersArrayList1.get(9),
                                        usersArrayList1.get(10),
                                        usersArrayList1.get(13)));
                            }

                            firstNameTxtFld.clear();
                            otherNamesTxtFld.clear();
                            emailTextField.clear();
                            phoneNumberTxtFld.clear();
                            usernameTxtFld.clear();
                            passwordFld.clear();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Meraki101.class.getName()).log(
                                    Level.SEVERE, null, ex);
                        }
                    }
                }

            });

            UserAccounts.setRowFactory(PI -> {
                TableRow<UserAccountsData> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        if (parseInt(
                                UserAccounts.getSelectionModel().getSelectedItem().getIsCreator()) != 3) {
                            userIdLbl.setText(row.getItem().getUserId());

                            firstNameTxtFld.clear();
                            firstNameTxtFld.setText(row.getItem().getFirstName());

                            otherNamesTxtFld.clear();
                            otherNamesTxtFld.setText(
                                    row.getItem().getOtherNames());

                            emailTextField.clear();
                            emailTextField.setText(row.getItem().getE_mail());

                            phoneNumberTxtFld.clear();
                            phoneNumberTxtFld.setText(row.getItem().getContact());

                            usernameTxtFld.clear();
                            usernameTxtFld.setText(row.getItem().getUserName());
                        } else {
                            Alert creatorAlert = new Alert(
                                    Alert.AlertType.WARNING,
                                    "Sorry, This Account Cannot Be Edited!",
                                    ButtonType.OK);
                            creatorAlert.showAndWait();
                        }
                    }
                });
                return row;
            });

            deleteUserBtn.setOnAction(e -> {
                if (UserAccounts.getSelectionModel().getSelectedItem() != null && parseInt(
                        UserAccounts.getSelectionModel().getSelectedItem().getIsCreator()) < 2) {
                    try {
                        merakiBusinessDBClass.processSQLUpdate(
                                "UPDATE users SET status = 0, invalidatedOn = '" + ((LocalDateTime.now() + "").replaceAll(
                                        "T", " ")) + "' WHERE userId = '" + parseInt(
                                        UserAccounts.getSelectionModel().getSelectedItem().getUserId()) + "'");
                        UserAccounts.getItems().clear();
                        ArrayList<ArrayList<String>> usersArrayList = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT * FROM users WHERE status > 0");

                        for (ArrayList<String> usersArrayList1 : usersArrayList) {
                            UserAccountsItems.add(new UserAccountsData(
                                    usersArrayList1.get(0), usersArrayList1.get(
                                    1), usersArrayList1.get(2),
                                    usersArrayList1.get(3), usersArrayList1.get(
                                    5), usersArrayList1.get(6),
                                    usersArrayList1.get(9), usersArrayList1.get(
                                    10), usersArrayList1.get(13)));
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Meraki101.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                } else {
                    Alert creatorAlert = new Alert(Alert.AlertType.WARNING,
                            "Sorry, This Main Admin Account Cannot Be Deleted!",
                            ButtonType.OK);
                    creatorAlert.showAndWait();
                }
            });

            //HBox for containment
            HBox hboxEditUserBtn = new HBox();
            hboxEditUserBtn.setId("hbox23");
            hboxEditUserBtn.getChildren().addAll(deleteUserBtn, editUserBtn);
            hboxEditUserBtn.setMinWidth(gW * 0.951);
            hboxEditUserBtn.setMaxWidth(gW * 0.951);

            //VBox for center containment
            VBox vboxCenterBP1 = new VBox();
            vboxCenterBP1.setId("vbox8");
            vboxCenterBP1.getChildren().addAll(hboxUserTblLbl,
                    hboxUserAccountTbl, hboxAddUserAccTitle,
                    hboxUserConfigControls, hboxUsernamePwd, hboxEditUserBtn);

            //Main BP center configuration
            editUserMajBP.setCenter(vboxCenterBP1);

            //final return method
            return editUserMajBP;
        }
    }

    public class salesPage {

        public BorderPane viewEditSales() throws ClassNotFoundException {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();

            //Main Sales Page BorderPane
            BorderPane salesViewEditMajBP = new BorderPane();

            ArrayList<ArrayList<String>> salesMemory = merakiBusinessDBClass.processSQLGeneralSelect(
                    "SELECT * FROM sales");

            ScheduledService<ArrayList<ArrayList<String>>> svc = new ScheduledService<ArrayList<ArrayList<String>>>() {
                protected Task<ArrayList<ArrayList<String>>> createTask() {
                    return new monitorSalesTask();
                }
            };
            svc.setPeriod(Duration.seconds(2.5));

            //Title label for the sales
            Label salesPageTitleLbl = new Label("Item Sales");
            salesPageTitleLbl.setId("label3");
            //HBox for containmnet
            HBox hboxSalesPageTitle = new HBox();
            hboxSalesPageTitle.setMinWidth(gW * 0.951);
            hboxSalesPageTitle.setMaxWidth(gW * 0.951);
            hboxSalesPageTitle.setId("hbox13");
            hboxSalesPageTitle.getChildren().add(salesPageTitleLbl);

            //Sales Table
            TableView<ItemSalesData> ItemSales = new TableView<>();
            ObservableList<ItemSalesData> ItemSalesItems = FXCollections.observableArrayList();
            ItemSales.setEditable(false);
            ItemSales.setPrefHeight(gH * 0.51);
            ItemSales.setItems(ItemSalesItems);

            ItemSales.getColumns().clear();

            TableColumn ItemSales_Barcode_ID = new TableColumn("Barcode/ID");
            ItemSales_Barcode_ID.setId("remainingClms");
            ItemSales_Barcode_ID.setPrefWidth(gW * 0.1);
            ItemSales_Barcode_ID.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("Barcode_ID"));
            ItemSales.getColumns().addAll(ItemSales_Barcode_ID);

            TableColumn ItemSales_ItemName = new TableColumn("ItemName");
            ItemSales_ItemName.setId("remainingClms");
            ItemSales_ItemName.setPrefWidth(gW * 0.12);
            ItemSales_ItemName.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("ItemName"));
            ItemSales.getColumns().addAll(ItemSales_ItemName);

            TableColumn ItemSales_Description = new TableColumn("Description");
            ItemSales_Description.setId("remainingClms");
            ItemSales_Description.setPrefWidth(gW * 0.13);
            ItemSales_Description.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>(
                            "Description"));
            ItemSales.getColumns().addAll(ItemSales_Description);

            TableColumn ItemSales_Type = new TableColumn("Type");
            ItemSales_Type.setId("remainingClms");
            ItemSales_Type.setPrefWidth(gW * 0.1);
            ItemSales_Type.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("Type"));
            ItemSales.getColumns().addAll(ItemSales_Type);

            TableColumn ItemSales_SaleType = new TableColumn("Sale Type");
            ItemSales_SaleType.setId("remainingClms");
            ItemSales_SaleType.setPrefWidth(gW * 0.1);
            ItemSales_SaleType.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("SaleType"));
            ItemSales.getColumns().addAll(ItemSales_SaleType);

            TableColumn ItemSales_Quantity = new TableColumn("Quantity");
            ItemSales_Quantity.setId("qtyCell");
            ItemSales_Quantity.setPrefWidth(gW * 0.08);
            ItemSales_Quantity.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<ItemSalesData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<ItemSalesData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###");
                    double money = parseDouble(p.getValue().Quantity.getValue());
                    String finalMoney = df.format(money);
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            ItemSales.getColumns().addAll(ItemSales_Quantity);

            TableColumn ItemSales_UnitPrice = new TableColumn("UnitPrice");
            ItemSales_UnitPrice.setId("moneyCell");
            ItemSales_UnitPrice.setPrefWidth(gW * 0.08);
            ItemSales_UnitPrice.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<ItemSalesData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<ItemSalesData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().UnitPrice.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            ItemSales.getColumns().addAll(ItemSales_UnitPrice);

            TableColumn ItemSales_TotalValue = new TableColumn("TotalValue");
            ItemSales_TotalValue.setId("moneyCell");
            ItemSales_TotalValue.setPrefWidth(gW * 0.08);
            ItemSales_TotalValue.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<ItemSalesData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<ItemSalesData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(
                            p.getValue().TotalValue.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            ItemSales.getColumns().addAll(ItemSales_TotalValue);

            TableColumn ItemSales_Profit = new TableColumn("Profit");
            ItemSales_Profit.setId("moneyCell");
            ItemSales_Profit.setPrefWidth(gW * 0.08);
            ItemSales_Profit.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<ItemSalesData, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(
                        final TableColumn.CellDataFeatures<ItemSalesData, Boolean> p) {
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    double money = parseDouble(p.getValue().Profit.getValue());
                    String finalMoney = df.format(money) + "/=";
                    return new SimpleObjectProperty(finalMoney);
                }

            });
            ItemSales.getColumns().addAll(ItemSales_Profit);

            TableColumn ItemSales_SoldBy = new TableColumn("SoldBy");
            ItemSales_SoldBy.setId("remainingClms");
            ItemSales_SoldBy.setPrefWidth(gW * 0.08);
            ItemSales_SoldBy.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("SoldBy"));
            ItemSales.getColumns().addAll(ItemSales_SoldBy);

            TableColumn ItemSales_SoldOn = new TableColumn("SoldOn");
            ItemSales_SoldOn.setId("remainingClms");
            ItemSales_SoldOn.setPrefWidth(gW * 0.1);
            ItemSales_SoldOn.setCellValueFactory(
                    new PropertyValueFactory<ItemSalesData, String>("SoldOn"));
            ItemSales.getColumns().addAll(ItemSales_SoldOn);

            for (ArrayList<String> selectInventoryItem : salesMemory) {
                switch (selectInventoryItem.get(9)) {
                    case "1": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "PACKET SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                    case "2": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "RETAIL SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                    case "3": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "WHOLESELL SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                }

            }

//            ItemSales.setRowFactory(new Callback<TableView<ItemSalesData>, TableRow<ItemSalesData>>() {
//                @Override
//                public TableRow<ItemSalesData> call(TableView<ItemSalesData> tableView) {
//                    final TableRow<ItemSalesData> row = new TableRow<ItemSalesData>() {
//                        if(row.)
//                    };
//                    return row;
//                }
//            });
            ///binding table to service data
            svc.setOnSucceeded((WorkerStateEvent t) -> {

                if (!svc.getValue().equals(svc.getLastValue())) {
                    ItemSalesItems.clear();
                    ArrayList<ArrayList<String>> tableViewItemsSum = svc.getValue();
                    for (ArrayList<String> tableViewItemsSum1
                            : tableViewItemsSum) {
                        ItemSalesItems.add(new ItemSalesData(
                                tableViewItemsSum1.get(0),
                                tableViewItemsSum1.get(1),
                                tableViewItemsSum1.get(2),
                                tableViewItemsSum1.get(3),
                                tableViewItemsSum1.get(4),
                                tableViewItemsSum1.get(5),
                                tableViewItemsSum1.get(6),
                                tableViewItemsSum1.get(7),
                                tableViewItemsSum1.get(8),
                                tableViewItemsSum1.get(9),
                                tableViewItemsSum1.get(10)));
                    }

                }
            });
            svc.start();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();
            svc.restart();

            //this line is to allow the filters do their work
            ObservableList dataList = ItemSales.getItems();

            //HBox for the table containment
            HBox hboxTableView = new HBox();
            hboxTableView.getChildren().add(ItemSales);

            //Controls for configuring the sales
            Label configureSalesTitleLbl = new Label("Configure Sales Table");
            //configureSalesTitleLbl.setId("label3");
            //HBox for containment
            HBox hboxConfigTitle = new HBox();
            hboxConfigTitle.setMinWidth(gW * 0.951);
            hboxConfigTitle.setMaxWidth(gW * 0.951);
            hboxConfigTitle.setId("hbox4");
            hboxConfigTitle.getChildren().add(configureSalesTitleLbl);

            //Totals for sales and for profits per selection
            Label totalSalesLbl = new Label("Total Sales:");
            TextField totalSalesTxtFld = new TextField();
            totalSalesTxtFld.setId("moneyTextFields");
            totalSalesTxtFld.setEditable(false);

            //HBox for containment
            HBox hboxTotalSales = new HBox();
            hboxTotalSales.setId("hbox32");
            hboxTotalSales.getChildren().addAll(totalSalesLbl, totalSalesTxtFld);

            Label totalProfitLbl = new Label("Total Profit:");
            TextField totalProfitTxtFld = new TextField();
            totalProfitTxtFld.setId("moneyTextFields");
            totalProfitTxtFld.setEditable(false);

            //HBox for containment
            HBox hboxTotalProfit = new HBox();
            hboxTotalProfit.setId("hbox32");
            hboxTotalProfit.getChildren().addAll(totalProfitLbl,
                    totalProfitTxtFld);

            //Listen for changes in table view and computer total
            ItemSalesItems.addListener(
                    (javafx.collections.ListChangeListener.Change<? extends ItemSalesData> pChange) -> {
                        while (pChange.next()) {
                            // Do your changes here
                            Double totalSalesValue = 0.00;
                            Double totalProfitValue = 0.00;
                            for (int x = 0; x < ItemSales.getItems().size(); x++) {
                                String totalSalesVal = ItemSales.getItems().get(
                                        x).TotalValue.get().replace("/=", "");
                                String totalProfitVal = ItemSales.getItems().get(
                                        x).Profit.get().replace("/=", "");
                                totalSalesValue = totalSalesValue + parseDouble(
                                        totalSalesVal);
                                totalProfitValue = totalProfitValue + parseDouble(
                                        totalProfitVal);
                            }

                            DecimalFormat df2 = new DecimalFormat(
                                    "###,###,###.00");
                            totalSalesTxtFld.setText(
                                    df2.format(totalSalesValue) + "/=");
                            totalProfitTxtFld.setText(df2.format(
                                    totalProfitValue) + "/=");
                        }
                    });

            //HBox for the two totals values
            HBox hboxTotals = new HBox();
            hboxTotals.setId("hbox31");
            hboxTotals.setMinWidth(gW * 0.9055);
            hboxTotals.setMaxWidth(gW * 0.9055);
            hboxTotals.getChildren().addAll(hboxTotalSales, hboxTotalProfit);

            //Edit sale button
            Button editSaleBtn = new Button("Edit Sale");
            editSaleBtn.setMinWidth(gW * 0.06);
            editSaleBtn.getStyleClass().add("btn-info");

            //HBox for containment
            HBox hboxEditSaleBtn = new HBox();
            hboxEditSaleBtn.getChildren().add(editSaleBtn);

            //HBox for total and edit button
            HBox hboxEditBtnTotal = new HBox();
            hboxEditBtnTotal.setMinWidth(gW * 0.951);
            hboxEditBtnTotal.setMaxWidth(gW * 0.951);
            hboxEditBtnTotal.getChildren().addAll(hboxEditSaleBtn, hboxTotals);

            //ComboBox for selecting type of sales to view
            Label salesSelectLbl = new Label("Sales Type:");
            ObservableList<String> options = FXCollections.observableArrayList(
                    "ALL SALES",
                    "PACKET SALES",
                    "RETAIL SALES",
                    "WHOLESELL SALES"
            );

            ComboBox salesSelectComboBx = new ComboBox(options);
            salesSelectComboBx.getSelectionModel().selectFirst();
            salesSelectComboBx.setMinWidth(gW * 0.15);
            salesSelectComboBx.setMaxWidth(gW * 0.15);
            salesSelectComboBx.setPromptText("Select Sales Type");
            salesSelectComboBx.setEditable(false);
            //HBox for containment
            HBox hboxSalesSelect = new HBox();
            hboxSalesSelect.setId("hbox26");
            hboxSalesSelect.getChildren().addAll(salesSelectLbl,
                    salesSelectComboBx);

            //Initial and Final dates
            //Labels for the Dates
            Label initialDate1Lbl = new Label("Starting Date:");
            Label finalDate1Lbl = new Label("End Date:");

            //Date Comparison for the graph controls
            DatePicker initialDatePk1 = new DatePicker();
            DatePicker finalDatePk1 = new DatePicker();

            //DatePicker containers
            HBox hboxInitialDate1 = new HBox();
            hboxInitialDate1.setId("hbox26");
            HBox hboxFinalDate1 = new HBox();
            hboxFinalDate1.setId("hbox26");

            //placing all of them in their containers
            hboxInitialDate1.getChildren().addAll(initialDate1Lbl,
                    initialDatePk1);
            hboxFinalDate1.getChildren().addAll(finalDate1Lbl, finalDatePk1);

            //Labels
            Label minimumSellingP1Lbl = new Label("Minimum Selling Price:");
            Label mixamumSellingP1Lbl = new Label("Maximum Selling Price:");

            //TextFields for holding the values
            DecimalTextField minimumSellingP1TxtFld = new DecimalTextField();
            DecimalTextField maximumSellingP1TxtFld = new DecimalTextField();

            //HBoxes for the selling prices containers
            HBox hboxMinimumSellingP1 = new HBox();
            hboxMinimumSellingP1.setId("hbox26");
            HBox hboxMaximumSellingP1 = new HBox();
            hboxMaximumSellingP1.setId("hbox26");

            ///Placing the things in their containers
            hboxMinimumSellingP1.getChildren().addAll(minimumSellingP1Lbl,
                    minimumSellingP1TxtFld);
            hboxMaximumSellingP1.getChildren().addAll(mixamumSellingP1Lbl,
                    maximumSellingP1TxtFld);

            ///Configuration by Product
            Label productLbl = new Label("Product:");
            UpperCaseTextField productTxtField = new UpperCaseTextField();
            productTxtField.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                        if (oldValue != null && (newValue.length() < oldValue.length())) {
                            ItemSales.setItems(dataList);
                        }

                        String value = newValue.toUpperCase();
                        ObservableList<ItemSalesData> subentries = FXCollections.observableArrayList();

                        for (int i = 0; i < ItemSales.getItems().size(); i++) {
                            String entry = "" + ItemSales.getColumns().get(1).getCellData(
                                    i);
                            if (entry.toUpperCase().startsWith(value)) {
                                subentries.add(ItemSales.getItems().get(i));
                                break;

                            }
                        }
                        ItemSales.setItems(subentries);

                    });

            //HBox for containment
            HBox hboxProduct = new HBox();
            hboxProduct.setId("hbox26");
            hboxProduct.getChildren().addAll(productLbl, productTxtField);

            ///Configuration by User
            Label userSalesLbl = new Label("Employee:");
            LowerCaseTextField userSalesTxtField = new LowerCaseTextField();
            //HBox for containment
            HBox hboxUserSales = new HBox();
            hboxUserSales.setId("hbox26");
            hboxUserSales.getChildren().addAll(userSalesLbl, userSalesTxtField);

            //HBox to contain all configurations
            HBox hboxTableConfigs1 = new HBox();
            hboxTableConfigs1.setId("hbox33");
            hboxTableConfigs1.getChildren().addAll(hboxSalesSelect,
                    hboxInitialDate1, hboxFinalDate1, hboxMinimumSellingP1,
                    hboxMaximumSellingP1, hboxProduct, hboxUserSales);

            HBox hboxTableConfigs2 = new HBox();
            hboxTableConfigs2.setId("hbox30");
            hboxTableConfigs2.getChildren().addAll(hboxProduct, hboxUserSales);

            //VBox config title and Controls
            VBox vboxConfigLblControls = new VBox();
            vboxConfigLblControls.setId("vbox2");
            vboxConfigLblControls.getChildren().addAll(hboxConfigTitle,
                    hboxTableConfigs1, hboxTableConfigs2);

            //Configurations for editing a sale
            Label editSaleTitleLbl = new Label("Edit Sale");
            //Hbox for containment
            HBox hboxEditSaleTitle = new HBox();
            hboxEditSaleTitle.setId("hbox4");
            hboxEditSaleTitle.setMinWidth(gW * 0.951);
            hboxEditSaleTitle.setMaxWidth(gW * 0.951);
            hboxEditSaleTitle.getChildren().add(editSaleTitleLbl);

            //Edit Sale controls
            //Label and TextField for Barcode
            Label barcodeIDLbl = new Label("Barcode/ID:");
            TextField barcodeIDTxtFld = new TextField();
            //HBox ofr their containment
            HBox hboxBarcodeID = new HBox();
            hboxBarcodeID.setId("hbox26");
            hboxBarcodeID.getChildren().addAll(barcodeIDLbl, barcodeIDTxtFld);

            //Label and TextField for Item Name
            Label itemNameLbl = new Label("Item Name:");
            UpperCaseTextField itemNameTxtFld = new UpperCaseTextField();
            //HBox for their containment
            HBox hboxItemName = new HBox();
            hboxItemName.setId("hbox34");
            hboxItemName.getChildren().addAll(itemNameLbl, itemNameTxtFld);

            //Label and Combobox for item description
            Label itemDescriptionLbl = new Label("Description:");
            ComboBox descriptionComboBx = new ComboBox();
            descriptionComboBx.setMinWidth(gW * 0.15);
            descriptionComboBx.setMaxWidth(gW * 0.15);
            descriptionComboBx.setEditable(false);
            descriptionComboBx.setPromptText("Select Description");
            //HBox ofr thei containment
            HBox hboxDescription = new HBox();
            hboxDescription.setId("hbox35");
            hboxDescription.getChildren().addAll(itemDescriptionLbl,
                    descriptionComboBx);

            //Label and Combobox for item Type
            Label itemTypeLbl = new Label("Type:");
            ComboBox itemTypeComboBx = new ComboBox();
            itemTypeComboBx.setMinWidth(gW * 0.15);
            itemTypeComboBx.setMaxWidth(gW * 0.15);
            itemTypeComboBx.setEditable(false);
            itemTypeComboBx.setPromptText("Select Type");
            //HBox ofr thei containment
            HBox hboxItemType = new HBox();
            hboxItemType.setId("hbox36");
            hboxItemType.getChildren().addAll(itemTypeLbl, itemTypeComboBx);

            //Label and TextField for Item Quantity
            Label itemQtyLbl = new Label("Quantity:");
            DecimalTextField itemQtyTxtFld = new DecimalTextField();
            //HBox for their containment
            HBox hboxItemQty = new HBox();
            hboxItemQty.setId("hbox37");
            hboxItemQty.getChildren().addAll(itemQtyLbl, itemQtyTxtFld);

            ////when the edit button is pressed.... its down here not up because the textFields and other nodes to be filled by this command are down here
            editSaleBtn.setOnAction(e -> {
                if (ItemSales.getSelectionModel().getSelectedItem() != null) {
                    barcodeIDTxtFld.clear();
                    barcodeIDTxtFld.setText(ItemSales.getItems().get(
                            ItemSales.getSelectionModel().getSelectedIndex()).getBarcode_ID());

                    itemNameTxtFld.clear();
                    itemNameTxtFld.setText(ItemSales.getItems().get(
                            ItemSales.getSelectionModel().getSelectedIndex()).getItemName());

                    ObservableList<String> data = FXCollections.observableArrayList(
                            ItemSales.getItems().get(
                                    ItemSales.getSelectionModel().getSelectedIndex()).getDescription());
                    descriptionComboBx.setItems(data);

                    ObservableList<String> data1 = FXCollections.observableArrayList(
                            ItemSales.getItems().get(
                                    ItemSales.getSelectionModel().getSelectedIndex()).getType());
                    itemTypeComboBx.setItems(data1);

                    itemQtyTxtFld.clear();
                    itemQtyTxtFld.setText(ItemSales.getItems().get(
                            ItemSales.getSelectionModel().getSelectedIndex()).getQuantity());
                }
            });

            //HBox for the Edit Controls
            HBox hboxEditSaleControls = new HBox();
            hboxEditSaleControls.getChildren().addAll(hboxBarcodeID,
                    hboxItemName, hboxDescription, hboxItemType, hboxItemQty);

            //Update Sale Button
            Button updateSaleBtn = new Button("  Update   ");
            updateSaleBtn.getStyleClass().add("btn-info");
            //HBox for containment
            HBox hboxUpdateSaleBtn = new HBox();
            hboxUpdateSaleBtn.setId("hbox23");
            hboxUpdateSaleBtn.setMinWidth(gW * 0.951);
            hboxUpdateSaleBtn.setMaxWidth(gW * 0.951);
            hboxUpdateSaleBtn.getChildren().add(updateSaleBtn);

            ///logic 
            initialDatePk1.setOnAction(e -> {
                DateTimeStringConverter sdf = new DateTimeStringConverter(
                        Locale.US, "yyyy-MM-dd");
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                        p -> !sdf.fromString(p.getSoldOn().substring(0, 10)).before(
                                sdf.fromString(
                                        initialDatePk1.getValue().toString())));
                ItemSales.setItems(filteredList);
                totalSalesTxtFld.clear();
                totalProfitTxtFld.clear();
                // Do your changes here
                Double totalSalesValue = 0.00;
                Double totalProfitValue = 0.00;
                for (int x = 0; x < ItemSales.getItems().size(); x++) {
                    String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                            "/=", "");
                    String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                            "/=", "");
                    totalSalesValue = totalSalesValue + parseDouble(
                            totalSalesVal);
                    totalProfitValue = totalProfitValue + parseDouble(
                            totalProfitVal);
                }

                DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                totalSalesTxtFld.setText(df2.format(totalSalesValue) + "/=");
                totalProfitTxtFld.setText(df2.format(totalProfitValue) + "/=");
            });

            finalDatePk1.setOnAction(e -> {
                DateTimeStringConverter sdf = new DateTimeStringConverter(
                        Locale.US, "yyyy-MM-dd");
                if (initialDatePk1.getValue() == null) {
                    final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                            p -> !sdf.fromString(p.getSoldOn().substring(0, 10)).after(
                                    sdf.fromString(
                                            finalDatePk1.getValue().toString())));
                    ItemSales.setItems(filteredList);
                    totalSalesTxtFld.clear();
                    totalProfitTxtFld.clear();
                    // Do your changes here
                    Double totalSalesValue = 0.00;
                    Double totalProfitValue = 0.00;
                    for (int x = 0; x < ItemSales.getItems().size(); x++) {
                        String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                "/=", "");
                        String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                "/=", "");
                        totalSalesValue = totalSalesValue + parseDouble(
                                totalSalesVal);
                        totalProfitValue = totalProfitValue + parseDouble(
                                totalProfitVal);
                    }

                    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                    totalSalesTxtFld.setText(df2.format(totalSalesValue) + "/=");
                    totalProfitTxtFld.setText(
                            df2.format(totalProfitValue) + "/=");
                } else if (initialDatePk1.getValue() != null) {
                    final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                            p -> !sdf.fromString(p.getSoldOn().substring(0, 10)).after(
                                    sdf.fromString(
                                            finalDatePk1.getValue().toString())) && !sdf.fromString(
                            p.getSoldOn().substring(0, 10)).before(
                            sdf.fromString(initialDatePk1.getValue().toString())));
                    ItemSales.setItems(filteredList);
                    totalSalesTxtFld.clear();
                    totalProfitTxtFld.clear();
                    // Do your changes here
                    Double totalSalesValue = 0.00;
                    Double totalProfitValue = 0.00;
                    for (int x = 0; x < ItemSales.getItems().size(); x++) {
                        String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                "/=", "");
                        String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                "/=", "");
                        totalSalesValue = totalSalesValue + parseDouble(
                                totalSalesVal);
                        totalProfitValue = totalProfitValue + parseDouble(
                                totalProfitVal);
                    }

                    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                    totalSalesTxtFld.setText(df2.format(totalSalesValue) + "/=");
                    totalProfitTxtFld.setText(
                            df2.format(totalProfitValue) + "/=");
                }

            });

            salesSelectComboBx.valueProperty().addListener(
                    new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String t, String t1) {
                    DateTimeStringConverter sdf = new DateTimeStringConverter(
                            Locale.US, "yyyy-MM-dd");
                    if (salesSelectComboBx.getSelectionModel().isSelected(0)) {
                        initialDatePk1.getEditor().clear();
                        finalDatePk1.getEditor().clear();
                        minimumSellingP1TxtFld.clear();
                        maximumSellingP1TxtFld.clear();
                        productTxtField.clear();
                        userSalesTxtField.clear();
                        ItemSales.setItems(ItemSalesItems);
                        totalSalesTxtFld.clear();
                        totalProfitTxtFld.clear();
                        // Do your changes here
                        Double totalSalesValue = 0.00;
                        Double totalProfitValue = 0.00;
                        for (int x = 0; x < ItemSales.getItems().size(); x++) {
                            String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                    "/=", "");
                            String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                    "/=", "");
                            totalSalesValue = totalSalesValue + parseDouble(
                                    totalSalesVal);
                            totalProfitValue = totalProfitValue + parseDouble(
                                    totalProfitVal);
                        }

                        DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                        totalSalesTxtFld.setText(
                                df2.format(totalSalesValue) + "/=");
                        totalProfitTxtFld.setText(
                                df2.format(totalProfitValue) + "/=");
                    } else if (salesSelectComboBx.getSelectionModel().isSelected(
                            1)) {
                        ItemSales.setItems(ItemSalesItems);
                        initialDatePk1.getEditor().clear();
                        finalDatePk1.getEditor().clear();
                        minimumSellingP1TxtFld.clear();
                        maximumSellingP1TxtFld.clear();
                        productTxtField.clear();
                        userSalesTxtField.clear();

                        final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                                p -> p.getSaleType().equals("PACKET SALES"));
                        ItemSales.setItems(filteredList);

                        totalSalesTxtFld.clear();
                        totalProfitTxtFld.clear();
                        // Do your changes here
                        Double totalSalesValue = 0.00;
                        Double totalProfitValue = 0.00;
                        for (int x = 0; x < ItemSales.getItems().size(); x++) {
                            String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                    "/=", "");
                            String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                    "/=", "");
                            totalSalesValue = totalSalesValue + parseDouble(
                                    totalSalesVal);
                            totalProfitValue = totalProfitValue + parseDouble(
                                    totalProfitVal);
                        }

                        DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                        totalSalesTxtFld.setText(
                                df2.format(totalSalesValue) + "/=");
                        totalProfitTxtFld.setText(
                                df2.format(totalProfitValue) + "/=");
                    } else if (salesSelectComboBx.getSelectionModel().isSelected(
                            2)) {
                        ItemSales.setItems(ItemSalesItems);
                        initialDatePk1.getEditor().clear();
                        finalDatePk1.getEditor().clear();
                        minimumSellingP1TxtFld.clear();
                        maximumSellingP1TxtFld.clear();
                        productTxtField.clear();
                        userSalesTxtField.clear();

                        final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                                p -> p.getSaleType().equals("RETAIL SALES"));
                        ItemSales.setItems(filteredList);

                        totalSalesTxtFld.clear();
                        totalProfitTxtFld.clear();
                        // Do your changes here
                        Double totalSalesValue = 0.00;
                        Double totalProfitValue = 0.00;
                        for (int x = 0; x < ItemSales.getItems().size(); x++) {
                            String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                    "/=", "");
                            String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                    "/=", "");
                            totalSalesValue = totalSalesValue + parseDouble(
                                    totalSalesVal);
                            totalProfitValue = totalProfitValue + parseDouble(
                                    totalProfitVal);
                        }

                        DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                        totalSalesTxtFld.setText(
                                df2.format(totalSalesValue) + "/=");
                        totalProfitTxtFld.setText(
                                df2.format(totalProfitValue) + "/=");
                    } else if (salesSelectComboBx.getSelectionModel().isSelected(
                            3)) {
                        ItemSales.setItems(ItemSalesItems);
                        initialDatePk1.getEditor().clear();
                        finalDatePk1.getEditor().clear();
                        minimumSellingP1TxtFld.clear();
                        maximumSellingP1TxtFld.clear();
                        productTxtField.clear();
                        userSalesTxtField.clear();

                        final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                                p -> p.getSaleType().equals("WHOLESELL SALES"));
                        ItemSales.setItems(filteredList);

                        totalSalesTxtFld.clear();
                        totalProfitTxtFld.clear();
                        // Do your changes here
                        Double totalSalesValue = 0.00;
                        Double totalProfitValue = 0.00;
                        for (int x = 0; x < ItemSales.getItems().size(); x++) {
                            String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                    "/=", "");
                            String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                    "/=", "");
                            totalSalesValue = totalSalesValue + parseDouble(
                                    totalSalesVal);
                            totalProfitValue = totalProfitValue + parseDouble(
                                    totalProfitVal);
                        }

                        DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                        totalSalesTxtFld.setText(
                                df2.format(totalSalesValue) + "/=");
                        totalProfitTxtFld.setText(
                                df2.format(totalProfitValue) + "/=");
                    }
                }
            });

            minimumSellingP1TxtFld.textProperty().addListener(e -> {
                if (!minimumSellingP1TxtFld.getText().isEmpty()) {
                    ItemSales.setItems(ItemSalesItems);
                    final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                            p -> parseDouble((p.getUnitPrice().replaceAll(",",
                                    "")).replaceAll("/=", "")) >= parseDouble(
                            minimumSellingP1TxtFld.getText()));
                    ItemSales.setItems(filteredList);

                    totalSalesTxtFld.clear();
                    totalProfitTxtFld.clear();
                    // Do your changes here
                    Double totalSalesValue = 0.00;
                    Double totalProfitValue = 0.00;
                    for (int x = 0; x < ItemSales.getItems().size(); x++) {
                        String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                "/=", "");
                        String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                "/=", "");
                        totalSalesValue = totalSalesValue + parseDouble(
                                totalSalesVal);
                        totalProfitValue = totalProfitValue + parseDouble(
                                totalProfitVal);
                    }

                    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                    totalSalesTxtFld.setText(df2.format(totalSalesValue) + "/=");
                    totalProfitTxtFld.setText(
                            df2.format(totalProfitValue) + "/=");
                }
            });

            maximumSellingP1TxtFld.textProperty().addListener(e -> {
                if (maximumSellingP1TxtFld.getText() != null) {
                    ItemSales.setItems(ItemSalesItems);
                    final FilteredList<ItemSalesData> filteredList = ItemSales.getItems().filtered(
                            p -> parseDouble((p.getUnitPrice().replaceAll(",",
                                    "")).replaceAll("/=", "")) <= parseDouble(
                            maximumSellingP1TxtFld.getText()));
                    ItemSales.setItems(filteredList);

                    totalSalesTxtFld.clear();
                    totalProfitTxtFld.clear();
                    // Do your changes here
                    Double totalSalesValue = 0.00;
                    Double totalProfitValue = 0.00;
                    for (int x = 0; x < ItemSales.getItems().size(); x++) {
                        String totalSalesVal = ItemSales.getItems().get(x).TotalValue.get().replace(
                                "/=", "");
                        String totalProfitVal = ItemSales.getItems().get(x).Profit.get().replace(
                                "/=", "");
                        totalSalesValue = totalSalesValue + parseDouble(
                                totalSalesVal);
                        totalProfitValue = totalProfitValue + parseDouble(
                                totalProfitVal);
                    }

                    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
                    totalSalesTxtFld.setText(df2.format(totalSalesValue) + "/=");
                    totalProfitTxtFld.setText(
                            df2.format(totalProfitValue) + "/=");
                }

            });

            ///Center VBox for the Sales Page
            VBox vboxSalesPgCenter = new VBox();
            vboxSalesPgCenter.setId("vbox8");
            vboxSalesPgCenter.getChildren().addAll(hboxSalesPageTitle,
                    hboxTableView, hboxEditBtnTotal, vboxConfigLblControls,
                    hboxEditSaleTitle, hboxEditSaleControls, hboxUpdateSaleBtn);

            //setting center of the BorderPane
            salesViewEditMajBP.setCenter(vboxSalesPgCenter);

            //major return method
            return salesViewEditMajBP;
        }
    }

    public class otherExpensesPage {

        String userId;
        String username;

        public otherExpensesPage(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getUsername() {
            return this.username;
        }

        public BorderPane addExpenses() {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ///---add Expenses Main Border Pane
            BorderPane addExpenseMajBP = new BorderPane();

            //Main Page Title
            Label addExpenseTitleLbl = new Label("Add New Business Expense");
            addExpenseTitleLbl.setId("label3");
            //HBox for containment
            HBox hboxAddExpenseTitle = new HBox();
            hboxAddExpenseTitle.setMinWidth(gW * 0.751);
            hboxAddExpenseTitle.setMaxWidth(gW * 0.751);
            hboxAddExpenseTitle.setId("hbox13");
            hboxAddExpenseTitle.getChildren().add(addExpenseTitleLbl);

            //Expenses table
            TableView<BusinessExpensesData> BusinessExpenses = new TableView<>();
            ObservableList<BusinessExpensesData> BusinessExpensesItems = FXCollections.observableArrayList();
            BusinessExpenses.setEditable(false);
            BusinessExpenses.setPrefHeight(gH * 0.5);
            BusinessExpenses.setItems(BusinessExpensesItems);

            BusinessExpenses.getColumns().clear();

            TableColumn BusinessExpenses_Product_Service = new TableColumn(
                    "Product/Service");
            BusinessExpenses_Product_Service.setPrefWidth(gW * 0.1);
            BusinessExpenses_Product_Service.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Product/Service"));
            BusinessExpenses.getColumns().addAll(
                    BusinessExpenses_Product_Service);

            TableColumn BusinessExpenses_Rate = new TableColumn("Rate");
            BusinessExpenses_Rate.setPrefWidth(gW * 0.07);
            BusinessExpenses_Rate.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Rate"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Rate);

            TableColumn BusinessExpenses_Quantity = new TableColumn("Quantity");
            BusinessExpenses_Quantity.setPrefWidth(gW * 0.07);
            BusinessExpenses_Quantity.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Quantity"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Quantity);

            TableColumn BusinessExpenses_PaymentTo = new TableColumn("PaymentTo");
            BusinessExpenses_PaymentTo.setPrefWidth(gW * 0.14);
            BusinessExpenses_PaymentTo.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "PaymentTo"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_PaymentTo);

            TableColumn BusinessExpenses_Priority = new TableColumn("Priority");
            BusinessExpenses_Priority.setPrefWidth(gW * 0.07);
            BusinessExpenses_Priority.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Priority"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Priority);

            TableColumn BusinessExpenses_DueDate = new TableColumn("DueDate");
            BusinessExpenses_DueDate.setPrefWidth(gW * 0.1);
            BusinessExpenses_DueDate.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "DueDate"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_DueDate);

            TableColumn BusinessExpenses_UnitPayment = new TableColumn(
                    "UnitPayment");
            BusinessExpenses_UnitPayment.setPrefWidth(gW * 0.1);
            BusinessExpenses_UnitPayment.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "UnitPayment"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_UnitPayment);

            TableColumn BusinessExpenses_PaidOn = new TableColumn("PaidOn");
            BusinessExpenses_PaidOn.setPrefWidth(gW * 0.1);
            BusinessExpenses_PaidOn.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "PaidOn"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_PaidOn);

            //HBox to contain the table
            HBox hboxBusinessExpensesTbl = new HBox();
            hboxBusinessExpensesTbl.getChildren().add(BusinessExpenses);

            ///Configurations for the adding expeses
            //Title for adding Expense
            Label addExpenseConfigControlsLbl = new Label(
                    "Configure/Add Expense");
            HBox addExpenseConfigControls = new HBox();
            addExpenseConfigControls.setMinWidth(gW * 0.751);
            addExpenseConfigControls.setMaxWidth(gW * 0.751);
            addExpenseConfigControls.setId("hbox4");
            addExpenseConfigControls.getChildren().add(
                    addExpenseConfigControlsLbl);

            //Label and TextField for productService Name
            Label pdtServiceNameLbl = new Label("Product/Service:");
            UpperCaseTextField pdtServiceNameTxtFld = new UpperCaseTextField();
            //HBox for their containment
            HBox hboxPdtServiceName = new HBox();
            hboxPdtServiceName.setId("hbox26");
            hboxPdtServiceName.getChildren().addAll(pdtServiceNameLbl,
                    pdtServiceNameTxtFld);

            //ComboBox for selecting Rate
            Label rateLbl = new Label("Rate:");
            ObservableList<String> options = FXCollections.observableArrayList(
                    "KILOGRAM",
                    "LITRE",
                    "HOUR",
                    "DAY",
                    "WEEK",
                    "MONTH",
                    "YEAR"
            );

            ComboBox rateComboBx = new ComboBox(options);
            rateComboBx.setMinWidth(gW * 0.1);
            rateComboBx.setMaxWidth(gW * 0.1);
            rateComboBx.setPromptText("Select Rate");
            rateComboBx.setEditable(false);
            //HBox for containment
            HBox hboxRate = new HBox();
            hboxRate.setId("hbox39");
            hboxRate.getChildren().addAll(rateLbl, rateComboBx);

            //Label and TextField for productService Quantity
            Label quantityLbl = new Label("Quantity:");
            DecimalTextField quantityTxtFld = new DecimalTextField();
            //HBox for their containment
            HBox hboxQuantity = new HBox();
            hboxQuantity.setId("hbox26");
            hboxQuantity.getChildren().addAll(quantityLbl, quantityTxtFld);

            //Label and TextField for paid to person
            Label paidToLbl = new Label("Paid To:");
            UpperCaseTextField paidToTxtFld = new UpperCaseTextField();
            //HBox for their containment
            HBox hboxPaidTo = new HBox();
            hboxPaidTo.setId("hbox26");
            hboxPaidTo.getChildren().addAll(paidToLbl, paidToTxtFld);

            //ComboBox for selecting Rate
            Label priorityLbl = new Label("Priority:");
            ObservableList<String> options1 = FXCollections.observableArrayList(
                    "NONE",
                    "LOW",
                    "MEDIUM",
                    "HIGH",
                    "CRITICAL"
            );

            ComboBox priorityComboBx = new ComboBox(options1);
            priorityComboBx.setMinWidth(gW * 0.105);
            priorityComboBx.setMaxWidth(gW * 0.105);
            priorityComboBx.setPromptText("Select Priority");
            priorityComboBx.setEditable(false);
            //HBox for containment
            HBox hboxPriority = new HBox();
            hboxPriority.setId("hbox26");
            hboxPriority.getChildren().addAll(priorityLbl, priorityComboBx);

            //Labels for the Dates
            Label dueDateLbl = new Label("Due Date:");
            DatePicker dueDatePk = new DatePicker();
            dueDatePk.setMinWidth(gW * 0.1);
            dueDatePk.setMaxWidth(gW * 0.1);

            //DatePicker containers
            HBox hboxDueDate = new HBox();
            hboxDueDate.setId("hbox26");
            hboxDueDate.getChildren().addAll(dueDateLbl, dueDatePk);

            //Label and TextField for Unit payment
            Label unitPaymentLbl = new Label("Unit Payment:");
            TextField unitPaymentTxtFld = new TextField();
            //HBox for their containment
            HBox hboxUnitPayment = new HBox();
            hboxUnitPayment.setId("hbox26");
            hboxUnitPayment.getChildren().addAll(unitPaymentLbl,
                    unitPaymentTxtFld);

            //Button for adding expense
            Button addExpenseBtn = new Button("Add Expense");
            addExpenseBtn.getStyleClass().add("btn-success");
            //HBox for containment
            HBox hboxAddExpenseBtn = new HBox();
            hboxAddExpenseBtn.setMinWidth(gW * 0.4189);
            hboxAddExpenseBtn.setMaxWidth(gW * 0.4189);
            hboxAddExpenseBtn.setId("hbox23");
            hboxAddExpenseBtn.getChildren().add(addExpenseBtn);

            //HBox to contain the configs
            HBox hboxAddExpenseConfigs1 = new HBox();
            hboxAddExpenseConfigs1.setId("hbox7");
            hboxAddExpenseConfigs1.getChildren().addAll(hboxPdtServiceName,
                    hboxRate, hboxQuantity, hboxPaidTo, hboxPriority);
            HBox hboxAddedExpenseConfigs2 = new HBox();
            hboxAddedExpenseConfigs2.setId("hbox38");
            hboxAddedExpenseConfigs2.getChildren().addAll(hboxDueDate,
                    hboxUnitPayment, hboxAddExpenseBtn);

            ///VBox for the Border Pane Center
            VBox vboxAddExpenseBPCenter = new VBox();
            vboxAddExpenseBPCenter.setId("vbox8");
            vboxAddExpenseBPCenter.getChildren().addAll(hboxAddExpenseTitle,
                    hboxBusinessExpensesTbl, addExpenseConfigControls,
                    hboxAddExpenseConfigs1, hboxAddedExpenseConfigs2);

            ///--setting left of the maj border pane
            //Foreword Before Adding Expense
            TextArea forewordTextArea = new TextArea();
            forewordTextArea.setId("textArea1");
            forewordTextArea.setMinSize(gW * 0.195, gH * 0.55);
            forewordTextArea.setMaxSize(gW * 0.195, gH * 0.55);
            forewordTextArea.setEditable(false);
            forewordTextArea.setFocusTraversable(false);
            forewordTextArea.setText("                 HELP    \n"
                    + "These Added Expenses will, \n"
                    + "contribute to the final   \n"
                    + "expected Overall Profit for\n"
                    + "the business.\n\n"
                    + "Care should be taken to  \n"
                    + "ensure consistency as \n"
                    + "may affect the business\n"
                    + "analysis \n\n"
                    + "Only the proprietor \n"
                    + "should fill this section\n\n"
            );

            HBox hboxForeword = new HBox();
            hboxForeword.getChildren().add(forewordTextArea);

            Label foreWordTitle = new Label("User Guide");
            foreWordTitle.setId("label3");
            HBox hboxForeWordTitle = new HBox();
            hboxForeWordTitle.setId("hbox13");
            hboxForeWordTitle.getChildren().add(foreWordTitle);

            //final vbox for the left part
            VBox vboxBPLeftPart = new VBox();
            vboxBPLeftPart.setId("vbox8");
            vboxBPLeftPart.getChildren().addAll(hboxForeWordTitle, hboxForeword);

            //setting left
            addExpenseMajBP.setLeft(vboxBPLeftPart);

            //setting the BordePane  center
            addExpenseMajBP.setCenter(vboxAddExpenseBPCenter);

            //Main return method
            return addExpenseMajBP;
        }

        public BorderPane editExpense() {
            ///--prerequisites
            final Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
            double gH = bounds.getHeight();
            double gW = bounds.getWidth();

            ///---edit Expenses Main Border Pane
            BorderPane editExpenseMajBP = new BorderPane();

            //Main Page Title
            Label editExpenseTitleLbl = new Label("Edit Business Expense");
            editExpenseTitleLbl.setId("label3");
            //HBox for containment
            HBox hboxEditExpenseTitle = new HBox();
            hboxEditExpenseTitle.setMinWidth(gW * 0.751);
            hboxEditExpenseTitle.setMaxWidth(gW * 0.751);
            hboxEditExpenseTitle.setId("hbox13");
            hboxEditExpenseTitle.getChildren().add(editExpenseTitleLbl);

            //Expenses table
            TableView<BusinessExpensesData> BusinessExpenses = new TableView<>();
            ObservableList<BusinessExpensesData> BusinessExpensesItems = FXCollections.observableArrayList();
            BusinessExpenses.setEditable(false);
            BusinessExpenses.setPrefHeight(gH * 0.5);
            BusinessExpenses.setItems(BusinessExpensesItems);

            BusinessExpenses.getColumns().clear();

            TableColumn BusinessExpenses_Product_Service = new TableColumn(
                    "Product/Service");
            BusinessExpenses_Product_Service.setPrefWidth(gW * 0.1);
            BusinessExpenses_Product_Service.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Product/Service"));
            BusinessExpenses.getColumns().addAll(
                    BusinessExpenses_Product_Service);

            TableColumn BusinessExpenses_Rate = new TableColumn("Rate");
            BusinessExpenses_Rate.setPrefWidth(gW * 0.07);
            BusinessExpenses_Rate.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Rate"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Rate);

            TableColumn BusinessExpenses_Quantity = new TableColumn("Quantity");
            BusinessExpenses_Quantity.setPrefWidth(gW * 0.07);
            BusinessExpenses_Quantity.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Quantity"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Quantity);

            TableColumn BusinessExpenses_PaymentTo = new TableColumn("PaymentTo");
            BusinessExpenses_PaymentTo.setPrefWidth(gW * 0.14);
            BusinessExpenses_PaymentTo.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "PaymentTo"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_PaymentTo);

            TableColumn BusinessExpenses_Priority = new TableColumn("Priority");
            BusinessExpenses_Priority.setPrefWidth(gW * 0.07);
            BusinessExpenses_Priority.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "Priority"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_Priority);

            TableColumn BusinessExpenses_DueDate = new TableColumn("DueDate");
            BusinessExpenses_DueDate.setPrefWidth(gW * 0.1);
            BusinessExpenses_DueDate.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "DueDate"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_DueDate);

            TableColumn BusinessExpenses_UnitPayment = new TableColumn(
                    "UnitPayment");
            BusinessExpenses_UnitPayment.setPrefWidth(gW * 0.1);
            BusinessExpenses_UnitPayment.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "UnitPayment"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_UnitPayment);

            TableColumn BusinessExpenses_PaidOn = new TableColumn("PaidOn");
            BusinessExpenses_PaidOn.setPrefWidth(gW * 0.1);
            BusinessExpenses_PaidOn.setCellValueFactory(
                    new PropertyValueFactory<BusinessExpensesData, String>(
                            "PaidOn"));
            BusinessExpenses.getColumns().addAll(BusinessExpenses_PaidOn);

            //HBox to contain the table
            HBox hboxBusinessExpensesTbl = new HBox();
            hboxBusinessExpensesTbl.getChildren().add(BusinessExpenses);

            //Filter By Title and controls
            Label filterByTitleLbl = new Label("FilterBy");
            filterByTitleLbl.setId("label3");
            HBox hboxFilterByTitle = new HBox();
            hboxFilterByTitle.setId("hbox13");
            hboxFilterByTitle.getChildren().add(filterByTitleLbl);

            //Labels for the Dates
            Label initialDate1Lbl = new Label("Start Date:");
            Label finalDate1Lbl = new Label("End Date:");

            //Date Comparison for the graph controls
            DatePicker initialDatePk1 = new DatePicker();
            initialDatePk1.setMinWidth(gW * 0.1);
            initialDatePk1.setMaxWidth(gW * 0.1);
            DatePicker finalDatePk1 = new DatePicker();
            finalDatePk1.setMinWidth(gW * 0.1);
            finalDatePk1.setMaxWidth(gW * 0.1);

            //DatePicker containers
            HBox hboxInitialDate1 = new HBox();
            hboxInitialDate1.setId("hbox26");
            HBox hboxFinalDate1 = new HBox();
            hboxFinalDate1.setId("hbox26");
            HBox hboxFinalDates1Config = new HBox();
            hboxFinalDates1Config.setId("hbox40");

            //placing all of them in their containers
            hboxInitialDate1.getChildren().addAll(initialDate1Lbl,
                    initialDatePk1);
            hboxFinalDate1.getChildren().addAll(finalDate1Lbl, finalDatePk1);
            hboxFinalDates1Config.getChildren().addAll(hboxInitialDate1,
                    hboxFinalDate1);

            ///Configurations for the adding expeses
            //Title for Editing Expense
            Label editExpenseConfigControlsLbl = new Label(
                    "Configure/Edit Expense");
            HBox editExpenseConfigControls = new HBox();
            editExpenseConfigControls.setMinWidth(gW * 0.751);
            editExpenseConfigControls.setMaxWidth(gW * 0.751);
            editExpenseConfigControls.setId("hbox4");
            editExpenseConfigControls.getChildren().add(
                    editExpenseConfigControlsLbl);

            //Label and TextField for productService Name
            Label pdtServiceNameLbl = new Label("Product/Service:");
            UpperCaseTextField pdtServiceNameTxtFld = new UpperCaseTextField();
            //HBox for their containment
            HBox hboxPdtServiceName = new HBox();
            hboxPdtServiceName.setId("hbox26");
            hboxPdtServiceName.getChildren().addAll(pdtServiceNameLbl,
                    pdtServiceNameTxtFld);

            //ComboBox for selecting Rate
            Label rateLbl = new Label("Rate:");
            ObservableList<String> options = FXCollections.observableArrayList(
                    "KILOGRAM",
                    "LITRE",
                    "HOUR",
                    "DAY",
                    "WEEK",
                    "MONTH",
                    "YEAR"
            );

            ComboBox rateComboBx = new ComboBox(options);
            rateComboBx.setMinWidth(gW * 0.1);
            rateComboBx.setMaxWidth(gW * 0.1);
            rateComboBx.setPromptText("Select Rate");
            rateComboBx.setEditable(false);
            //HBox for containment
            HBox hboxRate = new HBox();
            hboxRate.setId("hbox39");
            hboxRate.getChildren().addAll(rateLbl, rateComboBx);

            //Label and TextField for productService Quantity
            Label quantityLbl = new Label("Quantity:");
            DecimalTextField quantityTxtFld = new DecimalTextField();
            //HBox for their containment
            HBox hboxQuantity = new HBox();
            hboxQuantity.setId("hbox26");
            hboxQuantity.getChildren().addAll(quantityLbl, quantityTxtFld);

            //Label and TextField for paid to person
            Label paidToLbl = new Label("Paid To:");
            UpperCaseTextField paidToTxtFld = new UpperCaseTextField();
            //HBox for their containment
            HBox hboxPaidTo = new HBox();
            hboxPaidTo.setId("hbox26");
            hboxPaidTo.getChildren().addAll(paidToLbl, paidToTxtFld);

            //ComboBox for selecting Rate
            Label priorityLbl = new Label("Priority:");
            ObservableList<String> options1 = FXCollections.observableArrayList(
                    "NONE",
                    "LOW",
                    "MEDIUM",
                    "HIGH",
                    "CRITICAL"
            );

            ComboBox priorityComboBx = new ComboBox(options1);
            priorityComboBx.setMinWidth(gW * 0.105);
            priorityComboBx.setMaxWidth(gW * 0.105);
            priorityComboBx.setPromptText("Select Priority");
            priorityComboBx.setEditable(false);
            //HBox for containment
            HBox hboxPriority = new HBox();
            hboxPriority.setId("hbox26");
            hboxPriority.getChildren().addAll(priorityLbl, priorityComboBx);

            //Labels for the Dates
            Label dueDateLbl = new Label("Due Date:");
            DatePicker dueDatePk = new DatePicker();
            dueDatePk.setMinWidth(gW * 0.1);
            dueDatePk.setMaxWidth(gW * 0.1);

            //DatePicker containers
            HBox hboxDueDate = new HBox();
            hboxDueDate.setId("hbox26");
            hboxDueDate.getChildren().addAll(dueDateLbl, dueDatePk);

            //Label and TextField for Unit payment
            Label unitPaymentLbl = new Label("Unit Payment:");
            TextField unitPaymentTxtFld = new TextField();
            //HBox for their containment
            HBox hboxUnitPayment = new HBox();
            hboxUnitPayment.setId("hbox26");
            hboxUnitPayment.getChildren().addAll(unitPaymentLbl,
                    unitPaymentTxtFld);

            //Button for adding expense
            Button editExpenseBtn = new Button("  Update  ");
            editExpenseBtn.getStyleClass().add("btn-info");
            //HBox for containment
            HBox hboxEditExpenseBtn = new HBox();
            hboxEditExpenseBtn.setMinWidth(gW * 0.4189);
            hboxEditExpenseBtn.setMaxWidth(gW * 0.4189);
            hboxEditExpenseBtn.setId("hbox23");
            hboxEditExpenseBtn.getChildren().add(editExpenseBtn);

            //HBox to contain the configs
            HBox hboxEditExpenseConfigs1 = new HBox();
            hboxEditExpenseConfigs1.setId("hbox7");
            hboxEditExpenseConfigs1.getChildren().addAll(hboxPdtServiceName,
                    hboxRate, hboxQuantity, hboxPaidTo, hboxPriority);
            HBox hboxEditExpenseConfigs2 = new HBox();
            hboxEditExpenseConfigs2.setId("hbox38");
            hboxEditExpenseConfigs2.getChildren().addAll(hboxDueDate,
                    hboxUnitPayment, hboxEditExpenseBtn);

            ///VBox for the Border Pane Center
            VBox vboxAddExpenseBPCenter = new VBox();
            vboxAddExpenseBPCenter.setId("vbox8");
            vboxAddExpenseBPCenter.getChildren().addAll(hboxEditExpenseTitle,
                    hboxBusinessExpensesTbl, hboxFilterByTitle,
                    hboxFinalDates1Config, editExpenseConfigControls,
                    hboxEditExpenseConfigs1, hboxEditExpenseConfigs2);

            ///--setting left of the maj border pane
            //Foreword Before Editing Expense
            TextArea forewordTextArea = new TextArea();
            forewordTextArea.setId("textArea1");
            forewordTextArea.setMinSize(gW * 0.195, gH * 0.55);
            forewordTextArea.setMaxSize(gW * 0.195, gH * 0.55);
            forewordTextArea.setEditable(false);
            forewordTextArea.setFocusTraversable(false);
            forewordTextArea.setText("                 HELP    \n"
                    + "These Added Expenses will, \n"
                    + "contribute to the final   \n"
                    + "expected Overall Profit for\n"
                    + "the business.\n\n"
                    + "Care should be taken to  \n"
                    + "ensure consistency as \n"
                    + "may affect the business\n"
                    + "analysis \n\n"
                    + "Only the proprietor \n"
                    + "should fill this section\n\n"
            );

            HBox hboxForeword = new HBox();
            hboxForeword.getChildren().add(forewordTextArea);

            Label foreWordTitle = new Label("User Guide");
            foreWordTitle.setId("label3");
            HBox hboxForeWordTitle = new HBox();
            hboxForeWordTitle.setId("hbox13");
            hboxForeWordTitle.getChildren().add(foreWordTitle);

            //final vbox for the left part
            VBox vboxBPLeftPart = new VBox();
            vboxBPLeftPart.setId("vbox8");
            vboxBPLeftPart.getChildren().addAll(hboxForeWordTitle, hboxForeword);

            //setting left
            editExpenseMajBP.setLeft(vboxBPLeftPart);

            //setting the BordePane  center
            editExpenseMajBP.setCenter(vboxAddExpenseBPCenter);

            //Main return method
            return editExpenseMajBP;
        }
    }

//////---%$table classes
    public class InventoryItemsData {

        InventoryItemsData(String Barcode_IDx, String Namex, String Descriptionx,
                String Typex, String Unitsx, String UnitQuantityx,
                String PurchasePricex, String SellingPricex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            Name = new SimpleStringProperty(Namex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            Units = new SimpleStringProperty(Unitsx);
            UnitQuantity = new SimpleStringProperty(UnitQuantityx);
            PurchasePrice = new SimpleStringProperty(PurchasePricex);
            SellingPrice = new SimpleStringProperty(SellingPricex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty Name;

        public void setName(String value) {
            NameProperty().set(value);
        }

        public String getName() {
            return NameProperty().get();
        }

        public StringProperty NameProperty() {
            if (Name == null) {
                Name = new SimpleStringProperty(this, "Name");
            }
            return Name;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty Units;

        public void setUnits(String value) {
            UnitsProperty().set(value);
        }

        public String getUnits() {
            return UnitsProperty().get();
        }

        public StringProperty UnitsProperty() {
            if (Units == null) {
                Units = new SimpleStringProperty(this, "Units");
            }
            return Units;
        }

        private StringProperty UnitQuantity;

        public void setUnitQuantity(String value) {
            UnitQuantityProperty().set(value);
        }

        public String getUnitQuantity() {
            return UnitQuantityProperty().get();
        }

        public StringProperty UnitQuantityProperty() {
            if (UnitQuantity == null) {
                UnitQuantity = new SimpleStringProperty(this, "UnitQuantity");
            }
            return UnitQuantity;
        }

        private StringProperty PurchasePrice;

        public void setPurchasePrice(String value) {
            PurchasePriceProperty().set(value);
        }

        public String getPurchasePrice() {
            return PurchasePriceProperty().get();
        }

        public StringProperty PurchasePriceProperty() {
            if (PurchasePrice == null) {
                PurchasePrice = new SimpleStringProperty(this, "PurchasePrice");
            }
            return PurchasePrice;
        }

        private StringProperty SellingPrice;

        public void setSellingPrice(String value) {
            SellingPriceProperty().set(value);
        }

        public String getSellingPrice() {
            return SellingPriceProperty().get();
        }

        public StringProperty SellingPriceProperty() {
            if (SellingPrice == null) {
                SellingPrice = new SimpleStringProperty(this, "SellingPrice");
            }
            return SellingPrice;
        }

    }

    public class InventorySummaryData {

        InventorySummaryData(String Barcode_IDx, String ItemNamex,
                String TotalValuex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            TotalValue = new SimpleStringProperty(TotalValuex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode/ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty TotalValue;

        public void setTotalValue(String value) {
            TotalValueProperty().set(value);
        }

        public String getTotalValue() {
            return TotalValueProperty().get();
        }

        public StringProperty TotalValueProperty() {
            if (TotalValue == null) {
                TotalValue = new SimpleStringProperty(this, "TotalValue");
            }
            return TotalValue;
        }

    }

    public class SummaryGeneralSalesData {

        SummaryGeneralSalesData(String Barcode_IDx, String ItemNamex,
                String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode/ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class PacketSalesSummaryData {

        PacketSalesSummaryData(String Barcode_IDx, String ItemNamex,
                String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode/ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class RetailSalesSummaryData {

        RetailSalesSummaryData(String Barcode_IDx, String ItemNamex,
                String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode/ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class WholeSaleSummaryData {

        WholeSaleSummaryData(String Barcode_IDx, String ItemNamex,
                String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class ExpensesSummaryData {

        ExpensesSummaryData(String Product_Servicex, String PaidTox,
                String Amountx) {

            Product_Service = new SimpleStringProperty(Product_Servicex);
            PaidTo = new SimpleStringProperty(PaidTox);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Product_Service;

        public void setProduct_Service(String value) {
            Product_ServiceProperty().set(value);
        }

        public String getProduct_Service() {
            return Product_ServiceProperty().get();
        }

        public StringProperty Product_ServiceProperty() {
            if (Product_Service == null) {
                Product_Service = new SimpleStringProperty(this,
                        "Product/Service");
            }
            return Product_Service;
        }

        private StringProperty PaidTo;

        public void setPaidTo(String value) {
            PaidToProperty().set(value);
        }

        public String getPaidTo() {
            return PaidToProperty().get();
        }

        public StringProperty PaidToProperty() {
            if (PaidTo == null) {
                PaidTo = new SimpleStringProperty(this, "PaidTo");
            }
            return PaidTo;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class PacketInventoryData {

        PacketInventoryData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String RemainingQuantityx,
                String UnitPricex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            RemainingQuantity = new SimpleStringProperty(RemainingQuantityx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty RemainingQuantity;

        public void setRemainingQuantity(String value) {
            RemainingQuantityProperty().set(value);
        }

        public String getRemainingQuantity() {
            return RemainingQuantityProperty().get();
        }

        public StringProperty RemainingQuantityProperty() {
            if (RemainingQuantity == null) {
                RemainingQuantity = new SimpleStringProperty(this,
                        "RemainingQty");
            }
            return RemainingQuantity;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

    }

    public class RetailInventoryData {

        RetailInventoryData(String Idx, String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String PacketQtyx,
                String RemainingQtyx, String UnitPricex) {

            Id = new SimpleStringProperty(Idx);
            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            PacketQty = new SimpleStringProperty(PacketQtyx);
            RemainingQty = new SimpleStringProperty(RemainingQtyx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
        }

        private StringProperty Id;

        public void setId(String value) {
            IdProperty().set(value);
        }

        public String getId() {
            return IdProperty().get();
        }

        public StringProperty IdProperty() {
            if (Id == null) {
                Id = new SimpleStringProperty(this, "Id");
            }
            return Id;
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty PacketQty;

        public void setPacketQty(String value) {
            PacketQtyProperty().set(value);
        }

        public String getPacketQty() {
            return PacketQtyProperty().get();
        }

        public StringProperty PacketQtyProperty() {
            if (PacketQty == null) {
                PacketQty = new SimpleStringProperty(this, "PacketQty");
            }
            return PacketQty;
        }

        private StringProperty RemainingQty;

        public void setRemainingQty(String value) {
            RemainingQtyProperty().set(value);
        }

        public String getRemainingQty() {
            return RemainingQtyProperty().get();
        }

        public StringProperty RemainingQtyProperty() {
            if (RemainingQty == null) {
                RemainingQty = new SimpleStringProperty(this, "RemainingQty");
            }
            return RemainingQty;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

    }

    public class WholeSaleInventoryData {

        WholeSaleInventoryData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String RemainingQtyx,
                String MinWSQtyx, String UnitPricex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            RemainingQty = new SimpleStringProperty(RemainingQtyx);
            MinWSQty = new SimpleStringProperty(MinWSQtyx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty RemainingQty;

        public void setRemainingQty(String value) {
            RemainingQtyProperty().set(value);
        }

        public String getRemainingQty() {
            return RemainingQtyProperty().get();
        }

        public StringProperty RemainingQtyProperty() {
            if (RemainingQty == null) {
                RemainingQty = new SimpleStringProperty(this, "RemainingQty");
            }
            return RemainingQty;
        }

        private StringProperty MinWSQty;

        public void setMinWSQty(String value) {
            MinWSQtyProperty().set(value);
        }

        public String getMinWSQty() {
            return MinWSQtyProperty().get();
        }

        public StringProperty MinWSQtyProperty() {
            if (MinWSQty == null) {
                MinWSQty = new SimpleStringProperty(this, "MinWSQty");
            }
            return MinWSQty;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

    }

    public class TempPacketSaleData {

        TempPacketSaleData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String QtySoldx,
                String UnitPricex, String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            QtySold = new SimpleStringProperty(QtySoldx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty QtySold;

        public void setQtySold(String value) {
            QtySoldProperty().set(value);
        }

        public String getQtySold() {
            return QtySoldProperty().get();
        }

        public StringProperty QtySoldProperty() {
            if (QtySold == null) {
                QtySold = new SimpleStringProperty(this, "QtySold");
            }
            return QtySold;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class TempRetailSaleData {

        TempRetailSaleData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String QtySoldx,
                String UnitPricex, String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            QtySold = new SimpleStringProperty(QtySoldx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty QtySold;

        public void setQtySold(String value) {
            QtySoldProperty().set(value);
        }

        public String getQtySold() {
            return QtySoldProperty().get();
        }

        public StringProperty QtySoldProperty() {
            if (QtySold == null) {
                QtySold = new SimpleStringProperty(this, "QtySold");
            }
            return QtySold;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class TempWholeSaleData {

        TempWholeSaleData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String QtySoldx,
                String UnitPricex, String Amountx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            QtySold = new SimpleStringProperty(QtySoldx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
            Amount = new SimpleStringProperty(Amountx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

        private StringProperty QtySold;

        public void setQtySold(String value) {
            QtySoldProperty().set(value);
        }

        public String getQtySold() {
            return QtySoldProperty().get();
        }

        public StringProperty QtySoldProperty() {
            if (QtySold == null) {
                QtySold = new SimpleStringProperty(this, "QtySold");
            }
            return QtySold;
        }

        private StringProperty Amount;

        public void setAmount(String value) {
            AmountProperty().set(value);
        }

        public String getAmount() {
            return AmountProperty().get();
        }

        public StringProperty AmountProperty() {
            if (Amount == null) {
                Amount = new SimpleStringProperty(this, "Amount");
            }
            return Amount;
        }

    }

    public class UserAccountsData {

        UserAccountsData(String UserIdx, String FirstNamex, String OtherNamesx,
                String UserNamex, String E_mailx, String Contactx,
                String CreatedByx, String CreatedOnx, String IsCreatorx) {

            UserId = new SimpleStringProperty(UserIdx);
            FirstName = new SimpleStringProperty(FirstNamex);
            OtherNames = new SimpleStringProperty(OtherNamesx);
            UserName = new SimpleStringProperty(UserNamex);
            E_mail = new SimpleStringProperty(E_mailx);
            Contact = new SimpleStringProperty(Contactx);
            CreatedOn = new SimpleStringProperty(CreatedOnx);
            CreatedBy = new SimpleStringProperty(CreatedByx);
            IsCreator = new SimpleStringProperty(IsCreatorx);
        }

        private StringProperty UserId;

        public void setUserId(String value) {
            UserIdProperty().set(value);
        }

        public String getUserId() {
            return UserIdProperty().get();
        }

        public StringProperty UserIdProperty() {
            if (UserId == null) {
                UserId = new SimpleStringProperty(this, "UserId");
            }
            return UserId;
        }

        private StringProperty FirstName;

        public void setFirstName(String value) {
            FirstNameProperty().set(value);
        }

        public String getFirstName() {
            return FirstNameProperty().get();
        }

        public StringProperty FirstNameProperty() {
            if (FirstName == null) {
                FirstName = new SimpleStringProperty(this, "FirstName");
            }
            return FirstName;
        }

        private StringProperty OtherNames;

        public void setOtherNames(String value) {
            OtherNamesProperty().set(value);
        }

        public String getOtherNames() {
            return OtherNamesProperty().get();
        }

        public StringProperty OtherNamesProperty() {
            if (OtherNames == null) {
                OtherNames = new SimpleStringProperty(this, "OtherNames");
            }
            return OtherNames;
        }

        private StringProperty UserName;

        public void setUserName(String value) {
            UserNameProperty().set(value);
        }

        public String getUserName() {
            return UserNameProperty().get();
        }

        public StringProperty UserNameProperty() {
            if (UserName == null) {
                UserName = new SimpleStringProperty(this, "UserName");
            }
            return UserName;
        }

        private StringProperty E_mail;

        public void setE_mail(String value) {
            E_mailProperty().set(value);
        }

        public String getE_mail() {
            return E_mailProperty().get();
        }

        public StringProperty E_mailProperty() {
            if (E_mail == null) {
                E_mail = new SimpleStringProperty(this, "E_mail");
            }
            return E_mail;
        }

        private StringProperty Contact;

        public void setContact(String value) {
            ContactProperty().set(value);
        }

        public String getContact() {
            return ContactProperty().get();
        }

        public StringProperty ContactProperty() {
            if (Contact == null) {
                Contact = new SimpleStringProperty(this, "Contact");
            }
            return Contact;
        }

        private StringProperty CreatedBy;

        public void setCreatedBy(String value) {
            CreatedByProperty().set(value);
        }

        public String getCreatedBy() {
            return CreatedByProperty().get();
        }

        public StringProperty CreatedByProperty() {
            if (CreatedBy == null) {
                CreatedBy = new SimpleStringProperty(this, "CreatedBy");
            }
            return CreatedBy;
        }

        private StringProperty CreatedOn;

        public void setCreatedOn(String value) {
            CreatedOnProperty().set(value);
        }

        public String getCreatedOn() {
            return CreatedOnProperty().get();
        }

        public StringProperty CreatedOnProperty() {
            if (CreatedOn == null) {
                CreatedOn = new SimpleStringProperty(this, "CreatedOn");
            }
            return CreatedOn;
        }

        private StringProperty IsCreator;

        public void setIsCreator(String value) {
            IsCreatorProperty().set(value);
        }

        public String getIsCreator() {
            return IsCreatorProperty().get();
        }

        public StringProperty IsCreatorProperty() {
            if (IsCreator == null) {
                IsCreator = new SimpleStringProperty(this, "IsCreator");
            }
            return IsCreator;
        }

    }

    public class ItemSalesData {

        ItemSalesData(String Barcode_IDx, String ItemNamex, String Descriptionx,
                String Typex, String SaleTypex, String Quantityx,
                String UnitPricex, String TotalValuex, String Profitx,
                String SoldByx, String SoldOnx) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            SaleType = new SimpleStringProperty(SaleTypex);
            Quantity = new SimpleStringProperty(Quantityx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
            TotalValue = new SimpleStringProperty(TotalValuex);
            Profit = new SimpleStringProperty(Profitx);
            SoldBy = new SimpleStringProperty(SoldByx);
            SoldOn = new SimpleStringProperty(SoldOnx);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty SaleType;

        public void setSaleType(String value) {
            SaleTypeProperty().set(value);
        }

        public String getSaleType() {
            return SaleTypeProperty().get();
        }

        public StringProperty SaleTypeProperty() {
            if (SaleType == null) {
                SaleType = new SimpleStringProperty(this, "SaleType");
            }
            return SaleType;
        }

        private StringProperty Quantity;

        public void setQuantity(String value) {
            QuantityProperty().set(value);
        }

        public String getQuantity() {
            return QuantityProperty().get();
        }

        public StringProperty QuantityProperty() {
            if (Quantity == null) {
                Quantity = new SimpleStringProperty(this, "Quantity");
            }
            return Quantity;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

        private StringProperty TotalValue;

        public void setTotalValue(String value) {
            TotalValueProperty().set(value);
        }

        public String getTotalValue() {
            return TotalValueProperty().get();
        }

        public StringProperty TotalValueProperty() {
            if (TotalValue == null) {
                TotalValue = new SimpleStringProperty(this, "TotalValue");
            }
            return TotalValue;
        }

        private StringProperty Profit;

        public void setProfit(String value) {
            ProfitProperty().set(value);
        }

        public String getProfit() {
            return ProfitProperty().get();
        }

        public StringProperty ProfitProperty() {
            if (Profit == null) {
                Profit = new SimpleStringProperty(this, "Profit");
            }
            return Profit;
        }

        private StringProperty SoldBy;

        public void setSoldBy(String value) {
            SoldByProperty().set(value);
        }

        public String getSoldBy() {
            return SoldByProperty().get();
        }

        public StringProperty SoldByProperty() {
            if (SoldBy == null) {
                SoldBy = new SimpleStringProperty(this, "SoldBy");
            }
            return SoldBy;
        }

        private StringProperty SoldOn;

        public void setSoldOn(String value) {
            SoldOnProperty().set(value);
        }

        public String getSoldOn() {
            return SoldOnProperty().get();
        }

        public StringProperty SoldOnProperty() {
            if (SoldOn == null) {
                SoldOn = new SimpleStringProperty(this, "SoldOn");
            }
            return SoldOn;
        }

    }

    public class BusinessExpensesData {

        BusinessExpensesData(String Product_Servicex, String Ratex,
                String Quantityx, String PaymentTox, String Priorityx,
                String DueDatex, String UnitPaymentx, String PaidOnx) {

            Product_Service = new SimpleStringProperty(Product_Servicex);
            Rate = new SimpleStringProperty(Ratex);
            Quantity = new SimpleStringProperty(Quantityx);
            PaymentTo = new SimpleStringProperty(PaymentTox);
            Priority = new SimpleStringProperty(Priorityx);
            DueDate = new SimpleStringProperty(DueDatex);
            UnitPayment = new SimpleStringProperty(UnitPaymentx);
            PaidOn = new SimpleStringProperty(PaidOnx);
        }

        private StringProperty Product_Service;

        public void setProduct_Service(String value) {
            Product_ServiceProperty().set(value);
        }

        public String getProduct_Service() {
            return Product_ServiceProperty().get();
        }

        public StringProperty Product_ServiceProperty() {
            if (Product_Service == null) {
                Product_Service = new SimpleStringProperty(this,
                        "Product_Service");
            }
            return Product_Service;
        }

        private StringProperty Rate;

        public void setRate(String value) {
            RateProperty().set(value);
        }

        public String getRate() {
            return RateProperty().get();
        }

        public StringProperty RateProperty() {
            if (Rate == null) {
                Rate = new SimpleStringProperty(this, "Rate");
            }
            return Rate;
        }

        private StringProperty Quantity;

        public void setQuantity(String value) {
            QuantityProperty().set(value);
        }

        public String getQuantity() {
            return QuantityProperty().get();
        }

        public StringProperty QuantityProperty() {
            if (Quantity == null) {
                Quantity = new SimpleStringProperty(this, "Quantity");
            }
            return Quantity;
        }

        private StringProperty PaymentTo;

        public void setPaymentTo(String value) {
            PaymentToProperty().set(value);
        }

        public String getPaymentTo() {
            return PaymentToProperty().get();
        }

        public StringProperty PaymentToProperty() {
            if (PaymentTo == null) {
                PaymentTo = new SimpleStringProperty(this, "PaymentTo");
            }
            return PaymentTo;
        }

        private StringProperty Priority;

        public void setPriority(String value) {
            PriorityProperty().set(value);
        }

        public String getPriority() {
            return PriorityProperty().get();
        }

        public StringProperty PriorityProperty() {
            if (Priority == null) {
                Priority = new SimpleStringProperty(this, "Priority");
            }
            return Priority;
        }

        private StringProperty DueDate;

        public void setDueDate(String value) {
            DueDateProperty().set(value);
        }

        public String getDueDate() {
            return DueDateProperty().get();
        }

        public StringProperty DueDateProperty() {
            if (DueDate == null) {
                DueDate = new SimpleStringProperty(this, "DueDate");
            }
            return DueDate;
        }

        private StringProperty UnitPayment;

        public void setUnitPayment(String value) {
            UnitPaymentProperty().set(value);
        }

        public String getUnitPayment() {
            return UnitPaymentProperty().get();
        }

        public StringProperty UnitPaymentProperty() {
            if (UnitPayment == null) {
                UnitPayment = new SimpleStringProperty(this, "UnitPayment");
            }
            return UnitPayment;
        }

        private StringProperty PaidOn;

        public void setPaidOn(String value) {
            PaidOnProperty().set(value);
        }

        public String getPaidOn() {
            return PaidOnProperty().get();
        }

        public StringProperty PaidOnProperty() {
            if (PaidOn == null) {
                PaidOn = new SimpleStringProperty(this, "PaidOn");
            }
            return PaidOn;
        }

    }

    public class InventoryGenDataData {

        InventoryGenDataData(String ItemIdx, String Barcode_IDx,
                String ItemNamex, String Descriptionx, String Typex,
                String InputQuantityx, String RemainingQuantityx,
                String UnitQuantityx, String PurchasePricex, String UnitPricex,
                String CreatedByx, String CreatedOnx, String FullyConsumedOnx) {

            ItemId = new SimpleStringProperty(ItemIdx);
            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            InputQuantity = new SimpleStringProperty(InputQuantityx);
            RemainingQuantity = new SimpleStringProperty(RemainingQuantityx);
            UnitQuantity = new SimpleStringProperty(UnitQuantityx);
            PurchasePrice = new SimpleStringProperty(PurchasePricex);
            UnitPrice = new SimpleStringProperty(UnitPricex);
            CreatedBy = new SimpleStringProperty(CreatedByx);
            CreatedOn = new SimpleStringProperty(CreatedOnx);
            FullyConsumedOn = new SimpleStringProperty(FullyConsumedOnx);
        }

        private StringProperty ItemId;

        public void setItemId(String value) {
            ItemIdProperty().set(value);
        }

        public String getItemId() {
            return ItemIdProperty().get();
        }

        public StringProperty ItemIdProperty() {
            if (ItemId == null) {
                ItemId = new SimpleStringProperty(this, "ItemId");
            }
            return ItemId;
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty InputQuantity;

        public void setInputQuantity(String value) {
            InputQuantityProperty().set(value);
        }

        public String getInputQuantity() {
            return InputQuantityProperty().get();
        }

        public StringProperty InputQuantityProperty() {
            if (InputQuantity == null) {
                InputQuantity = new SimpleStringProperty(this, "InputQuantity");
            }
            return InputQuantity;
        }

        private StringProperty RemainingQuantity;

        public void setRemainingQuantity(String value) {
            RemainingQuantityProperty().set(value);
        }

        public String getRemainingQuantity() {
            return RemainingQuantityProperty().get();
        }

        public StringProperty RemainingQuantityProperty() {
            if (RemainingQuantity == null) {
                RemainingQuantity = new SimpleStringProperty(this,
                        "RemainingQuantity");
            }
            return RemainingQuantity;
        }

        private StringProperty UnitQuantity;

        public void setUnitQuantity(String value) {
            UnitQuantityProperty().set(value);
        }

        public String getUnitQuantity() {
            return UnitQuantityProperty().get();
        }

        public StringProperty UnitQuantityProperty() {
            if (UnitQuantity == null) {
                UnitQuantity = new SimpleStringProperty(this, "UnitQuantity");
            }
            return UnitQuantity;
        }

        private StringProperty PurchasePrice;

        public void setPurchasePrice(String value) {
            PurchasePriceProperty().set(value);
        }

        public String getPurchasePrice() {
            return PurchasePriceProperty().get();
        }

        public StringProperty PurchasePriceProperty() {
            if (PurchasePrice == null) {
                PurchasePrice = new SimpleStringProperty(this, "PurchasePrice");
            }
            return PurchasePrice;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

        private StringProperty CreatedBy;

        public void setCreatedBy(String value) {
            CreatedByProperty().set(value);
        }

        public String getCreatedBy() {
            return CreatedByProperty().get();
        }

        public StringProperty CreatedByProperty() {
            if (CreatedBy == null) {
                CreatedBy = new SimpleStringProperty(this, "CreatedBy");
            }
            return CreatedBy;
        }

        private StringProperty CreatedOn;

        public void setCreatedOn(String value) {
            CreatedOnProperty().set(value);
        }

        public String getCreatedOn() {
            return CreatedOnProperty().get();
        }

        public StringProperty CreatedOnProperty() {
            if (CreatedOn == null) {
                CreatedOn = new SimpleStringProperty(this, "CreatedOn");
            }
            return CreatedOn;
        }

        private StringProperty FullyConsumedOn;

        public void setFullyConsumedOn(String value) {
            FullyConsumedOnProperty().set(value);
        }

        public String getFullyConsumedOn() {
            return FullyConsumedOnProperty().get();
        }

        public StringProperty FullyConsumedOnProperty() {
            if (FullyConsumedOn == null) {
                FullyConsumedOn = new SimpleStringProperty(this,
                        "FullyConsumedOn");
            }
            return FullyConsumedOn;
        }

    }

    public class WholeSellConfigData {

        WholeSellConfigData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String MinimunQuantityx,
                String PurchasePricex, String WholeSellPricex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            MinimunQuantity = new SimpleStringProperty(MinimunQuantityx);
            PurchasePrice = new SimpleStringProperty(PurchasePricex);
            WholeSellPrice = new SimpleStringProperty(WholeSellPricex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty MinimunQuantity;

        public void setMinimunQuantity(String value) {
            MinimunQuantityProperty().set(value);
        }

        public String getMinimunQuantity() {
            return MinimunQuantityProperty().get();
        }

        public StringProperty MinimunQuantityProperty() {
            if (MinimunQuantity == null) {
                MinimunQuantity = new SimpleStringProperty(this,
                        "MinimunQuantity");
            }
            return MinimunQuantity;
        }

        private StringProperty PurchasePrice;

        public void setPurchasePrice(String value) {
            PurchasePriceProperty().set(value);
        }

        public String getPurchasePrice() {
            return PurchasePriceProperty().get();
        }

        public StringProperty PurchasePriceProperty() {
            if (PurchasePrice == null) {
                PurchasePrice = new SimpleStringProperty(this, "PurchasePrice");
            }
            return PurchasePrice;
        }

        private StringProperty WholeSellPrice;

        public void setWholeSellPrice(String value) {
            WholeSellPriceProperty().set(value);
        }

        public String getWholeSellPrice() {
            return WholeSellPriceProperty().get();
        }

        public StringProperty WholeSellPriceProperty() {
            if (WholeSellPrice == null) {
                WholeSellPrice = new SimpleStringProperty(this, "WholeSellPrice");
            }
            return WholeSellPrice;
        }

    }

    public class RetailSellConfigData {

        RetailSellConfigData(String Barcode_IDx, String ItemNamex,
                String Descriptionx, String Typex, String TotalQuantityx,
                String UnitPricex) {

            Barcode_ID = new SimpleStringProperty(Barcode_IDx);
            ItemName = new SimpleStringProperty(ItemNamex);
            Description = new SimpleStringProperty(Descriptionx);
            Type = new SimpleStringProperty(Typex);
            TotalQuantity = new SimpleStringProperty(TotalQuantityx);
            UnitPrice = new SimpleStringProperty(UnitPricex);
        }

        private StringProperty Barcode_ID;

        public void setBarcode_ID(String value) {
            Barcode_IDProperty().set(value);
        }

        public String getBarcode_ID() {
            return Barcode_IDProperty().get();
        }

        public StringProperty Barcode_IDProperty() {
            if (Barcode_ID == null) {
                Barcode_ID = new SimpleStringProperty(this, "Barcode_ID");
            }
            return Barcode_ID;
        }

        private StringProperty ItemName;

        public void setItemName(String value) {
            ItemNameProperty().set(value);
        }

        public String getItemName() {
            return ItemNameProperty().get();
        }

        public StringProperty ItemNameProperty() {
            if (ItemName == null) {
                ItemName = new SimpleStringProperty(this, "ItemName");
            }
            return ItemName;
        }

        private StringProperty Description;

        public void setDescription(String value) {
            DescriptionProperty().set(value);
        }

        public String getDescription() {
            return DescriptionProperty().get();
        }

        public StringProperty DescriptionProperty() {
            if (Description == null) {
                Description = new SimpleStringProperty(this, "Description");
            }
            return Description;
        }

        private StringProperty Type;

        public void setType(String value) {
            TypeProperty().set(value);
        }

        public String getType() {
            return TypeProperty().get();
        }

        public StringProperty TypeProperty() {
            if (Type == null) {
                Type = new SimpleStringProperty(this, "Type");
            }
            return Type;
        }

        private StringProperty TotalQuantity;

        public void setTotalQuantity(String value) {
            TotalQuantityProperty().set(value);
        }

        public String getTotalQuantity() {
            return TotalQuantityProperty().get();
        }

        public StringProperty TotalQuantityProperty() {
            if (TotalQuantity == null) {
                TotalQuantity = new SimpleStringProperty(this, "TotalQuantity");
            }
            return TotalQuantity;
        }

        private StringProperty UnitPrice;

        public void setUnitPrice(String value) {
            UnitPriceProperty().set(value);
        }

        public String getUnitPrice() {
            return UnitPriceProperty().get();
        }

        public StringProperty UnitPriceProperty() {
            if (UnitPrice == null) {
                UnitPrice = new SimpleStringProperty(this, "UnitPrice");
            }
            return UnitPrice;
        }

    }

//////---%$business Logic Classes/APIs
    public class insertUpdateStockUser {

        public String insertItem(String barcodeID, String itemName,
                String description, String type, String units, String unitQty,
                String purchasePrice, String sellingPrice) {
            String insertItemQuery = "INSERT INTO inventory (barcode_ID, itemName, description, type, inputQuantity, remainingQuantity, unitQuantity, purchasePrice, unitPrice, createdBy) VALUES ('" + barcodeID + "','" + itemName + "','" + description + "','" + type + "','" + parseInt(
                    units) + "','" + parseInt(units) + "','" + parseInt(unitQty) + "','" + parseDouble(
                    purchasePrice) + "','" + parseDouble(sellingPrice) + "','" + usernameNameLblTitle.getText() + "')";
            return insertItemQuery;
        }

        public String updateAllItems(String barcodeID, String itemName,
                String description, String type, String purchasePrice,
                String sellingPrice) {
            String updateItemQuery = "UPDATE inventory SET itemName = '" + itemName + "', description = '" + description + "', type = '" + type + "', purchasePrice = '" + purchasePrice + "', unitPrice = '" + sellingPrice + "' WHERE barcode_ID = '" + barcodeID + "'";
            return updateItemQuery;
        }

        public String insertUser(String firstName, String otherNames,
                String username, String password, String email, String contact,
                String designation, String createdBy) {
            String insertUserQuery = "INSERT INTO users (firstName, otherNames, username, password, email, contact, isAdmin, createdBy) VALUES ('" + firstName + "', '" + otherNames + "', '" + username + "', '" + password + "', '" + email + "', '" + contact + "', '" + designation + "', '" + createdBy + "')";
            return insertUserQuery;
        }

        public String updateUser(String firstName, String otherNames,
                String username, String password, String email, String contact,
                String designation) {
            String updateUserQuery = "UPDATE users SET (firstName = '" + firstName + "', otherNames = '" + otherNames + "', username  = '" + username + "', password  = '" + password + "', email  = '" + email + "', contact  = '" + contact + "',  = '" + designation + "')";
            return updateUserQuery;
        }

        public String insertSale(String barcodeID, String itemName,
                String description, String type, String quantity,
                String unitPrice, String totalVal) {
            String insertSaleQuery = "INSERT INTO sales (barcodeID, itemName, description, type, quantity, unitPrice, totalAmount) VALUES ('" + barcodeID + "', '" + itemName + "', '" + description + "', '" + type + "', '" + quantity + "', '" + unitPrice + "', '" + totalVal + "')";
            return insertSaleQuery;
        }
    }

    public class receipts {

        public String receiptBuilder(ArrayList<ArrayList<String>> receiptRows,
                String totalAmount, String paidAmount, String change) throws
                ClassNotFoundException {
            merakiBusinessDBClass merakiDBObj = new merakiBusinessDBClass();
            ArrayList<ArrayList<String>> businessDetail = merakiDBObj.processSQLGeneralSelect(
                    "SELECT * FROM businessdetails");
            ArrayList<ArrayList<String>> receiptNumbers = merakiDBObj.processSQLGeneralSelect(
                    "SELECT * FROM receiptnumbers ORDER BY receiptId DESC LIMIT 1");

            TextArea builtRows = new TextArea();
            Label receiptnumber = new Label();
            if (receiptNumbers.isEmpty()) {
                receiptnumber.setText("1");
            } else {
                receiptnumber.setText(
                        (parseInt(receiptNumbers.get(0).get(1)) + 1) + "");
            }

            merakiDBObj.processSQLInsert(
                    "INSERT INTO receiptnumbers (receiptnumber) VALUES ('" + (parseInt(
                            receiptnumber.getText())) + "')");

            for (ArrayList<String> receiptRow : receiptRows) {
                String builtRowsTemp = "		  <tr class=\"item-row\">"
                        + "<td class=\"item-name\"><label>" + receiptRow.get(0).toLowerCase() + "</label></div></td>"
                        + "<td class=\"item-qty\"><label>" + receiptRow.get(1).toLowerCase() + "</label></td>"
                        + "<td class=\"item-cost\"><label>" + receiptRow.get(2) + "0/=" + "</label></td>"
                        + "<td class=\"item-price\"><label>" + receiptRow.get(3) + "</label></span></td>"
                        + "</tr>";
                builtRows.appendText(builtRowsTemp);
            }
            Calendar cal = Calendar.getInstance();
            String htmlReceiptTemplate = "<html>"
                    + "<style>"
                    + "* { margin: 0; padding: 0; }"
                    + "body { font: 14px/1.4 Georgia, serif; width: 174px;}"
                    + "#page-wrap { width: 170px; margin: 0 auto; }"
                    + "textarea { border: 0; font: 10px Georgia, Serif; overflow: hidden; resize: none; }"
                    + "table { border-collapse: collapse; }"
                    + "table td, table th { border: 1px solid black; padding: 5px; }"
                    + "#addressParagraph {text-align: center; font-size: 9px}"
                    + "#divContacts {margin: 0 auto; width: 100%;}"
                    + "#header { height: 25px; width: 100%; margin: 20px 0; background: #222; alignment: top_center; text-align: CENTER; color: white; font: bold 14px Helvetica, Sans-Serif; text-decoration: uppercase; letter-spacing: 20px; padding: 8px 0px; }"
                    + "#address { width: 100%; height: 10px; }"
                    + "#customer { overflow: hidden; }"
                    + "#customer-title { font-size: 10px; font-weight: bold; float: left; }"
                    + "#meta { margin-top: 1px; width: 170px; float: left; }"
                    + "#meta td { text-align: right; font-size: 9px }"
                    + "#meta td.meta-head { text-align: left; background: #eee; font-size: 9px}"
                    + "#meta td textarea { width: 100%; height: 20px; text-align: right; font-size: 9px}"
                    + "#items { clear: both; width: 170px; margin: 2px 0 0 0; font-size: 9px; border: 1px solid black; table-layout:fixed; word-wrap:break-word;}"
                    + "#items th { background: #eee; }"
                    + "#items textarea { width: 40px; height: 30px; word-wrap:break-word;}"
                    + "#items tr.item-row td { border: 0; vertical-align: top; font-size: 10px; }"
                    + "#items td.item-name { width: 40px; font-size: 13px; word-wrap:break-word;}"
                    + "#items td.item-qty { width: 30px; font-size: 13px; word-wrap:break-word;}"
                    + "#items td.item-cost { width: 50px; font-size: 13px; word-wrap:break-word;}"
                    + "#items td.item-price { width: 50px; font-size: 13px; word-wrap:break-word;}"
                    + "#items td.total-line { border-right: 0; text-align: right; }"
                    + "#items td.total-value { border-left: 0; padding: 5px; }"
                    + "#items td.total-value textarea { height: 13px; background: none; }"
                    + "#items td.balance { background: #eee; }"
                    + "#items td.blank { border: 0; }"
                    + "#terms { text-align: center; margin: 5px 0 0 0; }"
                    + "#terms h5 { text-transform: uppercase; font: 12px Helvetica, Sans-Serif; letter-spacing: 3px; border-bottom: 1px solid black; padding: 0 0 8px 0; margin: 0 0 8px 0; }"
                    + "#terms textarea { width: 170px; text-align: center;}"
                    + "</style>"
                    //header
                    + "<head>"
                    + "	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
                    + "	<title>Receipt</title>"
                    + "</head>"
                    + "<body>"
                    + "	<div id=\"page-wrap\">"
                    + "		<textarea id=\"header\">CASH SALES</textarea>"
                    + "		"
                    + "		<div id=\"identity\">"
                    //configure business address
                    + "<div id = \"divContacts\">"
                    + "            <p id = \"addressParagraph\"><span id=\"address\"><b>" + businessDetail.get(
                            0).get(1) + " </b></span></p>"
                    //                    + "            <p id = \"addressParagraph\"><span id=\"address\" > Dealers in: Solar Panels, Water Heaters, Solar Fans,</span></p>"
                    //                    + "            <p id = \"addressParagraph\"><span id=\"address\" >Solar Iron Boxes, Solar TVs, Fridges etc</span></p>"
                    + "            <p id = \"addressParagraph\"><span id=\"address\" >Tel: " + businessDetail.get(0).get(
                            4) + "</span></p>"
                    + "            <p id = \"addressParagraph\"><span> Reciept No: <u style = \"border-bottom: 1px dashed #000000; text-decoration: none; \">" + (parseInt(
                            receiptnumber.getText())) + "</u></span></p>"
                    + "            <p id = \"addressParagraph\"><span> Date: <u style = \"border-bottom: 1px dashed #000000; text-decoration: none; \">" + (((LocalDateTime.now() + "")).replaceAll("T", " ")).substring(0, 11) + "</u></span></p>"
                    + "            <p id = \"addressParagraph\"><span> Located At: Ndejje</span></p><br>"
                    //+ "            <p id = \"addressParagraph\"><span>Mr/Ms. <u style = \"border-bottom: 1px dashed #000000; text-decoration: none; \"> Mukisa Joseph</u></span></p>"
                    + "</div><br>"
                    //end of config

                    //configure the items table
                    + "<table id=\"items\">"
                    + "		  <tr>"
                    + "		      <th>NAME</th>"
                    + "		      <th>QTY</th>"
                    + "		      <th>RATE</th>"
                    + "		      <th>AMT</th>"
                    + "		  </tr>"
                    //here we configure the table data

                    + builtRows.getText()
                    + "		  </tr>"
                    + "</table>"
                    //end of the tabel configuration

                    + "<br>"
                    //configuring totals and balance
                    //configuring totals
                    + "<table id=\"items\">"
                    + "		  <tr>"
                    + "		      <td class=\"total-line\">Total:</td>"
                    + "		      <td class=\"total-value\"><div id=\"total\">" + totalAmount + "</div></td>"
                    + "		  </tr>"
                    //configurong paid amount
                    + "		  <tr>"
                    + "		      <td class=\"total-line balance\">PaidAmount:</td>"
                    + "		      <td class=\"total-value balance\"><div class=\"due\">" + paidAmount + "</div></td>"
                    + "		  </tr>"
                    //configuring balance
                    + "		  <tr>"
                    + "		      <td class=\"total-line balance\">Balance:</td>"
                    + "		      <td class=\"total-value balance\"><div class=\"due\">" + change + "</div></td>"
                    + "		  </tr>"
                    + "		</table>"
                    //end of configuration

                    //thank you message
                    + "		<div id=\"terms\">"
                    + "		  <h5>Thank You</h5>"
                    + "		  <p id = \"addressParagraph\"><span>Come again please!</span></p>"
                    + "		</div>"
                    + "	</div>"
                    + "</body>"
                    + "</html>";

            Document doc = Jsoup.parse(htmlReceiptTemplate);
            return doc.toString();
        }

    }

//////---%placing the yvalue onto chart 
    public class chartYVal {

        private void displayLabelForData(XYChart.Data<String, Number> data) {
            final Node node = data.getNode();
            final Text dataText = new Text(data.getYValue() + "");
            node.parentProperty().addListener(
                    (ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) -> {
                        Group parentGroup = (Group) parent;
                        parentGroup.getChildren().add(dataText);
                    });

            node.boundsInParentProperty().addListener(
                    (ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) -> {
                        dataText.setLayoutX(
                                Math.round(
                                        bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(
                                        -1) / 2
                                )
                        );
                        dataText.setLayoutY(
                                Math.round(
                                        bounds.getMinY() - dataText.prefHeight(
                                        -1) * 0.5
                                )
                        );
                    });
        }
    }

/////User Idle Monitor
    public class IdleMonitor {

        private final Timeline idleTimeline;

        private final EventHandler<Event> userEventHandler;

        public IdleMonitor(Duration idleTime, Runnable notifier,
                boolean startMonitoring) {
            idleTimeline = new Timeline(new KeyFrame(idleTime,
                    e -> notifier.run()));
            idleTimeline.setCycleCount(Animation.INDEFINITE);

            userEventHandler = e -> notIdle();

            if (startMonitoring) {
                startMonitoring();
            }
        }

        public IdleMonitor(Duration idleTime, Runnable notifier) {
            this(idleTime, notifier, false);
        }

        public void register(Scene scene, EventType<? extends Event> eventType) {
            scene.addEventFilter(eventType, userEventHandler);
        }

        public void register(Node node, EventType<? extends Event> eventType) {
            node.addEventFilter(eventType, userEventHandler);
        }

        public void unregister(Scene scene, EventType<? extends Event> eventType) {
            scene.removeEventFilter(eventType, userEventHandler);
        }

        public void unregister(Node node, EventType<? extends Event> eventType) {
            node.removeEventFilter(eventType, userEventHandler);
        }

        public void notIdle() {
            if (idleTimeline.getStatus() == Animation.Status.RUNNING) {
                idleTimeline.playFromStart();
            }
        }

        public void startMonitoring() {
            idleTimeline.playFromStart();
        }

        public void stopMonitoring() {
            idleTimeline.stop();
        }
    }

/////---%thread classes for updating UIs and doing other things
    public class monitorInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        chartYVal chartYVal = new chartYVal();
        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<InventoryItemsData> InventoryItemsItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0;");
            for (ArrayList<String> selectInventoryItem : selectInventoryItems) {
                InventoryItemsItems.add(new InventoryItemsData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(6), selectInventoryItem.get(7),
                        selectInventoryItem.get(8), selectInventoryItem.get(9)));
            }

            for (int x = 0; x < InventoryItemsItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();
                oneProduct.add(InventoryItemsItems.get(x).getBarcode_ID());
                oneProduct.add(InventoryItemsItems.get(x).getName());
                oneProduct.add(InventoryItemsItems.get(x).getDescription());
                oneProduct.add(InventoryItemsItems.get(x).getUnits());
                results.add(oneProduct);
            }
            return results;
        }

    }

    public class monitorWholeSellInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        chartYVal chartYVal = new chartYVal();
        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<WholeSaleInventoryData> WholeSaleInventoryItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE (wholeSellPrice > 0.00 AND minWSQty > 0)");
            for (ArrayList<String> selectInventoryItem : selectInventoryItems) {
                WholeSaleInventoryItems.add(new WholeSaleInventoryData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(6), selectInventoryItem.get(14),
                        selectInventoryItem.get(13)));
            }

            for (int x = 0; x < WholeSaleInventoryItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();
                oneProduct.add(WholeSaleInventoryItems.get(x).getBarcode_ID());
                oneProduct.add(WholeSaleInventoryItems.get(x).getItemName());
                oneProduct.add(WholeSaleInventoryItems.get(x).getDescription());
                oneProduct.add(WholeSaleInventoryItems.get(x).getType());
                oneProduct.add(WholeSaleInventoryItems.get(x).getRemainingQty());
                oneProduct.add(WholeSaleInventoryItems.get(x).getMinWSQty());
                oneProduct.add(WholeSaleInventoryItems.get(x).getUnitPrice());
                results.add(oneProduct);
            }
            return results;
        }

    }

    public class monitorPacketInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<PacketInventoryData> PacketInventoryItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT barcode_ID as barcode, itemName as name, description, type, ROUND(SUM(remainingQuantity), 0)"
                    + " as remQty, unitPrice as sPrice FROM inventory WHERE remainingQuantity > 0"
                    + " GROUP BY barcode_ID, itemName, description, type, purchasePrice, unitPrice ORDER BY itemName ASC;");
            selectInventoryItems.forEach((selectInventoryItem) -> {
                PacketInventoryItems.add(new PacketInventoryData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(5), selectInventoryItem.get(6)));
            });

            for (int x = 0; x < PacketInventoryItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();

                oneProduct.add(PacketInventoryItems.get(x).getBarcode_ID());
                oneProduct.add(PacketInventoryItems.get(x).getItemName());
                oneProduct.add(PacketInventoryItems.get(x).getDescription());
                oneProduct.add(PacketInventoryItems.get(x).getType());
                oneProduct.add(
                        PacketInventoryItems.get(x).getRemainingQuantity());
                oneProduct.add(PacketInventoryItems.get(x).getUnitPrice());
                results.add(oneProduct);
            }
            return results;
        }

    }

    public class monitorWholePacketInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<WholeSellConfigData> WholeSellConfigItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0");
            for (ArrayList<String> selectInventoryItem : selectInventoryItems) {
                WholeSellConfigItems.add(new WholeSellConfigData(
                        selectInventoryItem.get(1), selectInventoryItem.get(2),
                        selectInventoryItem.get(3), selectInventoryItem.get(4),
                        selectInventoryItem.get(14), selectInventoryItem.get(9),
                        selectInventoryItem.get(13)));
            }

            for (int x = 0; x < WholeSellConfigItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();

                oneProduct.add(WholeSellConfigItems.get(x).getBarcode_ID());
                oneProduct.add(WholeSellConfigItems.get(x).getItemName());
                oneProduct.add(WholeSellConfigItems.get(x).getDescription());
                oneProduct.add(WholeSellConfigItems.get(x).getType());
                oneProduct.add(WholeSellConfigItems.get(x).getMinimunQuantity());
                oneProduct.add(WholeSellConfigItems.get(x).getPurchasePrice());
                oneProduct.add(WholeSellConfigItems.get(x).getWholeSellPrice());
                results.add(oneProduct);
            }
            return results;
        }

    }

    public class monitorRetailInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<RetailInventoryData> RetailInventoryItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();
            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLGeneralSelect(
                    "SELECT a.inventory_itemId,  b.barcode_ID as barcode, b.itemName as name, b.description, b.type, a.packetQty, "
                    + "ROUND(SUM(a.remainingQty), 0) as remQty, a.unitPrice FROM retialinventory "
                    + "AS a LEFT JOIN inventory AS b ON a.inventory_itemId = b.itemId  WHERE a.remainingQty > 0 "
                    + "GROUP BY a.inventory_itemId, b.barcode_ID, b.itemName, b.description, b.type, a.packetQty, a.unitPrice, a.retailedBy "
                    + "ORDER BY b.itemName ASC;");

            selectInventoryItems.forEach((selectInventoryItem) -> {
                RetailInventoryItems.add(new RetailInventoryData(
                        selectInventoryItem.get(0), selectInventoryItem.get(1),
                        selectInventoryItem.get(2), selectInventoryItem.get(3),
                        selectInventoryItem.get(4), selectInventoryItem.get(5),
                        selectInventoryItem.get(6), selectInventoryItem.get(7)));
            });

            RetailInventoryItems.stream().map((RetailInventoryItem) -> {
                ArrayList<String> oneProduct = new ArrayList<>();
                oneProduct.add(RetailInventoryItem.getId());
                oneProduct.add(RetailInventoryItem.getBarcode_ID());
                oneProduct.add(RetailInventoryItem.getItemName());
                oneProduct.add(RetailInventoryItem.getDescription());
                oneProduct.add(RetailInventoryItem.getType());
                oneProduct.add(RetailInventoryItem.getPacketQty());
                oneProduct.add(RetailInventoryItem.getRemainingQty());
                oneProduct.add(RetailInventoryItem.getUnitPrice());
                return oneProduct;
            }).forEachOrdered((oneProduct) -> {
                results.add(oneProduct);
            });
            return results;
        }

    }

    public class monitorSalesTask extends Task<ArrayList<ArrayList<String>>> {

        chartYVal chartYVal = new chartYVal();
        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<ItemSalesData> ItemSalesItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLGeneralSelect(
                    "SELECT * FROM sales");
            for (ArrayList<String> selectInventoryItem : selectInventoryItems) {
                switch (selectInventoryItem.get(9)) {
                    case "1": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "PACKET SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                    case "2": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "RETAIL SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                    case "3": {
                        String userWhoSold = merakiBusinessDBClass.processSQLGeneralSelect(
                                "SELECT username FROM users WHERE userId = '" + selectInventoryItem.get(
                                        11) + "'").get(0).get(0);
                        double totalCost = parseDouble(selectInventoryItem.get(
                                10)) * parseInt(selectInventoryItem.get(5));
                        String profit = (parseDouble(selectInventoryItem.get(7)) - totalCost) + "";
                        ItemSalesItems.add(new ItemSalesData(
                                selectInventoryItem.get(1),
                                selectInventoryItem.get(2),
                                selectInventoryItem.get(3),
                                selectInventoryItem.get(4), "WHOLESELL SALES",
                                selectInventoryItem.get(5),
                                selectInventoryItem.get(6),
                                selectInventoryItem.get(7), profit, userWhoSold,
                                selectInventoryItem.get(8)));
                        break;
                    }
                }

            }

            for (int x = 0; x < ItemSalesItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();
                oneProduct.add(ItemSalesItems.get(x).getBarcode_ID());
                oneProduct.add(ItemSalesItems.get(x).getItemName());
                oneProduct.add(ItemSalesItems.get(x).getDescription());
                oneProduct.add(ItemSalesItems.get(x).getType());
                oneProduct.add(ItemSalesItems.get(x).getSaleType());
                oneProduct.add(ItemSalesItems.get(x).getQuantity());
                oneProduct.add(ItemSalesItems.get(x).getUnitPrice());
                oneProduct.add(ItemSalesItems.get(x).getTotalValue());
                oneProduct.add(ItemSalesItems.get(x).getProfit());
                oneProduct.add(ItemSalesItems.get(x).getSoldBy());
                oneProduct.add(ItemSalesItems.get(x).getSoldOn());
                results.add(oneProduct);
            }
            return results;
        }

    }

    public class selectAllInventoryTask extends Task<ArrayList<ArrayList<String>>> {

        chartYVal chartYVal = new chartYVal();
        merakiBusinessDBClass merakiBusinessDBClass = new merakiBusinessDBClass();
        ObservableList<InventoryGenDataData> ItemSalesItems = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> results;
        ArrayList<ArrayList<String>> resultsCopy = new ArrayList<>();

        @Override
        protected ArrayList<ArrayList<String>> call() throws Exception {
            results = new ArrayList<>();

            ArrayList<ArrayList<String>> selectInventoryItems = merakiBusinessDBClass.processSQLGeneralSelect(
                    "SELECT * FROM inventory WHERE remainingQuantity > 0");
            for (ArrayList<String> selectInventoryItem : selectInventoryItems) {
                ItemSalesItems.add(new InventoryGenDataData(
                        selectInventoryItem.get(0), selectInventoryItem.get(1),
                        selectInventoryItem.get(2), selectInventoryItem.get(3),
                        selectInventoryItem.get(4), selectInventoryItem.get(5),
                        selectInventoryItem.get(6), selectInventoryItem.get(7),
                        selectInventoryItem.get(8), selectInventoryItem.get(9),
                        selectInventoryItem.get(10), selectInventoryItem.get(11),
                        selectInventoryItem.get(12)));
            }

            for (int x = 0; x < ItemSalesItems.size(); x++) {
                ArrayList<String> oneProduct = new ArrayList<>();

                oneProduct.add(ItemSalesItems.get(x).getBarcode_ID());
                oneProduct.add(ItemSalesItems.get(x).getItemName());
                oneProduct.add(ItemSalesItems.get(x).getDescription());
                oneProduct.add(ItemSalesItems.get(x).getType());
                oneProduct.add(ItemSalesItems.get(x).getInputQuantity());
                oneProduct.add(ItemSalesItems.get(x).getRemainingQuantity());
                oneProduct.add(ItemSalesItems.get(x).getUnitQuantity());
                oneProduct.add(ItemSalesItems.get(x).getPurchasePrice());
                oneProduct.add(ItemSalesItems.get(x).getUnitPrice());
                oneProduct.add(ItemSalesItems.get(x).getCreatedBy());
                oneProduct.add(ItemSalesItems.get(x).getCreatedOn());
                oneProduct.add(ItemSalesItems.get(x).getFullyConsumedOn());
                oneProduct.add(ItemSalesItems.get(x).getItemId());
                results.add(oneProduct);
            }
            return results;
        }

    }

//////---%$TextField values constrainers
    public class PhoneNumberTextField extends TextField {

        public PhoneNumberTextField() {
            textProperty().addListener(
                    (observableValue, oldValue, newValue) -> {
                        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                        try {
                            PhoneNumber number = phoneUtil.parse(newValue, "US");
                            this.setText(phoneUtil.format(number,
                                    PhoneNumberFormat.NATIONAL));
                        } catch (NumberParseException e) {

                        }
                    });
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {

            return text.matches("[0-9]*");

        }

        private String getPhoneNumber() {
            return this.getText().replaceAll("(", "").replaceAll(")", "").replaceAll(
                    "-", "").replaceAll(" ", "");
        }
    }

    public class UpperCaseTextField extends TextField {

        @Override
        public void replaceText(int start, int end, String text) {
            if (validate(text)) {
                super.replaceText(start, end, text.toUpperCase());
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {

            return text.matches("[a-z]*[A-Z]*[0-9]*[-]*[/]*[_]*[.]*[ ]*");

        }
    }

    public class LowerCaseTextField extends TextField {

        @Override
        public void replaceText(int start, int end, String text) {
            if (validate(text)) {
                super.replaceText(start, end, text.toLowerCase());
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {

            return text.matches("[a-z]*[0-9]*[-]*[/]*[_]*[.]*[ ]*");

        }
    }

    public class DecimalTextField extends TextField {

        public DecimalTextField() {
            super();

            textProperty().addListener((observable, oldValue, newValue) -> {
                if (validate(newValue) && !this.getText().isEmpty()) {
                    this.setText(
                            NumberFormat.getNumberInstance(Locale.US).format(
                                    parseDouble(
                                            newValue.replaceAll(",", "").replaceAll(
                                                    "/=", ""))));
                }

            });

        }

        public String getDecimal() {
            return this.getText().replaceAll(",", "").replaceAll("", "");
        }

        private boolean validate(String text) {

            return text.replaceAll(",", "").replaceAll("/=", "").matches(
                    "[0-9]*");

        }
    }

//////username and password encryption class
    public class CryptoUtil {

        private final Provider sunJCE = new com.sun.crypto.provider.SunJCE();
        private final String algorithm = "Blowfish";

        public CryptoUtil() {
            Security.addProvider(sunJCE);
        }

        public String generateKey() throws NoSuchAlgorithmException {
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);

            // Setup the random class and associate with key generator.
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            kgen.init(random);

            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();

            return new String(raw);
        }

        public String encrypt(String data, String key) {
            String retVal = null;

            try {
                SecretKeySpec skeySpec = getSecretKeySpec(key);

                // Instantiate the cipher.
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

                //byte[] encrypted = cipher.doFinal( URLEncoder.encode(data).getBytes() ); this line Base64.getEncoder().encode is imporant to prevent badpadding errors... also check in the decrypt method
                byte[] encrypted = cipher.doFinal(data.getBytes());
                retVal = new String(Base64.getEncoder().encode(encrypted));
            } catch (Exception ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        "Exception in CryptoUtil.decrypt(): " + ex,
                        ButtonType.OK);
                exceptionAlert.showAndWait();
                retVal = null;
            } finally {
                return retVal;
            }
        }

        public String decrypt(String data, String key) {
            String retVal = null;

            try {
                SecretKeySpec skeySpec = getSecretKeySpec(key);

                // Instantiate the cipher.
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);

                //byte[] decrypted = cipher.doFinal( URLDecoder.decode(data).getBytes() );
                byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(
                        data.getBytes()));
                retVal = new String(decrypted, "UTF-8");
            } catch (Exception ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        "Exception in CryptoUtil.decrypt(): " + ex,
                        ButtonType.OK);
                exceptionAlert.showAndWait();

                retVal = null;
            } finally {
                return retVal;
            }
        }

        private SecretKeySpec getSecretKeySpec(String key) throws Exception {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), algorithm);

            return skeySpec;
        }
    }

//////---%$database class
    public class merakiBusinessDBClass {

        Connection connection;
        Statement statement;
        String driver;
        String url;
        String username;
        String password;
        ArrayList<String> selectQRTemp;
        ArrayList<ArrayList<String>> selectQResults;
        ArrayList<ArrayList<String>> outPutArrayList;

        public void createDataBase() throws SQLException {
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false";
            username = "root";
            password = "Ark2016!?veron";

            connection = DriverManager.getConnection(url, username, password);

            statement = connection.createStatement();

            statement.addBatch(
                    "SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0");
            statement.addBatch(
                    "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0");
            statement.addBatch(
                    "SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'");
            statement.addBatch(
                    "CREATE SCHEMA IF NOT EXISTS `merakiBusinessDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
            statement.addBatch("USE `merakiBusinessDB`");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`accessLog` ("
                    + "  `accessId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "  `userId` INT UNSIGNED NULL,"
                    + "  `logOnTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "  `logOffTime` TIMESTAMP NULL,"
                    + "  PRIMARY KEY (`accessId`))"
                    + "ENGINE = InnoDB");

            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`businessDetails` ("
                    + "`detailId` INT NOT NULL AUTO_INCREMENT,"
                    + "`businessName` VARCHAR(200) NOT NULL,"
                    + "`primaryContact` VARCHAR(200) NOT NULL,"
                    + "`email` VARCHAR(200) NOT NULL,"
                    + "`contact` VARCHAR(45) NULL,"
                    + "`registrationStatus` INT NULL DEFAULT 1,"
                    + "`registrationDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "PRIMARY KEY (`detailId`))"
                    + "ENGINE = InnoDB");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`users` ("
                    + "`userId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`firstName` VARCHAR(200) NOT NULL,"
                    + "`otherNames` VARCHAR(200) NULL,"
                    + "`username` VARCHAR(200) NOT NULL,"
                    + "`password` TEXT NULL,"
                    + "`email` VARCHAR(200) NOT NULL,"
                    + "`contact` VARCHAR(45) NULL,"
                    + "`status` INT NOT NULL DEFAULT 1,"
                    + "`isAdmin` INT NULL DEFAULT 0,"
                    + "`createdBy` VARCHAR(200) NOT NULL,"
                    + "`createdOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`invalidatedOn` NVARCHAR(45) NULL,"
                    + "`businessDetails_detailId` INT NOT NULL,"
                    + "`isCreator` INT NULL DEFAULT 0,"
                    + "PRIMARY KEY (`userId`, `businessDetails_detailId`),"
                    + "INDEX `fk_users_businessDetails1_idx` (`businessDetails_detailId` ASC),"
                    + "CONSTRAINT `fk_users_businessDetails1`"
                    + "  FOREIGN KEY (`businessDetails_detailId`)"
                    + "  REFERENCES `merakiBusinessDB`.`businessDetails` (`detailId`)"
                    + "  ON DELETE NO ACTION"
                    + "  ON UPDATE NO ACTION)"
                    + "ENGINE = InnoDB");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`licenses` ("
                    + "`licenseId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`license` VARCHAR(200) NOT NULL,"
                    + "`status` INT NULL DEFAULT 1,"
                    + "`business` VARCHAR(200) NULL,"
                    + "`consumeDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`expiryDate` TIMESTAMP NULL,"
                    + "`businessDetails_detailId` INT NOT NULL,"
                    + "PRIMARY KEY (`licenseId`, `businessDetails_detailId`),"
                    + "INDEX `fk_licenses_businessDetails1_idx` (`businessDetails_detailId` ASC),"
                    + "CONSTRAINT `fk_licenses_businessDetails1`"
                    + "  FOREIGN KEY (`businessDetails_detailId`)"
                    + "  REFERENCES `merakiBusinessDB`.`businessDetails` (`detailId`)"
                    + "  ON DELETE NO ACTION"
                    + "  ON UPDATE NO ACTION)"
                    + "ENGINE = InnoDB");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`inventory` ("
                    + "`itemId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`barcode_ID` VARCHAR(200) NOT NULL,"
                    + "`itemName` VARCHAR(200) NOT NULL,"
                    + "`description` TEXT NULL,"
                    + "`type` VARCHAR(200) NULL,"
                    + "`inputQuantity` INT NULL,"
                    + "`remainingQuantity` INT NULL,"
                    + "`unitQuantity` INT NULL,"
                    + "`purchasePrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`unitPrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`createdBy` VARCHAR(200) NULL,"
                    + "`createdOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`fullyConsumedOn` TIMESTAMP NULL,"
                    + "`wholeSellPrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`minWSQty` INT NULL,"
                    + "PRIMARY KEY (`itemId`))"
                    + "ENGINE = InnoDB");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`retialInventory` ("
                    + "`retialId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`packetQty` INT NOT NULL,"
                    + "`remainingQty` INT NOT NULL,"
                    + "`purchasePrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`unitPrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`packetValue` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`retailedBy` VARCHAR(200) NOT NULL,"
                    + "`retailedOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`inventory_itemId` BIGINT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (`retialId`, `inventory_itemId`),"
                    + "INDEX `fk_retialInventory_inventory_idx` (`inventory_itemId` ASC),"
                    + "CONSTRAINT `fk_retialInventory_inventory`"
                    + "  FOREIGN KEY (`inventory_itemId`)"
                    + "  REFERENCES `merakiBusinessDB`.`inventory` (`itemId`)"
                    + "  ON DELETE NO ACTION"
                    + "  ON UPDATE NO ACTION)"
                    + "ENGINE = InnoDB");

            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`sales` ("
                    + "`saleId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`barcodeID` VARCHAR(200) NULL,"
                    + "`itemName` VARCHAR(200) NULL,"
                    + "`description` TEXT NULL,"
                    + "`type` VARCHAR(200) NULL,"
                    + "`quantity` INT NULL DEFAULT 0,"
                    + "`unitPrice` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`totalAmount` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`saleDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`saleType` INT NOT NULL,"
                    + "`purchasePrice` DECIMAL(20,2) NULL DEFAULT 0.00 ,"
                    + "`users_userId` BIGINT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (`saleId`, `users_userId`),"
                    + "INDEX `fk_sales_users1_idx` (`users_userId` ASC),"
                    + "CONSTRAINT `fk_sales_users1`"
                    + "  FOREIGN KEY (`users_userId`)"
                    + "  REFERENCES `merakiBusinessDB`.`users` (`userId`)"
                    + "  ON DELETE NO ACTION ON UPDATE NO ACTION)"
                    + "ENGINE = InnoDB");
            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakiBusinessDB`.`expenses` ("
                    + "`expenseId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`productService` VARCHAR(200) NOT NULL,"
                    + "`rate` INT NULL,"
                    + "`quantity` INT NULL,"
                    + "`paidTo` VARCHAR(200) NULL,"
                    + "`dueDate` TIMESTAMP NULL,"
                    + "`unitPayment` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`totalValue` DECIMAL(20,2) NULL DEFAULT 0.00,"
                    + "`createdOn` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "`users_userId` BIGINT UNSIGNED NOT NULL,"
                    + "PRIMARY KEY (`expenseId`, `users_userId`),"
                    + "CONSTRAINT `fk_expenses_users1`"
                    + "  FOREIGN KEY (`users_userId`)"
                    + "  REFERENCES `merakiBusinessDB`.`users` (`userId`)"
                    + "  ON DELETE NO ACTION"
                    + "  ON UPDATE NO ACTION)"
                    + "ENGINE = InnoDB");

            statement.addBatch(
                    "CREATE TABLE IF NOT EXISTS `merakibusinessdb`.`receiptnumbers` ("
                    + "`receiptId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "`receiptnumber` BIGINT UNSIGNED NULL DEFAULT 0,"
                    + "  PRIMARY KEY (`receiptId`),"
                    + "  UNIQUE INDEX `receiptnumber_UNIQUE` (`receiptnumber` ASC))"
                    + "ENGINE = InnoDB");
            statement.addBatch("SET SQL_MODE=@OLD_SQL_MODE");
            statement.addBatch("SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS");
            statement.addBatch("SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS");

            statement.executeBatch();
            connection.close();
        }

        //SQL select statement processor
        public ArrayList<ArrayList<String>> processSQLSelect(String sqlCommand) throws
                ClassNotFoundException {
            try {

                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost/merakiBusinessDB?autoReconnect=true&useSSL=false";
                username = "root";
                password = "Ark2016!?veron";

                selectQResults = new ArrayList<ArrayList<String>>();

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);

                //Get a new statement for the current connection
                statement = connection.createStatement();

                //Executing SQL command
                ResultSet resultSet = statement.executeQuery(sqlCommand);

                if (sqlCommand.contains("inventory")) {
                    while (resultSet.next()) {
                        selectQRTemp = new ArrayList<String>();
                        for (int x = 1;
                                x <= resultSet.getMetaData().getColumnCount();
                                x++) {
                            selectQRTemp.add(resultSet.getString(x));
                        }

                        if (!selectQResults.isEmpty()) {
                            for (int x = 0; x < selectQResults.size(); x++) {
                                String dbRowBCode = selectQResults.get(x).get(1).replaceAll(
                                        " ", "");
                                String alRowBCode = selectQRTemp.get(1).replaceAll(
                                        " ", "");
                                if (dbRowBCode.equals(alRowBCode)) {
                                    int total = parseInt(
                                            selectQResults.get(x).get(6)) + parseInt(
                                            selectQRTemp.get(6));
                                    selectQResults.get(x).set(6, total + "");
                                    break;
                                } else if (dbRowBCode != alRowBCode && x < selectQResults.size() - 1) {
                                    continue;
                                } else if (dbRowBCode != alRowBCode && x == selectQResults.size() - 1) {
                                    selectQResults.add(selectQRTemp);
                                    break;

                                }
                            }
                        } else if (selectQResults.isEmpty()) {
                            selectQResults.add(selectQRTemp);
                        }

                    }
                }
                connection.close();

            } catch (SQLException ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        ex + "", ButtonType.OK);
                exceptionAlert.showAndWait();
            }

            return selectQResults;
        }

        public ArrayList<ArrayList<String>> processSQLGeneralSelect(
                String sqlCommand) throws ClassNotFoundException {
            try {

                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost/merakiBusinessDB?autoReconnect=true&useSSL=false";
                username = "root";
                password = "Ark2016!?veron";

                selectQResults = new ArrayList<>();

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);

                //Get a new statement for the current connection
                statement = connection.createStatement();

                //Executing SQL command
                ResultSet resultSet = statement.executeQuery(sqlCommand);

                while (resultSet.next()) {
                    selectQRTemp = new ArrayList<>();
                    for (int x = 1;
                            x <= resultSet.getMetaData().getColumnCount(); x++) {
                        selectQRTemp.add(resultSet.getString(x));
                    }
                    selectQResults.add(selectQRTemp);

                }

                connection.close();
            } catch (SQLException ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        ex + "", ButtonType.OK);
                exceptionAlert.showAndWait();
            }

            return selectQResults;
        }

        public ArrayList<ArrayList<String>> processSQLRetailSelect(
                String sqlCommand) throws ClassNotFoundException, SQLException {
            try {

                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost/merakiBusinessDB?autoReconnect=true&useSSL=false";
                username = "root";
                password = "Ark2016!?veron";

                selectQResults = new ArrayList<>();

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);

                //Get a new statement for the current connection
                statement = connection.createStatement();

                //Executing SQL command
                ResultSet resultSet = statement.executeQuery(sqlCommand);

                while (resultSet.next()) {
                    selectQRTemp = new ArrayList<>();
                    for (int x = 1;
                            x <= resultSet.getMetaData().getColumnCount(); x++) {
                        selectQRTemp.add(resultSet.getString(x));

                    }

                    selectQResults.add(selectQRTemp);
                }

            } catch (SQLException ex) {
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        ex + "", ButtonType.OK);
                exceptionAlert.showAndWait();
            }
            for (int i = 0; i < selectQResults.size(); i++) {
                ResultSet resultSet1 = statement.executeQuery(
                        "SELECT barcode_ID FROM inventory WHERE itemId = '" + parseInt(
                                selectQResults.get(i).get(0)) + "'");

                while (resultSet1.next()) {
                    ArrayList<String> newSelectQRTemp = new ArrayList<>();
                    newSelectQRTemp.add(selectQResults.get(i).get(0));
                    newSelectQRTemp.add(selectQResults.get(i).get(1));

                    newSelectQRTemp.add(resultSet1.getString(1));
                    selectQResults.set(i, newSelectQRTemp);
                }

            }
            connection.close();
            return selectQResults;
        }

        public int processSQLInsert(String insertStatement) throws
                ClassNotFoundException {
            int status = 0;
            try {
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost/merakiBusinessDB?autoReconnect=true&useSSL=false";
                username = "root";
                password = "Ark2016!?veron";

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);

                //Get a new statement for the current connection
                statement = connection.createStatement();

                status = statement.executeUpdate(insertStatement);
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        ex + "", ButtonType.OK);
                exceptionAlert.showAndWait();
            }
            return status;
        }

        public int processSQLUpdate(String updateStatement) throws
                ClassNotFoundException {
            int status = 0;
            try {
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost/merakiBusinessDB?autoReconnect=true&useSSL=false";
                username = "root";
                password = "Ark2016!?veron";

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);

                //Get a new statement 
                statement = connection.createStatement();

                status = statement.executeUpdate(updateStatement);

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert exceptionAlert = new Alert(Alert.AlertType.WARNING,
                        ex + "", ButtonType.OK);
                exceptionAlert.showAndWait();
            }
            return status;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
