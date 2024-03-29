package com.rect2m.componentsforpc.domain.impl;

import com.rect2m.componentsforpc.domain.exception.SignUpException;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.entity.impl.User.Role;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Supplier;
import org.mindrot.bcrypt.BCrypt;

public class SignUpService {

    private static final int VERIFICATION_CODE_EXPIRATION_MINUTES = 1;
    private static LocalDateTime codeCreationTime;
    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // відправлення на пошту
    private static void sendVerificationCodeEmail(String email, String verificationCode) {
        // Властивості для конфігурації підключення до поштового сервера
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io"); // Замініть на власний
        properties.put("mail.smtp.port", "2525"); // Замініть на власний SMTP порт
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Отримання сесії з автентифікацією
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("faaa8205efd03b", "4c32bd69c62b98");
            }
        });

        try {
            // Створення об'єкта MimeMessage
            Message message = new MimeMessage(session);

            // Встановлення відправника
            message.setFrom(new InternetAddress("from@example.com")); // Замініть на власну адресу

            // Встановлення отримувача
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Встановлення теми
            message.setSubject("Код підтвердження");

            // Встановлення тексту повідомлення
            message.setText("Ваш код підтвердження: " + verificationCode);

            // Відправлення повідомлення
            Transport.send(message);

            System.out.println("Повідомлення успішно відправлено.");

        } catch (MessagingException e) {
            throw new RuntimeException(
                    "Помилка при відправці електронного листа: " + e.getMessage());
        }
    }

    public static String generateAndSendVerificationCode(String email) {
        // Генерація 6-значного коду
        String verificationCode = String.valueOf((int) (Math.random() * 900000 + 100000));

        sendVerificationCodeEmail(email, verificationCode);

        codeCreationTime = LocalDateTime.now();

        return verificationCode;
    }

    // Перевірка введеного коду
    public static void verifyCode(String inputCode, String generatedCode) {
        LocalDateTime currentTime = LocalDateTime.now();
        long minutesElapsed = ChronoUnit.MINUTES.between(codeCreationTime, currentTime);

        if (minutesElapsed > VERIFICATION_CODE_EXPIRATION_MINUTES) {
            throw new SignUpException("Час верифікації вийшов. Спробуйте ще раз.");
        }

        if (!inputCode.equals(generatedCode)) {
            throw new SignUpException("Невірний код підтвердження.");
        }

        // Скидання часу створення коду
        codeCreationTime = null;
    }

    public void signUp(String username,
            String password,
            String email,
            String phoneNumber,
            String address,
            Supplier<String> waitForUserInput) {
        signUp(username, password, email, phoneNumber, address, Role.GENERAL, waitForUserInput);
    }

    public void signUp(String username,
            String password,
            String email,
            String phoneNumber,
            String address,
            Role role,
            Supplier<String> waitForUserInput) {

        String verificationCode = generateAndSendVerificationCode(email);
        String userInputCode = waitForUserInput.get();

        verifyCode(userInputCode, verificationCode);

        // Додаємо користувача, якщо перевірка успішна
        userRepository.add(
                new User(UUID.randomUUID(),
                        username,
                        BCrypt.hashpw(password, BCrypt.gensalt()),
                        email,
                        phoneNumber,
                        address,
                        role)
        );
    }
}