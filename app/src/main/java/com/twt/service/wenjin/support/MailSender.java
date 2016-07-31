package com.twt.service.wenjin.support;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by tjliqy on 2016/7/30.
 */


public class MailSender extends Authenticator {
    private String mailhost = "smtp.163.com";
    private String user;
    private String password;
    private Session session;
    public MailSender(String user, String password) {
        this.user = user;
        this.password = password;
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        session = session.getInstance(props,this);
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        try{
            MimeMessage message = new MimeMessage(session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            message.saveChanges();
            try {
                session.setDebug(true);
                Transport trans = session.getTransport("smtp");
                trans.connect(mailhost,user, password);
                trans.send(message);
            } catch (AuthenticationFailedException ae) {
                ae.printStackTrace();
                Log.d("lqy","xxx");
            } catch (MessagingException mex) {
                mex.printStackTrace();
                Log.d("lqy","xxxmex");
                Exception ex = null;
                if ((ex = mex.getNextException()) != null) {
                    ex.printStackTrace();
                    Log.d("lqy","xxxex");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.d("lqy","xxxe");
        }
    }
    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;
        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }
        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }
        public String getName() {
            return "ByteArrayDataSource";
        }
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}
