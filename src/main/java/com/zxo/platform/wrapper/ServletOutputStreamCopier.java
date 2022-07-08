package com.zxo.platform.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Http Servlet Response Copier
 *
 * @ClassName: ServletOutputStreamCopier
 * @Author: zzzxxxooo
 * @Date: 2022/6/10 15:57
 * @Note:
 */
public class ServletOutputStreamCopier extends ServletOutputStream {

    private final OutputStream outputStream;

    private final ByteArrayOutputStream copy;

    public ServletOutputStreamCopier (OutputStream outputStream) {
        this.outputStream = outputStream;
        this.copy = new ByteArrayOutputStream(1024);
    }

    @Override
    public void write (int b) throws IOException {
        outputStream.write(b);
        copy.write(b);
    }

    public byte[] getCopy () {
        return copy.toByteArray();
    }

    @Override
    public boolean isReady () {
        return false;
    }

    @Override
    public void setWriteListener (WriteListener writeListener) {
    }
}
