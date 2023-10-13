//package arnergy.solar.service;
//
//import arnergy.solar.dto.Registration;
//import arnergy.solar.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {UserImpl.class})
//@ExtendWith(SpringExtension.class)
//class UserImplTest {
//    @Autowired
//    private UserImpl userImpl;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    /**
//     * Method under test: {@link UserImpl#register(Registration)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testRegister() {
//        // TODO: Complete this test.
//        //   Reason: R026 Failed to create Spring context.
//        //   Attempt to initialize test context failed with
//        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
//        //       at java.util.Optional.map(Optional.java:260)
//        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
//        //       at java.util.Optional.map(Optional.java:260)
//        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
//        //       at java.util.Optional.map(Optional.java:260)
//        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
//        //       at java.util.Optional.map(Optional.java:260)
//        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
//        //       at java.util.Optional.map(Optional.java:260)
//        //   See https://diff.blue/R026 to resolve this issue.
//
//        Registration registration = new Registration("Jane", "Doe", "jane.doe@example.org", "iloveyou", "iloveyou");
//
//        userImpl.register(registration);
//    }
//}
//
