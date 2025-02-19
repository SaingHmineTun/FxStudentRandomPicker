package it.saimao;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static final String FONT_FAMILY = "impact";
    private final static String[] group1 = {"Zarm Sar", "Mork Korn Kham", "Kaung Leng", "Leng Aung Kao", "Zarm Phoung Kham", "Mo Seng Inn", "Mwe Fha Hsei", "Mawn Zarm", "Lao Ngein", "Kham Hleng"};
    private final static String[] group2 = {"Jao Ponnya", "Jao Kawliya", "Kham Lin", "Hseng Hom", "Mwe Zarm Lein", "Yown Yain"};

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Label : for showing student name
        Label lbName = new Label("click to pick");
        lbName.setFont(Font.font(FONT_FAMILY, 28));

        // Button : for picking random student
        Button btPick = new Button("PICK");
        btPick.setFont(Font.font(FONT_FAMILY, 18));


        // Radio Button : for choosing between group one and two
        RadioButton rbOne = new RadioButton("Group one");
        rbOne.setFont(Font.font(FONT_FAMILY, 14));
        rbOne.setSelected(true);
        RadioButton rbTwo = new RadioButton("Group two");
        rbTwo.setFont(Font.font(FONT_FAMILY, 14));
        ToggleGroup rg = new ToggleGroup();
        rg.getToggles().addAll(rbOne, rbTwo);

        // Event listeners
        Random rd = new Random();
        btPick.setOnAction(event -> {
            String studentName;
            if (rbOne.isSelected()) {
                int index = rd.nextInt(group1.length);
                studentName = group1[index];
            } else {
                int index = rd.nextInt(group2.length);
                studentName = group2[index];
            }

            String[] counts = {"ONE", "TWO", "THREE", ""};
            Timeline timeline = new Timeline();
            for (int i = 0; i < counts.length; i++) {
                final String num = counts[i];
                timeline.getKeyFrames().add(new KeyFrame(
                        Duration.seconds(i),
                        e ->
                                lbName.setText(num)));
            }
            timeline.setOnFinished(e -> lbName.setText(studentName));
            timeline.play();

        });


        HBox rgChoices = new HBox();
        rgChoices.setAlignment(Pos.CENTER);
        rgChoices.setSpacing(16);
        rgChoices.getChildren().addAll(rbOne, rbTwo);


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(24);
        vBox.setBackground(Background.fill(Paint.valueOf("#8298C2")));
        vBox.getChildren().addAll(lbName, btPick, rgChoices);

        Scene scene = new Scene(vBox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResource("/lms.png").toExternalForm()));
        primaryStage.setTitle("LMS Student Picker");
        primaryStage.show();
    }
}