package academy.softserve.eschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.AddedUsersDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.service.base.CredentialsMailingServiceBase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialsMailingService implements CredentialsMailingServiceBase{
    
    @Value("#{messageSource.getMessage(\"credentials.email.body\", null, T(java.util.Locale).getDefault())}")
    private String emailTemplate;
    
    @Value("#{messageSource.getMessage(\"credentials.email.subject\", null, T(java.util.Locale).getDefault())}")
    private String subjectTemplate;
    
    @Value("#{messageSource.getMessage(\"html.table_row\", null, T(java.util.Locale).getDefault())}")
    private String htmlTableRow;
    
    @Value("#{messageSource.getMessage(\"html.table_data\", null, T(java.util.Locale).getDefault())}")
    private String htmlTableData;
    
    @Value("${spring.mail.username}")
    private String adminEmail;
    
    @NonNull
    UserRepository userRepo;
    
    @NonNull
    ClassRepository classRepo;
    
    @NonNull
    EmailService emailService;
    
    @NonNull
    PasswordDecodeService passwordDecodeSevice;

    @Override
    public void sendStudentsCredentials(Integer classId) {
        Clazz clazz = classRepo.getOne(classId);
        String subject = String.format(subjectTemplate, clazz.getName() + " " + clazz.getAcademicYear());
        List<AddedUsersDTO> students = userRepo.getAllStudentsCredentials(classId);
        sendCredentials(subject, students);
    }

    @Override
    public void sendTeachersCredentials() {
        String subject = String.format(subjectTemplate, "");
        List<AddedUsersDTO> teachers = userRepo.getAllTeachersCredentials();
        sendCredentials(subject, teachers);
    }
    
    private void sendCredentials(String subject, List<AddedUsersDTO> users) {
        String output = "";
        users = passwordDecodeSevice.decodemultiple(users);
        for (AddedUsersDTO user : users) {
            output += formatUserToTableRow(user);
        }
        emailService.sendHtmlMessage(adminEmail, subject, String.format(emailTemplate, output));
    }
    
    private String wrapInTd (String input) {
        return String.format(htmlTableData, input);
    }
    
    private String formatUserToTableRow(AddedUsersDTO user) {
        return String.format(htmlTableRow, wrapInTd(user.getLastname()) +
                wrapInTd(user.getFirstname()) +
                wrapInTd(user.getPatronymic()) +
                wrapInTd(user.getLogin()) +
                wrapInTd(user.getPassword()));
    }

}
