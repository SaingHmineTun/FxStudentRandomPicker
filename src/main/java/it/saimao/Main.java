package it.saimao;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static it.saimao.StudentData.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static final String FONT_FAMILY = "Impact";
    private List<String> listPre1, listPre2, listInter;

    private Random random;

    private String pickStudent(List<String> list) {

        int index = random.nextInt(list.size());
        String studentName = list.get(index);
        list.remove(studentName);
        return studentName;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Label : for showing student name
        Label lbName = new Label("click to pick");
        lbName.setTextFill(Paint.valueOf("#333333"));
        lbName.setFont(Font.font(FONT_FAMILY, 26));

        // Button : for picking random student
        Button btPick = new Button("PICK");
        btPick.setFont(Font.font(FONT_FAMILY, 22));

        // Radio Button : for choosing between group one and two
        RadioButton rbInter = new RadioButton("Inter");
        rbInter.setFont(Font.font(FONT_FAMILY, 18));
        rbInter.setSelected(true);
        RadioButton rbPre1 = new RadioButton("Pre 1");
        rbPre1.setFont(Font.font(FONT_FAMILY, 18));
        RadioButton rbPre2 = new RadioButton("Pre 2");
        rbPre2.setFont(Font.font(FONT_FAMILY, 18));
        // ToggleGroup : make three radio buttons only one selectable
        ToggleGroup rg = new ToggleGroup();
        rg.getToggles().addAll(rbInter, rbPre1, rbPre2);

        // Event listeners
        random = new Random();
        btPick.setOnAction(event -> {
            lbName.setTextFill(Paint.valueOf("#333333"));
            String studentName;
            if (rbInter.isSelected()) {
                if (listInter == null || listInter.isEmpty())
                    listInter = new LinkedList<>(Arrays.asList(studentsInter));
                studentName = pickStudent(listInter);
            } else if (rbPre1.isSelected()) {
                if (listPre1 == null || listPre1.isEmpty())
                    listPre1 = new LinkedList<>(Arrays.asList(studentsPre1));
                studentName = pickStudent(listPre1);
            } else {
                if (listPre2 == null || listPre2.isEmpty())
                    listPre2 = new LinkedList<>(Arrays.asList(studentsPre2));
                studentName = pickStudent(listPre2);
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
            timeline.setOnFinished(e -> {
                lbName.setTextFill(Paint.valueOf("#781256"));
                lbName.setText(studentName);
            });
            timeline.play();
        });


        // HBox : for holding radio buttons
        HBox rgChoices = new HBox();
        rgChoices.setAlignment(Pos.CENTER);
        rgChoices.setSpacing(22);
        rgChoices.getChildren().addAll(rbInter, rbPre1, rbPre2);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        vBox.setBackground(Background.fill(Paint.valueOf("#8298C2")));
        vBox.getChildren().addAll(lbName, btPick, rgChoices);

        Scene scene = new Scene(vBox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResource("/lms.png").toExternalForm()));
        primaryStage.setTitle("LMS Student Picker");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}