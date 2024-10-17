	package com.example.MailSernder.ServiceImpl;


import java.io.IOException;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.MailSernder.Entity.EmailDetails;
import com.example.MailSernder.ExcelReader.ExcelFileReader;
import com.example.MailSernder.GenrateChart.CreateChart;
import com.example.MailSernder.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	 //private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired private JavaMailSender javaMailSender;
	@Autowired private ExcelFileReader excelFileReader;
    @Autowired private CreateChart createChart;
	@Value("${spring.mail.username}") private String sender;
    
    
    final String filePath = "C:\\Users\\new\\Downloads\\chart\\piechart.xlsx";
   
	
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
           // EmailServiceImpl.readExcelFile();
            Map<String, Double> readDataFromExcel = excelFileReader.readDataFromExcel(filePath);
            // Sending the mail
            createChart.createPieChart(readDataFromExcel, "PIE chart");
           
            
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail" ;
        }
    }
        
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
            
            Map<String, Double> readDataFromExcel = excelFileReader.readDataFromExcel(filePath);
            
            
           
            
            String htmlContent = "<html>"
                    + "<body>"
                 //   + "<h1>" + details.getSubject() + "</h1>"
                    + "<p>" + details.getMsgBody() + "</p>"
                    + "<img src='cid:pieChartImage' />"  // Content-ID used here to reference the embedded image
                    + "<P>" + details.getSignature() + "</p>"
                    + "</body>"
                    + "</html>";
            
            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.addInline("pieChartImage", createChart.createPieChart(readDataFromExcel, "PIE chart"));
            
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException | IOException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
        
    

}
