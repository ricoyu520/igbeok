/*
 * $HeadURL: https://springside.googlecode.com/svn/springside3/trunk/modules/core/src/test/java/org/springside/modules/unit/utils/ThreadUtilsTest.java $
 * $Id: ThreadUtilsTest.java 1213 2010-09-11 16:28:22Z calvinxiu $
 * Copyright (c) 2010 by Ericsson, all rights reserved.
 */

package org.springside.modules.unit.utils;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.ThreadUtils;
import org.springside.modules.utils.ThreadUtils.CustomizableThreadFactory;

public class ThreadUtilsTest {

	@Test
	public void customizableThreadFactory() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			}
		};
		CustomizableThreadFactory factory = new CustomizableThreadFactory("foo");

		Thread thread = factory.newThread(runnable);
		assertEquals("foo-1", thread.getName());

		Thread thread2 = factory.newThread(runnable);
		assertEquals("foo-2", thread2.getName());
	}

	@Test
	public void gracefulShutdown() throws InterruptedException {

		Logger logger = LoggerFactory.getLogger("test");

		//time enough to shutdown
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Runnable task = new Task(logger, 500, 0);
		pool.execute(task);
		ThreadUtils.gracefulShutdown(pool, 1000, 1000, TimeUnit.MILLISECONDS);
		assertTrue(pool.isTerminated());

		//time not enough to shutdown,call shutdownNow
		pool = Executors.newSingleThreadExecutor();
		task = new Task(logger, 1000, 0);
		pool.execute(task);
		ThreadUtils.gracefulShutdown(pool, 500, 1000, TimeUnit.MILLISECONDS);
		assertTrue(pool.isTerminated());

		//self thread interrupt while calling gracefulShutdown

		final ExecutorService self = Executors.newSingleThreadExecutor();
		task = new Task(logger, 100000, 0);
		self.execute(task);

		final CountDownLatch lock = new CountDownLatch(1);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				lock.countDown();
				ThreadUtils.gracefulShutdown(self, 200000, 200000, TimeUnit.MILLISECONDS);
			}
		});
		thread.start();
		lock.await();
		thread.interrupt();
		ThreadUtils.sleep(500);
	}

	@Test
	public void normalShutdown() throws InterruptedException {

		Logger logger = LoggerFactory.getLogger("test");

		//time not enough to shutdown,write error log.
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Runnable task = new Task(logger, 1000, 0);
		pool.execute(task);
		ThreadUtils.normalShutdown(pool, 500, TimeUnit.MILLISECONDS);
		assertTrue(pool.isTerminated());

		//self thread interrupt while calling shutdown
		final ExecutorService selfpool = Executors.newSingleThreadExecutor();
		task = new Task(logger, 100000, 1000);
		selfpool.execute(task);

		final CountDownLatch lock = new CountDownLatch(1);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.countDown();
				ThreadUtils.normalShutdown(selfpool, 200000, TimeUnit.MILLISECONDS);
			}
		});
		thread.start();
		lock.await();
		thread.interrupt();

		ThreadUtils.sleep(1000);

//		assertEquals("InterruptedException", appender.getFirstLog().getMessage());
	}

	static class Task implements Runnable {
		private Logger logger;

		private int runTime = 0;

		private int sleepTime;

		Task(Logger logger, int sleepTime, int runTime) {
			this.logger = logger;
			this.sleepTime = sleepTime;
			this.runTime = runTime;
		}

		@Override
		public void run() {
			System.out.println("start task");
			if (runTime > 0) {
				long start = System.currentTimeMillis();
				while (System.currentTimeMillis() - start < runTime) {
				}
			}

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				logger.warn("InterruptedException");
			}
		}
	}
}
