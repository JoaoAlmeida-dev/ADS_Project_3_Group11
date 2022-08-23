package pt.iscte.asd.projectn3.group11.services.algorithms;

import org.moeaframework.util.progress.ProgressEvent;
import org.moeaframework.util.progress.ProgressListener;
import pt.iscte.asd.projectn3.group11.services.LogService;

public final class CustomProgressListener implements ProgressListener {

	private final CustomAlgorithmService algorithmService;

	public CustomProgressListener(CustomAlgorithmService algorithmService) {
		this.algorithmService = algorithmService;
	}

	@Override
	public void progressUpdate(ProgressEvent progressEvent) {
		final double progress = progressEvent.getPercentComplete();
		final double timeLeft = progressEvent.getRemainingTime();
		final String resultForLogging = progress + "%;" + timeLeft + " sec left";
		LogService.getInstance().info(resultForLogging);
		this.algorithmService.setProgress(progress);
	}
}