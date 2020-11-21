package net.tpc.mcdownloader;

public interface ProgressListener {

	public enum StoppingReason {
		Finished, Aborted, Exception
	}

	public void onProgressChanged(String state, float f);

	public void onStopped(StoppingReason reason);

}
