package faeuster.pro.status;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ViewItem extends HBox {

	private Label freeLBL = new Label();
	private Label totalLBL = new Label();

	private TextField freeTXT = new TextField();
	private TextField totalTXT = new TextField();

	private ProgressBar progressBar = new ProgressBar();

	public ViewItem(Label freeLBL, Label totalLBL, TextField freeTXT, TextField totalTXT, ProgressBar progressBar) {
		this.freeLBL = freeLBL;
		this.totalLBL = totalLBL;
		this.freeTXT = freeTXT;
		this.totalTXT = totalTXT;
		this.progressBar = progressBar;
		init();

	}

	private void init() {
		this.getChildren().add(freeLBL);
		this.getChildren().add(freeTXT);
		freeTXT.setEditable(false);
		this.getChildren().add(totalLBL);
		this.getChildren().add(totalTXT);
		totalTXT.setEditable(false);
		this.getChildren().add(progressBar);

	}

	public Label getFreeLBL() {
		return freeLBL;
	}

	public void setFreeLBL(Label freeLBL) {
		this.freeLBL = freeLBL;
	}

	public Label getTotalLBL() {
		return totalLBL;
	}

	public void setTotalLBL(Label totalLBL) {
		this.totalLBL = totalLBL;
	}

	public TextField getFreeTXT() {
		return freeTXT;
	}

	public void setFreeTXT(TextField freeTXT) {
		this.freeTXT = freeTXT;
	}

	public TextField getTotalTXT() {
		return totalTXT;
	}

	public void setTotalTXT(TextField totalTXT) {
		this.totalTXT = totalTXT;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}
