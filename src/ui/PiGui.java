package ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.MonteCarlo;

public class PiGui {
		
    @FXML
    private Label points;
    @FXML
    private Label error;
    @FXML
    private Label calcPi;
    @FXML
    private Label time;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label piAvg;
    @FXML
    private Label errAvg;
    
    private BigDecimal piAvgBD = new BigDecimal(0.0);
	private BigDecimal errAvgBD = new BigDecimal(0.0);
	private BigDecimal reps = new BigDecimal(0.0);

    @FXML
    public void funct(ActionEvent event) throws IOException {
        long timeTaked = 0;
        for (int i = 100000; timeTaked < 5000; i = i + 100000) {
            long time1 = System.currentTimeMillis();
            MonteCarlo mc = new MonteCarlo(i);
            // calculate PI for a certain epsilon
            mc.calcEpsilon();
            long time2 = System.currentTimeMillis();
            timeTaked = time2 - time1;
            time.setText(timeTaked + "milis");
            int pointsT = mc.getIn()+mc.getOut();
            points.setText("" + pointsT);
            error.setText(mc.getError());
            calcPi.setText("" + mc.getPiCalc());
            piAvgBD = piAvgBD.add(mc.getPiCalc());
            errAvgBD = errAvgBD.add(mc.getErrAvg());
            reps = reps.add(BigDecimal.valueOf(1.0));
            piAvg.setText("" + getPiAvg());
            errAvg.setText("" + getErrAvg());
        } 
    }
    
    public BigDecimal getErrAvg() {
		if(errAvgBD.compareTo(BigDecimal.valueOf(0.0)) > 0 && reps.compareTo(BigDecimal.valueOf(0.0)) > 0) {
			return errAvgBD.divide(reps, new MathContext(6, RoundingMode.CEILING));
		}else {
			return BigDecimal.valueOf(0.0);
		}
	}
	
	public BigDecimal getPiAvg() {
		if(piAvgBD.compareTo(BigDecimal.valueOf(0.0)) > 0 && reps.compareTo(BigDecimal.valueOf(0.0)) > 0) {
			BigDecimal result = piAvgBD.divide(reps, new MathContext(6, RoundingMode.CEILING));
			return result;
		}else {
			return BigDecimal.valueOf(0.0);
		}
	}
}
