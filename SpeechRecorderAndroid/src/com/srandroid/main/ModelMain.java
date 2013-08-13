/**
 * 
 */
package com.srandroid.main;

/**
 * @author fanli
 *
 */
public class ModelMain {

	// Presenter
	private PresenterMain presenter_main;
	
	
	/**
	 * 
	 */
	public ModelMain(PresenterMain presenter_main) {
		this.setPresenter_main(presenter_main);
	}


	/**
	 * @return the presenter_main
	 */
	public PresenterMain getPresenter_main() {
		return presenter_main;
	}


	/**
	 * @param presenter_main the presenter_main to set
	 */
	public void setPresenter_main(PresenterMain presenter_main) {
		this.presenter_main = presenter_main;
	}

}
