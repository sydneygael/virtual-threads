package com.edwyn.threads;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 1
public class VirtualThreadCreation {

    static final Logger logger = LoggerFactory.getLogger(VirtualThreadCreation.class);

    @SneakyThrows
    public static void main(String[] args) {
        // Exemple d'utilisation de platformThread
        Runnable platformTask = () -> {
            try {
                Thread.sleep(1000);
                logger.info("Platform thread executed: {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };

        Thread platformThread = CustomThreadFactory.platformThread("PlatformThread-1", platformTask);
        platformThread.join(); // Attendre que le thread se termine

        // Exemple d'utilisation de virtualThread
        Runnable virtualTask = () -> {
            try {
                Thread.sleep(1000);
                logger.info("Virtual thread executed: {} ",Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread virtualThread = CustomThreadFactory.virtualThread("VirtualThread-1", virtualTask);
        virtualThread.join();
    }
}