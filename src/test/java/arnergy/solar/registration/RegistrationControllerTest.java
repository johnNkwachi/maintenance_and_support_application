package arnergy.solar.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import arnergy.solar.email.EmailSender;
import arnergy.solar.registration.dtos.ConfirmationTokenRequest;
import arnergy.solar.registration.dtos.RegistrationRequest;
import arnergy.solar.registration.dtos.ResendTokenRequest;
import arnergy.solar.registration.tokens.ConfirmationToken;
import arnergy.solar.registration.tokens.ConfirmationTokenRepository;
import arnergy.solar.registration.tokens.ConfirmationTokenServiceImpl;
import arnergy.solar.user.User;
import arnergy.solar.user.UserImpl;
import arnergy.solar.user.UserRepository;
import arnergy.solar.user.UserRole;
import arnergy.solar.user.dtos.PasswordResetRequest;
import arnergy.solar.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class RegistrationControllerTest {
    /**
     * Method under test: {@link RegistrationController#register(RegistrationRequest, HttpServletRequest)}
     */
    @Test
    void testRegister() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setDisabled(true);
        user2.setEmailAddress("42 Main St");
        user2.setFirstName("Jane");
        user2.setId("42");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUserRole(UserRole.ADMIN);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setExpiredAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setId("42");
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user2);
        ConfirmationTokenRepository tokenRepository = mock(ConfirmationTokenRepository.class);
        when(tokenRepository.save(Mockito.<ConfirmationToken>any())).thenReturn(confirmationToken);
        UserImpl userService = new UserImpl(userRepository, new ConfirmationTokenServiceImpl(tokenRepository));

        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(userService,
                emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("42 Main St");
        registrationRequest.setFirstName("Jane");
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("iloveyou");
        ResponseEntity<?> actualRegisterResult = registrationController.register(registrationRequest,
                new MockHttpServletRequest());
        assertTrue(actualRegisterResult.hasBody());
        assertEquals(201, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        assertEquals("", ((ApiResponse) actualRegisterResult.getBody()).getPath());
        assertTrue(((ApiResponse) actualRegisterResult.getBody()).isSuccessful());
        assertEquals(201, ((ApiResponse) actualRegisterResult.getBody()).getStatus());
        verify(userRepository).save(Mockito.<User>any());
        verify(tokenRepository).save(Mockito.<ConfirmationToken>any());
        verify(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link RegistrationController#register(RegistrationRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister2() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.mail.MessagingException
        //       at arnergy.solar.registration.RegistrationServiceImpl.emailBuilder(RegistrationServiceImpl.java:50)
        //       at arnergy.solar.registration.RegistrationServiceImpl.register(RegistrationServiceImpl.java:46)
        //       at arnergy.solar.registration.RegistrationController.register(RegistrationController.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setDisabled(true);
        user2.setEmailAddress("42 Main St");
        user2.setFirstName("Jane");
        user2.setId("42");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUserRole(UserRole.ADMIN);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setExpiredAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setId("42");
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user2);
        ConfirmationTokenRepository tokenRepository = mock(ConfirmationTokenRepository.class);
        when(tokenRepository.save(Mockito.<ConfirmationToken>any())).thenReturn(confirmationToken);
        UserImpl userService = new UserImpl(userRepository, new ConfirmationTokenServiceImpl(tokenRepository));

        EmailSender emailSender = mock(EmailSender.class);
        doThrow(new MessagingException()).when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("42 Main St");
        registrationRequest.setFirstName("Jane");
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("iloveyou");
        registrationController.register(registrationRequest, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link RegistrationController#register(RegistrationRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister3() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getRequestURI()" because "httpServletRequest" is null
        //       at arnergy.solar.registration.RegistrationController.register(RegistrationController.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setDisabled(true);
        user2.setEmailAddress("42 Main St");
        user2.setFirstName("Jane");
        user2.setId("42");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setUserRole(UserRole.ADMIN);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setExpiredAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        confirmationToken.setId("42");
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user2);
        ConfirmationTokenRepository tokenRepository = mock(ConfirmationTokenRepository.class);
        when(tokenRepository.save(Mockito.<ConfirmationToken>any())).thenReturn(confirmationToken);
        UserImpl userService = new UserImpl(userRepository, new ConfirmationTokenServiceImpl(tokenRepository));

        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmailAddress("42 Main St");
        registrationRequest.setFirstName("Jane");
        registrationRequest.setLastName("Doe");
        registrationRequest.setPassword("iloveyou");
        registrationController.register(registrationRequest, null);
    }

    /**
     * Method under test: {@link RegistrationController#confirmToken(ConfirmationTokenRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfirmToken() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: arnergy.solar.registration.dtos.ConfirmationTokenRequest$MockitoMock$HlvWMbX1["mockitoInterceptor"]->org.mockito.internal.creation.bytebuddy.MockMethodInterceptor["serializationSupport"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:53)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:30)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   arnergy.solar.exception.RegistrationException: jane.doe@example.org does not exist
        //       at arnergy.solar.registration.RegistrationServiceImpl.lambda$confirmToken$0(RegistrationServiceImpl.java:58)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at arnergy.solar.registration.RegistrationServiceImpl.confirmToken(RegistrationServiceImpl.java:58)
        //       at arnergy.solar.registration.RegistrationController.confirmToken(RegistrationController.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImpl userService = new UserImpl(userRepository,
                new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class)));

        EmailSender emailSender = mock(EmailSender.class);
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(userService,
                emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));
        ConfirmationTokenRequest confirmationTokenRequest = mock(ConfirmationTokenRequest.class);
        when(confirmationTokenRequest.getEmail()).thenReturn("jane.doe@example.org");
        when(confirmationTokenRequest.getToken()).thenReturn("ABC123");
        registrationController.confirmToken(confirmationTokenRequest, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link RegistrationController#confirmToken(ConfirmationTokenRequest, HttpServletRequest)}
     */
    @Test
    void testConfirmToken2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: arnergy.solar.registration.dtos.ConfirmationTokenRequest$MockitoMock$HlvWMbX1["mockitoInterceptor"]->org.mockito.internal.creation.bytebuddy.MockMethodInterceptor["serializationSupport"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:53)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:30)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        RegistrationService registrationService = mock(RegistrationService.class);
        when(registrationService.confirmToken(Mockito.<ConfirmationTokenRequest>any())).thenReturn("ABC123");
        RegistrationController registrationController = new RegistrationController(registrationService);
        ConfirmationTokenRequest confirmationTokenRequest = mock(ConfirmationTokenRequest.class);
        when(confirmationTokenRequest.getEmail()).thenReturn("jane.doe@example.org");
        when(confirmationTokenRequest.getToken()).thenReturn("ABC123");
        ResponseEntity<?> actualConfirmTokenResult = registrationController.confirmToken(confirmationTokenRequest,
                new MockHttpServletRequest());
        assertTrue(actualConfirmTokenResult.hasBody());
        assertEquals(200, actualConfirmTokenResult.getStatusCodeValue());
        assertTrue(actualConfirmTokenResult.getHeaders().isEmpty());
        assertEquals("", ((ApiResponse) actualConfirmTokenResult.getBody()).getPath());
        assertTrue(((ApiResponse) actualConfirmTokenResult.getBody()).isSuccessful());
        assertEquals("ABC123", ((ApiResponse) actualConfirmTokenResult.getBody()).getData());
        assertEquals(200, ((ApiResponse) actualConfirmTokenResult.getBody()).getStatus());
        verify(registrationService).confirmToken(Mockito.<ConfirmationTokenRequest>any());
    }

    /**
     * Method under test: {@link RegistrationController#confirmToken(ConfirmationTokenRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConfirmToken3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: arnergy.solar.registration.dtos.ConfirmationTokenRequest$MockitoMock$HlvWMbX1["mockitoInterceptor"]->org.mockito.internal.creation.bytebuddy.MockMethodInterceptor["serializationSupport"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:53)
        //       at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:30)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getRequestURI()" because "httpServletRequest" is null
        //       at arnergy.solar.registration.RegistrationController.confirmToken(RegistrationController.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        RegistrationService registrationService = mock(RegistrationService.class);
        when(registrationService.confirmToken(Mockito.<ConfirmationTokenRequest>any())).thenReturn("ABC123");
        RegistrationController registrationController = new RegistrationController(registrationService);
        ConfirmationTokenRequest confirmationTokenRequest = mock(ConfirmationTokenRequest.class);
        when(confirmationTokenRequest.getEmail()).thenReturn("jane.doe@example.org");
        when(confirmationTokenRequest.getToken()).thenReturn("ABC123");
        registrationController.confirmToken(confirmationTokenRequest, null);
    }

    /**
     * Method under test: {@link RegistrationController#resendToken(ResendTokenRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResendToken() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   arnergy.solar.exception.RegistrationException: jane.doe@example.org does not exit.
        //       at arnergy.solar.registration.RegistrationServiceImpl.lambda$resendToken$1(RegistrationServiceImpl.java:74)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at arnergy.solar.registration.RegistrationServiceImpl.resendToken(RegistrationServiceImpl.java:74)
        //       at arnergy.solar.registration.RegistrationController.resendToken(RegistrationController.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImpl userService = new UserImpl(userRepository,
                new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class)));

        EmailSender emailSender = mock(EmailSender.class);
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        ResendTokenRequest tokenRequest = new ResendTokenRequest();
        tokenRequest.setEmail("jane.doe@example.org");
        registrationController.resendToken(tokenRequest, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link RegistrationController#resendToken(ResendTokenRequest, HttpServletRequest)}
     */
    @Test
    void testResendToken2() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        UserImpl userService = mock(UserImpl.class);
        when(userService.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(userService.findUserByEmailAddressIgnoreCase(Mockito.<String>any())).thenReturn(ofResult);
        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        ResendTokenRequest tokenRequest = new ResendTokenRequest();
        tokenRequest.setEmail("jane.doe@example.org");
        ResponseEntity<?> actualResendTokenResult = registrationController.resendToken(tokenRequest,
                new MockHttpServletRequest());
        assertTrue(actualResendTokenResult.hasBody());
        assertEquals(200, actualResendTokenResult.getStatusCodeValue());
        assertTrue(actualResendTokenResult.getHeaders().isEmpty());
        assertEquals("", ((ApiResponse) actualResendTokenResult.getBody()).getPath());
        assertTrue(((ApiResponse) actualResendTokenResult.getBody()).isSuccessful());
        assertEquals("Token Sent", ((ApiResponse) actualResendTokenResult.getBody()).getData());
        assertEquals(200, ((ApiResponse) actualResendTokenResult.getBody()).getStatus());
        verify(userService).generateToken(Mockito.<String>any());
        verify(userService).findUserByEmailAddressIgnoreCase(Mockito.<String>any());
        verify(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link RegistrationController#resendToken(ResendTokenRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResendToken3() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getRequestURI()" because "httpServletRequest" is null
        //       at arnergy.solar.registration.RegistrationController.resendToken(RegistrationController.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        UserImpl userService = mock(UserImpl.class);
        when(userService.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(userService.findUserByEmailAddressIgnoreCase(Mockito.<String>any())).thenReturn(ofResult);
        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        ResendTokenRequest tokenRequest = new ResendTokenRequest();
        tokenRequest.setEmail("jane.doe@example.org");
        registrationController.resendToken(tokenRequest, null);
    }

    /**
     * Method under test: {@link RegistrationController#resetPassword(PasswordResetRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   arnergy.solar.exception.UserException: jane.doe@example.org email does not exist
        //       at arnergy.solar.registration.RegistrationServiceImpl.lambda$resetPassword$2(RegistrationServiceImpl.java:83)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at arnergy.solar.registration.RegistrationServiceImpl.resetPassword(RegistrationServiceImpl.java:83)
        //       at arnergy.solar.registration.RegistrationController.resetPassword(RegistrationController.java:81)
        //   See https://diff.blue/R013 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImpl userService = new UserImpl(userRepository,
                new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class)));

        EmailSender emailSender = mock(EmailSender.class);
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        PasswordResetRequest resetRequest = new PasswordResetRequest();
        resetRequest.setEmail("jane.doe@example.org");
        resetRequest.setNewPassword("iloveyou");
        resetRequest.setOldPassword("iloveyou");
        registrationController.resetPassword(resetRequest, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link RegistrationController#resetPassword(PasswordResetRequest, HttpServletRequest)}
     */
    @Test
    void testResetPassword2() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        UserImpl userService = mock(UserImpl.class);
        when(userService.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(userService.findUserByEmailAddressIgnoreCase(Mockito.<String>any())).thenReturn(ofResult);
        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        PasswordResetRequest resetRequest = new PasswordResetRequest();
        resetRequest.setEmail("jane.doe@example.org");
        resetRequest.setNewPassword("iloveyou");
        resetRequest.setOldPassword("iloveyou");
        ResponseEntity<?> actualResetPasswordResult = registrationController.resetPassword(resetRequest,
                new MockHttpServletRequest());
        assertTrue(actualResetPasswordResult.hasBody());
        assertEquals(200, actualResetPasswordResult.getStatusCodeValue());
        assertTrue(actualResetPasswordResult.getHeaders().isEmpty());
        assertEquals("", ((ApiResponse) actualResetPasswordResult.getBody()).getPath());
        assertTrue(((ApiResponse) actualResetPasswordResult.getBody()).isSuccessful());
        assertEquals("Token sent to Email for Confirmation",
                ((ApiResponse) actualResetPasswordResult.getBody()).getData());
        assertEquals(200, ((ApiResponse) actualResetPasswordResult.getBody()).getStatus());
        verify(userService).generateToken(Mockito.<String>any());
        verify(userService, atLeast(1)).findUserByEmailAddressIgnoreCase(Mockito.<String>any());
        verify(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link RegistrationController#resetPassword(PasswordResetRequest, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResetPassword3() throws MessagingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getRequestURI()" because "httpServletRequest" is null
        //       at arnergy.solar.registration.RegistrationController.resetPassword(RegistrationController.java:87)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setDisabled(true);
        user.setEmailAddress("42 Main St");
        user.setFirstName("Jane");
        user.setId("42");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setUserRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        UserImpl userService = mock(UserImpl.class);
        when(userService.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(userService.findUserByEmailAddressIgnoreCase(Mockito.<String>any())).thenReturn(ofResult);
        EmailSender emailSender = mock(EmailSender.class);
        doNothing().when(emailSender).send(Mockito.<String>any(), Mockito.<String>any());
        RegistrationController registrationController = new RegistrationController(new RegistrationServiceImpl(
                userService, emailSender, new ConfirmationTokenServiceImpl(mock(ConfirmationTokenRepository.class))));

        PasswordResetRequest resetRequest = new PasswordResetRequest();
        resetRequest.setEmail("jane.doe@example.org");
        resetRequest.setNewPassword("iloveyou");
        resetRequest.setOldPassword("iloveyou");
        registrationController.resetPassword(resetRequest, null);
    }
}

