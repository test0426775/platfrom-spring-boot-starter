package com.zxo.platform.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @ClassName: ResponseWrapper
 * @Author: zzzxxxooo
 * @Date: 2022/6/10 16:04
 * @Note:
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private ServletOutputStream outputStream;
    private PrintWriter writer;
    private ServletOutputStreamCopier copier;

    public ResponseWrapper (HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream () throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }

        if (outputStream == null) {
            outputStream = getResponse().getOutputStream();
            copier = new ServletOutputStreamCopier(outputStream);
        }

        return copier;
    }

    @Override
    public PrintWriter getWriter () throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }

        if (writer == null) {
            copier = new ServletOutputStreamCopier(getResponse().getOutputStream());
            writer = new PrintWriter(new OutputStreamWriter(copier, DEFAULT_CHARACTER_ENCODING), true);
        }

        return writer;
    }

    @Override
    public void flushBuffer () throws IOException {
        if (writer != null) {
            writer.flush();
        } else if (outputStream != null) {
            copier.flush();
        }
    }

    public byte[] getCopy () {
        if (copier != null) {
            return copier.getCopy();
        } else {
            return new byte[0];
        }
    }
}

