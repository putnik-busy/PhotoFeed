package com.justapp.photofeed.data.repository;

import android.text.TextUtils;

import com.justapp.photofeed.data.keystore.KeyStoreManager;
import com.justapp.photofeed.data.network.AuthInterceptor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * @author Sergey Rodionov
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class AuthRepositoryImplTest {

    private static final String USER_TOKEN = "10325476h";

    @Mock
    private AuthInterceptor mAuthInterceptor;
    @Mock
    private KeyStoreManager mKeyStoreManager;
    @InjectMocks
    private AuthRepositoryImpl mAuthRepository;

    @Before
    public void setup() {
        mockStatic(TextUtils.class);
        when(mKeyStoreManager.getToken()).thenReturn(USER_TOKEN);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(invocation -> {
            CharSequence charSequence = (CharSequence) invocation.getArguments()[0];
            return !(charSequence != null && charSequence.length() != 0);
        });
    }

    @Test
    public void saveTokenShouldPass() throws Exception {
        mAuthRepository.saveToken(USER_TOKEN);
        ArgumentCaptor<String> tokenCaptor = ArgumentCaptor.forClass(String.class);

        verify(mKeyStoreManager).saveToken(tokenCaptor.capture());
        assertThat(tokenCaptor.getValue(), is(USER_TOKEN));

        verify(mAuthInterceptor).setToken(tokenCaptor.capture());
        assertThat(tokenCaptor.getValue(), is(USER_TOKEN));
    }

    @Test
    public void hasTokenShouldPass() throws Exception {
        boolean actual = mAuthRepository.hasToken();
        assertThat(actual, is(true));
    }

    @Test
    public void onLogoffShouldPass() throws Exception {
        mAuthRepository.onLogoff();
        verify(mKeyStoreManager).deleteToken();
    }

}
