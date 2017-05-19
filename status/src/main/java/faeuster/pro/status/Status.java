package faeuster.pro.status;

import java.io.File;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.jutils.jhardware.HardwareInfo;

import com.sun.javafx.binding.BidirectionalBinding;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Status {

	private Stage stage;
	private Scene aa;
	private BorderPane bp;
	private SimpleDoubleProperty aRam = new SimpleDoubleProperty();
	private SimpleDoubleProperty bRam = new SimpleDoubleProperty();
	private SimpleDoubleProperty a = new SimpleDoubleProperty();
	private HashMap<String, SimpleDoubleProperty> fileSystems = new HashMap<String, SimpleDoubleProperty>();
	private int counter = 0;

	public void init(Stage primaryStage) {

		stage = primaryStage;
		stage = new Stage();
		stage.setWidth(300);
		stage.setHeight(200);
		stage.getIcons().add(new Image("/NICCage.jpg"));
		bp = new BorderPane();
		Scene scene = new Scene(bp);
		aa = scene;
		Button transparenceButton = new Button();

		transparenceButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				if (stage.getOpacity() == 0.3) {
					stage.setOpacity(1);
				} else {
					stage.setOpacity(0.3);
				}
			}
		});
		bp.setRight(transparenceButton);
		VBox vBox = new VBox();
		vBox.getChildren().add(getRAMComponent());
		vBox.getChildren().add(getStorageComponent());
		bp.setCenter(vBox);
		stage.setScene(scene);

		bp.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		aa.setFill(null);
		Button button = new Button();
		bp.getChildren().add(button);
		stage.show();
	}

	public ViewItem getRAMComponent() {
		final ViewItem item = new ViewItem(new Label("RAM verfügbar"), new Label("RAM Total"), new TextField(),
				new TextField(), new ProgressBar());
		aRam.set(Double.valueOf(HardwareInfo.getMemoryInfo().getAvailableMemory()) / 1024);
		BidirectionalBinding.bind(item.getFreeTXT().textProperty(), aRam,
				new NumberStringConverter(NumberFormat.getIntegerInstance()));
		bRam.set(Double.valueOf(HardwareInfo.getMemoryInfo().getFullInfo().get("SystemDriverResidentBytes")) / 1024);
		BidirectionalBinding.bind(item.getTotalTXT().textProperty(), bRam,
				new NumberStringConverter(NumberFormat.getIntegerInstance()));

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				aRam.set(Double.valueOf(HardwareInfo.getMemoryInfo().getAvailableMemory()) / 1024);
				bRam.set(Double.valueOf(HardwareInfo.getMemoryInfo().getFullInfo().get("SystemDriverResidentBytes"))
						/ 1024);
				a.set((bRam.get() - aRam.get()) / bRam.get());
				Platform.runLater(new Runnable() {

					public void run() {
						item.getProgressBar().setProgress(a.get());
					}
				});
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, 500);

		if (a.get() == 0d) {
			a.set((bRam.get() - aRam.get()) / bRam.get());
		}
		item.getProgressBar().setProgress(a.get());
		// System.err.println("Prozessor: " +
		// HardwareInfo.getProcessorInfo().getFullInfo().get("LoadPercentage"));
		// System.err.println("Arbeitsspecherauslastung: " + (100 - ((a / b) *
		// 100)));
		//
		// }
		return item;
	}

	private Long mod(Long input) {
		if (input / 1024 > 0) {
			input = input / 1024;
			counter++;
			input = mod(input);
		} else {
			return input;
		}
		return input;

	}

	public ListView<ViewItem> getStorageComponent() {
		ListView<ViewItem> l = new ListView<ViewItem>();

		for (int ascii = 65; ascii <= 90; ascii++) {
			File f = new File((char) ascii + ":/");
			if (f.exists()) {
				ViewItem item = new ViewItem(new Label((char) ascii + " Verfügbar"), new Label((char) ascii + " Total"),
						new TextField(), new TextField(), new ProgressBar());
				counter = 0;
				Long mod = mod(f.getUsableSpace());
				System.err.println(counter);

				SimpleDoubleProperty free = new SimpleDoubleProperty(f.getUsableSpace() / 1024 / 1024 / 1024);
				fileSystems.put(f + "free", free);
				SimpleDoubleProperty total = new SimpleDoubleProperty(f.getTotalSpace() / 1024 / 1024 / 1024);
				fileSystems.put(f + "total", total);

				l.getItems().add(item);
			}
		}

		// DoubleProperty a = null;
		// DoubleProperty b = null;
		// SimpleDoubleProperty availableMemoryProperty = new
		// SimpleDoubleProperty(
		// Double.valueOf(HardwareInfo.getMemoryInfo().getAvailableMemory()) /
		// 1024);
		// a = (DoubleProperty)
		// DoubleProperty.doubleExpression(availableMemoryProperty);
		// BidirectionalBinding.bind(item.getFreeTXT().textProperty(), a, new
		// NumberStringConverter());
		// SimpleDoubleProperty totalMemoryProperty = new SimpleDoubleProperty(
		// Double.valueOf(HardwareInfo.getMemoryInfo().getFullInfo().get("SystemDriverResidentBytes"))
		// / 1024);
		// b = (DoubleProperty)
		// DoubleProperty.doubleExpression(totalMemoryProperty);
		// BidirectionalBinding.bind(item.getTotalTXT().textProperty(), b, new
		// NumberStringConverter());

		// System.err.println("Prozessor: " +
		// HardwareInfo.getProcessorInfo().getFullInfo().get("LoadPercentage"));
		// System.err.println("Arbeitsspecherauslastung: " + (100 - ((a / b) *
		// 100)));
		//
		// }

		return l;
	}

}
