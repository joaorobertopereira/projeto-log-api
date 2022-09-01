package br.com.contato.api.logger.enums;

import java.util.concurrent.ThreadFactory;

public enum DaemonThreadFactory implements ThreadFactory {
	INSTANCE;
	
	private DaemonThreadFactory() {
		
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
