/**
 * 
 */
package com.srandroid.main;

/**
 * Presenter for ActivityMain
 *
 */
public class PresenterMain {

	// View and Model
	private ActivityMain activity_main;
	private ModelMain model_main;
	
	/**
	 * 
	 */
	public PresenterMain(ActivityMain activity_main) {
		// TODO Auto-generated constructor stub
		this.setActivity_main(activity_main);
		this.setModel_main(new ModelMain(this));
	}

	/**
	 * @return the activity_main
	 */
	public ActivityMain getActivity_main() {
		return activity_main;
	}

	/**
	 * @param activity_main the activity_main to set
	 */
	public void setActivity_main(ActivityMain activity_main) {
		this.activity_main = activity_main;
	}

	/**
	 * @return the model_main
	 */
	public ModelMain getModel_main() {
		return model_main;
	}

	/**
	 * @param model_main the model_main to set
	 */
	public void setModel_main(ModelMain model_main) {
		this.model_main = model_main;
	}

}
