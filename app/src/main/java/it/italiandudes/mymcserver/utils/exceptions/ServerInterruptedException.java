package it.italiandudes.mymcserver.utils.exceptions;

import java.io.InterruptedIOException;

public class ServerInterruptedException extends InterruptedException {

    public ServerInterruptedException() throws InterruptedIOException {
        throw new InterruptedIOException();
    }

}
