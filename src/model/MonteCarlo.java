package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MonteCarlo {
	private int in = 0;
	private int out = 0;
	// Pi with 24 digits
	private BigDecimal pi = new BigDecimal(3.141592653589793238462643);
	private BigDecimal piCalc = new BigDecimal(0.0);
	private BigDecimal epsilon = new BigDecimal(0.000001);
	private String errorS = "";
	private BigDecimal error = new BigDecimal(0.0);
	private int n;

	public MonteCarlo(int n) {
		this.n = n;
	}

	// get random value
	private double getRand() {
		Random rd = new Random();
		return rd.nextDouble();
	}

	// get amount of points inside the circle
	public int getIn() {
		return this.in;
	}

	// get amount of points outside the circle
	public int getOut() {
		return this.out;
	}
	
	public String getError() {
		return this.errorS;
	}

	// calculate PI
	public BigDecimal calcPi() {
		// multiply the result with 4 because the simulation was only
		BigDecimal piTmp = BigDecimal
				.valueOf(4.0 * ((double) this.getIn() / ((double) this.getIn() + (double) this.getOut())));
		return piTmp;
	}

	// get Pi with 24 digits
	private BigDecimal getPi() {
		return this.pi;
	}
	
	public BigDecimal getPiCalc() {
		return this.piCalc;
	}

	// reset values to ensure valid results
	private void resetValues() {
		this.in = 0;
		this.out = 0;
		this.piCalc = new BigDecimal(0.0);
		this.error = new BigDecimal(0.0);
	}

	// print the results to the console
	private void print(int n) {
		System.out.println("Calculation with: " + n);
		System.out.println("Points in: " + this.getIn() + ", points out: " + this.getOut());
		System.out.println("Pi calculated: " + this.calcPi() + ", In+out: " + (this.getOut() + this.getIn()));
		System.out.println(
				"Difference: Pi calculated (" + this.calcPi() + ") - Pi (" + this.pi.setScale(10, RoundingMode.CEILING)
						+ "): " + this.calcPi().subtract(this.getPi()).abs().setScale(10, RoundingMode.CEILING));
		System.out.println("--------------- " + "\n");
	}

	// compare error with epsilon
	private BigDecimal getError(BigDecimal currentPi) {
		// currently calculated PI minus PI with 24 digits
		BigDecimal diff = currentPi.subtract(this.pi).abs();
//		int error = diff.compareTo(this.epsilon);
		// error = 1: diff > epsilon
		// error = 0: diff = epsilon
		// error = -1: diff < epsilon
		return diff;
	}

	// calculate PI for a certain Epsilon
	public void calcEpsilon() {
		while (this.error.compareTo(epsilon) <= 0) {
			// initiate values
			this.resetValues();
			// compute until done is true
			for (int i = 0; i < n; i++) {
				double x = this.getRand();
				double y = this.getRand();
				if (x * x + y * y <= 1) {
					// point is inside the circle
					this.in++;
				} else {
					// point is outside the circle
					this.out++;
				}

				// calculate current PI
				this.piCalc = this.calcPi();
				// compare error with epsilon
				this.error = this.getError(this.piCalc);
			}
			errorS = "(" + this.pi.subtract(this.piCalc).abs().setScale(15, RoundingMode.CEILING) + ")";
			System.out.println("Error (" + this.pi.subtract(this.piCalc).abs().setScale(15, RoundingMode.CEILING)
					+ ") < epsilon (" + this.epsilon.setScale(10, RoundingMode.CEILING) + ")? ");
			print((int) (this.in + this.out));
		}
		
	}
	
	public BigDecimal getErrAvg() {
		return error;
	}
}
