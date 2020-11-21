// TODO: THIS HAVE DOWNLOAD
package net.tpc.mcdownloader.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.tpc.mcdownloader.IconLoader;
import net.tpc.mcdownloader.MinecraftDownloader;
import net.tpc.mcdownloader.ProgressListener;

public class DownloaderWindow extends JFrame implements ActionListener, WindowListener, ProgressListener {

	private static final long	serialVersionUID	= 3428150718495407844L;

	private Container			layout;

	private JProgressBar		progressBar;
	private JButton				buttonStart;

	private Container			containerOutput;
	private JButton				buttonOutput;
	private JTextField			textFieldOutput;

	private JButton				buttonExit;

	public DownloaderWindow() {
		super("Minecraft Downloader");
		addWindowListener(this);
		setIconImages(IconLoader.loadIcons("/net/tpc/mcdownloader/resources", "icon"));

		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setLayout(new BorderLayout(5, 5));

		layout = new Container();
		layout.setLayout(new BorderLayout(5, 5));

		containerOutput = new Container();
		containerOutput.setLayout(new BorderLayout(5, 5));

		textFieldOutput = new JTextField();
		textFieldOutput.setText(MinecraftDownloader.getOutputPath().getAbsolutePath());
		textFieldOutput.setEditable(false);
		containerOutput.add(textFieldOutput, BorderLayout.CENTER);

		buttonOutput = new JButton();
		buttonOutput.setText("Output folder ...");
		buttonOutput.setActionCommand("ChooseOutput");
		buttonOutput.addActionListener(this);
		containerOutput.add(buttonOutput, BorderLayout.EAST);

		layout.add(containerOutput, BorderLayout.NORTH);

		buttonStart = new JButton();
		buttonStart.setText("Start");
		buttonStart.setActionCommand("Start");
		buttonStart.addActionListener(this);
		layout.add(buttonStart, BorderLayout.CENTER);

		progressBar = new JProgressBar();
		layout.add(progressBar, BorderLayout.SOUTH);

		add(layout, BorderLayout.CENTER);

		buttonExit = new JButton("Exit");
		buttonExit.setActionCommand("Exit");
		buttonExit.addActionListener(this);

		add(buttonExit, BorderLayout.EAST);

		progressBar.setStringPainted(true);
		progressBar.setMaximum(1000);
		progressBar.setMinimum(0);

		MinecraftDownloader.addProgressListener(this);

		setResizable(false);
		pack();
		setSize(450, this.getSize().height);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			if (!MinecraftDownloader.isDownloading()) {
				MinecraftDownloader.startDownloading();
				buttonStart.setText("Stop");
				buttonOutput.setEnabled(false);
				progressBar.setString(null);
				progressBar.setValue(0);
			} else {
				if (MinecraftDownloader.isAborted()) {
					return;
				}
				MinecraftDownloader.stopDownloading();
				buttonStart.setText("Stopping ...");
			}
		} else if (e.getActionCommand().equals("ChooseOutput")) {
			JFileChooser fchooser = new JFileChooser();
			fchooser.setDialogTitle("Choose the output directory ...");
			fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fchooser.setAcceptAllFileFilterUsed(false);
			if (fchooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				MinecraftDownloader.setOutputPath(fchooser.getSelectedFile());
				textFieldOutput.setText(fchooser.getSelectedFile().getAbsolutePath());
			}
		} else if (e.getActionCommand().equals("Exit")) {
			windowClosing(null);
		}
	}

	@Override
	public void onProgressChanged(String value, float f) {
		if (f < 0.0f) {
			progressBar.setString(value);
			progressBar.setValue(0);
		} else {
			progressBar.setString(value + " - " + (Math.floor(f * 1000.0f) / 10) + "%");
			progressBar.setValue((int) (f * 1000.0f));
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (!MinecraftDownloader.isDownloading()) {
			setVisible(false);
			dispose();
		} else {
			MinecraftDownloader.stopDownloading();
			while (!MinecraftDownloader.isStopped()) {
			}
			setVisible(false);
			dispose();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void onStopped(StoppingReason reason) {
		progressBar.setValue(0);
		buttonOutput.setEnabled(true);
		buttonStart.setText("Start");

		switch (reason) {
			case Aborted:
				progressBar.setString("Stopped by user!");
				break;
			case Finished:
				progressBar.setString("Finished!");
				progressBar.setValue(1000);
				break;
			case Exception:
				progressBar.setString(MinecraftDownloader.getLastException().getMessage());
				break;
			default:
				break;
		}
	}
}
